# gradle설정을 하고 jar파일을 만들어보자!

```
gradle을 사용하여 Jar 파일을 생성하려면 프로젝트의 build.gradle 파일에 몇 가지 구성을 추가해야한다.
먼저, Jar 플러그인을 추가한다. 
이를 위해 build.gradle 파일 상단에 다음 코드를 추가해야한다.
```

```
plugins {
    id 'java'
    id 'application'
}
```

그런 다음 아래와 같이 jar 빌드 태스크를 정의한다.
```
task createJar(type: Jar) {
    from 'src/main/java'
    manifest {
        attributes(
                'Implementation-Title': 'YourProjectName',
                'Implementation-Version': version,
                'Main-Class': 'com.your.package.MainClass'
        )
    }
    with jar
}
```
```
위 코드에서 YourProjectName은 프로젝트의 이름으로 바꿔주고, com.your.package.MainClass는 Jar 파일을 실행할 때 메인 클래스의 경로와 이름으로 변경해준다.
그러면 gradle createJar 명령어를 실행하여 Jar 파일을 생성할 수 있다. 
생성된 Jar 파일은 build/libs 디렉토리에서 찾을 수 있다.
```