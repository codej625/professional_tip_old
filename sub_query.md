# 서브쿼리를 알아보자

## 서브쿼리란?
1. select절에 {select, from ,where}을 넣거나,
2. from절에 {select, from ,where}을 넣거나,
3. where에 {select, from ,where} 조건을 넣는 구문을 의미한다.

### SELECT에 넣는 서브쿼리
<br>

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
<br>

### SELECT 넣는 서브쿼리

```
FROM절에 사용되는 서브쿼리는 하나의 테이블처럼 사용된다.
테이블처럼 사용되므로, 열 이름과 테이블명을 꼭 명시해줘야 한다. FROM절 내 서브쿼리는 괄호를 치고, AS 명시)
```

```
ex)order 테이블에서 회원번호(mem_no)별 주문금액(sales_amt) 합계를 집계하라.
```

```sql
위 질문에 대해서 저번 포스팅에서 배운 GROUP BY를 활용하면 다음과 같다.

SELECT mem_no, SUM(sales_amt) AS tot_amt
FROM   [ORDER]
GROUP BY mem_no
```

```sql
이렇게 쓴 쿼리를 FROM절로 바꾼다면 이렇게 하나의 테이블처럼 만들면 된다.

SELECT mem_no, SUM(sales_amt) AS tot_amt
FROM   (
        SELECT mem_no, SUM(sales_amt) AS tot_amt
        FROM   [ORDER]
        GROUP BY mem_no) A

위에 예시는 극단적인 예시이다.
처음에 썼던 쿼리를 그냥 FROM절 안에 넣고, SELECT문으로는 전체를 호출했기 때문 실제로는 이렇게 쿼리를 짜는 경우가 거의 없다.
```

```
실전에서는 여러 가지 테이블을 조인시켜 가공하고, 1차 가공한 결과물을 또다시 2차 가공하고 하는 경우가 많다.
1차 가공한 결과를 테이블 형태로 다시 저장하면 좋겠지만 이게 불가능한 경우엔 1차 가공물을 FROM절 서브쿼리에 넣어 하나의 테이블로 이용하는 경우가 많다.

그래서 또다른 예시를 살펴보자
ex) 위에서 만든 FROM절 서브쿼리 A를 기준으로, [MEMBER]테이블을 LEFT JOIN하여라.

```

```sql
SELECT *
FROM  (SELECT mem_no, SUM(sales_amt) AS tot_amt
       FROM   [ORDER]
       GROUP BY mem_no) A
LEFT JOIN [MEMBER] B
ON A.mem_no = B.mem_no

실제론 위 쿼리와 같이 사용되는 경우가 많다.(정말 편리한 FROM절 서브쿼리)
```

<br>

### WHERE에 넣는 서브쿼리

```
WHERE절 서브쿼리는 일반 서브쿼리이다.
가장 대표적인 형태이기 때문!

가장 대표적인 예제를 풀어보자.
```

```
ex) [MEMBER] 테이블의 mem_no = '1000005'인 주문내역을 [ORDER] 테이블에서 조회하여라.
```

```sql
SELECT *
FROM   [ORDER]
WHERE  mem_no IN (SELECT mem_no FROM [MEMBER] WHERE mem_no = '100005')
```

```
이렇게 [MEMBER] 테이블의 mem_no를 조회할 때 WHERE절은 유용하게 쓰인다.
위의 예시는 너무 간단하니 한번 더 해보자!
```

```
ex) [MEMBER] 테이블의 gender = 'man'인 주문내역을 [ORDER] 테이블에서 조회하여라.
```

```sql
SELECT *
FROM   [ORDER]
WHERE  mem_no IN (SELECT mem_no FROM [MEMBER] WHERE gender = 'man')
```

```
이는 [MEMBER]와 [ORDER]테이블의 공통열(key)이 mem_no이기 때문에, gender = 'man'인 mem_no를 WHERE절로부터 찾아내 조회 할 수 있었다.
```

```
ex2 실전)
```

```sql
SELECT a.visit_time,
       a.ip,
       a.device,
       a.source_code,
       a.medium_code,
       a.campaign_code,
       a.contents_code,
       a.term_code,
       a.adv_object_code,
       a.adv_object_name,
       a.url,
       b.conversion_type,
       b.visit_time AS req_time
FROM   (SELECT visit_time,
               ip,
               device,
               source_code,
               medium_code,
               campaign_code,
               contents_code,
               term_code,
               adv_object_code,
               adv_object_name,
               url
        FROM   visit_log
        WHERE  visit_time BETWEEN {date_start} AND {date_end}) AS a
        LEFT JOIN (SELECT conversion_type,
                          visit_time
                   FROM  record
                   WHERE visit_time BETWEEN {date_start} AND {date_end}) AS b
ON a.visit_time = b.visit_time
ORDER BY a.visit_time DESC
```

