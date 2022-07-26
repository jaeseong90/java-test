package me.jaeseong.javatest.repeat;

import me.jaeseong.javatest.Study;
import org.apache.logging.log4j.util.Strings;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.aggregator.AggregateWith;
import org.junit.jupiter.params.aggregator.ArgumentsAccessor;
import org.junit.jupiter.params.aggregator.ArgumentsAggregationException;
import org.junit.jupiter.params.aggregator.ArgumentsAggregator;
import org.junit.jupiter.params.converter.ArgumentConversionException;
import org.junit.jupiter.params.converter.ConvertWith;
import org.junit.jupiter.params.converter.SimpleArgumentConverter;
import org.junit.jupiter.params.provider.*;
import org.yaml.snakeyaml.scanner.ScannerImpl;

import java.util.function.IntConsumer;

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

    @DisplayName("반복테스트 null, empty")
    @ParameterizedTest(name = "{displayName}{index},  args : {arguments}")
    @NullSource
    @EmptySource
    @NullAndEmptySource
    void test3(Study s){
        System.out.println(s);
    }

    @DisplayName("ParameterizedTest 타입변환 Converter 이용")
    @ParameterizedTest
    @ValueSource(ints = {10,20})
    void test4(@ConvertWith(StudyConverter.class) Study study){
        System.out.println(study.getLimit());
    }

    static class StudyConverter extends SimpleArgumentConverter{
        @Override
        protected Object convert(Object o, Class<?> aClass) throws ArgumentConversionException {
            Assertions.assertEquals(Study.class, aClass,"study만 가능");
            return new Study(Integer.parseInt(o.toString()));
        }
    }

    @DisplayName("ParameterizedTest csv")
    @ParameterizedTest
    @CsvSource({"'java-test', 10", "'spring', 20"})
    void test5(String name, Integer limit){
        Study study = new Study(name, limit);
        System.out.println(study);
    }

    @DisplayName("ParameterizedTest csv with argumentsAccessor")
    @ParameterizedTest
    @CsvSource({"'java-test', 10", "'spring', 20"})
    void test6(ArgumentsAccessor argumentsAccessor){
        Study study = new Study(argumentsAccessor.getString(0), argumentsAccessor.getInteger(1));
        System.out.println(study);
    }

    @DisplayName("ParameterizedTest csv with ArgumentsAggregator")
    @ParameterizedTest
    @CsvSource({"'java-test', 10", "'spring', 20"})
    void test7(@AggregateWith(StudyArgumentsAggregator.class) Study study){
        System.out.println(study);
    }

    static class StudyArgumentsAggregator implements ArgumentsAggregator{
        @Override
        public Object aggregateArguments(ArgumentsAccessor argumentsAccessor, ParameterContext parameterContext) throws ArgumentsAggregationException {
            return new Study(argumentsAccessor.getString(0), argumentsAccessor.getInteger(1));
        }
    }





}
