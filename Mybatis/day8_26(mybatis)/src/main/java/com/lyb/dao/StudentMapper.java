package com.lyb.dao;

import com.lyb.pojo.Student;
import com.lyb.pojo.Teacher;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface StudentMapper {

    @Select("select id,name,tid from student")
    List<Student> getStudentList();

    List<Student> getStudents();

    List<Student> getStudents2();

    Student getStudentById(@Param("sid") int id);

    Teacher getTeacherById(@Param("tid") int id);
}
