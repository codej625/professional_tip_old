# 레프트 조인시 서브쿼리를 사용하여 테이블을 조회하여 성능을 높이자

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
