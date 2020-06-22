package day6_22;

import org.junit.Test;

import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 实现TCP的网络编程
 * 例子3：客户端发送文件给服务器，服务器将文件保存在本地
 *
 *
 */
public class TCPTest2 {

    @Test
    public void client(){
        Socket socket = null;
        OutputStream os = null;
        BufferedInputStream bis = null;

        try {
            //1.创建Socket
            socket = new Socket(InetAddress.getByName("127.0.0.1"), 6543);
            //2.获取输出流
            os = socket.getOutputStream();
            //3.获取输入流，从什么地方获取文件
            bis = new BufferedInputStream(new FileInputStream(new File("src\\day6_21\\等待1.jpg")));
            //4.文件读取，传输
            byte[] buffer = new byte[1024];
            int len;
            while ((len = bis.read(buffer)) != -1){
                os.write(buffer,0,len);
            }
            System.out.println("发送成功！");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //5.资源关闭
            if (bis != null) {
                try {
                    bis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (os != null) {
                try {
                    os.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (socket != null) {
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    @Test
    public void server(){

        ServerSocket ss = null;
        Socket socket = null;
        InputStream is = null;
        BufferedOutputStream bos = null;
        try {
            ss = new ServerSocket(6543);

            socket = ss.accept();

            is = socket.getInputStream();

            bos = new BufferedOutputStream(new FileOutputStream(new File("src\\day6_22\\等待.jpg")));

            byte[] buffer = new byte[1024];
            int len;

            while ((len = is.read(buffer)) != -1){
                bos.write(buffer,0,len);
            }
            System.out.println("接收成功！");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (bos != null) {
                try {
                    bos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (socket != null) {
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (ss != null) {
                try {
                    ss.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }


    }


}
