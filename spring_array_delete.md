# 기존의 array의 값에서 특정 요소 제거후 업데이트하기

```java
    @PostMapping("/api/v1/imgDelete")
    public int imgDelete(@RequestParam Map<String, Object> params) throws Exception {
      
      log.info(">> imgDelete controller >>");  
        
      // 1. parameter 정리
      String img  = (String) params.get("checkList");
      String fseq = (String) params.get("seq");
      
      log.info("img => {}, fseq => {}", img, fseq);
      
      fseq    = fseq.substring(5, fseq.length());
      int seq = Integer.parseInt(fseq);
      
      // 2. 데이터 조회
      String result       = ???.select(seq).toString();
      
      // delete list
      String[] checkList  = img.split(",");
      // original list
      String[] imgList    = result.split(",");
      // delete list를 담을 array => checkList에 크기를 기준으로 생성
      String[] deleteList = new String[imgList.length -checkList.length];
      
      for (int i = 0; i < checkList.length; i++) {
        /*Ex)*********************
         * imgList   {1, 2, 3, 4}
         * checkList {3, 4, 1}
         *     index [0] => 3
         * {1, 2, 4} <= 3 delete
         *************************/
        if (i <= 0) {
          deleteList = ArrayUtils.removeElement(imgList, checkList[i]);
        /*************************
         * imgList {1, 2, 4}
         * checkList {3, 4, 1}
         *        index [1] => 4
         * {1, 2} <= 4 delete
         *************************
         * imgList{1, 2}
         * checkList{3, 4, 1}
         *          index [2] => 1
         * {2} <= 1 delete
         *************************/
        } else {
          deleteList = ArrayUtils.removeElement(deleteList, checkList[i]);
        }
        
      }
      // 1. deleteList를 split해서 ","을 붙인다.
      // 2. 그전 textValue를 삭제한다.
      // 3. 업데이트
      
      Map<String, Object> imgName = new HashMap<String, Object>();
      imgName.put("lotteInsuranceProductSeq", seq);
      imgName.put("textValue", deleteList);
      
      return ???.insert(imgName);
    }
```