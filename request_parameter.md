# HttpServletRequest 객체를 사용하여 파라미터를 활용해보자!


```java
// Simple Logging Facade for Java
@Slf4j
@Service
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
       * @param HashMap<String, String> result         => db에서 조회한 값들을 담기위한 hashMap 
       * @param HashMap<String, String> dbSelectParams => db를 조회하기 위한 hashMap
       */
      log.info(">>> getParams method start >>>");
      
      // String Type Variable
      String seq          = null;
      String view         = viewName.replaceAll("/", "");
      String paramKey     = request.getParameter("img_key");
      String paramCode    = request.getParameter("utm_campaign");
      String returnImgTag = null;
      
      // Map Type Variable
      HashMap<String, String> result     = null;
      HashMap<String, String> dbSelectParams = new HashMap<>();

      // NullPointException 처리를 위한 try-catch문 
      try {
        // 필수 파라미터가 있고, 컨트롤러에서 넘겨받은 viewName과 파라미터에서 받아온 코드값을 비교한다.
        if (!paramKey.equals(null) && view.equals(paramCode)) {
          log.info(">>> parameter true >>>");
          // paramCode(상품명)을 사용하여 seq 생성
          seq = createSeq(paramCode);
          // 조회를 위한 seq, img_key
          dbSelectParams.put("seq", seq);
          dbSelectParams.put("img_key", paramKey);
          // 위에 파라미터를 hashMap에 담아서 db select
          result       = mapper.imgChange(dbSelectParams);
          // createImg() method를 사용하여 fornt에서 사용 될 tag를 완성시킨다.
          returnImgTag = createImg(result);
          // 최종적으로 만들어진 태그를 리턴
          return returnImgTag;
        }
      // 파라미터가 null인 경우
      } catch (Exception e) {
        log.info(">>> parameter false >>>");
        // paramCode(상품명)을 사용하여 seq 생성
        seq = createSeq(view);
        // 조회를 위한 seq를 hashMap에 담는다.
        dbSelectParams.put("seq", seq);
        // 디바이스 접속환경을 고려하여 기본 이미지를 찾는다.
        if (device.isMobile() || device.isTablet()) {
          log.info(">>> is mobile >>>");
          dbSelectParams.put("img_key", "mobile_main_img");
        } else {
          log.info(">>> is pc >>>");
          dbSelectParams.put("img_key", "pc_main_img");
        }
        // default img select
        result = mapper.originalImg(dbSelectParams);
        // createImg() method를 사용하여 fornt에서 사용 될 tag를 완성시킨다.
        returnImgTag = createImg(result);
      }
      // 최종적으로 만들어진 태그를 리턴
      return returnImgTag;
    }

    public String createSeq(String seq) throws Exception {
        
        String _seq = null;
        // seq 만들기
        switch (seq) {
          case "":    _seq = "3";  break;
          case "":       _seq = "7";  break;
          case "":      _seq = "8";  break;
          case "":      _seq = "14"; break;
          case "":      _seq = "15"; break;
          case "": _seq = "16"; break;
          case "":    _seq = "17"; break;
          case "":   _seq = "18"; break;
          case "":  _seq = "19"; break;
          case "":       _seq = "24"; break;
          case "":     _seq = "25"; break;
          default: log.info("error");      break;
        }
        return _seq;
    }

    public String createImg(HashMap<String, String> result) throws Exception {
        // front에서 사용할 tag를 생성
        StringBuilder addParams = new StringBuilder();
        addParams.append("<img ");
        addParams.append("class=");
        addParams.append("'changeImg' ");
        addParams.append("src='");
        addParams.append(result.get("text_value")); // img 넣기
        addParams.append("' ");
        addParams.append("alt='");
        addParams.append(result.get("description")); // alt 넣기
        addParams.append("' ");
        addParams.append("/>");
        // 스트링 빌더 패턴을 사용하여 이어붙인 값을 리턴
        return addParams.toString();
    }
    
}
