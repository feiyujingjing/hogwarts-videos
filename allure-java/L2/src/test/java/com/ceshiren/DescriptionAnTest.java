package com.ceshiren;

/*使用描述注解的测试用例*/

import io.qameta.allure.Allure;
import io.qameta.allure.Description;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("使用描述注解的测试用例")
public class DescriptionAnTest {

    @Test
    @DisplayName("加法测试用例")
    @Description("这是一个加法的测试用例描述信息\n回车换行")
    public void testDescAn() {
        int result = 3 + 2;
        assertEquals(5, result, "计算错误");
    }

    @Test
    @DisplayName("3+2")
    @Description("这是一个加法的测试用例，可能会更改描述信息")
    public void testDescAn2() {
        int result = 3 + 2;
        assertEquals(5, result, "计算错误");

        //动态更改描述信息- 这次用html
        Allure.getLifecycle().updateTestCase(testResult ->
                testResult.setDescriptionHtml("<h3>动态更改</h3><br><a href=\"https://www.baidu.com\">动态更改</a>"));

    }
}
