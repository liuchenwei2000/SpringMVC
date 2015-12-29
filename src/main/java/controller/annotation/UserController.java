package controller.annotation;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 * 页面控制器实现，使用 annotation
 * 
 * @author 刘晨伟
 * 
 * 创建日期：2015年5月19日
 */
@Controller 
// 如果在类上使用 @RequestMapping 注解一般是用于窄化功能处理方法的映射的
@RequestMapping("/user") // ① 处理器的通用映射前缀        
public class UserController {
	
	// 相对于①处的映射进行窄化，即响应 /user/firstname
	@RequestMapping("/firstname")
	public ModelAndView getFirstName() {
		ModelAndView mv = new ModelAndView();
		mv.addObject("message", "Bill");
		mv.setViewName("show");
		return mv;
	}

	// 限定只处理 GET 方式的请求
	@RequestMapping(value = "/lastname", method = RequestMethod.GET)
	public ModelAndView getLastName() {
		ModelAndView mv = new ModelAndView();
		mv.addObject("message", "Gates");
		mv.setViewName("show");
		return mv;
	}
	
	// 既可以处理 POST 又可以处理 GET 方式的请求
	@RequestMapping(value = "/all", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView all() {
		ModelAndView mv = new ModelAndView();
		mv.addObject("message", "Bill Gates");
		mv.setViewName("show");
		return mv;
	}
	
	// GET 请求中有 "query" 参数即可匹配，如 "http://×××/user?query=a"
	@RequestMapping(params = "query", method = RequestMethod.GET)
	public ModelAndView query(@RequestParam("query") String param) {
		ModelAndView mv = new ModelAndView();
		mv.addObject("message", param);
		mv.setViewName("show");
		return mv;
	}

	// URI 模板模式映射：{userid} 是占位符， 请求的 URL 可以是 "/delete/123456"或 "/users/abcd"
	// 通过 @PathVariable 可以提取 URI 模板模式中 {userid} 的 userid 变量值。
	@RequestMapping("/delete/{userid}")
	public ModelAndView deleteUser(@PathVariable String userid) {
		ModelAndView mv = new ModelAndView();
		mv.addObject("message", userid);
		mv.setViewName("show");
		return mv;
	}
}
