package demo.common_utils;

import cn.hutool.core.util.ReUtil;
import demo.TestUtil.AbstractTestBefore;
import demo.TestUtil.Comment;
import org.junit.Test;

public class RegexTest extends AbstractTestBefore {

    @Test
    @Comment("正则表达式")
    public void test1() {
        String content = "But just as he who called you is holy, so be holy in all you do; for it is written: “Be holy, because I am holy";

        p3("字符串", content);
        String regex = "\\w{5}";
        p3(regex + " 表：", "连续5个字母或者数字");

        Object result = ReUtil.get(regex, content, 0);
        p2("正则表达式", regex, "get 返回值", result);

        result = ReUtil.contains(regex, content);
        p2("正则表达式", regex, "contain 返回值", result);

        result = ReUtil.count(regex, content);
        p2("正则表达式", regex, "count 返回值", result);

        result = ReUtil.delAll(regex, content);
        p2("正则表达式", regex, "delAll 返回值", result);

        result = ReUtil.delFirst(regex, content);
        p2("正则表达式", regex, "delFirst 返回值", result);

        result = ReUtil.delPre(regex, content);
        p2("正则表达式", regex, "delPre 返回值", result);

        result = ReUtil.findAll(regex, content, 0);
        p2("正则表达式", regex, "findAll 返回值", result);
    }
}
