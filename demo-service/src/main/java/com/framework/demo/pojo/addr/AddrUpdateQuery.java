package com.framework.demo.pojo.addr;

import com.framework.demo.entity.Address;
import io.github.linpeilie.annotations.AutoMapper;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 *
 * </p>
 *
 * @author suyouliang
 * @since 2023-11-27
 */
@Schema(description = "角色修改对象")
@Getter
@Setter
@AutoMapper(target = Address.class, reverseConvertGenerate = false)
public class AddrUpdateQuery {

    @Schema(description = "id")
    private Long id;

    @Schema(description = "地址名称")
    private String name;

    @Schema(description = "地址标识")
    private String code;
}
