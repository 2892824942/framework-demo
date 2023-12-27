package com.framework.demo.dto;

import com.baomidou.mybatisplus.annotation.TableField;
import com.ty.mid.framework.common.dto.AbstractNameDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
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
@Schema(description = "用户全量对象")
@Getter
@Setter
public class UserFullDTO extends AbstractNameDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "姓名")
    private String name;

    @Schema(description = "密码")
    private String password;

    @Schema(description = "年龄")
    private Integer age;

    @Schema(description = "邮箱")
    private String email;

    @Schema(description = "角色信息")
    private List<RoleDTO> roleInfos;

    @Schema(description = "用户地址code")
    private AddrDTO addrInfo;


}
