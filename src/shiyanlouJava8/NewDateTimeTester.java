package shiyanlouJava8;

import java.time.*;
import java.time.temporal.ChronoUnit;

/**
 * @author qinc
 * @version V1.0
 * @Description: java8 提供的新的关于时间的api
 * @Date 2018/8/22 11:18
 */
public class NewDateTimeTester {

    public static void  main(String args[]){

        //1.获取当前的日期和时间
        LocalDateTime currentTime= LocalDateTime.now();
        System.out.println("当前时间："+currentTime);
        //2.输出当前时间的本地值（本时区）
        LocalDate date1=currentTime.toLocalDate();
        System.out.println(date1);

        int year=currentTime.getYear();
        Month month = currentTime.getMonth();
        int day = currentTime.getDayOfMonth();
        int seconds = currentTime.getSecond();
        // 由当前时间对象获得各个字段，输出结果
        System.out.println("year:"+year+"month: " + month +"day: " + day +"seconds: " + seconds);
        //由当前时间附带月份和年再输出 2012年8月10号
        LocalDateTime date2 = currentTime.withDayOfMonth(10).withYear(2012);
        System.out.println("date 2: " + date2);
        //输出2016年圣诞节的日期
        LocalDate date3 = LocalDate.of(2016, Month.DECEMBER, 25);
        System.out.println("date 3: " + date3);

        // 输出新闻联播的开始时间
        LocalTime date4 = LocalTime.of(19, 00);
        System.out.println("date 4: " + date4);

        // 转化为字符串，再输出
        LocalTime date5 = LocalTime.parse("20:15:30");
        System.out.println("date 5: " + date5);


        // 获得当前的日期并输出
        LocalDate today = LocalDate.now();
        System.out.println("Current date: " + today);

        // 在当前日期的基础上增加两周时间再输出
        LocalDate nextWeek = today.plus(2, ChronoUnit.WEEKS);
        System.out.println("two weeks after now: " + nextWeek);

        // 在当前日期的基础上增加6个月的时间再输出
        LocalDate nextMonth = today.plus(6, ChronoUnit.MONTHS);
        System.out.println("6 months after now: " + nextMonth);

        // 在当前日期的基础上增加5年的时间再输出
        LocalDate nextYear = today.plus(5, ChronoUnit.YEARS);
        System.out.println("5 years after now: " + nextYear);

        // 在当前日期的基础上增加20年的时间再输出（一个Decade为10年）
        LocalDate nextDecade = today.plus(2, ChronoUnit.DECADES);
        System.out.println("20 years after now: " + nextDecade);


        // 获得当前的日期
        LocalDate date6 = LocalDate.now();
        System.out.println("Current date: " + date6);

        // 在当前日期的基础上增加一个月时间
        LocalDate date7 = date1.plus(1, ChronoUnit.MONTHS);
        System.out.println("Next month: " + date7);

        // 用between方法计算两个日期直接的间隔（称之为Period）
        Period period = Period.between(date6, date7);
        System.out.println(period.getMonths());


        LocalTime time1 = LocalTime.now();
        Duration fiveHours = Duration.ofHours(5);

        LocalTime time2 = time1.plus(fiveHours);

        // 对应的，用between方法显示两个时间直接的间隔（称之为Duration）
        Duration duration = Duration.between(time1, time2);
        System.out.println("Duration: " + duration);
    }
}
