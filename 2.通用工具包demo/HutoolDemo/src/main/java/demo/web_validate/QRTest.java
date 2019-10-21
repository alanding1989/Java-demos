package demo.web_validate;

import cn.hutool.core.io.FileUtil;
import cn.hutool.extra.qrcode.QrCodeUtil;
import demo.TestUtil.AbstractTestBefore;
import demo.TestUtil.Comment;
import org.junit.Test;

public class QRTest extends AbstractTestBefore {

    @Test
    @Comment("生成二维码图片和解析图片")
    public void test1() {
        String string = "http://how2j.cn";
        String path = "d:/qrcode.jpg";
        QrCodeUtil.generate(string, 300, 300, FileUtil.file(path));

        p1("字符串", string, "二维码图片", path);

        string = QrCodeUtil.decode(FileUtil.file(path));
        p1("二维码图片", path, "二维码图片", string);
    }
}
