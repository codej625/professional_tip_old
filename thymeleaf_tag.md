# 타임리프 태그를 사용해보자!

```html

/* block 사용시 기존 태그없이 사용이 가능 */
<th:block th:utext="${bannerChange}"></th:block>

/* img태그도 이런식으로 활용가능 */
<img th:src="${pageTexts.pc_first_img}" th:alt="${pageImgs.pc_first_img}" />

/* forEach문 */
<th:block th:each="item: ${DTO}">
    <th:block th:if="${item.position == 1}">
        <th:block th:utext="${item.contentHtml}"></th:block>
    </th:block>
</th:block>
```
