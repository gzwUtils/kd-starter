package org.action.notice.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.action.notice.entity.Notice;
import org.action.notice.enums.NoticeState;
import org.action.notice.enums.NoticeType;
import static org.action.enums.RepoErrorCode.NOTICE_SAVE_FAILED;
import org.action.exception.BizException;
import org.action.notice.kd.mapper.NoticeMapper;
import org.springframework.stereotype.Service;

/**
 * @author gzw
 * @description：
 * @since：2024/8/2 19:36
 */
@Service
public class NoticeService extends ServiceImpl<NoticeMapper, Notice> {

    private static final String SMS_NOTICE_TITLE = "验证码";

    public Page<Notice> pageQueryForRetry(int currentPage, int pageSize) {
        Page<Notice> page = new Page<>(currentPage, pageSize);
        QueryWrapper<Notice> wrapper = new QueryWrapper<>();
        wrapper.between("state", NoticeState.INIT.name(),NoticeState.FAILED);

        wrapper.orderBy(true, true, "create_time");

        return this.page(page, wrapper);
    }


    public Notice saveCaptcha(String telephone, String captcha) {
        Notice notice = Notice.builder()
                .noticeTitle(SMS_NOTICE_TITLE)
                .noticeContent(captcha)
                .noticeType(NoticeType.SMS)
                .targetAddress(telephone)
                .state(NoticeState.INIT)
                .build();

        boolean saveResult = this.save(notice);

        if (!saveResult) {
            throw new BizException(NOTICE_SAVE_FAILED);
        }

        return notice;
    }


}
