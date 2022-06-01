front-end(javascript)

$(function() {
	// request parameter를 받기 위해 URL객체 생성
	// back-end단에서도 동일한 작업이 가능
	const url = new URL(sendUrl);
	
	// url속에 parameter를 찾음 
	const urlParams = url.searchParams;

	// source라는 이름의 parameter 값을 get
	const getParameter = urlParams.get('source');	

	// cur_page_url => controller에서 넘긴 string value 
	const pageType = cur_page_url;
	
	// category => navbar를 click시 가져오는 text
	const category = $("li[data-nav=" + pageType + "]").parents().parents().children("h3").text();
	
	// api_url => controller에서 넘긴 string value + url parameter를 얻기 위한 윈도우 함수 location.search
	const sendUrl = api_url + window.location.search;
	
	// 객체를 담기위한 sendData
	const sendData = {};

	// code라는 key를 갖고 value에는 #submit_form이라는 아이디를 가진 form속에 input태그중 ex_code라는 이름을 가진 input값을 가져옴
	sendData["code"] = $('#submit_form input[name="ex_code"]').val();

	// 위에서 받아온 navbar의 value를 sendData에 대입
	sendData["category"] = category;
	
	// request parameter
	sendData["source"] = getParameter;

	// console.log test
	console.log("category  -> ", category);
	console.log("url       -> ", sendUrl);
	console.log("sendData  -> ", JSON.stringify(sendData));
	console.log("parameter -> ", tag);
	
	$.ajax({
		url : sendUrl,
		type : "post",
		dataType : "json",
		data : sendData,
		success : function(result) {
		// ajax success시 수행할 함수
		},
		error: function(){
		// ajax error시 수행할 함수
		}
	});
});

back-end(java_spring)

@CrossOrigin

// ajax에서 요청한 url을 받아준다 ▼
@PostMapping(value = "           ")
public Map<String, Object> log(HttpServletRequest request, HttpServletResponse response, //
        @RequestParam(value = "code") String attrCode, //
        @RequestParam(value = "category") String category, //
        @RequestParam(value = "source", required = false) String urlParameter, //
) throws Exception {

    log.debug(">> log controller start  >>");

    // 날짜 기록을 위한 Data() 객체 생성
    Date now = new Date();

    ObjectDO objectDO = ObjectMapper.getCode(code);

    LogDO logDO = new LogDO();
    {
        logDO.setAttrCode(attrCode);
        logDO.setCategory(category);
        logDO.setCampaignCode(urlParameter);

        // request 객체에서 getParameter()함수를 사용하여 값을 가져올수도 있음
        logDO.setParameter((request.getParameter("") != null) ? request.getParameter("") : ''); 
        
        // db insert
        LogMapper.putLog(logDO);
    }
    
    // return value
    HashMap<String, Object> result = new HashMap<>();
    ret.put("time", now.getTime());
    
    // ajax 전송(비동기 통신)
    return result;      
}

