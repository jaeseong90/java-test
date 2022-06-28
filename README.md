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
 

###2.JUnit 5: 시작하기
####2.1. 스프링 부트 프로젝트 생성
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
  
```
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@Test
    void create(){
        Study study = new Study();
        assertNotNull(study);
        System.out.println("create");
    }
```

####2.2.기본애노테이션
- @Test
- @BeforeAll / @AfterAll
- @BeforeEach / @AfterEach
- @Disabled 
- junut4와 junit5 클래스와 이름이 변경되어 맵핑되어 동일하게 제공

```
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
###3.테스트 이름 표기하기
####3.1.DisplayNameGeneration
- Method와 Class 레퍼런스를 사용해서 테스트 이름을 표기하는 방법 설정입니다.
- 기본 구현체로 ReplaceUnderscores 가 선택됩니다.(_를 공백으로 치환)
```

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
```
    @Test
    @DisplayName("스터디 생성 확인 😁")
    void create_new_study(){
        Study study = new Study();
        assertNotNull(study);
        System.out.println("create");
    }
```

###4.Assertion API
테스트 API를 알아봅시다. 먼저 아래와 같이 테스트할 객체를 준비합니다.
```
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
####4.1 API
- assertEqulas(expected, actual)
- assertNotNull(actual)
- assertTrue(boolean)
- assertAll(executables...)
- assertThrows(expectedType, executable)
- assertTimeout(duration, executable)
- assertEqulas(expected, actual)
- 테스트를 해봅시다. 실패 시 메시지를 넣을 수 있습니다.
- Test를 한 곳에 2개 넣었다면 앞에 테스트가 실패하면 뒤쪽은 확인이 안됩니다.
 ```
    @Test
    @DisplayName("스터디 생성 시 상태가 드래프트인지 확인")
    void create_new_study(){
        Study study = new Study();
        assertNotNull(study);
        assertEquals(StudyStatus.DRAFT, study.getStudyStatus());
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
####4.2.



#### from inflearn whiteship