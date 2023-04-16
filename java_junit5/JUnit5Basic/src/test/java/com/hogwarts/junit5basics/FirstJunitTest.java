package com.hogwarts.junit5basics;

import org.junit.jupiter.api.Test;

public class FirstJunitTest {

    @Test
    void first(){
        System.out.println("第一个Junit5测试用例");
    }
    @Test
    void second(){
        System.out.println("第二个Junit5测试用例");
    }
}
