# 서블릿 이해
## 서블릿(Servlet)
### 개요
* 서블릿은 Jakarta EE (Enterprise Edition) 플랫폼의 핵심 기술 중 하나로클라이언트-서버 모델에서 서버 측에서 실행되는 작은 자바 프로그램이다.
* 주로 HTTP 요청과 응답을 처리하기 위해 사용되며자바 서블릿 API를 통해 웹 애플리케이션 개발을 쉽게 할 수 있도록 해 준다.

### 서블릿 생명주기 (Servlet Lifecycle)
* 서블릿은 서블릿 컨테이너에 의해 클래스 로드 및 객체 생성이 이루어지며 서블릿의 생명주기는 init, service, destroy 과정을 거친다

### 서블릿 로드 및 생성
* 서블릿 로드 - 서블릿 컨테이너는 서블릿을 처음으로 요청받거나 혹은 애플리케이션이 시작될 때 서블릿 메모리에 로드한다.
  * 지연 로딩 - 첫 번째 클라이언트 요청이 들어올 때 서블릿이 로드되고 서블릿 객체를 생성한다.
  * 즉시 로딩 - 애플리케이션이 시작될 때 서블릿이 로드되고 객체가 생성된다.

* load-on-startup
  * 음수 값 또는 설정하지 않은 경우: 서블릿은 지연 로딩된다.
  * 양수 값은 즉시 서블릿이 로딩되며 값이 낮을 수록 더 높은 우선순위를 가진다.
  * 0 값은 애플리케이션 시작시 서블릿을 로드하지만 양수 값들보다 우선하지 않을 수 있다.

### 서블릿 생명 주기
* init()
  * 서블릿이 생성되고 init 메서드를 통해 초기화 되며 최초 한 번만 호출된다.
  * 주로 초기화 파라미터를 읽거나 DB 연결 설정 등 초기 설정 작업을 수행한다.
* service()
  * 클라이언트로부터의 모든 요청은 service 메서드를 통해 처리되며 HttpServletRequest와 HttpServletResponse 객체가 생성되어 전달 된다.
  * Http 메서드(GET, POST 등)에 따라 doGet, doPost 등의 메서드를 호출한다.
* destroy()
  * 서블릿이 서비스에서 제외되면 destroy() 메서드를 통해 종료되고 이후 가비지 컬렉션 및 finalize 과정을 거친다.

## HttpServletRequest
### 개요
* HttpServletRequest는 클라이언트로부터 Http 요청이 들어오면 요청 데이터를 분석하고 분석한 정보들이 저장되어 HttpServletResponse와 함께 서블릿으로 전달되는 객체이다.

### HttpServletRequest 생성
1. org.apache.coyote.Request 객체 생성
   * 낮은 수준의 HTTP 요청 처리를 담당하여 서블릿 컨테이너와 독립적으로 동작
2. org.apache.catalina.connector.Request 객체 생성
   * 서블릿 API 규격을 구현하고 고수준 요청 데이터를 처리
3. org.apache.catalina.connector.RequestFacade 객체 생성
   * 캡슐화를 통해 서블릿 API 사용을 표준화하고 내부 구현을 보호

## HttpServletRequest 기본
### 개요
* HttpServletRequest는 클라이언트 요청에 대한 서블릿 API의 핵심 인터페이스로서 개발 시 자주 활용되거나 기본적으로 숙지해야할 API들이 있다.

#### Header 관련
1. getHeader(String name)
2. getHeaders(String name)
3. getHeaderNames()

#### 요청 메서드 및 경로 관련
1. getMethod()
2. getRequestURI()
3. getRequestURL()
4. getContextPath()
5. getServletPath()
6. getPathInfo()

#### 세션 관련
1. getSession(boolean create)
2. getSession()
3. getRequestedSessionId()

#### 속성 관련
1. getAttribute(String name)
2. getAttributeNames()
3. setAttribute(String name, Object value)
4. removeAttribute(String name)

#### 원격 및 로컬 정보 관련
1. getRemoteAddr()
2. getRemoteHost()
3. getRemotePort()
4. getLocalAddr()
5. getLocalName()
6. getLocalPort()

#### 기타 메타데이터 관련
1. getContentType()
2. getContentLength()
3. getProtocol()
4. getScheme()
5. getServerName()
6. getServerPort()

## HttpServletRequest 요청 처리
### 개요
* HttpServletRequest 는 클라이언트의 다양한 데이터 포맷과 요청 유형에 따라 정보를 읽고 처리할 수 있는 API 를 제공한다.
* 요청을 처리하는 방식으로 URL 쿼리 파라미터, 폼 데이터(GET/POST), REST API 처리 등 세 가지로 나누어 구분 할 수 있다.

### URL 쿼리 파라미터
* URL 쿼리 파라미터는 HTTP 요청의 쿼리 문자열(Query String)에 포함되어 전달되며 이는 보통 GET 요청에서 사용된다.

### FROM 데이터 처리
* FORM 데이터는 HTML <form> 태그를 통해 클라이언트에서 서버로 전달되며데이터는 요청 메서드에 따라 다르게 처리된다.

* GET 방식
  * 요청 데이터가 URL의 쿼리 문자열에 포함된다
  * 전송 데이터의 크기는 URL 길이 제한에 의해 제약을 받지 않고URL이 노출되므로 민감한 데이터 전송에 적합하지 않다
* POST 방식
  * 요청 데이터가 HTTP 요청 본문에 포함된다
  * Content-Type
    * application/x-www-form-urlencoded - 기본 폼 데이터 전송 방식으로서키-값 쌍이 URL 인코딩된 형태로 전달된다
    * Body: name=leaven&hobby=reading&hobby=writing

### REST API 데이터 처리
* REST API 요청은 클라이언트가 JSON 또는 Plain Text 형태의 데이터를 HTTP 요청 본문에 포함하여 서버로 전송하는 방식으로서 getParameter() 와 같은 방법이 아닌 InputStream 으로부터 본문 데이터를 직접 읽어야 한다.
* JSON 데이터
  * Content-Type: application/json
  * 요청 본문에 JSON 형식으로 데이터를 포함한다
  * JSON 데이터를 처리하려면 요청 본문을 파싱해야 한다
* Plain Text 데이터
  * Content-Type: text/plain
  * 요청 본문에 단순 문자열 데이터를 포함한다

## HttpServletResponse
### 개요
* HttpServletResponse 는 서버가 클라이언트의 요청에 대한 응답을 생성하고 반환할 때 사용되며 HTTP 응답의 상태 코드, 헤더, 본문 데이터를 설정하고 제어하는 다양한 메서드를 제공한다.

### HttpServletResponse 생성
1. org.apache.coyote.Response 객체 생성
  * 낮은 수준의 HTTP 응답 처리를 담당하여 서블릿 컨테이너와 독립적으로 동작
2. org.apache.catalina.connector.Response 객체 생성
  * 서블릿 API 규격을 구현하고 고수준 응답 데이터를 처리
3. org.apache.catalina.connector.ResponseFacade 객체 생성
  * 캡슐화를 통해 서블릿 API 사용을 표준화하고 내부 구현을 보호

## HttpServletResponse 요청 처리
### 개요
* HttpServletResponse는 응답을 처리하는 방식으로 단순 텍스트 응답, HTML 화면 처리 응답, HTTP 본문 응답 등 세 가지로 나누어 구분 할 수 있다.
* 스프링에서도 응답 패턴이 이 세 가지 범주에서 크게 벗어나지 않으며 사용하기 쉽게 추상화 해서 제공하고 있다.
