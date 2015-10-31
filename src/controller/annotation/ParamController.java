package controller.annotation;

import java.util.Arrays;
import java.util.List;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import vo.User;

/**
 * 功能处理方法支持的参数类型示例
 * 
 * @author 刘晨伟
 * 
 * 创建日期：2015年10月31日
 */
@Controller 
@RequestMapping("/params")
public class ParamController {
	
	/**
	 * ServletRequest/HttpServletRequest 和 ServletResponse/HttpServletResponse
	 */

	// SpringMVC 会自动把相应的请求/响应（Servlet API）作为参数传递进来
	@RequestMapping("/request")
	public ModelAndView request(ServletRequest request, HttpServletRequest httpRequest) {
		String path = httpRequest.getContextPath();
		String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
		ModelAndView mv = new ModelAndView("show");
		mv.addObject("message", basePath);
		return mv;
	}

	// SpringMVC 会自动把相应的请求/响应（Servlet API）作为参数传递进来
	@RequestMapping("/response")
	public ModelAndView response(ServletResponse response, HttpServletResponse httpResponse) {
		String message = response.getCharacterEncoding() + "</br>" + httpResponse.getContentType();
		ModelAndView mv = new ModelAndView("show");
		mv.addObject("message", message);
		return mv;
	}

	/** HttpSession */

	// SpringMVC 会自动把相应的 session 对象作为参数传递进来
	@RequestMapping("/session")
	public ModelAndView session(HttpSession session) {// 此处的 session 永远不为 null
		ModelAndView mv = new ModelAndView("show");
		mv.addObject("message", session.getId());
		return mv;
	}

	/** 命令/表单对象 */

	// SpringMVC 会自动将请求参数绑定到功能处理方法的 命令/表单 对象上
	// 如果提交的表单包含 code 和 name 文本域 ，将自动将请求参数绑定到命令对象 user 中去。
	@RequestMapping(value = "/commond", method = RequestMethod.GET)
	public ModelAndView commond(User user) {
		ModelAndView mv = new ModelAndView("show");
		mv.addObject("message", user.getCode() + "-" + user.getName());
		return mv;
	}

	/** 单个请求参数值 */

	// @RequestParam 用于将请求参数绑定到功能处理方法的参数上
	// 若请求中包含 username 参数（如/single?username=abc），则自动传入。
	@RequestMapping("/single")
	public ModelAndView singleParam(@RequestParam String username) {
		ModelAndView mv = new ModelAndView("show");
		mv.addObject("message", username);
		return mv;
	}

	// 通过 @RequestParam("name") 明确告诉 SpringMVC 使用 name 进行入参。各参数说明如下：
	// value：参数名字，即入参的请求参数名字，如 name 表示请求参数中名字为 name 的参数值将传入。
	// required：是否必须，默认是 true，表示请求中一定要有相应的参数，否则将报 404 错误。
	// defaultValue： 默认值，表示如果请求中没有同名参数时的默认值，默认值可以是 SpEL 表达式。
	@RequestMapping("/single2")
	public ModelAndView singleParam2(
			@RequestParam(value = "name", required = false, defaultValue = "default name") String username) {
		ModelAndView mv = new ModelAndView("show");
		mv.addObject("message", username);
		return mv;
	}

	// 如果请求中有多个同名的参数（如/multi?role=admin&role=manager），可以使用如下方式来接收多个请求参数。
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
	
	/** 绑定 URI 模板变量值 */
	
	// @PathVariable 用于将请求 URL 中的模板变量绑定到功能处理方法的参数上。
	// 如请求的 URL 为 "/load/12345”，则自动将 URL 中模板变量 {userId} 的值绑定到通过 @PathVariable 注解的同名参数上。
	@RequestMapping("/load/{userid}")
	public ModelAndView load(@PathVariable String userid) {
		ModelAndView mv = new ModelAndView("show");
		mv.addObject("message", userid);
		return mv;
	}
	
	/** 绑定 Cookie 数据值 */
	
	// @CookieValue 用于将请求的 Cookie 数据绑定到功能处理方法的参数上。
	// 下面的配置将自动将 JSESSIONID 值传给 sessionId 参数，defaultValue 表示 Cookie 中没有 JSESSIONID 时默认为空。
	@RequestMapping("/cookie")
	public ModelAndView cookie(@CookieValue(value="JSESSIONID", defaultValue="") String sessionId) {
		ModelAndView mv = new ModelAndView("show");
		mv.addObject("message", sessionId);
		return mv;
	}
	
	// 传入参数类型也可以是 javax.servlet.http.Cookie 类型。
	@RequestMapping("/cookie2")
	public ModelAndView cookie(@CookieValue(value = "JSESSIONID", defaultValue = "") Cookie cookie) {
		ModelAndView mv = new ModelAndView("show");
		mv.addObject("message", cookie.getName());
		return mv;
	}
	
	/** 绑定请求头数据 */
	
	// @RequestHeader 用于将请求的头信息数据绑定到功能处理方法的参数上。
	// 下面的配置将自动将请求头"User-Agent"的值传给 userAgent 参数，并将"Accept"请求头值传给 accepts 参数。
	@RequestMapping("/header")
	public ModelAndView requestHeader(@RequestHeader("User-Agent") String userAgent,
			@RequestHeader(value="Accept") String[] accepts) {
		String message = "userAgent=" + userAgent + "</br>" + "Accept=" + Arrays.asList(accepts);
		ModelAndView mv = new ModelAndView("show");
		mv.addObject("message", message);
		return mv;
	}
}