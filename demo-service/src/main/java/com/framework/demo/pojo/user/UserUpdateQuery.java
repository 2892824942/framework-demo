package com.framework.demo.pojo.user;

import com.baomidou.mybatisplus.annotation.TableField;
import com.framework.demo.entity.User;
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
@Schema(description = "用户分页查询对象")
@Getter
@Setter
@AutoMapper(target = User.class, reverseConvertGenerate = false)
public class UserUpdateQuery {

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
