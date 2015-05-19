package controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

/**
 * ҳ�������ʵ��
 * 
 * @author ����ΰ
 * 
 * �������ڣ�2015��5��19��
 */
public class HelloWorldController implements Controller {
	
    @Override
    public ModelAndView handleRequest(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		// 1���ռ���������֤����
		// 2���󶨲������������
		// 3�������������ҵ��������ҵ����
		// 4��ѡ����һ��ҳ��
		ModelAndView mv = new ModelAndView();
		// ���ģ�����ݣ������������POJO��
		mv.addObject("message", "Hello World!");
		// �����߼���ͼ������ͼ��������ViewResolver������ݸ����ֽ������������ͼҳ�档
		mv.setViewName("hello");
		return mv;
	}
}