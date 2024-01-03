package com.framework.demo.mapper;


import com.framework.demo.entity.Address;
import com.ty.mid.framework.mybatisplus.core.mapper.BaseMapperX;
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

}
