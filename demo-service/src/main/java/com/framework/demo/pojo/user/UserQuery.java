package com.framework.demo.pojo.user;

import com.ty.mid.framework.common.pojo.PageParam;
import com.ty.mid.framework.encrypt.annotation.HashedId;
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
@Schema(description = "用户分页查询对象")
@Data
public class UserQuery extends PageParam {

    @Schema(description = "主键ID")
    @HashedId
    private Long id;

    @Schema(description = "主键ID List")
    @HashedId
    private List<Long> ids;

    @Schema(description = "姓名")
    private String name;

    @Schema(description = "年龄")
    private Integer age;

    @Schema(description = "邮箱")
    private String email;

    @Schema(description = "地址code")
    private String addrCode;
}
