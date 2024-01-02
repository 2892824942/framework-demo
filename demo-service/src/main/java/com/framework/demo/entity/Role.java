package com.framework.demo.entity;


import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.handlers.JacksonTypeHandler;
import com.ty.mid.framework.mybatisplus.core.dataobject.BaseDO;
import com.ty.mid.framework.mybatisplus.core.type.JsonLongSetTypeHandler;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Set;

/**
 * 角色 DO
 *
 * @author ruoyi
 */
@EqualsAndHashCode(callSuper = true)
@TableName(value = "role", autoResultMap = true)
@Data
public class Role extends BaseDO {
    /**
     * 角色名称
     */
    private String name;
    /**
     * 角色标识
     */
    private String code;
    /**
     * 角色排序
     */
    private Integer sort;
    /**
     * 角色状态
     * <p>
     * 枚举 {@link com.ty.mid.framework.common.constant.EnableStatusEnum}
     */
    private Integer status;

    /**
     * 数据范围(指定部门id数组)
     */
    @TableField(typeHandler = JsonLongSetTypeHandler.class)
    private Set<Long> dataScopeDeptIds;

    /**
     * 数据范围(指定部门id数组)
     */
    @TableField(typeHandler = JacksonTypeHandler.class)
    private Extra extra;

    @Data
    public static class Extra {
        private Boolean canAppLogin;
        private String showName;
    }

}
