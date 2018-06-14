package com.winter.services;

import com.winter.model.curriculum;

import java.util.List;

public interface CurriculumService extends BaseService<curriculum> {

    List<curriculum> selectByEduProId(String eduProId);
}
