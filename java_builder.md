# java에서 String을 합칠때 효율적인 리소스 관리를 위해 StringBuilder를 사용하자

```java
ex)
    // StringBuilder 객체 생성
    StringBuilder builder = new StringBuilder();
    // String add
    builder.append("1");
    builder.append("2");

    // 이런식으로도 사용가능_01
    for (Map.Entry<String, String> kv : params.entrySet()) {
        builder.append(kv.getKey());
        builder.append('=');
        builder.append(kv.getValue());
        builder.append('&');
    }
    
    // 이런식으로도 사용가능_02
    builder.append("mkey=" + {variable});
    // String 변환
    builder.toString();
```