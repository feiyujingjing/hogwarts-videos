package com.hogwarts.junit5params;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullSource;

public class NullSourceDemoTest {

    // 1. 声明是一个参数化的测试方法，
    // 2. 使用 NullSource 注解
    // 3. 定义一个String 类型的形参
    @ParameterizedTest
    @NullSource
    void nullSourceDemo(String param) {
        System.out.println(param);
    }
}
