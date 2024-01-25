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

import com.framework.demo.dto.RoleDTO;
import com.framework.demo.entity.Role;
import com.framework.demo.pojo.role.RoleQuery;
import com.framework.demo.pojo.role.RoleSaveQuery;
import com.framework.demo.service.IRoleService;
import com.ty.mid.framework.common.pojo.BaseResult;
import com.ty.mid.framework.common.pojo.PageResult;
import com.ty.mid.framework.lock.annotation.FailFastLock;
import com.ty.mid.framework.lock.annotation.Lock;
import com.ty.mid.framework.lock.config.LockConfig;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.jws.WebResult;
import java.util.Collection;
import java.util.List;

/**
 * @author
 */
@RestController("角色控制器")
@RequestMapping("role")
public class RoleController {

    @PostMapping("/test")
    public BaseResult<Boolean> test(@RequestBody RoleSaveQuery query) {
        return BaseResult.success(roleService.test(query));
    }

    @Resource
    private IRoleService roleService;

    @GetMapping("/getByCode")
    public BaseResult<RoleDTO> getByCode(@RequestParam String code) {
        return BaseResult.success(roleService.getByCode(code));
    }


    @PostMapping("/getPage")
    public BaseResult<List<RoleDTO>> getPage(@RequestBody RoleQuery query) {
       return BaseResult.successPage(roleService.getPage(query));
    }


    @PostMapping("/save")
    public BaseResult<Boolean> save(@RequestBody RoleSaveQuery query) {
        return BaseResult.success(roleService.save(query));
    }


    @PostMapping("/saveBatch")
    public void saveBatch(@RequestBody List<RoleSaveQuery> query) {
        roleService.saveBatch(query);
    }

    @DeleteMapping("/delete/{id}")
    public BaseResult<Boolean> delete(@PathVariable(value = "id") Long id) {
        return BaseResult.success(roleService.deleteById(id));
    }

}
