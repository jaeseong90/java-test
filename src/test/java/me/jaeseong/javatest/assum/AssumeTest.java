package me.jaeseong.javatest.assum;

import me.jaeseong.javatest.Study;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Assumptions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.*;

public class AssumeTest {

    @Test
    @DisplayName("test1")
    void test(){
        String test_env = System.getenv("JAVA_HOME");
        System.out.println(test_env);
        Assumptions.assumeTrue("C:\\Program Files\\openjdk\\jdk-11".equalsIgnoreCase(test_env));

        Study study = new Study(-1);
        Assertions.assertThat(study.getLimit()).isGreaterThan(0);
    }

    @Test
    @DisplayName("test2")
    @DisabledOnOs(OS.WINDOWS)
    void test2(){
        String test_env = System.getenv("JAVA_HOME");
        System.out.println(test_env);
        Assumptions.assumeTrue("C:\\Program Files\\openjdk\\jdk-11".equalsIgnoreCase(test_env));

        Study study = new Study(1);
        Assertions.assertThat(study.getLimit()).isGreaterThan(0);
    }

    @Test
    @DisplayName("test3")
    @DisabledOnJre(JRE.JAVA_11)
    void test3(){
        String test_env = System.getenv("JAVA_HOME");
        System.out.println(test_env);
        Assumptions.assumeTrue("C:\\Program Files\\openjdk\\jdk-11".equalsIgnoreCase(test_env));

        Study study = new Study(1);
        Assertions.assertThat(study.getLimit()).isGreaterThan(0);
    }

    @Test
    @DisplayName("test4")
    @EnabledIfEnvironmentVariable(named = "JAVA_HOME", matches = "C:\\Program Files\\openjdk\\jdk-11")
    void test4(){
        Study study = new Study(1);
        Assertions.assertThat(study.getLimit()).isGreaterThan(0);
    }

}
