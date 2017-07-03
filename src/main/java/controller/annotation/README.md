
Spring2.5 引入注解式处理器支持，通过 @Controller 和 @RequestMapping 注解定义处理器类，并且提供了一组强大的注解：

* @Controller：用于标识是处理器类；
* @RequestMapping：请求到处理器功能方法的映射规则；
* @RequestParam：请求参数到处理器功能处理方法的方法参数上的绑定；
* @ModelAttribute：请求参数到命令对象的绑定；
* @SessionAttributes：用于声明 session 级别存储的属性，放置在处理器类上，通常列出模型属性（如
* @ModelAttribute：对应的名称，则这些属性会透明的保存到 session 中；
* @InitBinder：自定义数据绑定注册支持，用于将请求参数转换到命令对象属性的对应类型；


Spring3.0 引入 RESTful 架构风格支持(通过@PathVariable 注解和一些其他特性支持),且又引入了更多的注解支持：

* @CookieValue：cookie 数据到处理器功能处理方法的方法参数上的绑定；
* @RequestHeader：请求头（header）数据到处理器功能处理方法的方法参数上的绑定；
* @RequestBody：请求的 body 体的绑定（通过 HttpMessageConverter 进行类型转换） ；
* @ResponseBody：处理器功能处理方法的返回值作为响应体（通过 HttpMessageConverter 进行类型转换） ；
* @ResponseStatus：定义处理器功能处理方法/异常处理器返回的状态码和原因；
* @ExceptionHandler：注解式声明异常处理器；
* @PathVariable：请求 URI 中的模板变量部分到处理器功能处理方法的方法参数上的绑定，从而支持 RESTful 架构风格的 URI；


### 常用注解详解

* @Controller

    @Controller注解在类上，表明这个类是Spring MVC里面的Controller，将其声明为Spring的一个Bean，DispatcherServlet会自动扫描注解了此注解的类，并将请求映射到注解了@RequestMapping的方法上，这里特别指出，在声明普通Bean的时候，使用@Component，@Service，@Repository和@Controller是等同的，因为@Controller，@Service，@Repository都组合了@Component元注解，但在Spring MVC声明控制器Bean的时候，只能使用@Controller。

* @RequestMapping

    @RequestMapping注解是用来映射Web请求(访问路径和参数)，处理类和方法的。@RequestMapping可注解在类或者方法上。注解在方法上的@RequestMapping路径会继承注解在类上的路径，@RequestMapping支持Servlet的request和response作为参数，也支持对request和response的媒体类型进行配置。

* @ResponseBody

    @ResponseBody支持将返回值放在response体内，而不是返回一个页面。我们在很多基于Ajax程序的时候，可以以此注解返回数据而不是页面；此注解可放置在返回值前或者方法上。

* @RequestBody

    @RequestBod允许request的参数在request体内，而不是直接链接在地址后面。此注解放置在参数前。

* @PathVariable

    @PathVariable用来接收路径参数，如/news/001，可接收001作为参数，此注解放置在参数前。

* @RestController

    @RestController是一个组合注解，组合了@Controller和@ResponseBody，这就意味着当你只开发一个和页面交互数据的控制的时候，需要使用此注解。若没有此注解，要想实现上述功能，则需要自己在代码中加@Controller和@ResponseBody两个注解。
