package com.framework.demo.coverter;

import com.framework.demo.entity.Address;
import com.framework.demo.pojo.addr.AddrSaveQuery;
import com.framework.demo.pojo.addr.AddrUpdateQuery;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * 用户入值 Covert
 *
 * @author 芋道源码
 */
@Mapper
public interface AddrConvert {

    AddrConvert INSTANCE = Mappers.getMapper(AddrConvert.class);

    Address convert(AddrSaveQuery addrSaveQuery);

    List<Address> convert(List<AddrSaveQuery> addrSaveQuery);

    Address convert(AddrUpdateQuery addrUpdateQuery);

}
