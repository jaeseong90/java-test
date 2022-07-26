package me.jaeseong.javatest.configs;

import org.junit.jupiter.api.Test;

public class ConfigTest {
    int value = 1;

    @Test
    void test_aa(){
        System.out.println(value++);
    }

    @Test
    void test2(){
        System.out.println(value++);
    }

}
