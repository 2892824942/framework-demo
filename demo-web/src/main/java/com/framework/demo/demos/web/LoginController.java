package com.framework.demo.demos.web;

import cn.dev33.satoken.annotation.SaIgnore;
import cn.dev33.satoken.stp.SaTokenInfo;
import cn.dev33.satoken.stp.StpUtil;
import com.framework.demo.dto.RoleDTO;
import com.framework.demo.dto.UserFullDTO;
import com.framework.demo.enums.ErrorCodeEnum;
import com.framework.demo.service.IUserService;
import com.ty.mid.framework.common.model.LoginUser;
import com.ty.mid.framework.common.pojo.BaseResult;
import com.ty.mid.framework.common.util.AssertUtil;
import com.ty.mid.framework.security.utils.LoginHelper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.stream.Collectors;

/**
 * 登录测试
 */
@Tag(name = "登录操作", description = "登录相关操作")
@RestController
@RequestMapping("/login/")
@RequiredArgsConstructor
public class LoginController {
    private final IUserService userService;

    public static void main(String[] args) {
        Object loginIdByToken = StpUtil.getLoginIdByToken("eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJsb2dpblR5cGUiOiJsb2dpbiIsImxvZ2luSWQiOjEwMDAxLCJyblN0ciI6IkVEM2hxVnk3RGNwRkZVQnc2aFZtWUFNa3V1UW9TSjFnIn0.Wt3N2zWQBWUiUC6yrVdtcOKMRY9PbMCszRUPbWq4f0I");
        System.out.println(loginIdByToken);
    }

    @SaIgnore
    @GetMapping("doLogin")
    @Valid
    @Operation(summary = "用户名密码登录")
    public BaseResult<LoginUser> doLogin(@Schema(description = "用户名") @NotBlank(message = "用户名不能为空") String name
            , @Schema(description = "密码") @NotBlank(message = "密码不能为空") String pwd) {
        UserFullDTO userFullDTO = userService.getByUserName(name);
        AssertUtil.notEmpty(userFullDTO, ErrorCodeEnum.LOGIN_FAIL);
        AssertUtil.equals(userFullDTO.getPassword(), pwd, ErrorCodeEnum.LOGIN_FAIL);
        // 校验通过,开始登录
        LoginUser loginUser = new LoginUser();
        loginUser.setUserId(userFullDTO.getId());
        loginUser.setLoginId(userFullDTO.getId());
        loginUser.setUsername(userFullDTO.getName());
        loginUser.setRolePermission(userFullDTO.getRoleInfos().stream().map(RoleDTO::getCode).collect(Collectors.toSet()));
        LoginHelper.login(loginUser);
        return BaseResult.success(loginUser);
    }

    @SaIgnore
    @GetMapping("isLogin")
    @Operation(summary = "是否登录")
    public BaseResult<String> isLogin() {
        return BaseResult.success("是否登录：" + StpUtil.isLogin());
    }

    @SaIgnore
    @GetMapping("tokenInfo")
    @Operation(summary = "用户token详情")
    public BaseResult<SaTokenInfo> tokenInfo() {
        return BaseResult.success(StpUtil.getTokenInfo());
    }

    @SaIgnore
    @GetMapping("logout")
    @Operation(summary = "登出")
    public BaseResult<Void> logout() {
        StpUtil.logout();
        return BaseResult.success();
    }

}
