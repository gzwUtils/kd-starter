package org.action.user.common.entity.convertor;

import java.util.List;

import org.action.api.user.resp.data.BasicUserInfo;
import org.action.api.user.resp.data.UserInfo;
import org.action.user.common.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.factory.Mappers;
/**
 * @author gzw
 * @description：
 * @since：2025/2/10 22:58
 */
@Mapper(nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface UserConvertor {

    UserConvertor INSTANCE = Mappers.getMapper(UserConvertor.class);

    /**
     * 转换为vo
     *
     * @param request req
     * @return user
     */
    @Mapping(target = "userId", source = "request.id")
    public UserInfo mapToVo(User request);

    /**
     * 转换为简单的VO
     * @param request req
     * @return basic
     */
    @Mapping(target = "userId", source = "request.id")
    public BasicUserInfo mapToBasicVo(User request);

    /**
     * 转换为实体
     *
     * @param request req
     * @return user
     */
    @Mapping(target = "id", source = "request.userId")
    public User mapToEntity(UserInfo request);

    /**
     * 转换为VO
     *
     * @param request req
     * @return list
     */
    @Mapping(target = "userId", source = "request.id")
    public List<UserInfo> mapToVo(List<User> request);

}
