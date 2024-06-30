# @ModelAttribute에 대해

<br /><br />

1. HTML 폼을 통해 POST 방식으로 전송된 데이터를 받기 위해서는 다음과 같은 방식을 사용할 수 있다.
```
@ModelAttribute 를 사용하면,
HTML 폼에서 전송된 데이터를 자바 객체로 바인딩하여 처리한다.
(폼의 각 입력 필드는 DTO 클래스의 필드에 매핑.)
```
```java
@PostMapping("/submitForm")
public String submitForm(@ModelAttribute Object object) {
  // formObject에 웹 폼에서 전송된 데이터가 바인딩 된다.
  // 처리 로직 작성
  return "resultPage";
}
```

<br />

```
HTML 폼을 통해 POST 방식으로 전송된 데이터를 받기 위해서는
@ModelAttribute 또는 @RequestBody를 사용하는 것이 일반적이다.

@ModelAttribute는 HTML 폼 데이터를 자바 객체로 변환하여 받아오는 데 유용하며,
@RequestBody는 클라이언트가 JSON 또는 XML 데이터를 전송할 때 사용된다.
```
