package com.neutech.mammalia.mapper;

import com.neutech.mammalia.bean.User;
import com.neutech.mammalia.mapper.sqlProvider.UserSqlProvider;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserMapper {

    @Insert("""
            insert into t_user
            (name, avatar, password, gender, phone, email)
            values( #{user.name}, #{user.avatar}, #{user.password}, #{user.gender}, #{user.phone}, #{user.email})
            """)
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int addUser(@Param("user") User user);

    @Delete("delete from t_user where id=#{id}")
    int deleteUserById(@Param("id") Integer id);

    @UpdateProvider(value = UserSqlProvider.class, method = "updateUserById")
    int updateUserById(@Param("user") User user);

    @Select("""
            select * from t_user where id=#{id}
            """)
    User inquireUserById(@Param("id") Integer id);

    @Select("select * from t_user where name=#{name}")
    User inquireUserByName(@Param("name") String name);

    @SelectProvider(value = UserSqlProvider.class, method = "inquireUserByEmailOrPhone")
    User inquireUserByEmailOrPhone(@Param("user") User user);

    @Select("select * from t_user")
    List<User> inquireAllUsers();
}
