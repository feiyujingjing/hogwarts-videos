package com.hogwarts.junit5basics;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class DisplayNameExampleTest {
    @Test
    @DisplayName("霍格沃兹第一条用例")
    void hogwartsOne(){
        System.out.println("第一条用例");
    }
    @ParameterizedTest
    @ValueSource(strings = {"霍格沃兹测试开发"})
    void hogwartsTwo(String name){
        System.out.println(name);
        System.out.println("第二条用例");
    }
    @Test
    void hogwarts_Three(){
        System.out.println("第三条用例");
    }
}
