package com.framework.demo.mapper;


import com.framework.demo.entity.Address;
import com.framework.demo.entity.Role;
import com.framework.demo.pojo.role.RolePageQuery;
import com.ty.mid.framework.common.pojo.PageResult;
import com.ty.mid.framework.mybatisplus.core.mapper.BaseMapperX;
import com.ty.mid.framework.mybatisplus.core.query.LambdaQueryWrapperX;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author suyouliang
 * @since 2023-11-27
 */
@Mapper
public interface AddressMapper extends BaseMapperX<Address,Long> {

}
