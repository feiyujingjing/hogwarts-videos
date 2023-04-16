package com.hogwarts.junit5params;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

public class CsvFileSourceDemoTest {

    @ParameterizedTest
    @CsvFileSource(resources = "/data/data.csv")
    void csvFileSourceDemo1(String name, Integer age){
        System.out.println(name +"的年龄是："+age);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/data2.csv",delimiterString = "|")
    void csvFileSourceDemo2(String name, Integer age){
        System.out.println(name +"的年龄是："+age);
    }
}
