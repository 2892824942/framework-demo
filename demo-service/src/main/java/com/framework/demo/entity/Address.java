package com.framework.demo.entity;


import com.baomidou.mybatisplus.annotation.TableName;
import com.ty.mid.framework.mybatisplus.core.dataobject.BaseDO;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 角色 DO
 *
 * @author ruoyi
 */
@EqualsAndHashCode(callSuper = true)
@TableName(value = "address", autoResultMap = true)
@Data
public class Address extends BaseDO {
    /**
     * 地址名称
     */
    private String name;
    /**
     * 地址标识
     */
    private String code;

}
