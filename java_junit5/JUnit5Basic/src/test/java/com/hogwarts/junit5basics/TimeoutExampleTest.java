package com.hogwarts.junit5basics;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import static java.lang.Thread.sleep;
import static java.util.concurrent.TimeUnit.MINUTES;
import static java.util.concurrent.TimeUnit.SECONDS;

public class TimeoutExampleTest {

    /*@Timeout(1)
    @BeforeEach
    void setup() throws InterruptedException {
        sleep(2000);
        System.out.println("测试开始");
    }

    @Timeout(1)
    @AfterEach
    void teardown() throws InterruptedException {
        sleep(2000);
        System.out.println("测试结束");
    }
*/
    @Test
    // 设定用例执行的超时时间，一旦超过x秒，则用例失败，
    // 注解内的参数是以秒为单位
    @Timeout(1)
    void timeoutCaseOne() throws InterruptedException {
        System.out.println("第一条用例");
        sleep(2000); // 对应单位是毫秒，等于10s
    }

    @Test
//    @Timeout(value = 3, unit = MILLISECONDS)
    @Timeout(value = 1, unit = SECONDS)
        // @Timeout(value = 3, unit = MINUTES)
    void timeoutCaseTwo() throws InterruptedException {
        sleep(100);
        System.out.println("第二条用例");
    }

}
