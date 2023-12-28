package com.framework.demo.dto;

import com.ty.mid.framework.common.dto.AbstractNameDTO;
import com.ty.mid.framework.common.entity.BaseIdDO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

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
public class RoleDTO implements BaseIdDO<Long>, Serializable {

    private static final long serialVersionUID = 1L;
    @Schema(description = "角色名称")
    private Long id;

    @Schema(description = "角色名称")
    private String name;

    @Schema(description = "角色标识")
    private String code;

    @Schema(description = "角色排序")
    private Integer sort;

}
