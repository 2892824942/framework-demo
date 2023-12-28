package com.framework.demo.coverter;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import com.framework.demo.dto.RoleDTO;
import com.framework.demo.entity.Role;
import com.framework.demo.mapper.RoleMapper;
import com.ty.mid.framework.common.util.SafeGetUtil;
import com.ty.mid.framework.core.spring.SpringContextHelper;
import com.ty.mid.framework.core.util.StringUtils;
import org.mapstruct.Mapper;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 用户入值 Covert
 *
 * @author 芋道源码
 */
@Mapper
public interface BaseConvert {
    String COMMA = ",";

//    @AfterMapping
//    default void handleAbstractNameDTO(@MappingTarget AbstractNameDTO abstractNameDTO) {
//        UserMapper userMapper = SpringContextHelper.getBean(UserMapper.class);
//        List<Long> userIdList = Lists.newArrayList(abstractNameDTO.getCreator(), abstractNameDTO.getUpdater());
//        Map<Long, User> userMap = userMapper.selectMap(User::getId, userIdList);
//        abstractNameDTO.setCreatorName(SafeGetUtil.getString(userMap.get(abstractNameDTO.getCreator()), User::getName));
//        abstractNameDTO.setUpdaterName(SafeGetUtil.getString(userMap.get(abstractNameDTO.getUpdater()), User::getName));
//    }
//
//
//    @AfterMapping
//    default <T extends AbstractNameDTO> void handleAbstractNameDTOList(@MappingTarget List<T> abstractNameDTOList) {
//        if (CollUtil.isEmpty(abstractNameDTOList)) {
//            return;
//        }
//        List<Long> creatorIdList = CollectionUtils.convertList(abstractNameDTOList, AbstractNameDTO::getCreator);
//        List<Long> updaterIdList = CollectionUtils.convertList(abstractNameDTOList, AbstractNameDTO::getUpdater);
//        Collection<Long> userIdList = CollUtil.addAll(creatorIdList, updaterIdList);
//        UserMapper userMapper = SpringContextHelper.getBean(UserMapper.class);
//
//        Map<Long, User> userMap = userMapper.selectMap(User::getId, userIdList);
//        abstractNameDTOList.forEach(abstractNameDTO -> {
//            abstractNameDTO.setCreatorName(SafeGetUtil.getString(userMap.get(abstractNameDTO.getCreator()), User::getName));
//            abstractNameDTO.setUpdaterName(SafeGetUtil.getString(userMap.get(abstractNameDTO.getUpdater()), User::getName));
//        });
//    }

    default List<RoleDTO> convertRole1(String roleIdStr) {
        if (!StringUtils.hasText(roleIdStr)) {
            return Collections.emptyList();
        }
        return convertRole(Arrays.stream(StrUtil.splitToLong(roleIdStr, COMMA)).boxed().collect(Collectors.toList()));
    }

    default List<RoleDTO> convertRole(List<Long> codeList) {
        if (CollUtil.isEmpty(codeList)) {
            return Collections.emptyList();
        }
        RoleMapper roleMapper = SpringContextHelper.getBean(RoleMapper.class);

        Map<Long, Role> roleMap = SafeGetUtil.get(roleMapper.selectMap(Role::getId, codeList));
        return codeList.stream().map(code -> {
            Role role = roleMap.get(code);
            if (Objects.isNull(role)) {
                return null;
            }
            RoleDTO roleDTO = new RoleDTO();
            roleDTO.setName(role.getName());
            roleDTO.setCode(role.getCode());
            roleDTO.setSort(role.getSort());
            return roleDTO;
        }).filter(Objects::nonNull).collect(Collectors.toList());
    }

}
