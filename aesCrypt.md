# 암호화, 복호화를 해보자!

```java
private String _generateKey(int seq, int id) throws Exception {
  String baseText = String.format("/%d", seq);
  // 필요 길이를 출분히 능가하는 긴 문자열(알파벳 only, '/' 미포함)
  String paddingString;
  try {
      byte[] dummyBytes = new byte[32];
      SecureRandom.getInstanceStrong().nextBytes(dummyBytes);
      paddingString = Hex.encodeHexString(dummyBytes);
  } catch (NoSuchAlgorithmException e) {
      paddingString = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
  }
  // 총 합을 47 글자(byte) 로 제한.
  baseText = StringUtils.right(paddingString + baseText, 47); // 쓰레기TEXT/%d 형식.
  // 매체사 구분을 위해 앞자리를 비운다.
  baseText = baseText.substring(3, 47);
  switch (id) {
    case 6 : baseText = "aaa" + baseText; break;
    case 7 : baseText = "aab" + baseText; break;
    case 8 : baseText = "aac" + baseText; break;
    case 11: baseText = "aad" + baseText; break;
    case 19: baseText = "aae" + baseText; break;
    case 20: baseText = "aaf" + baseText; break;
    case 21: baseText = "aag" + baseText; break;
    
    default: baseText = StringUtils.right(paddingString + baseText, 47); break;
  }
  // 암호화
  return aesCrypt.encryptBase64URL(baseText);
}
```

<br/>

```java
private String _postback(String key) throws Exception {
  String plainText = "";
  try {
    plainText = aesCrypt.decryptBase64(key);
  } catch {
    ...
  }
  return plainText;
}
```