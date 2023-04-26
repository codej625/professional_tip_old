# Mybatis에서 LIKE 사용법을 알아보자!

1. [PostgreSQL] [MySQL] 
```sql
AND title LIKE CONCAT('%', #{param}, '%')
```

2. [Oracle] 
```sql
AND title LIKE '%' || #{param} || '%'
```

3. [Mssql]
```sql
AND title LIKE '%' + #{param} + '%'
```