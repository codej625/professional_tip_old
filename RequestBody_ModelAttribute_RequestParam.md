# RequestBody, ModelAttribute, RequestParam 간단 정리

1. RequetParam
1개의 HTTP 파라미터를 얻기 위해 사용되며 기본값을 지정할 수 있음
필수 여부가 true이기 때문에 반드시 필요한 경우가 아니라면 required=false 설정이 필요함

2. RequestBody
Json(application/json) 형태의 HTTP Body 데이터를 MessageConverter를 통해 Java 객체로 변환시킴
기본 생성자로 객체를 만들고, Getter나 Setter 등의 메소드로 필드를 찾아 Reflection으로 값을 설정함

3. ModelAttribute
폼 형태(form)의 HTTP Body와 요청 파라미터들을 객체에 바인딩시킴
기본적으로 생성자로 값이 설정되고, 생성자로 설정되지 않은 필드는 Setter로 설정됨