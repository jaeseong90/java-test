package me.jaeseong.javatest;

import net.bytebuddy.asm.Advice;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

public class TagFilterTest {

    @Test
    @DisplayName("태그")
    @Tag("local")
    void test1(){
        Assertions.assertTrue(true);
    }

    @Test
    @DisplayName("태그")
    @Tag("server")
    void test2(){
        Assertions.assertTrue(true);
    }


    @Test
    @DisplayName("커스텀태그")
    @LocalTest
    void test3(){
        Assertions.assertTrue(true);
    }
}
