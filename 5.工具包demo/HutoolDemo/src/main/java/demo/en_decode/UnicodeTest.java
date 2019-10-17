package demo.en_decode;

import cn.hutool.core.text.UnicodeUtil;
import demo.TestUtil.AbstractTestBefore;
import demo.TestUtil.Comment;
import org.junit.Test;


public class UnicodeTest extends AbstractTestBefore {

    @Test
    @Comment("unicode转换")
    public void test1() {
        String charset = "utf-8";
        String content = "how2j.cn - java教程";

        p3("原字符串", content);

        String unicode = UnicodeUtil.toUnicode(content);
        content = UnicodeUtil.toString(unicode);

        p3("获取unicode", unicode);
        p3("转会原字符串", content);
    }
}
