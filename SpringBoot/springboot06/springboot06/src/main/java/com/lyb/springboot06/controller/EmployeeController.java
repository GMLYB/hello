package com.lyb.springboot06.controller;

import com.lyb.springboot06.dao.DepartmentDao;
import com.lyb.springboot06.dao.EmployeeDao;
import com.lyb.springboot06.entities.Department;
import com.lyb.springboot06.entities.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@Controller
public class EmployeeController {

    @Autowired
    EmployeeDao employeeDao;

    @Autowired
    DepartmentDao departmentDao;

    @GetMapping("/emps")
    public String list(Model model){
        //查询
        Collection<Employee> list = employeeDao.getAll();
        //放到请求域中
        model.addAttribute("emps",list);

        return "emp/list";
    }

    @GetMapping("/emp")
    public String toAddPage(Model model){
//        先查询全部部门，再跳转到添加页面
        Collection<Department> departments = departmentDao.getDepartments();
        model.addAttribute("departments",departments);
        return "emp/add";
    }

    @PostMapping("/emp")
    public String AddEmp(Employee employee){
//        System.out.println(employee);
        employeeDao.save(employee);
        return "redirect:/emps";
    }


    @GetMapping("/emp/{id}")
    public String toUpdateEmp(@PathVariable("id") Integer id, Model model){
        Employee employee = employeeDao.get(id);
        model.addAttribute("emp",employee);
        Collection<Department> departments = departmentDao.getDepartments();
        model.addAttribute("departments",departments);
        return "emp/add";
    }

    @PutMapping("/emp")
    public String updateEmp(Employee employee){
        System.out.println(employee);
        employeeDao.save(employee);
        return "redirect:/emps";
    }

    @DeleteMapping("/emp/{id}")
    public String deleteEmp(@PathVariable("id") Integer id){
        employeeDao.delete(id);
        return "redirect:/emps";
    }
}
