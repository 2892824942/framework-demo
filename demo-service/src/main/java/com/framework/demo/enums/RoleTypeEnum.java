package com.framework.demo.enums;


import com.ty.mid.framework.common.pojo.KVResp;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum RoleTypeEnum implements KVResp<Integer, String> {

    /**
     * 内置角色
     */
    SYSTEM(1, "内置角色"),
    /**
     * 自定义角色
     */
    CUSTOM(2, "自定义角色");

    private final Integer type;

    private final String desc;


    @Override
    public Integer getKey() {
        return type;
    }

    @Override
    public String getValue() {
        return desc;
    }
}
