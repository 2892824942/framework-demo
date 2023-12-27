package com.framework.demo.coverter.out;

import com.framework.demo.coverter.AbstractConvert;
import com.framework.demo.dto.UserAddrDTO;
import com.framework.demo.dto.UserFullDTO;
import com.framework.demo.entity.User;
import com.framework.demo.entity.bo.UserFullBO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

/**
 * 用户出值 Covert
 *
 * @author 芋道源码
 */
@Mapper
public interface UserConvert extends AbstractConvert {

    UserConvert INSTANCE = Mappers.getMapper(UserConvert.class);

    UserAddrDTO convert(UserFullBO userFullBO);

    @Mappings({
            @Mapping(target = "addrInfo",  expression =  "java(convertAddr(user.getAddrCode()))")
    })
    UserFullDTO convert(User user);

}
