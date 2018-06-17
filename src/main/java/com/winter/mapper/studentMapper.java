package com.winter.mapper;

import com.winter.model.student;
import org.apache.ibatis.annotations.Select;

public interface studentMapper extends BaseMapper<student> {

    @Select("select * from student where studentscode=#{code}")
    student selectByScode(String code);
}