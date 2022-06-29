package me.jaeseong.javatest;

import org.apache.logging.log4j.util.Strings;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.RepetitionInfo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;

public class RepeatTest {

    @DisplayName("반복테스트")
    @RepeatedTest(value = 10, name = "{displayName} {currentRepetition} 회차")
    void test(RepetitionInfo info){
        System.out.println("test : " + info.getCurrentRepetition() + "/" + info.getTotalRepetitions());
    }

    @DisplayName("반복테스트 param")
    @ParameterizedTest(name = "{displayName}{index},  args : {arguments}")
    @ValueSource(strings = {"jaeseong","재성"})
    void test2(String s){
        System.out.println(s);
    }

    @DisplayName("반복테스트 param")
    @ParameterizedTest(name = "{displayName}{index},  args : {arguments}")
    /*@NullSource
    @EmptySource
    @NullAndEmptySource*/
    @CsvSource()
    void test3(Study s){
        System.out.println(s);
    }

    @DisplayName("ParameterizedTest 타입변환")
    @ParameterizedTest
    @ValueSource(ints = {1,2})
    void test4(Study study){
        System.out.println(study.getLimit());
    }


}
