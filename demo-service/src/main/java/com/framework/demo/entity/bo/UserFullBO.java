package com.framework.demo.entity.bo;

import com.alibaba.nacos.shaded.com.google.common.collect.Lists;
import com.framework.demo.dto.UserFullDTO;
import com.framework.demo.entity.User;
import com.ty.mid.framework.web.annotation.desensitize.HashedId;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

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
public class UserFullBO extends UserFullDTO {

    private static final long serialVersionUID = 1L;

    @Schema(description = "地址名称")
    private String addrName;

}
