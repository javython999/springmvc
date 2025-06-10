# 타입 변환 (Type Conversion)
## 개요
### 타입변환 
* 타입 변환(Type Conversion)은 데이터를 한 데이터 타입에서 다른 데이터 타입으로 변경하는 과정을 의미한다.
* 주로 서로 다른 데이터 타입 간의 호환성을 확보하거나 특정 연산을 수행하기 위해 필요하다.

### 타입 변환의 종류
* 자동 변환 (Implicit Conversion)
    * 컴파일러가 개발자의 개입 없이 자동으로 수행하는 변환.
    * 주로 작은 크기의 데이터 타입이 큰 크기의 데이터타입으로 변환될 때 사용
* 명시적 변환 (Explicit Conversion)
  * 개발자가 직접 변환을 명시해야하는 경우
  * 데이터 손실이 발생할 가능성이 있거나 타입이 다를 경우 사용한다.

## Converter
### 개요
* 타입변환은 바인딩을 처리하는 과정속에서 포함되어 있으며 타입 변환이 실패하면 더 이상 바인딩을 진행하지 않고 오류를 발생시킨다.
* Spring의 Converter<S, T>는 입력 데이터를 원하는 데이터 타입으로 변환하기 위한 인터페이스로서 소스 객체(S)를 대상 객체(T)로 변환하는 데 사용된다.
* 스프링은 이미 수많은 컨버터 구현체를 제공하고 있으며 특별한 타입이 필요한 경우 Converter를 직접 구현해서 사용할 수 있다.

### 구조
```java
public interface Converter<S, T> {
    T convert(S source);
}
```
## ConverterFactory & ConditionalConverter
### ConverterFactory
* ConverterFactory는 클래스 계층 전체를 처리하기 위한 클래스로서 변환 로직을 따로따로 작성하지 않고 하나의 공통 로직으로 처리할 수 있도록 한다.
* 예를 들어문자열(String)데이터를 다양한 열거형(Enum) 타입으로 변환해야 할 때각 열거형마다 변환기를 만들 필요 없이 변환 로직을 일관되게 관리할 수 있다.

```java
public interface ConverterFactory<S, R> {    
    <T extends R> Converter<S, T> getConverter(Class<T> targetType); // S를 R의 하위 타입인 T로 변환할 수 있는 변환기를 가져온다
}
```

### ConditionalConverter
* ConditionalConverter는 특정 조건이 참일 때만 Converter를 실행하고 싶은 경우 사용할수 있다.
* 예를 들어타겟 필드에 특정 주석이 있을 경우 Converter를 실행하거나 타겟 클래스에 특정 메서드가 정의된 경우 변환기를 실행하고 싶을 때 사용할 수 있다.

```java
public interface ConditionalConverter {
    boolean matches(TypeDescriptor sourceType, TypeDescriptor targetType);
}
```

#### TypeDescriptor
* 객체의 타입과 관련된 부가적인 정보를 표현하기 위해 사용된다. (클래스 타입 정보, 제네릭 정보, 주석(Annotation), 배열&컬렉션 요소 타입)

## ConversionService
### 개요
* Converter가 단일 변환 로직을 위한 것이라면 ConversionService는 어플리케이션 전반에서 통합된 타입 변환 서비스를 제공한다.
* ConversionService는 타입 변환과 Converter들을 등록하고 관리하는 기능을 제공하며 데이터 바인딩, 유효성 검사 등에서 통합적으로 사용하고 있다.


#### converter
```mermaid
flowchart LR
    A --> Converter --> B
```

#### conversionService
```mermaid
flowchart LR
    A --> ConversionService
    ConversionService --> ConverterB --> B
    ConversionService --> ConverterC --> C
    ConversionService --> ConverterD --> D
```

#### 클래스 계층도
```mermaid
flowchart BT
  DefaultConversionService --> GenericConversionService --> ConfigurableConversionService
  ConfigurableConversionService --> ConversionService
  ConfigurableConversionService --> ConverterRegistry
```

## Converter 스프링 적용
### WebMvcConfigurer 에 Converter 등록하기
* 앞에서 수동으로 변환했던 작업을 스프링에 의한 자동 변환 방식으로 적용되도록 WebMvcConfigurer를 사용해 Converter 를 등록한다.
* FormatterRegistry는 웹에서 전반적으로 사용되는 WebConversionService 구현체가 전달된다.
```java
@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(new StringToUrlConverter());
        registry.addConverter(new UrlToStringConverter());
        registry.addConverter(newStringToUserConditionalConverter());        
        registry.addConverterFactory(newStringToEnumConverterFactory());
    }
 }
```

### 바인딩과 타입변환 관계
* DataBinder의 역할
  * DataBinder는 요청 데이터를 객체로 바인딩하는 클래스로서 HTTP 요청 파라미터를 자바 객체의 속서에 매핑한다.
* ConversionService의 역할
  * ConversionService는 타입 변환에 특화된 서비스로서 바인딩 중 필요한 경우 Converter를 호출해 특정 필드 값을 변환한다.
* DataBinder와 ConversionService의 상호 작용
  * DataBinder는 필요한 경우 ConversionService를 통해 요청 데이터를 변환하고 변환된 데이터를 객체 필드에 할당한다.
  * 바인딩 중에 타입변환이 실패하게 되면 예외가 발생해 바인딩을 더 이상 진행하지 않거나 BindingResult를 사용해 오류를 남기고 계속 바인딩을 진행할 수 있다.

* @RequestParam에 지정된 매개변수가 객체인 경우 보통 컨버터가 등록 안되어 있기 때문에 오류가 발생한다.
* 객체를 지정해서 사용할 경우는 @ModelAttribute를 사용하거나 객체타입으로 변환할 수 있는 컨버터를 만들어야 한다.

#### @ModelAttribute 동작 방식
* @ModelAttribute는 클라이언트의 요청 유형에 따라 객체 바인딩 방식과 타입변환 방식으로 객체가 생성되어 메서드에 전달된다.

1. 객체 바인딩 방식
```java
@PostMapping("/url")
public String saveUrl(@ModelAttribute Url url) {
    return "Url : " + url;
}
```
```java
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Url {
  private String protocol;
  private String domain;
  private String port;
}
```
```
POST http://localhost:8080/url
Content-Type: application/x-www-form-urlencoded
protocol=http&domain=www.springmvc.com&port=8080
```
2. 타입 변황 방식
```java
@PostMapping("/url")
public String saveUrl(@ModelAttribute Url url) {
    return "Url : " + url;
}
```
```java
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Url {
  private String protocol;
  private String domain;
  private String port;
}
```
```
POST http://localhost:8080/url
Content-Type: application/x-www-form-urlencoded
url=http://www.springmvc.com:8080
```
## Formatter
### 개요
* Converter는 범용 타입 변환 시스템이다. 즉 타입에 제한 없이 데이터 타입 간 일반적인 변환을 다루는데 목적이 있다.
* Formatter는 특정한 환경(예: 웹 애플리케이션)에서 데이터를 특정 형식에 맞게 변환하거나특정 형식에서 객체로 변환하는 것에 목적이 있다.
* Converter는 로컬화(Localization)를 고려하지 않지만 Formatter는 지역(Locale)에 따라 데이터 표현방식을 다르게 처리할 수 있다.
* Converter가 주로 서버 내부 데이터 변환에 사용된다면 Formatter는 뷰(View) 와 클라이언트 간 데이터 변환에 사용된다고 볼 수 있다.



## Formatting ConversionService
## Formatter 스프링 적용
## 애너테이션 기반 포맷팅