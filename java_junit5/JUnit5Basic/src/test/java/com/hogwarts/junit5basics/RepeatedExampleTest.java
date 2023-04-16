package com.hogwarts.junit5basics;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;

// 重复测试
public class RepeatedExampleTest {
    // 在name = "" 中，所有 的{}中都是变量， 其他的都是固定的格式
    // displayName代表显示名称，如果没有设定，那么会使用方法名称
    // currentRepetition 当前是第几次重复
    // totalRepetitions 总共需要重复几次
    @RepeatedTest(value = 3, name = " {displayName} {currentRepetition} -- {totalRepetitions}")
    @DisplayName("hogwartsSchool")
    void hogwarts() {
        System.out.println("霍格沃兹测试学社支付成功");
    }
}
