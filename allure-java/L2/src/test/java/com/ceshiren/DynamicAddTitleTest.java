package com.ceshiren;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.TestFactory;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;

/*Allure2 报告中添加用例标题*/
@DisplayName("动态更新测试用例标题")
public class DynamicAddTitleTest {

    @TestFactory
    List<DynamicTest> dynamicStrTest() {
        List<String> inputList = Arrays.asList("apple", "banan", "orang");

        return inputList.stream()
                .map(str -> DynamicTest.dynamicTest("每次生成测试用例长度" + str, () -> {
                    assertEquals(5, str.length(), "Length of " + str);
                }))
                .collect(Collectors.toList());
    }

}
