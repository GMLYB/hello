package day6_19;

import org.junit.Test;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.time.temporal.TemporalAccessor;

/**
 *
 * LocalDate、LocalTime、LocalDateTime类的使用
 * 说明：
 *      1.LocalDateTime相较于 LocalDate、LocalTime，使用频率更高
 *      2.类似于Calendar
 *
 */

public class JDK8DateTimeTest {

    @Test
    public void test1(){

        //now()：获取当前的日期、时间、时间+日期
        LocalDate localDate = LocalDate.now();
        LocalTime localTime = LocalTime.now();
        LocalDateTime localDateTime = LocalDateTime.now();
        System.out.println(localDate);
        System.out.println(localTime);
        System.out.println(localDateTime);

        //of()：设置指定的年、月、日、时、分、秒。没有偏移量
        LocalDateTime localDateTime1 = LocalDateTime.of(2020, 6, 19, 14, 20, 36);
        System.out.println(localDateTime1);

        //getXxx()
        System.out.println(localDateTime.getDayOfMonth());
        System.out.println(localDateTime.getDayOfWeek());
        System.out.println(localDateTime.getMonth());
        System.out.println(localDateTime.getMonthValue());
        System.out.println(localDateTime.getMinute());

        //提现不可变性
        LocalDateTime localDateTime2 = localDateTime.withDayOfMonth(1);
        System.out.println(localDateTime);
        System.out.println(localDateTime2);

        LocalDateTime localDateTime3 = localDateTime.withHour(5);
        System.out.println(localDateTime);
        System.out.println(localDateTime3);

        LocalDateTime localDateTime4 = localDateTime.plusMonths(5);
        System.out.println(localDateTime);
        System.out.println(localDateTime4);

        LocalDateTime localDateTime5 = localDateTime.minusDays(5);
        System.out.println(localDateTime);
        System.out.println(localDateTime5);

    }

    @Test
    public void test2(){

        //now():本初子午线日期
        Instant instant = Instant.now();
        System.out.println(instant);
        //添加时间的偏移量
        OffsetDateTime offsetDateTime = instant.atOffset(ZoneOffset.ofHours(8));
        System.out.println(offsetDateTime);
        //获取自1970年1月1日0时0分0秒（UTC）开始的毫秒数
        long milli = instant.toEpochMilli();
        System.out.println(milli);
        //ofEpochMilli():通过给定的毫秒数，获取Instant实例 --> Date(long millis)
        Instant instant1 = Instant.ofEpochMilli(1592545085998L);
        System.out.println(instant1);
    }
    /*
        DateTimeFormatter：格式化或解析日期、时间
        类似于SimpleDateFormat
     */
    @Test
    public void test3(){
        //方式一：预定义的标准格式。如：ISO_LOCAL_DATE_TIME;ISO_LOCAL_DATE;ISO_LOCAL_TIME
        DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
        //格式化：日期 --> 字符串
        LocalDateTime localDateTime = LocalDateTime.now();
        String str1 = formatter.format(localDateTime);
        System.out.println(localDateTime);
        System.out.println(str1);
        //解析：字符串--> 日期
        TemporalAccessor temporalAccessor = formatter.parse("2020-06-19T13:58:38.44");
        System.out.println(temporalAccessor);

        //方式二：本地化相关的格式。如：ofLocalizedDateTime(FormatStyle.LONG)
        //FormatStyle.LONG / FormatStyle.MEDIUM / FormatStyle.SHORT :适用于LocalDateTime
        DateTimeFormatter formatter1 = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.SHORT);
        //格式化
        String str2 = formatter1.format(localDateTime);
        System.out.println(str2);//20-6-19 下午4:56

        //本地化相关的格式。如：ofLocalizedDate()
        //FormatStyle.FULL / FormatStyle.LONG / FormatStyle.MEDIUM / FormatStyle.SHORT : 适用于LocalDate
        DateTimeFormatter formatter2 = DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL);
        //格式化
        String str3 = formatter2.format(LocalDate.now());
        System.out.println(str3);//2020年6月19日 星期五

        //方式三：自定义格式。如：ofPattern("yyyy-MM-dd hh:mm:ss E")
        DateTimeFormatter formatter3 = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss");
        //格式化
        String str4 = formatter3.format(LocalDateTime.now());
        System.out.println(str4);//2020-06-19 05:02:34

        //解析
        TemporalAccessor temporalAccessor1 = formatter3.parse("2020-06-19 05:02:34");
        System.out.println(temporalAccessor1);

    }

}
