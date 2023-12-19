package com.framework.demo.service;


import com.framework.demo.entity.User;
import com.framework.demo.pojo.UserPageQuery;
import com.framework.demo.pojo.UserSaveQuery;
import com.ty.mid.framework.common.pojo.PageResult;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author suyouliang
 * @since 2023-11-27
 */
public interface IUserService {

    User getById(Long id);

    PageResult<User> getPage(UserPageQuery userPageQuery);

    Boolean save(UserSaveQuery query);

    void saveBatch(List<UserSaveQuery> query);

    Boolean deleteById(Long id);
}
