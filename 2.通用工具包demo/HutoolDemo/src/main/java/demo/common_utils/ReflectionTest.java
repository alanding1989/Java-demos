package demo.common_utils;

import cn.hutool.core.util.ReflectUtil;
import cn.hutool.core.util.TypeUtil;
import demo.TestUtil.AbstractTestBefore;
import demo.TestUtil.Comment;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.junit.Test;

public class ReflectionTest extends AbstractTestBefore {

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    static class Hero {
        String name;
        int hp;
    }

    @Test
    @Comment("设置属性")
    public void test1() {
        Hero h = new Hero();
        ReflectUtil.setFieldValue(h, "name", "盖伦");
        p3("对象通过反射设置name属性后的值", h.getName());
    }


    @Test
    @Comment("调用方法")
    public void test2() {
        TypeUtil ty;
        Hero h = new Hero();
        ReflectUtil.invoke(h, "setName", "盖伦");
        p3("对象通过反射设置调用setName属性后的值", h.getName());
    }
}
