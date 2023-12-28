package com.framework.demo.coverter.bussiness;

import cn.hutool.core.collection.CollUtil;
import com.ty.mid.framework.common.entity.BaseIdDO;
import com.ty.mid.framework.common.util.Validator;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class MappingParser {
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
     * 获取BMapping中标注的wrapper实例
     *
     * @param source        源对象
     * @param bMappingField 拥有@BMapping注解的属性
     * @param <T>           泛型
     * @return ClassWrapperEnum
     */

    //
    public static <T extends BaseIdDO<Long>> ClassWrapperEnum getWrapper(T source, Field bMappingField) {
        if (Objects.isNull(bMappingField)) {
            return null;
        }
        BMapping annotation = bMappingField.getAnnotation(BMapping.class);
        return annotation.value();
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
                .filter(field -> field.getType().equals(mClass))
                .collect(Collectors.toList());
        if (CollUtil.isEmpty(fieldList)){
            return null;
        }
        Validator.requireTrue(fieldList.size() == 1, "目标中存在多个标注为特定类型的属性");
        return  fieldList.get(0);
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
