package com.jingpeng.controller;

import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jingpeng.model.BlindSampDetailed;
import com.jingpeng.model.BlindSampleInfo;
import com.jingpeng.service.BlindSampleInfoService;
import com.jingpeng.service.OrganizationInfoService;
import com.jingpeng.service.UserInfoService;
import com.jingpeng.util.DateConvert;
import com.jingpeng.util.Message;
import com.kdt.base.exception.BusinessException;

@Controller
@RequestMapping("/BlindSamp2")
public class BlindSampController {

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
	
	@RequestMapping("/BlindSampInfo2_1.html")
	public @ResponseBody Map<String,Object>  getBlindSampleAll(int id,int orgId) throws BusinessException, ParseException {
		Map<String,Object> param = new HashMap<String,Object>();
		param.put("id", id);
		param.put("orgId", orgId);
		List<BlindSampleInfo> blindSampleInfoList = blindSampleInfoService.getBlindSampleInfo(param);
		Map<String,Object> blindSample = new HashMap<String,Object>();
		if(blindSampleInfoList!=null ) {	
			if(blindSampleInfoList.get(0).getMoldDate() != null) {
				blindSampleInfoList.get(0).setMoldDate(DateConvert.changeDate(blindSampleInfoList.get(0).getMoldDate().toString()));
			}
		}
		if(blindSampleInfoList!=null) {	
			if(blindSampleInfoList.get(0).getSamplingDate() != null) {
				blindSampleInfoList.get(0).setSamplingDate(DateConvert.changeDate(blindSampleInfoList.get(0).getSamplingDate().toString()));
			}
		}
		if(blindSampleInfoList!=null) {		
			if(blindSampleInfoList.get(0).getTargetDate() != null) {
				blindSampleInfoList.get(0).setTargetDate(DateConvert.changeDate(blindSampleInfoList.get(0).getTargetDate().toString()));
			}
		}
		blindSample.put("blindSampleInfoList", blindSampleInfoList);
		blindSample.put(APPCONTROLLER_MESSAGECODE_FILED, Message.SUCCESS_CODE);
		blindSample.put(APPCONTROLLER_MESSAGE_FILED, Message.SUCCESSFUL_OPERATION);
		return blindSample;
	}
	
	@RequestMapping("/BlindSampInfo2_2.html")
	public @ResponseBody Map<String,Object>  getBlindSampleAll_2(int id) throws BusinessException {
		Map<String,Object> param = new HashMap<String,Object>();
		param.put("id", id);
		List<BlindSampDetailed> blindSampDetailedList = blindSampleInfoService.blindSampDetailed(param);
		Map<String,Object> blindSample = new HashMap<String,Object>();
		blindSample.put("blindSampDetailedList", blindSampDetailedList);
		return blindSample;
	}

}
