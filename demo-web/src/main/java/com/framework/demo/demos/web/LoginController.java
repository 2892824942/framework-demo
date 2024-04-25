package com.framework.demo.demos.web;

import cn.dev33.satoken.annotation.SaIgnore;
import cn.dev33.satoken.stp.SaTokenInfo;
import cn.dev33.satoken.stp.StpUtil;
import com.ty.mid.framework.common.lang.NeverNull;
import com.ty.mid.framework.common.pojo.BaseResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * 登录测试 
 */
@RestController
@RequestMapping("/login/")
public class LoginController {

    @SaIgnore
    @RequestMapping("doLogin")
    @Valid
    public BaseResult<String> doLogin(@NotNull String name, @NotNull String pwd) {
        // 此处仅作模拟示例，真实项目需要从数据库中查询数据进行比对 
        if("zhang".equals(name) && "123456".equals(pwd)) {
            StpUtil.login(10001);
            return BaseResult.success("登录成功");
        }
        return BaseResult.fail("登录失败");
    }

    // 查询登录状态  ---- http://localhost:8081/acc/isLogin
    @RequestMapping("isLogin")
    public BaseResult<String> isLogin() {
        return BaseResult.success("是否登录：" + StpUtil.isLogin());
    }
    
    // 查询 Token 信息  ---- http://localhost:8081/acc/tokenInfo
    @RequestMapping("tokenInfo")
    public BaseResult<SaTokenInfo> tokenInfo() {
        return BaseResult.success(StpUtil.getTokenInfo());
    }
    
    // 测试注销  ---- http://localhost:8081/acc/logout
    @RequestMapping("logout")
    public BaseResult<Void> logout() {
        StpUtil.logout();
        return BaseResult.success();
    }

    public static void main(String[] args) {
        Object loginIdByToken = StpUtil.getLoginIdByToken("eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJsb2dpblR5cGUiOiJsb2dpbiIsImxvZ2luSWQiOjEwMDAxLCJyblN0ciI6IkVEM2hxVnk3RGNwRkZVQnc2aFZtWUFNa3V1UW9TSjFnIn0.Wt3N2zWQBWUiUC6yrVdtcOKMRY9PbMCszRUPbWq4f0I");
        System.out.println(loginIdByToken);
    }
    
}
