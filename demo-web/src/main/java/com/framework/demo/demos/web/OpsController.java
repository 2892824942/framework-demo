package com.framework.demo.demos.web;

import cn.dev33.satoken.annotation.SaIgnore;
import cn.dev33.satoken.stp.SaTokenInfo;
import cn.dev33.satoken.stp.StpLogic;
import cn.dev33.satoken.stp.StpUtil;
import com.ty.mid.framework.common.pojo.BaseResult;
import com.ty.mid.framework.common.pojo.Result;
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
@Tag(name = "系统管理操作",description = "给系统开发者提供的便携接口")
@RestController
@RequestMapping("/login/")
@Valid
public class OpsController {

    @SaIgnore
    @GetMapping("getUserIdByToken")
    @Operation(summary = "通过token获取用户id")
    public Result<Object> getUserIdByToken(@NotNull(message = "token不能为空") String token) {
        return BaseResult.success(StpUtil.getLoginIdByToken(token));
    }

    @GetMapping("switchTo")
    @Operation(summary = "当前用户伪装为另一个用户",description = "这个接口一定注意安全,防止恶意利用")
    public BaseResult<String> switchTo(@NotNull(message = "切换的用户id不能为空") String userId) {
        StpUtil.switchTo(userId);
        StpUtil.getTokenInfo();
        return BaseResult.success("切换userId：" + userId+"成功");
    }
    @GetMapping("endSwitch")
    @Operation(summary = "结束伪装为另一个用户",description = "这个接口一定注意安全,防止恶意利用")
    public BaseResult<String> endSwitch(@NotNull(message = "切换的用户id不能为空") String userId) {
        StpUtil.endSwitch();
        return BaseResult.success("结束切换userId：" + userId+"成功");
    }
}
