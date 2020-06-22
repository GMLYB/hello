package day6_22;

import org.junit.Test;

import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 实现TCP的网络编程
 * 例题3：从客户端发送文件给服务端，服务端保存到本地。并返回“发送成功”给客户端，并关闭相关的连接
 *
 *
 */

public class TCPTest3 {

    @Test
    public void client(){
        Socket socket = null;
        OutputStream os = null;
        BufferedInputStream bis = null;
        ByteArrayOutputStream baos = null;
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
//            System.out.println("发送成功！");
            //关闭输出数据
            socket.shutdownOutput();

            //5.接收来自于服务器端的数据，并显示到控制台上
            InputStream is = socket.getInputStream();

            baos = new ByteArrayOutputStream();
            byte[] buffer2 = new byte[20];
            int len2;
            while ((len2 = is.read(buffer2)) != -1){
                baos.write(buffer2,0,len2);
            }
            System.out.println("反馈信息为："+baos.toString());


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
            if (baos != null) {
                try {
                    baos.close();
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
        OutputStream os = null;

        try {
            //1.
            ss = new ServerSocket(6543);
            //2.
            socket = ss.accept();
            //3.
            is = socket.getInputStream();
            //4.
            bos = new BufferedOutputStream(new FileOutputStream(new File("src\\day6_22\\等待.jpg")));
            //5.
            byte[] buffer = new byte[1024];
            int len;
            while ((len = is.read(buffer)) != -1){
                bos.write(buffer,0,len);
            }
//            System.out.println("接收成功！");

            //6.服务端给予客户端反馈
            os = socket.getOutputStream();
            os.write("已经接收成功，谢谢！".getBytes());

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
            if (os != null) {
                try {
                    os.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }


    }

}
