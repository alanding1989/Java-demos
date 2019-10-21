package demo.file_io;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.extra.mail.MailAccount;
import cn.hutool.extra.mail.MailUtil;
import demo.TestUtil.AbstractTestBefore;
import demo.TestUtil.Comment;
import org.junit.Before;
import org.junit.Test;

public class MailTest extends AbstractTestBefore {

    private MailAccount account;

    @Before
    public void prepareMailAccount() {
        account = new MailAccount();
        account.setHost("smtp.163.com");
        account.setPort(25);
        account.setAuth(true);
        account.setFrom("test@163.com"); //假邮箱，请自己申请真实邮箱
        account.setUser("test@163.com"); //假邮箱，请自己申请真实邮箱
        account.setPass("testpassword"); //假密码，请自己申请真实邮箱
    }


    @Test
    @Comment("发送普通文本")
    public void test1() {
        //因为账号密码不对，所以不能正确发送
        //MailUtil.send(account,"test@163.com", "hutool 测试邮件" + DateUtil.now(), "测试内容", false);
    }


    @Test
    @Comment("发送html邮件")
    public void test2() {
        //因为账号密码不对，所以不能正确发送
        //MailUtil.send(account,"test@163.com", "hutool 测试邮件" + DateUtil.now(), "<p>测试内容</p>", true);
    }


    @Test
    @Comment("发送带附件的邮件")
    public void test3() {
        //因为账号密码不对，所以不能正确发送
        MailUtil.send(account, "test@163.com", "hutool 测试邮件" + DateUtil.now(),
                      "<p>测试内容</p>", true, FileUtil.file("d:/test.txt"));
    }
}
