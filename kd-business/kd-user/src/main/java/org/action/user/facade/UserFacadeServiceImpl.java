package org.action.user.facade;

import javax.annotation.Resource;
import org.action.user.common.entity.User;
import org.action.user.common.entity.convertor.UserConvertor;
import org.action.user.facade.service.UserService;
import org.action.user.req.UserQueryRequest;
import org.action.user.req.UserRegisterRequest;
import org.action.user.req.condition.UserIdQueryCondition;
import org.action.user.req.condition.UserPhoneQueryCondition;
import org.action.user.resp.UserOperatorResponse;
import org.action.user.resp.UserQueryResponse;
import org.action.user.resp.data.UserInfo;
import org.action.user.service.UserFacadeService;
import org.apache.dubbo.config.annotation.DubboService;

/**
 * @author gzw
 * @description：
 * @since：2025/2/12 23:41
 */
@DubboService(version = "1.0.0")
public class UserFacadeServiceImpl implements UserFacadeService {


    @Resource
    private UserService userService;

    @Override
    public UserQueryResponse<UserInfo> query(UserQueryRequest userQueryRequest) {
        //使用switch表达式精简代码，如果这里编译不过，参考我的文档调整IDEA的JDK版本
        //文档地址：https://thoughts.aliyun.com/workspaces/6655879cf459b7001ba42f1b/docs/6673f26c5e11940001c810fb#667971268a5c151234adcf92
        User user = new User();
        if(userQueryRequest.getUserQueryCondition() instanceof UserIdQueryCondition){
          user =   userService.findById(((UserIdQueryCondition)userQueryRequest.getUserQueryCondition()).getUserId());
        }
        if(userQueryRequest.getUserQueryCondition() instanceof UserPhoneQueryCondition){
            user =   userService.findByTelephone(((UserPhoneQueryCondition)userQueryRequest.getUserQueryCondition()).getTelephone());
        }
        UserQueryResponse<UserInfo> response = new UserQueryResponse<>();
        response.setSuccess(true);
        UserInfo userInfo = UserConvertor.INSTANCE.mapToVo(user);
        response.setData(userInfo);
        return response;
    }

    @Override
    public UserOperatorResponse register(UserRegisterRequest userRegisterRequest) {
        return userService.register(userRegisterRequest.getTelephone(), userRegisterRequest.getInviteCode());
    }
}
