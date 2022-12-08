import requests
import csv

## api를 요청하기 위한  url 생성
url_front  = "http://api.vworld.kr/req/search?"
auth_key   = "{auth_key}"
## type은 place, query는 낚시터
url_params = "&type=place&query=낚시터&request=search"
## url 완성
url = url_front + auth_key + url_params 
## url check
print("url => " + url)
## api request
result = requests.get(url)
## api response return => data(json parse)
json_data = result.json()
## api request status check
print("status => ", json_data['response']['status'])

## status OK => if문 실행
if json_data['response']['status'] == 'OK':
  id           = json_data['response']['result']['items'][0]['id']
  title        = json_data['response']['result']['items'][0]['title']
  address_road = json_data['response']['result']['items'][0]['address']['road']
  category     = json_data['response']['result']['items'][0]['category']
  point_x      = json_data['response']['result']['items'][0]['point']['x']
  point_y      = json_data['response']['result']['items'][0]['point']['y']
## 키워드명으로 이름 저장(개행제거), 한글이 ?으로 출력되므로 encoding 처리
f      = open(title+'.csv', 'w', newline='', encoding='utf-8-sig')
## 6개의 컬럼을 생성
data   = [['id', 'title', 'address_road', 'category', 'point_x', 'point_y'], [id, title, address_road, category, point_x, point_y]]
writer = csv.writer(f)
writer.writerows(data)
f.close()