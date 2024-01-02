package com.framework.demo.mapper;


import cn.hutool.core.collection.CollUtil;
import com.framework.demo.coverter.out.RoleDTOConvert;
import com.framework.demo.dto.RoleDTO;
import com.framework.demo.entity.Role;
import com.framework.demo.pojo.role.RolePageQuery;
import com.ty.mid.framework.common.pojo.PageResult;
import com.ty.mid.framework.core.spring.SpringContextHelper;
import com.ty.mid.framework.mybatisplus.core.mapper.BaseMapperX;
import com.ty.mid.framework.mybatisplus.core.query.LambdaQueryWrapperX;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author suyouliang
 * @since 2023-11-27
 */
@Mapper
public interface RoleMapper extends BaseMapperX<Role, Long> {

    default PageResult<Role> selectPage(@Param("rolePageQuery") RolePageQuery rolePageQuery) {

        LambdaQueryWrapperX<Role> wrapper = new LambdaQueryWrapperX<Role>()
                .inIfPresent(Role::getCode, rolePageQuery.getCodeList())
                .likeIfPresent(Role::getName, rolePageQuery.getNameLike())
                .inIfPresent(Role::getStatus, rolePageQuery.getStatusList());

        return selectPage(rolePageQuery, wrapper);
    }

    ;

    default List<RoleDTO> covertRole(Collection<Long> roleIdList) {
        if (CollUtil.isEmpty(roleIdList)) {
            return Collections.emptyList();
        }
        RoleMapper roleMapper = SpringContextHelper.getBean(RoleMapper.class);
        List<Role> roleList = roleMapper.selectList(Role::getId, roleIdList);
        return RoleDTOConvert.INSTANCE.convert(roleList);
    }
}
