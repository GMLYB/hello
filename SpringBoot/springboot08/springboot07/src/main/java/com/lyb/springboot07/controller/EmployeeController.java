package com.lyb.springboot07.controller;

import com.lyb.springboot07.bean.Employee;
import com.lyb.springboot07.mapper.EmployeeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmployeeController {

    @Autowired
    EmployeeMapper employeeMapper;

    @GetMapping("/emp/{id}")
    public Employee getEmpBuId(@PathVariable("id") Integer id){
        return employeeMapper.getEmpById(id);
    }
}
