package com.framework.demo.demos.web;

import cn.dev33.satoken.annotation.SaIgnore;
import cn.dev33.satoken.stp.SaTokenInfo;
import cn.dev33.satoken.stp.StpUtil;
import com.ty.mid.framework.common.lang.NeverNull;
import com.ty.mid.framework.common.pojo.BaseResult;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * 登录测试 
 */
@Tag(name = "登录操作",description = "登录相关操作")
@RestController
@RequestMapping("/login/")
public class LoginController {

    @SaIgnore
    @GetMapping("doLogin")
    @Valid
    @Operation(summary = "用户名密码登录")
    public BaseResult<String> doLogin(@NotNull String name, @NotNull String pwd) {
        // 此处仅作模拟示例，真实项目需要从数据库中查询数据进行比对 
        if("zhang".equals(name) && "123456".equals(pwd)) {
            StpUtil.login(10001);
            return BaseResult.success("登录成功");
        }
        return BaseResult.fail("登录失败");
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

    public static void main(String[] args) {
        Object loginIdByToken = StpUtil.getLoginIdByToken("eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJsb2dpblR5cGUiOiJsb2dpbiIsImxvZ2luSWQiOjEwMDAxLCJyblN0ciI6IkVEM2hxVnk3RGNwRkZVQnc2aFZtWUFNa3V1UW9TSjFnIn0.Wt3N2zWQBWUiUC6yrVdtcOKMRY9PbMCszRUPbWq4f0I");
        System.out.println(loginIdByToken);
    }
    
}
