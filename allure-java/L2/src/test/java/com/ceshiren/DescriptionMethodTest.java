package com.ceshiren;

import io.qameta.allure.Allure;
import jdk.jfr.Description;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/*使用描述方法的测试用例*/
@DisplayName("使用描述方法的测试用例")
public class DescriptionMethodTest {

    @Test
    @DisplayName("加法测试用例")
    public void testDescAn() {
        int result = 3 + 2;
        Allure.description("这是一个加法的测试用例描述信息\n回车换行");
        assertEquals(5, result, "计算错误");
    }

    @Test
    @DisplayName("3-2")
    public void testDescAn2() {
        int result = 3 - 2;
        Allure.description("这是一个减法的测试用例不换行");
        assertEquals(1, result, "计算错误");
    }

    @Test
    @DisplayName("html方法描述测试用例")
    public void testDescAn3() {
        int result = 3 - 2;
        Allure.descriptionHtml("<h1>这是一个大标题</h1><br><a href=\"https://www.baidu.com\">百度</a>");
        assertEquals(1, result, "计算错误");
    }
}

