# HttpSession, HttpServletRequest

<br /><br />

* 세션 관리를 위해 사용되는 객체
---

<br />

1. HttpServletRequest
```
HttpServletRequest 인터페이스는 클라이언트로부터 서버로 HTTP 요청을 받는 객체이다.

1) 요청 파라미터 처리
HTTP 요청에서 전달된 파라미터들을 읽어오거나 처리할 수 있다.

2) 헤더 접근
HTTP 요청 헤더 정보를 읽을 수 있다.

3) 세션 관리
세션을 생성하거나 기존 세션에 접근할 수 있는 메서드를 제공. (getSession(), getSession(boolean create) 등)

4) 쿠키 처리
HTTP 쿠키를 읽고 쓸 수 있는 기능을 제공.
```

<br /><br />

2. HttpSession
```
서버 측에서 개별 사용자의 세션을 관리하는 객체이다.

1) 세션 속성 관리
setAttribute(String name, Object value) 메서드를 사용하여 세션에 속성을 저장하거나,
getAttribute(String name) 메서드를 사용하여 속성을 읽어올 수 있다.

2) 세션 ID 관리
각 세션은 고유한 세션 ID를 가지며, 이를 통해 클라이언트와 서버 사이의 상태 유지가 가능.

3) 세션 만료 관리
세션의 유효 시간을 설정하고, 만료시킬 수 있다.

4) 세션 속성 제거
removeAttribute(String name) 메서드를 사용하여 세션에서 특정 속성을 제거할 수 있다.
```

<br /><br />

* 차이점
---

```
역할과 범위
HttpServletRequest는 클라이언트가 보낸 HTTP 요청 전체를 처리하는 인터페이스로,
요청당 하나씩 생성됩니다.
반면 HttpSession은 개별 사용자의 세션 정보를 관리하는 객체로,
여러 요청 간에 상태를 유지한다.

생명 주기
HttpServletRequest는 각 HTTP 요청마다 생성되어 요청을 처리한 후 사라진다.
반면 HttpSession은 클라이언트가 웹 애플리케이션에 접속한 후 세션이 만료될 때까지 유지된다.

접근 방법
HttpServletRequest는 HTTP 요청 객체에서 세션을 얻어오는 메서드를 제공한다.
HttpSession은 세션에 직접적으로 접근하여 속성을 설정하거나 가져올 수 있다.
```
