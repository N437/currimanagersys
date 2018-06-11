package com.winter.mapper;

import org.springframework.context.annotation.Primary;

import java.util.List;

@Primary
public interface BaseMapper<T> {
    int deleteByPrimaryKey(String id);

    int insert(T record);

    int insertSelective(T record);

    T selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(T record);

    int updateByPrimaryKey(T record);

    List<T> selectAll();

    int selectCount();

    List<T> select(T record);
}
