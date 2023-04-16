package com.hogwarts.junit5extend;

import org.junit.jupiter.api.*;

public class BaseBTest extends BaseATest{
    @BeforeAll
    static void beforeBAll(){
        System.out.println("BaseBTest----beforeAAll");
    }
    @AfterAll
    static void afterBAll(){
        System.out.println("BaseBTest----afterAAll");
    }
    @BeforeEach
    void beforeBEach(){
        System.out.println("BaseBTest----beforeAEach");
    }
    @AfterEach
    void afterBEach(){
        System.out.println("BaseBTest----afterAEach");
    }
    @Test
    void hogwarts3(){
        System.out.println("BaseBTest----hogwarts1测试用例");
    }
    @Test
    void hogwarts4(){
        System.out.println("BaseBTest----hogwarts2测试用例");
    }
}
