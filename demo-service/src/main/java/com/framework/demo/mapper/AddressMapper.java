package com.framework.demo.mapper;


import com.framework.demo.entity.Address;
import com.framework.demo.pojo.addr.AddrQuery;
import com.ty.mid.framework.common.pojo.PageResult;
import com.ty.mid.framework.mybatisplus.core.mapper.BaseMapperX;
import com.ty.mid.framework.mybatisplus.core.query.LambdaQueryWrapperX;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author suyouliang
 * @since 2023-11-27
 */
@Mapper
public interface AddressMapper extends BaseMapperX<Address, Long> {

    default PageResult<Address> getPage(AddrQuery addrQuery) {
        LambdaQueryWrapperX<Address> wrapper = new LambdaQueryWrapperX<Address>()
                .eqIfPresent(Address::getId, addrQuery.getId())
                .in(Address::getCode, addrQuery.getCodes())
                .likeLeft(Address::getName, addrQuery.getName());
        return selectPage(addrQuery, wrapper);
    }
}
