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

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.framework.demo.entity.User;
import com.framework.demo.pojo.UserPageQuery;
import com.framework.demo.service.IUserService;
import com.ty.mid.framework.common.pojo.PageResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author
 */
@RestController("user")
public class UserController {

    @Resource
    private IUserService userService;
    @GetMapping("/getById")
    public User hello(@RequestParam Long id) {
        return userService.getById(id);
    }


    @GetMapping("/getPage")
    public PageResult<User> getPage(@RequestParam UserPageQuery query) {
        return userService.getPage(query);
    }


    @GetMapping("/save")
    public PageResult<User> save(@RequestParam UserPageQuery query) {
        return userService.getPage(query);
    }


    @GetMapping("/saveBatch")
    public PageResult<User> saveBatch(@RequestParam UserPageQuery query) {
        return userService.getPage(query);
    }

    @GetMapping("/delete")
    public PageResult<User> delete(@RequestParam UserPageQuery query) {
        return userService.getPage(query);
    }

}
