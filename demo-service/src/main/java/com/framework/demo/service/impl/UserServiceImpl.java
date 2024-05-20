package com.framework.demo.service.impl;


import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
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
import com.ty.mid.framework.service.wrapper.AutoWrapService;
import com.ty.mid.framework.service.wrapper.UserNameTranslation;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
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
@Valid
public class UserServiceImpl extends AutoWrapService<User, UserFullDTO, UserMapper> implements IUserService, UserNameTranslation {

    @Resource
    private UserMapper userMapper;

    @Override
    public UserFullDTO getById(Long id) {
        if (isNegative(id)) {
            return null;
        }
        return selectOneDTO(User::getId, id);
    }

    @Override
    public List<UserFullDTO> getByIds(List<Long> ids) {
        List<User> userList = listByIds(ids);
        return convert2DTO(userList);
    }

    @Override
    public PageResult<UserFullBO> getPage(UserQuery userQuery) {
        return userMapper.selectJoinPage(userQuery);
    }

    @Override
    public List<UserFullDTO> getFullList(UserQuery userQuery) {
        userQuery.openSelectAll();
        PageResult<User> userPageResult = userMapper.selectPage(userQuery);
        return convert(userPageResult.getList(), UserFullDTO.class);
    }

    @Override
    public List<UserInfoDTO> getInfoList(UserQuery userQuery) {
        userQuery.setPageNo(PageParam.PAGE_SIZE_NONE);
        PageResult<User> userPageResult = userMapper.selectPage(userQuery);
        return convert(userPageResult.getList(), UserInfoDTO.class);
    }

    @Override
    public Boolean save(UserSaveQuery query) {
        User user = convert(query);
        return userMapper.insert(user) > 0;
    }

    @Override
    public Boolean updatePassWord(String password,Long userId) {
        return update(new LambdaUpdateWrapper<User>().eq(User::getId, userId).set(User::getPassword,password));
    }


    @Override
    public void saveBatch(List<UserSaveQuery> queryList) {
        if (CollUtil.isEmpty(queryList)) {
            return;
        }
        List<User> userList = convert(queryList, User.class);

        userMapper.insertBatch(userList);
    }

    @Override
    public Boolean deleteById(Long id) {
        return userMapper.deleteById(id) > 0;
    }

    @Override
    public UserFullDTO getByUserName(@NotNull String name) {
        return selectOneDTO(User::getName, name);

    }

    /**
     * 业务暂时使用接口方式
     *
     * @param collection
     * @return
     */
    @Override
    public Map<Long, String> getUserNameMap(Collection<Long> collection) {
        List<User> userList = userMapper.selectList(User::getId, collection);
        if (CollUtil.isEmpty(userList)) {
            return Collections.emptyMap();
        }
        return userList.stream().collect(Collectors.toMap(User::getId, User::getName, (a, b) -> a));
    }
}
