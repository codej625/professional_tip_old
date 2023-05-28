# gradle build통해 jar파일을 생성해보자

```
스프링부트는 톰캣을 내장하기 때문에 war파일과 별도의 톰캣이 필요없다.
즉 jar파일로 만들어 실행하면 그대로 리눅스에 앱서버를 배포할 수 있다.
```

```
build.gradle 파일을 열고
일단 밑에 명령어를 추가한다.
```

```gradle
bootJar {
  baseName = 'myproject'
  version =  '0.0.1'
}
```

```
mac => 1. Permission denied error 에러가 뜬다면, chmod +x ./gradlew를 사용하여 권한을 준다.
2. ./gradlew build (gradle build 명령어를 사용하면 path경로로 이동할 필요가 없다.)

windows => 1. .\gradlew createJar
```

```
서버를 종료 => Ctrl + C
전체 자바 종료 => killall -9 java
```