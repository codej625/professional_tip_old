# windows에 linux를 설치해보자(wsl)

1. 가상화 기능과 wsl을 사용하기 위해 윈도우 터미널에 밑에 명령어를 먼저 실행한다.
설치가 완료되면 재부팅을 한다.
```
dism.exe /online /enable-feature /featurename:Microsoft-Windows-Subsystem-Linux /all /norestart 
dism.exe /online /enable-feature /featurename:VirtualMachinePlatform /all /norestart
```

2. 재부팅이 완료 되었으면 밑에 명령어를 사용하여 wsl을 설치한다.
```
wsl --install
```

3. 처음에는 default값으로 wsl1이 설치 되어있을 수 있다.
wsl2로 기본설정을 바꿔준다.
ex) wsl --set-version <distribution name> <versionNumber>
```
wsl --set-version Ubuntu 2
```

4. microsoft store를 열고 ubuntu를 설치하면 아이콘이 생기는것을 확인할수 있다.

<br/>

etc

1. 현재 사용중인 리눅스와 버전을 알 수 있다.
```
wsl --list --verbose
```