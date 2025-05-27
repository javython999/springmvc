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
## HttpServletRequest 기본
## HttpServletRequest 요청 처리
## HttpServletResponse
## HttpServletResponse 요청 처리