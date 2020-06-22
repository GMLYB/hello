package day6_22;

import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 实现TCP的网络编程
 * 例子1：客户端发送信息给服务端服务端将数据显示在控制台上
 */


public class TCPTest {


    @Test
    public void client() {

        Socket socket = null;
        OutputStream os = null;
        try {
            //1.创建Socket对象，指明服务器端的ip和端口号
            InetAddress inet = InetAddress.getByName("192.168.171.1");

            socket = new Socket(inet, 8659);
            //2.获取一个输出流，用于数据输出
            os = socket.getOutputStream();
            //3.写出数据的操作
            os.write("这是客户端!".getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                //4.资源关闭
                if (os != null) {
                    os.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {

                if (socket != null) {
                    socket.close();
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    @Test
    public void server() {

        ServerSocket ss = null;
        Socket accept = null;
        InputStream is = null;
        ByteArrayOutputStream baos = null;
        try {
            //1.创建服务器端的ServerSocket，指明自己的端口号
            ss = new ServerSocket(8659);
            //2.调用accept()表示接收来自客户端的socket
            accept = ss.accept();
            //3.获取输入流
            is = accept.getInputStream();


            //不建议这样写，可能会出现乱码
//        byte[] buffer = new byte[20];
//        int len;
//        while ((len = is.read(buffer)) != -1){
//            String str = new String(buffer,0,len);
//            System.out.println(str);
//        }
            //4.读取输入流中的数据
            baos = new ByteArrayOutputStream();

            byte[] buffer = new byte[5];
            int len;
            while ((len = is.read(buffer)) != -1){
                baos.write(buffer,0,len);
            }

            String str = baos.toString();

            System.out.println(str);

            System.out.println("收到来自于："+accept.getInetAddress().getHostAddress()+" 的数据");

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //5.关闭资源
            try {
                if (baos != null){
                    baos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if (is != null){
                    is.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if (accept != null){
                    accept.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if (ss != null){
                    ss.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

}
