# java 스트림 예시

```
다음과 같은 숫자 목록이 있을 때 이를 제곱한 값으로 변환한 후 홀수 값만 필터링하고 정렬하여 출력하는 예시
```

```java
public class JavaStreamExample {
    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        List<Integer> squaredOdds = numbers.stream()
            .map(n -> n * n) // 숫자를 제곱합니다.
            .filter(n -> n % 2 != 0) // 홀수만 필터링합니다.
            .sorted() // 정렬합니다.
            .collect(Collectors.toList()); // 결과를 리스트로 변환합니다.

        System.out.println(squaredOdds); // 결과를 출력합니다: [1, 9, 25, 49, 81]
    }
}
```
```
이 예제에서는 numbers 리스트를 스트림으로 변환한 후, map()을 사용하여 각 요소를 제곱하고, filter()를 사용하여 홀수만 필터링합니다. 
그런 다음 sorted()를 사용하여 결과를 정렬하고, collect()를 사용하여 결과를 리스트로 수집한 후 출력합니다.
```