# @RequestBody, @RequestParam 이해하기!

```java
@Controller
public class UserController {

	@PostMapping("/receive")
	public String age(@RequestParam String name) {
		System.out.println("통신 성공");
		System.out.println(">>> " + name);
		return "index";
	}
}

@Controller
public class UserController {

	@PostMapping("/receive")
	public String age(@RequestBody String name) {
		System.out.println("통신 성공");
		System.out.println(">>> " + name);
		return "index";
	}
}
```
```
두 방식의 차이점에 대해서 알아보려고 한다.
```

<br/>
```
먼저 기본적인 형태인 <form> 태그를 통해서 데이터를 전송해보자
```

```java
@Controller
public class UserController {

	@PostMapping("/receive")
	public String age(@RequestBody String req) {
		System.out.println("통신 성공");
		System.out.println(">>> " + req);
		return "result";
	}
}
//출력 결과
//통신 성공
//>>> name=jun&age=13
```
```
우리가 입력한 'jun' 이라는 이름과 '13' 이라는 나이가 잘 전달이 되었지만 단지 'name=jun&age=13' 이라는 String 으로 전달되어 전달된 데이터를 사용하기에는 불편함이 있다.
그렇다면 이번엔 @RequestParam 으로 받아보자.
```
<br/>

```java
@Controller
public class UserController {

	@PostMapping("/receive")
	public String age(@RequestParam String name) {
		System.out.println("통신 성공");
		System.out.println(">>> " + name);
		return "result";
	}
}
//출력 결과
//통신 성공
//>>> jun
```
```
@RequestBody 로 데이터를 받을 때는 메서드의 변수명이 상관이 없었지만, @RequestParam 으로 데이터를 받을때는 데이터를 저장하는 이름으로 메서드의 변수명을 설정해주어야 한다.
결과적으로 jun 이라는 이름이 잘 전달이 되었고, 이번엔 name 이라는 변수에 할당이 되어 사용하기에도 용이하다.
```
<br/>

```
Json형식으로 데이터를 전달
```
```
이번에는 ajax를 이용하여 데이터를 전달해보도록 하겠다.
ajax 는 Asynchronous JavaScript and XML 의 약자로서 비동기적으로 서버와 브라우저가 데이터를 주고 받는 방식이다.
대표적인 예로 javaScript 의 fetch API 가 있다.
많은 사람들이 사용하는 방법인데, 이번엔 fetch API 로 데이터를 전송하고 데이터를 받아보자.
```

```javascript
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>
<button id="postData">
    데이터 전송
</button>
<script>
    const $postDataButton = document.querySelector("#postData")

    const postData = event => {
        console.log("데이터 전송");

        fetch("/receive", {
            method: 'post',
            headers: {
                'content-type': 'application/json'
            },
            body : JSON.stringify({
                name : "jun",
                age : "13"
            })
        })
    }

    $postDataButton.addEventListener("click", postData);
</script>
</body>
</html>
```
```
'데이터 전송' 버튼을 누르면 
'/receive' 주소로,
post 방식으로,
{name : "jun",
age : "13}
이라는 데이터가 Json의 형태로 전송이 된다.
이 데이터를 @RequestParam 을 이용하여 받아보자.
```

<br/>

```java
@Controller
public class UserController {

	@PostMapping("/receive")
	public String age(@RequestParam String name) {
		System.out.println("통신 성공");
		System.out.println(">>> " + name);
		return "result";
	}
}
```
```
위와 같이 컨트롤러를 구현한 후 데이터를 전송해보았지만 에러가 발생하였다.
```
```
MissingServletRequestParameterException: Required String parameter 'name' is not present

name 이라는 파라미터가 없다고 한다.
그 이유는 기본적으로 @RequestParam 은 url 상에서 데이터를 찾기 때문이다.
우리가 위에서 <form> 태그를 이용하여 데이터를 입력하고 제출 버튼을 누르면 입력한 데이터들이 url을 통해서 전달된다.

예를 들면 'http://localhost:8080/receive?name=jun&age=13' 이런 식이다.
반면에 Json형식으로 데이터를 전달할때는, url은 http://localhost:8080/receive로 변함이 없고 body에 데이터를 포함하여 전송하기 때문에 @RequestParam 으로는 받을 수 없는 것이다.

그렇다면 한번 fetch API 로 데이터를 전달하되 전송하는 주소를 /receive?name=jun&age=13 으로 변경하고 전달해보면 어떻게 될까?
```
```
//출력 결과
//통신 성공
//>>> jun
정상적으로 name 변수에 jun 이 할당이 되었다.
```

<br/>

```
이제 다시 본론으로 돌아와 '/receive' 주소로 데이터를 전송하고 @RequestBody 로 데이터를 받아보자.
```
```java
@Controller
public class UserController {

	@PostMapping("/receive")
	public String age(@RequestBody String req) {
		System.out.println("통신 성공");
		System.out.println(">>> " + req);
		return "result";
	}
}
//출력 결과
//통신 성공
//>>> {"name":"jun","age":"13"}
```
```
<form> 태그로 데이터를 전달하고 @RequestBody 로 받았을 때와 차이가 없어 보인다.
하지만 여기에는 큰 차이가 있다.
```
<br/>

```
자동 객체 생성
```
```
만약 다음과 같이 name 과 age 를 필요로 하는 Person 클래스가 있고 getter 가 구현되어 있다면
```

```java
public class Person {
    private String name;
    private int age;

    public Person() {

    }

    public Person(final String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
```
```
다음과 같은 기능이 가능하다.
```
```java
@Controller
public class UserController {

	@PostMapping("/receive")
	public String age(@RequestBody Person person) {
		System.out.println("통신 성공");
		System.out.println(">>> " + person);
		return "result";
	}
}

//출력 결과
//통신 성공
//>>> Person{name='jun', age=13}
```
```
신기하게도 Person 객체를 자동으로 생성해 주었다.
@RequestBody 가 아닌 @RequestParam 을 이용한다면 불가능하다. 한번 시도해보자.
```
<br/>

```
비동기통신에서 @RequestParam 으로 데이터를 전달받기 위해 /receive?name=jun&age=13 의 주소로 데이터를 전송하고 @RequestParam 으로 데이터를 받아 같은 행동을 취했지만 에러가 발생하는 모습이다.
```
```java
@Controller
public class UserController {

	@PostMapping("/receive")
	public String age(@RequestParam Person person) {
		System.out.println("통신 성공");
		System.out.println(">>> " + person);
		return "result";
	}
}
//출력 결과
// MissingServletRequestParameterException: Required Person parameter 'person' is not present
```

<br/>

```
@RequestBody, @RequestParam 모두 Map<String,String> 으로 결과를 받아올 수도 있다.
```
```java
@Controller
public class UserController {

	@PostMapping("/receive")
	public String age(@RequestBody Map<String,String> map) {
		System.out.println("통신 성공");
		System.out.println(">>> " + map.get("name"));
		System.out.println(">>> " + map.get("age"));
		return "result";
	}
}
//출력 결과
//통신 성공
//>>> jun
//>>> 13
```

<br/>

||@RequestBody|@RequestParam|
|------|---|---|
|객체생성|가능|불가능|
|각 변수별로 데이터 저장|불가능|가능|