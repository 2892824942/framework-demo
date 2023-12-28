package com.framework.demo.coverter.bussiness;

import cn.hutool.core.collection.CollUtil;
import com.framework.demo.coverter.out.AddrDTOConvert;
import com.framework.demo.coverter.out.RoleDTOConvert;
import com.framework.demo.dto.AddrDTO;
import com.framework.demo.dto.RoleDTO;
import com.framework.demo.entity.Address;
import com.framework.demo.entity.Role;
import com.framework.demo.mapper.AddressMapper;
import com.framework.demo.mapper.RoleMapper;
import com.ty.mid.framework.common.entity.BaseIdDO;
import com.ty.mid.framework.common.util.SafeGetUtil;
import com.ty.mid.framework.core.spring.SpringContextHelper;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Field;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * 常见类自动映射定义
 * 0.选择使用Enum让逻辑聚焦,减少子类创建
 * 1.定义的keyList类型,必须和出参的Map的key类型一致
 *
 * @author suyoulinag
 */
@Getter
@Slf4j
public enum ClassWrapperEnum {
    /**
     * AddrDTO数据处理
     */
    ADDR_DTO(AddrDTO.class) {
        @Override
        public Map<Object, Object> covert(Collection<Object> keyList){
            List<String> keyStrList = keyList.stream().map(Object::toString).collect(Collectors.toList());
            List<AddrDTO> addrDTOS = convertAddr(keyStrList);
            if (CollUtil.isEmpty(addrDTOS)) {
                return Collections.emptyMap();
            }
            return addrDTOS.stream().collect(Collectors.toMap(AddrDTO::getCode, Function.identity(), (a, b) -> a));

        }
    },

    /**
     * RoleDTO数据处理
     */
    ROLE_DTO(RoleDTO.class) {
        @Override
        public Map<Object, Object> covert(Collection<Object> keyList){
            List<Long> keyStrList = keyList.stream().map(key-> (Long) key).collect(Collectors.toList());
            List<RoleDTO> roleDTOList = covertRole(keyStrList);
            if (CollUtil.isEmpty(roleDTOList)) {
                return Collections.emptyMap();
            }
            return roleDTOList.stream().collect(Collectors.toMap(RoleDTO::getId, Function.identity(), (a, b) -> a));

        }
    },

    ;

    private final Class<?> javaClass;


    abstract  Map<Object, Object> covert(Collection<Object> keyList);

    ClassWrapperEnum(Class<?> javaClass) {
        this.javaClass = javaClass;
    }

    //TODO 以下方法转移到service中
    public List<AddrDTO> convertAddr(Collection<String> codeList) {
        if (CollUtil.isEmpty(codeList)) {
            return Collections.emptyList();
        }
        AddressMapper addressMapper = SpringContextHelper.getBean(AddressMapper.class);
        List<Address> addresses = addressMapper.selectList(Address::getCode, codeList);
        return AddrDTOConvert.INSTANCE.covert(addresses);
    }

    public List<RoleDTO> covertRole(Collection<Long> roleIdList) {
        if (CollUtil.isEmpty(roleIdList)) {
            return Collections.emptyList();
        }
        RoleMapper roleMapper = SpringContextHelper.getBean(RoleMapper.class);
        List<Role> roleList = roleMapper.selectList(Role::getId, roleIdList);
        return RoleDTOConvert.INSTANCE.covert(roleList);
    }

}
