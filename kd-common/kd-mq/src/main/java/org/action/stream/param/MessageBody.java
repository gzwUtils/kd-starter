package org.action.stream.param;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author gzw
 */
@Data
@Accessors(chain = true)
public class MessageBody {
    /**
     * 幂等号
     */
    private String identifier;
    /**
     * 消息体
     */
    private String body;
}