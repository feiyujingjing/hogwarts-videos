package com.ceshiren;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
/*Allure2 报告生成*/
@DisplayName("首个报告用例")
public class FirstTest {

    @Test
    @DisplayName("第一个测试用例")
    public void test1() {
        int result = 3 + 2;
        assertEquals(5, result, "计算错误");
    }



}
