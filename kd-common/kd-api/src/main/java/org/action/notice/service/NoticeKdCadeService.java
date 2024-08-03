package org.action.notice.service;
import org.action.notice.req.SendInput;
import org.action.notice.resp.NoticeResponse;

/**
 * @author gzw
 * @description：
 * @since：2024/8/2 19:16
 */
public interface NoticeKdCadeService {
    /**
     * 发送短信
     *
     * @param sendInput phone
     * @return resp
     */
    NoticeResponse generateAndSendSmsCaptcha(SendInput sendInput);
}
