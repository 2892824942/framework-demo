package com.framework.demo.coverter;

import com.framework.demo.entity.Role;
import com.framework.demo.entity.User;
import com.framework.demo.pojo.role.RoleSaveQuery;
import com.framework.demo.pojo.role.RoleUpdateQuery;
import com.framework.demo.pojo.user.UserSaveQuery;
import com.framework.demo.pojo.user.UserUpdateQuery;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * 用户 Covert
 *
 * @author 芋道源码
 */
@Mapper
public interface RoleConvert {

    RoleConvert INSTANCE = Mappers.getMapper(RoleConvert.class);

    Role convert(RoleSaveQuery roleSaveQuery);

    Role convert(RoleUpdateQuery roleUpdateQuery);
}
