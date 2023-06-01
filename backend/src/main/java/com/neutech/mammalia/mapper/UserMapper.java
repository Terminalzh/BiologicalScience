package com.neutech.mammalia.mapper;

import com.neutech.mammalia.bean.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserMapper {

    @Insert("""
            insert into t_user
            (name, password, gender, phone, email, create_time)
            values( #{user.name}, #{user.password}, #{user.gender}, #{user.phone}, #{user.email}, #{user.createTime})
            """)
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int addUser(@Param("user") User user);

    @Delete("delete from t_user where id=#{id}")
    int deleteUserById(@Param("id") Integer id);

    @Update("""
            update t_user set
            name=#{user.name},
            password=#{user.password},
            gender=#{user.gender},
            phone=#{user.phone},
            email=#{user.email},
            create_time=#{user.createTime}
            where id=#{user.id}
            """)
    int updateUserById(@Param("user") User user);

    @Select("select * from t_user where id=#{id}")
    User inquireUserById(@Param("id") Integer id);

    @Select("select * from t_user where name=#{name}")
    User inquireUserByName(@Param("name") String name);

    @Select("select * from t_user")
    List<User> inquireAllUsers();
}
