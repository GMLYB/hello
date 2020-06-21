package day6_21;

import org.junit.Test;

import java.io.*;

/**
 * 处理流之二：转换流的使用
 * 1.转换流：属于字符流
 * InputStreamReader：将一个字节的输入流转换为字符的输入流
 * OutputStreamWriter：将一个字符的输出流转换为字节的输入流
 * <p>
 * 2.作用：提供字节流与字符流之间的转换
 * <p>
 * 3. 解码： 字节、字节数组 --> 字符数组、字符串
 * 编码：字符数组、字符串 --> 字节字节数组
 * <p>
 * 4.字符集
 */


public class InputStreamReaderTest {

    /*
    InputStreamReader：将一个字节的输入流转换为字符的输入流
     */
    @Test
    public void test1() {

        InputStreamReader isr = null;
        try {
            FileInputStream fis = new FileInputStream("src\\day6_21\\hello.txt");
//        InputStreamReader isr = new InputStreamReader(fis);//使用系统默认的字符集 utf-8
            //参数2指明了字符集，具体使用哪个取决于文件保存时候使用的字符集
            isr = new InputStreamReader(fis, "UTF-8");

            char[] cbf = new char[1024];
            int len;
            while ((len = isr.read(cbf)) != -1) {
                String str = new String(cbf, 0, len);
                System.out.print(str);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (isr != null) {
                    isr.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /*
        OutputStreamWriter：将一个字符的输出流转换为字节的输入流
        综合练习
    */
    @Test
    public void test2() {

        InputStreamReader isr = null;
        OutputStreamWriter osw = null;
        try {
            File file1 = new File("src\\day6_21\\hello.txt");
            File file2 = new File("src\\day6_21\\hello_gbk.txt");

            FileInputStream fis = new FileInputStream(file1);
            FileOutputStream fos = new FileOutputStream(file2);

            isr = new InputStreamReader(fis, "UTF-8");
            osw = new OutputStreamWriter(fos, "GBK");

            char[] cbuf = new char[1024];
            int len;
            while ((len = isr.read(cbuf)) != -1) {
                osw.write(cbuf, 0, len);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (osw != null) {
                    osw.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if (isr != null) {
                    isr.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


    }
}
