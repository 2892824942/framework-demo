package com.framework.demo.mapper;


import com.framework.demo.entity.Role;
import com.framework.demo.entity.User;
import com.framework.demo.pojo.role.RolePageQuery;
import com.framework.demo.pojo.user.UserPageQuery;
import com.ty.mid.framework.common.pojo.PageResult;
import com.ty.mid.framework.mybatisplus.core.mapper.BaseMapperX;
import com.ty.mid.framework.mybatisplus.core.query.LambdaQueryWrapperX;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author suyouliang
 * @since 2023-11-27
 */
@Mapper
public interface RoleMapper extends BaseMapperX<Role,Long> {

    default PageResult<Role> selectPage(@Param("rolePageQuery") RolePageQuery rolePageQuery){

        LambdaQueryWrapperX<Role> wrapper = new LambdaQueryWrapperX<Role>()
                .inIfPresent(Role::getCode, rolePageQuery.getCodeList())
                .likeIfPresent(Role::getName,rolePageQuery.getNameLike())
                .inIfPresent(Role::getStatus, rolePageQuery.getStatusList());

        return selectPage(rolePageQuery, wrapper);
    };
}
