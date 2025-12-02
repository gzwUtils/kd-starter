package org.action.user.common.entity.convertor;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.action.api.user.enums.UserStateEnum;
import org.action.api.user.resp.data.BasicUserInfo;
import org.action.api.user.resp.data.UserInfo;
import org.action.user.common.entity.User;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-12-02T00:56:04+0800",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.3 (Oracle Corporation)"
)
public class UserConvertorImpl implements UserConvertor {

    @Override
    public UserInfo mapToVo(User request) {
        if ( request == null ) {
            return null;
        }

        UserInfo userInfo = new UserInfo();

        if ( request.getId() != null ) {
            userInfo.setUserId( request.getId().longValue() );
        }
        if ( request.getCreateTime() != null ) {
            userInfo.setCreateTime( request.getCreateTime() );
        }
        if ( request.getNickName() != null ) {
            userInfo.setNickName( request.getNickName() );
        }
        if ( request.getProfilePhotoUrl() != null ) {
            userInfo.setProfilePhotoUrl( request.getProfilePhotoUrl() );
        }
        if ( request.getTelephone() != null ) {
            userInfo.setTelephone( request.getTelephone() );
        }
        if ( request.getState() != null ) {
            userInfo.setState( request.getState().name() );
        }
        if ( request.getCertification() != null ) {
            userInfo.setCertification( request.getCertification() );
        }
        if ( request.getUserRole() != null ) {
            userInfo.setUserRole( request.getUserRole() );
        }
        if ( request.getInviteCode() != null ) {
            userInfo.setInviteCode( request.getInviteCode() );
        }

        return userInfo;
    }

    @Override
    public BasicUserInfo mapToBasicVo(User request) {
        if ( request == null ) {
            return null;
        }

        BasicUserInfo basicUserInfo = new BasicUserInfo();

        if ( request.getId() != null ) {
            basicUserInfo.setUserId( request.getId().longValue() );
        }
        if ( request.getNickName() != null ) {
            basicUserInfo.setNickName( request.getNickName() );
        }
        if ( request.getProfilePhotoUrl() != null ) {
            basicUserInfo.setProfilePhotoUrl( request.getProfilePhotoUrl() );
        }

        return basicUserInfo;
    }

    @Override
    public User mapToEntity(UserInfo request) {
        if ( request == null ) {
            return null;
        }

        User user = new User();

        if ( request.getUserId() != null ) {
            user.setId( request.getUserId().intValue() );
        }
        if ( request.getCreateTime() != null ) {
            user.setCreateTime( request.getCreateTime() );
        }
        if ( request.getNickName() != null ) {
            user.setNickName( request.getNickName() );
        }
        if ( request.getState() != null ) {
            user.setState( Enum.valueOf( UserStateEnum.class, request.getState() ) );
        }
        if ( request.getInviteCode() != null ) {
            user.setInviteCode( request.getInviteCode() );
        }
        if ( request.getTelephone() != null ) {
            user.setTelephone( request.getTelephone() );
        }
        if ( request.getProfilePhotoUrl() != null ) {
            user.setProfilePhotoUrl( request.getProfilePhotoUrl() );
        }
        if ( request.getCertification() != null ) {
            user.setCertification( request.getCertification() );
        }
        if ( request.getUserRole() != null ) {
            user.setUserRole( request.getUserRole() );
        }

        return user;
    }

    @Override
    public List<UserInfo> mapToVo(List<User> request) {
        if ( request == null ) {
            return null;
        }

        List<UserInfo> list = new ArrayList<UserInfo>( request.size() );
        for ( User user : request ) {
            list.add( mapToVo( user ) );
        }

        return list;
    }
}
