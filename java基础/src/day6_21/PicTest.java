package day6_21;

import org.junit.Test;

import java.io.*;

public class PicTest {

    //图片加密

    @Test
    public void test1(){
        BufferedInputStream bis = null;
        BufferedOutputStream bos = null;
        try {
            bis = new BufferedInputStream(new FileInputStream("src\\day6_21\\等待1.jpg"));
            bos = new BufferedOutputStream(new FileOutputStream("src\\day6_21\\等待（加密）.jpg"));

            byte[] buffer = new byte[1024];
            int len;

            while ((len = bis.read(buffer)) != -1){
                //字节数组进行修改
                for (int i = 0; i < len; i++) {
                    buffer[i] = (byte) (buffer[i] ^ 5);
                }
                bos.write(buffer,0,len);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (bos != null){
                    bos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            if(bis != null){
                try {
                    bis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    //图片解密
    @Test
    public void test2(){
        BufferedInputStream bis = null;
        BufferedOutputStream bos = null;
        try {
            bis = new BufferedInputStream(new FileInputStream("src\\day6_21\\等待（加密）.jpg"));
            bos = new BufferedOutputStream(new FileOutputStream("src\\day6_21\\等待（解密）.jpg"));

            byte[] buffer = new byte[1024];
            int len;

            while ((len = bis.read(buffer)) != -1){
                //字节数组进行修改
                for (int i = 0; i < len; i++) {
                    buffer[i] = (byte) (buffer[i] ^ 5);
                }
                bos.write(buffer,0,len);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (bos != null){
                    bos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            if(bis != null){
                try {
                    bis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
