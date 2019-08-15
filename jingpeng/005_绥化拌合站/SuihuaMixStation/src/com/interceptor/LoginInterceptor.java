package com.interceptor;
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
		if (reqUrl.endsWith("UserInfo/userLogin.action") || reqUrl.endsWith("UserInfo/getTestRoomList.action") 
				|| reqUrl.endsWith("UserInfo/getOrgInfoList.action")|| reqUrl.endsWith("UserInfo/insertUserInfo.action")|| reqUrl.contains("FluorescentCloud")) {
			// 平台拦截器
			return true;
<<<<<<< .mine
		} else if (reqUrl.endsWith("login/userLogin.action") || reqUrl.endsWith("login/getOrg.action") || reqUrl.endsWith("login/addUser.action")) {
||||||| .r200
		} else if (reqUrl.endsWith("login/userLogin.action") || reqUrl.endsWith("login/getOrg.action") || reqUrl.endsWith("login/addUser.action")
				|| reqUrl.contains("Jpush/jpush.action")) {
=======
		} else if (reqUrl.endsWith("login/userLogin.action") || reqUrl.endsWith("login/getOrg.action") || reqUrl.endsWith("login/addUser.action")
				|| reqUrl.contains("Jpush/jpush.action")|| reqUrl.contains("AsphaltData/getAppAsphaltData.json")) {
>>>>>>> .r322
			// 拌合站拦截器
			return true;
		} else if (reqUrl.endsWith(".action")){
			HttpSession session = request.getSession();
			// 平台用户
			Object obj = session.getAttribute("user");
			// 拌合站用户
			Object mixUser = session.getAttribute("mixUser");
			String requestType = request.getHeader("X-Requested-With");

			if ((obj == null || "".equals(obj.toString())) && (mixUser == null || "".equals(mixUser.toString()))) {
				if (!StringUtils.isEmpty(requestType)) {
					response.setCharacterEncoding("UTF-8");
					response.sendError(HttpStatus.UNAUTHORIZED.value(), "您已经太长时间没有操作,请刷新页面");
				} else {
					// 请求的controller路径
					String[] reqArray = reqUrl.split("/");
					String url = reqArray[1];
					// 判断本次请求的是那个系统的路径，如果是拌合站，则跳转拌合站的登录页面，否则，跳转平台登录页面
					if (url.equals("asphaltProductionPlan") || url.equals("material") || url.equals("meshSize") ||
							url.equals("equipment")|| url.equals("User")) {
						jumpPage(request, response, "mixStation");
					} else {
						jumpPage(request, response, "center");
					}
				}
				return false;
			} else {
				return true;
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
	
	public void jumpPage(HttpServletRequest request, HttpServletResponse response, String type) {	
		PrintWriter out = null;
		try {
			response.setContentType("text/html; charset=UTF-8");
			response.setHeader("Cache-Control", "no-cache");
			System.out.println(request.getRequestURL());
			out = response.getWriter();
			StringBuffer buffer = new StringBuffer();
			buffer.append("<script type=\"text/javascript\">");
			System.out.println(request.getContextPath());
			
			// 跳转的请求路径
			StringBuilder url = new StringBuilder();
			if (type.equals("mixStation")) {
				// 拌合站客户端
				url.append("'../page/mixLogin/mixStationLogin.html';");
			} else {
				// 平台
				url.append("'../page/centerLogin/centerLogin.html';");
			}
			// buffer.append("window.top.location='" + request.getContextPath() + "/page/login.html';");
			buffer.append("window.top.location=" + url);
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
