package demo.common_utils;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.NumberUtil;
import demo.TestUtil.AbstractTestBefore;
import demo.TestUtil.Comment;
import org.junit.Test;


public class NumberTest extends AbstractTestBefore {

    @Test
    @Comment("精确计算")
    public void test1() {
        double result1 = (1.2 - 0.4);
        p3("浮点数计算 1.2 - 0.4 无法得到精确结果", result1);
        double result2 = NumberUtil.sub(1.2, 0.4);
        p3("浮点数计算 NumberUtil.sub(1.2,0.4) 就能得到精确结果", result2);
    }


    @Test
    @Comment("四舍五入")
    public void test2() {
        double a = 100.123;
        double b = 100.125;

        double result1 = NumberUtil.round(a, 2).doubleValue();
        double result2 = NumberUtil.round(b, 2).doubleValue();

        p1("浮点数", a, "四舍五入之后", result1);
        p1("浮点数", b, "四舍五入之后", result2);
    }


    @Test
    @Comment("数字格式化")
    public void test3() {

//		0 -> 取一位整数
//		0.00 -> 取一位整数和两位小数
//		00.000 -> 取两位整数和三位小数
//		# -> 取所有整数部分
//		#.##% -> 以百分比方式计数，并取两位小数
//		#.#####E0 -> 显示为科学计数法，并取五位小数
//		,### -> 每三位以逗号进行分隔，例如：299,792,458
//		光速大小为每秒,###米 -> 将格式嵌入文本

        p3("对π进行格式化，π的值是", Math.PI);
        double pi = Math.PI;
        String format = null;
        String str = null;

        format = "0";
        str = NumberUtil.decimalFormat(format, pi);
        p2("格式", format, "格式化后得到", str);

        format = "0.00";
        str = NumberUtil.decimalFormat(format, pi);
        p2("格式", format, "格式化后得到", str);

        format = "00.000";
        str = NumberUtil.decimalFormat(format, pi);
        p2("格式", format, "格式化后得到", str);

        format = "#";
        str = NumberUtil.decimalFormat(format, pi);
        p2("格式", format, "格式化后得到", str);

        format = "#.##";
        str = NumberUtil.decimalFormat(format, pi);
        p2("格式", format, "格式化后得到", str);

        format = "#.##%";
        str = NumberUtil.decimalFormat(format, pi);
        p2("格式", format, "格式化后得到", str);

        format = "#.####E0";
        str = NumberUtil.decimalFormat(format, pi);
        p2("格式", format, "格式化后得到", str);

        format = ",###";
        str = NumberUtil.decimalFormat(format, pi * 10000);
        p2("格式", format, "x1000 再格式化后得到", str);

        format = ",####";
        str = NumberUtil.decimalFormat(format, pi * 10000);
        p2("格式", format, "x1000 再格式化后得到", str);

        format = "π的大小是#.##########，请课后记忆";
        str = NumberUtil.decimalFormat(format, pi);
        p2("格式", format, "格式化后得到", str);
    }


    @Test
    @Comment("数字判断")
    public void test4() {
        String s1 = "3.1415926";
        int n = 11;
        p2("字符串", s1, "是否数字", NumberUtil.isNumber(s1));
        p2("字符串", s1, "是否整数(这个有问题)", NumberUtil.isInteger(s1));
        p2("字符串", s1, "是否浮点数", NumberUtil.isDouble(s1));
        p2("整数", n, "是否质数", NumberUtil.isPrimes(n));
    }


    @Test
    @Comment("随机数")
    public void test5() {
        int random[] = NumberUtil.generateRandomNumber(1, 1000, 10);

        p3("最小是1，最大是1000，总长度是10的不重复随机数组", Convert.toStr(random));
    }


    @Test
    @Comment("整数列表")
    public void test6() {
        int numbers[] = NumberUtil.range(0, 100, 9);
        p3("最小是0，最大是100，步长是9的数组", Convert.toStr(numbers));
    }


    @Test
    @Comment("其他相关")
    public void test7() {
        p3("计算3的阶乘", NumberUtil.factorial(3));
        p3("计算9的平方根", NumberUtil.sqrt(9));
        p3("计算9和6的最大公约数", NumberUtil.divisor(9, 6));
        p3("计算9和6的最小公倍数", NumberUtil.multiple(9, 6));
        p3("获得数字9对应的二进制字符串", NumberUtil.getBinaryStr(9));
        p3("获取123456789对应金额", NumberUtil.decimalFormatMoney(123456789));
    }
}
