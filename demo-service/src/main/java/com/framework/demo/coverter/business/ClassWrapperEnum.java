package com.framework.demo.coverter.business;

import cn.hutool.core.collection.CollUtil;
import com.framework.demo.dto.AddrDTO;
import com.framework.demo.dto.RoleDTO;
import com.framework.demo.mapper.AddressMapper;
import com.framework.demo.mapper.RoleMapper;
import com.ty.mid.framework.core.spring.SpringContextHelper;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * 常见类自动映射定义
 * 0.选择使用Enum让逻辑聚焦,减少子类创建
 * 1.定义的keyList类型,必须和出参的Map的key类型一致
 * //TODO 作为Service 增强能力,更改实现为注解实现,并支持额外定制参数
 *
 * @author suyoulinag
 */
@Getter
@Slf4j
public enum ClassWrapperEnum{
    /**
     * AddrDTO数据处理
     */
    ADDR_DTO(AddrDTO.class) {
        @Override
        public Map<Object, Object> covert(Collection<Object> keyList){
            List<String> keyStrList = keyList.stream().map(Object::toString).collect(Collectors.toList());
            List<AddrDTO> addrDTOS = SpringContextHelper.getBean(AddressMapper.class).convertAddr(keyStrList);
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
            List<RoleDTO> roleDTOList = SpringContextHelper.getBean(RoleMapper.class).covertRole(keyStrList);
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

}
