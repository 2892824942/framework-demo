package com.framework.demo.coverter.out;

import com.framework.demo.coverter.BaseConvert;
import com.framework.demo.dto.RoleDTO;
import com.framework.demo.entity.Role;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * 用户入值 Covert
 *
 * @author 芋道源码
 */
@Mapper
public interface RoleDTOConvert extends BaseConvert {

    RoleDTOConvert INSTANCE = Mappers.getMapper(RoleDTOConvert.class);

    RoleDTO covert(Role role);
    List<RoleDTO> covert(List<Role> role);
}
