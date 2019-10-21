package demo.en_decode;

import cn.hutool.core.util.URLUtil;
import demo.TestUtil.AbstractTestBefore;
import demo.TestUtil.Comment;
import org.junit.Test;


public class TestURL extends AbstractTestBefore {

    @Test
    @Comment("URLUtil使用举例")
    public void test1() {
        String url1 = "how2j.cn";
        String url2 = "http://how2j.cn/k/tmall_springboot/tmall_springboot-1799/1799.html";
        String urla = URLUtil.normalize(url1);
        String urlb = URLUtil.encode(url2);
        String urlc = URLUtil.decode(urlb);
        String urld = URLUtil.getPath(url2);

        p1("原数据", url1, "格式化之后", urla);
        p1("原数据", url2, "编码数据", urlb);
        p1("编码数据", urlb, "解码数据", urlc);
        p1("原数据", url2, "对应路径路径", urld);
    }
}
