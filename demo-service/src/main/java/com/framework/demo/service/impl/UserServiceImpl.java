package com.framework.demo.service.impl;


import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.collection.CollectionUtil;
import com.framework.demo.coverter.UserConvert;
import com.framework.demo.coverter.out.UserDTOConvert;
import com.framework.demo.dto.UserFullDTO;
import com.framework.demo.dto.UserInfoDTO;
import com.framework.demo.entity.User;
import com.framework.demo.entity.bo.UserFullBO;
import com.framework.demo.mapper.UserMapper;
import com.framework.demo.pojo.user.UserQuery;
import com.framework.demo.pojo.user.UserSaveQuery;
import com.framework.demo.service.IUserService;
import com.ty.mid.framework.common.pojo.PageParam;
import com.ty.mid.framework.common.pojo.PageResult;
import com.ty.mid.framework.mybatisplus.service.integrate.GenericAutoWrapService;
import com.ty.mid.framework.mybatisplus.service.wrapper.AutoWrapService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author suyouliang
 * @since 2023-11-27
 */
@Service
public class UserServiceImpl extends GenericAutoWrapService<User, UserFullDTO, UserMapper> implements IUserService {

    @Resource
    private UserMapper userMapper;

    @Override
    public User getById(Long id) {
        return userMapper.selectById(id);
    }

    @Override
    public PageResult<UserFullBO> getPage(UserQuery userQuery) {
        PageResult<UserFullBO> userFullBOPageResult = userMapper.selectJoinPage(userQuery);
        List<UserFullBO> list = userFullBOPageResult.getList();
        if (CollectionUtil.isEmpty(list)) {
            return userFullBOPageResult;
        }
        //组装用户名
        return userFullBOPageResult;

    }

    @Override
    public List<UserFullDTO> getFullList(UserQuery userQuery) {
        userQuery.setPageNo(PageParam.PAGE_SIZE_NONE);
        PageResult<User> userPageResult = userMapper.selectPage(userQuery);
        List<User> userList = userPageResult.getList();
        if (CollectionUtil.isEmpty(userList)) {
            return Collections.emptyList();
        }
        return UserDTOConvert.INSTANCE.convert(userList);

    }

    @Override
    public List<UserInfoDTO> getInfoList(UserQuery userQuery) {
        userQuery.setPageNo(PageParam.PAGE_SIZE_NONE);
        PageResult<User> userPageResult = userMapper.selectPage(userQuery);
        List<User> userList = userPageResult.getList();
        if (CollectionUtil.isEmpty(userList)) {
            return Collections.emptyList();
        }
        return UserDTOConvert.INSTANCE.convert2Info(userList);
    }

    @Override
    public Boolean save(UserSaveQuery query) {
        User user = UserConvert.INSTANCE.convert(query);
        return userMapper.insert(user) > 0;
    }

    @Override
    public void saveBatch(List<UserSaveQuery> queryList) {
        if (CollUtil.isEmpty(queryList)) {
            return;
        }
        List<User> userList = queryList.stream().map(UserConvert.INSTANCE::convert).collect(Collectors.toList());

        userMapper.insertBatch(userList);
    }

    @Override
    public Boolean deleteById(Long id) {
        return userMapper.deleteById(id) > 0;
    }


}
