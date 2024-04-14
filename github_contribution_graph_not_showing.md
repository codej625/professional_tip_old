# 깃허브에서 잔디가 보이지 않을때

<br />

```
깃허브에서는 동일한 메일로 커밋을 해야 잔디를 표시해준다.
혹시라도 git log를 실행시켜 커밋한 이메일이 다른지 확인해보자.
```

<br />

1. 해쉬 코드 찾기
```git
git log --pretty=format:"%h = %an , %ar : %s" --graph

로그 중 잔디를 심지 못한 해쉬 코드를 기억한다.
```

<br />

2. 해쉬코드로 rebase 하기
```git
git rebase -i -p <해쉬코드>

화면이 뜨면 변경해야하는 커밋들 맨앞에 쓰여있는 값들을 edit으로 바꿔준다.
(pick은 그대로 두는 옵션이다.)
저장을 하고 화면을 나온다.
```

<br />

3. 메일 바꿔주기
```git
git commit --amend --author="이름 <본인 이메일>"

ex) git commit --amend --author="codej625 <codej625@gmail.com>"
입력하면 화면이 뜨는데 잔디만 심을것이기 때문에 무시하고 화면을 닫는다.
```

<br />

4. 파일 처리를 위한 리베이스 해주기
```git
git rebase --continue
git commit --amend --author="이름 <본인 이메일>"

git 명령어를 차례대로 실행시킨다.
```

<br />

5. 무한반복
```

본인이 고쳐야하는 수만큼 반복한다.

```
