# 맵을 원하는 값으로 초기화 해보자!!

```
한줄로 깔끔하게 정리가 가능하다.
```
```java
	public void mapInitialization(List<Map<String, Object>> map) throws Exception {

		Map<String, Object> _json = new HashMap<>() {{ put("json", map); }};
		
	}
```

