package day6_19;


import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * jdk 8之前的日期时间的API
 * 1.System类中currentTimeMillis();
 * 2.java.lang.Date和子类java.sql.Date
 * 3.SimpleDateFormat
 * 4.Calendar
 *
 */

public class DateTimeTest {
    /**
     * SimpleDateFormat的使用：SimpleDateFormat对日期Date类的格式化和解析
     *
     * 两个操作：
     * 1.格式化：日期-->字符
     * 2. 解析：格式化逆过程，字符串-->日期
     *
     * SimpleDateFormat的实例化
     *
     */

    @Test
    public void testSimpleDateFormat() throws ParseException {
        //SimpleDateFormat实例化：使用默认的构造器
        SimpleDateFormat sdf = new SimpleDateFormat();

        //格式化：日期-->字符
        Date date = new Date();
        System.out.println(date);

        String format = sdf.format(date);
        System.out.println(format);

        //解析：格式化逆过程，字符串-->日期
        String str = "20-6-19 上午10:24";
        Date date1 = sdf.parse(str);
        System.out.println(date1);

        //**************************按照指定的方式格式化和解析：调用带参的构造器**************************
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        //格式化
        String format1 = sdf1.format(date);
        System.out.println(format1);//2020-06-19 10:51:17
        //解析:要求字符串必须是符合SimpleDateFormat识别的格式（通过构造器参数体现），否则抛异常
        Date date2 = sdf1.parse(format1);
        System.out.println(date2);
    }

    /*
    练习一：将字符串"2020-06-19"转换为java.sql.Date
     */
    @Test
    public void test1() throws ParseException {
        String str1 = "2020-06-19";
        //SimpleDateFormat实例化，设置日期格式
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        //将String转成date类型
        Date date = format.parse(str1);
        //实例化java.sql.Date
        java.sql.Date date1 = new java.sql.Date(date.getTime());
        System.out.println(date1);
    }

    /*
    练习二：“三天打鱼两天晒网” 1990-01-01 xxxx-xx-xx 打鱼?还是晒网?
     */
    @Test
    public void test2() throws ParseException {
        String str1 = "1990-01-01";
        String str2 = "2020-06-19";
        //先转成Date类
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date1 = sdf.parse(str1);
        Date date2 = sdf.parse(str2);
        //一天的秒数
        long nianduan = 60 * 60 * 24;
        //计算总秒数
        long tmptime = (date2.getTime() - date1.getTime())/1000;
        System.out.println((tmptime/nianduan + 1) % 5);

    }

    /*
    Calendar日历类(抽象类)的使用
     */
    @Test
    public void testCalendar(){
        //1.实例化
        //方式一：创建调用其子类GregorianCalendar的对象
        //方式二：调用其静态方法getInstance()
        Calendar calendar = Calendar.getInstance();
//        System.out.println(calendar.getClass());

        //2.常用方法
        //get()
        int days = calendar.get(Calendar.DAY_OF_MONTH);
        System.out.println(days);//本月第几天
        System.out.println(calendar.get(Calendar.DAY_OF_YEAR));//今年第几天
        //set()
        calendar.set(Calendar.DAY_OF_MONTH,11);
        System.out.println(calendar.get(Calendar.DAY_OF_MONTH));

        //add()
        calendar.add(Calendar.DAY_OF_MONTH,5);
        System.out.println(calendar.get(Calendar.DAY_OF_MONTH));

        //getTime() 日历类 --> Date
        Date date = calendar.getTime();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        System.out.println(sdf.format(date));

        //setTime() Date--> 日历类
        Date date1 = new Date();
        calendar.setTime(date1);
        System.out.println(sdf.format(date1));

    }

}
