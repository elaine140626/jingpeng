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
import com.MixStation.model.PlatformAsphaltWarningDeviationEntity;
import com.MixStation.model.PlatformCementStableWarningDeviationEntity;
import com.MixStation.service.PlatformCementStableWarningDeviationService;
import com.highwayPlatform.model.ResponseInfo;
import com.highwayPlatform.util.MessageUtilBlindSample;

@Controller
@RequestMapping("/platformCementStableWarningDeviation")
public class PlatformCementStableWarningDeviationController {
	@Autowired
	private PlatformCementStableWarningDeviationService platformCementStableWarningDeviationService;
	/**
	 * 水泥稳定土 水泥稳定土预警偏差datatable
	 **/
	@RequestMapping("/getPlatformCementStableWarningDeviation.action")
	@ResponseBody
	public DataTablesResponseInfo getPlatformCementStableWarningDeviation(HttpServletRequest request,@RequestParam Map<String, Object> param) {		
		DataTablesResponseInfo dtri = new DataTablesResponseInfo();	
		
		List<PlatformCementStableWarningDeviationEntity> list = platformCementStableWarningDeviationService.getPlatformCementStableWarningDeviation(param);
		if (list!=null && list.size()>0) {
			for (int i = 0; i < list.size(); i++) {	
				list.get(i).setSerialNumber(i+1);
			}
		}

		dtri.setData(list);
		return dtri;
	}
	
	/**
	 * 插入水泥稳定土阀值设定
	**/
	@RequestMapping("/insertPlatformCementStableWarningDeviation.action")
	public @ResponseBody ResponseInfo insertPlatformCementStableWarningDeviation(HttpServletRequest request, 
			PlatformCementStableWarningDeviationEntity platformCementStableWarningDeviationEntity) {
		ResponseInfo info = new ResponseInfo();
	
		platformCementStableWarningDeviationEntity.setOperator("yonghuming");
		int result = platformCementStableWarningDeviationService.insertPlatformCementStableWarningDeviation(platformCementStableWarningDeviationEntity);

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
