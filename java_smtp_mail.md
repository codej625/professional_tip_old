# java mail smtp

```java

    @CrossOrigin
    @ResponseBody
    @GetMapping(value = "/mail")
    public void getMail(HttpServletRequest request, HttpSession httpSession, Device device,
      @RequestParam("companyName") String companyName,//
      @RequestParam("userName")    String userName,//
      @RequestParam("contact")     String contact,//
      @RequestParam("email")       String email) throws Exception {

      log.info(">>> getMail controller start >>>");

      StringBuilder builder = new StringBuilder();
      builder.append("신청자 입력 정보\n");
      builder.append("- 회사명 : " + companyName + "\n");
      builder.append("- 이름 : " + userName + "\n");
      builder.append("- 연락처 : " + contact + "\n");
      builder.append("- 이메일 : " + email);
      builder.append("\n");
      builder.append("추가 정보\n");
      builder.append("- 신청 일시 : " + DATE_TIME_HUMAN_READABLE_FORMAT.format(new Date()) + "\n");
      builder.append("- 모바일/PC 구분 : " + (device.isMobile() ? "모바일" : "PC") + "\n");
//      builder.append("- 접속 IP : " + SystemUtil.getClientIpAddr(request) + "\n");
//      builder.append("- Agent : " + SystemUtil.getClientAgent(request) + "\n");

      final String user     = "asd@naver.com"; // 발신자의 이메일 아이디를 입력
      final String password = "1234"; // 발신자 이메일의 패스워드를 입력

      Properties prop       = new Properties();
      prop.put("mail.smtp.host", "smtp.naver.com");
      prop.put("mail.smtp.port", 587);
      prop.put("mail.smtp.auth", "true");
      prop.put("mail.smtp.ssl.enable", "false");
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
        message.addRecipient(Message.RecipientType.TO, new InternetAddress("보내는사람 메일 ex) asd@naver.com"));
        // Subject
        message.setSubject("회사명 " + "\"" + companyName + "\"" + "컨설팅 요청" ); // 메일 제목을 입력
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
    }
    ```
