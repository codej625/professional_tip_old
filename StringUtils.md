# StringUtils를 알아보자!

- str의 문자열을 length의 길이만큼 오른쪽부터 출력한다.
- str이 null이면 return값은 null이다.
```java
StringUtils.right("test", 0); // ""
StringUtils.right("test", 2); // st
StringUtils.right("test", 4); // test
```

<br/>

- str의 문자열을 length의 길이만큼 왼쪽부터 출력한다.
- str이 null return 값은 null이다.
```java
StringUtils.left("abc", 0); // ""
StringUtils.left("abc", 2); // "ab"
StringUtils.left("abc", 4); // "abc"
```