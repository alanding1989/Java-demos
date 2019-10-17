package demo.en_decode;

import cn.hutool.core.util.HexUtil;
import demo.TestUtil.AbstractTestBefore;
import demo.TestUtil.Comment;
import java.awt.Color;
import org.junit.Test;

/**
 *  Author      :   AlanDing
 *  Time        :   2019/10/15 下午1:16
 *  File        :   HexTools.java
 *  Description :
 */

public class HexTest extends AbstractTestBefore {

    @Test
    @Comment("判断是否是十六进制")
    public void test1() {
        String s1 = "12";
        boolean b1 = HexUtil.isHexNumber(s1);
        String s2 = "0x12";
        boolean b2 = HexUtil.isHexNumber(s2);

        p2("字符串", s1, "是否十六进制", b1);
        p2("字符串", s2, "是否十六进制", b2);
    }


    @Test
    @Comment("字符串和十六进制互相转换")
    public void test2() {
        String s1 = "hutool.cn - java";
        String s2 = HexUtil.encodeHexStr(s1);
        String s3 = HexUtil.decodeHexStr(s2);

        p2("原数据", s1, "十六进制编码", s2);
    }


    @Test
    @Comment("颜色转换")
    public void test3() {
        Color color1 = Color.red;
        String s1 = HexUtil.encodeColor(color1);
    }
}

