# java mail aceess to default session denied error을 해결해 보자!

```java
[기존 소스]
MyAuthenticator auth = new MyAuthenticator(id,pw);
Session mailSession = Session.getDefaultInstance(props,auth);

[수정 소스]
MyAuthenticator auth = new MyAuthenticator(id,pw);
Session mailSession = Session.getInstance(props,auth);
```