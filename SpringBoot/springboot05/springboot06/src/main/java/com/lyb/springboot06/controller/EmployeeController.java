package com.lyb.springboot06.controller;

import com.lyb.springboot06.dao.EmployeeDao;
import com.lyb.springboot06.entities.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Collection;

@Controller
public class EmployeeController {

    @Autowired
    EmployeeDao employeeDao;

    @GetMapping("/emps")
    public String list(Model model){
        //查询
        Collection<Employee> list = employeeDao.getAll();
        //放到请求域中
        model.addAttribute("emps",list);

        return "emp/list";
    }
}
