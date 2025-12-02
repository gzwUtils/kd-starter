package org.action.api.notice.service;
import org.action.api.notice.resp.NoticeResponse;

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


}
