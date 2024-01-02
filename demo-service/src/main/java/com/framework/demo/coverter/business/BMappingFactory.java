package com.framework.demo.coverter.business;

import com.framework.demo.coverter.out.AddrDTOConvert;
import com.framework.demo.dto.AddrDTO;
import com.framework.demo.entity.Address;
import com.framework.demo.mapper.AddressMapper;
import com.ty.mid.framework.mybatisplus.service.wrapper.AbstractAutoWrapper;
import com.ty.mid.framework.mybatisplus.service.wrapper.core.AbstractMappingFactory;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Map;
import java.util.function.Function;


@Component
public class BMappingFactory implements AbstractMappingFactory {

//    public AbstractAutoWrapper<String, Address, AddrDTO, AddressMapper> ADDR_DTO = new AbstractAutoWrapper<String, Address, AddrDTO, AddressMapper>() {
//        @Override
//        public Map<String, AddrDTO> autoWrap(Collection<String> collection) {
//            Function<Address, AddrDTO> function = AddrDTOConvert.INSTANCE::convert;
//            return convert(AddressMapper.class, Address::getCode, AddrDTO::getCode, collection, function);
//        }
//    };
}

