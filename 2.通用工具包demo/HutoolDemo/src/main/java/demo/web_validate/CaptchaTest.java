package demo.web_validate;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.CircleCaptcha;
import cn.hutool.captcha.LineCaptcha;
import cn.hutool.captcha.ShearCaptcha;
import cn.hutool.core.io.IoUtil;
import demo.TestUtil.AbstractTestBefore;
import demo.TestUtil.Comment;
import java.io.OutputStream;
import org.junit.Test;


public class CaptchaTest extends AbstractTestBefore {

    @Test
    @Comment("创建 线段干扰的验证码")
    public void test1() {
        int width = 200;
        int height = 100;
        LineCaptcha captcha = CaptchaUtil.createLineCaptcha(width, height);
        p3("当前的验证码是", captcha.getCode());
        String path = "d:/captcha1.png";
        captcha.write(path);
    }

    @Test
    @Comment("创建 圆圈干扰的验证码")
    public void test2() {
        int width = 200;
        int height = 100;
        int codeCount = 5;
        int circleCount = 40;
        CircleCaptcha captcha = CaptchaUtil.createCircleCaptcha(width, height, codeCount, circleCount);
        p3("当前的验证码是", captcha.getCode());
        String path = "d:/captcha2.png";
        captcha.write(path);
    }

    @Test
    @Comment("创建 扭曲线干扰的验证码")
    public void test3() {
        int width = 200;
        int height = 100;
        int codeCount = 5;
        int thickness = 2;
        ShearCaptcha captcha = CaptchaUtil.createShearCaptcha(width, height, codeCount, thickness);
        p3("当前的验证码是", captcha.getCode());
        String path = "d:/captcha3.png";
        captcha.write(path);
    }

    @Test
    @Comment("web 页面输出")
    public void test4() {
        //junit 毕竟不是servlet 容器，拿不到 response对象， 这里是伪代码
        int width = 200;
        int height = 100;
        LineCaptcha captcha = CaptchaUtil.createLineCaptcha(width, height);
        OutputStream out = null;
//		out = HttpServletResponse.getOutputStream();
//		captcha.write(out);
        IoUtil.close(out);
    }
}
