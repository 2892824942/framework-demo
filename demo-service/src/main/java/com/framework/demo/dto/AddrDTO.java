package com.framework.demo.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

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
public class AddrDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "地址名称")
    private String name;

    @Schema(description = "地址标识")
    private String code;

}
