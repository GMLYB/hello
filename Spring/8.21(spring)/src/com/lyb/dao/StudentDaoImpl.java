package com.lyb.dao;

public class StudentDaoImpl implements StudentDao {

    @Override
    public Integer add(int a, int b) {
        System.out.println("StudentDaoImpl:add");
        return a + b;
    }

    @Override
    public String update(String str) {
        System.out.println("StudentDaoImpl:update");
        return str;
    }
}
