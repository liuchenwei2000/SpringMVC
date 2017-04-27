package controller.annotation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private static Logger LOGGER = LoggerFactory.getLogger(HomeController.class);
	
	@RequestMapping(value = { "/", "/home", "/index" })
	public ModelAndView index() {
        // 通过 SpringUtil 可以获取到 Spring 容器管理的 Bean
        LOGGER.info(SpringUtil.getBean("foo").toString());
        LOGGER.info(SpringUtil.getBean("bar").toString());
        return new ModelAndView("index");
	}
	
	@RequestMapping(value = { "/restful" })
	public ModelAndView restful() {
		return new ModelAndView("restful");
	}
}
