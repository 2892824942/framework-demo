package com.framework.demo.coverter;

import com.framework.demo.entity.Role;
import com.framework.demo.pojo.role.RoleSaveQuery;
import com.framework.demo.pojo.role.RoleUpdateQuery;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

/**
 * 用户入值 Covert
 *
 * @author 芋道源码
 */
@Mapper
public interface RoleConvert {

    RoleConvert INSTANCE = Mappers.getMapper(RoleConvert.class);

    @Mapping(source = "extra", target = "extra")
    Role convert(RoleSaveQuery roleSaveQuery);

    //@Mapping(source = "extra",target = "extra")
    Role convert(RoleUpdateQuery roleUpdateQuery);

}
