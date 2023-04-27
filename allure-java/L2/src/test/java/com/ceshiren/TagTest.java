package com.ceshiren;

import org.junit.jupiter.api.Assumptions;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.DisabledIf;

import static org.junit.jupiter.api.Assertions.assertEquals;

/*Allure2 报告中添加用例支持 tags 标签*/

@Tag("类标签")
public class TagTest {
    @Tag("单个标签")
    @Tags({
            @Tag("复合标签1"),
            @Tag("复合标签2")
    })
    @Test
    public void test1() {
        System.out.println("测试报告添加用例标签");
    }

    @Test
    @DisabledIf("disable")
    //当disable返回值true时，用例跳过不执行
    public void test2() {
        assertEquals(3, 3);
    }

    boolean disable() {
        return 5 + 5 != 11;
    }

    @Test
    //assumeFalse()实现预期失败用例
    public void test3() {
        //good返回值true时
        Assumptions.assumeFalse(this::good, "预期失败用例");
    }

    boolean good() {
        return 5 + 5 != 11;
    }
}
