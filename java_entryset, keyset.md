# map 전체 출력(entrySet, keySet)

## Map에 값을 전체 출력하기 위해서는 entrySet(), keySet() 메소드를 사용하면 되는데 entrySet() 메서드는 key와 value의 값이 모두 필요한 경우 사용하고, keySet() 메서드는 key의 값만 필요한 경우 사용합니다.

ex ) entrySet();

```java
Map<String, String> map = new HashMap<String, String>();
map.put("key01", "value01");
map.put("key02", "value02");
map.put("key03", "value03");
map.put("key04", "value04");
map.put("key05", "value05");

// 방법 01 : entrySet()
for (Map.Entry<String, String> entry : map.entrySet()) {
	System.out.println("[key]:" + entry.getKey() + ", [value]:" + entry.getValue());
}
```

ex) keySet();
```java
Map<String, String> map = new HashMap<String, String>();
map.put("key01", "value01");
map.put("key02", "value02");
map.put("key03", "value03");
map.put("key04", "value04");
map.put("key05", "value05");
        
// 방법 02 : keySet()
for (String key : map.keySet()) {
	String value = map.get(key);
  System.out.println("[key]:" + key + ", [value]:" + value);
}    
```

## Iterator 인터페이스를 사용할 수 없는 컬렉션인 Map에서 Iterator 인터페이스를 사용하기 위해서는 Map에 entrySet(), keySet() 메소드를 사용하여 Set 객체를 반환받은 후 Iterator 인터페이스를 사용하시면 됩니다.

ex) entrySet().iterator();
```java
Map<String, String> map = new HashMap<String, String>();
map.put("key01", "value01");
map.put("key02", "value02");
map.put("key03", "value03");
map.put("key04", "value04");
map.put("key05", "value05");
    
// 방법 03 : entrySet().iterator()
Iterator<Map.Entry<String, String>> iteratorE = map.entrySet().iterator();
while (iteratorE.hasNext()) {
	Map.Entry<String, String> entry = (Map.Entry<String, String>) iteratorE.next();
  String key = entry.getKey();
  String value = entry.getValue();
  System.out.println("[key]:" + key + ", [value]:" + value);
}
```

ex) keySet().iterator();
```java
Map<String, String> map = new HashMap<String, String>();
map.put("key01", "value01");
map.put("key02", "value02");
map.put("key03", "value03");
map.put("key04", "value04");
map.put("key05", "value05");

// 방법 04 : keySet().iterator()
Iterator<String> iteratorK = map.keySet().iterator();
	while (iteratorK.hasNext()) {
	String key = iteratorK.next();
	String value = map.get(key);
	System.out.println("[key]:" + key + ", [value]:" + value);
}
```