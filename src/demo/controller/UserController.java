/**
 * 
 */
package demo.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import demo.service.UserService;

/**
 * 
 * <p>
 * 
 * 
 * @author 刘晨伟
 * 
 * 创建日期：2015年10月22日
 */
@Controller
@RequestMapping("/duser")
public class UserController {

	@Resource
	private UserService userService;
	
	@RequestMapping("/index")
    public ModelAndView index() {
		ModelAndView mv = new ModelAndView();
		mv.addObject("firstname", userService.getUser());
		mv.setViewName("user");
		return mv;
	}
}
