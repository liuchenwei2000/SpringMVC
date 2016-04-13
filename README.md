## SpringMVC ##

![](http://imgsrc.baidu.com/forum/w%3D580/sign=64336c916c63f6241c5d390bb745eb32/cc6abbd6277f9e2f9124faa01830e924b999f363.jpg)

**请求**（request）旅程的第一站是 SpringMVC 的 **DispatcherServlet** 。与大多数基于 Java 的 Web 框架一样，所有的请求都会通过一个**前端控制器** Servlet，前端控制器是常用的 Web 应用程序模式，在这里一个单实例的 Servlet 将请求委托给应用程序的其他组件来执行实际的处理。在 SpringMVC 中，DispatcherServlet 就是前端控制器。

DispatcherServlet 的任务是将请求发送给 SpringMVC **控制器**（Controller）。控制器是一个用于处理请求的 Spring 组件。在典型的应用程序中可能会有多个控制器，DispatcherServlet 需要知道应该将请求发送给那个控制器。所以 DispatcherServlet 会查询一个或多个**处理器映射**（Handler mapping）来确定请求的下一站在哪里。处理器映射会根据请求所携带的 URL 信息来进行决策。

一旦选择了合适的控制器，DispatcherServlet 会将请求发送给选中的控制器。到达了控制器，请求会卸下其负载（用户提交的信息）并等待控制器处理这些信息（实际上，设计良好的控制器本身只处理很少甚至不处理信息，而是将业务逻辑委托给一个或多个服务对象）。

控制器在完成逻辑处理后，通常会产生一些信息，这些信息需要返回给用户并在浏览器上显示。这些信息被称为**模型**（model）。通常模型会以用户友好的方式进行格式化，一般是 HTML。所以，模型需要发送给一个**视图**（view），通常会是 JSP。

控制器所做的最后一件事是将模型数据打包，并且标示出用于渲染输出的视图名称，接下来会将请求连同模型和视图名称发送回 DispatcherServlet。这样一来，控制器就不会与特定的视图相耦合，传递给 DispatcherServlet 的视图名称并不直接表示某个特定的 JSP。实际上，它甚至并不能确定视图是 JSP。相反，它仅仅传递了一个逻辑名，这个名字将会用来查找渲染模型的真正视图。DispatcherServlet 将会使用**视图解析器**（ViewResolver）来将逻辑视图名匹配为一个特定的视图实现，它可能是也可能不是 JSP。

既然 DispatcherServlet 已经知道由哪个视图渲染结果，那么请求的任务基本上也就完成了。它的最后一站是视图的实现（比如 JSP），在这里它交付模型数据。请求的任务完成了。视图将使用模型数据渲染输出，并通过这个输出将**响应**（response）对象传递给客户端。