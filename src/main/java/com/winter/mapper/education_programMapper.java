package com.winter.mapper;

import com.winter.model.education_program;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface education_programMapper extends BaseMapper<education_program> {

    /**
     * 根据专业获取课程体系
     * @param majorid
     * @return
     */
    @Select("select * from education_program where majorid=#{majorid}")
    List<education_program> selectByMajorId(String majorid);
}