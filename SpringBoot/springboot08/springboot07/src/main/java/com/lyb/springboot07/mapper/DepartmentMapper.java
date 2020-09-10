package com.lyb.springboot07.mapper;

import com.lyb.springboot07.bean.Department;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

//指定这是一个操作数据库的mapper
@Mapper
public interface DepartmentMapper {

    @Select("select * from department where id = #{id}")
    Department getDeptById(Integer id);

    @Delete("delete from department where id=#{id}")
    int deleteDeptById(Integer id);

    @Options(useGeneratedKeys = true,keyProperty = "id")
    @Insert("insert into department(departmentName) values (#{departmentName})")
    int insertDept(Department department);

    @Insert("update department set departmentName=#{departmentName} where id=#{id}")
    int updateDept(Department department);
}
