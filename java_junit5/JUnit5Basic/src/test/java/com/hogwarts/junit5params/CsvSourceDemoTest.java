package com.hogwarts.junit5params;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class CsvSourceDemoTest {

    @ParameterizedTest
    @CsvSource({"哈利,2","赫敏,22","罗恩,12"})
    void csvSourceDemo(String name, Integer age) {
        System.out.println(name + "的年龄是:" + age);
    }
    @ParameterizedTest
    @CsvSource(value = {"哈利-2","赫敏-3","罗恩-5"},delimiterString = "-")
    void csvSourceDemo2(String name, Integer age){
        System.out.println(name+"的年龄是:"+age);
    }
}
