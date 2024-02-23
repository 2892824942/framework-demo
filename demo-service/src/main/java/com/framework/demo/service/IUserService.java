package com.framework.demo.service;


import com.framework.demo.dto.UserFullDTO;
import com.framework.demo.dto.UserInfoDTO;
import com.framework.demo.entity.bo.UserFullBO;
import com.framework.demo.pojo.user.UserQuery;
import com.framework.demo.pojo.user.UserSaveQuery;
import com.ty.mid.framework.common.pojo.PageResult;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author suyouliang
 * @since 2023-11-27
 */
public interface IUserService {

    UserFullDTO getById(Long id);

    PageResult<UserFullBO> getPage(UserQuery userQuery);

    List<UserFullDTO> getFullList(UserQuery userQuery);

    List<UserInfoDTO> getInfoList(UserQuery userQuery);

    Boolean save(UserSaveQuery query);

    void saveBatch(List<UserSaveQuery> query);

    Boolean deleteById(Long id);
}
