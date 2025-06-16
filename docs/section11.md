# Return Values
## Return Values 개요
### 개요
* 스프링 MVC에서 컨트롤러 메서드의 반환 방식은 크게 View 와 HTTP 본문 응답으로 나눌 수 있으며 각 방식은 클라이언트에게 반환하는 응답의 형태를 결정한다.
1. View 렌더링
   * HTML 과 같은 페이지를 클라이언트에게 반환하는 방식으로서 컨트롤러가 뷰 이름을 반환하고 뷰 레이어에서 해당 이름을 해석하여 적절한 HTML을 생성한다.
2. HTTP 본문 응답
   * JSON, XML 등 데이터 형식으로 응답을 직접 반환하는 방식으로서 REST API 와 같은 데이터 중심의 애플리케이션에서 주로 사용된다.
   * 뷰가 아닌 데이터를 응답 본문에 담아 전달해서 클라이언트는 페이지가 아닌 데이터를 수신하게 된다.

### HandlerMethodReturnValueHandler
* 컨트롤러로부터 응답결과를 받아 응답처리를 위한 작업을 담당하는 클래스이다.
* 다양한 유형의 파라미터(예: String, View, @ResponseBody 등)를 처리하기 위해 여러 HandlerMethodReturnValueHandler 기본 구현체를 제공한다.
* 개발자가 필요에 따라 HandlerMethodReturnValueHandler 인터페이스를 직접 구현할 수 있다.

### View 렌더링 반환 타입
1. String – 문자열로 뷰 이름을 반환하면 ViewResolver에 의해 해당 이름에 맞는 뷰가 렌더링 된다.
2. ModelAndView - 뷰와 모델 데이터를 함께 담아 반환하는 객체입니다. 뷰 이름뿐만 아니라 모델 데이터를 함께 설정하여 전달할 수 있다.
3. View - View 인터페이스를 구현한 객체를 직접 반환할 수 있다. 이 경우 ViewResolver에 의존하지 않고 특정 View 인스턴스를 직접 렌더링 한다.
4. Model 또는 Map - 모델 데이터를 반환하면뷰 이름은 요청 경로에 따라 자동으로 결정된다. Model이나 Map은 뷰에 전달할 데이터로만 사용된다.

```
1. String
public String viewName(Model model) {
    return "home"; // ViewResolver에 의해 home.jsp 또는 home.html 렌더링
}

2. ModelAndView
public ModelAndView modelAndView() {
    ModelAndView mav = new ModelAndView("home");// 렌더링할 뷰 이름 설정
    model.addObject("message", “Hello World"); // 데이터 저장
    return mav;
}

3. View
public View view() {
    return new ExcelView(); // 엑셀 데이터로 렌더링, View 객체를 직접 반환하여 ViewResolver 를 사용하지 않음
}

4. Model or Map
public Model model(Model model) {
    model.addAttribute("message", “Hello World");
    return model; // Model을 반환하여 데이터만 제공 (뷰 이름은 요청 경로를 기준으로 자동 결정)
}
```
### ModelViewContainer
* Spring MVC가 내부적으로 요청 처리 시점에 자동으로 생성 및 관리하는 클래스로서 요청 처리가 완료될 때까지 모델과 뷰 관련 데이터를 임시로 보관한다.

### ModelAndView
* 최종적으로 뷰를 렌더링하기 위한 모델과 뷰의 정보를 제공하는 클래스이다.
* ModelAndView 객체를 직접 반환해도 되고 뷰 이름이나 뷰 객체를 반환하게 되면 내부적으로 ModelAndView 객체가 생성되어 응답을 구성한다.

### HTTP 본문 응답 반환 타입
1. @ResponseBody -컨트롤러 메서드의 반환 값을 HttpMessageConverter를 통해 JSON, XML 등으로 변환하여 응답 본문에 직접 작성한다.
2. HttpEntity<T>, ResponseEntity<T> - HTTP 응답(헤더와 본문 모두)을 구성할 수 있다. ResponseEntity는 상태 코드, 헤더, 본문을 모두 포함할 수 있어 더 정밀한 응답 구성이 가능하다.
3. Callable<V>, ListenableFuture<V>, CompletableFuture<V> - 비동기 작업의 결과로 반환되는 타입을 사용할 수 있다.

## @ResponseBody
### 개요
* @ResponseBody는 메서드의 반환 값을 HTTP 응답 본문에 직접 직렬화하여 클라이언트에 전송하며 HttpMessageConverter를 사용하여 변환이 이루어진다.
* 일반적으로 컨트롤러는 HTTP 요청을 처리하고뷰(View)를 반환하는 방식으로 동작하는데 @ResponseBody를 사용하면 뷰를 반환하는 대신 메서드가 반환하는 객체를 바로 HTTP 응답 본문에 직렬화하여 전송하게 된다.

### @ResponseBody & @RestController
* ResponseBody는 메서드뿐만 아니라 클래스 수준에서도 사용될 수 있으며 이와 같은 효과를 가진 것이 바로 @RestController라 할 수 있다.
* @RestController는 @Controller와 @ResponseBody를 결합한 메타 애노테이션으로서 @RestController가 선언된 클래스는 모든 메서드에서 반환되는 값이 자동으로 @ResponseBody 처럼 처리가 이루어진다.

## ResponseEntity<T>
* ResponseEntity<T>는 HTTP 응답을 나타내는 클래스로서 주로 응답 상태 코드, 헤더, 본문을 제어하고 반환하는 데 사용되며  HttpEntity<T>를 상속하고 있다.
* ResponseEntity<T>는 @ResponseBody와 비슷하지만 @ResponseBody는 메서드 반환 값을 HTTP 응답 본문으로 기록하는 반면 ResponseEntity는 상태 코드와 헤더 그리고 본문까지 세밀하게 제어할 수 있는 기능을 제공한다.
* ResponseEntity<T>는 @RestController나 @ResponseBody가 없어도 적절한 HTTP 응답을 반환할 수 있다.

### 상태 코드 설정 API
* ResponseEntity.status(HttpStatus status): 지정한 상태 코드로 ResponseEntity를 생성한다.
  * ResponseEntity.status(HttpStatus.NOT_FOUND) → 404 상태 코드 설정
* ResponseEntity.ok(): 200 OK 상태 코드를 설정한다.
  * ResponseEntity.ok().body(user)
* ResponseEntity.created(URI location): 201 Created 상태 코드와 함께, Location 헤더에 지정된 URI를 추가한다.
  * ResponseEntity.created(URI.create("/api/users/1")).build()
* ResponseEntity.noContent(): 204 No Content 상태 코드를 설정합니다. 본문이 없는 응답을 보낼 때 사용한다.
  * ResponseEntity.noContent().build()
* ResponseEntity.accepted(): 202 Accepted 상태 코드를 설정한다.
  * ResponseEntity.accepted().build()
* ResponseEntity.badRequest(): 400 Bad Request 상태 코드를 설정한다.
  * ResponseEntity.badRequest().body(errorMessage)
* ResponseEntity.notFound(): 404 Not Found 상태 코드를 설정한다.
  * ResponseEntity.notFound().build()
* ResponseEntity.internalServerError(): 500 Internal Server Error 상태 코드를 설정한다.
  * ResponseEntity.internalServerError().body(errorMessage)

