package com.oil.controller.login;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.oil.model.Userinfo;
import com.oil.util.JpushClientUtil;

@Controller
@RequestMapping("/index")
public class IndexController {
	
	@RequestMapping("/getMenu.action")
		public @ResponseBody Userinfo getSession(HttpServletRequest request) {
			HttpSession session = request.getSession();
			Userinfo obj = (Userinfo) session.getAttribute("user");
			return obj;
		}
	
	 /**
	 * 推送
	 */
	/*@RequestMapping("/appJpush.action")
		public @ResponseBody int appJpush(HttpServletRequest request) {
			System.out.println("1111111111111111推送测试");
			int result = 0;
			Collection<String> collection = new HashSet<String>();
			collection.add("60");
			collection.add("61");
			String title="推送内容";
			String content="测试测试测试测试";
				//调用推送接口
				result = JpushClientUtil.pushMsg(collection,title,content);
			System.out.println("1111111111111111推送测试");
			return result;
		}*/
	}


