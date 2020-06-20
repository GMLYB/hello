package day6_20;

import org.junit.Test;

import java.io.*;

/**
 * 测试FileInputStream和FileOutputStream的使用
 *
 * 结论：
 *      1.对于文本文件(*.txt,java,*.c,*.cpp)，使用字符流处理
 *      2.对于非文本文件(*.jpg,*.mp3,*.mp4,*.avi,*.doc,*.ppt....)，使用字节流处理
 *
 */

public class FileInputOutputStreamTest {

    //使用字节流FileInputStream处理文本文件，可能会导致乱码
    @Test
    public void testFileInputStream() {
        FileInputStream fis = null;
        try {
            //1.创建File类的对象，指明读入和写出的文件
            File file = new File("src\\day6_20\\hello.txt");

            //2.创建输入流和输出流的对象
            fis = new FileInputStream(file);

            //3.数据的读入和写出操作
            byte[] cbuf = new byte[5];
            int len;
            while ((len = fis.read(cbuf)) != -1){
                String str = new String(cbuf, 0, len);
                System.out.print(str);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                //4.关闭流资源
                if(fis != null){
                    fis.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /*
    实现对图片的复制操作

     */

    @Test
    public void testFileInputOutputStream() {
        FileInputStream fis = null;
        FileOutputStream fos = null;
        try {
            //1.创建File类的对象，指明读入和写出的文件;
            File srcfile = new File("src\\day6_20\\等待.jpg");
            File destfile = new File("src\\day6_20\\等待1.jpg");

            //2.创建输入流和输出流的对象
            fis = new FileInputStream(srcfile);
            fos = new FileOutputStream(destfile);

            //3.数据的读入和写出操作
            byte[] buffer = new byte[5];
            int len;
            while ((len = fis.read(buffer)) != -1){
                fos.write(buffer,0,len);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //4.关闭流资源
            try {
                if(fos != null){
                    fos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if(fis != null){
                    fis.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }




}
