package com.framework.demo.pojo.role;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

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
public class RoleUpdateQuery {

    @Schema(description = "主键ID")
    private Long id;

    @Schema(description = "角色名称")
    private String name;


    @Schema(description = "角色标识")
    private String code;

    @Schema(description = "角色排序")
    private Integer sort;
    /**
     *
     * 枚举 {@link com.ty.mid.framework.common.constant.EnableStatusEnum}
     */
    @Schema(description = "角色状态")
    private Integer status;

    /**
     * 数据范围(指定部门id数组)
     *
     */
    @Schema(description = "数据范围(指定部门id数组)")
    private Set<Long> dataScopeDeptIds;
}
