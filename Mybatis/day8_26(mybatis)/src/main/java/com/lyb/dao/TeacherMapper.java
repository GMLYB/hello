package com.lyb.dao;

import com.lyb.pojo.Student;
import com.lyb.pojo.Teacher;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TeacherMapper {

    //获取指定id的老师和他的学生
    Teacher getTeacher(@Param("tid")int id);

    //获取指定id的老师和他的学生
    Teacher getTeacher2(@Param("tid")int id);

}
