# mybatis에 동적쿼리를 사용해보자! (+List 사용방법까지)

1. Mapper parameter => List

```java
@Mapper

void insertList(List<Board> boardList) throws Exception;
```
```xml
<insert id="insertBoard" parameterType="java.util.List">
  INSERT INTO T_BOARD (
                        TITLE
                        , CONTENTS
                        , DISPLAY_YN
                        , EMAIL
                        , REG_ID
                        , REG_DATE
                      ) 
  VALUES (
          <foreach collection="list" item="item" separator=",">
            #{item.title}
            , #{item.contents}
            , #{item.displayYn}
            , #{item.email}
            , #{item.regId}
            , NOW()
          </foreach>
         )
</insert>
```

2. Mapper 파라미터가 Model객체 내 List를 foreach로 활용 할 경우

### DTO
```java
@Data
public class Board implements Serializable  {

  public Integer boardIdx;
  //...생략...
  List<BoardReply> boardReplyList;
}
```
```java
@Mapper
BoardReply selectBoardReply(Board board) throws Exception;
```
```html
<select id="selectBoardReply" parameterType="BoardSearch" resultType="BoardReply">
    SELECT
        BOARD_IDX
        , REPLY_IDX
        , TITLE
        , CONTENTS
    FROM
        T_BOARD_REPLY
    WHERE
        BOARD_IDX = #{boardIdx}
        AND REPLY_IDX NOT IN (
        <foreach collection="boardReplyList" item="item" separator=",">
            #{item.replyIdx}
        </foreach> )
</select>
```

3. Mapper 파라미터가 Map 일 경우

```java
@Override
public void insertEventGoodsMng(Map<String, Object> dbParams) {
  // dbParams={eventIdx=113, cate=24, ...}
  List<Map<String, Object>> goodsList = boardMapper.selectGoodsList(dbParams);
  dbParams.put("goodsList", goodsList);
  eventMapper.insertEventGoodsMng(dbParams);
}
```
```xml
<insert id="insertEventGoodsMng" parameterType="java.util.HashMap">
  <if test="goodsList.size != 0">
    INSERT INTO EVENT_GOODS_MNG
    (
        EVENT_GOODS_MNG_IDX
        , GOODS_IDX
    )
    VALUES
    <foreach collection="goodsList" item="item" separator=",">
    (
        #{eventIdx}
        , #{item.goodsIdx}
    )
    </foreach>
  </if>
</insert>
```