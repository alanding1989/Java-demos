package demo.common_utils;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.comparator.PinyinComparator;
import cn.hutool.core.comparator.PropertyComparator;
import cn.hutool.core.util.RandomUtil;
import demo.TestUtil.AbstractTestBefore;
import demo.TestUtil.Comment;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.junit.Test;


public class ComparatorTest extends AbstractTestBefore {
    @Test
    @Comment("属性比较器")
    public void test1() {
        List<Hero> heros = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            heros.add(new Hero("hero " + i, RandomUtil.randomInt(100)));
        }
        System.out.println("未排序的集合:");
        System.out.println(CollectionUtil.join(heros, "\r\n"));

        heros.sort(new PropertyComparator<>("hp"));
        System.out.println("根据属性 hp 排序之后：");
        System.out.println(CollectionUtil.join(heros, "\r\n"));
    }


    @Test
    @Comment("拼音比较器")
    public void test2() {
        List<String> names = new ArrayList<>();
        names.add("令狐冲");
        names.add("陈家洛");
        names.add("石破天");
        names.add("胡一刀");

        p3("未排序的集合", CollectionUtil.join(names, " , "));

        names.sort(new PinyinComparator());
        p3("根据拼音排序的集合", CollectionUtil.join(names, " , "));
    }


    @Data
    @AllArgsConstructor
    static class Hero {
        String name;
        int hp;
    }
}
