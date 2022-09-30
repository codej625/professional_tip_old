# 스트링을 타임스탬프로 변환하기

```java
SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

String date  = "2022-10-01 00:00:00";
Date dateSDF = simpleDateFormat.parse(date);
long dateL   = dateSDF.getTime();

System.out.println("unix_timestamp => " + dateL);
```