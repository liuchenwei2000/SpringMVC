package rest;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import vo.Book;

import javax.servlet.http.HttpServletResponse;

/**
 * RESTfull 风格的 Controller
 * 
 * @author 刘晨伟
 * 
 * 创建日期：2015年5月19日
 */
@Controller 
@RequestMapping("/book") 
public class BookController {

	// {id} 是一个占位符，通过它会将变量数据传递给方法，对应了方法参数 id 的 @PathVariable 注解
	// @PathVariable 可以编写控制器方法来处理标识资源的 URL 而不是描述行为的 URL。
	// method 属性是描述 HTTP 方法的关键，它会用控制器的方法来进行处理指定类型 HTTP 请求。
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ModelAndView getBook(@PathVariable("id") String id) {
		ModelAndView mv = new ModelAndView("show");
		mv.addObject("message", "Book: Thinking in Java");
		return mv;
	}

	/**
	 * 使用 DELETE 删除资源
	 */
	// 如果方法的参数名与路径的变量名相同，可以省略 @PathVariable 的值。
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public @ResponseBody String deleteBook(@PathVariable String id) {
		return id + " has been deleted.";
	}

	/**
	 * 使用 POST 创建资源
	 */
	@RequestMapping(method = RequestMethod.POST)
	// 本例将响应状态设置为 HTTP 状态码 201，即资源被成功创建了。
	@ResponseStatus(HttpStatus.CREATED)
	public @ResponseBody String addBook(Book book, HttpServletResponse response) {
		return book.getName() + " has been added.";
	}

	/**
	 * 使用 PUT 更新资源
	 * <p>
	 * GET 请求将资源的状态从服务器转移到客户端，而 PUT 将资源的状态从客户端转移到服务器上。
	 */
	@RequestMapping(method = RequestMethod.PUT)
	// @ResponseStatus 注解定义了 HTTP 状态，这个状态要设置在发往客户端的响应中。
	// 本例将响应状态设置为 HTTP 状态码 204，即请求被成功处理了，但响应体中不包含任何返回信息。
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void updateBook(Book book) {
		System.out.println(book.getName() + " has been updated.");
	}
}
