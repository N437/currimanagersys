package com.winter.services.impl;

import com.winter.mapper.dictionaryMapper;
import com.winter.model.dictionary;
import com.winter.services.DictionaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service(value = "dictionaryService")
public class DictionaryServiceimpl extends BaseServiceimpl<dictionary> implements DictionaryService {

    @Autowired
    dictionaryMapper dictionaryMapper;

    @Override
    public List<dictionary> selectByType(String typeid) {
        return dictionaryMapper.selectByType(typeid);
    }
}
