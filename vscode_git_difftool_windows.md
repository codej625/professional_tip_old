# vscode로 difftool을 실행시키자!

<br/>

```
윈도우를 기준으로 한다.

우선, git config를 띄워야 한다.
```

```
git config --global -e
```

<br/>

```
.gitconfig에 다음을 추가한다.
```

```
...
[diff]
  tool = vscode
[difftool "vscode"]
  cmd = code --wait --diff $LOCAL $REMOTE
```

<br>

```
다음을 실행시켜보자
```

```
git difftool
git difftool --staged
```
