package com.framework.demo.service;


import com.framework.demo.dto.AddrDTO;
import com.framework.demo.pojo.addr.AddrQuery;
import com.framework.demo.pojo.addr.AddrSaveQuery;
import com.framework.demo.pojo.role.RoleSaveQuery;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author suyouliang
 * @since 2023-11-27
 */
public interface IAddressService {
    AddrDTO getByCode(String code);

    List<AddrDTO> getList(AddrQuery addrQuery);

    Boolean save(AddrSaveQuery query);

    void saveBatch(List<AddrSaveQuery> query);

    Boolean deleteById(Long id);

}
