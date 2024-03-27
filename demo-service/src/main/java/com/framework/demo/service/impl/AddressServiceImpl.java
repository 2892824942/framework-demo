package com.framework.demo.service.impl;


import com.alibaba.nacos.shaded.com.google.common.collect.Lists;
import com.baomidou.mybatisplus.core.toolkit.support.SFunction;
import com.framework.demo.dto.AddrDTO;
import com.framework.demo.entity.Address;
import com.framework.demo.mapper.AddressMapper;
import com.framework.demo.pojo.addr.AddrQuery;
import com.framework.demo.pojo.addr.AddrSaveQuery;
import com.framework.demo.pojo.addr.AddrUpdateQuery;
import com.framework.demo.service.IAddressService;
import com.ty.mid.framework.common.pojo.PageParam;
import com.ty.mid.framework.common.pojo.PageResult;
import com.ty.mid.framework.common.util.GenericsUtil;
import com.ty.mid.framework.service.cache.mybatisplus.AllCacheAutoWrapService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author suyouliang
 * @since 2023-11-27
 */
@Service
public class AddressServiceImpl extends AllCacheAutoWrapService<Address, AddrDTO, AddressMapper> implements IAddressService {

    @Override
    public AddrDTO getByCode(String code) {
        return selectOneDTO(Address::getCode, code);
    }

    @Override
    public List<AddrDTO> getByCodesFromCache(List<String> codes) {
        Map<String, AddrDTO> all = cacheGetAll(codes);
        return new ArrayList<>(all.values());
    }

    @Override
    public AddrDTO getByCodeFromCache(String code) {
        return cacheGetByKey(code);
    }

    @Override
    public List<AddrDTO> getList(AddrQuery addrQuery) {
        addrQuery.setPageNo(PageParam.PAGE_SIZE_NONE);
        PageResult<Address> pageResult = baseMapper.getPage(addrQuery);
        return convert(pageResult.getList(), AddrDTO.class);
    }


    /**
     *↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓增删改部分demo↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓
     */

    /**
     * 默认开启了ReadThrough,读取不到会自动查询数据库
     *
     * @param query
     * @return
     */

    @Override
    public Boolean save(AddrSaveQuery query) {
        Address address = convert(query, Address.class);
        return save(address);
    }

    /**
     * 1.如更新不会更新key相关字段,直接删除缓存即可
     * 2.如更新会更新key相关字段,则需要先查询数据库原始数据,update后需要删除前后两个值已达到缓存重新加载的目的
     *
     * @param addrUpdateQuery
     * @return
     */
    @Override
    @Transactional
    public Boolean update(AddrUpdateQuery addrUpdateQuery) {
        Address address = convert(addrUpdateQuery, Address.class);
        Address dbAddress = getById(address.getId());
        boolean result = updateById(address);
        if (result) {
            cacheClear(Lists.newArrayList(address, dbAddress));
        }
        return null;
    }

    /**
     * 默认开启了ReadThrough,读取不到会自动查询数据库
     *
     * @param query
     * @return
     */
    @Override
    public void saveBatch(List<AddrSaveQuery> query) {
        List<Address> dataList = convert(query, Address.class);
        super.saveBatch(dataList);
    }

    /**
     * 删除需要手动操作缓存,更新类似
     *
     * @param id
     * @return
     */
    @Override
    @Transactional
    public Boolean deleteById(Long id) {
        Address address = selectOne(Address::getId, id);
        if (address == null) {
            return Boolean.TRUE;
        }
        boolean result = removeById(id);
        cacheClear(address);
        return result;
    }

    /**
     * ↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓框架父类方法重写↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓
     * @return
     */

//    /**
//     * 缓存Service:
//     * 重写缓存定义key,这里缓存两个key没有实际意义,只是为了测试多个key作为缓存的场景
//     * @return
//     */
//    @Override
//    public List<SFunction<Address, ?>> cacheDefineDOMapKeys() {
//        return Lists.newArrayList(Address::getCode, Address::getName);
//    }

    /**
     * 缓存Service:
     * 缓存key和自动装载字段一致,自动装载将走缓存
     *
     * @return
     */
    @Override
    public SFunction<Address, ?> cacheDefineDOMapKey() {
        return Address::getCode;
    }

    /**
     * 自动装载Service:
     * 定义code->AddrDTO自动装载
     *
     * @return
     */
    @Override
    public Map<?, AddrDTO> autoWrap(Collection<?> collection) {
        return convertMap(GenericsUtil.check2Collection(collection), Address::getCode, AddrDTO::getCode);
    }

}
