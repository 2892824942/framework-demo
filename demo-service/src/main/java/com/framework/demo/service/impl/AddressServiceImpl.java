package com.framework.demo.service.impl;


import com.baomidou.mybatisplus.core.toolkit.support.SFunction;
import com.framework.demo.coverter.AddrConvert;
import com.framework.demo.coverter.out.AddrDTOConvert;
import com.framework.demo.dto.AddrDTO;
import com.framework.demo.entity.Address;
import com.framework.demo.mapper.AddressMapper;
import com.framework.demo.pojo.addr.AddrQuery;
import com.framework.demo.pojo.addr.AddrSaveQuery;
import com.framework.demo.service.IAddressService;
import com.google.common.collect.Lists;
import com.ty.mid.framework.common.pojo.PageParam;
import com.ty.mid.framework.common.pojo.PageResult;
import com.ty.mid.framework.common.util.GenericsUtil;
import com.ty.mid.framework.service.cache.mybatisplus.MpAllCacheService;
import org.springframework.stereotype.Service;

import java.util.*;
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
public class AddressServiceImpl extends MpAllCacheService<Address, AddrDTO, AddressMapper> implements IAddressService {



    @Override
    public List<SFunction<Address, ?>> cacheDefineDOMapKeys() {
        return Lists.newArrayList(Address::getCode, Address::getName);
    }

    @Override
    public AddrDTO getByCode(String code) {
        Address address = selectOne(Address::getCode, code);
        return AddrDTOConvert.INSTANCE.convert(address);
    }

    @Override
    public List<AddrDTO> getByCodesFromCache(List<String> codes) {
        Map<String, AddrDTO> all = cacheGetAll(codes);
        return new ArrayList<>(all.values());
    }

    @Override
    public AddrDTO getByCodeFromCache(String code) {
        return cacheGetByKey( code);
    }

    @Override
    public List<AddrDTO> getList(AddrQuery addrQuery) {
        addrQuery.setPageNo(PageParam.PAGE_SIZE_NONE);
        PageResult<Address> pageResult = baseMapper.getPage(addrQuery);
        if (pageResult.hasEmptyData()) {
            return Collections.emptyList();
        }
        return AddrDTOConvert.INSTANCE.convert(pageResult.getList());
    }

    @Override
    public Boolean save(AddrSaveQuery query) {
        Address address = AddrConvert.INSTANCE.convert(query);
        return save(address);
    }

    @Override
    public void saveBatch(List<AddrSaveQuery> query) {
        List<Address> dataList = AddrConvert.INSTANCE.convert(query);
        super.saveBatch(dataList);
    }

    @Override
    public Boolean deleteById(Long id) {
        return removeById(id);
    }

    @Override
    public Map<?, AddrDTO> autoWrap(Collection<?> collection) {
        Function<List<Address>, List<AddrDTO>> function = AddrDTOConvert.INSTANCE::convert;
        return convert(GenericsUtil.check2Collection(collection), Address::getCode, AddrDTO::getCode, function);
    }

    public static void main(String[] args) {
        SFunction<Address, ?> sFunction = (SFunction<Address, Object>) o -> {
            AddrDTO t = AddrDTOConvert.INSTANCE.convert(o);
            Function<AddrDTO, String> getCode = AddrDTO::getCode;
            return getCode.apply(t);
        };
        Address address = new Address();
        address.setCode("12343");
        address.setName("aaa");
        Object apply = sFunction.apply(address);
        System.out.println(apply);
    }
}
