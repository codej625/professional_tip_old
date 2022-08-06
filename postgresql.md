# postgresql 명령어 모음

```
/* select */
select * from 'table_name';

/* insert */
insert into 'table_name'(title, content, created_on) values('', '', NOW());

/* delete */
delete from 'table_name' where seq = 0;

/* seq 초기화 */
ALTER SEQUENCE 'sequence_name' RESTART WITH 1
```