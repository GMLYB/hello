package day6_21;

import org.junit.Test;

import java.io.*;

/**
 * 处理流之一：缓冲流的作用
 * <p>
 * 1.缓冲流：
 * BufferedInputStream
 * BufferedOutputStream
 * BufferedReader
 * BufferedWriter
 * <p>
 * 2.作用：提高流的读取、写入速度
 * 提高读写速度的原因：内部提供了一个缓冲区
 */

public class BufferedTest {

    /*
    实现非文本文件的复制
     */

    @Test
    public void BufferedStreamTest() {
        BufferedInputStream bis = null;
        BufferedOutputStream bos = null;

        try {
            //1.造文件
            File srcfile = new File("src\\day6_20\\等待.jpg");
            File destfile = new File("src\\day6_21\\等待1.jpg");

            //2.造流
            //2.1 造节点流
            FileInputStream fis = new FileInputStream(srcfile);
            FileOutputStream fos = new FileOutputStream(destfile);

            //2.2 造缓冲流
            bis = new BufferedInputStream(fis);
            bos = new BufferedOutputStream(fos);

            //3.复制的细节：读取、写入
            byte[] buffer = new byte[1024];
            int len;
            while ((len = bis.read(buffer)) != -1) {
                bos.write(buffer, 0, len);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {

            //4.资源关闭
            //要求1：先关外层的流，在关内层的流
            try {
                if (bos != null) {
                    bos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if (bis != null) {
                    bis.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        //说明：关闭外层流的同时，内层流也会自动的进行关闭，关于内层流的关闭，我们可以省略
//        fis.close();
//        fos.close();
    }


    @Test
    public void BufferedReaderWriterTest() {
        BufferedReader br = null;
        BufferedWriter bw = null;

        try {
            //1,2
            br = new BufferedReader(new FileReader(new File("src\\day6_21\\hello.txt")));
            bw = new BufferedWriter(new FileWriter(new File("src\\day6_21\\hello(copy).txt")));

            //3
            //方式一：
//            char[] buffer = new char[1024];
//            int len;
//            while ((len = br.read(buffer)) != -1){
//                bw.write(buffer,0,len);
//            }

            //方式二：使用String
            String data;
            while ((data = br.readLine()) != null) {
                //方法一
//                bw.write(data+"\n");
                bw.write(data);
                bw.newLine();
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //4
            try {
                if (bw != null) {
                    bw.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if (br != null) {
                    br.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


    }

}
