package com.oil.interceptor;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class LoginInterceptor implements HandlerInterceptor{	
	private Logger logger = LoggerFactory.getLogger(LoginInterceptor.class);

	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		// app跨域设置
		// 设置：Access-Control-Allow-Origin头，处理Session问题
        response.setHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));
        response.setHeader("Access-Control-Allow-Credentials", "true");
        response.setHeader("P3P", "CP=CAO PSA OUR");
        if (request.getHeader("Access-Control-Request-Method") != null && "OPTIONS".equals(request.getMethod())) {
            response.addHeader("Access-Control-Allow-Methods", "POST,GET,TRACE,OPTIONS");
            response.addHeader("Access-Control-Allow-Headers", "Content-Type,Origin,Accept");
            response.addHeader("Access-Control-Max-Age", "120");
        }
		
		String reqUrl = request.getRequestURI().replace(request.getContextPath(), "");
		request.setAttribute("reqUrl", reqUrl);
		logger.debug("url:" + reqUrl);
		if (reqUrl.endsWith("login/login.action")||reqUrl.endsWith("login/getUserInfo.action")||reqUrl.endsWith("login/insertUserInfo.action")
				|| reqUrl.endsWith("Screen2/getCountList.action")|| reqUrl.endsWith("Screen2/getScreen2List.action")|| reqUrl.endsWith("Screen2/getEchar.action")
				|| reqUrl.endsWith("index/getMenu.action")|| reqUrl.endsWith("Map/getCustomertransports.action")|| reqUrl.endsWith("Map/getCarTrajectory.action")
				|| reqUrl.endsWith("sdProductionPlan/getProductionPlanList.action")|| reqUrl.endsWith("sdProductionPlan/getProductionPlanCount.action")
				|| reqUrl.endsWith("quality/getAllBeforeQuality.action")|| reqUrl.endsWith("quality/getAllPlanQuality.action")
				|| reqUrl.endsWith("file/preview.action")) {
			return true;
		} else if (reqUrl.endsWith(".action")){
			HttpSession session = request.getSession();
			Object obj = session.getAttribute("user");
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
		
	}

	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		
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
			
			buffer.append("window.top.location='" + request.getContextPath() + "/page/user/login.html';");
			
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
