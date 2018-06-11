package com.winter.mapper;

import com.winter.model.resource;

public interface resourceMapper {
    int deleteByPrimaryKey(String resourceid);

    int insert(resource record);

    int insertSelective(resource record);

    resource selectByPrimaryKey(String resourceid);

    int updateByPrimaryKeySelective(resource record);

    int updateByPrimaryKey(resource record);
}