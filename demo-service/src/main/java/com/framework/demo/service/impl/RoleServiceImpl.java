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
import com.ty.mid.framework.common.entity.BaseIdDO;
import com.ty.mid.framework.common.pojo.PageResult;
import com.ty.mid.framework.lock.annotation.FailFastLock;
import com.ty.mid.framework.service.integrate.GenericAutoWrapService;
import com.ty.mid.framework.service.wrapper.AutoWrapService;
import com.ty.mid.framework.service.wrapper.core.AutoWrapper;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

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

    @Resource
    private IRoleService _self;


    @Override
    public RoleDTO getByCode(String code) {
        return RoleDTOConvert.INSTANCE.convert(roleMapper.selectOne(Role::getCode, code));
    }

    @Override
    public PageResult<RoleDTO> getPage(RoleQuery roleQuery) {
        PageResult<Role> rolePageResult = roleMapper.selectPage(roleQuery);
        return covertPage(rolePageResult, RoleDTOConvert.INSTANCE::convert);
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
    //@Transactional(propagation = Propagation.REQUIRES_NEW)
    @Transactional(rollbackFor = Exception.class)
    @FailFastLock(keys = "#query.code")
    public Boolean save(RoleSaveQuery query) {

        Role role = RoleConvert.INSTANCE.convert(query);
        return roleMapper.insert(role) > 0;
    }

    @Override
    @Transactional
    public Boolean test(RoleSaveQuery query) {
        _self.save(query);
        return _self.save(query);
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
    public AutoWrapper<Role> roleSimpleDTOAutoWrapper() {
        //注意:不可以省略后面的泛型否则报错
        return new AutoWrapService<Role, RoleSimpleDTO, RoleMapper>() {
            @Override
            public Map<?, RoleSimpleDTO> autoWrap(Collection<?> collection) {
                //return this.convert(collection);  //BeanUtil复制
                return this.convert(collection, RoleDTOConvert.INSTANCE::convert2Simple);
            }

        };
    }


}
