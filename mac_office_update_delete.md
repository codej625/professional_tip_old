# Microsoft AutoUpdate를 삭제하는 방법

1. MacOS의 Finder에서 폴더로 이동(Command + Shift + G)을 누르고 아래 경로를 입력한다.
```
/Library/Application Support/Microsoft/
```
2. "MAU" 혹은 "MAU2.0"의 폴더를 찾아 들어가면 "Microsoft AutoUpdate.app"을 찾을 수 있다. 이를 쓰레기통으로 던지면 된다.

# 설정을 지우기

1. 폴더로 이동(Command + Shift + G)을 누르고 아래 경로로 들어가 "com.microsoft.update.agent.plist"를 지운다.
```
/Library/LaunchAgents
```

2. 폴더로 이동(Command + Shift + G)을 누르고 아래 경로로 들어가 "com.microsoft.autoupdate.helper.plist"를 지운다.
```
/Library/LaunchDaemons/
```

3. 폴더로 이동(Command + Shift + G)을 누르고 아래 경로로 들어가 "com.microsoft.autoupdate.helper"를 지운다.
```
/Library/PrivilegedHelperTools
```
