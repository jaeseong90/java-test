package me.jaeseong.javatest;

import org.junit.jupiter.api.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class InstanceTest {
    int value = 1;

    @Test
    @Order(2)
    void test(){
        System.out.println(value++);
    }

    @Test
    @Order(1)
    void test2(){
        System.out.println(value++);
    }
}
