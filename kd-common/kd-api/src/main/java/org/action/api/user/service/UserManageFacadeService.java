package org.action.api.user.service;

import org.action.api.user.req.UserRegisterRequest;
import org.action.api.user.resp.UserOperatorResponse;

/**
 * @author gzw
 * @description：
 * @since：2025/2/10 21:45
 */
public interface UserManageFacadeService {


    /**
     * 管理用户注册
     *
     * @param userRegisterRequest req
     * @return resp
     */
    UserOperatorResponse registerAdmin(UserRegisterRequest userRegisterRequest);

    /**
     * 用户冻结
     *
     * @param userId userId
     * @return resp
     */
    UserOperatorResponse freeze(Long userId);

    /**
     * 用户解冻
     *
     * @param userId userId
     * @return  resp
     */
    UserOperatorResponse unfreeze(Long userId);
}
