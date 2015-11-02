package controller.annotation;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import vo.User;

/**
 * ���ܴ�����֧�ֵĲ�������ʾ��
 * 
 * @author ����ΰ
 * 
 * �������ڣ�2015��10��31��
 */
@Controller 
@RequestMapping("/params")
public class ParamController {
	
	/**
	 * ServletRequest/HttpServletRequest �� ServletResponse/HttpServletResponse
	 */

	// SpringMVC ���Զ�����Ӧ������/��Ӧ��Servlet API����Ϊ�������ݽ���
	@RequestMapping("/request")
	public ModelAndView request(ServletRequest request, HttpServletRequest httpRequest) {
		String path = httpRequest.getContextPath();
		String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
		ModelAndView mv = new ModelAndView("show");
		mv.addObject("message", basePath);
		return mv;
	}

	// SpringMVC ���Զ�����Ӧ������/��Ӧ��Servlet API����Ϊ�������ݽ���
	@RequestMapping("/response")
	public ModelAndView response(ServletResponse response, HttpServletResponse httpResponse) {
		String message = response.getCharacterEncoding() + "</br>" + httpResponse.getContentType();
		ModelAndView mv = new ModelAndView("show");
		mv.addObject("message", message);
		return mv;
	}

	/** HttpSession */

	// SpringMVC ���Զ�����Ӧ�� session ������Ϊ�������ݽ���
	@RequestMapping("/session")
	public ModelAndView session(HttpSession session) {// �˴��� session ��Զ��Ϊ null
		ModelAndView mv = new ModelAndView("show");
		mv.addObject("message", session.getId());
		return mv;
	}

	/** ����/������ */

	// SpringMVC ���Զ�����������󶨵����ܴ������� ����/�� ������
	// ����ύ�ı����� code �� name �ı��� �����Զ�����������󶨵�������� user ��ȥ��
	@RequestMapping(value = "/commond", method = RequestMethod.GET)
	public ModelAndView commond(User user) {
		return new ModelAndView("user");// �� JSP ��ͼ��Ҳ����ʹ�� user ������Ⱦҳ��
	}
	
	/***/
	
	// SpringMVC �ṩ Model��Map �� ModelMap ʹ���ܱ�¶��Ⱦ��ͼ��Ҫ��ģ�����ݡ�
	// ��Ȼ�˴�ע�����������ͬ�����ͣ�Model model, Map model2, ModelMap model3������������ͬһ������
	@RequestMapping(value = "/model")
	@SuppressWarnings("unchecked")
	public ModelAndView model(Model model, Map model2, ModelMap model3) {
		model.addAttribute("a", "A");
		model2.put("b", "B");
		model3.put("c", "C");
		System.out.println(model == model2);
		System.out.println(model2 == model3);
		return new ModelAndView("model");
	}


	/** �����������ֵ */

	// @RequestParam ���ڽ���������󶨵����ܴ������Ĳ�����
	// �������а��� uname ��������/single?uname=abc������ uname ������ֵ���� usernam ������
	// �ڲ�ѯ�����뷽�����������ֲ�ƥ���ʱ��@RequestParam �����õġ�
	// ����Լ������������������в���û��ʹ��ע��Ļ������󶨵�ͬ���Ĳ�ѯ�����ϡ�
	@RequestMapping("/single")
	public ModelAndView singleParam(@RequestParam("uname") String username) {
		ModelAndView mv = new ModelAndView("show");
		mv.addObject("message", username);
		return mv;
	}

	// ͨ�� @RequestParam("name") ��ȷ���� SpringMVC ʹ�� name ������Ρ�������˵�����£�
	// value���������֣�����ε�����������֣��� name ��ʾ�������������Ϊ name �Ĳ���ֵ�����롣
	// required���Ƿ���룬Ĭ���� true����ʾ������һ��Ҫ����Ӧ�Ĳ��������򽫱� 404 ����
	// defaultValue�� Ĭ��ֵ����ʾ���������û��ͬ������ʱ��Ĭ��ֵ��Ĭ��ֵ������ SpEL ���ʽ��
	@RequestMapping("/single2")
	public ModelAndView singleParam2(
			@RequestParam(value = "name", required = false, defaultValue = "default name") String username) {
		ModelAndView mv = new ModelAndView("show");
		mv.addObject("message", username);
		return mv;
	}

	// ����������ж��ͬ���Ĳ�������/multi?role=admin&role=manager��������ʹ�����·�ʽ�����ն�����������
	@RequestMapping("/multi")
	public ModelAndView multiParam(@RequestParam("role") String[] roles) {
		ModelAndView mv = new ModelAndView("show");
		mv.addObject("message", Arrays.asList(roles));
		return mv;
	}

	@RequestMapping("/multi2")
	public ModelAndView multiParam(@RequestParam("role") List<String> roles) {
		ModelAndView mv = new ModelAndView("show");
		mv.addObject("message", roles);
		return mv;
	}
	
	/** �� URI ģ�����ֵ */
	
	// @PathVariable ���ڽ����� URL �е�ģ������󶨵����ܴ������Ĳ����ϡ�
	// ������� URL Ϊ "/load/12345�������Զ��� URL ��ģ����� {userId} ��ֵ�󶨵�ͨ�� @PathVariable ע���ͬ�������ϡ�
	@RequestMapping("/load/{userid}")
	public ModelAndView load(@PathVariable String userid) {
		ModelAndView mv = new ModelAndView("show");
		mv.addObject("message", userid);
		return mv;
	}
	
	/** �� Cookie ����ֵ */
	
	// @CookieValue ���ڽ������ Cookie ���ݰ󶨵����ܴ������Ĳ����ϡ�
	// ��������ý��Զ��� JSESSIONID ֵ���� sessionId ������defaultValue ��ʾ Cookie ��û�� JSESSIONID ʱĬ��Ϊ�ա�
	@RequestMapping("/cookie")
	public ModelAndView cookie(@CookieValue(value="JSESSIONID", defaultValue="") String sessionId) {
		ModelAndView mv = new ModelAndView("show");
		mv.addObject("message", sessionId);
		return mv;
	}
	
	// �����������Ҳ������ javax.servlet.http.Cookie ���͡�
	@RequestMapping("/cookie2")
	public ModelAndView cookie(@CookieValue(value = "JSESSIONID", defaultValue = "") Cookie cookie) {
		ModelAndView mv = new ModelAndView("show");
		mv.addObject("message", cookie.getName());
		return mv;
	}
	
	/** ������ͷ���� */
	
	// @RequestHeader ���ڽ������ͷ��Ϣ���ݰ󶨵����ܴ������Ĳ����ϡ�
	// ��������ý��Զ�������ͷ"User-Agent"��ֵ���� userAgent ����������"Accept"����ͷֵ���� accepts ������
	@RequestMapping("/header")
	public ModelAndView requestHeader(@RequestHeader("User-Agent") String userAgent,
			@RequestHeader(value="Accept") String[] accepts) {
		String message = "userAgent=" + userAgent + "</br>" + "Accept=" + Arrays.asList(accepts);
		ModelAndView mv = new ModelAndView("show");
		mv.addObject("message", message);
		return mv;
	}
}