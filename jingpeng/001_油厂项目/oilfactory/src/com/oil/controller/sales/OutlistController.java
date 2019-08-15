package com.oil.controller.sales;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.oil.model.DataTablesResponseInfo;
import com.oil.model.ResponseInfo;
import com.oil.model.dispath.InstroeEntity;
import com.oil.model.dispath.OutboundEntity;
import com.oil.model.sales.CustomerInfoEntity;
import com.oil.model.sales.NoweighEntity;
import com.oil.service.repertory.RepertoryService;
import com.oil.service.sales.OutlistService;

@Controller
@RequestMapping("/outlist")
public class OutlistController {

	@Autowired
	OutlistService outlistService;
	
	@Autowired
	RepertoryService repertoryService;
	
	// 树形显示信息
	@RequestMapping("/getCustomerInfo.action")
	@ResponseBody
	public List<CustomerInfoEntity> getCustomerInfo(@RequestParam Map<String, Object> map) {
		// TODO Auto-generated method stub
		return outlistService.getCustomerInfo(map);
	}

	// 销售订单信息
	@RequestMapping("/getSalesList.action")
	@ResponseBody
	public DataTablesResponseInfo getSalesList(@RequestParam Map<String, Object> map) {
		DataTablesResponseInfo dInfo = new DataTablesResponseInfo();
		List<OutboundEntity> list = outlistService.getSalesList(map);
		dInfo.setData(list);
		return dInfo;
	}
	
	// 出库单list信息
	@RequestMapping("/getExportList.action")
	@ResponseBody
	public DataTablesResponseInfo getExportList(@RequestParam Map<String, Object> map) {
		DataTablesResponseInfo dInfo = new DataTablesResponseInfo();
		List<OutboundEntity> list = outlistService.getExportList(map);
		dInfo.setData(list);
		return dInfo;
	}
	
	// 销售订单信息
	@RequestMapping("/getInfoList.action")
	@ResponseBody
	public DataTablesResponseInfo getInfoList(@RequestParam Map<String, Object> map) {
		DataTablesResponseInfo dInfo = new DataTablesResponseInfo();
		List<OutboundEntity> list = outlistService.getInfoList(map);
		dInfo.setData(list);
		return dInfo;
	}
		
	// 未称重信息
	@RequestMapping("/getNoweighoutList.action")
	@ResponseBody
	public DataTablesResponseInfo getNoweighoutList(@RequestParam Map<String, Object> map) {
		DataTablesResponseInfo dInfo = new DataTablesResponseInfo();
		List<NoweighEntity> list = outlistService.getNoweighoutList(map);
		dInfo.setData(list);
		return dInfo;
	}

	// 未称重信息
	@RequestMapping("/getRepertoryInfoList.action")
	@ResponseBody
	public DataTablesResponseInfo getRepertoryInfoList(@RequestParam Map<String, Object> map) {
		return repertoryService.getInfoList(map);
	}
	
	// 来料加工
	@RequestMapping("/getProcessList.action")
	@ResponseBody
	public DataTablesResponseInfo getProcessList(@RequestParam Map<String, Object> map) {
		DataTablesResponseInfo dInfo = new DataTablesResponseInfo();
		List<InstroeEntity> list = outlistService.getProcessList(map);
		dInfo.setData(list);
		return dInfo;
	}
	
	// 退货
	@RequestMapping("/refund.action")
	@ResponseBody
	public ResponseInfo updateRefund(HttpServletRequest request, @RequestParam Map<String, Object> map) throws IOException {
		ResponseInfo dInfo = new ResponseInfo();
		dInfo = outlistService.updateRefund(request, map);
		return dInfo;
	}
	
	// 半车调拨
	@RequestMapping("/haltCar.action")
	@ResponseBody
	public ResponseInfo updateHaltCar(HttpServletRequest request, @RequestParam Map<String, Object> map) throws IOException {
		ResponseInfo dInfo = new ResponseInfo();
		dInfo = outlistService.updateHaltCar(request, map);
		return dInfo;
	}
	
	/**
	 * 获取调度单已有车牌号
	 * @param 
	 * @return
	 */
	@RequestMapping("/getPlateNumberList.action")
	public @ResponseBody List<String> getPlateNumberList(@RequestParam Map<String, Object> map){
		return outlistService.getPlateNumberList(map);	
	}
}
