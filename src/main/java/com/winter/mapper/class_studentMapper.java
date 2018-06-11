package com.winter.mapper;

import com.winter.model.class_student;

public interface class_studentMapper {
    int deleteByPrimaryKey(String classstudentid);

    int insert(class_student record);

    int insertSelective(class_student record);

    class_student selectByPrimaryKey(String classstudentid);

    int updateByPrimaryKeySelective(class_student record);

    int updateByPrimaryKey(class_student record);
}