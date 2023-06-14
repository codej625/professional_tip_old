# openssl을 사용하여 keystore를 만들어보자!

1. 무료 인증서를 발급받자
```
https://www.sslforfree.com/
```

2. http file upload 방식이 가장 쉽다.

3. 다운 받은 text파일을 밑에 경로로 넣는다.
```
resources/static/.well-known/pki-validation/textfile
```
4. 로컬 환경에서 http://localhost:8080/.well-known/textfile 경로로 접속하여 테스트

5. 성공하였으면 빌드 후 배포한다.

6. 무료 인증서를 다운받고
```
openssl pkcs12 -export -out keystore.p12 -inkey private.key -in certificate.crt -certfile ca_bundle.crt
```

7. 생성된 p12파일을 resources/keystore.p12경로에 넣는다.

8. yaml 설정을 바꿔준다.

```yaml
	server:
  ssl:
    key-store: classpath:keystore.p12
    key-store-password: 입력한 비밀번호
    key-store-type: PKCS12
```

9. 끝