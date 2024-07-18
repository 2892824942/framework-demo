package com.framework.demo.dto;

import com.ty.mid.framework.common.entity.BaseIdDO;
import com.ty.mid.framework.common.pojo.KVResp;
import com.ty.mid.framework.encrypt.annotation.HashedId;
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
@Schema(description = "角色简单对象")
@Getter
@Setter
@AutoMapper(target = RoleDTO.class)
public class RoleSimpleDTO implements BaseIdDO<Long> {

    private static final long serialVersionUID = 1L;
    @Schema(description = "id")
    private Long id;

    @Schema(description = "角色名称")
    private String name;

    /**
     * 角色状态
     * <p>
     * 枚举 {@link com.ty.mid.framework.common.constant.EnableStatusEnum}
     */
    @Schema(description = "角色状态")
    private KVResp<String, String> statusInfo;

}
