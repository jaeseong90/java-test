package me.jaeseong.javatest.instance;

import org.junit.jupiter.api.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class InstanceTest {
    int value = 1;

    @Test
    void test(){
        System.out.println(value++);
    }

    @Test
    void test2(){
        System.out.println(value++);
    }
}
