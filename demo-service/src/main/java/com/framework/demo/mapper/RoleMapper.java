package com.framework.demo.mapper;


import com.framework.demo.entity.Role;
import com.framework.demo.pojo.role.RoleQuery;
import com.ty.mid.framework.common.pojo.PageResult;
import com.ty.mid.framework.mybatisplus.core.mapper.BaseMapperX;
import com.ty.mid.framework.mybatisplus.core.query.LambdaQueryWrapperX;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

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

    default PageResult<Role> selectPage(@Param("rolePageQuery") RoleQuery roleQuery) {

        LambdaQueryWrapperX<Role> wrapper = new LambdaQueryWrapperX<Role>()
                .inIfPresent(Role::getCode, roleQuery.getCodeList())
                .likeIfPresent(Role::getName, roleQuery.getNameLike())
                .inIfPresent(Role::getStatus, roleQuery.getStatusList());

        return selectPage(roleQuery, wrapper);
    }


}
