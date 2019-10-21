package demo.system_runtime;

import cn.hutool.core.util.ClassUtil;
import demo.TestUtil.AbstractTestBefore;
import demo.TestUtil.Comment;
import java.util.Set;
import org.junit.Test;

public class ClassTest extends AbstractTestBefore {

    @Test
    @Comment("ClassUtil使用举例")
    public void test1() {

        String packageName = "cn.how2j";
        Set<Class<?>> classes = ClassUtil.scanPackage("cn.how2j");
        p2("包名", packageName, "当前包下所有的类", classes);
    }
}
