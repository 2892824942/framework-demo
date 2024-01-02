package com.framework.demo.service.impl;


import com.framework.demo.coverter.out.AddrDTOConvert;
import com.framework.demo.dto.AddrDTO;
import com.framework.demo.entity.Address;
import com.framework.demo.mapper.AddressMapper;
import com.framework.demo.service.IAddressService;
import com.ty.mid.framework.common.util.GenericsUtil;
import com.ty.mid.framework.mybatisplus.service.wrapper.AutoWrapService;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Map;
import java.util.function.Function;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author suyouliang
 * @since 2023-11-27
 */
@Service
public class AddressServiceImpl extends AutoWrapService<Address, AddrDTO, AddressMapper> implements IAddressService {

    @Override
    public Map<?, AddrDTO> autoWrap(Collection<?> collection) {
        Function<Address, AddrDTO> function = AddrDTOConvert.INSTANCE::convert;
        return convert(GenericsUtil.check2Collection(collection),Address::getCode, AddrDTO::getCode, function);
    }
}
