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
import com.truckscale.weighingManagement.model.WeighingQueryOtherOutEntity;
import com.truckscale.weighingManagement.service.WeighingQueryOtherOutService;

@Controller
@RequestMapping("/WeighingQueryOtherOut")
public class WeighingQueryOtherOutController {

	@Autowired
	private WeighingQueryOtherOutService weighingQueryOtherOutService;
	
	// 查询其他出库单信息
	@RequestMapping("/getWeighingQueryOtherOut.action")
	@ResponseBody
	public DataTablesResponseInfo getWeighingQueryOtherOut(HttpServletRequest request, @RequestParam Map<String, Object> map) {
		DataTablesResponseInfo dInfo = new DataTablesResponseInfo();
		List<WeighingQueryOtherOutEntity> dataList = weighingQueryOtherOutService.getWeighingQueryOtherOut(map);
		dInfo.setData(dataList);
		return dInfo;
	};
	//获取datalist
	@RequestMapping("/getDataList.action")
	@ResponseBody
	public Map<String, Object> getDataList(@RequestParam Map<String, Object> map) {
		return weighingQueryOtherOutService.getDataList(map);
	}
	//删除其他出库单信息
	@RequestMapping("/deleteWeighingQueryOtherOut.action")
	@ResponseBody
	public ResponseInfo deleteWeighingQueryOtherOut(@RequestParam Map<String, Object> map) {
		ResponseInfo info = new ResponseInfo();
		int res = weighingQueryOtherOutService.deleteWeighingQueryOtherOut(map);
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
	
	//编辑其他出库单信息
	@RequestMapping("/updateWeighingQueryOtherOut.action")
	@ResponseBody
	public ResponseInfo updateWeighingQueryOtherOut(@RequestBody WeighingQueryOtherOutEntity weighingQueryOtherOutEntity) {
		ResponseInfo info = new ResponseInfo();
		int res = weighingQueryOtherOutService.updateWeighingQueryOtherOut(weighingQueryOtherOutEntity);
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
	
	//添加其他出库单信息
	@RequestMapping("/insertWeighingQueryOtherOut.action")
	@ResponseBody
	public ResponseInfo insertWeighingQueryOtherOut(@RequestBody WeighingQueryOtherOutEntity weighingQueryOtherOutEntity) {
		ResponseInfo info = new ResponseInfo();
		int res = weighingQueryOtherOutService.insertWeighingQueryOtherOut(weighingQueryOtherOutEntity);
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
	//删除司机电子签名
	@RequestMapping("/deleteElectronicsName.action")
	@ResponseBody
	public ResponseInfo deleteElectronicsName(@RequestParam Map<String, Object> map) {
		ResponseInfo info = new ResponseInfo();
		int res = weighingQueryOtherOutService.deleteElectronicsName(map);
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
	
	//小票打印
	@RequestMapping("/getWeighingQueryOtherInfo.action")
	@ResponseBody
	public List<WeighingQueryOtherOutEntity> getWeighingQueryOtherInfo(@RequestParam Map<String, Object> map) {
		return weighingQueryOtherOutService.getWeighingQueryOtherInfo(map);
	}
}
