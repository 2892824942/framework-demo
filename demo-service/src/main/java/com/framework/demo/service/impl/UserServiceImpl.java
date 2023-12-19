package com.framework.demo.service.impl;


import com.framework.demo.entity.User;
import com.framework.demo.mapper.UserMapper;
import com.framework.demo.pojo.UserPageQuery;
import com.framework.demo.service.IUserService;
import com.ty.mid.framework.common.pojo.PageResult;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

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
}
