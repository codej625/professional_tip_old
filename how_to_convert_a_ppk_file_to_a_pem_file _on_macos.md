# MacOS ppk파일을 pem 파일로 변경하기

<br />

1. putty install
```

brew install putty

```

<br />

2. ppk to pem
```

puttygen {자신의 ppk키 파일이름}.ppk -O private-openssh -o {변경할 pem키 파일이름}.pem

```

<br />

3. pem 파일 권한 설정
```

chmod 600 {변경된 pem 키 이름}.pem

```

<br />

4. 터미널 접속
```

ssh -i "{your_pem_key_name}.pem" {your_server_account}@{your_server_dns}

```
