package com.framework.demo.coverter.out;

import cn.hutool.core.collection.CollUtil;
import com.framework.demo.entity.User;
import com.framework.demo.mapper.UserMapper;
import com.ty.mid.framework.core.spring.SpringContextHelper;
import com.ty.mid.framework.mybatisplus.service.wrapper.core.BaseAutoConvert;
import org.mapstruct.Mapper;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 用户入值 Covert
 *
 * @author suyouliang
 */
@Mapper
public interface BaseConvert extends BaseAutoConvert {
    @Override
    default Map<Long, String> getUserNameMap(Collection<Long> userIdList) {
        UserMapper userMapper = SpringContextHelper.getBean(UserMapper.class);
        List<User> userList = userMapper.selectList(User::getId, userIdList);
        if (CollUtil.isEmpty(userList)) {
            return Collections.emptyMap();
        }
        return userList.stream().collect(Collectors.toMap(User::getId, User::getName, (a, b) -> a));
    }
}

