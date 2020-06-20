package day6_20;

import org.junit.Test;

import java.io.*;

/**
 * 一、流的分类
 * 1.操作数据单位：字节流、字符流
 * 2.数据的流向：输入流、输出流
 * 3.流的角色：节点流、处理流
 *
 * 二、流的体系结构
 * 抽象基类             节点流（文件流）                缓冲流（处理流的一种）
 * InputStream          FileInputStream                 BufferedInputStream
 * OutputStream         FileOutputStream                BufferOutputStream
 * Reader               FileReader                      BufferReader
 * Writer               FileWriter                      BufferWriter
 *
 *
 * 操作步骤：
 * 1.实例化File类的对象，指明要操作的文件
 * 2.提供具体的流
 * 3.读入的操作
 * 4.资源的关闭
 *
 */

public class FileReaderWriterTest {

    /**
     * 将hello.txt文件内容读入程序，并输出到控制台
     *
     *      说明：
     *      1.read()：返回读入的一个字符，如果达到文件末尾，返回-1；
     *      2.异常处理：为了保证流资源一定可以执行关闭操作。需要使用try-catch-finally处理
     *      3.读入的文件一定要存在，否则会报FileNotFoundException
     */
    @Test
    public void testFileReader(){
        FileReader fr = null;
        try {
            //1.实例化File类的对象，指明要操作的文件
            File file = new File("src\\day6_20\\hello.txt");
//        System.out.println(file.getAbsolutePath());
            //2.提供具体的流
            fr = new FileReader(file);
            //3.数据的读入
            //read()：返回读入的一个字符，如果达到文件末尾，返回-1
            //方式一
//        int data = fr.read();
//        while (data != -1){
//            System.out.print((char)data);
//            data = fr.read();
//        }

            //方式二：语法上针对方式一的修改
            int data;
            while ((data = fr.read()) != -1){
                System.out.print((char)data);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //4.流的关闭操作
            try {
                if(fr != null){
                    fr.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    //对read()操作的升级：使用read的重载方法
    @Test
    public void testFileReader1(){
        FileReader fr = null;
        try {
            //1.实例化File类的对象，指明要操作的文件
            File file = new File("src\\day6_20\\hello.txt");
//        System.out.println(file.getAbsolutePath());
            //2.提供具体的流
            fr = new FileReader(file);
            //3.数据的读入
            //read(char[] cbuf)：返回每次读入cbuf数组中的字符的个数。如果达到文件末尾，返回-1；
            char[] cbuf = new char[5];
            int len;
            while ((len = fr.read(cbuf)) != -1){
                //方式一：
//                //错误写法:输出结果:HelloWorld123ld
//                for (int i = 0; i < cbuf.length; i++) {
//                    System.out.print(cbuf[i]);
//                }
//                //正确写法
//                for (int i = 0; i < len; i++) {
//                    System.out.print(cbuf[i]);
//                }
//                //方式二：
//                //错误写法-->对应方式一的错误写法
//                String str = new String(cbuf);
//                System.out.print(str);
                //正确写法
                String str1 = new String(cbuf, 0, len);
                System.out.print(str1);


            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //4.流的关闭操作
            try {
                if(fr != null){
                    fr.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /*
    从内存中写出数据到硬盘的文件里
    1.输出操作，对应的File可以不存在，并不会报异常
    2.
        File对应的硬盘中的文件如果不存在，在输出的过程中，会自动创建此文件
         File对应的硬盘中的文件如果存在：
                如果流使用的构造器是：FileWrite(file,false) / FileWriter(file)：对原有文件的覆盖
                如果流使用的构造器是：FileWrite(file,true) ：不会对原有文件覆盖，而是在原有文件基础上追加内容

     */

    @Test
    public void testFileWriter(){
        FileWriter fw = null;
        try {
            //1.提供File类的对象，指明写出到的文件
            File file = new File("src\\day6_20\\hello1.txt");

            //2.提供FileWriter的对象，用于数据的写出    append:true-->原有的基础上追加  false-->覆盖
//        FileWriter fw = new FileWriter(file);
            fw = new FileWriter(file,true);

            //3.写出的操作
            fw.write("I have a dream!\n");
            fw.write("you need a dream!");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //4.流资源的关闭
            if(fw != null){
                try {
                    fw.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    /*
    综合，从一个文件读取，然后在写到另外一个文件里面
     */

    @Test
    public void testFileReaderFileWriter() {
        FileReader fr = null;
        FileWriter fw = null;

        try {
            //1.创建File类的对象，指明读入和写出的文件
            File srcfile = new File("src\\day6_20\\hello.txt");
            File destfile = new File("src\\day6_20\\hello2.txt");

            //2.创建输入流和输出流的对象
            fr = new FileReader(srcfile);
            fw = new FileWriter(destfile);

            //3.数据的读入和写出操作
            char[] cbuf = new char[5];
            int len;
            while ((len = fr.read(cbuf)) != -1){
                fw.write(cbuf,0,len);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //4.关闭流资源
            if(fw != null){
                try {
                    fw.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            try {
                if(fr != null){
                    fr.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }



    }


}
