package com.lyb.springboot07.mapper;


import com.lyb.springboot07.bean.Employee;

public interface EmployeeMapper {

    Employee getEmpById(Integer id);

    int deleteEmpById(Integer id);

    int insertEmp(Employee employee);

    int updateEmp(Employee employee);
}
