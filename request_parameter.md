# HttpServletRequest 객체를 사용하여 파라미터를 활용해보자!


```java
// Simple Logging Facade for Java
@Slf4j
@Service
public class ParameterSearch extends ModelAndView {

    @Autowired
    LotteInsurancePageTextsMapper mapper;

    public String getParams(String viewName, HttpServletRequest request) throws Exception {
        /**
         * @param String   paramKey => 이미지 이름과 seq(데이터베이스에서 상품에 해당하는 seq), alt을 조합한 값
         * @param String[] paramKey => paramKey를 "_"를 기준으로 잘라 배열로 저장하기 위한 array
         * @param String   paramCode => query string에서 utm_campaign값을 가져온 값(상품명)
         */
        log.info(">>> getParams method start >>>");

        String view = viewName.replaceAll("/", "");
        String seq = null;
        String returnImgTag = null;
        HashMap<String, String> result;
        String paramKey = request.getParameter("img_key");
        String paramCode = request.getParameter("utm_campaign");
        Map<String, String> dbSelectParams = new HashMap<>();

        switch (view) {
            case "":    seq = "1";  break;
            case "":      seq = "2";  break;
            case "": seq = "3";  break;
            case "":      seq = "4";  break;
            case "":  seq = "5";  break;
            case "":       seq = "6";  break;
            case "":       seq = "7"; break;
            case "":     seq = "8";  break;
            case "":      seq = "9";  break;
            case "":    seq = "10";  break;
            case "":   seq = "11";  break;
            
            default: log.info("error");     break;
        }

        try {
            // 파라미터가 있으면!
            if (!paramKey.equals(null)) {
                System.out.println("test");
                dbSelectParams.put("img_key", paramKey);
                dbSelectParams.put("seq", seq);
                result = mapper.imgChange(dbSelectParams);
                
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
                
                return returnImgTag = addParams.toString();
            }
            // 파라미터가 없으면!!!
        } catch (NullPointerException e) {
            
            dbSelectParams.put("seq", seq);
            result = mapper.originalImg(dbSelectParams);

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
            
            returnImgTag = addParams.toString();
        }
        return returnImgTag;
    }