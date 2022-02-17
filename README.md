#Java Application Test  

###1.JUnit 5: 소개
- 자바 개발자가 가장 많이 사용하는 테스팅 프레임워크.
- 자바  8 이상을 필요로 함.
- 대체제: TestNG, Spock, ...
(비슷하니 junit 하면 다른것도 사용 가능할것으로 생각됨) 
 
- Platform:  테스트를 실행해주는 런처 제공. TestEngine API 제공.
( Vscode, 인텔리j 와 같은 툴에서 실행 가능하도록 런처 제공 툴에서 플랫폼을 제공한다는 의미 ide를통해서 )
- Jupiter: TestEngine API 구현체로 JUnit 5를 제공.
- Vintage: JUnit 4와 3을 지원하는 TestEngine 구현체.

- 참고: https://junit.org/junit5/docs/current/user-guide/

미세팁) intellij sout -> system.out.println 자동 완성

###2.JUnit 5: 시작하기
1. 스프링 부트 프로젝트 생성
 - 2.2 이상부터는 spring-boot-test 포함 => 들어가 보면 그 안에 junit 디펜던시 들어있음
 - spring boot 아니면 아래와 같이 추가  

```xml
    <dependency>
      <groupId>org.junit.jupiter</groupId>
      <artifactId>junit-jupiter</artifactId>
      <version>5.8.2</version>
      <scope>test</scope>
    </dependency>
```