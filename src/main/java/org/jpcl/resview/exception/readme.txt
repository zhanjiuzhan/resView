15. spring boot的异常处理
	1） 实现 HandlerExceptionResolver
		HandlerExceptionResolver 的实现类会 catch 到 @Controller 方法执行时发生的异常，处理后返回 ModelAndView 作为结果视图，因此可以通过它来定制异常视图。
		HandlerExceptionResolver 只能捕获 @Controller 层发生的异常（包括 @Controller 调用 @Service 发生的异常），其他地方的异常，比如访问了一个不存在的路径，不会被 HandlerExceptionResolver 捕获，此时会跳到 ErrorController 处理
	2）@ControllerAdvice和@ExceptionHandler
		这种方式配置的异常处理由 HandlerExceptionResolver 的默认实现类 HandlerExceptionResolverComposite 处理，因此也只能捕获 @Controller 层的异常。
		1） 在controller上添加@ExceptionHandler注解可以进行异常的捕获
		2） 增强Controller使用@ControllerAdive注解 使用@ExceptionHandler进行全局的异常处理
		* 经过测试 当使用@ExceptionHandler的时候 对于同一个异常来说，自定义实现HandlerExceptionResolver的异常处理类会失效。 即ExceptionHandler优先将异常处理了没有处理的会走过来。
	3) ErrorController类
		如果没有配置 ErrorController, SpringBoot 会通过 ErrorMvcAutoConfiguration 自动配置一个，默认的实现类为 BasicErrorController。
		ErrorController 可以处理非 @Controller 层抛出的异常，例如常见的访问了一个不存在的路径。
		ErrorController 可以进行统一的错误处理，即让 HandlerExceptionResolver 返回的 ModelAndView 导向错误页面。
	* HandlerExceptionResolver @ExceptionHandler同时存在会优先@ExceptionHandler
