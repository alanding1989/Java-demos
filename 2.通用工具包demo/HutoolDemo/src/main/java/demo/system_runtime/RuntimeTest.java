package demo.system_runtime;

import cn.hutool.core.util.RuntimeUtil;
import demo.TestUtil.AbstractTestBefore;
import demo.TestUtil.Comment;
import org.junit.Test;


public class RuntimeTest extends AbstractTestBefore {

    @Test
    @Comment("RuntimeUtil使用举例")
    public void test1() {
        String s = RuntimeUtil.execForStr("netstat -n");

        System.out.println(s);
    }
}
