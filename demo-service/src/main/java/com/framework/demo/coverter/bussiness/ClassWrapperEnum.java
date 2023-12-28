package com.framework.demo.coverter.bussiness;

import cn.hutool.core.collection.CollUtil;
import com.framework.demo.dto.AddrDTO;
import com.framework.demo.entity.Address;
import com.framework.demo.mapper.AddressMapper;
import com.ty.mid.framework.common.entity.BaseIdDO;
import com.ty.mid.framework.common.util.SafeGetUtil;
import com.ty.mid.framework.core.spring.SpringContextHelper;
import com.ty.mid.framework.core.util.StringUtils;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Field;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * 类型数据默认值常量类
 *
 * @author suyoulinag
 */
@Getter
@Slf4j
public enum ClassWrapperEnum {
    /**
     * LocalDateTime时间类型
     */
    ADDR_DTO(AddrDTO.class) {
        @Override
        @SuppressWarnings("unchecked")
        public Map<Object, Object> covert(Collection<Object> keyList){
            List<String> keyStrList = keyList.stream().map(Object::toString).collect(Collectors.toList());
            List<AddrDTO> addrDTOS = convertAddr(keyStrList);
            if (CollUtil.isEmpty(addrDTOS)) {
                return Collections.emptyMap();
            }
            return addrDTOS.stream().collect(Collectors.toMap(AddrDTO::getCode, Function.identity(), (a, b) -> a));

        }
    },

    ;

    private final Class<?> javaClass;


    abstract  Map<Object, Object> covert(Collection<Object> keyList);

    ClassWrapperEnum(Class<?> javaClass) {
        this.javaClass = javaClass;
    }

//    public static <T extends BaseIdDO<Long>> void autoWrapper(T source, T target) {
//        autoWrapper(Collections.singletonList(source), Collections.singletonList(target));
//    }

    public static <S extends BaseIdDO<Long>,T extends BaseIdDO<Long>> void autoWrapper(List<S> sourceList, List<T> targetList) {
        if (CollUtil.isEmpty(sourceList) || CollUtil.isEmpty(targetList)) {
            return;
        }
        S source = sourceList.get(0);
        List<Field> bMappingFieldList = MappingParser.getBMappingField(source);
        if (CollUtil.isEmpty(bMappingFieldList)) {
            return;
        }
        for (Field sourceField : bMappingFieldList) {
            BMapping annotation = sourceField.getAnnotation(BMapping.class);
            //1.校验
            ClassWrapperEnum wrapperEnum = annotation.value();
            T targetFirst = targetList.iterator().next();
            Field targetField = MappingParser.getFieldByClass(targetFirst, wrapperEnum.getJavaClass());
            if (Objects.isNull(targetField)) {
                log.warn("target class not found field when auto covert process,it may lower efficiency!check out you target:{} whether forgot define AddrDTO" +
                        ",or make a mistake on  @BMapping in source:{} ", targetFirst.getClass(), sourceList.iterator().next().getClass());
                return;
            }
            //2.组装映射
            //获取value
            sourceField.setAccessible(Boolean.TRUE);
            Map<Long, Object> idKeyMap = new HashMap<>();
            sourceList.forEach(inSource -> {
                Object key;
                try {
                    key = sourceField.get(inSource);
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                }
                if (Objects.nonNull(key)) {
                    idKeyMap.put(inSource.getId(), key);
                }

            });
            //3.获取核心数据
            Map<Object, Object> dataMap = wrapperEnum.covert(idKeyMap.values());
            for (T target : targetList) {
                MappingParser.setTargetField(target, targetField, dataMap.get(idKeyMap.get(target.getId())));
            }

        }
    }

    public List<AddrDTO> convertAddr(Collection<String> codeList) {
        if (CollUtil.isEmpty(codeList)) {
            return Collections.emptyList();
        }
        AddressMapper addressMapper = SpringContextHelper.getBean(AddressMapper.class);

        Map<String, Address> addrMap = SafeGetUtil.get(addressMapper.selectMap(Address::getCode, codeList, Address::getCode));
        return codeList.stream().map(code -> {
            Address address = addrMap.get(code);
            if (Objects.isNull(address)) {
                return null;
            }
            AddrDTO addrDTO = new AddrDTO();
            addrDTO.setName(address.getName());
            addrDTO.setCode(address.getCode());
            return addrDTO;
        }).filter(Objects::nonNull).collect(Collectors.toList());
    }

}
