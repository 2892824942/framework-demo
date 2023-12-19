package com.framework.demo.service.impl;


import cn.hutool.core.collection.CollUtil;
import com.framework.demo.coverter.UserConvert;
import com.framework.demo.entity.User;
import com.framework.demo.mapper.UserMapper;
import com.framework.demo.pojo.UserPageQuery;
import com.framework.demo.pojo.UserSaveQuery;
import com.framework.demo.service.IUserService;
import com.ty.mid.framework.common.pojo.PageResult;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author suyouliang
 * @since 2023-11-27
 */
@Service
public class UserServiceImpl implements IUserService {

    @Resource
    private UserMapper userMapper;
    @Override
    public User getById(Long id) {
        return userMapper.selectById(id);
    }

    @Override
    public PageResult<User> getPage(UserPageQuery userPageQuery) {
        PageResult<User> pageResult=userMapper.selectPage(userPageQuery);
        return pageResult;
    }

    @Override
    public Boolean save(UserSaveQuery query) {
        User user = UserConvert.INSTANCE.convert(query);
        return userMapper.insert(user) >0;
    }

    @Override
    public void saveBatch(List<UserSaveQuery> queryList) {
        if (CollUtil.isEmpty(queryList)){
            return;
        }
        List<User> userList = queryList.stream().map(UserConvert.INSTANCE::convert).collect(Collectors.toList());

        userMapper.insertBatch(userList);
    }

    @Override
    public Boolean deleteById(Long id) {
       return userMapper.deleteById(id)>0;
    }
}
