# @RequestMapping의 produces 속성을 이용하여 Response의 Content-Type을 제어할 수 있다.

```
@Consumes : 수신 하고자하는 데이터 포맷을 정의한다.
@Produces : 출력하고자 하는 데이터 포맷을 정의한다.
```

## 최근의 추세는 요청 URl을 통해 응답할 데이터를 정하는 것이 아니라, 요청시 Accept header 를 이용하여 응답 받고자 하는 dataType을 요청 헤더 에 적도록 하고, Content-Type 이란 헤더를 통하여 사용자가 Request Body에 담은 내용을 알리도록 하고 있습니다.

```java
@Controller
public class Test{

	@GetMapping("/test", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
  @ResponseBody
    public String hello(){

    	return "hello";
    }
}
```
## @RequestMapping, @GetMapping 등등 어노테이션 속성으로 "consumes" 이라는 속성이 존재합니다.

## 즉, (서버에서) 이 핸들러에서는 body에 담긴 데이터의 타입이 APPLIACTION_JSON_UTF8일 경우의 요청만을 처리한다는 의미입니다. 따라서 요청시 헤더에 꼭 application/json 이 존재해야합니다.



# 응답 데이터 제한하기(produces,headers)

```java
@PostMapping( value ="/hello", produces = MediaType.TEXT_PLAIN_VALUE)
@ResponseBody
public String hello(){

	return "hello";
    
 }
```
## 위 코드와 같이 @Mapping 언노테이션의 속성으로 produces 를 추가하고 data Type을 지정하면 해당 dataType으로만 사용자에게 응답하겠다는 의미입니다.