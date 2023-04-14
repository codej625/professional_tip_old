# ServletContext, HttpSession, HttpServletRequest 정리


1. ServletContext
```
ServletContext는 웹 애플리케이션의 전역 정보를 저장하고 관리하는 인터페이스입니다. 웹 애플리케이션 당 하나의 ServletContext 객체가 생성되며, 모든 서블릿과 JSP 페이지에서 사용할 수 있습니다.
ServletContext를 사용하여 웹 애플리케이션의 구성 정보를 읽어올 수 있으며, 리소스를 가져오거나 초기화 매개변수를 사용할 수 있습니다.
여러 서블릿 간에 데이터를 공유하기 위해 사용됩니다.
```

2. HttpSession
```
HttpSession은 클라이언트의 세션 정보를 저장하고 관리하는 인터페이스입니다. 각 클라이언트(브라우저)가 웹 애플리케이션에 연결할 때마다 새로운 HttpSession 객체가 생성됩니다.
세션 객체는 일정 시간동안 사용자별 정보를 저장하고 추적할 수 있습니다. 사용자가 로그인한 후 서버에서 인증 정보를 저장하는 데 사용되는 것이 일반적입니다.
세션은 시간 제한이 있으며, 시간이 만료되면 자동으로 종료됩니다.
```

3. HttpServletRequest
```
HttpServletRequest는 클라이언트 요청에 대한 정보를 포함하고 있는 인터페이스입니다. 클라이언트가 서버에 요청을 보낼 때마다 새로운 HttpServletRequest 객체가 생성됩니다.
HttpServletRequest 객체는 요청에 대한 정보를 포함하며, 요청 파라미터, 헤더 정보, 쿠키, 요청 URL, 요청 메소드(GET, POST 등) 등을 제공합니다.
HttpServletRequest는 서블릿이나 JSP 페이지에서 요청에 대한 정보를 처리하거나 응답을 생성하는 데 사용됩니다.
요약하면, ServletContext는 웹 애플리케이션의 전역 정보를 저장하고 관리하는 데 사용되며, HttpSession은 사용자별 세션 정보를 저장하고 관리하는 데 사용되고, HttpServletRequest는 클라이언트의 요청 정보를 저장하고 처리하는 데 사용됩니다.
```

```

|ServletContext|HttpSession|HttpServletRequest|  
|---|---|---|
|생성 : 서버 시작 시|생성 : Client 최초 접속 시|생성 : Client가 요청 시|
|제거 : 서버 중지 시|제거 : Client 접속 종료 시|삭제 : Server가 응답 시|
|web application 이 서비스 중인 동안에는 계속 존재|Client가 접속 중인 동안에만 존재|Request 중인 동안에만 존재|
||로그인/로그아웃|*요청 재지정|
||장바구니 등||