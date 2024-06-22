# 데이터 전달의 사용되는 여러 객체를 알아보자.

<br /><br />

1. DTO(Data Transfer Object)

```
계층(Layer) 간 데이터 교환을 위해 사용하는 객체이다.
데이터 교환만을 위해 사용하므로 로직을 갖지 않고, getter/setter 메소드만 가진다.

일반적으로 Controller layer에서 Service layer까지 데이터를 전달하는 데 쓰인다.
(상세 분류 시 Request / Response 으로 분류가 가능하다.)
```

<br />

```java
class Dto {
   private int red; // Field
   private int green;
   private int blue;
  
   public RGBColor(int red, int green, int blue) { // Constructor
      this.red = red;
      this.green = green;
      this.blue = blue;
   }
  
   public int getRed() { // Getter
      return red;
   }
  
   public int setRed(int red) { // Setter
      this.red = red;
   }

   ...

}

로직은 없고, 데이터를 담고, 꺼내는 getter/setter 메소드만 담는다.
```

<br /><br /><br />

2. VO(Value Object)

```
값 그 자체를 표현하는 객체이다.
비즈니스 로직에서 데이터를 담는 데 사용된다.
예를 들어, 데이터베이스에서 가져온 데이터를 객체로 매핑하여 비즈니스 로직에서 사용할 수 있도록 하거나,
사용자 입력 데이터를 처리하기 위해 사용될 수 있다.

데이터 전달 객체(Data Transfer Object, DTO)와 유사하지만,
VO는 비즈니스 로직에서 값의 단위로 사용.
```
```
불변성(Immutable)

값을 가지고 있지만 한 번 생성되면 내부 상태가 변경되지 않는다.
이는 객체의 상태를 안정적으로 유지하고 예측 가능하게 만든다.
```
```
Equality(동등성)

Value Object는 내용이 같으면 동등하다고 간주.
즉, 값이 같으면 같은 객체로 취급된다.
```
```
Identity(식별성)이 없음

Value Object는 주로 값 자체가 중요한 경우에 사용.
객체가 고유하게 식별되지 않고 값만으로 비교되는 경우가 많다.
```

<br />

```java
class RGBColor {
   private final int red;
   private final int green;
   private final int blue;

  
   @Override
   public boolean equals(Object o) {
      if (this == o) return true;
      if (o == null || getClass() != o.getClass()) return false;
      RGBColor rgbColor = (RGBColor) o;
      return red == rgbColor.red && green == rgbColor.green && blue == rgbColor.blue;
   }
}
// equals() 오버라이드 코드는 IDE에서 자동완성해준다.
```
