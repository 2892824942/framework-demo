/*
 * Copyright 2013-2018 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.framework.demo.demos.web;

import com.framework.demo.dto.UserFullDTO;
import com.framework.demo.dto.UserInfoDTO;
import com.framework.demo.entity.bo.UserFullBO;
import com.framework.demo.pojo.user.UserQuery;
import com.framework.demo.pojo.user.UserSaveQuery;
import com.framework.demo.service.IUserService;
import com.ty.mid.framework.common.pojo.BaseResult;
import com.ty.mid.framework.common.pojo.PageResult;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * user控制器
 */
@Tag(name = "user控制器",description = "用户相关接口")
@Controller("user控制器")
@ResponseBody
@RequestMapping("user")
public class UserController {

    @Resource
    private IUserService userService;

    @GetMapping("/getById")
    @Operation(summary = "通过id获取用户")
    public BaseResult<UserFullDTO> getById(@RequestParam Long id) {
        return BaseResult.success(userService.getById(id));
    }


    @PostMapping("/getPage")
    @Operation(summary = "分页查询用户")
    public BaseResult<List<UserFullBO>> getPage(@RequestBody UserQuery query) {
        PageResult<UserFullBO> page = userService.getPage(query);

        return BaseResult.successPage(page);
    }

    @PostMapping("/getList")
    @Operation(summary = "查询用户list")
    public BaseResult<List<UserFullDTO>> getList(@RequestBody UserQuery query) {
        return BaseResult.success(userService.getFullList(query));
    }

    @PostMapping("/getInfoList")
    @Operation(summary = "查询用户listInfo",description = "用户角色附带详情")
    public BaseResult<List<UserInfoDTO>> getInfoList(@RequestBody UserQuery query) {
        return BaseResult.success(userService.getInfoList(query));
    }


    @PostMapping("/save")
    @Operation(summary = "保存用户")
    public BaseResult<Boolean> save(@RequestBody UserSaveQuery query) {
        return BaseResult.success(userService.save(query));
    }


    @PostMapping("/saveBatch")
    @Operation(summary = "批量保存用户")
    public void saveBatch(@RequestBody List<UserSaveQuery> query) {
        userService.saveBatch(query);
    }

    @DeleteMapping("/delete/{id}")
    @Operation(summary = "通过id删除用户")
    public BaseResult<Boolean> delete(@PathVariable(value = "id") Long id) {
        return BaseResult.success(userService.deleteById(id));
    }

}
