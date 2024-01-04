package com.framework.demo.service.impl;


import com.fasterxml.jackson.core.type.TypeReference;
import com.framework.demo.coverter.AddrConvert;
import com.framework.demo.coverter.out.AddrDTOConvert;
import com.framework.demo.dto.AddrDTO;
import com.framework.demo.entity.Address;
import com.framework.demo.mapper.AddressMapper;
import com.framework.demo.pojo.addr.AddrQuery;
import com.framework.demo.pojo.addr.AddrSaveQuery;
import com.framework.demo.service.IAddressService;
import com.ty.mid.framework.common.pojo.PageParam;
import com.ty.mid.framework.common.pojo.PageResult;
import com.ty.mid.framework.common.util.GenericsUtil;
import com.ty.mid.framework.mybatisplus.service.cache.mybatisplus.MpAllCacheService;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
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
public class AddressServiceImpl extends MpAllCacheService<Address, AddrDTO, AddressMapper> implements IAddressService {

    @Override
    public String getCacheName() {
        return "addrCache";
    }

    @Override
    public String resolveMapKey(AddrDTO dto) {
        return String.valueOf(dto.getId());
    }

    @Override
    public TypeReference<AddrDTO> getTargetTypeReference() {
        return new TypeReference<AddrDTO>() {
        };
    }

    @Override
    public TypeReference<Address> getSourceTypeReference() {
        return new TypeReference<Address>() {
        };
    }

    @Override
    public AddrDTO getByCode(String code) {
        Address address = selectOne(Address::getCode, code);
        return AddrDTOConvert.INSTANCE.convert(address);
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

}
