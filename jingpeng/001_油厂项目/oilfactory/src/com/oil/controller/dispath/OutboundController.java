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
import com.oil.model.dispath.MaterielinfoEntity;
import com.oil.model.dispath.OrderNumberEntity;
import com.oil.model.dispath.OutboundEntity;
import com.oil.model.dispath.SalesOrderEntity;
import com.oil.model.system.CarInfo;
import com.oil.model.system.Purchasecontract;
import com.oil.service.dispath.OutboundService;

@Controller
@RequestMapping("/outbound")
public class OutboundController {
	@Autowired
	OutboundService outboundService;
	
	/**
	 * 根据用户获取对应的销售订单
	 * @param 关键字查询
	 * @return
	 */
	@RequestMapping("/getSalesOrderList.action")
	public @ResponseBody List<SalesOrderEntity> getSalesOrderList(@RequestParam Map<String, Object> map){
		return outboundService.getSalesOrderList(map);	
	}
	
	/**
	 * 获取调度单已有车牌号
	 * @param 
	 * @return
	 */
	@RequestMapping("/getPlateNumberList.action")
	public @ResponseBody List<String> getPlateNumberList(@RequestParam Map<String, Object> map){
		return outboundService.getPlateNumberList(map);	
	}

	/**
	 * 获取页面list信息
	 * @param 
	 * @return
	 */
	@RequestMapping("/getInfoList.action")
	public @ResponseBody DataTablesResponseInfo getInfoList(@RequestParam Map<String, Object> map){
		return outboundService.getInfoList(map);	
	}
	
	/**
	 * 出库单作废或者删除
	 * @param 
	 * @return
	 * @throws IOException 
	 */
	@RequestMapping("/updateValidFlag.action")
	public @ResponseBody ResponseInfo updateValidFlag(HttpServletRequest request, @RequestParam Map<String, Object> map) throws IOException{
		return outboundService.updateValidFlag(request, map);	
	}
	
	/**
	 * 根据出库单id获取信息
	 * @param 
	 * @return
	 */
	@RequestMapping("/getOutboundInfo.action")
	public @ResponseBody OutboundEntity getOutboundInfo(@RequestParam Map<String, Object> map){
		OutboundEntity outboundEntity = outboundService.getOutboundInfo(map);
		
		if(outboundEntity == null) {
			outboundEntity = new OutboundEntity();
		}
		return outboundEntity;
	}
	
	/**
	 * 获取销售订单编号
	 * @param 
	 * @return
	 */
	@RequestMapping("/getOrderNumberList.action")
	public @ResponseBody List<OrderNumberEntity> getOrderNumberList(@RequestParam Map<String, Object> map){
		return outboundService.getOrderNumberList(map);
	}
	
	/**
	 * 物料名称和型号
	 * @param 
	 * @return
	 */
	@RequestMapping("/getMaterielinfoList.action")
	public @ResponseBody List<MaterielinfoEntity> getMaterielinfoList(@RequestParam Map<String, Object> map){
		return outboundService.getMaterielinfoList(map);
	}
	
	/**
	 * 获取所有车辆号码
	 * @param 
	 * @return
	 */
	@RequestMapping("/getAllPlateNumbers.action")
	public @ResponseBody List<CarInfo> getAllPlateNumbers(@RequestParam Map<String, Object> map){
		return outboundService.getAllPlateNumbers(map);
	}
	
	/**
	 * 采购合同
	 * @param 
	 * @return
	 */
	@RequestMapping("/getPurchasecontractList.action")
	public @ResponseBody List<Purchasecontract> getPurchasecontractList(@RequestParam Map<String, Object> map){
		return outboundService.getPurchasecontractList(map);
	}
	
	/**
	 * 新增出库单
	 * @param 
	 * @return
	 */
	@RequestMapping("/addExportMeasure.action")
	public @ResponseBody ResponseInfo addExportMeasure(HttpServletRequest request, @RequestParam Map<String, Object> map) throws IOException{
		return outboundService.addExportMeasure(request, map);
	}
	
	/**
	 * 更新出库单
	 * @param 
	 * @return
	 */
	@RequestMapping("/updateExportMeasure.action")
	public @ResponseBody ResponseInfo updateExportMeasure(HttpServletRequest request, @RequestParam Map<String, Object> map) throws IOException{
		return outboundService.updateExportMeasure(request, map);
	}
	
	/**
	 * 获取无法选中的车辆信息
	 * @return
	 */
	@RequestMapping("/queryCarInUse.action")
	@ResponseBody
	public List<Map<String,Object>> queryCarInUse() throws IOException{
		return outboundService.queryCarInUse();
	}
	
	/**
	 * 销售订单详细信息
	 * @return
	 */
	@RequestMapping("/getOrderDetail.action")
	@ResponseBody
	public List<Map<String,Object>> getOrderDetail(@RequestParam Map<String, Object> map) throws IOException{
		return outboundService.getOrderDetail(map);
	}
	
	/**
	 * 空发出库单订单
	 * @return
	 */
	@RequestMapping("/getEmptyOutboundInfo.action")
	@ResponseBody
	public List<Map<String,Object>> getEmptyOutboundInfo(@RequestParam Map<String, Object> map) throws IOException{
		return outboundService.getEmptyOutboundInfo(map);
	}
	
	/**
	 * 新增关联空发出库单订单
	 * @return
	 */
	@RequestMapping("/insertEmptyOutBound.action")
	@ResponseBody
	public int insertEmptyOutBound(HttpServletRequest request,@RequestParam Map<String, Object> map) throws IOException{
		return outboundService.insertEmptyOutBound(request, map);
	}
	
	/**
	 * 修改关联空发出库单订单
	 * 先删除之前的再新增新的
	 * @return
	 */
	@RequestMapping("/updateEmptyOutBound.action")
	@ResponseBody
	public int updateEmptyOutBound(HttpServletRequest request,@RequestParam Map<String, Object> map) throws IOException{
		return outboundService.updateEmptyOutBound(request, map);
	}
	
	/**
	 * 先删除出库单封签号
	 * @return
	 */
	@RequestMapping("/updateFacingSlipNum.action")
	@ResponseBody
	public int updateFacingSlipNum(HttpServletRequest request,@RequestParam Map<String, Object> map) throws IOException{
		return outboundService.updateFacingSlipNum(request, map);
	}
	
	/**
	 *   查询所有可选出库单号
	 * @return
	 */
	@RequestMapping("/getAllOutboundList.action")
	@ResponseBody
	public List<Map<String, Object>> getAllOutboundList() throws IOException{
		return outboundService.getAllOutboundList();
	}
	
	/**
	 *   查询所有可选运输单号
	 * @return
	 */
	@RequestMapping("/getTransportList.action")
	@ResponseBody
	public List<Map<String, Object>> getTransportList(@RequestParam Map<String, Object> map) throws IOException{
		return outboundService.getTransportList(map);
	}
	
	/**
	 *   查询所有可选销售订单明细
	 * @return
	 */
	@RequestMapping("/getOrderDetailInfo.action")
	@ResponseBody
	public List<Map<String, Object>> getOrderDetailInfo(@RequestParam Map<String, Object> map) throws IOException{
		return outboundService.getOrderDetailInfo(map);
	}
	
	/**
	 *   查询该用户运距，止运地
	 * @return
	 */
	@RequestMapping("/getCustomerTrans.action")
	@ResponseBody
	public List<Map<String, Object>> getCustomerTrans(@RequestParam Map<String, Object> map) throws IOException{
		return outboundService.getCustomerTrans(map);
	}
	
	/**
	 *  删除前校验是否有关联的运输单
	 * @return
	 */
	@RequestMapping("/checkTransList.action")
	@ResponseBody
	public List<Map<String, Object>> checkTransList(@RequestParam Map<String, Object> map) throws IOException{
		return outboundService.checkTransList(map);
	}
	
	/**
	 * 获取符合条件的调拨销售订单物料Id
	 * @return
	 */
	@RequestMapping("/getDiaoMaterielId.action")
	@ResponseBody
	public List<Map<String, Object>> getDiaoMaterielId(@RequestParam Map<String, Object> map) throws IOException{
		return outboundService.getDiaoMaterielId(map);
	}
	
	/**
	 * 获取符合条件的调拨销售订单列表
	 * @return
	 */
	@RequestMapping("/getDiaoOrderNumber.action")
	@ResponseBody
	public List<Map<String, Object>> getDiaoOrderNumber(@RequestParam Map<String, Object> map) throws IOException{
		return outboundService.getDiaoOrderNumber(map);
	}
	
	/**
	 * 获取符合条件的调拨销售订单明细列表
	 * @return
	 */
	@RequestMapping("/getDiaoOrderDetail.action")
	@ResponseBody
	public List<Map<String, Object>> getDiaoOrderDetail(@RequestParam Map<String, Object> map) throws IOException{
		return outboundService.getDiaoOrderDetail(map);
	}
	
	/**
	 * 根据收货地址获取收货人信息
	 * @return
	 */
	@RequestMapping("/getConsigneeInfo.action")
	@ResponseBody
	public List<Map<String, Object>> getConsigneeInfo(@RequestParam Map<String, Object> map) throws IOException{
		return outboundService.getConsigneeInfo(map);
	}
	
	/**
	 * 获取当前出库单相关重量
	 * @return
	 */
	@RequestMapping("/getOutBoundWeight.action")
	@ResponseBody
	public List<Map<String, Object>> getOutBoundWeight(@RequestParam Map<String, Object> map) throws IOException{
		return outboundService.getOutBoundWeight(map);
	}
	
	/**
	 * 修改当前出库单相关重量
	 * @return
	 */
	@RequestMapping("/updateOutBoundWeight.action")
	@ResponseBody
	public ResponseInfo updateOutBoundWeight(@RequestParam Map<String, Object> map) throws IOException{
		return outboundService.updateOutBoundWeight(map);
	}
	
	
	// 导出
	@RequestMapping("/export.action")
	@ResponseBody
	public void export(HttpServletRequest request,HttpServletResponse response,@RequestParam HashMap<String, Object> map) throws IOException{
		String param = request.getParameter("param");
		Map<String,Object> paramMap= JSON.parseObject(param);
		outboundService.export(request,response,paramMap);		
	}
}
