package rest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import vo.Book;

/**
 * RESTlessController
 * <p>
 * 本例不是一个 RESTful 的控制器，它是面向行为的并关注于一个特殊的用例：
 * 以 HTML 的形式展现一个 Book 对象的详细信息。
 * 
 * @author 刘晨伟
 * 
 * 创建日期：2015年11月1日
 */
@Controller
@RequestMapping("/showBook.html")
// 第一个单词是 show，一个动词，表明该控制器是面向行为而不是资源。
// 扩展名说明它只能以 HTML 的形式来展现对象。
public class RESTlessController {

	@RequestMapping(method = RequestMethod.GET)
	public String showBook(String bookid, Model model) {
		Book book = new Book();
		book.setId(bookid);
		book.setName("Thinking in Java");
		model.addAttribute(book);
		return "show";
	}
}
