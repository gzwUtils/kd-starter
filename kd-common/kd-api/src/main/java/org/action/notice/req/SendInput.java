package org.action.notice.req;

import java.io.Serializable;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author gzw
 * @description：
 * @since：2023/5/25 23:55
 */
@Data
@Accessors(chain = true)
public class SendInput implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 消息模板Id
     * 【必填】
     */
    private Long messageTemplateId;


    /**
     * 消息相关的参数
     * 当业务类型为"send"，必传
     */
    private MessageParam messageParam;
}
