# git difftool을 vscode로 사용해보자!

```
VS Code command 인스톨
```
1. Command line 에서 code --help 가 동작하는지 확인한다.
2. 동작하지 않으면, 맥 환경에서는 VS Code 의 Command Palette(⇧⌘P)에서 Shell Command: Install ‘code’ command in PATH를 선택한다.
    윈도우즈의 경우 VS Code 인스톨 시 “Add to PATH”를 선택해야 한다.
3. Command line 에서 다음을 입력한다.
   git config --global core.editor "code --wait"

<br/>

```
이제 다음과 같이 입력하면 VS Code를 Git config 에디터로 사용할 수 있다.
git config --global -e
```
```
Git configuration
```
```
위의 명령어를 입력하거나, ~/.gitcofig 파일을 에디터를 이용하여 직접 연다. 
그리고 아래와 같은 설정을 추가해 준다.
```
```
[diff]
  tool = default-difftool
[difftool "default-difftool"]
  cmd = code --wait --diff $LOCAL $REMOTE
```
```
--wait 옵션은 Command line으로 VS Code를 실행시켰을 경우 VS Code 인스턴스를 닫을 때 까지 command를 대기 시킨다.
Command line으로 실행한 VS Code의 window를 닫으면 command가 다시 활성화 된다.
VS Code를 Git 외부 편집기로 사용할때 편하다.
--diff 옵션을 사용하여 두 파일을 비교한다.
```
```
이제 코드 수정을 하다가 git difftool 명령을 입력하면 VS Code를 통하여 diff가 실행되는 것을 볼 수 있다.
```

<br/>

```
Merge tool 설정
VS Code를 merge tool 로도 활용하고 싶으면, git config 에 다음 설정을 추가한다.
```
```
[merge]
  tool = vscode
[mergetool "vscode"]
  cmd = code --wait $MERGED
```