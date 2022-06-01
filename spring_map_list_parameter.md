# map으로 parameter 받기
ex01)
api =>
http get방식 전송 => http://localhost:8080/api?data1=100&data2=200&data3=300&data3=400

back-end
@RestController
public class Api {
	@RequestMapping(value = "/api", method = { RequestMethod.POST, RequestMethod.GET })
	public String api(@RequestParam Map<String, String> map, @RequestParam List<String> list) throws Exception {
		
		// Map은 중복되는 parameter를 한개만 담기 때문에 이럴때는 List를 사용한다.
		System.out.println(map);
		
		// List를 통해 data3이라는 parameter를 중복없이 받았다.
		System.out.println(list);
		
		return "true";
	}
}

ex02)
api =>
http post방식 전송 => http://localhost:8080/api
json file => {"test": "test"}

back-end
@RestController
public class Api {
	@RequestMapping(value = "/api", method = { RequestMethod.POST, RequestMethod.GET })
	public String api(@RequestBody Map<String, String> map) throws Exception {

		// @RequestBody 어노테이션을 사용하여 json형식의 파일을 전달 받을 수 있다.
		System.out.println(map);

		return "true";
	}
}

