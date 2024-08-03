package org.action.notice.req;

import java.io.Serializable;
import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @author gzw
 * @description： 消息参数
 * @since：2023/5/25 22:07
 */

@Data
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MessageParam implements Serializable {

    private static final long serialVersionUID = 1L;


    /** 接收者*/
    private String receiver;

    /**消息内容中的可变部分(占位符替换)*/
    private Map<String, String> variables;

}
