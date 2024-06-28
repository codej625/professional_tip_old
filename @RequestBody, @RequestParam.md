# @RequestBody, @RequestParam

<br /><br />

1. @RequestBody
```java
@PostMapping("/create")
public ResponseEntity<User> createUser(@RequestBody User user) {
  // user 객체는 요청의 JSON 본문에서 자동으로 매핑.
  // ...
  return ResponseEntity.ok(user);
}
```
```
어노테이션은 HTTP 요청의 본문(body)을 자바 객체로 변환해주는 역할을 한다.
주로 POST나 PUT 요청과 함께 사용되며,
요청의 JSON 본문을 User 객체로 변환하여 메서드의 매개변수로 받는다.
```

<br/><br/>

2. @RequestParam
```java
@GetMapping("/search")
public ResponseEntity<List<User>> searchUsers(
  @RequestParam(value = "name", required = false) String name,
  @RequestParam(value = "age", required = false, defaultValue = "0") int age) {
  // name, age 매개변수는 요청의 쿼리 문자열에서 추출.
  // ...
  return ResponseEntity.ok(users);
}
```
```
HTTP 요청의 매개변수를 메서드의 매개변수에 바인딩한다.
URL 쿼리 문자열(query string)이나 폼 데이터(form data)에서 매개변수 값을 추출하여 사용할 수 있다.
```

<br /><br />

```
@RequestBody
HTTP 요청의 본문(body)을 자바 객체로 변환하여 컨트롤러 메서드에서 사용.
```
```
@RequestParam
HTTP 요청의 쿼리 문자열(query string)이나 폼 데이터(form data)에서 매개변수 값을 추출하여 사용.
```

||@RequestBody|@RequestParam|
|------|---|---|
|객체생성|가능|불가능|
|각 변수별로 데이터 저장|불가능|가능|
