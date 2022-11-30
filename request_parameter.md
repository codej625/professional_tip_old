# HttpServletRequest 객체를 사용하여 파라미터를 활용해보자!


```java
// Simple Logging Facade for Java
@Slf4j
@Service
public class ParameterSearch extends ModelAndView {

  @Autowired
  LotteInsurancePageTextsMapper mapper;

  public String getParams(String viewName, HttpServletRequest request, Device device) throws Exception {
    /**
     * @param String   paramKey     => db에서 image를 조회하기 위한 key값
     * @param String   view         => controller에서 넘어온 viewName
     * @param String   paramCode    => query string에서 utm_campaign값을 가져온 값(상품명)
     * @param String   seq          => createSeq() mathod의 return값을 담기 위한 변수
     * @param String   returnImgTag => 최종적으로 만들어진 tag 이름을 담아 return하기 위한 변수
     * 
     * @param boolean  toggle       => device 변경시 pc, mobile 이미지 전환을 위한 toggle 변수
     * 
     * @param HashMap<String, String> result         => db에서 조회한 값들을 담기위한 hashMap 
     * @param HashMap<String, String> dbSelectParams => db를 조회하기 위한 hashMap
     */
    log.info(">>> getParams method start >>>");
    
    /* String Type Variable */
    String seq          = null;
    String view         = viewName.replaceAll("/", "");
    String paramKey     = request.getParameter("img_key");
    String paramCode    = request.getParameter("utm_campaign");
    String returnImgTag = null;
    boolean toggle;
    
    /* Map Type Variable */
    HashMap<String, String> result         = null;
    HashMap<String, String> dbSelectParams = new HashMap<>();

    /* NullPointException 처리를 위한 try-catch문 */
    try {
      /* paramCode(상품명)을 사용하여 seq 생성 */
      seq = createSeq(paramCode);
      /* 필수 파라미터, 컨트롤러에서 넘겨받은 viewName과 비교한다. */
      paramsException(paramKey, paramCode, view, seq);
      
      log.info(">>> parameter true >>>");
      
      /* 디바이스 접속환경을 고려하여 기본 이미지를 찾는다. */
      if (device.isMobile() || device.isTablet()) {
        toggle = false;
        log.info(">>> is pc {} >>>", toggle);
      } else {
        toggle = true;
        log.info(">>> is pc {} >>>", toggle);
      }
      /* device 변환이 일어났을때 환경에 맞는 이미지 전환 */
      paramKey = changeImg(paramKey, toggle);
      /* 조회를 위한 seq, img_key */
      dbSelectParams.put("seq", seq);
      dbSelectParams.put("img_key", paramKey);
      /* 위에 파라미터를 hashMap에 담아서 db select */
      result       = mapper.imgChange(dbSelectParams);
      /* createImg() method를 사용하여 fornt에서 사용 될 tag를 완성시킨다. */
      returnImgTag = createImg(result);
      /* 최종적으로 만들어진 태그를 리턴 */
      return returnImgTag;
      
    /* 파라미터가 null인 경우 */
    } catch (Exception e) {
        
      log.info(">>> parameter false >>>");
      
      /* paramCode(상품명)을 사용하여 seq 생성 */
      seq = createSeq(view);
      /* 조회를 위한 seq를 hashMap에 담는다. */
      dbSelectParams.put("seq", seq);
      /* 디바이스 접속환경을 고려하여 기본 이미지를 찾는다. */
      if (device.isMobile() || device.isTablet()) {
        log.info(">>> is mobile >>>");
        dbSelectParams.put("img_key", "mobile_main_img");
      } else {
        log.info(">>> is pc >>>");
        dbSelectParams.put("img_key", "pc_main_img");
      }
      /* default img select */
      result = mapper.originalImg(dbSelectParams);
      /* createImg() method를 사용하여 fornt에서 사용 될 tag를 완성시킨다. */
      returnImgTag = createImg(result);
    }
    /* 최종적으로 만들어진 태그를 리턴 */
    return returnImgTag;
  }
//=============================================================================
  private String createSeq(String seq) throws Exception {
      
    log.info(">>> createSeq method start >>>");
      
    String _seq = null;
    /* 상품이름으로 seq 만들기 */
    switch (seq) {
      case "test1"  : _seq = "3";  break;
      case "test2"  : _seq = "7";  break;
      case "test3"  : _seq = "8";  break;
      case "test4"  : _seq = "14"; break;
      case "test5"  : _seq = "15"; break;
      case "test6"  : _seq = "16"; break;
      case "test7"  : _seq = "17"; break;
      case "test8"  : _seq = "18"; break;
      case "test9"  : _seq = "19"; break;
      case "test10" : _seq = "24"; break;
      case "test11" : _seq = "25"; break;
      default: log.info("error");      break;
    }
    return _seq;
  }
//=============================================================================
  private String createImg(HashMap<String, String> result) throws Exception {
      
    log.info(">>> createImg method start >>>");
    
    /* front에서 사용할 tag를 생성 */
    StringBuilder addParams = new StringBuilder();
    addParams.append("<img ");
    addParams.append("class=");
    addParams.append("'{className}' ");
    addParams.append("src='");
    addParams.append(result.get("{imgName}")); // img 넣기
    addParams.append("' ");
    addParams.append("alt='");
    addParams.append(result.get("alt")); // alt 넣기
    addParams.append("' ");
    addParams.append("/>");
    /* 스트링 빌더 패턴을 사용하여 이어붙인 값을 리턴 */
    return addParams.toString();
  }
//=============================================================================
  private void paramsException(String paramKey,//
                               String paramCode,// 
                               String view,// 
                               String seq) throws Exception {
    
    log.info(">>> paramsException method start >>>");
    
    /* img_key값이 null이 아니면 */
    if (!paramKey.equals(null)) {
      log.info(">>> img_key is not null >>>");
      /* 내용이 ""라면 */
      if (paramKey.equals("")) {
        log.info(">>> img_key is \"\" >>>");
        /* 예외처리 던지기 */
        throw new Exception();
      /* 검색해서 없으면 */
      } else {
        HashMap<String, Object> params = new HashMap<>();
        params.put("key", paramKey);
        params.put("seq", seq);
        
        String result = mapper.selectImg(params);
        if (result == null) {
          log.info(">>> image search fail >>>");
          throw new Exception();
        }
      }
    }
    /* utm_campaign값이 null이 아니면 */
    if (!paramCode.equals(null)) {
      log.info(">>> utm_campaign is not null >>>");
      /* 내용이 ""라면 */
      if (paramCode.equals("")) {
        log.info(">>> utm_campaign is \"\" >>>");
        /* 예외처리 던지기 */
        throw new Exception();
      } else {
        /* utm_campaign 비교 */
        int count = 0;
        for (String campaign : Constants.UTM_CAMPAIGN_ARRAY) {
          if (campaign.equals(paramCode)) {
            count = 1;
          }
        }
        if (count < 1) {
          log.info(">>> view x >>>");
          throw new Exception();
        }
      }
    }
    /* view name != utm_campaign이면 */
    if (!view.equals(paramCode)) {
      log.info(">>> view name is inconsistency >>>");
      throw new Exception();
    }
  }
//=============================================================================
  private String changeImg(String paramKey, boolean toggle) throws Exception {
    
    log.info(">>> changeImg method start >>>");
    
    String underbar        = "_";
    String[] changeDevice  = paramKey.split(underbar);
    
    StringBuilder addParam = new StringBuilder();
    if (toggle == false) {
      addParam.append("mobile_");
    } else {
      addParam.append("pc_");
    }
    addParam.append(changeDevice[1] + underbar);
    addParam.append(changeDevice[2] + underbar);
    addParam.append(changeDevice[3]);
    
    return addParam.toString();
  }
//=============================================================================
}
```
