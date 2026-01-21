package org.action.api.user.service;

import org.action.api.user.req.UserQueryRequest;
import org.action.api.user.req.UserRegisterRequest;
import org.action.api.user.resp.UserOperatorResponse;
import org.action.api.user.resp.UserQueryResponse;
import org.action.api.user.resp.data.UserInfo;

/**
 * @author gzw
 * @description：
 * @since：2025/2/10 21:43
 */
public interface UserFacadeService {

    /**
     * 用户信息查询
     * @param userQueryRequest user
     * @return resp
     */
    UserQueryResponse<UserInfo> query(UserQueryRequest userQueryRequest);



    /**
     * 用户注册
     * @param userRegisterRequest res
     * @return resp
     */
    UserOperatorResponse register(UserRegisterRequest userRegisterRequest);


}
