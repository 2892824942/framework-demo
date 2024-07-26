package com.framework.demo.mapper;


import com.framework.demo.entity.Address;
import com.framework.demo.entity.User;
import com.framework.demo.entity.bo.UserFullBO;
import com.framework.demo.pojo.user.UserQuery;
import com.ty.mid.framework.common.pojo.PageResult;
import com.ty.mid.framework.mybatisplus.core.mapper.MPJBaseMapperX;
import com.ty.mid.framework.mybatisplus.core.query.LambdaQueryWrapperX;
import com.ty.mid.framework.mybatisplus.core.query.MPJLambdaWrapperX;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author suyouliang
 * @since 2023-11-27
 */
@Mapper
public interface UserMapper extends MPJBaseMapperX<User, Long> {

    default PageResult<UserFullBO> selectJoinPage(@Param("userPageQuery") UserQuery userQuery) {
        MPJLambdaWrapperX<User> wrapper = new MPJLambdaWrapperX<User>()
                .selectAll(User.class)
                .selectAs(Address::getName, UserFullBO::getAddrName)
                .leftJoin(Address.class, "addr", on -> on
                        .eq(Address::getCode, User::getAddrCode))
                .eqIfPresent(User::getId, userQuery.getId())
                .inIfPresent(User::getId, userQuery.getIds())
                .likeIfPresent(User::getName, userQuery.getName())
                .eqIfPresent(User::getAge, userQuery.getAge());

        return selectJoinPage(UserFullBO.class, userQuery, wrapper);
    }

    ;

    default PageResult<User> selectPage(@Param("userPageQuery") UserQuery userQuery) {
        LambdaQueryWrapperX<User> wrapper = new LambdaQueryWrapperX<User>()
                .eqIfPresent(User::getId, userQuery.getId())
                .inIfPresent(User::getId, userQuery.getIds())
                .likeIfPresent(User::getName, userQuery.getName())
                .eqIfPresent(User::getAge, userQuery.getAge())
                .eqIfPresent(User::getAddrCode, userQuery.getAddrCode());

        return selectPage(userQuery, wrapper);
    }
}
