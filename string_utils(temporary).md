# StringUtils에 대해 알아보자.

<br /><br />

```
Apache Commons 라이브러리에서 제공된다.
이 클래스는 다양한 문자열 연산을 간편하게 수행할 수 있도록 도와준다.
```

<br /><br /><br />

* 예시

<br />

```
String의 문자열을 length의 길이만큼 오른쪽부터 출력 예시
String이 null이면 return값은 null이다.
```
```java
StringUtils.right("test", 0); // ""
StringUtils.right("test", 2); // st
StringUtils.right("test", 4); // test
```

<br/>

```

String의 문자열을 length의 길이만큼 왼쪽부터 출력 예시

```
```java
StringUtils.left("abc", 0); // ""
StringUtils.left("abc", 2); // "ab"
StringUtils.left("abc", 4); // "abc"
```
