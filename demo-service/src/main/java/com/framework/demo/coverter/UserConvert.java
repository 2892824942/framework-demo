package com.framework.demo.coverter;

import com.framework.demo.entity.User;
import com.framework.demo.pojo.UserSaveQuery;
import com.framework.demo.pojo.UserUpdateQuery;
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
