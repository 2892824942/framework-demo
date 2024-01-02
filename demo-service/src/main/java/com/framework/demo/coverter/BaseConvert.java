package com.framework.demo.coverter;

import cn.hutool.core.collection.CollUtil;
import com.framework.demo.coverter.business.MappingProvider;
import com.framework.demo.entity.User;
import com.framework.demo.mapper.UserMapper;
import com.ty.mid.framework.common.dto.AbstractNameDTO;
import com.ty.mid.framework.common.entity.BaseIdDO;
import com.ty.mid.framework.common.util.SafeGetUtil;
import com.ty.mid.framework.common.util.collection.CollectionUtils;
import com.ty.mid.framework.core.spring.SpringContextHelper;
import org.mapstruct.AfterMapping;
import org.mapstruct.BeforeMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.*;

/**
 * 用户入值 Covert
 *
 * @author 芋道源码
 */
@Mapper
public interface BaseConvert {
    String SKIP_FLAG = "skip";
    ThreadLocal<Map<String, Object>> METHOD_CONTEXT = new ThreadLocal<>();

    static void setMethodContext(String key, Object value) {
        Map<String, Object> contextMap = METHOD_CONTEXT.get();
        if (contextMap == null) {
            contextMap = new HashMap<>();
            METHOD_CONTEXT.set(contextMap);
        }
        contextMap.put(key, value);
    }

    static Object getMethodContext(String key) {
        Map<String, Object> contextMap = METHOD_CONTEXT.get();
        return (contextMap != null) ? contextMap.get(key) : null;
    }

    static Boolean exit(String key) {
        return Objects.nonNull(getMethodContext(key));
    }

    static void clearMethodContext() {
        METHOD_CONTEXT.remove();
    }

    @BeforeMapping
    default <S extends BaseIdDO<Long>> void tagList(List<S> sourceList) {
        setMethodContext(SKIP_FLAG, Boolean.TRUE);
    }

    @AfterMapping
    default <S extends BaseIdDO<Long>, T extends BaseIdDO<Long>> void handle(S source, @MappingTarget T target) {
        if (exit(SKIP_FLAG)) {
            return;
        }
        MappingProvider.autoWrapper(source, target);
    }


    @AfterMapping
    default <S extends BaseIdDO<Long>, T extends BaseIdDO<Long>> void handleList(List<S> sourceList, @MappingTarget List<T> targetList) {
        //必须在自动装载前清除,否则自动装载过程,影响其他自动装载项读取自己的上下文
        clearMethodContext();
        MappingProvider.autoWrapper(sourceList, targetList);

    }

    @AfterMapping
    default void handleAbstractNameDTO(@MappingTarget AbstractNameDTO abstractNameDTO) {
        if (exit(SKIP_FLAG)) {
            return;
        }
        handleAbstractNameDTOList(Collections.singletonList(abstractNameDTO));
    }


    @AfterMapping
    default <T extends AbstractNameDTO> void handleAbstractNameDTOList(@MappingTarget List<T> abstractNameDTOList) {
        if (CollUtil.isEmpty(abstractNameDTOList)) {
            return;
        }
        List<Long> creatorIdList = CollectionUtils.convertList(abstractNameDTOList, AbstractNameDTO::getCreator);
        List<Long> updaterIdList = CollectionUtils.convertList(abstractNameDTOList, AbstractNameDTO::getUpdater);
        Collection<Long> userIdList = CollUtil.addAll(creatorIdList, updaterIdList);
        if (CollUtil.isEmpty(userIdList)) {
            return;
        }
        UserMapper userMapper = SpringContextHelper.getBean(UserMapper.class);

        Map<Long, User> userMap = userMapper.selectMap(User::getId, userIdList);
        abstractNameDTOList.forEach(abstractNameDTO -> {
            abstractNameDTO.setCreatorName(SafeGetUtil.getString(userMap.get(abstractNameDTO.getCreator()), User::getName));
            abstractNameDTO.setUpdaterName(SafeGetUtil.getString(userMap.get(abstractNameDTO.getUpdater()), User::getName));
        });
    }


}

