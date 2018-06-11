package com.winter.services.impl;

import com.github.pagehelper.PageHelper;
import com.winter.mapper.BaseMapper;
import com.winter.services.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BaseServiceimpl<T> implements BaseService<T> {

    @Autowired
    BaseMapper<T> mapper;

    @Override
    public int deleteByPrimaryKey(String id) {
        return mapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(T record) {
        return mapper.insert(record);
    }

    @Override
    public int insertSelective(T record) {
        return mapper.insertSelective(record);
    }

    @Override
    public T selectByPrimaryKey(String id) {
        return mapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(T record) {
        return mapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(T record) {
        return mapper.updateByPrimaryKey(record);
    }

    @Override
    public List<T> selectAll() {
        return mapper.selectAll();
    }

    @Override
    public List<T> selectBypage(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        return selectAll();
    }

    @Override
    public int selectCount() {
        return mapper.selectCount();
    }

    @Override
    public List<T> select(int pageNum, int pageSize, T record) {
        PageHelper.startPage(pageNum,pageSize);
        return mapper.select(record);
    }
}
