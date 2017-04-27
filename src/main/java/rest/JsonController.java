package rest;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import vo.Book;
import vo.User;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * 使用 HTTP 信息转换器将资源的 Java 表述形式转换为发送给客户端的表述形式
 * <p>
 * 发送给客户端的表述形式以 JSON 和 XML 为例子。
 *
 * @author liuchenwei
 * @date 2016/4/13
 * @since 1.0
 */
@Controller
@RequestMapping("/api")
public class JsonController {

    @RequestMapping
    public ModelAndView index() {
        return new ModelAndView("api");
    }

    /**
     * 如上面的 index() 方法所示。
     * 典型的控制器方法在结束的时候会将一些信息放在模型中，然后返回一个视图来为用户渲染这些数据。
     *
     * 当控制器的工作是产生资源表述的时候（只产生数据，不关心视图），可以使用 @ResponseBody 注解绕过模型和数据。
     * 这表明 HTTP 信息转换器机制会发挥作用，将返回的对象将自动转化为适合客户端的表述形式（如 JSON、XML 等）。
     * Spring 自带多种 HTTP 信息转换器，用于实现资源表述与各种 Java 类型之间的相互转换。
     * 比如 MappingJackson2HttpMessageConverter 用于支持 JSON，Jaxb2RootElementHttpMessageConverter 用于支持 XML。
     * 这些转换器是自动注册的，使用它们的话，只需要将添加相应的库到 classpath 下即可。
     * 比如 JSON 用到 jackson-core、jackson-databind、jackson-annotations，XML 用到 jaxb-core。详见 pom.xml。
     */

    /**
     * headers 属性表明该方法只处理 Accept 头部信息为 application/json 和 text/xml 的请求。
     * 其他任何类型的请求，即使它的 URL 匹配指定的路径并且是 GET 请求也不会被这个方法处理。
     */
    @RequestMapping(value = "/book/{id}", method = RequestMethod.GET,
            headers = {"Accept=application/json,text/xml"})
    @ResponseBody
    public Book getBook(@PathVariable String id) {
        Book book = new Book();
        book.setId(id);
        book.setName("Spring in Action");
        return book;
    }

    /**
     * 使用 produces 属性实际上和 headers 属性的效果是一样的，但是这种方式更能表明目的。
     */
    @RequestMapping(value = "/user/{id}", method = RequestMethod.GET,
            produces = {MediaType.TEXT_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public User getUser(@PathVariable String id) {
        User user = new User();
        user.setId(id);
        user.setCode("MS0001");
        user.setName("Bill Gates");
        return user;
    }

    /**
     * 下面两个方法只支持 JSON。
     */
    @RequestMapping(value = "/books", method = RequestMethod.GET,
            headers = {"Accept=application/json"})
    @ResponseBody
    public List<Book> getBooks() {
        List<Book> books = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            Book book = new Book();
            book.setId("id" + i);
            book.setName("Spring in Action V" + i);
            books.add(book);
        }
        return books;
    }

    @RequestMapping(value = "/users", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<User> getUsers() {
        List<User> users = new ArrayList<User>();
        for (int i = 0; i < 3; i++) {
            users.add(new User("id000" + i, "code000" + i, "name000" + i));
        }
        return users;
    }

    /**
     * 在请求体中接收资源状态
     *
     * REST 客户端可能会以 JSON、XML 或其他内容格式发送一个对象过来，
     * 如果控制器以原始形式来接收数据并进行转换的话，实在麻烦。
     * 与 @ResponseBody 一样，@RequestBody 注解能够对客户端发过来的对象做相同的事情。
     *
     * 本方法发现抵达的信息是 JSON 格式，而方法参数要求的是 Java 对象，
     * 此时，它会选择 MappingJackson2HttpMessageConverter 将 JSON 转换为 Java 对象。
     * 需要注意的是，请求的 Content-Type 头信息必须是 application/json。
     *
     * 本例接收一个 JSON 对象，然后返回给客户端一个 XML 对象。
     */
    @RequestMapping(value = "/book", method = RequestMethod.POST,
            produces = MediaType.APPLICATION_XML_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Book addBook(@RequestBody Book book) {
        book.setId("id000X");
        return book;
    }

    /**
     * 使用 PUT 更新资源
     */
    @RequestMapping(value = "/book", method = RequestMethod.PUT)
    @ResponseBody
    public void updateBook(@RequestBody Book book) {
        System.out.println(book.getName() + " has been updated.");
    }

    /**
     * 使用 DELETE 删除资源
     */
    @RequestMapping(value = "/book/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public void deleteBook(@PathVariable String id) {
        System.out.println("Book " + id + " has been deleted.");
    }

    /**
     * 使用 POST 创建资源
     */
    @RequestMapping(value = "/bookv2", method = RequestMethod.POST)
    @ResponseBody
    public Book addBook2(@RequestBody Book book) {
        book.setId("id000X");
        return book;
    }

    /**
     * 使用 POST 创建资源
     */
    @RequestMapping(value = "/bookv3", method = RequestMethod.POST)
    @ResponseBody
    public Book addBook3(@RequestBody Book book, HttpServletResponse response) {
        book.setId("id000X");
        // 设置 Location 头信息来包含资源的 URL
        response.setHeader("Location", "/api/book/" + book.getId());
        return book;
    }
}
