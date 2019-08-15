package com.oil.controller.dispath;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.oil.model.DataTablesResponseInfo;
import com.oil.model.ResponseInfo;
import com.oil.model.dispath.CustomerOrderEntity;
import com.oil.model.dispath.SalesOrderListEntity;
import com.oil.model.system.CarInfo;
import com.oil.service.dispath.ExportMeasureService;

@Controller
@RequestMapping("/ExportMeasure")
public class ExportMeasureController {
	@Autowired
	ExportMeasureService exportMeasureService;
	
    // 左侧客户订单信息树
	@RequestMapping("/getCustomerOrderList.action")
	public @ResponseBody List<CustomerOrderEntity> getCustomerOrderList(@RequestParam Map<String, Object> map){
		return exportMeasureService.getCustomerOrderList(map);	
	}
	
	// 获取调度单已有车牌号
	@RequestMapping("/getPlateNumberList.action")
	public @ResponseBody List<String> getPlateNumberList(@RequestParam Map<String, Object> map){
		return exportMeasureService.getPlateNumberList(map);	
	}

	// 获取页面list信息
	@RequestMapping("/getExportMeasureList.action")
	public @ResponseBody DataTablesResponseInfo getExportMeasureList(@RequestParam Map<String, Object> map){
		return exportMeasureService.getExportMeasureList(map);	
	}
	
	// 获取销售订单编号
	@RequestMapping("/getSalesOrderList.action")
	public @ResponseBody List<SalesOrderListEntity> getSalesOrderList(@RequestParam Map<String, Object> map){
		return exportMeasureService.getSalesOrderList(map);
	}
	
	// 空发：查询所有可选出库单号
	@RequestMapping("/getAllOutboundList.action")
	@ResponseBody
	public List<Map<String, Object>> getAllOutboundList(@RequestParam Map<String, Object> map){
		return exportMeasureService.getAllOutboundList(map);
	}
	
	// 获取所有车辆号码
	@RequestMapping("/getAllPlateNumbers.action")
	public @ResponseBody List<CarInfo> getAllPlateNumbers(@RequestParam Map<String, Object> map){
		return exportMeasureService.getAllPlateNumbers(map);
	}
	
	// 获取所有车牌号码（不包含没有二次称重的车牌号）
	@RequestMapping("/getAllPlateNumbersExc.action")
	public @ResponseBody List<CarInfo> getAllPlateNumbersExc(@RequestParam Map<String, Object> map){
		return exportMeasureService.getAllPlateNumbersExc(map);
	}
	
	// 判断输入车牌号是否没有完成二次称重
	@RequestMapping("/checkPlateNumber.action")
	public @ResponseBody ResponseInfo checkPlateNumber(@RequestParam Map<String, Object> map) {
		ResponseInfo responseInfo = new ResponseInfo();
		int res = exportMeasureService.checkPlateNumber(map);
		if (res>0) {
			responseInfo.setCode("warn");
		} else {
			responseInfo.setCode("success");
		}
		return responseInfo;
	}
	
	// 出库单作废或者删除
	@RequestMapping("/updateValidFlag.action")
	public @ResponseBody ResponseInfo updateValidFlag(HttpServletRequest request, @RequestParam Map<String, Object> map){
		return exportMeasureService.updateValidFlag(map);	
	}

	// 新增出库单
	@RequestMapping("/addExportMeasure.action")
	public @ResponseBody ResponseInfo addExportMeasure(HttpServletRequest request, @RequestParam Map<String, Object> map){
		return exportMeasureService.addExportMeasure(map);
	}
	
	// 更新出库单
	@RequestMapping("/updateExportMeasure.action")
	public @ResponseBody ResponseInfo updateExportMeasure(HttpServletRequest request, @RequestParam Map<String, Object> map) throws IOException{
		return exportMeasureService.updateExportMeasure(map);
	}
	
	// 调度导出
	@RequestMapping("/export.action")
	@ResponseBody
	public void export(HttpServletRequest request,HttpServletResponse response,@RequestParam HashMap<String, Object> map) throws IOException{
		String param = request.getParameter("param");
		Map<String,Object> paramMap= JSON.parseObject(param);
		exportMeasureService.export(request,response,paramMap);		
	}
	
//	/**
//	 * 新增关联空发出库单订单
//	 * @return
//	 */
//	@RequestMapping("/insertEmptyOutBound.action")
//	@ResponseBody
//	public int insertEmptyOutBound(HttpServletRequest request,@RequestParam Map<String, Object> map) throws IOException{
//		return outboundService.insertEmptyOutBound(request, map);
//	}
//	
//	/**
//	 * 修改关联空发出库单订单
//	 * 先删除之前的再新增新的
//	 * @return
//	 */
//	@RequestMapping("/updateEmptyOutBound.action")
//	@ResponseBody
//	public int updateEmptyOutBound(HttpServletRequest request,@RequestParam Map<String, Object> map) throws IOException{
//		return outboundService.updateEmptyOutBound(request, map);
//	}	

//	
//	/**
//	 *   查询所有可选运输单号
//	 * @return
//	 */
//	@RequestMapping("/getTransportList.action")
//	@ResponseBody
//	public List<Map<String, Object>> getTransportList(@RequestParam Map<String, Object> map) throws IOException{
//		return outboundService.getTransportList(map);
//	}
//	

		


	/******出库单查询用********/
	// 获取符合条件的调拨销售订单物料Id
	@RequestMapping("/getDiaoMaterielId.action")
	@ResponseBody
	public List<Map<String, Object>> getDiaoMaterielId(@RequestParam Map<String, Object> map){
		return exportMeasureService.getDiaoMaterielId(map);
	}
	
	// 获取符合条件的调拨销售订单列表
	@RequestMapping("/getDiaoOrderNumber.action")
	@ResponseBody
	public List<Map<String, Object>> getDiaoOrderNumber(@RequestParam Map<String, Object> map){
		return exportMeasureService.getDiaoOrderNumber(map);
	}
	
	// 获取符合条件的调拨销售订单明细列表
	@RequestMapping("/getDiaoOrderDetail.action")
	@ResponseBody
	public List<Map<String, Object>> getDiaoOrderDetail(@RequestParam Map<String, Object> map){
		return exportMeasureService.getDiaoOrderDetail(map);
	}
	// 根据收货地址获取收货人信息
	@RequestMapping("/getConsigneeInfo.action")
	@ResponseBody
	public List<Map<String, Object>> getConsigneeInfo(@RequestParam Map<String, Object> map){
		return exportMeasureService.getConsigneeInfo(map);
	}
}
