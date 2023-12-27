package com.framework.demo.coverter.out;

import com.framework.demo.coverter.AbstractConvert;
import com.framework.demo.entity.Role;
import com.framework.demo.pojo.role.RoleSaveQuery;
import com.framework.demo.pojo.role.RoleUpdateQuery;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * 用户入值 Covert
 *
 * @author 芋道源码
 */
@Mapper
public interface RoleConvert extends AbstractConvert {

    RoleConvert INSTANCE = Mappers.getMapper(RoleConvert.class);
}
