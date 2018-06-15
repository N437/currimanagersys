package com.winter.services;

import com.winter.model.dictionary;

import java.util.List;

public interface DictionaryService extends BaseService<dictionary> {

    /**
     * 通过类型获取所有的字典列
     * @param typeid
     * @return
     */
    List<dictionary> selectByType(String typeid);
}
