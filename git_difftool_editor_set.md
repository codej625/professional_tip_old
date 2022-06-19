# difftool -> open vscode

### 준비 사항
문제를 해결하기 전에 준비해야 할 사항이 있습니다. VS Code가 PATH에 설정이 되어 있는지 확인해야 합니다. 커맨드라인에서 다음 명령이 실행되는지 우선 확인하셔야 합니다.

1. cmd => code --help 명령어 실행
if (error) { 
  vscode 경로를  환경 변수 path에 추가해준다.
}

2. git의 설치 root로 가서 .gitconfig 파일을 열고
[diff]
    tool = vscode
[difftool "vscode"]
    cmd = code --wait --diff $LOCAL $REMOTE
이렇게 추가시켜준다.
