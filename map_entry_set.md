# map entry set을 사용해보자!

```
map의 key와 value를 전부 사용할 수 있다!
```

```java
	public void entry(HashMap<String, Object> map) {
		
		for(Map.Entry<String, Object> result : map.entrySet()) {
			System.out.println("key => " + result.getKey() + " : " + "value => " + result.getValue());
		}

	}
```
