package com.ceshiren;

import io.qameta.allure.Allure;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;

import static org.junit.jupiter.api.Assertions.assertEquals;

/*链接地址的方法用例*/
@DisplayName("链接地址的方法用例")
public class LinkAndIssueMethodTest {
    @Test
    @DisplayName("百度和腾讯link首页链接")
    public void testLink(TestInfo testInfo) {
        Allure.link("百度首页", "sub link", "https://www.baidu.com");
        Allure.link("腾讯首页", "sub link", "https://www.tencent.com/zh-cn/");
        assertEquals(10, 10, "计算错误");

    }

    @Test
    @DisplayName("百度和腾讯issue首页链")
    public void testIssue(TestInfo testInfo) {
        Allure.issue("百度首页", "https://www.baidu.com");
        Allure.issue("腾讯首页", "https://www.tencent.com/zh-cn/");
        assertEquals(10, 10, "计算错误");
    }
}
