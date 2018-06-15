package com.winter.mapper;

import com.winter.model.classes;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface classesMapper extends BaseMapper<classes> {

    @Select("select * from classes where majorid=#{majorid}")
    List<classes> selectByMajorId(String majorid);
}