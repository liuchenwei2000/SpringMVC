package controller.annotation;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import util.SpringUtil;
import vo.Book;

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

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView getBook() {
		ModelAndView mv = new ModelAndView("show");
		mv.addObject("message", "Book: Thinking in Java");
		return mv;
	}

	@RequestMapping(method = RequestMethod.POST)
	public @ResponseBody String addBook(Book book) {
		return book.getName() + " has been added.";
	}

	@RequestMapping(method = RequestMethod.PUT)
	public @ResponseBody String updateBook(Book book) {
		return book.getName() + " has been updated.";
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public @ResponseBody String deleteBook(@PathVariable String id) {
		return id + " has been deleted.";
	}
}
