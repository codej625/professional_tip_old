# [Tomcat] 톰캣에 파일 업로드 시 502 Bad Gateway 발생할 경우

1. 보통 파일 업로드 시 multipart/form-data 로 POST 방식으로 전송을 하게 되는데올리는 도중 아래와 같이 502 Bad Gateway를 응답받을 경우가 있다.

```html
<!DOCTYPE HTML PUBLIC "-//IETF//DTD HTML 2.0//EN">
<html>
    <head>
        <title>502 Bad Gateway</title>
    </head>
    <body>
        <h1>Bad Gateway</h1>
        <p>The proxy server received an invalid response from an upstream server.</p>
    </body>
</html>
```

2. 그럴 경우 tomcat의 conf/server.xml 에 있는 Connector에 maxPostSize를 아래와 같이 적절한 값으로 세팅해주면 해결된다.
   아무것도 설정되어 있지 않을 때 default 값은 2MB(2097152byte)이다.

```
<Connector 
    port="80" protocol="HTTP/1.1"
    connectionTimeout="20000"
    redirectPort="8443"
    maxPostSize="5242880" 
/>
```
