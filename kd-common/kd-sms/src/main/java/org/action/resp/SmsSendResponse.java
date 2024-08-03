package org.action.resp;

import lombok.Data;
import org.action.response.BaseResponse;

/**
 * @author gzw
 * @description：
 * @since：2024/8/2 15:14
 */
@SuppressWarnings("all")
@Data
public class SmsSendResponse extends BaseResponse {

    private String smsid;

}
