package controller.annotation;

import java.util.Date;

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
// �� @RequestMapping ��һ��POJO������Ϊ Controller  
// �Ƽ�ʹ�� @Controller ���������������� @Service��@Repository �ܺõĶ�Ӧ�˳��������㿪���ܹ������
public class TodayController {
	
	// ���� URL �����������ܴ�������ӳ�䣬���URL·������ӳ�䵽ͬһ���������Ĺ��ܴ�����
	@RequestMapping(value = { "/today", "/show/index" })
    public ModelAndView show() {
		// 1���ռ���������֤����
		// 2���󶨲������������
		// 3�������������ҵ��������ҵ����
		// 4��ѡ����һ��ҳ��
		ModelAndView mv = new ModelAndView();
		// ���ģ�����ݣ������������POJO��
		mv.addObject("message", new Date());
		// �����߼���ͼ������ͼ��������ViewResolver������ݸ����ֽ������������ͼҳ�档
		mv.setViewName("show");
		return mv;//  ģ�����ݺ��߼���ͼ��  
	}
}