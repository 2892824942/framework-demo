package com.framework.demo.mapper;


import cn.hutool.core.collection.CollUtil;
import com.framework.demo.coverter.out.AddrDTOConvert;
import com.framework.demo.dto.AddrDTO;
import com.framework.demo.entity.Address;
import com.framework.demo.entity.Role;
import com.framework.demo.pojo.role.RolePageQuery;
import com.ty.mid.framework.common.pojo.PageResult;
import com.ty.mid.framework.core.spring.SpringContextHelper;
import com.ty.mid.framework.mybatisplus.core.mapper.BaseMapperX;
import com.ty.mid.framework.mybatisplus.core.query.LambdaQueryWrapperX;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

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
    default List<AddrDTO> convertAddr(Collection<String> codeList) {
        if (CollUtil.isEmpty(codeList)) {
            return Collections.emptyList();
        }
        List<Address> addresses = selectList(Address::getCode, codeList);
        return AddrDTOConvert.INSTANCE.covert(addresses);
    }
}
