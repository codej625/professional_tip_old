# 당일 날짜를 특정 포맷으로 불러오는 방법

```java
public class CurrentDateTime {
  public static void main(String[] args) {
    // 현재 날짜와 시간을 가져옵니다.
    LocalDateTime now = LocalDateTime.now();

    // 날짜와 시간을 원하는 형식으로 포맷합니다.
    DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");

    String formattedDate = now.format(dateFormatter);
    String formattedTime = now.format(timeFormatter);

    // 결과를 출력합니다.
    System.out.println("오늘 날짜: " + formattedDate);
    System.out.println("현재 시간: " + formattedTime);
  }
}
```