package me.jaeseong.javatest.asserts;

import me.jaeseong.javatest.Study;
import me.jaeseong.javatest.StudyStatus;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.util.function.Supplier;

import static org.junit.jupiter.api.Assertions.*;

public class AssertTest {

    @Test
    @DisplayName("스터디 생성 시 상태가 드래프트인지 확인")
    void studyTest(){
        Study study = new Study();
        assertNotNull(study);
        Assertions.assertEquals(StudyStatus.DRAFT, study.getStudyStatus(), "실패시 메시지도 넣을 수 있습니다");
        /*assertEquals(StudyStatus.DRAFT, study.getStudyStatus(), new Supplier<String>() {
            @Override
            public String get() {
                return "서플라이어입니다. 모른다면 자바8을 공부해야합니다.";
            }
        });*/
        //assertEquals(StudyStatus.DRAFT, study.getStudyStatus(), ()->"람다식 모르겠다면 자바8을 공부해야합니다..");
    }

    @Test
    @DisplayName("한번에 assert문 확인")
    void studyTest2(){
        Study study = new Study();
        assertAll(
                ()->assertNotNull(study),
                ()->assertEquals(StudyStatus.DRAFT, study.getStudyStatus()),
                ()->assertTrue(1>2)
        );
    }

    @Test
    @DisplayName("예외 테스트 코드 확인")
    void studyTest3(){
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,()-> new Study(-1));
        assertEquals("limit 은 0보다 작을 수 없습니다.", exception.getMessage());
    }

    @Test
    @DisplayName("타임아웃 테스트 코드 확인")
    void studyTest4(){

       /*
       assertTimeout(Duration.ofMillis(1000L),()->{
            new Study();
            Thread.sleep(15000L);
        });
        */

        //Thread Local
        assertTimeoutPreemptively(Duration.ofMillis(1000L),()->{
            new Study();
            Thread.sleep(15000L);
        });

    }





}
