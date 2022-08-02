# java 입력 받은 날짜의 해당주(week) 날짜 구하기

```java
/**
	 * 특정 날짜가 포함된 주 날짜들 return
	 * @param dateStr
	 * @return
	 */
	public static String[] getDaysOfWeek(String dateStr) {
		DateFormat df = new SimpleDateFormat("yyyy.MM.dd");
		String[] arrYMD = new String[7];
		try {
			Date date = df.parse(dateStr);
			Calendar cal = Calendar.getInstance();
			cal.setTime(date);
			
			int inYear = cal.get(cal.YEAR); 
		    int inMonth = cal.get(cal.MONTH);
		    int inDay = cal.get(cal.DAY_OF_MONTH);
		    
		    int yoil = cal.get(cal.DAY_OF_WEEK); //요일나오게하기(숫자로)
		    if(yoil != 1){   //해당요일이 일요일이 아닌경우
		        yoil = yoil-2;
		     }else{           //해당요일이 일요일인경우
		        yoil = 7;
		     }
		    inDay = inDay-yoil;
		    
		    for(int i = 0; i < 7;i++){
		     cal.set(inYear, inMonth, inDay+i);  //
		     String y = Integer.toString(cal.get(cal.YEAR)); 
		     String m = Integer.toString(cal.get(cal.MONTH)+1);
		     String d = Integer.toString(cal.get(cal.DAY_OF_MONTH));
		     if(m.length() == 1) m = "0" + m;
		     if(d.length() == 1) d = "0" + d;
		     
		     //arrYMD[i] = y+m +d;
		     arrYMD[i] = m +"."+d;
		    }
		} catch (ParseException e) {
		}
		
		return arrYMD;
	}
```