# 서브쿼리를 알아보자

## 서브쿼리란?
1. select절에 {select, from ,where}을 넣거나,
2. from절에 {select, from ,where}을 넣거나,
3. where에 {select, from ,where} 조건을 넣는 구문을 의미한다.

### select에 넣는 서브쿼리

```
ex) table1, table2 테이블 간 공통값만 매칭하되, table1은 전부 & table2는 gender열만 나오게 하라.
밑에 예시는 join으로 결과를 나타낸것
```

```sql
SELECT A.*, B.gender
FROM   [table1] A
LEFT JOIN [table2] B
ON A.mem_no = B.mem_no;
```
<br>
```
이렇게 left join을 활용한 것을 select절 서브쿼리로 바꿔서 풀면 이렇게 할 수 있다.
```

```sql
SELECT *, (
           SELECT gender
           FROM   [table2] B
           WHERE  B.mem_no = A.mem_no
          ) AS gender
FROM [table1] A;
```

```
즉, table1 테이블은 전체를(A.*), 그리고 table2 테이블의 'gender'컬럼만 떼서 가져오는 것이다.
SELECT절 서브쿼리가 하나의 'gender'컬럼처럼 사용되고 있다.
```