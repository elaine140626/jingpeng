package com.truckscale.weighingManagement.controller;

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

import com.truckscale.common.model.DataTablesResponseInfo;
import com.truckscale.common.model.ResponseInfo;
import com.truckscale.common.util.MessageUtil;
import com.truckscale.weighingManagement.model.NoweighEntity;
import com.truckscale.weighingManagement.model.Testreport;
import com.truckscale.weighingManagement.model.Testreportsaledetailed;
import com.truckscale.weighingManagement.model.WeighingQueryOutEntity;
import com.truckscale.weighingManagement.model.WeighingQueryOutPrint;
import com.truckscale.weighingManagement.service.WeighingQueryOutService;

@Controller
@RequestMapping("/WeighingQueryOut")
public class WeighingQueryOutController {

	@Autowired
	private WeighingQueryOutService weighingQueryOutService;
	
	// 查询出库单信息
	@RequestMapping("/getWeighingQueryOut.action")
	@ResponseBody
	public DataTablesResponseInfo getWeighingQueryOut(HttpServletRequest request, @RequestParam Map<String, Object> map) {
		DataTablesResponseInfo dInfo = new DataTablesResponseInfo();
		List<WeighingQueryOutEntity> dataList = weighingQueryOutService.getWeighingQueryOut(map);
		dInfo.setData(dataList);
		return dInfo;
	};

	//删除出库单信息
	@RequestMapping("/deleteWeighingQueryOut.action")
	@ResponseBody
	public ResponseInfo deleteWeighingQueryOut(@RequestParam Map<String, Object> map) {
		ResponseInfo info = new ResponseInfo();
		int res = weighingQueryOutService.deleteWeighingQueryOut(map);
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
	
	//编辑出库单信息
	@RequestMapping("/updateWeighingQueryOut.action")
	@ResponseBody
	public ResponseInfo updateWeighingQueryOut(@RequestBody WeighingQueryOutEntity weighingQueryOutEntity) {
		ResponseInfo info = new ResponseInfo();
		int res = weighingQueryOutService.updateWeighingQueryOut(weighingQueryOutEntity);
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
	
	
	//查询出库单的打印信息
	@RequestMapping("/getWeighingQueryOutPrintInfo.action")
	@ResponseBody
	public List<WeighingQueryOutPrint> getWeighingQueryOutPrintInfo(@RequestParam Map<String, Object> map){
		return weighingQueryOutService.getWeighingQueryOutPrintInfo(map);
	}
	
	//查询出库单的打印信息
	@RequestMapping("/getNoweighEntityPrintInfo.action")
	@ResponseBody
	public List<NoweighEntity> getNoweighEntityPrintInfo(@RequestParam Map<String, Object> map){
		return weighingQueryOutService.getNoweighEntityPrintInfo(map);
	}
	
	//报告信息
	@RequestMapping("/getTestreportInfoById.action")
	@ResponseBody
	public Map<String,Object> getTestreportInfoById(HttpServletRequest request,@RequestParam HashMap<String, Object> map){
		Map<String,Object> resultMap = new HashMap<String,Object>();
		//出库单检测报告单明细
		List<Testreport> testreportListList = weighingQueryOutService.getTestreportInfo(map);
		if (testreportListList.size() > 0 && testreportListList != null) {
			map.put("testReportId", testreportListList.get(0).getId());	
		}
		//检测报告单销售订单明细
		List<Testreportsaledetailed> testreportsaledetailedList = weighingQueryOutService.getTestreportsaledetailedInfo(map);
		resultMap.put("testreportListList",testreportListList);
		resultMap.put("testreportsaledetailedList", testreportsaledetailedList);
		return resultMap;
	}
	
	//获取电子公章检测单位表
	@RequestMapping("/getCachetCompany.action")
	@ResponseBody
	public List<Map<String,Object>> getCachetCompany(HttpServletRequest request,@RequestParam HashMap<String, Object> map){
		return weighingQueryOutService.getCachetCompany(map);
	}
	
	//获取电子公章人员表
	@RequestMapping("/getCachetPersonnel.action")
	@ResponseBody
	public List<Map<String,Object>> getCachetPersonnel(HttpServletRequest request,@RequestParam HashMap<String, Object> map){
		return weighingQueryOutService.getCachetPersonnel(map);
	}
}
