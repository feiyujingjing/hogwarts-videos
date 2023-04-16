package com.hogwarts.junit5assert;

import org.junit.jupiter.api.Test;

import java.time.Duration;

import static java.lang.Thread.sleep;
import static org.junit.jupiter.api.Assertions.assertTimeout;

public class AssertsTimeoutDemoTest {
    @Test
    void timeoutDemo() {
        assertTimeout(Duration.ofSeconds(1), () -> {
        sleep(1000);
        });
    }
}
