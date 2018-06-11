package com.winter.services.impl;

import com.winter.mapper.education_programMapper;
import com.winter.model.education_program;
import com.winter.services.EducationProgramService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service(value = "EducationProgramService")
public class EducationProgram extends BaseServiceimpl<education_program> implements EducationProgramService {

    @Autowired
    education_programMapper educationProgramMapper;

    @Override
    public List<education_program> selectByMajorId(String majorid) {
        return educationProgramMapper.selectByMajorId(majorid);
    }
}
