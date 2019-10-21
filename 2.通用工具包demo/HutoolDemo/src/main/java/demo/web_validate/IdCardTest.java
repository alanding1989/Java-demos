package demo.web_validate;

import cn.hutool.core.util.IdcardUtil;
import demo.TestUtil.AbstractTestBefore;
import demo.TestUtil.Comment;
import org.junit.Test;

public class IdCardTest extends AbstractTestBefore {

    @Test
    @Comment("身份证校验器")
    public void test1() {
        String id15 = "510108871125243";
        p3("15位身份证号码", id15);
        p3("判断是否有效", IdcardUtil.isValidCard(id15));
        p3("转换为18位身份证号码", IdcardUtil.convert15To18(id15));

        p3("获取生日", IdcardUtil.getBirthByIdCard(id15));
        p3("获取年龄", IdcardUtil.getAgeByIdCard(id15));
        p3("获取出生年", IdcardUtil.getYearByIdCard(id15));
        p3("获取出生月", IdcardUtil.getMonthByIdCard(id15));
        p3("获取出生天", IdcardUtil.getDayByIdCard(id15));
        p3("获取性别", IdcardUtil.getGenderByIdCard(id15));
        p3("获取省份", IdcardUtil.getProvinceByIdCard(id15));
    }
}
