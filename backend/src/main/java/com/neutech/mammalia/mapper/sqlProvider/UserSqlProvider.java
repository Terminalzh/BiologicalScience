package com.neutech.mammalia.mapper.sqlProvider;

import com.neutech.mammalia.bean.User;
import org.apache.ibatis.jdbc.SQL;

public class UserSqlProvider {
    public static String inquireUserByEmailOrPhone(User user) {
        SQL sql = new SQL();
        sql.SELECT("*");
        sql.FROM("t_user ");
        sql.WHERE("password = #{user.password}");
        if (user.getEmail() != null)
            sql.WHERE("email = #{user.email}");
        else
            sql.WHERE("phone = #{user.phone}");
        return sql.toString();
    }

    public static String updateUserById(User user) {
        SQL sql = new SQL();
        sql.UPDATE("t_user");
        if (user.getName() != null)
            sql.SET("name = #{user.name}");
        if (user.getAvatar() != null)
            sql.SET("avatar = #{user.avatar}");
        if (user.getPassword() != null)
            sql.SET("password = #{user.password}");
        sql.SET("gender = #{user.gender}");
        if (user.getPhone() != null)
            sql.SET("phone = #{user.phone}");
        if (user.getEmail() != null)
            sql.SET("email = #{user.email}");
        sql.WHERE("id = #{user.id}");
        return sql.toString();
    }
}
