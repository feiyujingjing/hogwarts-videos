package com.ceshiren;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;

@DisplayName("用例重试验证")
public class RetryTest {

  /* 执行命令
   mvn clean test allure:report -Dsurefire.rerunFailingTestsCount=重试次数
   */

    @Test
    public void testRetry() throws IOException {
        throw new IOException("IO流异常！");
    }
}
