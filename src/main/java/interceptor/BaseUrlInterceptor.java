/**
 * 
 */
package interceptor;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * BaseUrl 拦截器
 * <p>
 *     为每一个请求添加 baseUrl 属性，以便 JSP 页面直接使用。
 *     如 http://10.24.11.232:8080/app/
 *
 * @author 刘晨伟
 * 
 * 创建日期：2015年11月1日
 */
public class BaseUrlInterceptor extends HandlerInterceptorAdapter {

	/**
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.web.servlet.handler.HandlerInterceptorAdapter#preHandle(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, java.lang.Object)
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		String path = request.getContextPath();
		// http://10.24.11.232:8080/app/
		String basePath = request.getScheme() + "://" + request.getServerName() + ":" +
				request.getServerPort() + path + "/";
		request.setAttribute("baseurl", basePath);
		return super.preHandle(request, response, handler);
	}
}
