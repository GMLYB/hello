package com.lyb.dao;

import com.lyb.pojo.Student;
import com.lyb.utils.MybatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.List;

public class StudentDaoTest {

    @Test
    public void testgetStudentList(){
        try(SqlSession sqlSession = MybatisUtils.getSqlSession()) {
            StudentMapper mapper = sqlSession.getMapper(StudentMapper.class);
            List<Student> list = mapper.getStudentList();
            list.forEach(System.out::println);

        }catch (Exception e){
            e.printStackTrace();
        }

    }

    @Test
    public void testgetStudentById(){
        try(SqlSession sqlSession = MybatisUtils.getSqlSession()) {
            StudentMapper mapper = sqlSession.getMapper(StudentMapper.class);
            Student student = mapper.getStudentById(2);
            System.out.println(student);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Test
    public void testgetStudents(){
        try(SqlSession sqlSession = MybatisUtils.getSqlSession()) {
            StudentMapper mapper = sqlSession.getMapper(StudentMapper.class);
            List<Student> list = mapper.getStudents();
            list.forEach(System.out::println);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Test
    public void testgetStudents2(){
        try(SqlSession sqlSession = MybatisUtils.getSqlSession()) {
            StudentMapper mapper = sqlSession.getMapper(StudentMapper.class);
            List<Student> list = mapper.getStudents2();
            list.forEach(System.out::println);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
