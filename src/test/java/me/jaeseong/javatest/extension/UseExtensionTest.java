package me.jaeseong.javatest.extension;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(ExtensionTest.class)
public class UseExtensionTest {

    @Test
    @DisplayName("SlowTest")
    @SlowTest
    void Test1 (){
        System.out.println(this);

        try {
            Thread.sleep(1005L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(this);
    }

    @Test
    @DisplayName("SlowTest2")
    void Test2 (){
        System.out.println(this);

        try {
            Thread.sleep(1005L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(this);
    }

}
