package controller.annotation;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import util.SpringUtil;

/**
 * 首页页面控制器
 * 
 * @author 刘晨伟
 * 
 * 创建日期：2015年11月1日
 */
@Controller 
public class HomeController {
	
	@RequestMapping(value = { "/", "/home", "/index" })
	public ModelAndView index() {
        // 通过 SpringUtil 可以获取到 Spring 容器管理的 Bean
        System.out.println(SpringUtil.getBean("foo"));
        System.out.println(SpringUtil.getBean("bar"));
        return new ModelAndView("index");
	}
	
	@RequestMapping(value = { "/restful" })
	public ModelAndView restful() {
		return new ModelAndView("restful");
	}
}
