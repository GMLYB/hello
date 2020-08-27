package com.lyb.dao;

import com.lyb.pojo.Teacher;
import com.lyb.utils.MybatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.List;

public class TeacherDaoTest {

    @Test
    public void testTeacher(){
        try (SqlSession sqlSession = MybatisUtils.getSqlSession()) {
            TeacherMapper mapper = sqlSession.getMapper(TeacherMapper.class);
            Teacher teacher = mapper.getTeacher(1);
            System.out.println(teacher);
        }catch (Exception e){
            e.printStackTrace();
        }

    }


    @Test
    public void testTeacher2(){
        try (SqlSession sqlSession = MybatisUtils.getSqlSession()) {
            TeacherMapper mapper = sqlSession.getMapper(TeacherMapper.class);
            Teacher teacher = mapper.getTeacher2(1);
            System.out.println(teacher);
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
