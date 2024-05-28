package com.framework.demo.dto;

import com.alibaba.nacos.shaded.com.google.common.collect.Lists;
import com.framework.demo.entity.User;
import com.ty.mid.framework.common.dto.BaseNameDTO;
import com.ty.mid.framework.web.annotation.desensitize.ChineseNameDesensitize;
import com.ty.mid.framework.web.annotation.desensitize.EmailDesensitize;
import com.ty.mid.framework.web.annotation.desensitize.HashedId;
import com.ty.mid.framework.web.annotation.desensitize.PasswordDesensitize;
import com.ty.mid.framework.web.core.model.vo.BaseNameVO;
import io.github.linpeilie.annotations.AutoMapper;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Collections;
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
@AutoMapper(target = User.class)
public class UserFullDTO extends BaseNameVO implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "姓名")
    @ChineseNameDesensitize
    private String name;

    @Schema(description = "密码")
    @PasswordDesensitize
    private String password;

    @Schema(description = "年龄")
    private Integer age;

    @Schema(description = "邮箱")
    @EmailDesensitize
    private String email;

    @Schema(description = "角色信息")
    private List<RoleDTO> roleInfos = Collections.emptyList();

    @Schema(description = "用户地址code")
    private AddrDTO addrInfo;

    @HashedId
    @Schema(description = "测试的ids")
    List<Long> ids= Lists.newArrayList(1L,2L,3L);

}
