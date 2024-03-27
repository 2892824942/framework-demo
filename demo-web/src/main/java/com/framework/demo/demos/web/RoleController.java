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
import com.framework.demo.pojo.role.RoleQuery;
import com.framework.demo.pojo.role.RoleSaveQuery;
import com.framework.demo.service.IRoleService;
import com.ty.mid.framework.common.pojo.BaseResult;
import com.ty.mid.framework.common.util.SafeGetUtil;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;

/**
 * @author
 */
@RestController("角色控制器")
@RequestMapping("role")
public class RoleController {
    @Resource
    private CacheManager cacheManager;

    @Resource
    private IRoleService roleService;



    @PostMapping("/test")
    public BaseResult<Boolean> test(@RequestBody RoleSaveQuery query) {
        return BaseResult.success(roleService.test(query));
    }

    @PostMapping("/cache/get")
    public BaseResult<RoleDTO> cacheGetByCode(@RequestParam String code) {
        Cache roleDTOCache = cacheManager.getCache("test");

        RoleDTO result = SafeGetUtil.getOrDefault(roleDTOCache.get(code, RoleDTO.class), () -> {
            RoleDTO roleDTO = roleService.getByCode(code);
            if (Objects.nonNull(roleDTO)){
                roleDTOCache.put(code, roleDTO);
            }
            return roleDTO;
        });
        return BaseResult.success(result);
    }

    @GetMapping("/getByCode")
    public BaseResult<RoleDTO> getByCode(@RequestParam String code) {
        return BaseResult.success(roleService.getByCode(code));
    }


    @PostMapping("/getPage")
    public BaseResult<List<RoleDTO>> getPage(@RequestBody RoleQuery query) {
        return BaseResult.successPage(roleService.getPage(query));
    }


    @PostMapping("/save")
    public BaseResult<Boolean> save(@Validated @RequestBody RoleSaveQuery query) {
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
