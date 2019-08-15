package com.jingpeng.controller;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jingpeng.dao.DataTablesResponseInfo;
import com.jingpeng.model.BlindSampDetailed;
import com.jingpeng.model.BlindSampleInfo;
import com.jingpeng.model.Search;
import com.jingpeng.model.UserInfo;
import com.jingpeng.service.BlindSampleInfoService;
import com.jingpeng.util.DateConvert;
import com.jingpeng.util.Message;
import com.kdt.base.exception.BusinessException;
import com.kdt.base.support.springsupport.KDController;
@Controller
@RequestMapping("/BlindSampleInfo")
public class BlindSampleInfoController extends KDController<Object>{
	
	//返回code key
	public final static String APPCONTROLLER_MESSAGECODE_FILED = "messageCode";
	//返回code key
	public final static String APPCONTROLLER_MESSAGE_FILED = "message";
	//登录返回信息 key
	public final static String APPCONTROLLER_USERINFO_FILED = "userInfo";

	//盲样查询信息list key
	private  final static String APPCONTROLLER_BLINDSAMPLEINFOLIST_FILED = "blindSampleInfoList";
	
	//盲样二维码编号区间
	private  final static String APPCONTROLLER_NUMBERED_INTERVAL_FILED = "0-5000";
	//盲样二维码编号前缀
	private  final static String APPCONTROLLER_PREFIX_FILED = "jingpeng";
	@Autowired
	private BlindSampleInfoService blindSampleInfoService;
	
	@RequestMapping("jilu_2.html")
	public String testUserjilu_2(HttpServletRequest request) {
		return "/lq/jilu_2";
	}
	
	@RequestMapping("index.html")
	public String index(HttpServletRequest request) {
		return "/test/index";
	}
	@RequestMapping("blindSampleInfo.html")
	public String blindSampleInfo(HttpServletRequest request) {
		return "/test/blindSampleInfo";
	}
	//查询盲样试验全部信息
	@RequestMapping("/getBlindSampleInfo.html")
	public @ResponseBody DataTablesResponseInfo getblindSampleInfo1(HttpServletRequest request,@RequestParam HashMap<String, Object> map) throws ParseException{
		DataTablesResponseInfo dtri = new DataTablesResponseInfo();
		try {
			UserInfo user =(UserInfo) request.getSession().getAttribute("user");
			int orgId = user.getOrgId();
			
			if(!(map.get("oId")==null||"".equals(map.get("oId").toString()))) {
			String[] org_Ids1 =map.get("oId").toString().split(",");
			int[] a = new int[org_Ids1.length];
			int index = 0;
			for(String str : org_Ids1) {
				
				a[index] = Integer.parseInt(str);
				index++;
			}
			map.put("org_Ids", a);
			}
			
			List<Map<String, Object>> list = blindSampleInfoService.getBlindSampleInfo1(map);
			for(int i = 0; i < list.size(); i++) {
				/*list.get(i).put("testState", list.get(i).get("testState"));*/
				list.get(i).put("serialNumber", i+1);
				String operate = "<td class=\"caozuo20\"><span class=\"scgl_del fudong\"><a href=\"javascript:void(0)\" onclick=\"showBlind('"+list.get(i).get("id")+"');\">"
						+ "<img src=\"../resources/images/cha.png\" width=\"18\" height=\"16\" alt=\"查看结果\" title=\"查看结果\">"
						+ "</a></span></td>";
				list.get(i).put("details", operate);
				list.get(i).put("samplingDate",DateConvert.changeDate(list.get(i).get("samplingDate").toString()));
			}
			dtri.setData(list);
			return dtri;
		} catch (BusinessException e) {
			e.printStackTrace();
			return null;
		}
}
	//查询工程项目名称
	@RequestMapping("/getConstructionUnit.html")
	public  @ResponseBody DataTablesResponseInfo getConstructionUnit(HttpServletRequest request,@RequestParam HashMap<String, Object> map) throws ParseException{
		DataTablesResponseInfo dtri = new DataTablesResponseInfo();
		try {
			List<Map<String, Object>> list = blindSampleInfoService.getConstructionUnit(map);
			for (int i = 0; i < list.size(); i++) {
				list.get(i).put("ConstructionUnit",list.get(i).get("ConstructionUnit"));
			}
			dtri.setData(list);
			return dtri;
		} catch (BusinessException e) {
			e.printStackTrace();
			return null;
		}
	}
	//查询施工单位
	@RequestMapping("/getEngineeringName.html")
	public  @ResponseBody DataTablesResponseInfo getEngineeringName(HttpServletRequest request,@RequestParam HashMap<String, Object> map) throws ParseException{
		DataTablesResponseInfo dtri = new DataTablesResponseInfo();
		try {
			List<Map<String, Object>> list = blindSampleInfoService.getEngineeringName(map);
			for (int i = 0; i < list.size(); i++) {
				list.get(i).put("EngineeringName",list.get(i).get("EngineeringName"));
			}
			dtri.setData(list);
			return dtri;
		} catch (BusinessException e) {
			e.printStackTrace();
			return null;
		}
	}
	//获取session中参数
	@RequestMapping("/getBlindSampleInfoValue")
	public @ResponseBody UserInfo getValue(HttpServletRequest request) {
		HttpSession session = request.getSession();
		UserInfo obj = (UserInfo) session.getAttribute("user");
		return obj;
	}

}
