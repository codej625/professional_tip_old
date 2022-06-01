
// validation
String mobileFirstNum  = mobileNum.substring(0, 3);

// parameter value lowerCase 처리
String genderLowerCase = gender.toLowerCase();
String deviceLowerCase = device.toLowerCase();

// validation_logic
  try {
        if(!Arrays.asList(Constants.GENDER).contains(genderLowerCase)) {
            Exception e = new Exception("gender");
            throw e;
      } else if (birthday.length() != 8) {
            Exception e = new Exception("birthday");
            throw e;
      } else if (mobileNum.length() != 11) {
            Exception e = new Exception("mobileLength");
            throw e;
      } else if (!mobileFirstNum.equals("010")) {
            Exception e = new Exception("mobileFirstNum");
            throw e;
      } else if (!Arrays.asList(Constants.DEVICE).contains(deviceLowerCase)) {
            Exception e = new Exception("device");
            throw e;
      } 
  } catch (Exception e) {
      log.debug("Exception-> " + e.getMessage());
      e.printStackTrace();
      return RESULT_STATUS_FALSE;
  }