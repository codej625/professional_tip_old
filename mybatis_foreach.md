# mybatis 동적 쿼리를 사용해보자!

```
배열 파라미터를 Map을 통해 넘겼을 경우
```
```java
String[] userArray = {"1","2","3"};
```
```java
HashMap<String, Object> map = new HashMap<String, Object>();
map.put("userArray",userArray);
```
```xml
<select id="getAuthUserList"  resultType="members">
  SELECT m.*,a.name FROM members AS m 
  JOIN authority AS a ON m.authority = a.authority
  WHERE m.authority IN
    <foreach collection="userArray" item="arr" open="(" close=")" separator=",">
      #{arr}
    </foreach>
  ORDER BY m.authority;
</select>
```
```
※ 주의 : collection을 꼭! 넘겨주는 배열 변수 값과 동일하게 작성하셔야 합니다.
```

<br/>

```
배열 파라미터를 직접 넘겼을 경우
```
```java
String[] userArray = {"1","2","3"};
```
```xml
<select id="getAuthUserList"  resultType="members">
  SELECT m.*,a.name FROM members AS m 
  JOIN authority AS a ON m.authority = a.authority
  WHERE m.authority IN
    <foreach collection="array" item="arr" open="(" close=")" separator=",">
    #{arr}
    </foreach>
  ORDER BY m.authority;
</select>
```
```
※ 주의 : collection을 꼭! "array"로 작성하셔야 합니다.
```

<br/>

```
리스트 Map을 통해 넘겼을 경우
```
```java
List<Members> chkList = userService.getUserList(); //SELECT * FROM members 결과값
```
```java
HashMap<String, Object> map = new HashMap<String, Object>();
map.put("chkList",chkList);
```
```xml
<select id="getListTest" resultType="members">
  SELECT m.*,a.name FROM members AS m 
  JOIN authority AS a ON m.authority = a.authority
  WHERE m.authority IN
    <foreach collection="chkList" item="item" open="(" close=")" separator=",">
    #{item.authority}
    </foreach>
  ORDER BY m.authority;
</select>
```
```
리스트 안에 뽑고 싶은 결괏값을 {key.value} 형태로 뽑으시면 됩니다.
※ 주의 : collection을 꼭! 넘겨주는 리스트 변수 값과 동일하게 작성하셔야 합니다.
```

<br/>

```
리스트 파라미터를 직접 넘겼을 경우
```
```java
List<Members> chkList = userService.getUserList(); //SELECT * FROM members 결과값
```
```xml
<select id="getListTest" resultType="members">
  SELECT m.*,a.name FROM members AS m 
  JOIN authority AS a ON m.authority = a.authority
  WHERE m.authority IN
    <foreach collection="list" item="item" open="(" close=")" separator=",">
      #{item.authority}
    </foreach>
  ORDER BY m.authority;
</select>
```
```
리스트 안에 뽑고 싶은 결괏값을 {key.value} 형태로 뽑으시면 됩니다.
※ 주의 : collection을 꼭! "list"로 작성하셔야 합니다.
```

<br/>

1. 먼저 리스트/배열 변수 값을 collection에 넣어주고, item이라는 설정으로 별칭 설정을 해준다.
2. 리스트/배열의 값이 시작하기 전 open="(" 이 설정돼있으므로'(' (열린 괄호)가 열리게 되고
3. 리스트/배열의 값이 한 번씩 반복문을 거칠 때마다 separator 옵션에 있는 ', '(콤마)가 찍히게 된다.
4. 반복이 끝나면 close=")" 설정이 있으므로 ')' (닫힌 괄호)가 쓰인다.