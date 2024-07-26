package com.framework.demo.service;


import com.framework.demo.dto.UserFullDTO;
import com.framework.demo.dto.UserInfoDTO;
import com.framework.demo.entity.bo.UserFullBO;
import com.framework.demo.pojo.user.UserQuery;
import com.framework.demo.pojo.user.UserSaveQuery;
import com.ty.mid.framework.common.pojo.PageResult;
import org.springframework.scheduling.annotation.Async;

import java.util.List;
import java.util.concurrent.Future;

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
    @Async
    Future<List<UserFullDTO>> getByIdAsync(List<Long> ids);

    @Async
    void getByIdAsync2(List<Long> ids);

    List<UserFullDTO>  getByIds(List<Long> ids);

    PageResult<UserFullBO> getPage(UserQuery userQuery);

    List<UserFullDTO> getFullList(UserQuery userQuery);

    List<UserInfoDTO> getInfoList(UserQuery userQuery);

    Boolean save(UserSaveQuery query);

    Boolean updatePassWord(String password,Long userId);

    void saveBatch(List<UserSaveQuery> query);

    Boolean deleteById(Long id);

    UserFullDTO getByUserName(String name);
}
