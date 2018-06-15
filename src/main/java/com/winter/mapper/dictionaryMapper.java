package com.winter.mapper;

import com.winter.model.dictionary;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface dictionaryMapper extends BaseMapper<dictionary> {

    @Select("select * from dictionary where dictypeId=#{id}")
    List<dictionary> selectByType(String id);
}