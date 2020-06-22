package day6_22;

import org.junit.Test;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/**
 * UDP的网络编程
 *
 *
 */

public class UDPTest {

    //发送端
    @Test
    public void sender(){

        DatagramSocket socket = null;
        try {
            socket = new DatagramSocket();

            String str = "UDP传输测试!";

            byte[] data = str.getBytes();

            InetAddress inet = InetAddress.getLocalHost();

            DatagramPacket packet = new DatagramPacket(data,0,data.length,inet,7856);

            socket.send(packet);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            socket.close();
        }




    }


    //接收端
    @Test
    public void receiver(){

        DatagramSocket socket = null;
        try {
            socket = new DatagramSocket(7856);

            byte[] buffer = new byte[100];

            DatagramPacket packet = new DatagramPacket(buffer,0,buffer.length);

            socket.receive(packet);

            System.out.println(new String(packet.getData(),0,packet.getLength()));
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            socket.close();
        }


    }



}
