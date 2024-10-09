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
     * 发送验证码
     *
     * @param phone phone
     * @return resp
     */
    NoticeResponse generateAndSendSmsCaptcha(String phone);



    /**
     * 发送模板消息
     *
     * @param sendInput phone
     * @return resp
     */
    NoticeResponse generateModelContext(SendInput sendInput);
}
