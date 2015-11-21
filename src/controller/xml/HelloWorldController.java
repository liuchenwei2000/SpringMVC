package controller.xml;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

/**
 * 页面控制器实现
 * <p>
 * Spring2.5 之前，都是通过实现 Controller 接口或其实现来定义控制器类。
 * 
 * @author 刘晨伟
 * 
 * 创建日期：2015年5月19日
 */
public class HelloWorldController implements Controller {
	
    @Override
    public ModelAndView handleRequest(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		// 1、收集参数、验证参数
		// 2、绑定参数到命令对象
		// 3、将命令对象传入业务对象进行业务处理
		// 4、选择下一个页面
		ModelAndView mv = new ModelAndView();
		// 添加模型数据，可以是任意的POJO。
		mv.addObject("message", "Hello World!");
		// 设置逻辑视图名，视图解析器（ViewResolver）会根据该名字解析出具体的视图页面。
		mv.setViewName("hello");
		return mv;
	}
}
