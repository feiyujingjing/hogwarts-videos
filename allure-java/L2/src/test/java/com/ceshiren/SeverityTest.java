package com.ceshiren;

/*Allure2 报告中添加用例优先级*/

import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("用例等级划分验证")
//@Severity(SeverityLevel.TRIVIAL)
public class SeverityTest {

    @Test
    @DisplayName("Blocker级别的优先级")
    @Severity(SeverityLevel.BLOCKER)
    public void testSeverity() throws IOException {
        String input = "test data";
        ByteArrayInputStream inputStream = new ByteArrayInputStream(input.getBytes());
        byte[] buffer = new byte[1024];
        int lengthRead = inputStream.read(buffer);
        inputStream.close();
        assertEquals("test data", new String(buffer, 0, lengthRead));
    }

    @Test
    @DisplayName("CRITICAL级别的优先级")
    @Severity(SeverityLevel.CRITICAL)
    public void testSeverity2() {
        assertEquals("test data", "test data");
    }

    @Test
    @DisplayName("NORMAL级别的优先级")
    //默认是NORMAL级别的优先级
    //本方法未添加优先级，如果类上添加优先级，使用类上的优先级
    public void testSeverity3() {
        assertEquals("test data", "test data");
    }
}
