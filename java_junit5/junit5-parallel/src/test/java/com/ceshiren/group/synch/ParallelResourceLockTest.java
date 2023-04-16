package com.ceshiren.group.synch;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import org.junit.jupiter.api.parallel.ResourceLock;
import org.junit.jupiter.api.parallel.Resources;

import java.util.Properties;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.parallel.ResourceAccessMode.READ;
import static org.junit.jupiter.api.parallel.ResourceAccessMode.READ_WRITE;

@Execution(ExecutionMode.CONCURRENT)
public class ParallelResourceLockTest {
    Properties properties;

    @BeforeEach
    void before() {
        properties = new Properties(System.getProperties());
    }
    @Test
    @ResourceLock(value=Resources.SYSTEM_PROPERTIES,mode = READ)
    void test1(){
        assertNull(System.getProperty("custom.property"));
    }
    @Test
    @ResourceLock(value=Resources.SYSTEM_PROPERTIES,mode = READ_WRITE)
    void test2(){
        System.setProperty("custom.property","ceshiren");
        assertEquals("ceshiren",System.getProperty("custom.property"));
    }
    @Test
    @ResourceLock(value=Resources.SYSTEM_PROPERTIES,mode = READ_WRITE)
    void test3(){
        System.setProperty("custom.property","hogwarts");
        assertEquals("hogwarts",System.getProperty("custom.property"));
    }
}
