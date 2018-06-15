package com.winter.services.impl;

import com.winter.mapper.classesMapper;
import com.winter.model.classes;
import com.winter.services.ClassesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service(value = "ClassesService")
public class ClassesServiceimpl extends BaseServiceimpl<classes> implements ClassesService {

    @Autowired
    classesMapper classesMapper;

    @Override
    public List<classes> selectByMajorId(String id) {
        return classesMapper.selectByMajorId(id);
    }
}
