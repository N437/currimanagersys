package com.winter.mapper;

import com.winter.model.curriculum;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface curriculumMapper extends BaseMapper<curriculum> {

    /**
     * 根据课程培养方案获取对应课程体系中的所有课程
     * @param eduProid
     * @return
     */
    @Select("select * from curriculum where educationprogramid=#{eduProid} ")
    List<curriculum> selectByEduProId(String eduProid);
}