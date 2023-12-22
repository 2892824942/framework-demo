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

import com.framework.demo.entity.Role;
import com.framework.demo.entity.User;
import com.framework.demo.pojo.role.RolePageQuery;
import com.framework.demo.pojo.role.RoleSaveQuery;
import com.framework.demo.service.IRoleService;
import com.ty.mid.framework.common.pojo.PageResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author
 */
@RestController("user控制器")
@RequestMapping("user")
public class RoleController {

    @Resource
    private IRoleService roleService;

    @GetMapping("/getByCode")
    public Role getByCode(@RequestParam String code) {
        return roleService.getByCode(code);
    }


    @PostMapping("/getPage")
    public PageResult<Role> getPage(@RequestBody RolePageQuery query) {
        return roleService.getPage(query);
    }


    @PostMapping("/save")
    public Boolean save(@RequestBody RoleSaveQuery query) {
        return roleService.save(query);
    }


    @PostMapping("/saveBatch")
    public void saveBatch(@RequestBody List<RoleSaveQuery> query) {
        roleService.saveBatch(query);
    }

    @DeleteMapping("/delete/{id}")
    public Boolean delete(@PathVariable(value = "id") Long id) {
        return roleService.deleteById(id);
    }

}
