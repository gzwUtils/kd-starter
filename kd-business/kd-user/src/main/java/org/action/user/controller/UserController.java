package org.action.user.controller;

import cn.dev33.satoken.stp.StpUtil;
import javax.annotation.Resource;
import org.action.user.common.entity.User;
import org.action.user.common.entity.convertor.UserConvertor;
import static org.action.user.common.enums.UserErrorCode.USER_NOT_EXIST;
import org.action.user.exception.UserException;
import org.action.user.facade.service.UserService;
import org.action.user.resp.data.BasicUserInfo;
import org.action.user.resp.data.UserInfo;
import org.action.web.vo.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
/**
 * @author gzw
 * @description：
 * @since：2025/2/10 22:19
 */

@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService userService;


    @GetMapping("/getUserInfo")
    public Result<UserInfo> getUserInfo() {
        String userId = (String) StpUtil.getLoginId();
        User user = userService.findById(Long.valueOf(userId));

        if (user == null) {
            throw new UserException(USER_NOT_EXIST);
        }
        return Result.success(UserConvertor.INSTANCE.mapToVo(user));
    }

    @GetMapping("/queryUserByTel")
    public Result<BasicUserInfo> queryUserByTel(String telephone) {
        User user = userService.findByTelephone(telephone);
        if (user == null) {
            throw new UserException(USER_NOT_EXIST);
        }
        return Result.success(UserConvertor.INSTANCE.mapToBasicVo(user));
    }

}
