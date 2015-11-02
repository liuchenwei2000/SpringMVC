package controller.annotation;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * ��ҳҳ�������
 * 
 * @author ����ΰ
 * 
 * �������ڣ�2015��11��1��
 */
@Controller 
public class HomeController {
	
	@RequestMapping(value = { "/", "/home", "/index" })
	public ModelAndView index() {
		return new ModelAndView("index");
	}
	
	@RequestMapping(value = { "/restful" })
	public ModelAndView restful() {
		return new ModelAndView("restful");
	}
}