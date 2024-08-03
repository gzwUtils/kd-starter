package org.action.notice.resp;

import org.action.response.BaseResponse;

/**
 * @author gzw
 * @description：
 * @since：2024/8/2 19:16
 */
public class NoticeResponse extends BaseResponse {

    public static class Builder{
        private final NoticeResponse noticeResponse;
        public Builder(){
            noticeResponse = new NoticeResponse();
        }

        public Builder setCode(String code){
            noticeResponse.setCode(code);
            return this;
        }

        public Builder setMsg(String msg){
            noticeResponse.setMsg(msg);
            return this;
        }

        public Builder setSuccess(boolean success) {
            noticeResponse.setSuccess(success);
            return this;
        }

        public NoticeResponse build() {
            return noticeResponse;
        }

    }
}
