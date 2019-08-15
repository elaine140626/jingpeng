package com.MixStation.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.MixStation.model.DataTablesResponseInfo;
import com.MixStation.model.PlatformCementWarningDeviationEntity;
import com.MixStation.service.PlatformCementWarningDeviationService;
import com.highwayPlatform.model.ResponseInfo;
import com.highwayPlatform.util.MessageUtilBlindSample;

@Controller
@RequestMapping("/PlatformCementWarningDeviation")
public class PlatformCementWarningDeviationController{	
	@Autowired
	private PlatformCementWarningDeviationService PlatformCementWarningDeviationService;
	/**
	 * 水泥混凝土 沥青预警偏差datatable
	 **/
	@RequestMapping("/getPlatformCementWarningDeviation.action")
	@ResponseBody
	public DataTablesResponseInfo getPlatformCementWarningDeviation(HttpServletRequest request,@RequestParam Map<String, Object> param) {		
		DataTablesResponseInfo dtri = new DataTablesResponseInfo();	
		
		List<PlatformCementWarningDeviationEntity> list = PlatformCementWarningDeviationService.getPlatformCementWarningDeviation(param);
		if (list!=null && list.size()>0) {
			for (int i = 0; i < list.size(); i++) {	
				list.get(i).setSerialNumber(i+1);
			}
		}

		dtri.setData(list);
		return dtri;
	}
	
	/**
	 * 插入沥青阀值设定
	**/
	@RequestMapping("/insertPlatformCementWarningDeviation.action")
	public @ResponseBody ResponseInfo insertPlatformCementWarningDeviation(HttpServletRequest request, 
			PlatformCementWarningDeviationEntity platformCementWarningDeviationEntity) {
		ResponseInfo info = new ResponseInfo();
	
		platformCementWarningDeviationEntity.setOperator("yonghuming");
		int result = PlatformCementWarningDeviationService.insertPlatformCementWarningDeviation(platformCementWarningDeviationEntity);

		if (result>0) {
			//成功处理请求提示 200
			info.setCode(MessageUtilBlindSample.SUCCESSFUL_OPERATION_REQUESTS);
			//操作成功提示
			info.setMessage(MessageUtilBlindSample.SUCCESSFUL_OPERATION);
		} else {
			//多种选择 300
			info.setCode(MessageUtilBlindSample.MULTIPLE_CHOICES);
			//钢筋牌号和直径
			info.setMessage(MessageUtilBlindSample.REBAR_NOREPEAT);
		}
		
		return info;
	}
	
}



