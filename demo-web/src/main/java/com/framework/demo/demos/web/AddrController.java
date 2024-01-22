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

import com.framework.demo.dto.AddrDTO;
import com.framework.demo.entity.Role;
import com.framework.demo.pojo.role.RoleQuery;
import com.framework.demo.pojo.role.RoleSaveQuery;
import com.framework.demo.service.IAddressService;
import com.framework.demo.service.IRoleService;
import com.ty.mid.framework.common.pojo.BaseResult;
import com.ty.mid.framework.common.pojo.PageResult;
import com.ty.mid.framework.lock.annotation.FailFastLock;
import com.ty.mid.framework.lock.annotation.LockKey;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author
 */
@RestController("地址控制器")
@RequestMapping("addr")
public class AddrController {

    @Resource
    private IAddressService addressService;

    @GetMapping("/getByCode")
    public BaseResult<AddrDTO> getByCode(@RequestParam  String code) {
        return BaseResult.success(addressService.getByCodeFromCache(code));
    }

    @GetMapping("/getByCodes")
    public  BaseResult<List<AddrDTO>> getByCodes(@RequestBody List<String> codes) {
        return BaseResult.success(addressService.getByCodesFromCache(codes));
    }

}
