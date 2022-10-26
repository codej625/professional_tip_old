# 컬럼은 하나이고 checkbox로 check한 값을 삭제하면서 update하고 싶을때

```java

    @PostMapping("/api/v1/imgDelete")
    public int imgDelete(@RequestParam Map<String, Object> params) throws Exception {

        log.info(">> imgDelete controller >>");

        // 1. request parameter 정리
        String img  = (String) params.get("checkList"); // check된 이미지 리스트
        String fseq = (String) params.get("seq");

        fseq    = fseq.substring(5, fseq.length());
        int seq = Integer.parseInt(fseq); // db 조회에 필요한 key값 가공

        // 2. img select result => exception처리
        /**
         * @param String   result => image list를 select하여 나온 결과 값
         * @param String[] selectList => select 결과 값을 담기위한 array
         * @param String[] deleteList => delete check된 목록을 담기위한 array
         */
        String result = "";
        String[] deleteList;
        String[] selectList;

        try {
            // insert img select
            result = mainMapper.selectTextKey(seq).toString();
            // 3. select result를 단수인지 복수인지 구별
            if (result.length() == 103) {
                // select img 단수일때
                selectList = new String[1];
                selectList[0] = result;
            } else {
                // select img 복수일때
                selectList = result.split(",");
            }
            
            // 4. delete img를 단수인지 복수인지 구별
            if (img.length() == 103) {
                // delete img 단수일때
                deleteList = new String[1];
                deleteList[0] = img;
            } else {
                // delete img 복수일때
                deleteList = img.split(",");
            }
        } catch (Exception e) {
            log.info(">> e.getMessage() => {}", e.getMessage());
            // select 결과 => null일시 return false
            return 0;
        }
        
        // 5. 전체 목록에서 deleteList 제거
        for (int i = 0; i < deleteList.length; i++) {
            // 원래 배열과 요소를 넣고 삭제
            selectList = ArrayUtils.removeElement(selectList, deleteList[i]);
        }
        
        String saveImg = Arrays.toString(selectList).replaceAll("\\s", "");
        saveImg = saveImg.substring(1, saveImg.length() - 1);
        
        Map<String, Object> imgName = new HashMap<String, Object>();
        imgName.put("lotteInsuranceProductSeq", seq);
        imgName.put("textValue", saveImg);

        // list all delete
        mainMapper.imgDelete(imgName);
        
        // 정리된 list insert
        if (!saveImg.equals("")) {
            mainMapper.imgInsert(imgName);
        }
        
        return 1;
    }
    ```