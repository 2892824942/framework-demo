package com.framework.demo.coverter.out;

import com.framework.demo.coverter.BaseConvert;
import com.framework.demo.dto.AddrDTO;
import com.framework.demo.entity.Address;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * 用户入值 Covert
 *
 * @author 芋道源码
 */
@Mapper
public interface AddrDTOConvert extends BaseConvert {

    AddrDTOConvert INSTANCE = Mappers.getMapper(AddrDTOConvert.class);

    AddrDTO covert(Address address);

    List<AddrDTO> covert(List<Address> address);
}
