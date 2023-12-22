package com.framework.demo.coverter;

import com.framework.demo.entity.User;
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
public interface UserConvert {

    UserConvert INSTANCE = Mappers.getMapper(UserConvert.class);

    User convert(UserSaveQuery userSaveQuery);

    User convert(UserUpdateQuery userUpdateQuery);
}
