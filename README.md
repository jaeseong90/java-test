#Java Application Test  

### 개요 
자바 애플리케이션 테스트를 하는 다양한 방법에 대해 알아봅시다.

### 실습환경
- openjdk 11 
- ide intellij   

###1.JUnit 5: 소개
- 자바 개발자가 가장 많이 사용하는 테스팅 프레임워크입니다.
- 기존에는 JUnit4를 많이 사용했으나 SpringBoot 버전을 2.2로 올리면서 기본 junit 버전을 junit5 로 올렸으니 JUnit5를 준비하도록 합시다.
- 자바  8 이상을 필요로 합니다.
- 대체제: TestNG, Spock, ...
<br>(비슷하니 junit 하면 다른것도 사용 가능할것으로 생각됨) 
 
 ![캡처](./Zimages/캡처.JPG)
 
- Platform:  테스트를 실행해주는 런처 제공. TestEngine API 제공. <br>
 ( Vscode, 인텔리j 와 같은 툴에서 실행 가능하도록 런처 제공 툴에서 플랫폼을 제공한다는 의미 ide를통해서 )
- Jupiter: TestEngine API 구현체로 JUnit 5를 제공.
- Vintage: JUnit 4와 3을 지원하는 TestEngine 구현체.

- 참고: https://junit.org/junit5/docs/current/user-guide/

미세팁) intellij sout -> system.out.println , 
psvm public static void main 자동완성 
 
<hr/>

###JUnit 5: 시작하기

###1.스프링 부트 프로젝트 생성
 - start.spring.io 에서 프로젝트 생성하여 오픈합니다. (starter 와 test 만 포함하였습니다.)
 - 2.2 이상부터는 spring-boot-test 포함 => 들어가 보면 그 안에 junit 디펜던시 들어있습니다.
 - spring boot 아니면 아래와 같이 추가하여 줍시다. 

```xml
    <dependency>
      <groupId>org.junit.jupiter</groupId>
      <artifactId>junit-jupiter</artifactId>
      <version>5.8.2</version>
      <scope>test</scope>
    </dependency>
```

- statudy 라는 클래스를 만들고 테스트를 한번 실행하여 봅시다.(안된다면 import static 확인하여 봅시다.) 
- jupiter API 사용하는것을 볼 수 있습니다. 
  
```java
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@Test
    void create(){
        Study study = new Study();
        assertNotNull(study);
        System.out.println("create");
    }
```

<hr/>

####2.기본애노테이션
- @Test
- @BeforeAll / @AfterAll
- @BeforeEach / @AfterEach
- @Disabled 
- junut4와 junit5 클래스와 이름이 변경되어 맵핑되어 동일하게 제공

```java
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

    beforeAll
    
    beforeEach
    create
    afterEach
    
    
    beforeEach
    create2
    afterEach
    
    afterAll

```

<hr/>

###3.테스트 이름 표기하기
####3.1.DisplayNameGeneration
- Method와 Class 레퍼런스를 사용해서 테스트 이름을 표기하는 방법 설정입니다.
- 기본 구현체로 ReplaceUnderscores 가 선택됩니다.(_를 공백으로 치환)
```java

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class NameTest {

    @Test
    void create_new_study(){
        Study study = new Study();
        assertNotNull(study);
        System.out.println("create");
    }

}
```

####3.2.DisplayName
- value를 넣어서 직관적으로 적용 가능합니다.
- @DisplayNameGeneration 보다 우선 순위입니다.
```java
    @Test
    @DisplayName("스터디 생성 확인 😁")
    void create_new_study(){
        Study study = new Study();
        assertNotNull(study);
        System.out.println("create");
    }
```

참고
- https://junit.org/junit5/docs/current/user-guide/#writing-tests-display-names

<hr/>

###4.Junit5 Assertion
테스트 API를 알아봅시다. 먼저 아래와 같이 테스트할 객체를 준비합니다.
```java
public class Study {

    private StudyStatus studyStatus;

    public StudyStatus getStudyStatus() {
        return studyStatus;
    }
}
public enum  StudyStatus {
    DRAFT, START, END
}
```
####4.1.API
1. assertEqulas(expected, actual)
2. assertNotNull(actual)
3. assertTrue(boolean)
4. assertAll(executables...)
5. assertThrows(expectedType, executable)
6. assertTimeout(duration, executable)
7. assertEqulas(expected, actual)
- 테스트를 해봅시다. 실패 시 메시지를 넣을 수 있습니다.
- Test를 한 곳에 2개 넣었다면 앞에 테스트가 실패하면 뒤쪽은 확인이 안됩니다.
 ```java
    @Test
    @DisplayName("스터디 생성 시 상태가 드래프트인지 확인")
    void create_new_study(){
        Study study = new Study();
        assertNotNull(study);
        assertEquals(StudyStatus.DRAFT, study.getStudyStatus());
        //Supplier<String> 가 들어갑니다.
        //assertEquals(StudyStatus.DRAFT, study.getStudyStatus(), "실패시 메시지도 넣을 수 있습니다");
        /*assertEquals(StudyStatus.DRAFT, study.getStudyStatus(), new Supplier<String>() {
            @Override
            public String get() {
                return "서플라이어입니다. 모르겠다면 자바8을 공부해야합니다.";
            }
        });*/
        //assertEquals(StudyStatus.DRAFT, study.getStudyStatus(), ()->"람다 표현식 모르겠다면 자바8을 공부해야합니다..");
    }

    org.opentest4j.AssertionFailedError: 
    Expected :DRAFT
    Actual   :null
```
- assertAll 을 활용하여 assert 문을 람다 표현식으로 전달하여 한번에 여러 assert문을 실행하여 봅시다.
```java
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

    expected: <DRAFT> but was: <null>
    Comparison Failure: 
    Expected :DRAFT
    Actual   :null
    
    expected: <true> but was: <false>
    Comparison Failure: 
    Expected :true
    Actual   :false

```
- 예외 테스트 코드를 확인해 봅시다.
```java
public class Study {

    private StudyStatus studyStatus;
    private int limit;

    public Study(){
    }

    public Study(int limit) {
        if(limit < 0){
            throw new IllegalArgumentException("limit 은 0보다 작을 수 없습니다.");
        }
        this.limit = limit;
    }

    public int getLimit() {
        return limit;
    }

    public StudyStatus getStudyStatus() {
        return studyStatus;
    }
}
```
```java
    @Test
    @DisplayName("예외 테스트 코드 확인")
    void studyTest3(){
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,()-> new Study(-1));
        assertEquals("limit 은 0보다 작을 수 없습니다.", exception.getMessage());
    }
```
- 타임아웃 테스트 코드를 작성해 봅시다.
- 해당 테스트 소스코드가 모두 종료될때까지 기다립니다.
```java
    @Test
    @DisplayName("타임아웃 테스트 코드 확인")
    void studyTest4(){

        assertTimeout(Duration.ofMillis(1000L),()->{
            new Study();
            Thread.sleep(15000L);
        });

    }

```
- 즉각 종료 테스트 코드 
- Thread Local 을 사용합니다.
- Spring의 Transaction 은 ThreadLocal 을 기본 전략으로 사용합니다.
- 테스트 코드에 Transaction 이 포함된다면 적절히 선택하여 사용하도록 합시다.
```java
    @Test
    @DisplayName("타임아웃 테스트 코드 확인")
    void studyTest4(){
        assertTimeoutPreemptively(Duration.ofMillis(1000L),()->{
            new Study();
            Thread.sleep(15000L);
        });
    }
```

<hr/>

###5.조건에 따라 테스트
####5.1.assume
- assume이 만족하지 않으면 아래 테스트를 실행하지 않고 실행하지 않은것으로 표시합니다.
1. assumeTrue(조건)
2. assumeThat(조건, 테스트)
```java
    @Test
    @DisplayName("assume")
    void test(){
        String test_env = System.getenv("JAVA_HOME");
        System.out.println(test_env);
        Assumptions.assumeTrue("C:\\Program Files\\openjdk\\jdk-11".equalsIgnoreCase(test_env));
        //Assumptions.assumeTrue("".equalsIgnoreCase(test_env));

        Study study = new Study(1);
        Assertions.assertThat(study.getLimit()).isGreaterThan(0);
    }
```
####5.2.애노테이션활용
- @Enabled, @Disabled
- OnOS, OnJre, IfSystemProperty, IfenvironmentVariable, If
```java
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
```

<hr/>

###6.태깅과 필터링
- 태깅을 통하여 해당 설정이 된 환경에서의 테스트 진행 
- 기본적으로 인텔리J에서는 class 기준으로 모두 실행합니다. 설정을 변경하여 줍시다.
![intellijEditConfigurations](./ZImages/intellijEditConfigurations.PNG)
- 태그가 local 만 실행됩니다.
```java
    @Test
    @DisplayName("true확인1")
    @Tag("local")
    void test1(){
        Assertions.assertTrue(true);
    }

    @Test
    @DisplayName("true확인2")
    @Tag("server")
    void test2(){
        Assertions.assertTrue(true);
    }
```
- maven 테스트를 실행해 보면 모든 테스트가 실행되는것을 볼 수 있습니다.
- 설정을 통하여 특정 tag만 실행하여 봅시다
- maven 특정 프로파일마다 각기 다른 설정이 가능합니다.
- pom.xml default 프로파일로 실행해 봅시다.
- test 실행 시 local 태그만 실행되는것을 확인할 수 있습니다.
- groups 를 안주면 모든 테스트를 진행합니다. 
```xml
    <profiles>
		<profile>
			<id>default</id>
			<activation>
				<activeByDefault>true</activeByDefault>
			</activation>
			<build>
				<plugins>
					<plugin>
						<artifactId>maven-surefire-plugin</artifactId>
						<configuration>
							<groups>local</groups>
						</configuration>
					</plugin>
				</plugins>
			</build>
		</profile>
		<profile>
			<id>server</id>
			<build>
				<plugins>
					<plugin>
						<artifactId>maven-surefire-plugin</artifactId>
						<configuration>
							<groups>server</groups>
							<!--<groups>server|ci</groups>-->
						</configuration>
					</plugin>
				</plugins>
			</build>
		</profile>
	</profiles>
```
```
$ mvnw test -P server
```

<hr/>

###7.커스텀태그
- 애노테이션을 조합하여 커스텀 태그를 만들어 봅시다.
- 애노테이션을 추가합니다.
```java
@Target(ElementType.METHOD)
@Test
@Tag("local")
@Retention(RetentionPolicy.RUNTIME)
public @interface LocalTest {
}
```
- 커스텀태그를 사용해서 태깅합니다.
- 태그를 사용하면 typesafe 하지않습니다. (오타 등의 위험)
```java
    @Test
    @DisplayName("커스텀태그")
    @LocalTest
    void test3(){
        Assertions.assertTrue(true);
    }
```

참고
- https://maven.apache.org/guides/introduction/introduction-to-profiles.html
- https://junit.org/junit5/docs/current/user-guide/#running-tests-tag-expressions

###8.테스트 반복하기
####8.1.RepeatedTest
- 반복 횟수와 반복 테스트 이름을 설정할 수 있습니다.
- displayName, currentRepetition, totalRepetitions
- RepetitionInfo 인자를 받을 수 있습니다.
```java
    @DisplayName("반복테스트")
    @RepeatedTest(value = 10, name = "{displayName} {currentRepetition} 회차")
    void test(RepetitionInfo info){
        System.out.println("test : " + info.getCurrentRepetition() + "/" + info.getTotalRepetitions());
    }
```
####8.2.Parameterized
테스트에 여러 다른 매개변수를 대입해가며 반복 실행합니다.
- @ValueSource
- @NullSource, @EmptySource, @NullAndEmptySource
- @EnumSource
- @MethodSource
- @CsvSource
- @CvsFileSource
- @ArgumentSource

1. 형변환
    - 암시적 타입변환 가능
    - 명시적 타입변환 
        - SimpleArgumentConverter 상속 받은 구현체
        - @ConvertWith   
2. 인자 값 조합
    - ArgumentsAccessor
    - 커스텀 Accessor
        - ArgumentsAggregator 인터페이스 구현
        - @AggregateWith


```java
    @DisplayName("반복테스트 param")
    @ParameterizedTest(name = "{displayName}{index},  args : {arguments}")
    @ValueSource(strings = {"jaeseong","재성"})
    void test2(String s){
        System.out.println(s);
    }
```
```java
    @DisplayName("반복테스트 null, empty")
    @ParameterizedTest(name = "{displayName}{index},  args : {arguments}")
    @NullSource
    @EmptySource
    @NullAndEmptySource
    void test3(Study s){
        System.out.println(s);
    }
```
```java
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
```
```java
public class Study {

    private StudyStatus studyStatus;
    private String title;
    private int limit;

    public Study(){
    }

    public String getTitle() {
        return title;
    }

    public Study(String title, int limit) {
        this.title = title;
        this.limit = limit;
    }

    @Override
    public String toString() {
        return "Study{" +
                "studyStatus=" + studyStatus +
                ", title='" + title + '\'' +
                ", limit=" + limit +
                '}';
    }

    public Study(int limit) {
        if(limit < 0){
            throw new IllegalArgumentException("limit 은 0보다 작을 수 없습니다.");
        }
        this.limit = limit;
    }

    public int getLimit() {
        return limit;
    }

    public StudyStatus getStudyStatus() {
        return studyStatus;
    }
}



    @DisplayName("ParameterizedTest csv")
    @ParameterizedTest
    @CsvSource({"'java-test', 10", "'spring', 20"})
    void test5(String name, Integer limit){
        Study study = new Study(name, limit);
        System.out.println(study);
    }

```
```java
    @DisplayName("ParameterizedTest csv with argumentsAccessor")
    @ParameterizedTest
    @CsvSource({"'java-test', 10", "'spring', 20"})
    void test6(ArgumentsAccessor argumentsAccessor){
        Study study = new Study(argumentsAccessor.getString(0), argumentsAccessor.getInteger(1));
        System.out.println(study);
    }
```
```java
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
```
- 참고 
 https://junit.org/junit5/docs/current/user-guide/#writing-tests-parameterized-tests

<hr/>

###9.테스트 인스턴스
- 단위테스트의 메서드는 각각 테스트마다 독립된 인스턴스를 생성하여 실행되는것을 기본전략으로 합니다.
- 단위테스트는 독립적으로 실행하여 예상치 못한 오류를 방지하기 위함입니다.
- @TestInstance 애노테이션을 통하여 인스턴스를 공유 할 수 있습니다.
- 이 때 before, after 테스트는 static 이 아니여도 됩니다.

```java

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

//@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class InstanceTest {
    int value = 1;
    @Test
    void test(){
        System.out.println(value++);
    }

    @Test
    void test2(){
        System.out.println(value++);
    }
}

```

<hr/>

###10.테스트순서
- 테스트 메서드는 특정한 순서에 의해 실행되지만 어떻게 그 순서를 정하는지는 분명하지 않습니다. 순서대로 실행된다고 생각하고 작성하면 안됩니다.
- 경우에 따라 특정 순서대로 실행하고 싶을때도 있습니다. 그 경우에는 테스트 메소드를 원하는 순서에 따라 실행하도록 TestInstance 애노테이션과
함께 @TestMethodOrder 구현체를 설정합니다.
```java
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
```

<hr/>

###11.junit-platform.properties
- JUnit 설정 파일로, 클래스패스 루트 (src/test/resources/)에 넣어두면 적용됩니다.
- IntelliJ [project structure] - [module] - TestResources 로 설정되어 있어야 인식합니다.

```xml
#테스트 인스턴스 라이프사이클 설정
junit.jupiter.testinstance.lifecycle.default = per_class
#확장팩 자동 감지 기능 default false
junit.jupiter.extensions.autodetection.enabled = true
#@Disabled 무시하고 실행하기
junit.jupiter.conditions.deactivate = org.junit.*DisabledCondition
#테스트 이름 표기 전략 설정
junit.jupiter.displayname.generator.default = \
org.junit.jupiter.api.DisplayNameGenerator$ReplaceUnderscores
```

<hr/>

###13.JUnit5확장모델 Extension
- Extension 으로 확장

####13.1. 등록방법
- 선언적인 등록 @ExtendWith
- 프로그래밍 등록 @RegisterExtension
- 자동 등록 자바 ServiceLoader 이용( junit properties 를 통해 사용가능)

- 참고 https://junit.org/junit5/docs/current/user-guide/#extensions

<hr/>

###14.Junit4마이그레이션
- 실습은 넘어가도록 합시다.

![캡처2](./Zimages/캡처2.PNG)

<hr/>

###15.연습 
- 


<hr/>

#### from inflearn whiteship