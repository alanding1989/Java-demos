package demo.file_io;

import cn.hutool.core.util.ZipUtil;
import demo.TestUtil.AbstractTestBefore;
import demo.TestUtil.Comment;
import org.junit.Test;

public class ZipTest extends AbstractTestBefore {

    @Test
    @Comment("压缩字符串")
    public void test1() {
        String str = "hello!!!!!!!!!!!!!!!";
        byte[] bs = ZipUtil.zlib(str, "utf-8", 4);
        String str2 = ZipUtil.unZlib(bs, "utf-8");

        p3("源字符串", str);
        p3("长度是", str.length());
        p3("zlib压缩后，长度是", bs.length);
        p3("unzip后得到", str2);
    }


    @Test
    @Comment("压缩文件")
    public void test2() {
        System.out.println("\t因为是压缩文件，不好演示,主要就是使用 zip和unzip方法，很好用，都挨个试试就知道了");
    }
}
