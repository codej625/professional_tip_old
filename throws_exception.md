# 예외처리를 던지자!

```java
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

```