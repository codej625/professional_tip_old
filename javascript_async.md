# 📒 js에서 비동기 통신 실습 및 함수 문법

## 📖 소개

- 21-11-06 부천 스터디 발표 준비 자료
- 두가지 주제로 발표
    - 자바스크립트에서 ajax, axios, fetch에 대한 간단한 설명과 사용방법
    - 자바스크립트에서 1급 객체로서의 함수 관련 문법 몇가지

## 📖 목차

1. 소개
1. js 비동기 통신 개념 정리
1. 비동기 통신 실습
1. 객체로서의 js 함수 문법
1. 참고

## 📖 js 비동기 통신 개념 정리

### js에서 비동기 통신이란?

- 새로고침(전체 페이지 갱신)없이 통신한다.
- Ajax(Asynchronous JavaScript And XML)

### js에서 비동기 통신하는 방법은?

- XMLHttpRequest 객체 이용
- jQuery 라이브러리 이용
- axios 라이브러리 이용
- fetch API 이용

### XMLHttpRequest

- 비동기 통신을 하기 복잡하다.

### jquery

- promise기반이 아니다.

### axios

- node.js와 브라우저를 위한 http통신 javascript 라이브러리
- JSON 데이터 자동 변환
- Promise 기반
- 브라우저 호환성 뛰어나다.
- 에러핸들링이 쉽다고 한다.
- 많이 사용한다고 한다.
- npm에서 axios를 설치해서 사용하지 않고 cdn으로도 사용 가능

```html
<script src="https://unpkg.com/axios/dist/axios.min.js"></script>
```

### fetch

- ES6부터 js의 내장 라이브러리
- Promise 기반
- json으로 변환해주는 과정이 필요하다.

## 📖 비동기 통신 실습

### 실습 환경

IDE

- Visual Studio Code
    - Live Server Extension

REST API 사이트

- https://reqres.in/api/

### jQuery

```html
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <script src="http://code.jquery.com/jquery-latest.min.js"></script>
</head>

<body>
    <!-- GET -->
    <script>
        function funcGet() {
            $.ajax({
                url: 'https://reqres.in/api/users/2',
                type: 'GET',
                success: function onData(res) {
                    console.log(res);
                    console.log(res.data.email);
                    let div = document.getElementById("get_result");
                    div.innerHTML = JSON.stringify(res);
                    // div.innerHTML = res.data.email;
                },
                error: function onError(error) {
                    console.error(error);
                }
            });
        }

    </script>
    <h3>get_result</h3>
    <div id="get_result">

    </div>
    <button type="button" onclick="funcGet();">get</button>

    <!-- POST -->
    <script>
        function funcPost() {
            $.ajax({
                url: 'https://reqres.in/api/users',
                type: 'POST',
                data: {
                    name: document.getElementById("post_name").value,
                    job: "leader"
                },
                success: function onData(res) {
                    console.log(res);
                    console.log(res.id);
                    let div = document.getElementById("post_result");
                    div.innerHTML = JSON.stringify(res);
                },
                error: function onError(error) {
                    console.error(error);
                }
            });
        }
    </script>
    <h3>post_result</h3>
    <div id="post_result">

    </div>
    name: <input type="text" id="post_name" name="name"><br>
    <button type="button" onclick="funcPost();">post</button>
</body>

</html>
```

### axios

```html
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <script src="https://unpkg.com/axios/dist/axios.min.js"></script>
</head>

<body>
    <!-- GET -->
    <script>
        function funcGet() {
            axios({
                method: 'get',
                url: 'https://reqres.in/api/users/2',
                responseType: 'json'

            })
                .then(res => {
                console.log(res);
                console.log(res.data.data.email);
                let div = document.getElementById("get_result");
                div.innerHTML = JSON.stringify(res);
                // div.innerHTML = res.data.email;
            })
            .catch(error => {
                console.log(error);
            });
        } 

    </script>
    <h3>get_result</h3>
    <div id="get_result">

    </div>
    <button type="button" onclick="funcGet();">get</button>

    <!-- POST -->
    <script>
        function funcPost() {
            axios({
                method: 'post',
                url: 'https://reqres.in/api/users',
                data: {
                    name : document.getElementById("post_name").value,
                    job : "leader"
                },
                responseType: 'json'

            })
            .then(res => {
                console.log(res);
                console.log(res.data.id);
                let div = document.getElementById("post_result");
                div.innerHTML = JSON.stringify(res);
            })
            .catch(error => {
                console.log(error);
            });
        }
    </script>
    <h3>post_result</h3>
    <div id="post_result">

    </div>
    name: <input type="text" id="post_name" name="name"><br>
    <button type="button" onclick="funcPost();">post</button>    
</body>
</html>
```

### fetch

```html
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <script src="https://unpkg.com/axios/dist/axios.min.js"></script>
</head>

<body>
    <!-- GET -->
    <script>
        function funcGet() {
            fetch("https://reqres.in/api/users/2", {
                method: "GET",
                headers: {
                    "Content-Type": "application/json"
                }
            })
                .then(res => res.json())
                .then(res => {
                    console.log(res);
                    console.log(res.data.email);
                    let div = document.getElementById("get_result");
                    div.innerHTML = JSON.stringify(res);
                })
                .catch((error) => {
                    console.log(error);
                });
        }
    </script>
    <h3>get_result</h3>
    <div id="get_result">

    </div>
    <button type="button" onclick="funcGet();">get</button>

    <!-- POST -->
    <script>
        function funcPost() {
            fetch("https://reqres.in/api/users", {
                method: "POST",
                headers: {
                    "Content-Type": "application/json"
                },
                body: JSON.stringify({
                    name: document.getElementById("post_name").value,
                    job: "leader"
                })
            })
                .then(res => res.json())
                .then(res => {
                    console.log(res);
                    console.log(res.id);
                    let div = document.getElementById("post_result");
                    div.innerHTML = JSON.stringify(res);
                })
                .catch((error) => {
                    console.log(error);
                });
        }
    </script>
    <h3>post_result</h3>
    <div id="post_result">

    </div>
    name: <input type="text" id="post_name" name="name"><br>
    <button type="button" onclick="funcPost();">post</button>
</body>
</html>
```

### 🔍 then ⇒ async, await으로 변경

then

```jsx
<!-- GET -->
<script>
    function funcGet() {
        fetch("https://reqres.in/api/users/2", {
            method: "GET",
            headers: {
                "Content-Type": "application/json"
            }
        })
            .then(res => res.json())
            .then(res => {
                console.log(res);
                console.log(res.data.email);
                let div = document.getElementById("get_result");
                div.innerHTML = JSON.stringify(res);
            })
            .catch((error) => {
                console.log(error);
            });
    }
</script>
```

async, await

```jsx
<!-- GET -->
<script>
    async function funcGet() {
        let response = await fetch("https://reqres.in/api/users/2", {
            method: "GET",
            headers: {
                "Content-Type": "application/json"
            }
        });
        let response2 = await (response.json());
        console.log(response2);
        console.log(response2.data.email);
        let div = document.getElementById("get_result");
        div.innerHTML = JSON.stringify(response2);
    }
</script>
```

## 📖 객체로서의 js 함수 문법

### 1급객체란?

다음 3가지 조건을 충족하는 객체이다.

1. 변수나 데이터에 할당할 수 있어야 한다.
2. 객체의 인자로 넘길 수 있어야 한다.
3. 객체의 리턴값으로 리턴할 수 있어야 한다.

### 자바와 자바스크립트 비교

자바

- 함수를 객체에 담을 수 없다.

```java
public class FirstClassCitizen {
	public static void func() {
		System.out.println("hi");
	}
	
	public static void main(String[] args) {
		// Object a = func;
	}
}
```

자바스크립트

- 함수를 객체에 담을 수 있다.

```jsx
<script>
    function func(a) {
        console.log(a);
        return func
    }
    func('hi');
</script>
```

### 함수 관련 js 문법 예시

```jsx
<script>
    function func(a) {
        console.log(a);
        return func
    }
    func('hi')('bye')('hahaha');
</script>
```

```jsx
<script>
    const isDelivery = obj => flag => ({...obj, "deliver":flag});
    const chooseMenu = obj => menu => isDelivery({...obj, menu});
    const chooseRestaurant = name => chooseMenu({name});
    console.log(chooseRestarant("중국집")("짜장면")(true));
</script>
```

## 📖 참고

[[개발상식] Ajax와 Axios 그리고 fetch](https://velog.io/@kysung95/%EA%B0%9C%EB%B0%9C%EC%83%81%EC%8B%9D-Ajax%EC%99%80-Axios-%EA%B7%B8%EB%A6%AC%EA%B3%A0-fetch)

[Reqres - A hosted REST-API ready to respond to your AJAX requests](https://reqres.in/)
