# whrows를 사용하여 예외를 던지자!
<br>
```
findClass method 선언부 뒤에 throws가 붙어있기 때문에,
forName() method를 호출 시 try-catch 블록으로 예외 처리를 하거나,
throws로 예외를 떠넘겨야 한다.
```

```java
public class ThrowExam {
    public static void main(String[] args) {
        try {
            findClass();
        } catch(ClassNotFoundException e) {
            System.out.println("not found class");
        }
    }

    public static void findClass throws ClassNotFoundException {
        Class throwsClass = Class.forName("java.lang.String2") // Exception! throws!
    }
}
```
