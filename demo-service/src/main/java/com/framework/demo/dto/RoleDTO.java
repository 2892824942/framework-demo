package com.framework.demo.dto;

import com.framework.demo.entity.Role;
import com.ty.mid.framework.common.entity.BaseIdDO;
import io.github.linpeilie.annotations.AutoMapper;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

/**
 * 角色基本对象
 * <p>
 *
 * @author suyouliang
 * @since 2023-11-27
 */
@Schema(description = "角色基本对象")
@Getter
@Setter
@AutoMapper(target = Role.class)
public class RoleDTO implements BaseIdDO<Long> {

    private static final long serialVersionUID = 1L;
    @Schema(description = "id")
    private Long id;

    @Schema(description = "角色名称")
    private String name;

    @Schema(description = "角色标识")
    private String code;

    @Schema(description = "角色排序")
    private Integer sort;

}
