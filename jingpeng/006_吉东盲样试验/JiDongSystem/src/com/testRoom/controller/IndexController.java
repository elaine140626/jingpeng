package com.testRoom.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.highwayPlatform.model.DataTablesResponseInfo;
import com.testRoom.service.IndexService;

@Controller
@RequestMapping("/index")
public class IndexController{
	
	@Autowired
	private IndexService  indexService;
	
	// 查询index实验室数据，返回list
	@RequestMapping("/getIndexSummary.action")
	@ResponseBody
	public DataTablesResponseInfo getIndexSummary(HttpServletRequest request) {		
		DataTablesResponseInfo dtr = new DataTablesResponseInfo();
		
		HttpSession session = request.getSession();
		// 获取当前登录用户的试验室权限
		String userTestDetailed = (String) session.getAttribute("userTestDetailed");
		String[] param = {};
		if(userTestDetailed != null && !("").equals(userTestDetailed)) {
			param = userTestDetailed.split(",");
		}
		
		// 检索条件设置
		Map<String, Object> map = new HashMap<String, Object>();
		// 获取当前登录用户的id
		map.put("uniqueIdentifier", param);
		List<Map<String, Object>> testRoomNamelist = indexService.getTestRoomName(map);
		String TestRoomName = "";
		if (testRoomNamelist!=null) {
			for(int i=0;i<testRoomNamelist.size();i++) {
				if (i==0) {
					TestRoomName = testRoomNamelist.get(i).get("TestRoomName").toString();
				} else {
					TestRoomName = TestRoomName + ","+testRoomNamelist.get(i).get("TestRoomName").toString();
				}
			}
		}
		map.put("testRoomNamelist", testRoomNamelist);
		List<Map<String, Object>> list = indexService.getIndexSummary(map);		
		list.get(0).put("testRoomName", TestRoomName);
		dtr.setData(list);
		return dtr;
	}
}



