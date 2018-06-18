package com.winter.services.impl;

import com.winter.mapper.teacherMapper;
import com.winter.model.teacher;
import com.winter.services.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service(value = "TeacherService")
public class TeacherServiceimpl extends BaseServiceimpl<teacher> implements TeacherService {

    @Autowired
    teacherMapper teacherMapper;

    @Override
    public teacher selectByTcode(String code) {
        return teacherMapper.selectByTcode(code);
    }
}
