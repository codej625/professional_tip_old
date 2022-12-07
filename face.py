# -*- coding: utf-8 -*-

import matplotlib.pyplot as plt  # 그림 관련 라이브러리
import matplotlib.image as mpgimg  # 사진 관련 라이브러리

img = mpgimg.imread('과제 1번/jinwoolee.png')  # 사진 이름 설정
plt.figure(figsize=(10,8))    # 액자설정
plt.imshow(img) # 사진 읽기
plt.show()      # 사진 보여죠

import json # json 형식 사용(만들어진 엔진) key : value
import requests # 인터넷 연결

client_id = ''    # 네이버개발자센터에서 app 등록 후 나온 계정
client_secret = ''

url = 'https://openapi.naver.com/v1/vision/face'  # 얼굴감지
files = {'image':open('과제 1번/jinwoolee.png','rb')}
headers = {'X-Naver-Client-Id': client_id, 'X-Naver-Client-Secret': client_secret }
response = requests.post(url,  files=files, headers=headers)

print(response.text)

parsed = json.loads(response.text)
print(parsed) 

print(json.dumps(parsed, indent=4, sort_keys = False, ensure_ascii=False)) # 데이터를 들여쓰기 적용하여 보기 좋게 출력

parsed.keys()

parsed['faces']

parsed['faces'][0]['gender']

parsed['faces'][0]['age']

parsed['faces'][0]['emotion']

parsed['faces'][0]['roi']

for each in parsed['faces'] :
  h,w,x,y = each['roi'].values()
  gender,gen_conf = each['gender'].values()
  age,age_conf = each['age'].values()
  emotion,emo_conf = each['emotion'].values()

  print(gender + age)

import matplotlib.patches as patches 
img = mpgimg.imread('과제 1번/jinwoolee.png')
fig,ax = plt.subplots(figsize = (10,10))
ax.imshow(img)

for each in parsed['faces'] :
  x,y,w,h = each['roi'].values()
  gender,gen_conf = each['gender'].values()
  age,age_conf = each['age'].values()
  emotion,emo_conf = each['emotion'].values()

  rect_face = patches.Rectangle((x,y),w,h,
                                linewidth = 3,
                                edgecolor = 'r',
                                facecolor = 'none')
  letter = gender +' '+str(gen_conf*100)[0:5] +'%, \n' + emotion + str(emo_conf*100)[0:5] +'%,\n' + age + str(age_conf*100)[0:5] +'%'
  plt.text(x-50,y+h+120, letter, size=8, color = 'blue')
  ax.add_patch(rect_face)