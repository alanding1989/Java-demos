package demo.web_validate;

import cn.hutool.core.lang.Validator;
import demo.TestUtil.AbstractTestBefore;
import demo.TestUtil.Comment;
import org.junit.Test;

public class ValidatorTest extends AbstractTestBefore {

    @Test
    @Comment("校验器")
    public void test1() {
        String email = "123@qq.com";
        boolean valid = Validator.isEmail(email);

        p2("邮件地址", email, " 是否合法 ", valid);
    }
}
