package demo.common_utils;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.lang.Filter;
import cn.hutool.core.util.ArrayUtil;
import demo.TestUtil.AbstractTestBefore;
import demo.TestUtil.Comment;
import java.util.Map;
import org.junit.Test;

public class ArrayTest extends AbstractTestBefore {

    @Test
    @Comment("为空判断")
    public void test1() {
        int[] a = null;
        int[] b = new int[5];
        int[] c = new int[]{10, 11, 12};

        p1("数组", Convert.toStr(a), "是否为空", ArrayUtil.isEmpty(a));
        p1("数组", Convert.toStr(b), "是否为空", ArrayUtil.isEmpty(b));
        p1("数组", Convert.toStr(c), "是否为空", ArrayUtil.isEmpty(c));
    }


    @Test
    @Comment("调整数组大小")
    public void test2() {
        Integer[] a = new Integer[]{10, 11, 12};
        Integer[] b = ArrayUtil.resize(a, 5);

        p3("调整大小前的数组", Convert.toStr(a));
        p3("调整大小后的数组", Convert.toStr(b));
    }


    @Test
    @Comment("合并数组")
    public void test3() {
        Integer a[] = {1, 2, 3};
        Integer b[] = {10, 11, 12};
        Integer c[] = ArrayUtil.addAll(a, b);

        p2("合并前的两个数组 ", Convert.toStr(a) + " , " + Convert.toStr(b), "合并后的数组是", Convert.toStr(c));
    }


    @Test
    @Comment("克隆")
    public void test4() {
        Integer a[] = {1, 2, 3};
        Integer b[] = ArrayUtil.clone(a);

        p2("原数组", Convert.toStr(a), "克隆的数组", Convert.toStr(b));
    }


    @Test
    @Comment("生成有序数组")
    public void test5() {
        p3("生成开始是0，结束是100，步长是9的有序数组", Convert.toStr(ArrayUtil.range(0, 100, 9)));
    }


    @Test
    @Comment("过滤")
    public void test6() {
        Integer[] a = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        Integer[] b = ArrayUtil.filter(a, (Filter<Integer>) t -> 0 == t % 3);

        p2("原数组", Convert.toStr(a), "3的倍数过滤之后", Convert.toStr(b));
    }

    @Test
    @Comment("转换为map")
    public void test7() {
        Integer a[] = {1, 2, 3};
        String c[] = {"a", "b", "c"};
        Map<Integer, String> m = ArrayUtil.zip(a, c);

        p2("两个数组", Convert.toStr(a) + " , " + Convert.toStr(c), "转换为 Map ", m);
    }


    @Test
    @Comment("是否包含某元素")
    public void test8() {
        Integer a[] = {1, 2, 3};

        p1("数组", Convert.toStr(a), "是否包含元素3", ArrayUtil.contains(a, 3));

    }


    @Test
    @Comment("装箱拆箱")
    public void test9() {
        int a[] = {1, 2, 3};
        Integer b[] = ArrayUtil.wrap(a);
        int c[] = ArrayUtil.unWrap(b);

        p3("数组基本类型的装箱拆箱", "ArrayUtil.wrap | ArrayUtil.unWrap");
    }


    @Test
    @Comment("转换为字符串")
    public void testa() {
        int a[] = {1, 2, 3};

        p3("数组转换为默认字符串", ArrayUtil.toString(a));
        p3("数组转换为自定义分隔符的字符串", ArrayUtil.join(a, "-"));
    }


    @Test
    @Comment("拆分")
    public void testb() {
        byte[] a = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        byte[][] b = ArrayUtil.split(a, 2);

        p3("数组被拆成2为长度的等份", Convert.toStr(a));

        for (byte[] bs : b) {
            p3("拆分后的数组：", Convert.toStr(bs));
        }
    }
}
