package com.framework.demo.service.impl;


import cn.hutool.core.collection.CollUtil;
import com.framework.demo.coverter.RoleConvert;
import com.framework.demo.coverter.out.RoleDTOConvert;
import com.framework.demo.dto.RoleDTO;
import com.framework.demo.entity.Role;
import com.framework.demo.mapper.RoleMapper;
import com.framework.demo.pojo.role.RolePageQuery;
import com.framework.demo.pojo.role.RoleSaveQuery;
import com.framework.demo.service.IRoleService;
import com.ty.mid.framework.common.pojo.PageResult;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Collection;
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
public class RoleServiceImpl implements IRoleService {

    @Resource
    private RoleMapper roleMapper;


    @Override
    public Role getByCode(String code) {
        return roleMapper.selectOne(Role::getCode, code);
    }

    @Override
    public PageResult<Role> getPage(RolePageQuery rolePageQuery) {
        return roleMapper.selectPage(rolePageQuery);
    }

    @Override
    public List<RoleDTO> covertRole(Collection<Long> roleIdList) {
        if (CollUtil.isEmpty(roleIdList)) {
            return Collections.emptyList();
        }
        List<Role> roleList = roleMapper.selectList(Role::getId, roleIdList);
        return RoleDTOConvert.INSTANCE.covert(roleList);
    }

    @Override
    public Boolean save(RoleSaveQuery query) {

        Role role = RoleConvert.INSTANCE.convert(query);
        return roleMapper.insert(role) > 0;
    }

    @Override
    public void saveBatch(List<RoleSaveQuery> queryList) {
        if (CollUtil.isEmpty(queryList)) {
            return;
        }
        List<Role> roleList = queryList.stream().map(RoleConvert.INSTANCE::convert).collect(Collectors.toList());

        roleMapper.insertBatch(roleList);
    }

    @Override
    public Boolean deleteById(Long id) {
        return roleMapper.deleteById(id) > 0;
    }
}
