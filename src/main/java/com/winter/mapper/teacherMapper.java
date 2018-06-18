package com.winter.mapper;

import com.winter.model.teacher;
import org.apache.ibatis.annotations.Select;

public interface teacherMapper extends BaseMapper<teacher> {

    @Select("select * from teacher where teachertcode=#{code}")
    teacher selectByTcode(String code);
}