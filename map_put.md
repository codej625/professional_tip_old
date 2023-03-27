# map의 여러가지 put 방법중에 편리한 방법과 map을 clear 할때 효율적인 방법을 알아보자!

```
map.put();을 조금 더 축약한 표현법
```

```java
Map<String, Object> map = new HashMap<>() {
  {
    put("01", 10);
    put("02", 11);
    put("03", 12);
    put("04", 13);
    put("05", 14);
    put("06", 15);
    put("07", 16);
  }
};
```

<br/>

```
map을 clear할때는 어떤 방법이 있을까? 밑에는 해당 key값만 삭제하는 예제이다.
```

```java
Map<String, Object> testMap = new HashMap<String, Object>();

testMap.put("red", "apple");
testMap.put("green", "melon");
testMap.put("yellow", "banana");

System.out.println("testMap : " + testMap);

// remove() 를 사용하여 해당 key, value 삭제 
testMap.remove("red");

System.out.println("remove 후 testMap : " + testMap);
```

```
맵 전체를 삭제 하려면?
```

```java
Map<String, Object> testMap = new HashMap<String, Object>();

testMap.put("red", "apple");
testMap.put("green", "melon");
testMap.put("yellow", "banana");

System.out.println("testMap : " + testMap);

// clear() 를 사용하여 해당 Map의 모든 내용 삭제 
testMap.clear();

System.out.println("clear 후 testMap : " + testMap);
```

