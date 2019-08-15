package com.highwayPlatform.controller;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.MixStation.service.AsphaltDataService;
import com.highwayPlatform.model.ResponseInfo;
import com.highwayPlatform.service.JpushService;
import com.highwayPlatform.util.JpushClientUtil;

@Controller
@RequestMapping("/Jpush")
public class JpushController {
	@Autowired
	AsphaltDataService asphaltDataService;

	@Autowired
	private JpushService jpushService;
	
	// 接收map参数，查询实验室名称
	@RequestMapping("/jpush.action")
	@ResponseBody
	public ResponseInfo addTestRoomName(@RequestParam HashMap<String, Object> map) {
		ResponseInfo info = new ResponseInfo();
		Map<String, Object> asphaltData = asphaltDataService.getAsphaltData(map);
		// 分析结果:0:未分析数据（无生产配合比或级配） 1：正常；
		// 2：骨料初级预警；3：骨料中级预警；4：骨料高级预警；
		// 5：粉料初级预警；6：粉料中级预警；7：粉料高级预警；
		// 8：沥青初级预警；9：沥青中级预警；10：沥青高级预警；
		// 11：外掺剂初级预警；12：外掺剂中级预警；13：外掺剂高级预警；
		// 14：温度预警；		
		if (asphaltData.get("analysisResult") != null) {
			String analysisResult = asphaltData.get("analysisResult").toString();
<<<<<<< .mine
			
||||||| .r200
			String[] arr = analysisResult.split(",");
			// 拌合站
			String orgName = asphaltData.get("orgName").toString();
			// 采集时间
			String collectTime = asphaltData.get("collectTime").toString();
			String content = orgName + " " +collectTime+ " ";
			boolean flag = false;
			if (arr.length > 0) {
				for (int i = 0; i < arr.length; i++) {
					if("1".equals(arr[i])){
						content += "正常  ";
		     		}
		     		if("2".equals(arr[i])){
		     			content += "骨料超差  ";
		     			flag = true;
		     		}
		     		if("3".equals(arr[i])){
		     			content += "粉料超差  ";
		     			flag = true;
		     		}
		     		if("4".equals(arr[i])){
		     			content += "沥青超差  ";
		     			flag = true;
		     		}
		     		if("5".equals(arr[i])){
		     			content += "外掺剂超差  ";
		     			flag = true;
		     		}
		     		if("6".equals(arr[i])){
		     			content += "温度超差  ";
		     			flag = true;
		     		}
				}
			}
			if (flag) {
				// 推送
				appJpush(asphaltData,"拌合站预警",content);
				// 推送成功
				info.setCode("success");
				info.setMessage("推送成功");
			} else {
				// 没有预警
				info.setCode("normal");
				info.setMessage("没有预警");
			}					
=======
			String[] arr = analysisResult.split(",");
			// 拌合站
			String orgName = asphaltData.get("orgName").toString();
			// 采集时间
			String collectTime = asphaltData.get("collectTime").toString();
			String title = orgName + " " +collectTime;
			String content = "";
			// 是否异常推送
			boolean flag = false;
			// 是否初级
			boolean primary = false;
			// 是否中级
			boolean intermediate  = false;
			// 是否高级
			boolean senior = false;
			if (arr.length > 0) {
				for (int i = 0; i < arr.length; i++) {
					if("1".equals(arr[i])){
						content += "正常  ";
		     		}
		     		if("2".equals(arr[i])){
		     			content += "骨料初级预警 ";
		     			flag = true;
		     			primary = true;
		     		}
		     		if("3".equals(arr[i])){
		     			content += "骨料中级预警 ";
		     			flag = true;
		     			intermediate = true;
		     		}
		     		if("4".equals(arr[i])){
		     			content += "骨料高级预警 ";
		     			flag = true;
		     			senior = true;
		     		}
		     		if("5".equals(arr[i])){
		     			content += "粉料初级预警 ";
		     			flag = true;
		     			primary = true;
		     		}
		     		if("6".equals(arr[i])){
		     			content += "粉料中级预警 ";
		     			flag = true;
		     			intermediate = true;
		     		}
		     		if("7".equals(arr[i])){
		     			content += "粉料高级预警 ";
		     			flag = true;
		     			senior = true;
		     		}
		     		if("8".equals(arr[i])){
		     			content += "沥青初级预警 ";
		     			flag = true;
		     			primary = true;
		     		}
		     		if("9".equals(arr[i])){
		     			content += "沥青中级预警 ";
		     			flag = true;
		     			intermediate = true;
		     		}
		     		if("10".equals(arr[i])){
		     			content += "沥青高级预警 ";
		     			flag = true;
		     			senior = true;
		     		}
		     		if("11".equals(arr[i])){
		     			content += "外掺剂初级预警 ";
		     			flag = true;
		     			primary = true;
		     		}
		     		if("12".equals(arr[i])){
		     			content += "外掺剂中级预警 ";
		     			flag = true;
		     			intermediate = true;
		     		}
		     		if("13".equals(arr[i])){
		     			content += "外掺剂高级预警 ";
		     			flag = true;
		     			senior = true;
		     		}
		     		if("14".equals(arr[i])){
		     			content += "温度预警";
		     			flag = true;
		     			primary = true;
		     		}
				}
			}
			if (flag) {
				// 初级预警
				if (primary) {
					asphaltData.put("WarningLevel", "0");
				}
				// 中级预警
				if (intermediate) {
					asphaltData.put("WarningLevel", "1");
				}
				// 高级预警
				if (senior) {
					asphaltData.put("WarningLevel", "2");
				}
				// 推送
				appJpush(asphaltData,title,content);
				// 推送成功
				info.setCode("success");
				info.setMessage("推送成功");
			} else {
				// 没有预警
				info.setCode("normal");
				info.setMessage("没有预警");
			}					
>>>>>>> .r322
		} else {
			
		}
		
		
		// 推送
//		appJpush(map,"title","content");
		
		// 注册失败
		info.setCode("");
		info.setMessage("");
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
					collection.add(userList.get(i).get("UserCode").toString());
				}			
			}
			//调用推送接口
			result = JpushClientUtil.pushMsg(collection,title,content);
		}	
		return result;
	}
}
