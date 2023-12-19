package com.framework.demo.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.extension.plugins.pagination.PageDTO;
import com.ty.mid.framework.common.pojo.PageParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author suyouliang
 * @since 2023-11-27
 */
@Schema(description = "用户分页查询对象")
@Getter
@Setter
public class UserPageQuery extends PageParam {

    @Schema(description = "主键ID")
    private Long id;

    @Schema(description = "姓名")
    @TableField("`name`")
    private String name;

    @Schema(description = "年龄")
    private Integer age;

    @Schema(description = "邮箱")
    private String email;
}
