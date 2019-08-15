package com.jingpeng.sys.interceptor;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.PrintWriter;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.jingpeng.util.Consts;

public class LoginInterceptor implements HandlerInterceptor{
	
	
	
	private Logger logger = LoggerFactory.getLogger(LoginInterceptor.class);

	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		String reqUrl = request.getRequestURI().replace(request.getContextPath(), "");
		request.setAttribute("reqUrl", reqUrl);
		logger.debug("url:" + reqUrl);
		if (reqUrl.contains("addTestRoomName") || reqUrl.contains("getTestRoomName") || reqUrl.contains("index") || reqUrl.contains("APP") || reqUrl.contains("Login") || reqUrl.contains("files") || reqUrl.contains("resources")
                || reqUrl.contains("apidoc") || reqUrl.contains("login") || reqUrl.startsWith("/dist")
                || reqUrl.startsWith("/contracts") || reqUrl.startsWith("/bootstrap") || reqUrl.startsWith("/ajax")
                || reqUrl.startsWith("/plugins")||reqUrl.contains("/Register") ||reqUrl.contains("/user") || reqUrl.contains("getTestUserName")
                ||reqUrl.contains("Jqindex")||reqUrl.contains("VideoSurveillance")||reqUrl.contains("spjk")||reqUrl.contains("FluorescentCloud")) {
			return true;
		} else {
			HttpSession session = request.getSession();
			Object obj = session.getAttribute(Consts.SESSION_USER_KEY);
			String requestType = request.getHeader("X-Requested-With");

			if (obj == null || "".equals(obj.toString())) {
				if (!StringUtils.isEmpty(requestType)) {
					response.setCharacterEncoding("UTF-8");
					response.sendError(HttpStatus.UNAUTHORIZED.value(), "您已经太长时间没有操作,请刷新页面");
				} else {

					jumpPage(request, response);
				}
				return false;
			}
		}
		return true;
	}

	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub
		
	}

	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub
		
	}
	
	public void jumpPage(HttpServletRequest request, HttpServletResponse response) {

	
		PrintWriter out = null;

		try {
			response.setContentType("text/html; charset=UTF-8");
			response.setHeader("Cache-Control", "no-cache");
			System.out.println(request.getRequestURL());
			out = response.getWriter();
			StringBuffer buffer = new StringBuffer();
			buffer.append("<script type=\"text/javascript\">");
			System.out.println(request.getContextPath());
			
			String reqUrl = request.getRequestURI().replace(request.getContextPath(), "");
			
				buffer.append("window.top.location='" + request.getContextPath() + "/testUserInfo/login.html';");
			
			buffer.append("</script>");
			out.println(buffer.toString());

		} catch (Exception e) {

		} finally {
			if (out != null) {
				out.flush();
				out.close();
			}
		}

	}
	
}