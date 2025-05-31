# 스프링 웹 MVC 활용 - HandlerMapping
## HandlerMapping 이해
### 개요
* HandlerMapping은 요청 URL과 이를 처리할 핸들러(Handler, 일반적으로 Controller)를 매핑하는 인터페이스다.
* 클라이언트 요청이 들어오면 DispatcherServlet은 등록된 모든 HandlerMapping 구현체를 탐색하여 적합한 핸들러를 찾아 반환하고 이후 적절한 HandlerAdapter를 통해 실행한다.
* HandlerMapping은 핸들러를 검색해서 찾는 역할만 할 뿐 핸들러를 실행하지 않는다. 핸들러 실행은 HandlerAdapter가 담당한다.

### 구조
* BeanNameUrlHandlerMapping
  * Bean 이름을 URL로 매핑한다.
  * 현재는 잘 사용되지 않는 이전 방식이다.
* RequestMappingHandlerMapping
  * 가장 우선 순위가 높고 대부분 이 방식을 사용한다.
  * @RequestMapping 또는 @GetMapping, @PostMapping과 같은 애노테이션을 기반으로 URL롸 핸들러를 매핑한다.
* SimpleUrlHandler
  * 명시적으로 URL 패턴을 핸들러와 매핑하는 방식으로 간단한 URL 매핑에 사용된다.

### HandlerMapping 설계 의도
* DispatcherServlet은 수 많은 요청들을 핸들러를 통해서 처리하는데 현재 요청이 어떤 핸들러에 의해 처리될 것인지 결정해서DispatcherServlet에게 알려 주어야 한다.
* HandlerMapping은 클라이언트의 요청 Url 정보를 분석해서 해당 Url 과 매핑이 되어 있는 핸들러를 결정한다.

### Handler 구현 방식
* @Controller, @RestController
  * @Controller는 Spring MVC에서 가장 널리 사용되는 요청 처리 방식으로서 클래스에 @Controller를 붙이고 메서드에 @RequestMapping과 같은 어노테이션을 사용하여 요청을 처리한다.
* Controller 인터페이스
  * Spring 2.5 이전에 사용되던 요청 처리 방식으로서 Controller 인터페이스를 구현하여 요청을 처리한다.
* HttpRequestHandler
  * HttpRequestHandler 인터페이스를 구현하여 요청을 처리하는 방식으로서 Spring의 가장 저수준 API 중 하나로 서블릿에 가까운 형태로 동작한다.


#### BeanNameUrlHandlerMapping
```java
@Component("/beanHandler") // Bean 이름이 요청 URL과 매핑됨
public class BeanNameHandler implements Controller {
    @Override
    public ModelAndView handleRequest(HttpServletRequest req, HttpServletResponse res) throws Exception {
        response.getWriter().write("BeanNameUrlHandlerMapping!");
        return null;
    }
}
```
`http://localhost:8080/beanHandler`
* Spring 컨테이너에 등록된 빈 이름을 기반으로 URL 을 자동 매핑하며빈 이름이 URL 경로와 일치하면 해당 요청을 처리한다.

#### SimpleUrlHandlerMapping
```java
public class MyHttpRequestHandler implements HttpRequestHandler {
    @Override
    public void handleRequest(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        response.setContentType("text/plain");
        response.getWriter().write("SimpleUrlHandlerMapping!");
    }
}

@Bean
public SimpleUrlHandlerMapping simpleUrlHandlerMapping() {
    SimpleUrlHandlerMapping mapping = new SimpleUrlHandlerMapping();
    Properties urlMappings = new Properties();
    urlMappings.setProperty("/simpleUrl", "myHttpRequestHandler");
    mapping.setMappings(urlMappings);
    mapping.setOrder(1);
    return mapping;
}

@Bean
public MyHttpRequestHandler myHttpRequestHandler() {
    return new MyHttpRequestHandler();
}
```
` http://localhost:8080/simpleUrl`
* 설정 파일이나 코드에서 명시적으로 URL 패턴과 핸들러를 매핑하여특정 URL 요청을 지정된 핸들러로 처리한다.

## @RequestMapping
### 개요
* @RequestMapping은 특정 URL 경로에 대한 요청을 처리할 메서드를 매핑하는데 사용되는 어노테이션이다.
* @RequestMapping은 @Controller 및 @RestController로 선언된 클래스에서 사용되며 내부적으로 RequestMappingHandlerMapping 클래스가 처리하고 있다.

### 기본 구조
* @Controller 및 @RestController를 선언하면 스프링이 자동으로 빈으로 등록한다.
* 메서드 위에 @RequestMapping을 정의하고 요청 경로를 지정한다.
* @Controller에서 String 타입으로 반환 하면 반환 값으로 된 페이지(HTML, JSP)를 검색하고 렌더링한다.
* @RestController에서 String 타입으로 반환하면 반환 값이 HTTP 본문에 기록되고 화면에 출력된다.

### URL 매핑
```java
@RestController
public class HomeController {
    @RequestMapping("/profile")
    public String profile () {
        return "This is the profile page";
    }
    
    @RequestMapping({"/user/**", "/mypage"})
    public String multi() {
        return "This is the profile and about page";
    }
}
```
* 경로 매핑 URI 는 예를 들어 "/profile"과 같은 형태를 가진다.
* Ant 스타일의 경로 패턴("/user/**") 지원한다.
* 다중 설정({"/user/**“, "/mypage"})도 가능하다.

```java
@RestController
@RequestMapping("/profile") // 타입 수준 매핑
public class ProfileController {
    
    // "/profile/view"로 매핑
    @RequestMapping("/view") // 메서드 수준 매핑
    public String viewProfile() {
        return "Viewing Profile";
    }
    // "/profile/edit"로 매핑
    @RequestMapping("/edit") // 메서드 수준 매핑
    public String editProfile() {
        return "Editing Profile";
    }
}
```
* 타입 수준과 메서드 수준 모두 지원되며 타입 수준에서 사용되는 경우모든 메서드 수준 매핑은 타입 수준 매핑을 상속한다.
* 즉 타입 수준에서 경로를 정의하면 모든 메서드는 해당 경로를 포함한 URI를 가지게 된다.

```java
@RestController
@RequestMapping("/${profile_path}") // 플레이스홀더 사용
public class ProfileController {

    @RequestMapping("/view")
    public String viewProfile() {
        return "Viewing Profile";
    }

    @RequestMapping("/edit")
    public String editProfile() {
        return "Editing Profile";
    }
}
```
* 매핑 URI는 플레이스홀더(예: "/${profile_path}")를 포함할 수 있다.
* profile_path 값을 profile로 설정했으므로 최종 경로는 다음과 같다.
* /profile/view → viewProfile() 메서드 실행
* /profile/edit → editProfile() 메서드 실행

### HTTP 메서드 매핑
```java
@RestController
@RequestMapping(value = "/api")
public class UserController {
    
    @RequestMapping(value = "/order")
    public String getOrder() {
        return "Order data";
    }
    
    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public String getUser() {
        return "User data";
    }
    
    @RequestMapping(value = "/user", method = RequestMethod.POST)
    public String createUser(@RequestBody User user) {
        return "User created";
    }
}
```
* GET, POST, HEAD, OPTIONS, PUT, PATCH, DELETE, TRACE 메서드를 설정할 수 있다.
* 타입 수준에서 사용되는 경우모든 메서드 수준 매핑은 해당 HTTP 메서드 설정을 상속받는다.
* 타입 수준과 메서드 수준이 함께 설정되었을 경우 메서드 수준의 HTTP 메서드 설정이 타입 수준의 설정을 덮어쓴다. 즉, 메서드 수준의 설정이 우선 적용된다.

### param 매핑
```java
@RestController
public class OrderController {
    
    @RequestMapping(value = "/order", params = "type=pizza")
    public String order1() {
        return " pizza ";
    }
    
    @RequestMapping(value = "/order", params = "type= chicken")
    public String order2() {
        return " chicken ";
    }
}
```
* 표현식은 != 연산자를 사용하여 부정할 수 있다.(예: "myParam!=myValue“)
* "myParam" 표현식도 지원되며요청에 존재해야 하지만어떤 값이라도 가질 수 있다.
* "!myParam" 표현식은 해당 매개변수가 요청에 포함되지 않아야 함을 나타낸다.

### headers 매핑
```java
@RestController
@RequestMapping("/api")
public class ContentTypeController {

    // Content-Type이 text/* 형식인 요청만 매핑
    @RequestMapping(value = "/text", method = RequestMethod.POST, headers = "content-type=text/*") // text/html, text/plain ..
    public String handleText() {
        return "text/* Content-Type";
    }
    
    // Content-Type이 application/json인 요청만 매핑
    @RequestMapping(value = "/json", method = RequestMethod.POST, headers = "content-type=application/json")
    // @RequestMapping(value = "/json", method = RequestMethod.POST, headers = "content-type!=application/json")
    public String handleJson() {
        return "application/json Content-Type";
    }
    
    // 헤더가 지정된 값을 가질 경우에만 요청이 매핑
    @RequestMapping(value = "/header", method = RequestMethod.GET, headers = "My-Header=myValue")
    public String handleNoContentType() {
        return "My-Header";
    }
}
```
* 타입 수준과 메서드 수준 모두에서 지원되며 타입 수준에서 사용되는 경우모든 메서드 수준 매핑은 이 헤더 설정을 상속받는다.

### produces와 consumes 설정

```java
@RequestMapping(value = "/report", method = RequestMethod.POST, consumes = "application/json")
public void saveReport(@RequestBody Report report) {
    // JSON 형식의 Report 데이터를 처리하는 로직
}
```
* consumes
  * 클라이언트가 서버로 전송하는 데이터의 형식을 제한한다.
  * 클라이언트에서 Content-Type: application/json 헤더를 지정하고 스프링에서 consumes = "application/json“ 으로 설정하면 해당 메서드는 JSON 형식의 데이터를 처리할 수 있다.

```java
@RequestMapping(value = "/report", method = RequestMethod.GET, produces = "application/json")
public Report getReport() {
    return new Report("title");
}
```
* produces
  * 클라이언트가 서버에 요청을 보낼 때 서버가 어떤 형식의 데이터를 반환 할지를 지정한다.
  * 클라이언트가 Accept: application/json 헤더를 지정하고 스프링에서 produces = "application/json"으로 설정하면 해당 메서드는 JSON 형식의 응답을 반환한다.

#### HTTP Content-Type / Accept 헤더 이해
1. consumes 속성과 Content-Type 헤더
   * 일치할 경우: 
     * 서버의 consumes 속성에 정의된 미디어 타입이 클라이언트의 Content-Type 헤더와 일치하는 경우 서버는 해당 요청을 정상적으로 처리한다.  consumes 속성은 서버가 수락할 수 있는 요청의 미디어 타입을 지정하며, 클라이언트는 그 형식으로 데이터를 서버에 전송해야 한다. 
   * 일치하지 않을 경우:
     * 만약 클라이언트가 전송한 요청의 Content-Type 헤더가 서버의 consumes 속성에 정의된 형식과 일치하지 않으면 서버는 HTTP 415 (Unsupported Media Type) 상태 코드를 반환할 수 있으며 이 상태 코드는 서버가 클라이언트가 보낸 데이터 형식을 처리할 수 없음을 나타낸다.
   * consumes 속성 미지정:
     *  만약 서버에서 consumes 속성을 지정하지 않았다면, 서버는 기본적으로 요청을 처리할 수 있는 미디어 타입에 대해 특별한 제한을 두지 않지만 클라이언트의 Content-Type 헤더와 서버의 처리 능력이 일치하지 않으면, 요청이 처리되지 않을 수 있다.

2. produces 속성과 Accept 헤더
   * 일치할 경우: 
     * 서버의 produces 속성에 정의된 미디어 타입이 클라이언트의 Accept 헤더와 일치하는 경우, 서버는 해당 미디어 타입으로 응답을 생성한다.
   * 일치하지 않을 경우: 
     * 만약 produces 속성에 정의된 미디어 타입이 클라이언트의 Accept 헤더와 일치하지 않으면, 서버는 HTTP 406 (Not Acceptable) 상태 코드를 반환할 수 있으며 이 상태 코드는 서버가 클라이언트가 요청한 형식으로 응답을 생성할 수 없음을 나타낸다.
   * produces 속성 미지정: 
     * 만약 서버에서 produces 속성을 지정하지 않았다면, 서버는 클라이언트의 Accept 헤더와 일치하는 미디어 타입으로 응답을 생성하려고 시도하며 이 경우 클라이언트가 요청한 형식과 서버가 반환할 수 있는 형식이 일치하면 그 형식으로 응답을 생성한다.

### RequestMappingHandlerMapping
* RequestMappingHandlerMapping은 @RequestMapping이 선언된 핸들러와 URL 경로를 매핑하고 검색하는 기능을 제공한다.
1) RequestMappingHandlerMapping은 스프링 부트의 초기화 과정에서 @RequestMapping이 선언된 모든 핸들러를 검사하고검사 결과 해당 핸들러와 URL 경로를 매핑하여 저장소에 저장한다.
2) 클라이언트 요청이 들어오면 매핑 저장소로부터 URL 패턴과 일치하는 핸들러를 검색하고 적합한 핸들러를 찾아 반환한다.

## @RequestMapping 원리 이해