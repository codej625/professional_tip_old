# array의 중복을 제거해보자!

```
간결하고 가독성이 좋은 스트림을 사용해보자!
```
```java
String[] arr = {"1", "1", "2", "2", "3"};

arr = Arrays.stream(arr).distinct().toArray(String[]::new);
```