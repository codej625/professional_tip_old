# 개발자라면 XML, JSON, YAML에 최소 한 번은 들어본 적이 있을 것이다. 아마도 XML과 JSON은 웹에서 서버와 클라이언트가 데이터를 주고받을 때 사용하는 형식, 그리고 YAML은 어떠한 설정 파일을 작성할 때 사용하는 형식 정도로 알고 있을 것이다. 사실 그 정도로만 알고 있어도 실무에서 크게 문제가 되지는 않지만, 한 번 정도는 각각의 개념을 깔끔히 정리해두면 좋겠다는 생각에 이번 포스팅을 작성하게 되었다. 그렇다면 본격적으로 XML, JSON, YAML의 개념에 대해 알아보도록 하자.

<br>

1. 1. XML, JSON

```
1-1. XML, JSON의 필요성

웹에서 서버와 클라이언트는 데이터를 주고받는다. 이때 주고받는 데이터는 한 줄의 텍스트 형식이다. 그래서 간단한 데이터의 경우 문제가 없지만, 복잡한 구조의 데이터의 경우 한 줄의 텍스트로 어떻게 표현해야 하는가에 관한 문제가 남는다. 이러한 문제를 해결하기 위한 데이터 표현 형식으로 합의된 것이 바로 XML과 JSON이다. 즉, XML과 JSON은 임의의 구조를 가진 데이터를 한 줄의 텍스트로 표현하는 형식이다.
```

```
이때 중요한 개념이 하나 있는데, 그것은 바로 이러한 형식들은 프로그래밍 언어나 소프트웨어의 종류와 완전히 독립적이라는 것이다. 한국어로는 "하나, 둘, 셋"이고 영어로는 "One, Two, Three"이지만 두 나라의 사람들 모두 "1, 2, 3"이라는 표기는 이해하고 있는 것과 마찬가지로, 어떤 환경에서도 이해할 수 있는 표준 형식을 약속한 것이 바로 XML과 JSON이다.
```

```
1-2. XML (eXtensible Markup Language)
```

```XML
<?xml version="1.0" encoding="UTF-8"?>
<profile>
  <name>최덕경</name>
  <job>웹 개발자</job>
  <email>hjcdg197@gmail.com</email>
  <skill-categories>
    <skill-category>
      <name>Frontend</name>
      <skills>
        <skill>HTML</skill>
        <skill>CSS</skill>
        <skill>JavaScript</skill>
      </skills>
    </skill-category>
    <skill-category>
      <name>Backend</name>
      <skills>
        <skill>Django</skill>
        <skill>PostgreSQL</skill>
      </skills>
    </skill-category>
  </skills>
</profile>
```

```
XML은 우리가 익숙하게 알고 있는 HTML과 유사하게 태그 형식으로 데이터를 표현한다. 실제로 XML 형식으로 웹 페이지를 표현할 수 있도록 개발한 것이 바로 HTML이다. 참고로, 코드를 작성할 때 엔터와 탭을 사용하는 것은 가독성을 위한 것뿐이고, 실제로 엔터와 탭을 모두 지우고 한 줄의 텍스트로 표현해도 동일하게 동작한다.
```

```
XML의 장점 (= 안정성)

문법 오류에 취약하지 않다. 여는 태그와 닫는 태그로 영역이 구분되어 있어, 문법에 어긋나게 작성한 부분에 해당하는 태그를 제외한 나머지 부분은 정상적으로 해석될 수 있기 때문이다. 이는 HTML에서도 마찬가지이다.
가독성을 높이기 위한 주석을 작성할 수 있다. XML에서 주석은 HTML과 마찬가지로 <!-- 내용 --> 형식으로 작성할 수 있다.
데이터가 갖춰야 할 구조를 스키마(.xsd 파일)라는 것으로 정의해두고, 이를 통해 데이터 검증이 가능하다.
 

XML의 단점

여는 태그와 닫는 태그 때문에 사실상 동일한 텍스트를 두 번씩이나 작성해야 해서 데이터의 사이즈가 크다.
태그 형식의 데이터 표현은 가독성이 떨어진다.
```
<br>

```
1-3. JSON (JavaScript Object Notation)
```

```JSON
{
  "name": "최덕경",
  "job": "웹 개발자",
  "email": "hjcdg197@gmail.com",
  "skillCategories": [
    {
      "name": "Frontend",
      "skills": [
        "HTML",
        "CSS",
        "JavaScript"
      ]
    },
    {
      "name": "Backend",
      "skills": [
        "Django",
        "PostgreSQL"
      ]
    }
  ]
}
```

```
JSON은 JavaScript에서 객체를 표현하는 형식으로 데이터를 표현한다. 그리고 XML과 마찬가지로, 코드를 작성할 때 엔터와 탭을 사용하는 것은 가독성을 위한 것뿐이고, 실제로 엔터와 탭을 모두 지우고 한 줄의 텍스트로 표현해도 동일하게 동작한다.
```

```
JSON의 장점 (= 가벼움)

데이터의 표현에 필요한 정보만 간결하게 표현하므로 데이터의 사이즈가 작다.
태그 형식의 데이터 표현에 비해 가독성이 좋다.
 

JSON의 단점

문법 오류에 취약하다. 문법에 어긋나게 작성한 부분이 조금이라도 있으면 전체가 정상적으로 해석되지 못한다.
가독성을 높이기 위한 주석을 작성할 수 없다.
스키마 등의 검증 기능이 자체적으로 없기 때문에, 필요시 검증 로직을 직접 프로그래밍해야 한다.
```
<br>

2. YAML (YAML Ain't Markup Language)

```YAML
name: 최덕경
job: 웹 개발자
email: hjcdg197@gmail.com
skillCategories:
  - name: Frontend
    skills:
      - HTML
      - CSS
      - JavaScript
  - name: Backend
    skills:
      - Django
      - PostgreSQL
```

```
데이터를 한 줄의 텍스트로 압축적으로 표현하는 것이 목적인 XML, JSON과 달리, YAML은 사람이 보기 좋게 데이터를 표현하는 것이 목적이다. 즉, 사용자의 편의를 가장 우선시하는 것이다. 그래서 오히려 엔터를 이용한 개행과 탭을 이용한 인덴트가 반드시 필요하다(Python과 유사하다). 일반적으로 docker-compose나 Spring 설정 파일과 같이 여러 종류의 설정 파일에서 채택하여 사용하고 있는 형식이다. 사용자의 편의를 가장 우선시하기 때문에 당연히 주석도 작성할 수 있고, (여기서는 자세히 설명하지 않겠지만) 상속 기능을 활용하여 작성할 수도 있다.
```

