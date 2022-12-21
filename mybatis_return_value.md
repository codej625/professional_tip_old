# mybatis의 ibatis와 리턴 결과 차이를 알아보자!

|iBATIS|iBATIS|MyBatis|
|------|------|------|
|SELECT|SELECT result|SELECT result|
|INSERT|NULL|1(다중 INSERT도 마찬가지)|
|UPDATE|1|UPDATE된 행의 갯수 return(없으면 0)|
|DELETE|DELETE된 행의 갯수|DELETE된 행의 갯수(없으면 0)|