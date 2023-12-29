package com.framework.demo.service;


import com.framework.demo.dto.RoleDTO;
import com.framework.demo.entity.Role;
import com.framework.demo.pojo.role.RolePageQuery;
import com.framework.demo.pojo.role.RoleSaveQuery;
import com.ty.mid.framework.common.pojo.PageResult;

import java.util.Collection;
import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author suyouliang
 * @since 2023-11-27
 */
public interface IRoleService {

    Role getByCode(String code);

    PageResult<Role> getPage(RolePageQuery rolePageQuery);

    List<RoleDTO> covertRole(Collection<Long> roleIdList);

    Boolean save(RoleSaveQuery query);

    void saveBatch(List<RoleSaveQuery> query);

    Boolean deleteById(Long id);
}
