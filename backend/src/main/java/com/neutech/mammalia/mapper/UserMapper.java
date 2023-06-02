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

    @Update("""
            update t_user set
            name=#{user.name},
            avatar=#{user.avatar},
            password=#{user.password},
            gender=#{user.gender},
            phone=#{user.phone},
            email=#{user.email}
            where id=#{user.id}
            """)
    int updateUserById(@Param("user") User user);

    @Select("select * from t_user where id=#{id}")
    User inquireUserById(@Param("id") Integer id);

    @Select("select * from t_user where name=#{name}")
    User inquireUserByName(@Param("name") String name);

    @SelectProvider(value = UserSqlProvider.class, method = "inquireUserCountByEmailOrPhone")
    User inquireUserCountByEmailOrPhone(@Param("user") User user);

    @Select("select * from t_user")
    List<User> inquireAllUsers();
}
