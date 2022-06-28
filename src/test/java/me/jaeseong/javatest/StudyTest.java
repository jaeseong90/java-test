package me.jaeseong.javatest;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class StudyTest {

    @Test
    void create(){
        Study study = new Study();
        assertNotNull(study);
        System.out.println("create");
    }

    @Test
    void create2(){
        Study study = new Study();
        assertNotNull(study);
        System.out.println("create2");
    }

    @BeforeAll
    static void beforeAll(){
        System.out.println("beforeAll");
    }

    @AfterAll
    static void afterAll(){
        System.out.println("afterAll");
    }

    @AfterEach
    void afterEach(){
        System.out.println("afterEach");
    }

    @BeforeEach
    void beforeEach(){
        System.out.println("beforeEach");
    }

}