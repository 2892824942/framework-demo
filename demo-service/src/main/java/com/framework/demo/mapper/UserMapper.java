package com.framework.demo.mapper;


import com.framework.demo.entity.User;
import com.framework.demo.pojo.user.UserPageQuery;
import com.ty.mid.framework.common.pojo.PageResult;
import com.ty.mid.framework.mybatisplus.core.mapper.BaseMapperX;
import com.ty.mid.framework.mybatisplus.core.query.LambdaQueryWrapperX;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author suyouliang
 * @since 2023-11-27
 */
@Mapper
public interface UserMapper extends BaseMapperX<User> {

    default PageResult<User> selectPage(@Param("userPageQuery") UserPageQuery userPageQuery){
        LambdaQueryWrapperX<User> wrapper = new LambdaQueryWrapperX<User>()
                .eqIfPresent(User::getId, userPageQuery.getId())
                .likeIfPresent(User::getName, userPageQuery.getName())
                .eqIfPresent(User::getAge, userPageQuery.getAge());

        return selectPage(userPageQuery, wrapper);
    };
}
