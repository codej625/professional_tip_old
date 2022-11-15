# java mail smtp

```html
<div class="uk-margin">
  <p class="uk-text-bold">CONTACT</p>
  <form action="/mail" id="frm">
    <div class="uk-margin-small">
      <input type="text" placeholder="회사명" maxlength="100" class="uk-input" name="companyName" id="companyName">
    </div>
    <div class="uk-margin-small">
      <input type="text" placeholder="이름" maxlength="100" class="uk-input" name="userName" id="userName">
    </div>
    <div class="uk-margin-small">
      <input type="text" placeholder="연락처" maxlength="100" class="uk-input" name="contact" id="contact">
    </div>
    <div class="uk-margin-small">
      <input type="text" placeholder="이메일 ( 정확한 메일을 입력해주세요 )" class="uk-input" name="email" id="email">
    </div>
    <input type="checkbox" value="true" class="uk-checkbox" name="agreeprivacy" id="agreeprivacy">
    <span class="agreeprivacy">
      <a href="#modal-privacy-01" uk-toggle="target:#modal-privacy-01" data-toggle="modal" data-target="#modal-privacy-01">개인정보 수집 및 이용에 동의합니다.</a>
    </span>
    <p class="uk-light uk-margin"><button type="submit" id="consult" class="uk-button uk-width-1-1 uk-button-primary">무료 컨설팅 신청하기</button></p>
  </form>
</div>
```

```java
@CrossOrigin
@ResponseBody
@PostMapping(value = "/mail")
public int getMail(HttpServletRequest request, HttpSession httpSession, Device device,
  @RequestParam HashMap<String, Object> result) throws Exception {
  
  log.info(">>> getMail controller start >>>");
  
  StringBuilder builder = new StringBuilder();
  builder.append("신청자 입력 정보\n");
  builder.append("- 회사명 : " + result.get("companyName") + "\n");
  builder.append("- 이름 : "  + result.get("userName") + "\n");
  builder.append("- 연락처 : " + result.get("contact") + "\n");
  builder.append("- 이메일 : " + result.get("email"));
  builder.append("\n");
  builder.append("추가 정보\n");
  builder.append("- 신청 일시 : " + DATE_TIME_HUMAN_READABLE_FORMAT.format(new Date()) + "\n");
  builder.append("- 모바일/PC 구분 : " + (device.isMobile() ? "모바일" : "PC") + "\n");
  // builder.append("- 접속 IP : " + SystemUtil.getClientIpAddr(request) + "\n");
  // builder.append("- Agent : " + SystemUtil.getClientAgent(request) + "\n");

  final String user     = "test@naver.com"; // 발신자의 이메일 아이디를 입력
  final String password = "1234"; // 발신자 이메일의 패스워드를 입력

  Properties prop       = new Properties();
  prop.put("mail.smtp.host", "smtp.naver.com");
  prop.put("mail.smtp.port", 587);
  prop.put("mail.smtp.auth", "true");
  prop.put("mail.smtp.ssl.enable", "false"); // <= 운영시 true
  prop.put("mail.smtp.ssl.trust", "smtp.naver.com");

  Session session = Session.getInstance(prop, new javax.mail.Authenticator() {
      
    protected PasswordAuthentication getPasswordAuthentication() {
      return new PasswordAuthentication(user, password);
    }
    
  });
  
  try {
      
    MimeMessage message = new MimeMessage(session);

    message.setFrom(new InternetAddress(user));
    // 수신자메일주소
    message.addRecipient(Message.RecipientType.TO, new InternetAddress("test@naver.com"));
    // Subject
    message.setSubject("[컨설팅 요청]" + " \"" + result.get("companyName") + "\"" ); // 메일 제목을 입력
    // Text
    message.setText(builder.toString()); // 메일 내용을 입력
    // send the message
    Transport.send(message);
    log.info("message sent successfully...");
    
  } catch (AddressException e) {
      
    log.info(">>> AddressException >>>");
    e.printStackTrace();
  } catch (MessagingException e) {
      
    log.info(">>> MessagingException >>>");
    e.printStackTrace();
  }
  
  return 1;
}
```

```javascript
$('#consult').on('click', function(e) {
  
  e.preventDefault();
  
  var result  = ['companyName', 'userName', 'contact', 'email'];
  var checked = $('#agreeprivacy').is(':checked');
  
  for (var vali of result) {
    if ($(`#${vali}`).val() == '') {
      alert(`#${vali} 내용을 입력해주세요`); 
      return false;
    } 
  }
  
  if (checked == false) {
    alert('개인정보 수집 및 이용에 동의 부탁드립니다.');
    return false;
  }
  
  var result = $('#frm').serialize();
  
  // ajax 통신
  $.ajax({
    type : "get",
    url : "/mail",
    data : result,
    success : function(res) {
      if (res == 1) {
        alert('무료 컨설팅 요청이 완료되었습니다.');
        reload();
      }
    },
    error : function(XMLHttpRequest, textStatus, errorThrown) {
      alert("error");
    }
  });
});
        
function reload() {
  location.reload();
}  
```