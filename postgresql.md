# postgresql 명령어 모음

```
/* select */
select * from 'table_name';

/* insert */
insert into 'table_name'(title, content, created_on) values('', '', NOW());
ex) CREATE TABLE name
(
	id SERIAL PRIMARY KEY,
	name VARCHAR(50) UNIQUE NOT NULL,
	password VARCHAR(50) NOT NULL,
	email VARCHAR(355) UNIQUE NOT NULL,
	created_on TIMESTAMP NOT NULL,
	last_login TIMESTAMP
);

/* delete */
delete from 'table_name' where seq = 0;

/* seq 초기화 */
ALTER SEQUENCE 'sequence_name' RESTART WITH 1
or
SELECT setval('t_hklife_record_seq_seq', 1, false);

```