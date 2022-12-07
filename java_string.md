# 문자열(String)의 빈 값 혹은 Null을 다루는 여러가지 방법을 알아보자!

```java
ex) 먼저, 문자열의 빈 값과 null을 다룰 때 아래와 같은 코드를 자주 만나게 된다.

boolean checkString(String str) {
  return str == null || str.equals("");
}

하지만 이 방법은 equals()가 내부적으로 가지는 여러 로직에 의해 기능적으로 낭비일 수 있습니다.
```
<br>

```java
ex) 자바 6보다 낮은 버전이라면 length()를 활용해보자!
//equals()

public boolean equals(Object anObject) {
  if (this == anObject) {
	return true;
  }
  if (anObject instanceof String) {
 	String anotherString = (String)anObject;
	int n = count;
	if (n == anotherString.count) {
		char v1[] = value;
		char v2[] = anotherString.value;
		int i = offset;
		int j = anotherString.offset;
       		while (n-- != 0) {
				if (v1[i++] != v2[j++])
				return false;
			}
		return true;
	}
  }
  return false;
}

//length()

public int length() {
  return count;
}
```
<br>

```java

ex) length()는 길이만 계산하여 반환하는 것에 반해, equals()는 내부적으로 복잡한 로직이 존재하는 것을 볼 수 있다.
boolean checkString(String str) {
  return str == null || str.length() == 0;
}
```

```
문자열이 빈 값이면 true를 반환합니다.
단, 여기서 문제가 있는데요.
바로 문자열이 스페이스바를 이용하여 입력한 whitespace("  ")인 경우입니다.
그럴 때는 아래와 같이 trim()을 이용하여 공백을 제거 후 사용하시면 됩니다. 
```

```java
boolean checkString(String str) {
  return str == null || str.trim().length() == 0;
}
```

<br>

```java
ex) 자바 6 이상이라면 isEmpty()를 사용해보시기 바랍니다.

boolean checkString(String str) {
  return str == null || str.isEmpty();
}
```

```
문자열이 빈 값("") 일 경우 true를 리턴합니다. 
하지만 isEmpty()를 이용할 경우에도 whitespace("  ")인 경우에 문제가 생길 수 있는데요.
이번에도 아래와 같이 trim()을 이용하여 공백을 제거 후 사용하시면 됩니다. 
```

```java
boolean checkString(String str) {
  return str == null || str.trim().isEmpty();
}
```
<br>

```
자바 11 이상이라면 isBlank()를 사용해보자
```

```
1번 방법과 2번 방법 모두, 고질적인 whitespace("  ")의 문제가 있었다.
isBlank()를 활용하는 것도 하나의 방법이 될 수 있습니다.
```

```java
boolean checkString(String str) {
  return str == null || str.isBlank();
}
```

<br>

```
라이브러리를 추가하여 StringUtils를 활용해보자!
```

```
<!--MAVEN-->
<dependency>
	<groupId>org.apache.commons</groupId>
	<artifactId>commons-lang3</artifactId>
</dependency>

<!--GRADLE-->
compile group: 'org.apache.commons', name: 'commons-lang3', version: '3.7'
```

```
StringUtils의 빈 값을 처리하는 메서드는 2가지가 있는데요.
바로 isEmpty()와 isBlank()입니다.
사용 방법은 각각 다음과 같습니다.
```

```java
//isEmpty()
boolean checkString(String str) {
  return StringUtils.isEmpty(str);
}

//isBlank()
boolean checkString(String str) {
  return StringUtils.isBlank(str);
}
```

```
문자열이  빈 값("") 일 경우 true를 반환합니다.
하지만 whitespace("  ")의 처리에서는 다른데요.
isEmpty()는 false를, isBlank()는 true를 반환합니다. 이 점을  주의하셔서 사용하시면 될 것 같습니다.
하지만 두 가지의 방법 모두 공통적으로 지닌 강점이 있는데요.
바로 NullPointerException을 발생시키지 않는다는 점입니다.
위에서 제시한 1~3번 모두 문자열의 null처리를 추가로 작성해줬었죠?
하지만 StringUtils를 이용하면 그럴 필요가 없습니다.
```