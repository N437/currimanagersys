package com.winter.mapper;
import com.winter.model.semester;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

public interface semesterMapper {

    @Delete("delete from semesterid where semesterid = #{semesterid}")
    int deleteByPrimaryKey(String semesterid);

    @Insert("insert into semesterid values(……)")
    int insert(semester record);
    
    @Insert("")
    int insertSelective(semester record);


    semester selectByPrimaryKey(String semesterid);

    int updateByPrimaryKeySelective(semester record);

    int updateByPrimaryKey(semester record);
}