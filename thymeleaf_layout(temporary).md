# Thymeleaf(Template engine)을 사용하여 Layout을 구성해보자

<br /><br />

```html
<html>
  <header>
  <nav>
  <content>
  <footer>
</html>
```
<br />

* Layout을 왜 사용할까?
---

```
header와 footer가 고정적으로 들어가는
웹에서 각각의 page마다 header, footer를 복사해서 넣어주는 것은 효율적이지 않기 때문에,
반복되는 HTML 코드를 줄이기 위해서 Layout을 사용하게 된다.
```


<br /><br /><br />

2. 프로젝트 구조와 적용 원리
```
fragments directory에는 레이아웃에서 공통적으로 쓸 부분들의 html 코드를 생성한다.

layouts directory의 default_layout.html에는 공통적으로 쓸 header, footer 같은 부분을 적용 및 전체 layout 구조를 세팅하고, page directory는 실제 각각의 page에서는 페이지별로 다른 부분인 content가 적용된다.
```

```
ex)

templates - flagments -> header.html, footer.html
            layouts   -> default_layout.html
            page      -> ...html
```

3. fragments

```html
<html lagn="ko" xmlns:th="http://www.thymeleaf.org">
<!--headerFragment 선언-->
<div th:fragment="headerFragment">
    <h1>Header Section</h1>
</div>
</html>
```

header.html

```html
<html lagn="ko" xmlns:th="http://www.thymeleaf.org">
<!--footerFragment 선언-->
<div th:fragment="footerFragment">
    <h1>Footer Section</h1>
</div>
</html>
```
footer.html

<br><br><br>

```
xmlns:th="http://www.thymeleaf.org"
=> Thymeleaf를 사용한다는 선언으로 html 태그에 xmlns:th="http://www.thymeleaf.org"를 추가합니다.

 

th:fragment="fragment명"
=> th:fragment는 해당 부분을 fragment로 선언한다는 의미입니다.
```

4. layout

```html
<!DOCTYPE html>
<html lagn="ko"
      xmlns:th="http://www.thymeleaf.org"
      xmlns:layout="http://www.ultraq.net.nz/thymeleaf/layout">
<head>
    <meta charset="UTF-8" />
    <title>thymeleaf layout</title>
    <meta name="viewport" content="width=device-width, maximum-scale=1.0, minimum-scale=1, user-scalable=yes,initial-scale=1.0" />
    <!-- content script -->
    <th:block layout:fragment="css"></th:block>
    <!-- content script -->
    <th:block layout:fragment="script"></th:block>
</head>
<body>
    <!-- header fragment 사용 -->
    <th:block th:replace="fragments/header :: headerFragment"></th:block>
    <!-- content fragment 사용 -->
    <th:block layout:fragment="content"></th:block>
    <!-- footer fragment 사용 -->
    <th:block th:replace="fragments/footer :: footerFragment"></th:block>
</body>
</html>
```

```
default_layout.html 파일에서는 html에 xmlns:layout="http://www.ultraq.net.nz/thymeleaf/layout"를 추가합니다.

 

<th:block layout:fragment="css"></th:block>

<th:block layout:fragment="script"></th:block>

=> 각각의 content에서만 적용되는 css, js가 이 부분에서 적용되며,

 

<th:block th:replace="fragments/header :: headerFragment"></th:block>

<th:block th:replace="fragments/footer :: footerFragment"></th:block>

=> th:replace="fragment 위치 :: fragment명"을 호출하여 fragment를 불러올 수 있습니다.

(th:replace는 해당 태그 부분을 fragment로 선언한 코드로 바꾼다는 의미입니다.)
```

5. page

```html
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml"
      xmlns:th="http://www.thymeleaf.org"
      xmlns:layout="http://www.ultraq.net.nz/thymeleaf/layout"
      layout:decorate="~{layouts/default_layout}">

<!-- index.html 고유 CSS 추가 -->
<th:block layout:fragment="css">
    <!--    <link rel="stylesheet" th:href="@{/css/page/home.css}" >-->
</th:block>
<!-- index.html 고유 스크립트 추가 -->
<th:block layout:fragment="script">
    <!--    <script th:src="@{/js/page/home.js}"></script>-->
</th:block>

<!-- Content -->
<div layout:fragment="content">
    <h1>Content</h1>
</div>
</html>
```

```
각각의 content에는 html에 layout:decorate="~{layouts/default_layout}"가 추가되어야 합니다.

 

<th:block layout:fragment="css">

<th:block layout:fragment="script">

<div layout:fragment="content">

=> 부분을 통해 각각의 페이지에서 적용되는 css, js와 페이지의 content를 적용하게 됩니다.
```

6. controller ex)

```java
@Controller
@RequestMapping("/")
public class layoutController {

  @getMapping("") {
  public ModelAndView main() {
    ModelAndView model = new ModelAndView();
    model.setViewName("page/main");
    
    return model;
  }
  }
}
```




