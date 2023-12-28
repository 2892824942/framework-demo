package com.framework.demo.coverter.bussiness;

import cn.hutool.core.collection.CollUtil;
import com.ty.mid.framework.common.entity.BaseIdDO;
import com.ty.mid.framework.common.util.Validator;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 业务常用实体自动装载器
 * 使用说明:
 * 0.常用实体转换方法需在ClassWrapperEnum中定义,具体可参考
 *
 * @see ClassWrapperEnum
 * 1.mapstruct中禁止定义常用实体转换,主要此类转换内部执行sql并将数据填充,而mapstruct会自动识别单一的A->B,当调用List<A>->List<B>时,会自动调用A->B,导致多次执行sql
 * 2.转换的source以及target必须继承BaseIdDO<Long>
 * 3.暂时只支持单一入参.多入参完全可以拆分变成多个单一入参
 * <p>
 * 注意:不频繁的实体转换推荐手动查询sql转换,此类的自动转换的价值在于CLassWrapperEnum定义的实体频繁使用,减少在对应实体转换时,手动查询sql(或调用已有方法赋值)的步骤.
 * 同样的,由于反射引入会降低此部分的性能.与带来的价值对比,可以接受
 */
@Slf4j
public class MappingProvider {

    public static <T extends BaseIdDO<Long>> void autoWrapper(T source, T target) {
        autoWrapper(Collections.singletonList(source), Collections.singletonList(target));
    }

    public static <S extends BaseIdDO<Long>, T extends BaseIdDO<Long>> void autoWrapper(Collection<S> sourceList, Collection<T> targetList) {
        if (CollUtil.isEmpty(sourceList) || CollUtil.isEmpty(targetList)) {
            return;
        }
        S source = sourceList.iterator().next();
        List<Field> bMappingFieldList = MappingProvider.getBMappingField(source);
        if (CollUtil.isEmpty(bMappingFieldList)) {
            return;
        }
        for (Field sourceField : bMappingFieldList) {
            BMapping annotation = sourceField.getAnnotation(BMapping.class);
            //1.校验
            ClassWrapperEnum wrapperEnum = annotation.value();
            T targetFirst = targetList.iterator().next();
            Field targetField = MappingProvider.getFieldByClass(targetFirst, wrapperEnum.getJavaClass());
            if (Objects.isNull(targetField)) {
                log.warn("target class not found field when auto covert process.check out you target:{} whether forgot define {}" +
                        ",or make a mistake on  @BMapping in source:{} ", targetFirst.getClass(), annotation.value().getJavaClass(), sourceList.iterator().next().getClass());
                continue;
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
            //TODO 兼容List<Long>形式标记
            Map<Object, Object> dataMap = wrapperEnum.covert(idKeyMap.values());
            for (T target : targetList) {
                MappingProvider.setTargetField(target, targetField, dataMap.get(idKeyMap.get(target.getId())));
            }

        }
    }

    /**
     * 解析实体中标注了@BMapping注解的属性
     */

    public static <T extends BaseIdDO<Long>> List<Field> getBMappingField(T source) {
        Field[] fields = source.getClass().getDeclaredFields();
        return Arrays.stream(fields)
                .filter(field -> Objects.nonNull(field.getAnnotation(BMapping.class)))
                .collect(Collectors.toList());

    }


    /**
     * 解析目标中标注为特定类型的属性
     *
     * @param target
     * @param mClass
     * @param <S>
     * @param <M>
     * @return
     */

    public static <S extends BaseIdDO<Long>, M> Field getFieldByClass(S target, Class<M> mClass) {
        Field[] fields = target.getClass().getDeclaredFields();
        List<Field> fieldList = Arrays.stream(fields)
                .filter(field -> {
                    if (field.getType().equals(mClass)) {
                        return true;
                    }
                    if (field.getType().equals(List.class)) {
                        Type genericType = field.getGenericType();
                        if (genericType instanceof ParameterizedType) {
                            ParameterizedType parameterizedType = (ParameterizedType) genericType;
                            Type[] typeArguments = parameterizedType.getActualTypeArguments();

                            if (typeArguments.length == 0) {
                                return false;
                            }
                            Class<?> genericClass = (Class<?>) typeArguments[0];
                            return genericClass.equals(mClass);
                        }else {
                            return false;
                        }

                    }
                    //暂时不支持Map
                    return false;
                }).collect(Collectors.toList());
        if (CollUtil.isEmpty(fieldList)) {
            return null;
        }
        Validator.requireTrue(fieldList.size() == 1, "目标中存在多个标注为特定类型的属性");
        return fieldList.get(0);
    }


    /**
     * 为目标中标注为特定类型的属性赋值
     *
     * @param target
     * @param <S>
     * @param <M>
     * @return
     */

    public static <S extends BaseIdDO<Long>, M> void setTargetField(S target, Field targetField, M value) {
        if (Objects.isNull(targetField) || Objects.isNull(value)) {
            return;
        }
        if (!targetField.getType().isAssignableFrom(value.getClass())) {
            //给定的类型不匹配
            throw new RuntimeException("类型不匹配,无法转换");
        }

        targetField.setAccessible(Boolean.TRUE);
        try {
            targetField.set(target, value);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }


}
