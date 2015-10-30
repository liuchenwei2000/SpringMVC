package controller.annotation;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 * ҳ�������ʵ�֣�ʹ�� annotation
 * 
 * @author ����ΰ
 * 
 * �������ڣ�2015��5��19��
 */
@Controller 
// ���������ʹ�� @RequestMapping ע��һ��������խ�����ܴ�������ӳ���
@RequestMapping("/user") // �� ��������ͨ��ӳ��ǰ׺        
public class UserController {
	
	// ����ڢٴ���ӳ�����խ��������Ӧ /user/firstname
	@RequestMapping("/firstname")
	public ModelAndView getFirstName() {
		ModelAndView mv = new ModelAndView();
		mv.addObject("message", "Bill");
		mv.setViewName("show");
		return mv;
	}

	// �޶�ֻ���� GET ��ʽ������
	@RequestMapping(value = "/lastname", method = RequestMethod.GET)
	public ModelAndView getLastName() {
		ModelAndView mv = new ModelAndView();
		mv.addObject("message", "Gates");
		mv.setViewName("show");
		return mv;
	}
	
	// �ȿ��Դ��� POST �ֿ��Դ��� GET ��ʽ������
	@RequestMapping(value = "/all", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView all() {
		ModelAndView mv = new ModelAndView();
		mv.addObject("message", "Bill Gates");
		mv.setViewName("show");
		return mv;
	}
	
	// GET �������� "query" ��������ƥ�䣬�� "http://������/user?query=a"
	@RequestMapping(params = "query", method = RequestMethod.GET)
	public ModelAndView query(@RequestParam("query") String param) {
		ModelAndView mv = new ModelAndView();
		mv.addObject("message", param);
		mv.setViewName("show");
		return mv;
	}

	// URI ģ��ģʽӳ�䣺{userid} ��ռλ���� ����� URL ������ "/delete/123456"�� "/users/abcd"
	// ͨ�� @PathVariable ������ȡ URI ģ��ģʽ�� {userid} �� userid ����ֵ��
	@RequestMapping("/delete/{userid}")
	public ModelAndView deleteUser(@PathVariable String userid) {
		ModelAndView mv = new ModelAndView();
		mv.addObject("message", userid);
		mv.setViewName("show");
		return mv;
	}
}