# mybatis 에서 join을 해보자!

```
DTO
```
```Java
@Getter
@Setter
public class SubDO {
	private int seq;
	private String product; 
	private String brand;
}

public class MainDO {
	private int main_seq;
	private String main_product; 
	private String brand;
  private SubDO subDO;
}
```

<br/>

```
XML
```
```XML
<resultMap type="SubDO" id="SubMap">
  <result property="seq" column="seq"/>
  <result property="product" column="product"/>
  <result property="brand" column="brand"/>
</resultMap>

<resultMap type="MainDO" id="MainMap">
  <result property="main_seq" column="main_seq"/>
  <result property="main_product" column="main_product"/>
  <result property="status" column="status"/>
  <collection property="subDO" resultMap="SubMap"/>
</resultMap>

<select id="join" parameterType="String" resultMap="UserOrderListMap">
  SELECT A.*, B.product, B.brand
  FROM (
    SELECT *
    FROM user
  ) A
  INNER JOIN map B
  ON A.main_product = B.product
</select>
```