package annotations;

import java.util.Date;

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
@Controller // 将一个POJO类声明为 Controller  
// 或 @RequestMapping               
public class TodayController {
	
	// 请求 URL 到处理器功能处理方法的映射  
	@RequestMapping("/today")
    public ModelAndView helloWorld() {
		// 1、收集参数、验证参数
		// 2、绑定参数到命令对象
		// 3、将命令对象传入业务对象进行业务处理
		// 4、选择下一个页面
		ModelAndView mv = new ModelAndView();
		// 添加模型数据，可以是任意的POJO。
		mv.addObject("today", new Date());
		// 设置逻辑视图名，视图解析器（ViewResolver）会根据该名字解析出具体的视图页面。
		mv.setViewName("today");
		return mv;//  模型数据和逻辑视图名  
	}
}