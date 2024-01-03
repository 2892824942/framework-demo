package com.framework.demo.coverter.out;

import com.framework.demo.dto.UserAddrDTO;
import com.framework.demo.dto.UserFullDTO;
import com.framework.demo.dto.UserInfoDTO;
import com.framework.demo.entity.User;
import com.framework.demo.entity.bo.UserFullBO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * 用户出值 Covert
 *
 * @author 芋道源码
 */
@Mapper
public interface UserDTOConvert extends BaseConvert {

    UserDTOConvert INSTANCE = Mappers.getMapper(UserDTOConvert.class);

    UserAddrDTO convert(UserFullBO userFullBO);

    UserFullDTO convert(User user);

    List<UserFullDTO> convert(List<User> user);

    List<UserInfoDTO> convert2Info(List<User> user);

}
