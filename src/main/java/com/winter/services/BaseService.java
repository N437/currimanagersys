package com.winter.services;


import java.util.List;

public interface BaseService<T> {

    /**
     * 根据主键删除数据
     * @param id
     * @return
     */
    int deleteByPrimaryKey(String id);

    /**
     * 添加完整model数据
     * @param record
     * @return
     */
    int insert(T record);

    /**
     * 添加model的部分数据
     * @param record
     * @return
     */
    int insertSelective(T record);

    /**
     * 根据主键查询数据
     * @param id
     * @return
     */
    T selectByPrimaryKey(String id);

    /**
     * 部分更新model数据
     * @param record
     * @return
     */
    int updateByPrimaryKeySelective(T record);

    /**
     * 更新所有model数据
     * @param record
     * @return
     */
    int updateByPrimaryKey(T record);

    /**
     * 获取所有数据
     * @return
     */
    List<T> selectAll();

    /**
     * 分页查询数据
     * @param pageNum 开始页数
     * @param pageSize 页面长度
     * @return
     */
    List<T> selectBypage(int pageNum, int pageSize);

    /**
     * 获取总数
     * @return
     */
    int selectCount();

    /**
     * 多条件查询
     * @param record
     * @return
     */
    List<T> select(int pageNum, int pageSize, T record);
}
