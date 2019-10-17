package demo.en_decode;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import demo.TestUtil.AbstractTestBefore;
import demo.TestUtil.Comment;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.junit.Test;

public class JSONTest extends AbstractTestBefore {

    @Test
    @Comment("java bean 和 json 互相转换")
    public void test0() {
        Hero hero = new Hero("garren", 345);
        String str = JSONUtil.toJsonPrettyStr(hero);
        System.out.println(str);
        JSONObject o = JSONUtil.parseObj(str);
        System.out.println(o.getClass());
        Hero hero2 = JSONUtil.toBean(o, Hero.class);
        System.out.println(hero2);
    }


    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    static class Hero {
        String name;
        int hp;
    }
}
