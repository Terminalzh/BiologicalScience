package com.neutech.mammalia.mapper;

import com.neutech.mammalia.bean.Data2;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface DataMapper {
    @Insert("""
            insert into data2
            (species_id, c_name, latin_name, category_id, brief_introduction, detail_introduction, recommend, level, create_time, update_time)
            values (#{speciesId},#{cName},#{latinName},#{categoryId},#{briefIntroduction},#{detailIntroduction},#{recommend},#{level},current_timestamp,current_timestamp)
            """)
    int addData2(Data2 data2);

    @Insert("insert into data1 (path, species_id) values (#{path},#{speciesId});")
    void addData1(String path, Integer speciesId);

    @Select("select * from data2")
    List<Data2> inquireAll();
}
