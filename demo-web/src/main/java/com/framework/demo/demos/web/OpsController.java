package com.framework.demo.demos.web;

import cn.dev33.satoken.annotation.SaIgnore;
import cn.dev33.satoken.stp.SaTokenInfo;
import cn.dev33.satoken.stp.StpLogic;
import cn.dev33.satoken.stp.StpUtil;
import com.ty.mid.framework.common.pojo.BaseResult;
import com.ty.mid.framework.common.pojo.Result;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * 登录测试 
 */
@RestController
@RequestMapping("/login/")
@Valid
public class OpsController {

    @SaIgnore
    @RequestMapping("getUserIdByToken")

    public Result<Object> getUserIdByToken(@NotNull(message = "token不能为空") String token) {
        return BaseResult.success(StpUtil.getLoginIdByToken(token));
    }

    @RequestMapping("switchTo")
    public BaseResult<String> switchTo(@NotNull(message = "切换的用户id不能为空") String userId) {
        StpUtil.switchTo(userId);
        return BaseResult.success("切换userId：" + userId+"成功");
    }
    @RequestMapping("endSwitch")
    public BaseResult<String> endSwitch(@NotNull(message = "切换的用户id不能为空") String userId) {
        StpUtil.endSwitch();
        return BaseResult.success("结束切换userId：" + userId+"成功");
    }
}
