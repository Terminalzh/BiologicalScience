package com.neutech.mammalia.mapper.sqlProvider;

import com.neutech.mammalia.bean.User;
import org.apache.ibatis.jdbc.SQL;

public class UserSqlProvider {
    public static String inquireUserCountByEmailOrPhone(User user) {
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
}
