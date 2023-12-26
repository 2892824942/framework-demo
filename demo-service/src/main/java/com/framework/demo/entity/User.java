package com.framework.demo.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.ty.mid.framework.mybatisplus.core.dataobject.BaseDO;
import com.ty.mid.framework.mybatisplus.core.type.DefaultTypeHandler;
import com.ty.mid.framework.mybatisplus.core.type.EncryptTypeHandler;
import com.ty.mid.framework.mybatisplus.core.type.LongListTypeHandler;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * <p>
 * 用户表:User对象
 * </p>
 * 注意自定义TypeHandler必须加autoResultMap = true,否则返回值无法正确处理
 *
 * @author suyouliang
 * @since 2023-11-27
 */
@Schema(description = "用户表:User对象")
@TableName(autoResultMap = true)
@Getter
@Setter
public class User extends BaseDO {

    private static final long serialVersionUID = 1L;

    @Schema(description = "姓名")
    @TableField("`name`")
    private String name;

    @Schema(description = "密码")
    @TableField(value = "`password`", typeHandler = EncryptTypeHandler.class)
    private String password;

    @Schema(description = "角色id列表,多个使用,号隔开")
    @TableField(value = "`role_ids`", typeHandler = LongListTypeHandler.class)
    private List<Long> roleIds;

    @Schema(description = "年龄")
    @TableField(value = "`age`", typeHandler = DefaultTypeHandler.class)
    private Integer age;

    @Schema(description = "邮箱")
    private String email;

    @Schema(description = "用户地址code")
    @TableField(value = "`addr_code`")
    private String addrCode;
}
