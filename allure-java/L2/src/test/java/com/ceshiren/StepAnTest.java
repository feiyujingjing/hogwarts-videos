package com.ceshiren;

import entity.User;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Step;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Epic("购物平台")
@DisplayName("步骤注解验证")
@Feature("订单模块")
//@Step("输入数字 {digit}")
public class StepAnTest {
    int result;


    /**
     * @Step("步骤描述")
     */
    @Test
    @DisplayName("减法步骤验证")
    @Step("--减法步骤验证--")
    void testSub() {
        addUser(new User("张三", 28));//输入的用户名及年龄为： 张三,28
        int a = pressDigit(6);//输入数字 6
        String str = pressSubtraction();
        int b = pressDigit(2);//输入数字 2
        result = subtract(a, b);
        assertEquals(4, result, a + str + b + "计算错误");
    }

    @Step("输入的用户名及年龄为： {u.name},{u.age}")
    private int addUser(User u) {
        return u.getAge();
    }

    @Step("输入数字 {digit}")
    private int pressDigit(int digit) {
        return digit;
    }

    @Step("输入减号")
    private String pressSubtraction() {
        return "-";
    }

    @Step("减法计算")
    public int subtract(int x, int y) {
        return x - y;
    }
}


