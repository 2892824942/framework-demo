package com.framework.demo.pojo.addr;

import com.ty.mid.framework.common.pojo.PageParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * <p>
 *
 * </p>
 *
 * @author suyouliang
 * @since 2023-11-27
 */
@EqualsAndHashCode(callSuper = true)
@Schema(description = "角色分页查询对象")
@Data
public class AddrQuery extends PageParam {

    @Schema(description = "id")
    private Long id;

    @Schema(description = "地址名称")
    private String name;

    @Schema(description = "地址标识")
    private List<String> codes;

}
