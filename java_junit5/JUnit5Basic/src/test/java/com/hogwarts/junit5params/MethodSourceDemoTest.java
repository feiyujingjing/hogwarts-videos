package com.hogwarts.junit5params;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

public class MethodSourceDemoTest {

    @ParameterizedTest
    //@MethodSource("stringProvider")// 2. 通过MethodSource("")指定数据源的方法名,
    @MethodSource
        // 如果不加参数，那么会找和测试方法同名的静态方法
        //    4. 添加形参，形参的类型，要和静态方法内部的元素类型一致
    void methodSourceDemo(Integer name) {
        System.out.println(name);
    }

    static Stream<Integer> stringProvider(){
        return Stream.of(1, 2);
    }
    static Stream<Integer> methodSourceDemo(){
        return Stream.of(3, 4);
    }
}
