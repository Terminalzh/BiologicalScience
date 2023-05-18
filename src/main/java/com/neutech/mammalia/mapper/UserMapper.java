package com.neutech.mammalia.mapper;

import com.neutech.mammalia.bean.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserMapper {
    @Insert("""
            insert into t_user
            (name, password, gender, phone, create_time)
            values (#{name}, #{password}, #{gender}, #{phone}, #{createTime});
            """)
    int addUser(User user);

    @Delete("delete from t_user where id = #{id};")
    int deleteUserById(Integer id);

    @Update("""
            update t_user set
            name = #{name},
            password = #{password},
            gender = #{gender},
            phone = #{phone},
            create_time = #{createTime}
            where id = #{id};
            """)
    int updateUserById(User user);

    @Select("select * from t_user where id = #{id}")
    User inquireUserById(Integer id);

    @Select("select * from t_user where name = #{name}")
    User inquireUserByName(String name);

    @Select("select * from t_user")
    List<User> inquireAllUser();
}