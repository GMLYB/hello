package day6_22;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * URL网络编程
 * 1.URL：统一资源定位符。对应着互联网的某一资源地址
 * 2.格式：
 *      http://localhost:90/bysj/addbijiyati.php?username=Tom
 *      协议    主机名   端口     资源地址           参数列表
 *
 */

public class URLTest {

    public static void main(String[] args) {

        try {
            URL url = new URL("http://localhost:90/bysj/addbijiyati.php");
            System.out.println(url.getProtocol());
            System.out.println(url.getHost());

        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }
}
