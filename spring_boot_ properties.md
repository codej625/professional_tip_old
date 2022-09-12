# properties를 분리하여 관리

1. resource 경로 및에 ex) jdbc.properites를 작성한다.

2. application 클래스에 해당 파일의 경로를 추가한다.

ex)
```java
@SpringBootApplication
@PropertySource(value = { "classpath:jdbc.properties" })
public class MainApplication {
 
  public static void main(String[] args) {
    SpringApplication.run(MainApplication.class, args);
  }
}
```

3. 추가적으로 여러 개의 properties를 설정하고 싶은 경우

ex) @PropertySource 속성 값을 여러개 설정하거나. ( Java8 이상 가능 )
```java
@PropertySource("classpath:foo.properties")
@PropertySource("classpath:bar.properties")
public class PropertiesWithJavaConfig {
    //...
}
```

ex) @PropertySources를 사용하여 설정할 수 있다. (모든 Java 버전에서 가능)
```java
@PropertySources({
    @PropertySource("classpath:foo.properties"),
    @PropertySource("classpath:bar.properties")
})
public class PropertiesWithJavaConfig {
    //...
}
```

만약 각 파일에 중복된 설정 값이 있는 경우 마지막 @propertySource 파일을 기준으로 설정 값이 사용되어진다.

+) 내용 추가
```
1. application.properties파일과 같은 위치에 application-TEST.properites라고 이름을 짓고 해당 파일에 적으면 된다.
2. application.properties파일에서 spring.config.activate.on-profile = TEST라고 작성해 해당 파일을 불러와 적용하게 된다.
```
