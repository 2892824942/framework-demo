package com.framework.demo.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.framework.demo.dto.AddrDTO;
import com.framework.demo.dto.RoleDTO;
import com.framework.demo.dto.RoleSimpleDTO;
import com.ty.mid.framework.encrypt.annotation.ChineseNameDesensitize;
import com.ty.mid.framework.encrypt.annotation.EncryptField;
import com.ty.mid.framework.encrypt.annotation.HashedId;
import com.ty.mid.framework.encrypt.enumd.AlgorithmType;
import com.ty.mid.framework.mybatisplus.core.dataobject.BaseDO;
import com.ty.mid.framework.mybatisplus.core.type.DefaultTypeHandler;
import com.ty.mid.framework.mybatisplus.core.type.LongListTypeHandler;
import com.ty.mid.framework.mybatisplus.core.type.StringListTypeHandler;
import com.ty.mid.framework.service.wrapper.core.AutoWrap;
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
    @ChineseNameDesensitize
    private String name;

    @Schema(description = "密码")
    @EncryptField(algorithm= AlgorithmType.AES)
    private String password;
    /**
     * 1.typeHandler执行在Interceptor之前,
     * 2.混合使用场景:string->Ling,且需要解密情况
     * 没有找到更好的方法,保留List中正确的Long泛型,目前只能通过List<String>接收
     * 3.AutoWrap优化,兼容String类型映射long类型数据,此处仍可以正确自动装载数据
     */
    @Schema(description = "角色id列表,多个使用,号隔开")
    @TableField(value = "`role_ids`", typeHandler = StringListTypeHandler.class)
    @HashedId
    @AutoWrap(values = {RoleDTO.class, RoleSimpleDTO.class})
    private List<String> roleIds;

    @Schema(description = "年龄")
    @TableField(value = "`age`", typeHandler = DefaultTypeHandler.class)
    private Integer age;

    @Schema(description = "邮箱")
    private String email;

    @Schema(description = "用户地址code")
    @TableField(value = "`addr_code`")
    @AutoWrap(values = AddrDTO.class)
    private String addrCode;
}
