package servlet;

import pojo.Student;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SearchStudentServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1.获取请求参数
        //2.查询数据库
        // 使用 for循环 生成查询到的数据 模拟
        List<Student> studentList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            int t = i+1;
            studentList.add(new Student(t,"name"+t,18+t,"phone"+t));
        }

        //3. 使用 request 域存储数据
        req.setAttribute("studentlist",studentList);

        //4. 请求转发 到 showStudent.jsp 页面中
        req.getRequestDispatcher("/showStudent.jsp").forward(req,resp);

    }
}
