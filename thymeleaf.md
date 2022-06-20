# thymeleaf include를 대체하는 권장 방법

## 방법
1. 먼저 include에 사용할 html부분을 태그로 감싼다. (일반적으로 block를 사용)
ex) 
  <th:block th:fragment="test">
    <div>...내용</div>
  <th:block/>

2. 메인 html에 불러올 th:replace="html 경로" 와 fragment를 적어준다.
<th:block th:replace="/xx/xxx/list_chart2 :: test"></th:block>