package com.highwayPlatform.controller;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.highwayPlatform.model.ResponseInfo;
import com.highwayPlatform.service.JpushService;
import com.highwayPlatform.util.JpushClientUtil;
import com.testRoom.service.AppJpushService;

@Controller
@RequestMapping("/Jpush")
public class JpushController {
	@Autowired
	AppJpushService appJpushService;

	@Autowired
	private JpushService jpushService;
	
	// 推送
	@RequestMapping("/jpush.action")
	@ResponseBody
	public ResponseInfo jpush(@RequestParam HashMap<String, Object> map) {
		ResponseInfo info = new ResponseInfo();
		List<Map<String, Object>> listMap = appJpushService.getAppData(map);			
		if (listMap != null && listMap.size() > 0) {
			// 试验室
			String testRoomName = listMap.get(0).get("testRoomName").toString();
			// 试验日期
			String testDate = listMap.get(0).get("testDate").toString();
			// 试验名称
			String testName = listMap.get(0).get("testName").toString();
			// 样品编号
			String sampleCode = listMap.get(0).get("sampleCode").toString();
			String title = testRoomName + " " +testDate;
			String content = testName + "不合格";
			// 推送
			appJpush(listMap.get(0),title,content);
			// 推送成功
			info.setCode("success");
			info.setMessage("推送成功");				
		}else {
			// 没有预警
			info.setCode("normal");
			info.setMessage("没有预警");
		}	
		
		return info;
	}
		
	/**
	 * 推送
	 */
    public int appJpush(Map<String, Object> map,String title,String content) {
		int result = 0;
		List<Map<String, Object>> userList= jpushService.getAppUser(map);
		if (userList.size()>0) {
			Collection<String> collection = new HashSet<String>();
			for(int i= 0;i<userList.size();i++) {
				if(userList.get(i)!=null) {
//					collection.add(userList.get(i).get("UserCode").toString());
					collection.add(userList.get(i).get("Id").toString());
				}			
			}
			//调用推送接口
			result = JpushClientUtil.pushMsg(collection,title,content);
		}	
		return result;
	}
    
    // 自动采集异常列表App
 	@RequestMapping("/getAppDataList.json")
 	@ResponseBody
 	public List<Map<String, Object>> getAppDataList(HttpServletRequest request, @RequestParam Map<String, Object> param) {	
 		// 试验室
 		String uniqueIdentifier = param.get("uniqueIdentifier").toString();
 		String[] str = uniqueIdentifier.split(",");
 		Map<String, Object> map = new HashMap<String, Object>();
 		map.put("uniqueIdentifier",str);
 		List<Map<String, Object>> list = appJpushService.getAppData(map);
 		return list;
 	}
}
