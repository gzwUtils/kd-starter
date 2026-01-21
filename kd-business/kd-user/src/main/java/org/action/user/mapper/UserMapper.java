package org.action.user.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import jakarta.validation.constraints.NotNull;
import org.action.user.common.entity.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author gzw
 * @description：
 * @since：2025/2/10 23:46
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

    /**
     * 根据id查询用户
     *
     * @param id id
     * @return user
     */
    User findById(long id);

    /**
     * 根据昵称查询用户
     *
     * @param nickname nk
     * @return user
     */
    User findByNickname(@NotNull String nickname);

    /**
     * 根据邀请码查询用户
     * @param inviteCode inCode
     * @return user
     */
    User findByInviteCode(@NotNull String inviteCode);

    /**
     * 根据手机号查询用户
     *
     * @param telephone tel
     * @return user
     */
    User findByTelephone(@NotNull String telephone);

    /**
     * 根据昵称和密码查询用户
     *
     * @param telephone  tel
     * @param passwordHash pass
     * @return user
     */
    User findByTelephoneAndPass(String telephone, String passwordHash);

}
