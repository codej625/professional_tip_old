# table의 여러 가지 행 속의 input 값을 JSON 형식으로 만들어 insert 해보자!

### RestController
```java
// service
@Autowired
TypeCheck typeCheck;

@PostMapping(value = "/sendTable")
public int tableSaveRest(Model model, @RequestBody String json, Map<String, Object> obj) throws Exception {
  log.info(">>> tableSaveRest start >>>");
  
  return testService.tableSave(typeCheck.typeCheck(json));
}
```

<br/>

### DTO
```java
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class OrderListDto { // 주문서(커머스) 테이블
	private int order_seq; // 주문서 순번
	private String mall_type;	// 쇼핑몰		 
	private String order_date; // 주문일시
	private String buyer; // 구매자명
	private String reciever; // 수취인명			  
	private String personal_customs_code; // 개인통관고유부호			 
	private String buyer_number; // 구매자연락처			
	private String reciever_number_1; // 수취인연락처1			
	private String product;	// 상품
	private int quantity; // 수량
	private String item_code; // 판매자상품코드
	private String product_order_number; // 상품주문번호		
	private String order_number; // 주문번호
	private String expected_settlement_amount; // 정산예정금액		
	private String default_address; // 기본배송지
	private String detailed_delivery_address; // 상세배송지		
	private String delivery_message; // 배송메세지
	private String zip_code; // 우편번호
	private String total_order_amount_per_product; // 상품별총주문금액
	private String shipping_method_buyers_request; // 배송방법(구매자요청)
	private String shipping_method;	// 배송방법	
	private String courier_company;	// 택배사	
	private String invoice_number; // 송장번호		
	private String sending_date; // 발송일	
	private String reciever_number_2; // 수취인연락처2		
	private String option_information; // 옵션정보		
	private String option_management_code; // 옵션관리코드	
	private String option_price; // 옵션가격
	private String todays_date; // 오늘날짜
}

```

<br/>

### Service util
```java
@Log4j2
@Service
public class TypeCheck {
//===================================================================================
  public Map<String, Object> typeCheck(String obj) throws Exception {
    log.info(">>> TypeCheck typeCheck service start >>>");
    // mall의 종류
    String mall = obj.substring(obj.length() - 3, obj.length() - 2); //

    return saveUtil(mall, obj);
  }
//===================================================================================
  private Map<String, Object> saveUtil(String mall, String obj) throws Exception {
    log.info(">>> saveUtil method start >>>");
    
    List<OrderListDto> result;
    ObjectMapper mapper = new ObjectMapper(); //
    Map<String, Object> dtos = new HashMap<String, Object>(); //

    switch (mall) {
      case "0":
        result = Arrays.asList(mapper.readValue(obj, OrderListDto[].class));
        dtos.put("result", result); 
        break;
      case "1":
        // ... dto를 변경하여 준다.
        break;
      case "2":
        // ... dto를 변경하여 준다.
        break;
      default: 
        log.info("error"); 
        break;
    }

    return dtos;
  }
//===================================================================================
}
```

<br/>

### Service Interface
```java
public interface tableSaveService {
	public int tableSaveService(Map<String, Object> obj) throws Exception;
}
```

<br/>

### Service impl
```java
@Log4j2
@Service
public class tableSaveServiceImpl implements ReleaseService {
  // service
	@Autowired
	tableSaveMapper mapper;

	@Override
	public int tableSaveService(Map<String, Object> map) throws Exception {
		log.info(">>> tableSave start >>>");

		return mapper.tableSave(map);
	}
}
```

<br/>

### Mapper Interface
```java
@Mapper
@Repository
public interface tableSaveMapper {
	// 1단계 커머스 개별 엑셀을 저장
	public int tableSave(Map<String, Object> obj) throws Exception;
}
```

<br/>

### HTML
```html
<table id="table" class="table table-hover">
  <tr class="table-row">
    <td>
      <input
        class="form-control view-table"
        type="text"
        value=""
      >
    </td>
    <td>
      <input 
        class="form-control view-table"
        type="text"
        value=""
      >
    </td>
  </tr>
</table>
```

<br/>

### HTML
```html
<button
  class="btn btn-primary my-1"
  type="button" 
  onclick="createTable(index);"
>
  저장
</button>`;

<table id="table" class="table table-hover">
  <tr class="table-row">
    <td>
      <input
        class="form-control view-table"
        type="text"
        value=""
      >
    </td>
    <td>
      <input 
        class="form-control view-table"
        type="text"
        value=""
      >
    </td>
    <!-- 중략 -->
  </tr>
  <tr>
  <!-- 중략 -->
  </tr>
    <tr>
  <!-- 중략 -->
  </tr>
</table>
```

<br/>

### script
```javascript
function createTable(data) {
	const input = document.querySelectorAll('.table-row'); //
	let	obj = []; //
		
	input.forEach(elements => {
		obj.push({
			order_date: elements.children[0].children[0].value,
			buyer: elements.children[1].children[0].value,
			reciever: elements.children[2].children[0].value,
			personal_customs_code: elements.children[3].children[0].value,
			buyer_number: elements.children[4].children[0].value,
			reciever_number_1: elements.children[5].children[0].value,
			product: elements.children[6].children[0].value,
			quantity: elements.children[7].children[0].value,
			item_code: elements.children[8].children[0].value,
			product_order_number: elements.children[9].children[0].value,
			order_number: elements.children[10].children[0].value,
			expected_settlement_amount: elements.children[11].children[0].value,
			default_address: elements.children[12].children[0].value,
			detailed_delivery_address: elements.children[13].children[0].value,
			delivery_message: elements.children[14].children[0].value,
			zip_code: elements.children[15].children[0].value,
			total_order_amount_per_product: elements.children[16].children[0].value,
			shipping_method_buyers_request: elements.children[17].children[0].value,
			shipping_method: elements.children[18].children[0].value,
			courier_company: elements.children[19].children[0].value,
			invoice_number: elements.children[20].children[0].value,
			sending_date: elements.children[21].children[0].value,
			reciever_number_2: elements.children[22].children[0].value,
			option_information: elements.children[23].children[0].value,
			option_management_code: elements.children[24].children[0].value,
			option_price: elements.children[25].children[0].value,
			mall_type: data // type index
		});
	});

	return sendTable(obj); // send axios
}
```