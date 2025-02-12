package org.action.user.req;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.action.request.BaseRequest;

/**
 * @author gzw
 * @description：
 * @since：2025/2/9 12:37
 */

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserPageQueryRequest extends BaseRequest {

    /**
     * 手机号关键字
     */
    private String keyWord;
    /**
     * 用户状态
     */
    private String state;
    /**
     * 当前页
     */
    private int currentPage;
    /**
     * 页面大小
     */
    private int pageSize;

}
