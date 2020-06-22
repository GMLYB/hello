package day6_22;

import org.junit.Test;

import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 实现TCP的网络编程
 * 例2：客户端发送信息给服务端。服务端将信息以文件形式保存在本地
 *
 */

public class TCPTest1 {

    @Test
    public void client(){

        Socket socket = null;
        OutputStream os = null;
        try {
            //1.创建InetAddress
            InetAddress is = InetAddress.getByName("127.0.0.1");
            //2.创建socket，指明IP地址和端口号
            socket = new Socket(is,7813);
            //3.创建输出流
            os = socket.getOutputStream();
            //4.输出内容
            os.write("Hello World!\n".getBytes());
            os.write("I love China!\n".getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //5.关闭资源
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
        ByteArrayOutputStream baos = null;
        FileWriter fw = null;
        BufferedWriter br = null;
        try {
            //1.创建ServerSocket对象
            ss = new ServerSocket(7813);
            //2.调用ServerSocket中是accept()方法，生成Socket对象
            socket = ss.accept();
            //3.通过socket创建输入流
            is = socket.getInputStream();
            //4.创建接收字节输出流
            baos = new ByteArrayOutputStream();

            byte[] buffer = new byte[5];
            int len;
            while ((len = is.read(buffer)) != -1){
                baos.write(buffer,0,len);

            }
            //5.文件写入的流程，因为是文本文件，所以创建了字符流
            File file = new File("src\\day6_22\\SocketTest.txt");

            fw = new FileWriter(file);

            br = new BufferedWriter(fw);

            br.write(baos.toString());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //6.关闭资源
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (fw != null) {
                try {
                    fw.close();
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
