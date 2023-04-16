import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DynamicContainer;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.TestFactory;

import java.util.*;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.hamcrest.Matchers.*;

public class DynamicNodeTest {

    @TestFactory
    @DisplayName("单个动态测试")
    DynamicTest DynamicTest() {
        return DynamicTest.dynamicTest("返回DynamicTest", () ->
                assertEquals(5, 3 + 2));
    }

    @TestFactory
    @DisplayName("多个动态测试的容器")
    DynamicContainer DynamicContainerTest() {
        return DynamicContainer
                .dynamicContainer("多个动态测试的容器",
                        Stream.of(
                                DynamicTest.dynamicTest("加法动态测试用例", () -> {
                                    System.out.println("这是一个加法动态测试用例");
                                    assertEquals(6, 3 + 2);
                                }),
                                DynamicTest.dynamicTest("除法动态测试用例", () -> {
                                    System.out.println("这是一个除法动态测试用例");
                                    assertEquals(5, 25 / 5);
                                })
                        )
                );
    }

    @TestFactory
    @DisplayName("动态测试Stream")
    Stream<DynamicTest> dynamicTestStream() {
        return Stream.of(5, 6, 7)
                .map(
                        arg -> DynamicTest.dynamicTest("动态测试-" + arg, () -> {
                            System.out.println("参数" + arg);
                            assertThat(arg, is(greaterThan(5)));
                        })
                );
    }

    @TestFactory
    @DisplayName("动态测试多个Stream流")
    Stream<DynamicContainer> dynamicTestStreams() {
        return Stream.of(5, 6, 7)
                .map(
                        arg -> DynamicContainer.dynamicContainer("Container" + arg,
                                Stream.of(
                                        DynamicTest.dynamicTest("动态测试-" + arg, () -> {
                                            System.out.println("参数" + arg);
                                            assertThat(arg, is(greaterThan(3)));
                                        }),
                                        DynamicTest.dynamicTest("2动态测试-" + arg, () -> {
                                            System.out.println("参数" + arg);
                                            assertThat(arg, is(greaterThan(5)));
                                        })
                                )
                        )
                );
    }

    @TestFactory
    @DisplayName("动态测试Collection")
    Collection<DynamicTest> dynamicTestCollection() {
        DynamicTest dynamicTest1 = DynamicTest.dynamicTest("add-动态测试", () -> {
            System.out.println("动态测试用例：");
        });
        DynamicTest dynamicTest2 = DynamicTest.dynamicTest("sub-动态测试", () -> {
            System.out.println("sub动态测试用例：");
        });
        return Arrays.asList(dynamicTest1, dynamicTest2);
    }

    @TestFactory
    @DisplayName(("迭代器的动态测试1"))
    Iterator<DynamicTest> dynamicTestIterator() {
        Collection<DynamicTest> dynamicTests = new ArrayList<>();
        PrimitiveIterator.OfInt iterator = IntStream.iterate(2, n -> n + 1).limit(3).iterator();
        while (iterator.hasNext()) {
            Integer next = iterator.next();
            DynamicTest dynamicTest1 = DynamicTest.dynamicTest("n动态测试", () -> {
                System.out.println("n1:" + next);
                assertThat(next, is(greaterThan(1)));
            });
            DynamicTest dynamicTest2 = DynamicTest.dynamicTest("n2动态测试", () -> {
                System.out.println("n2:" + next);
                assertThat(next, is(greaterThan(2)));
            });
            dynamicTests.add(dynamicTest1);
            dynamicTests.add(dynamicTest2);
        }
        return dynamicTests.iterator();

    }

    @TestFactory
    @DisplayName(("迭代器的动态测试2"))
    Iterable<DynamicTest> dynamicTestIterable() {
        Collection<DynamicTest> dynamicTests = new ArrayList<>();
        PrimitiveIterator.OfInt iterator = IntStream.iterate(2, n -> n + 1).limit(3).iterator();
        while (iterator.hasNext()) {
            Integer next = iterator.next();
            DynamicTest dynamicTest1 = DynamicTest.dynamicTest("n动态测试", () -> {
                System.out.println("n1:" + next);
                assertThat(next, is(greaterThan(1)));
            });
            DynamicTest dynamicTest2 = DynamicTest.dynamicTest("n2动态测试", () -> {
                System.out.println("n2:" + next);
                assertThat(next, is(greaterThan(2)));
            });
            dynamicTests.add(dynamicTest1);
            dynamicTests.add(dynamicTest2);
        }
        return dynamicTests;

    }


}
