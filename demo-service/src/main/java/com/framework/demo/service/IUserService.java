package com.framework.demo.service;


import com.framework.demo.entity.User;
import com.framework.demo.pojo.UserPageQuery;
import com.ty.mid.framework.common.pojo.PageResult;

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
}
