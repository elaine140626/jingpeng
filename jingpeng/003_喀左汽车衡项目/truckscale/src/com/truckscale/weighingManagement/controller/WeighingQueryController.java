package com.truckscale.weighingManagement.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.truckscale.basicSetting.model.MaterialSettingEntity;
import com.truckscale.common.model.DataTablesResponseInfo;
import com.truckscale.common.model.ResponseInfo;
import com.truckscale.common.util.MessageUtil;
import com.truckscale.weighingManagement.model.WeighingQueryEntity;
import com.truckscale.weighingManagement.service.WeighingQueryService;

@Controller
@RequestMapping("/WeighingQuery")
public class WeighingQueryController {

	@Autowired
	private WeighingQueryService weighingQueryService;
	
	// 查询称重信息
	@RequestMapping("/getWeighingQuery.action")
	@ResponseBody
	public DataTablesResponseInfo getWeighingQuery(HttpServletRequest request, @RequestParam Map<String, Object> map) {
		DataTablesResponseInfo dInfo = new DataTablesResponseInfo();
		List<WeighingQueryEntity> dataList = weighingQueryService.getWeighingQuery(map);
		dInfo.setData(dataList);
		return dInfo;
	};
	// 查询称重信息Sum
	@RequestMapping("/getWeighingQuerySum.action")
	@ResponseBody
	public DataTablesResponseInfo getWeighingQuerySum(HttpServletRequest request, @RequestParam Map<String, Object> map) {
		DataTablesResponseInfo dInfo = new DataTablesResponseInfo();
		List<WeighingQueryEntity> dataList = weighingQueryService.getWeighingQuerySum(map);
		dInfo.setData(dataList);
		return dInfo;
	};
	
	//获取datalist
	@RequestMapping("/getDataList.action")
	@ResponseBody
	public Map<String, Object> getDataList(@RequestParam Map<String, Object> map) {
		return weighingQueryService.getDataList(map);
	}
	
	//编辑称重信息获取datalist
	@RequestMapping("/getDataList_add.action")
	@ResponseBody
	public Map<String, Object> getDataList_add(@RequestParam Map<String, Object> map) {
			return weighingQueryService.getDataList_add(map);
	}
		
	// 供料单位List
	@RequestMapping("/getFeedcompanyList.action")
	@ResponseBody
	public Map<String, Object> getFeedcompanyList(@RequestParam Map<String, Object> map) {
		return weighingQueryService.getFeedcompanyList(map);
	}
	
	// 材料名称List
	@RequestMapping("/getMaterielNameList.action")
	@ResponseBody
	public Map<String, Object> getMaterielNameList(@RequestParam Map<String, Object> map) {
		return weighingQueryService.getMaterielNameList(map);
	}
	
	// 规格型号List
	@RequestMapping("/getMaterielModelList.action")
	@ResponseBody
	public Map<String, Object> getMaterielModelList(@RequestParam Map<String, Object> map) {
		return weighingQueryService.getMaterielModelList(map);
	}
	
	//删除称重信息
	@RequestMapping("/deleteWeighingQuery.action")
	@ResponseBody
	public ResponseInfo deleteWeighingQuery(@RequestParam Map<String, Object> map) {
		ResponseInfo info = new ResponseInfo();
		int res = weighingQueryService.deleteWeighingQuery(map);
		if (res>0) {
			// 操作成功
			info.setCode(MessageUtil.success);
			info.setMessage(MessageUtil.successInfo);
		} else {
			// 操作失败
			info.setCode(MessageUtil.error);
			info.setMessage(MessageUtil.errorInfo);
		}
		return info;
	}
	
	//编辑称重信息
	@RequestMapping("/updateWeighingQuery.action")
	@ResponseBody
	public ResponseInfo updateWeighingQuery(@RequestBody WeighingQueryEntity weighingQueryEntity) {
		ResponseInfo info = new ResponseInfo();
		int res = weighingQueryService.updateWeighingQuery(weighingQueryEntity);
		if (res>0) {
			// 操作成功
			info.setCode(MessageUtil.success);
			info.setMessage(MessageUtil.successInfo);
		} else {
			// 操作失败
			info.setCode(MessageUtil.error);
			info.setMessage(MessageUtil.errorInfo);
		}
		return info;
	}
	
	//打印信息
	@RequestMapping("/getPrintInfo.action")
	@ResponseBody
	public List<Map<String, Object>> getPrintInfo(@RequestParam Map<String, Object> map) {
		return weighingQueryService.getPrintInfo(map);
	}
	
	//获取供料单位的材料名称和型号（ygt）
	@RequestMapping("/getOutFeedCompanyInfo.action")
	@ResponseBody
	public List<Map<String, Object>> getOutFeedCompanyInfo(@RequestParam Map<String, Object> map) {
		return weighingQueryService.getOutFeedCompanyInfo(map);
	}
	
	//获取入供料单位的材料名称和型号（ygt）
	@RequestMapping("/getInFeedCompanyInfo.action")
	@ResponseBody
	public List<Map<String, Object>> getInFeedCompanyInfo(@RequestParam Map<String, Object> map) {
		return weighingQueryService.getInFeedCompanyInfo(map);
	}
	
	//获取收料单位信息(ygt)
	@RequestMapping("/getOutReceiveUnitInfo.action")
	@ResponseBody
	public List<Map<String, Object>> getOutReceiveUnitInfo(@RequestParam Map<String, Object> map) {
		return weighingQueryService.getOutReceiveUnitInfo(map);
	}
	
	//添加出库(ygt)
	@RequestMapping("/addExportMeasureOut.action")
	@ResponseBody
	public ResponseInfo addExportMeasureOut(@RequestBody WeighingQueryEntity weighingQueryEntity) {
		ResponseInfo info = new ResponseInfo();
		int res = weighingQueryService.addExportMeasureOut(weighingQueryEntity);
		if (res>0) {
			// 操作成功
			info.setCode(MessageUtil.success);
			info.setMessage(MessageUtil.successInfo);
		} else {
			// 操作失败
			info.setCode(MessageUtil.error);
			info.setMessage(MessageUtil.errorInfo);
		}
		return info;
	}
	
	//称重信息出库编号自增(ygt)
	@RequestMapping("/getWeighingInfoCount.action")
	@ResponseBody
	public Map<String,Object> getCarCount(String type) throws IOException{
		Map<String,Object> resultMap = new HashMap<String,Object>();
		String result = weighingQueryService.getWeighingInfoCount(type);
		resultMap.put("weighingCount", result);
		return resultMap;		
	}
	
	//称重信息入库编号自增(ygt)
	@RequestMapping("/getInWeighingInfoCount.action")
	@ResponseBody
	public Map<String,Object> getInWeighingInfoCount(String type) throws IOException{
		Map<String,Object> resultMap = new HashMap<String,Object>();
		String result = weighingQueryService.getInWeighingInfoCount(type);
		resultMap.put("weighingCount", result);
		return resultMap;		
	}
	
	//添加入库(ygt)
	@RequestMapping("/addExportMeasureIn.action")
	@ResponseBody
	public ResponseInfo addExportMeasureIn(@RequestBody WeighingQueryEntity weighingQueryEntity) {
		ResponseInfo info = new ResponseInfo();
		int res = weighingQueryService.addExportMeasureIn(weighingQueryEntity);
		if (res>0) {
			// 操作成功
			info.setCode(MessageUtil.success);
			info.setMessage(MessageUtil.successInfo);
		} else {
			// 操作失败
			info.setCode(MessageUtil.error);
			info.setMessage(MessageUtil.errorInfo);
		}
		return info;
	}
}
