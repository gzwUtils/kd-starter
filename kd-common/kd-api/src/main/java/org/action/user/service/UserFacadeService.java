package org.action.user.service;

import org.action.user.req.*;
import org.action.user.resp.UserOperatorResponse;
import org.action.user.resp.UserQueryResponse;
import org.action.user.resp.data.UserInfo;

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
