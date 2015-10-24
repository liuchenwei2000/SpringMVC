package annotations;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * 页面控制器实现，使用 annotation
 * 
 * @author 刘晨伟
 * 
 * 创建日期：2015年5月19日
 */
@Controller
// 如果在类上使用 @RequestMapping 注解一般是用于窄化功能处理方法映射的
@RequestMapping("/user") // ① 处理器的通用映射前缀        
public class UserController {
	
	// 相对于①处的映射进行窄化，即响应 /user/firstname 
	@RequestMapping("/firstname")
    public ModelAndView getFirstName() {
		ModelAndView mv = new ModelAndView();
		mv.addObject("firstname", "Bill");
		mv.setViewName("user");
		return mv;
	}
	
	@RequestMapping("/lastname")
    public ModelAndView getLastName() {
		ModelAndView mv = new ModelAndView();
		mv.addObject("lastname", "Gates");
		mv.setViewName("user");
		return mv;
	}
}