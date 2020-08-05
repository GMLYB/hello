package updown;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class UploadServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");
        System.out.println("传输到这了");
        // 4、编写服务器代码（Servlet 程序）接收，处理上传的数据。
        //先判断上传的数据是否多段数据（只有是多段的数据，才是文件上传的）
        if (ServletFileUpload.isMultipartContent(req)){
            // 1 创建 fileItemFactory 实现类
            FileItemFactory fileItemFactory = new DiskFileItemFactory();
            // 2 创建用于解析上传数据的 ServletFileUpload 工具类
            ServletFileUpload servletFileUpload = new ServletFileUpload(fileItemFactory);
            try {
                //3 解析上传的数据，得到每一个表单项 FileItem
                List<FileItem> list = servletFileUpload.parseRequest(req);
                //4 循环判断，每一个表单项，是普通类型，还是上传的文件
                for (FileItem item: list){
                    if (item.isFormField()){
                        //4.1 普通项列表
                        System.out.println("表单项name的属性值:"+item.getFieldName());
                        // 设置参数 UTF-8 解决乱码问题
                        System.out.println("表单项value的属性值:"+item.getString("UTF-8"));
                    }else {
                        //4.2 上传的文件
                        System.out.println("表单项name的属性值:"+item.getFieldName());
                        System.out.println("上传的文件名："+item.getName());
                        // 写入磁盘
                        item.write(new File("e:\\" + item.getName()));

                    }
                }

            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }
}
