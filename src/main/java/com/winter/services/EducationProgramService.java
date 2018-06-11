package com.winter.services;

import com.winter.model.education_program;

import java.util.List;

public interface EducationProgramService extends BaseService<education_program> {

    List<education_program> selectByMajorId(String majorid);
}
