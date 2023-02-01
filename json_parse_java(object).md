# json 형식의 String을 DTO에 맵핑해보자!


예제에서는 엑셀 파일을 받아 여러 JSON을 array 담고 string으로 변환하여 Axios를 사용하여 비동기 전송한다.
```javascript
//==============================================================================================
function readExcel(e) {
	const input = e.target,
	// 비동기적으로 파일의 내용을 읽어 들이는 데 사용되는 객체.
	fileReader = new FileReader();
	
	// 첫번째 파일을 바이너리로 읽어들인다.
	fileReader.readAsBinaryString(input.files[0]);
	// load 이벤트의 핸들러. 이 이벤트는 읽기 동작이 성공적으로 완료되었을 때마다 발생한다.
	fileReader.onload = () => {
		const data = fileReader.result,
		workBook = XLSX.read(data, { type: 'binary' }),
		// excel의 row를 담는다.
		rows = XLSX.utils.sheet_to_json(workBook.Sheets[workBook.SheetNames]);
		
		// map을 사용하여 필요한 필드값만 담은 객체로 새로운 array를 만든다.
		const validJson = rows.map(elements => {
			const obj = {
				바코드구매주문번호: elements.바코드구매주문번호,
				사업자: elements.사업자,
				상품주소: elements.상품주소,
				판매처: elements.판매처
			}
			return obj;
		});
		// test
		console.log(validJson);
		sendExcel(JSON.stringify(validJson));
	};
}
//==============================================================================================
function sendExcel(obj) {
	axios.post('/readExcel', obj, {
		headers: { 'Content-Type': 'application/json' }
	}).then((response) => {
    // 통신 성공시 값 확인
		console.log(response);
	}).catch((err) => {
		console.log('error => ', err);
	});
}
//==============================================================================================
```

axios를 사용하여 전송받은 데이터를 가공할 차례이다.
```java
@PostMapping(value = "/readExcel")
public String readExcel(Model model, @RequestBody String obj) throws Exception {
  log.info(">>> readExcel start >>>");
  // 매핑을 도와줄 ObjectMapper 객체를 생성한다.
  ObjectMapper mapper = new ObjectMapper();
  // ArrayList를 반환하는 Arrays.asList()메소드를 사용하고 인수값으로는 String과 DTO를 대입해준다.
  List<ReleaseDto> dtos = Arrays.asList(mapper.readValue(obj, ReleaseDto[].class));
  
  // test
  for (ReleaseDto releaseDto : dtos) {
    System.out.println("바코드구매주문번호: " + releaseDto.get바코드구매주문번호());
    System.out.println("사업자: " + releaseDto.get사업자());
    System.out.println("상품주소: " + releaseDto.get상품주소());
    System.out.println("판매처: " + releaseDto.get판매처());
    System.out.println("===================================");
  }

  return "통신 성공";
}
```

<br/>

만약 list가 필요없다면 이렇게 간단하게 소스를 작성할 수 있다.
```java
// 1. 스트링에서 DTO로 매핑하기
ReleaseDto dto = mapper.readValue(obj, UserDTO.class);
System.out.println(dto);
```

<br/>

반대로 변환도 가능하다.(객체를 json 스트링으로 변환하기)
```java
String jsonStr = mapper.writeValueAsString(dtos);
System.out.println(jsonStr);
```