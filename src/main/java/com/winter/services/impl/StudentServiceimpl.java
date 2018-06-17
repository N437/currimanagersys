package com.winter.services.impl;

import com.winter.mapper.studentMapper;
import com.winter.model.student;
import com.winter.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service(value = "StudentService")
public class StudentServiceimpl extends BaseServiceimpl<student> implements StudentService {

    @Autowired
    studentMapper studentMapper;

    @Override
    public student selectByScode(String code) {
        return studentMapper.selectByScode(code);
    }
}
