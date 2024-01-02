package com.framework.demo.entity.bo;

import com.framework.demo.entity.User;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 用户表:User对象
 * </p>
 * 注意自定义TypeHandler必须加autoResultMap = true,否则返回值无法正确处理
 *
 * @author suyouliang
 * @since 2023-11-27
 */
@Schema(description = "用户全量对象")
@Getter
@Setter
public class UserFullBO extends User {

    private static final long serialVersionUID = 1L;

    @Schema(description = "地址名称")
    private String addrName;


}
