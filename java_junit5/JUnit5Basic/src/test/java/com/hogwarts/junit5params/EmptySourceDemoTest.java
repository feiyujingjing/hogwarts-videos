package com.hogwarts.junit5params;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EmptySource;
import org.junit.jupiter.params.provider.NullAndEmptySource;

public class EmptySourceDemoTest {
    @ParameterizedTest
    @EmptySource
    void emptySourceDemo(String param) {
        System.out.println(param);
    }

    @ParameterizedTest
    @NullAndEmptySource
    void nullAndEmptySourceDemo(String param) {
        System.out.println(param);
    }
}
