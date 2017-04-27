package rest.client;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import vo.Book;

/**
 * Spring 的 RestTemplate 所做的事情，就像 JdbcTemplate 处理 JDBC
 * 数据访问时的复杂部分，也能让开发者在使用 TESTful 资源时免于乏味模板化的代码。
 * <p>
 * RestTemplate 定义了 33 个与 REST 资源交互的方法，涵盖了 HTTP 动作的各种形式。
 * 其实这里面只有 11 个独立的方法，而每一个都有 3 个重载的变种：
 * <li>一个使用 URI 作为 URL 格式，不支持参数化 URL
 * <li>一个使用 String 作为 URL 格式，并使用 Map 指明 URL 参数
 * <li>一个使用 String 作为 URL 格式，并使用可变参数列表指明 URL 参数
 * <p>
 * 除此之外，execute() 和 exchange() 提供了较低层次的通用方法来使用任意的 HTTP 方法。
 * <p>
 * Created by liuchenwei on 2016/4/14.
 */
public class SpringRestTemplate {

    /**
     * GET
     *
     * 主要有两种执行 GET 请求的方法：getForObject() 和 getForEntity()。
     * 除了返回类型，它们的工作方式基本一样，都是执行根据 URL 检索资源的 GET 请求。
     * 它们都将资源根据 responseType 参数匹配为一定的类型。唯一的区别在于
     * getForObject() 只返回所请求类型的对象，而 getForEntity() 会返回请求的对象以及相应相关的额外信息。
     */

    /**
     * 根据 id 查询相应的 Book 对象
     */
    public Book retrieveBookById(String id) {
        String url = "http://localhost:8080/SpringMVC/api/book/{id}";
        // getForObject() 方法请求一个资源并以所指定的 Java 类型接收该资源
        // 方法的最后一个参数是可变参数列表，每个参数都会按出现顺序插入到指定 URL 的占位符中
        return new RestTemplate().getForObject(url, Book.class, id);

        // 替代方案是将参数值放入 Map 中，并以占位符作为 key，然后将 Map 作为最后一个参数传给 getForObject()
//        Map<String, String> urlVariables = new HashMap<>();
//        urlVariables.put("id", id);
//        return new RestTemplate().getForObject(url, Book.class, urlVariables);
    }

    /**
     * 获取所有 Book 对象
     */
    public Book[] retrieveBooks() {
        String url = "http://localhost:8080/SpringMVC/api/books";
        return new RestTemplate().getForObject(url, Book[].class);
    }

    /**
     * 根据 id 查询相应的 Book 对象
     */
    public Book retrieveBookById2(String id) {
        String url = "http://localhost:8080/SpringMVC/api/book/{id}";
        // getForEntity() 方法在 ResponseEntity 中返回对象，还带有关于响应的额外信息，如 HTTP 状态码和响应头。
        // ResponseEntity 的一个用途是获取响应头的一个值，比如最后修改时间。
        ResponseEntity<Book> responseEntity = new RestTemplate().getForEntity(url, Book.class, id);
        // 获取 HTTP 状态码
        System.out.println("StatusCode:" + responseEntity.getStatusCode());
        // HttpHeaders 对象提供了多个便利的方法来查询响应头
        HttpHeaders headers = responseEntity.getHeaders();

        printHeaders(headers);

        return responseEntity.getBody();// 返回响应中的对象
    }

    private void printHeaders(HttpHeaders headers) {
        System.out.println("Accept:" + headers.getAccept());
        System.out.println("AcceptCharset:" + headers.getAcceptCharset());
        System.out.println("Allow:" + headers.getAllow());
        System.out.println("CacheControl:" + headers.getCacheControl());
        System.out.println("ContentType:" + headers.getContentType());
        System.out.println("Date:" + headers.getDate());
        System.out.println("LastModified:" + headers.getLastModified());
        System.out.println("Location:" + headers.getLocation());
        // HttpHeaders 还提供了 get() 和 getFirst() 方法来实现更通用的 HTTP 头信息访问。
        // 两个方法都接受 String 参数来标识头信息，get() 将会返回一个 String 值得列表，
        // 每个值都是赋予这个头信息的；getFirst() 方法只会返回头信息的第一个值。
        System.out.println("First Accept:" + headers.getFirst("Accept"));
    }

    /**
     * POST
     *
     * 主要有三种执行 POST 请求的方法：postForObject()、postForEntity() 和 postForLocation()。
     * 这三种方法的第一个参数都是资源要 POST 到的 URL，第二个参数是要 POST 的对象，
     * 第三个参数是预期返回的 Java 类型，第四个参数指定了 URL 变量（可变参数列表或 Map）。
     */

    /**
     * 新增 Book 对象
     */
    public Book addNewBook(Book book) {
        String url = "http://localhost:8080/SpringMVC/api/bookv2";
        return new RestTemplate().postForObject(url, book, Book.class);
    }

    public Book addNewBook2(Book book) {
        String url = "http://localhost:8080/SpringMVC/api/bookv2";
        // postForEntity() 方法在 ResponseEntity 中返回对象，还带有关于响应的额外信息。
        ResponseEntity<Book> responseEntity = new RestTemplate().postForEntity(url, book, Book.class);
        System.out.println(responseEntity.getHeaders().getLocation());// 获取相应的一些元数据
        return responseEntity.getBody();// 返回从服务器获取的对象
    }

    public String addNewBook3(Book book) {
        String url = "http://localhost:8080/SpringMVC/api/bookv3";
        // POST 通常并不需要将资源发送回来，如果真正需要的是 Location 头信息的值，可以直接使用 postForLocation()
        // 它也是将资源 POST 到服务器端，但是响应不再是相同的资源对象，而是新创建资源的位置。
        return new RestTemplate().postForLocation(url, book).toString();// 返回从服务器获取的对象
    }

    /**
     * PUT
     *
     * 在所有版本的 put() 方法中，第二个参数都是表示资源的 Java 对象，它将按照指定 URL 发送到服务器。
     * 对象将被转换成什么内容类型很大程度上取决于传递给 put() 方法的类型：
     * 如果传入一个 String 值，那么这个值直接被写到请求体重，内容类型设置为 text/plain。
     * 如果传入一个 Map，那么这个 Map 的值将会以 application/x-www-form-urlencoded 的格式写到请求体中。
     * 如果传入一个 Book 对象，就需要一个能够处理任意对象的信息转换器，如果 classpath 下包含 Jackson JSON 库，
     * 那么 Book 对象将以 application/json 格式写到请求中，如果 Book 类使用了 JAXB 序列化注解并且 JAXB 在类路径中，
     * 那么 Book 对象将会作为 application/xml 发送，并且以 XML 的格式写到请求体中。
     */

    /**
     * 更新指定的 Book 对象
     */
    public void updateBook(Book book) {
        String url = "http://localhost:8080/SpringMVC/api/book";
        new RestTemplate().put(url, book);
    }

    /**
     * DELETE
     *
     * delete() 方法是最简单的，只需要提供要删除资源的 URL 即可。
     */

    /**
     * 删除指定 id 的 Book 对象
     */
    public void deleteBookById(String id) {
        String url = "http://localhost:8080/SpringMVC/api/book/{id}";
        new RestTemplate().delete(url, id);
    }

    /**
     * exchange() 方法可以在发送给服务端的请求中设置头信息。
     */
    public void retrieveBookById3(String id) {
        String url = "http://localhost:8080/SpringMVC/api/book/{id}";
        // 第二个参数表明要使用的 HTTP 动作，根据这个参数值，exchange() 能够执行与 RestTemplate 其他方法一样的工作。
        // 第三个参数是在请求中发送的资源和头信息，GET 请求不需要，POST、GET 请求则需要该参数值。
        // 第四个参数是预期返回的 Java 类型，第五个参数指定了 URL 变量（可变参数列表或 Map）。
        new RestTemplate().exchange(url, HttpMethod.GET, null, Book.class, id);
    }

    public void retrieveBookById4(String id) {
        String url = "http://localhost:8080/SpringMVC/api/book/{id}";
        // 如果不指名头信息，exchange() 执行 GET 请求默认带有一份头信息，比如：
        // Accept:applicsation/xml, text/xml, application/json
        // 这就为服务器留有余地来决定采用哪种格式返回资源，如果只希望接收 JSON 格式资源的话，
        // 需要指明 application/json 是 Accept 头信息的唯一值。可以通过下面的方式：
        MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
        headers.add("Accept", "application/json");
        HttpEntity<Object> requestEntity = new HttpEntity<>(headers);
        // 如果这是一个 PUT 或 POST 请求，则需要为 HttpEntity 设置在请求体中发送的对象，对 GET 请求则没有必要。
//        HttpEntity<Object> requestEntity = new HttpEntity<>(book, headers);
        new RestTemplate().exchange(url, HttpMethod.GET, requestEntity, Book.class, id);
    }

    public static void main(String[] args) {
        SpringRestTemplate t = new SpringRestTemplate();

        System.out.println(t.retrieveBookById("0001"));
        System.out.println(t.retrieveBookById2("0001"));
        System.out.println(t.retrieveBooks());

        t.updateBook(new Book("Thinking in Java"));
        t.deleteBookById("0001");

        System.out.println(t.addNewBook(new Book("Spring in Action")));
        System.out.println(t.addNewBook2(new Book("Spring in Action")));
        System.out.println(t.addNewBook3(new Book("Spring in Action")));
    }
}
