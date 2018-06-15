package com.winter.services;

import com.winter.model.classes;

import java.util.List;

public interface ClassesService extends BaseService<classes> {

    /**
     * 根据专业获取班级
     * @param id
     * @return
     */
    List<classes> selectByMajorId(String id);
}
