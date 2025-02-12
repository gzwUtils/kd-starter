package org.action.user.facade.service;

import com.alibaba.fastjson2.JSON;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import java.util.Date;
import org.action.user.common.entity.User;
import org.action.user.common.entity.UserOperateStream;
import org.action.user.enums.UserOperateTypeEnum;
import org.action.user.mapper.UserOperateStreamMapper;
import org.springframework.stereotype.Service;

/**
 * @author gzw
 * @description：
 * @since：2025/2/12 23:10
 */
@Service
public class UserOperateStreamService extends ServiceImpl<UserOperateStreamMapper, UserOperateStream> {


    public Integer insertStream(User user, UserOperateTypeEnum type) {
        UserOperateStream stream = new UserOperateStream();
        stream.setUserId(String.valueOf(user.getId()));
        stream.setOperateTime(new Date());
        stream.setType(type.name());
        stream.setParam(JSON.toJSONString(user));
        boolean result = save(stream);
        if (result) {
            return stream.getId();
        }
        return null;
    }

}
