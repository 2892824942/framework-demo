package com.framework.demo.pojo.role;

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
public class RolePageQuery extends PageParam {

    @Schema(description = "编码列表")
    private List<String> codeList;
    /**
     * 角色状态
     * <p>
     * 枚举 {@link com.ty.mid.framework.common.constant.EnableStatusEnum}
     */
    @Schema(description = "状态列表")
    private List<Integer> statusList;

    @Schema(description = "角色名称")
    private String nameLike;

}
