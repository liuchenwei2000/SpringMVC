## REST ##

Spring 对 REST（Representational State Transfer，表述性状态转移）的支持是构建在 SpringMVC 之上的。

REST 与 RPC 几乎没有任何关系。RPC 是面向服务的，并关注于行为和动作；而 REST 是面向资源的，强调描述应用程序的事物和名词。尽管 URL 在 REST 中起了关键作用，但它们仅仅是整体的一部分而已。更简洁地讲，REST 就是将资源的状态以最合适的形式从服务器转移到客户端（或者反之）。

* 表述性（Representational） REST 资源实际上可以用各种形式来进行表述，包括 XML、JSON 甚至 HTML——最适合资源使用者的任意形式。

* 状态（State） 当使用 REST 的时候，更关注资源的状态而不是对资源采取的行为。

* 转移（Transfer） REST 涉及转移资源数据，它以某一种表述性形式从一个应用转移到另一个应用。

### RESTful URL

	http://localhost:8080/SpringMVC/showBook.html?id=123

这个 URL 并没有定位或标识资源，它要求服务器展现一个 Book。URL 中唯一的标识就是 id 查询参数，URL 的基础部分是面向动作的（show是个动词），所以说这是一个 RESTless 的 URL。（RESTless 的 URL 是面向行为的并不标识或定位资源）

	http://localhost:8080/SpringMVC/books/123

RESTful URL 完全承认 HTTP 是用于标识资源的,这个 URL 并不做任何事情，只是用来标识一个资源，它定位一个表现为 Book 对象的资源。对这个资源做什么是另外一件事——这是由 HTTP 方法决定的。（RESTful 的 URL 是面向资源的，可以标识和定位资源）

这个 URL 不仅定位资源还可以唯一标识这个资源，使用完整的基本 URL 来标识资源，而不是使用查询参数标识资源，查询参数应当用于为服务器创建资源提供指导。

另外，RESTful URL 是有层级的，从左向右是一个从抽象到具体的过程：

* `http://localhost:8080` 标识了域和端口。
* `http://localhost:8080/SpringMVC` 标识应用程序的 Servlet 上下文，指明了运行在服务器上的一个应用程序。
* `http://localhost:8080/SpringMVC/books` 标识了一种资源，也就是 Book 的对象列表。
* `http://localhost:8080/SpringMVC/books/123` 最精确的 URL，标识了一个特定的 Book 资源。

RESTful URL 的路径是参数化的，RESTless URL 使用查询参数作为输入，而 RESTful URL 的输入时 URL 路径的一部分。为了处理这种类型的 URL，需要一种能够从 URL 路径中获取输入的方式来编写控制器处理方法。为此，Spring MVC 提供了 **@PathVariable** 注解。

### REST 动作

对于任意给定的资源，最常见的操作是在服务器上对资源进行CRUD（创建、查询、更新、删除），这些动作直接对应于 HTTP 规范定义的 4 个方法——GET 方法对应查询，DELETE 方法对应删除，POST 方法对应创建，PUT 方法对应更新。

每个 HTTP 方法都具有两个特性：安全性和幂等性。如果一个方法不改变资源的状态，则认为它是安全的。幂等的方法可能改变也可能不改变状态，但一次请求和多次请求具有相同的作用。按照定义，所有安全的方法都必须是幂等的，但并不是所有幂等的方法都是安全的。

* GET方法 从服务器上检索资源，资源通过请求的 URL 来标识，安全的，幂等的。
* POST方法 提交数据到服务器，数据会由负责响应该请求 URL 的控制器来处理，非安全的，非幂等的。
* PUT方法 按照请求的 URL 更新服务器上的资源，非安全的，幂等的。
* DELETE方法 将请求 URL 标识的资源从服务器上删除，非安全的，幂等的。

尽管 SpringMVC 支持所有的 HTTP 方法，但要由开发者来保证这些方法的实现遵循了方法的语义。也就是说，一个 GET 的请求的处理方法应该只是返回资源，不应该更新或删除资源。通过使用 SpringMVC 的 @RequestMapping 注解的 method 属性可以设置其处理的 HTTP 方法。