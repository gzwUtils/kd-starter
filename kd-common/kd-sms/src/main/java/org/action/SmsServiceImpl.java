package org.action;

import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson2.JSON;
import java.util.HashMap;
import java.util.Map;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.action.exception.BizException;
import org.action.resp.SmsSendResponse;

/**
 * @author gzw
 * @description： sms send
 * @since：2024/8/2 15:17
 */
@Setter
@Slf4j
public class SmsServiceImpl implements SmsService {

    private String account;

    private String smsPassword;

    private String host;

    @DistributeLock(scene = "send_sms",keyExpression = "#phoneNumber")
    @Override
    public SmsSendResponse sendMsg(String phoneNumber, String context) throws BizException {
        Map<String, Object> map = new HashMap<>();
        map.put("account",account);
        map.put("password",smsPassword);
        map.put("mobile",phoneNumber);
        map.put("content",context);
        map.put("format","json");
        String result = HttpUtil.post(host, map);
        return JSON.parseObject(result, SmsSendResponse.class);
    }
}
