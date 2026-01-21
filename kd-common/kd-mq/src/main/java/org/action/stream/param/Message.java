package org.action.stream.param;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author gzw
 * @description： 消息
 * 2025/8/2 00:01
 */
@Data
@Accessors(chain = true)
public class Message {
    /**
     * 消息id
     */
    private String msgId;
    /**
     * 消息体
     */
    private String body;
}