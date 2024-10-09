package org.action.notice.kdcade;
import cn.hutool.core.util.RandomUtil;
import com.alibaba.fastjson2.JSON;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.TimeUnit;
import javax.annotation.Resource;
import org.action.SlidingWindowRateLimiter;
import org.action.SmsService;
import org.action.notice.entity.Notice;
import org.action.notice.entity.TemplateInfo;
import org.action.notice.enums.NoticeState;
import static org.action.enums.RepoErrorCode.SEND_NOTICE_DUPLICATED;
import static org.action.enums.RepoErrorCode.TEMPLATE_NOT_FOUND;
import static org.action.notice.constant.Constant.*;
import static org.action.notice.constant.NoticeConstant.CAPTCHA_KEY_PREFIX;
import org.action.exception.SystemException;
import org.action.notice.req.SendInput;
import org.action.notice.resp.NoticeResponse;
import org.action.notice.service.NoticeKdCadeService;
import org.action.resp.SmsSendResponse;
import org.action.notice.service.NoticeService;
import org.action.notice.service.TemplateService;
import org.action.notice.utils.FreemarkerUtils;
import org.action.rpc.kdCade.KdCade;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * @author gzw
 * @description：
 * @since：2024/8/2 19:32
 */
@DubboService(version = "1.0.0")
public class NoticeKdCadeServiceImpl implements NoticeKdCadeService {

    @Resource
    private SlidingWindowRateLimiter slidingWindowRateLimiter;

    @Resource
    private NoticeService noticeService;

    @Resource
    private SmsService smsService;

    @Resource
    private TemplateService templateService;

    @Resource
    private FreemarkerUtils freemarkerUtils;

    @Resource
    private RedisTemplate redisTemplate;

    @Override
    public NoticeResponse generateAndSendSmsCaptcha(String telephone) {
        Boolean access = slidingWindowRateLimiter.tryAcquire(telephone, 1, 60);

        if (Boolean.FALSE.equals(access)) {
            throw new SystemException(SEND_NOTICE_DUPLICATED);
        }

        TemplateInfo templateInfo = templateService.QueryTemplateInfo(DEFAULT_NOTICE_CAPTCHA);

        if(templateInfo == null) {
            throw new SystemException(TEMPLATE_NOT_FOUND);
        }

        // 生成验证码
        String captcha = RandomUtil.randomNumbers(4);


        Map<String, String> variables = new HashMap<>();

        variables.put(DEFAULT_NOTICE_CAPTCHA_VARIABLE,captcha);

        String context = freemarkerUtils.freeMarkerRender(variables, templateInfo.getTempContent(), templateInfo.getTempId());

        // 验证码存入Redis
        redisTemplate.opsForValue().set(CAPTCHA_KEY_PREFIX + telephone, captcha, 5, TimeUnit.MINUTES);

        Notice notice = noticeService.saveCaptcha(telephone, context,DEFAULT_NOTICE_CAPTCHA_NAME);

        return getNoticeResponse(notice);
    }


    @KdCade
    @Override
    public NoticeResponse generateModelContext(SendInput sendInput) {
        Boolean access = slidingWindowRateLimiter.tryAcquire(sendInput.getMessageParam().getReceiver(), 1, 60);

        if (Boolean.FALSE.equals(access)) {
            throw new SystemException(SEND_NOTICE_DUPLICATED);
        }

        TemplateInfo templateInfo = templateService.QueryTemplateInfo(sendInput.getMessageTemplateId().toString());

        if(templateInfo == null) {
            throw new SystemException(TEMPLATE_NOT_FOUND);
        }
        String context = freemarkerUtils.freeMarkerRender(sendInput.getMessageParam().getVariables(), templateInfo.getTempContent(), templateInfo.getTempId());

        Notice notice = noticeService.saveCaptcha(sendInput.getMessageParam().getReceiver(), context,templateInfo.getTempName());


        return getNoticeResponse(notice);
    }


    private NoticeResponse getNoticeResponse(Notice notice) {
        SmsSendResponse result = smsService.sendMsg(notice.getTargetAddress(), notice.getNoticeContent());
        if (Objects.equals(result.getCode(), "2")) {
            notice.setState(NoticeState.SUCCESS);
            notice.setSendSuccessTime(new Date());
            noticeService.updateById(notice);
        } else {
            notice.setState(NoticeState.FAILED);
            notice.addExtendInfo("executeResult", JSON.toJSONString(result));
            noticeService.updateById(notice);
        }

        return new NoticeResponse.Builder().setSuccess(true).build();
    }
}
