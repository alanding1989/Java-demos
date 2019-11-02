package demo.TestUtil;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.ReflectUtil;
import cn.hutool.core.util.StrUtil;
import java.lang.reflect.Method;
import java.util.Arrays;
import org.junit.Before;
import org.junit.Test;

/**
 *  Author      :   AlanDing
 *  Time        :   2019/10/15 下午7:47
 *  File        :   AbstractTestBefore.java
 *  Description :
 */

public abstract class AbstractTestBefore {

    private static final String FORMAT_1 = "format1";
    private static final String FORMAT_2 = "format2";
    private static final String FORMAT_3 = "format3";
    private static final String FORMAT_4 = "format4";
    private static final String FORMAT_5 = "format5";

    private static String preComment = null;

    private Class<?> clazz;


    @Test
    @Before
    public void before() {
        clazz = this.getClass();
    }


    protected void c() {
        System.out.format("\t备注： %s%n", "这个在查询数据库时，根据日期查一个范围内的数据就很有用");
    }


    protected void p1(String type1, Object value1, String type2, Object value2) {
        print(clazz, type1, value1, type2, value2, FORMAT_1);
    }


    protected void p2(String type1, Object value1, String type2, Object value2) {
        print(clazz, type1, value1, type2, value2, FORMAT_2);
    }


    protected void p3(String type1, Object value1) {
        print(clazz, type1, value1, "", "", FORMAT_3);
    }


    protected void p3(String type1, Object value1, String type2, Object value2) {
        print(clazz, type1, value1, "", "", FORMAT_3);
    }


    protected void p4(Object value) {
        print(clazz, null, value, "", "", FORMAT_4);
    }


    protected void p4(String type1, Object value1, String type2, Object value2) {
        print(clazz, null, value1, "", "", FORMAT_4);
    }


    protected void p5(String type1, Object value1) {
        print(clazz, type1, value1, "", "", "format5");
    }


    protected void p5(String type1, Object value1, String type2, Object value2) {
        print(clazz, type1, value1, "", "", FORMAT_5);
    }


    private static void
    print(Class<?> clazz, String type1, Object value1, String type2, Object value2, String format) {
        try {
            throw new Exception();
        } catch (Exception e) {
            String methodName = getTestMethodName(e.getStackTrace());
            Method m = ReflectUtil.getMethod(clazz, methodName);
            Comment annotation = m.getAnnotation(Comment.class);
            if (annotation != null) {
                String comment = annotation.value();
                if (!comment.equals(preComment)) {
                    System.out.format("\n%s 例子: \n\n", comment);
                }
            }
        }

        int padLength = 12;
        type1 = StrUtil.padAfter(type1, padLength, Convert.toSBC(" ").charAt(0));
        type2 = StrUtil.padAfter(type2, padLength, Convert.toSBC(" ").charAt(0));

        chooseFormat(type1, value1, type2, value2, format);
    }


    private static void
    chooseFormat(String type1, Object value1, String type2, Object value2, String format) {
        switch (format) {
            case FORMAT_1:
                System.out.printf("\t%s的:\t\"%s\" \n\t被转换为----->\n\t%s的 :\t\"%s\" \n\n",
                                  type1, value1, type2, value2);
                break;
            case FORMAT_2:
                System.out.printf("\t基于 %s:\t\"%s\" \n\t获取 %s:\t\"%s\"\n\n",
                                  type1, value1, type2, value2);
                break;
            case FORMAT_3:
                System.out.printf("\t%s:\t\"%s\" \n\t\n", type1, value1);
                break;
            case FORMAT_4:
                System.out.printf("\t%s%n%n", value1);
                break;
            case FORMAT_5:
                System.out.printf("---------%s-------:%n%s %n%n", type1, value1);
                break;
            default:
                break;
        }
    }


    private static String
    getTestMethodName(StackTraceElement[] stackTrace) {
        return Arrays.stream(stackTrace).map(StackTraceElement::getMethodName)
                     .filter(methodName -> methodName.startsWith("test"))
                     .findFirst().orElseThrow(NullPointerException::new);
    }
}
