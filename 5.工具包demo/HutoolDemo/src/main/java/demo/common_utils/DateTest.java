package demo.common_utils;

import cn.hutool.core.date.BetweenFormater.Level;
import cn.hutool.core.date.DateField;
import cn.hutool.core.date.DateUnit;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.date.TimeInterval;
import demo.TestUtil.AbstractTestBefore;
import demo.TestUtil.Comment;
import java.util.Date;
import org.junit.Test;

public class DateTest extends AbstractTestBefore {
    @Test
    @Comment("字符串转日期")
    public void test0() {
        printDefaultFormat();
        Date d;
        String str3 = "12:12:12";
        d = DateUtil.parse(str3);
        p1("字符串", str3, "日期格式", d);

        String str1 = "2012-12-12";
        d = DateUtil.parse(str1);
        p1("字符串", str1, "日期格式", d);

        String str4 = "2012-12-12 12:12";
        d = DateUtil.parse(str4);
        p1("字符串", str4, "日期格式", d);

        String str2 = "2012-12-12 12:12:12";
        d = DateUtil.parse(str2);
        p1("字符串", str2, "日期格式", d);
    }


    @Test
    @Comment("日期转字符串")
    public void test1() {
        Date d = new Date();

        //结果 2017/03/01
        String format = DateUtil.format(d, "yyyy/MM/dd");

        //常用格式的格式化，结果：2017-03-01
        String formatDate = DateUtil.formatDate(d);

        //结果：2017-03-01 00:00:00
        String formatDateTime = DateUtil.formatDateTime(d);

        //结果：00:00:00
        String formatTime = DateUtil.formatTime(d);

        p1("日期格式", d, "自定义格式的字符串", format);
        p1("日期格式", d, "只是日期格式", formatDate);
        p1("日期格式", d, "日期和时间格式", formatDateTime);
        p1("日期格式", d, "只是时间格式", formatTime);
    }


    @Test
    @Comment("获取部分信息")
    public void test2() {
        Date d = new Date();
        //获得年的部分
        int year = DateUtil.year(d);
        //获得月份，从0开始计数
        int month = DateUtil.month(d);
        //获得月份枚举
        Enum months = DateUtil.monthEnum(d);

        p2("当前日期", DateUtil.formatDateTime(d), "年份", year);
        p2("当前日期", DateUtil.formatDateTime(d), "月份", month);
        p2("当前日期", DateUtil.formatDateTime(d), "月份枚举信息", months);
    }


    @Test
    @Comment("开始和结束时间")
    public void test3() {
        Date date = new Date();

        //一天的开始，结果：2017-03-01 00:00:00
        Date beginOfDay = DateUtil.beginOfDay(date);

        //一天的结束，结果：2017-03-01 23:59:59
        Date endOfDay = DateUtil.endOfDay(date);
        p2("当前日期", DateUtil.formatDateTime(date), "开始时间", beginOfDay);
        p2("当前日期", DateUtil.formatDateTime(date), "结束时间", endOfDay);
        c();
    }


    @Test
    @Comment("日期时间偏移")
    public void test4() {
        Date date = new Date();

        Date d1 = DateUtil.offset(date, DateField.DAY_OF_MONTH, 2);

        Date d2 = DateUtil.offsetDay(date, 3);

        Date d3 = DateUtil.offsetHour(date, -3);

        p2("当前日期", DateUtil.formatDateTime(date), "两天之后的日期", d1);
        p2("当前日期", DateUtil.formatDateTime(date), "三天之后的日期", d2);
        p2("当前日期", DateUtil.formatDateTime(date), "三小时之前的日期", d3);
    }


    @Test
    @Comment("偏移简化用法")
    public void test5() {
        Date date = new Date();
        Date d1 = DateUtil.yesterday();
        Date d2 = DateUtil.tomorrow();
        Date d3 = DateUtil.lastWeek();
        Date d4 = DateUtil.nextWeek();
        Date d5 = DateUtil.lastMonth();
        Date d6 = DateUtil.nextMonth();
        p2("当前日期", DateUtil.formatDateTime(date), "昨天", d1);
        p2("当前日期", DateUtil.formatDateTime(date), "明天", d2);
        p2("当前日期", DateUtil.formatDateTime(date), "上周", d3);
        p2("当前日期", DateUtil.formatDateTime(date), "下周", d4);
        p2("当前日期", DateUtil.formatDateTime(date), "上个月", d5);
        p2("当前日期", DateUtil.formatDateTime(date), "下个月", d6);
    }


    @Test
    @Comment("日期时间差")
    public void test6() {
        Date date1 = DateUtil.parse("2012-12-12 12:12:12");
        Date date2 = DateUtil.parse("2013-13-13 13:13:13");

        long b1 = DateUtil.between(date1, date2, DateUnit.MS);
        long b2 = DateUtil.between(date1, date2, DateUnit.SECOND);
        long b3 = DateUtil.between(date1, date2, DateUnit.MINUTE);
        long b4 = DateUtil.between(date1, date2, DateUnit.HOUR);
        long b5 = DateUtil.between(date1, date2, DateUnit.DAY);
        long b6 = DateUtil.between(date1, date2, DateUnit.WEEK);

        p2("当前如下两个日期", date1 + " " + date2, "相差毫秒", b1);
        p2("当前如下两个日期", date1 + " " + date2, "相差秒", b2);
        p2("当前如下两个日期", date1 + " " + date2, "相差分", b3);
        p2("当前如下两个日期", date1 + " " + date2, "相差小时", b4);
        p2("当前如下两个日期", date1 + " " + date2, "相差天", b5);
        p2("当前如下两个日期", date1 + " " + date2, "相差星期", b6);
    }


    @Test
    @Comment("格式化时间差")
    public void test7() {
        long between = System.currentTimeMillis();

        String s0 = DateUtil.formatBetween(between, Level.MILLSECOND);
        String s1 = DateUtil.formatBetween(between, Level.SECOND);
        String s2 = DateUtil.formatBetween(between, Level.MINUTE);
        String s3 = DateUtil.formatBetween(between, Level.HOUR);
        String s4 = DateUtil.formatBetween(between, Level.DAY);
        p2("毫秒数", between, "对应时间，精度到毫秒", s0);
        p2("毫秒数", between, "对应时间，精度到秒", s1);
        p2("毫秒数", between, "对应时间，精度到秒分钟", s2);
        p2("毫秒数", between, "对应时间，精度到秒小时", s3);
        p2("毫秒数", between, "对应时间，精度到秒天", s4);
    }


    @Test
    @Comment("性能统计")
    public void test8() {
        int loopcount = 100;
        TimeInterval timer = DateUtil.timer();
        forloop(loopcount);
        long interval1 = timer.interval();
        forloop(loopcount);
        long interval2 = timer.intervalRestart();
        forloop(loopcount);
        long interval3 = timer.interval();

        p3("性能统计，总共花费了 (毫秒数)", interval1);
        p3("性能统计，总共花费了 (毫秒数),并重置", interval2);
        p3("性能统计，总共花费了 (毫秒数)", interval3);
    }


    @Test
    @Comment("其他")
    public void test9() {
        String birthDay = "1949-10-01";
        int age = DateUtil.ageOfNow(birthDay);

        int year = 2012;
        boolean isLeap = DateUtil.isLeapYear(year);

        String now = DateUtil.now();
        String today = DateUtil.today();

        p2("生日", birthDay, "年龄", age);
        p2("年份", year, "是否闰年", isLeap);

        p3("现在", now);
        p3("今天", today);

    }


    private void forloop(int total) {
        for (int i = 0; i < total; i++) {
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }


    private void printDefaultFormat() {
        System.out.println("DateUtil默认会对如下格式进行识别：");
        System.out.println();

        System.out.println("\tyyyy-MM-dd HH:mm:ss");
        System.out.println("\tyyyy/MM/dd HH:mm:ss");
        System.out.println("\tyyyy.MM.dd HH:mm:ss");
        System.out.println("\tyyyy年MM月dd日 HH时mm分ss秒");
        System.out.println("\tyyyy-MM-dd");
        System.out.println("\tyyyy/MM/dd");
        System.out.println("\tyyyy.MM.dd");
        System.out.println("\tHH:mm:ss");
        System.out.println("\tHH时mm分ss秒");
        System.out.println("\tyyyy-MM-dd HH:mm");
        System.out.println("\tyyyy-MM-dd HH:mm:ss.SSS");
        System.out.println("\tyyyyMMddHHmmss");
        System.out.println("\tyyyyMMddHHmmssSSS");
        System.out.println("\tyyyyMMdd");
        System.out.println();
    }
}