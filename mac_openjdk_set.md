# 환경 변수 설정

```
vi ~/.bash_profile
```

```
vi를 이용하여 bash_profile이 실행되면 창이 변경됩니다.

" i " 키를 눌러주세요.
```

```
"i"키를 누르면 하단에 "-- INSERT --"로 바뀐 걸 확인할 수 있습니다.
```

```
export JAVA_HOME=/Library/Java/JavaVirtualMachines/adoptopenjdk-11.jdk/Contents/Home

export PATH=${PATH}:$JAVA_HOME/bin

입력 후 "esc" 키를 눌러주세요.



그리고

":wq"를 입력해주면 하단에 작성된 걸 확인할 수 있습니다.

"return" 키를 눌러주세요.
```

```
다시 원래의 실행 창으로 변경되면

source ~/.bash_profile 명령어를 입력해주세요.

이제 설정이 끝났습니다.
```

```
정상적으로 설정이 적용된 건지 확인하기 위해서 몇 가지 명령어를 통해서 확인해보도록 하겠습니다.

먼저, " echo $PATH " 명령어를 입력해주세요.

사진처럼 JDK 경로가 출력되면 정상적으로 PATH 설정이 완료된 거예요.
```

```
" javac -version " 명령어를 입력해주세요.

자신이 설정한 JDK 버전이 정상적으로 출력됩니다.
```

```
" Java -version " 명령어를 입력해주세요.

자신이 설정한 JDK 버전이 정상적으로 출력됩니다.


3가지 명령어를 확인했을 때 이상 없이 출력된다면,

정상적으로 환경 변수 PATH 설정이 완료된것임
```
