package org.action;
import org.action.exception.BizException;
import org.action.resp.SmsSendResponse;

/**
 * @author gzw
 * @description： sms
 * @since：2024/8/2 15:13
 */
public interface SmsService {


    /**
     *  发送短信
     * @param context 短信内容
     * @param phoneNumber phone
     * @return resp
     */

    SmsSendResponse sendMsg(String phoneNumber, String context) throws BizException;

}
