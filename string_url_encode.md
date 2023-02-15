# url 인코딩/디코딩을 해보자!

1. 인코딩
```java
String encodeResult = URLEncoder.encode(String encodingString, String charsetName);
```
* charsetName에는 캐릭터 인코딩 셋을 넣는다.
* ex) "UTF-8"

2. 디코딩
```java
String decodeResult = URLDecoder.decode(String decodingString, String charsetName);
```