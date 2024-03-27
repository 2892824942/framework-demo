package com.framework.demo.dto;

import com.framework.demo.entity.Address;
import com.ty.mid.framework.common.entity.BaseIdDO;
import io.github.linpeilie.annotations.AutoMapper;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

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
@ToString
@AutoMapper(target = Address.class)
public class AddrDTO implements BaseIdDO<Long> {

    private static final long serialVersionUID = 1L;

    @Schema(description = "id")
    private Long id;

    @Schema(description = "地址名称")
    private String name;

    @Schema(description = "地址标识")
    private String code;

}
