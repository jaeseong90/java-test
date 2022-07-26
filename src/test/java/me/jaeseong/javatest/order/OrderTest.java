package me.jaeseong.javatest.order;

import org.junit.jupiter.api.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class OrderTest {

    @Test
    @DisplayName("두번째 테스트")
    @Order(2)
    void test(){
        System.out.println("2");
    }

    @Test
    @DisplayName("첫번째 테스트")
    @Order(1)
    void test2(){
        System.out.println("1");
    }

}
