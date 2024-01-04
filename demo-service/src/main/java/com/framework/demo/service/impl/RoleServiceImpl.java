package com.framework.demo.service.impl;


import cn.hutool.core.collection.CollUtil;
import com.framework.demo.coverter.RoleConvert;
import com.framework.demo.coverter.out.RoleDTOConvert;
import com.framework.demo.dto.RoleDTO;
import com.framework.demo.dto.RoleSimpleDTO;
import com.framework.demo.entity.Role;
import com.framework.demo.mapper.RoleMapper;
import com.framework.demo.pojo.role.RoleQuery;
import com.framework.demo.pojo.role.RoleSaveQuery;
import com.framework.demo.service.IRoleService;
import com.ty.mid.framework.common.pojo.PageResult;
import com.ty.mid.framework.mybatisplus.service.integrate.GenericAutoWrapService;
import com.ty.mid.framework.mybatisplus.service.wrapper.core.AutoWrapper;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
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
public class RoleServiceImpl extends GenericAutoWrapService<Role, RoleDTO, RoleMapper> implements IRoleService {

    @Resource
    private RoleMapper roleMapper;


    @Override
    public Role getByCode(String code) {
        return roleMapper.selectOne(Role::getCode, code);
    }

    @Override
    public PageResult<Role> getPage(RoleQuery roleQuery) {
        return roleMapper.selectPage(roleQuery);
    }


    @Override
    public List<RoleDTO> covertRole(Collection<Long> roleIdList) {
        if (CollUtil.isEmpty(roleIdList)) {
            return Collections.emptyList();
        }

        List<Role> roleList = roleMapper.selectList(Role::getId, roleIdList);
        return RoleDTOConvert.INSTANCE.convert(roleList);
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


    @Bean
    public AutoWrapper<Role, RoleSimpleDTO, RoleMapper> roleSimpleDTOAutoWrapper() {
        //注意:不可以省略后面的泛型否则报错
        return new AutoWrapper<Role, RoleSimpleDTO, RoleMapper>() {
            @Override
            public Map<?, RoleSimpleDTO> autoWrap(Collection<?> collection) {
                List<Role> mDo = roleMapper.selectList(Role::getId, collection);
                List<RoleSimpleDTO> roleSimpleDTOS = RoleDTOConvert.INSTANCE.convert2Simple(mDo);
                if (CollUtil.isEmpty(roleSimpleDTOS)) {
                    return Collections.emptyMap();
                }
                return roleSimpleDTOS.stream().collect(Collectors.toMap(RoleSimpleDTO::getId, Function.identity(), (a, b) -> a));
            }
        };
    }
}
