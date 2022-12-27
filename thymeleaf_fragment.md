# thymeleaf에 대해 알아보자

```
일부 코드를 가져와서 사용하는 방법
템플릿 조각을 이용하기 위해 파일을 우선 만들어 준다.
```

```html
<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
  <body>
    <!-- test01 -->
    <div th:fragment="copy">
      푸터 자리 입니다.
    </div>

    <!-- test02 -->
    <div th:fragment="copyParam (param1, param2)">
        <p>파라미터 자리 입니다.</p>
        <p th:text="${param1}"></p>
        <p th:text="${param2}"></p>
    </div>
  </body>
</html>
```

<br>
<br>

```
insert는 현재 태그를 유지하면서 템플릿 조각을 가져오는 방법이다.
```

```html
<div th:insert="~{fragment/{folder name} :: copy}"></div>

<div>
  <div>
    insert
  </div>
</div>
```

<br>
<br>

```
replace는 말 그대로 '대체'하는 방법이다. 현재 태그를 템플릿 조각에 있는 태그로 대체하는 방법이다.
```

```html
<div th:replace="~{fragment/{folder name}} :: copy}"></div>

<div>
  replace
</div>
```

<br>
<br>

```
템플릿 조각은 파라미터를 전달하여 동적으로 렌더링을 할 수 있다.
```

```html
<div th:replace="~{fragment/{folder name} :: copyParam ('데이터1', '데이터2')}"></div>

<div>
  <p>파라미터 자리 입니다.</p>
  <p>데이터1</p>
  <p>데이터2</p>
</div>
```