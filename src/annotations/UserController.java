package annotations;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * ҳ�������ʵ�֣�ʹ�� annotation
 * 
 * @author ����ΰ
 * 
 * �������ڣ�2015��5��19��
 */
@Controller
// ���������ʹ�� @RequestMapping ע��һ��������խ�����ܴ�����ӳ���
@RequestMapping("/user") // �� ��������ͨ��ӳ��ǰ׺        
public class UserController {
	
	// ����ڢٴ���ӳ�����խ��������Ӧ /user/firstname 
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