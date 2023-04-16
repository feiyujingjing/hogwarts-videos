package com.ceshiren.class2;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

@Tag("SuiteTag")
public class TwoTest {
    public TwoTest() {
    }
    @Test
    void test1() {
        System.out.println("com.ceshiren.class2-----TwoTest");
    }

    @Test
    void test2() {
        System.out.println("com.ceshiren.class2-----TwoTest2");
    }

}
