# response entity 객체를 사용해보자!

```java
@GetMapping(value = "/")
public ResponseEntity<List<Map<String, Object>>> nextDayList() throws Exception {
  
  List<Map<String, Object>> result = service.method();

  return new ResponseEntity<>(result, HttpStatus.OK);
	}
```