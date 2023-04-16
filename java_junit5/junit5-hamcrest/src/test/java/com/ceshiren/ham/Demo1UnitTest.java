package com.ceshiren.ham;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class Demo1UnitTest {

    @Test
    void UsingIsForMatch() {
        String testString = "hamcrest cor1e Is match";
        //is就是一个增强的效果
        assertThat(testString, is("hamcrest cor1e Is match"));
        assertThat(testString, is(equalTo("hamcrest cor1e Is match")));

    }

    @Test
    void UsingEqualToForMatch() {
        //T 泛型
        String actualString = "equalTo match";
        List<String> actualList = Arrays.asList("equalTo", "match");
        Object original = 100;
        List<String> stringList = Arrays.asList("equalTo", "match");

        assertThat(actualString, is(equalTo("equalTo match")));
        assertThat(actualList, is(equalTo(stringList)));
        assertThat(original, equalToObject(100));
    }
    @Test
    void UsingNotForMatch(){
        String testString = "hamcrest not match";

        assertThat(testString,not("hamcrest other match"));
        assertThat(testString,is(not(equalTo("hamcrest other match"))));
        assertThat(testString,is(not(instanceOf(Integer.class))));
    }

    @Test
    void UsingHasItemForMatch(){
        List<String> list = Arrays.asList("java", "hamcrest", "junit5");

        assertThat(list,hasItem("java"));
        assertThat(list,hasItem("junit5"));
        assertThat(list,hasItem(isA(String.class)));

        assertThat(list,hasItems("java","junit5"));
        assertThat(list,hasItems(isA(String.class),endsWith("est"),containsString("j")));
    }
    @Test
    void UsingAllOfForMatch(){
        String testString = "Achilles is powerful";
        assertThat(testString,allOf(
               startsWith("Achi") ,endsWith("ful"),containsString("Achilles")
        ));
    }
    @Test
    void UsingAllOfForMatch1(){
        String testString = "Achilles is powerful";
        assertThat(testString,anyOf(
                startsWith("2Achi"),endsWith("2ful"),containsString("powerful")
        ));
    }
    @Test
    void UsingBothForMatch(){
        String testString = "daenerys targaryen";
        assertThat(testString,both(startsWith("dae")).and(containsString("targaryen")));
    }
    @Test
    void UsingEitherForMatch(){
        String testString = "daenerys targaryen";
        assertThat(testString,either(startsWith("2dae")).or(containsString("targaryen")));
    }
}
