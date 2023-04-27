package com.ceshiren;

import io.qameta.allure.Issue;
import io.qameta.allure.Link;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;

import static org.junit.jupiter.api.Assertions.assertEquals;
/*链接地址的注解用例*/
@DisplayName("链接地址的注解用例")
@Link(name = "腾讯首页", url = "https://www.tencent.com/zh-cn/", type = "sub link")
public class LinkAndIssueAnTest {

    @Test
    @DisplayName("百度首页链接")
    @Link(name = "百度首页", url = "https://www.baidu.com", type = "sub link")
    public void test1(TestInfo testInfo) {
        Link link = testInfo.getTestMethod().get().getAnnotation(Link.class);
        String name = link.name();
        String url = link.url();
        assertEquals("https://www.baidu.com", url, () -> url + "错误");
    }

    @Test
    @DisplayName("加法计算")
    @Issue("https://www.baidu.com")
    public void test2(TestInfo testInfo) {
        int result = 9 + 1;
        assertEquals(10, result, "计算错误");
    }
}

