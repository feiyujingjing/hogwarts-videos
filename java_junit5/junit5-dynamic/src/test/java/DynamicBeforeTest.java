import org.junit.jupiter.api.*;

import java.util.stream.Stream;

/*动态测试生命周期*/
public class DynamicBeforeTest {
    static int m = 0;

    @BeforeAll
    static void beforeAll() {
        System.out.println("BeforeAll");
    }

    @BeforeEach
    void beforeEach() {
        System.out.println("BeforeEach");
        m = 8;
    }

    @TestFactory
    Stream<DynamicTest> dynamicTestStream() {
        return Stream.of(6, 7, 8)
                .map(arg -> DynamicTest.dynamicTest("test" + arg, () -> {
                            System.out.println("m:" + m);
                        })
                );
    }

    @TestFactory
    Stream<DynamicContainer> dynamicTestStream2() {
        return Stream.of(6, 7, 8)
                .map(arg -> DynamicContainer.dynamicContainer("Container" + arg,
                        Stream.of(
                                DynamicTest.dynamicTest("test" + arg, () -> {
                                    System.out.println("m:" + m);
                                    m += arg;
                                    System.out.println("m:" + m);
                                }),
                                DynamicTest.dynamicTest("test" + arg, () -> {
                                    System.out.println("2m:" + m);
                                    m += arg;
                                    System.out.println("2m:" + m);
                                })
                        ))
                );
    }

    @AfterEach
    void afterEach() {
        System.out.println("afterEach");

    }

    @AfterAll
    static void afterAll() {
        System.out.println("afterAll");
    }
}
