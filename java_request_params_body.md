# request 요청에 따른 parameter를 받아보자!

```
@RestController 기준
```
```
ex) get 방식으로 parameter를 전송했을때
```
```java
@GetMapping(value = "/")
public Map<String, Object> get(Model model,
  @RequestParam(value = "nowPage", required = false) String nowPage) throws Exception {
  log.info(">>> get parameter >>>");
  ...
}
```

```
ex) post 방식으로 json parameter를 전송했을때
```
```java
@PostMapping(value = "/")
public Map<String, Object> post(Model model,
  @RequestBody HashMap<String, Object> params) throws Exception {
  log.info(">>> post parameter >>>");
  ...
}
```

