# DTO와 MAP을 활용해보자

```java
{
    List<ImgDto> imgList = ImgMapper.getList({seq});

    Map<String, String> imgMap = new HashMap<>();

    for (ImgDto imgDto : imgList) {
        imgMap.put(imgDto.getText(), imgDto.getName());
    }

    mv.addObject("imgList", imgMap);
}

return mv;
```


