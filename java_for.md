# for 문제

```java
package study;

public class Array {

	public static void main(String[] args) {

		/**
		 * 1. array을 사용하여 반복문을 만드세요. 콘솔창에 아이유, 조보아, 사나, 김초아를 순차적으로 출력되게 만들어야 합니다.
		 * 
		 * 힌트1: 김초아는 저의 첫사랑 이름입니다. 
		 * 힌트2: for문 혹은 while문을 사용하면 됩니다. 
		 * 힌트3: 변수를 적극적으로 활용하세요. 변수에 값을 대입하여 사용하는 습관을 길러야 합니다.
		 * 힌트4: if 문속에는 두가지 조건을 넣을 수 있습니다. 
		 *       ex) if( i == 1 && j == 1) { ... } <- i는 1이다 그리고(&&) j는 1이다. 두가지 조건이 맞아야 참이 됩니다.
		 */

		String[] name = { "아이유", "조보아", "김초아", "사나" };

		for (int i = 0; i < name.length; i++) {

			for (int j = 0; j < name.length; j++) {

				if (i == 0 && name[j] == "아이유") {
					System.out.println(name[j]);
				} else if (i == 1 && name[j] == "조보아") {
					System.out.println(name[j]);
				} else if (i == 2 && name[j] == "사나") {
					System.out.println(name[j]);
				} else if (i == 3 && name[j] == "김초아") {
					System.out.println(name[j]);
				}
			}

		}
		
		/**
		 * 2. array 속에 내용을 거꾸로 출력하세요
		 * 
		 * 힌트1: 반복문을 사용하여 새로운 변수에 담으면 되겠죠? 혹은 더쉬운 방법이 있을지도?
		 */
		
		String[] name2 = { "거꾸로", "로꾸꺼", "말해말" };
		
		int reverse = name2.length -1;
		
		for (int i = 0; i < name2.length; i++) {
			
			System.out.println(name2[reverse]);
			reverse--;
		}
		
		/**
		 * 3. for문을 사용하여 half tree를 만드세요.
		 *    ex)
		 *    *
		 *    **
		 *    ***
		 *    ****
		 *    *****
		 *    
		 * 힌트1: 핵심은 printf(); 와 prinfln();의 차이가 문제를 푸는 핵심입니다.
		 *       테스트 해보세요
		 */
	
		int num = 5;
		
		for (int i = 0; i <= num; i++) {
			
			for (int j = 0; j < i; j++) {
				
				System.out.printf("*");
			}
			System.out.println("");
		}

	}

}
```