package com.lyb.web;

import com.lyb.pojo.Book;
import com.lyb.pojo.Page;
import com.lyb.service.BookService;
import com.lyb.service.impl.BookServiceImpl;
import com.lyb.utils.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ClientBookServlet extends BaseServlet {

    BookService service = new BookServiceImpl();


    //主页 分页
    protected void page(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //1 获取请求的参数 pageNow 和 pageSize
        int pageNow = WebUtils.parseInt(req.getParameter("pageNow"), 1);
        int pageSize = WebUtils.parseInt(req.getParameter("pageSize"), Page.PAGE_SIZE);
        //2 调用 BookService.page(pageNow，pageSize)： Page 对象
        Page<Book> page = service.page(pageNow,pageSize);
        page.setUrl("client/bookServlet?action=page");
        //3 保存 Page 对象到 Request 域中
        req.setAttribute("page",page);
        //4 请 求 转 发 到 pages/client/index.jsp 页 面
        req.getRequestDispatcher("/pages/client/index.jsp").forward(req,resp);

    }

    //通过价格查询
    protected void pageByPrice(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //1 获取请求的参数 pageNow 和 pageSize
        int pageNow = WebUtils.parseInt(req.getParameter("pageNow"), 1);
        int pageSize = WebUtils.parseInt(req.getParameter("pageSize"), Page.PAGE_SIZE);
        int min = WebUtils.parseInt(req.getParameter("min"),0);
        int max = WebUtils.parseInt(req.getParameter("max"),Integer.MAX_VALUE);
        //2 调用 BookService.page(pageNow，pageSize)： Page 对象
        Page<Book> page = service.pageByPrice(pageNow,pageSize,min,max);
//        StringBuilder sb = new StringBuilder("client/bookServlet?action=pageByPrice");
//        if (req.getParameter("min") != null){
//            sb.append("&min="+req.getParameter("min"));
//        }
//        if (req.getParameter("max") != null){
//            sb.append("&max="+req.getParameter("max"));
//        }
//        page.setUrl(sb.toString());
        page.setUrl("client/bookServlet?action=pageByPrice&min="+min+"&max="+max);
        //3 保存 Page 对象到 Request 域中
        req.setAttribute("page",page);
        //4 请 求 转 发 到 pages/client/index.jsp 页 面
        req.getRequestDispatcher("/pages/client/index.jsp").forward(req,resp);

    }
}
