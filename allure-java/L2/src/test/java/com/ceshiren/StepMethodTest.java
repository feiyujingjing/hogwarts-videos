package com.ceshiren;

import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Integer.sum;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

/*使用Allure.step()方法添加用例步骤*/
@DisplayName("步骤方法验证")
@Epic("购物平台")
@Epics({
        @Epic("需求2"),
        @Epic("需求3")
})
@Feature("登录模块")
public class StepMethodTest {
    int result;

    @Test
    @DisplayName("加法步骤验证")
    @Story("通过用户名和密码登录")
    public void testSum() {
        Allure.step("输入数字 3");
        int a = pressDigit(3);
        Allure.step("输入加号");
        String str = pressAddition();
        Allure.step("输入数字 2");
        int b = pressDigit(2);
        Allure.step("点击等号");
        result = sum(a, b);
        Allure.step("验证结果是否正确");
        assertEquals(5, result, a + str + b + "计算错误");
    }

    @Test
    @DisplayName("一个测试方法中添加多个大步骤")
    @Story("通过手机号码登录")
    public void testSteps() {
        List<Executable> lists = new ArrayList<>();
        Allure.step("步骤1：登录", () -> {
            System.out.println("登录");
            Allure.step("输入用户名");
            String name = "admin";
            Allure.step("输入密码");
            String pwd = "123456";
            Allure.step("点击登录");
            String login = name + pwd;
            lists.add(() -> assertEquals("admin123456", login));
        });
        Allure.step("步骤2：搜索", () -> {
            System.out.println("搜索");
            Allure.step("输入搜索内容名称");
            Allure.step("点击搜索按钮");
            Allure.step("获取搜索结果");
            lists.add(() -> assertEquals("搜索成功", "搜索成功"));
        });
        assertAll(lists);
    }

    private String pressAddition() {
        return "+";
    }

    private int pressDigit(int digit) {
        return digit;
    }


}
