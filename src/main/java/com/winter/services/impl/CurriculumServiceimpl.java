package com.winter.services.impl;

import com.winter.mapper.curriculumMapper;
import com.winter.model.curriculum;
import com.winter.services.CurriculumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service(value = "CurriculumService")
public class CurriculumServiceimpl extends BaseServiceimpl<curriculum> implements CurriculumService {

    @Autowired
    curriculumMapper curriculumMapper;

    @Override
    public List<curriculum> selectByEduProId(String eduProId) {
        return curriculumMapper.selectByEduProId(eduProId);
    }
}
