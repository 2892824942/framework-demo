package com.framework.demo.mapper;


import com.framework.demo.entity.Address;
import com.framework.demo.entity.Role;
import com.framework.demo.entity.User;
import com.framework.demo.entity.bo.UserFullBO;
import com.framework.demo.pojo.user.UserPageQuery;
import com.ty.mid.framework.common.pojo.PageResult;
import com.ty.mid.framework.mybatisplus.core.mapper.BaseMapperX;
import com.ty.mid.framework.mybatisplus.core.query.LambdaQueryWrapperX;
import com.ty.mid.framework.mybatisplus.core.query.MPJLambdaWrapperX;
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
public interface UserMapper extends BaseMapperX<User,Long> {

    default PageResult<UserFullBO> selectJoinPage(@Param("userPageQuery") UserPageQuery userPageQuery){
        MPJLambdaWrapperX<User> wrapper = new MPJLambdaWrapperX<User>()
                .selectAll(User.class)
                .selectAs(Address::getName, UserFullBO::getAddrName)
                .leftJoin(Address.class, "addr", on -> on
                        .eq(Address::getCode, User::getAddrCode))
                .eqIfPresent(User::getCreator, userPageQuery.getId())
                .likeIfPresent(User::getName, userPageQuery.getName())
                .eqIfPresent(User::getAge, userPageQuery.getAge());

        return selectJoinPage(UserFullBO.class,userPageQuery, wrapper);
    };

    default PageResult<User> selectPage(@Param("userPageQuery") UserPageQuery userPageQuery){
        LambdaQueryWrapperX<User> wrapper = new LambdaQueryWrapperX<User>()
                .eqIfPresent(User::getId, userPageQuery.getId())
                .likeIfPresent(User::getName, userPageQuery.getName())
                .eqIfPresent(User::getAge, userPageQuery.getAge())
                .eqIfPresent(User::getAddrCode, userPageQuery.getAddrCode());

        return selectPage(userPageQuery, wrapper);
    };
}
