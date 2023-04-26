# Mybatis에서 LIKE 사용법을 알아보자!

1. [PostgreSQL] [MySQL] 
```sql
AND title LIKE CONCAT('%', #{param}, '%')
```
```
다른 DB는 테스트 안 해봤지만 PostgreSQL에서는 차라리 변수값에 "%"+변수+"%" 이렇게 처리를 하고 CONCAT없이 #{변수}를 사용하는게 적합하다.
```


2. [Oracle] 
```sql
AND title LIKE '%' || #{param} || '%'
```

3. [Mssql]
```sql
AND title LIKE '%' + #{param} + '%'
```