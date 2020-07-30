package updown;

import org.apache.commons.io.IOUtils;
import sun.misc.BASE64Encoder;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;

public class DownLoad extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1. 获取要下载的文件名
        String downloadFileName = "1.jpg";
        //2. 读取要下载的文件内容 (通过ServletContext对象)
        ServletContext servletContext = getServletContext();

        //获取要下载的文件类型
        String mimeType = servletContext.getMimeType("/file/" + downloadFileName);
        System.out.println("下载的文件类型：" + mimeType);
        //3. 在回传前，通过响应头告诉客户端返回的数据类型
        resp.setContentType(mimeType);

        //4.告诉客户端收到的数据是用于下载使用（响应头）
        // Content-Disposition 响应头，表示收到的数据怎么处理
        // attachment 表示附件，表示下载使用 filename= 表示下载的文件名
        //resp.setHeader("Content-Disposition","attachment; filename=" + downloadFileName);

        String ua = req.getHeader("User-Agent");

        if (ua.contains("Firefox")){
            // 使用下面的格式进行 BASE64 编码后
            String str = "attachment; fileName=" + "=?utf-8?B?" + new BASE64Encoder().encode("中文.jpg".getBytes("utf-8")) + "?=";
            resp.setHeader("Content-Disposition",str);
        }else {
            // URLEncoder.encode("中文.jpg","UTF-8")
            String str = "attachment; filename=" + URLEncoder.encode("中文.jpg","UTF-8");
            resp.setHeader("Content-Disposition",str);
        }


        InputStream resourceAsStream = servletContext.getResourceAsStream("/file/" + downloadFileName);
        // 获取响应的输出流
        ServletOutputStream outputStream = resp.getOutputStream();
        // 读取输入流中的全部数据，复制给输出流 输出给客户端
        //5.把下载的文件回传给客户端
        IOUtils.copy(resourceAsStream,outputStream);

    }
}
