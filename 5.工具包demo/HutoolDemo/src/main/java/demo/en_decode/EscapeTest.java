package demo.en_decode;

import cn.hutool.core.util.EscapeUtil;
import demo.TestUtil.AbstractTestBefore;
import demo.TestUtil.Comment;
import org.junit.Test;

public class EscapeTest extends AbstractTestBefore {

    @Test
    @Comment("转义与反转义")
    public void test2() {
    	String s1 = "<script>location.href='http://how2j.cn';</script>";
    	String s2 = EscapeUtil.escapeHtml4(s1);
    	String s3 = EscapeUtil.unescapeHtml4(s2);

    	p2("原数据",s1, "转义后",s2);
    	p2("转义后",s2, "原数据",s3);
    }
}