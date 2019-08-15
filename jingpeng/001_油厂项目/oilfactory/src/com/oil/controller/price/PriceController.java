package com.oil.controller.price;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.oil.model.DataTablesResponseInfo;
import com.oil.model.ResponseInfo;
import com.oil.model.dispath.MaterielinfoEntity;
import com.oil.model.dispath.OrderNumberEntity;
import com.oil.model.dispath.OutboundEntity;
import com.oil.model.dispath.SalesOrderEntity;
import com.oil.model.system.CarInfo;
import com.oil.model.system.Purchasecontract;
import com.oil.service.price.PriceService;

@Controller
@RequestMapping("/price")
public class PriceController {
	@Autowired
	PriceService priceService;
	
	/**
	 * 根据用户获取对应的销售订单
	 * @param 关键字查询
	 * @return
	 */
	@RequestMapping("/getSalesOrderList.action")
	public @ResponseBody List<SalesOrderEntity> getSalesOrderList(@RequestParam Map<String, Object> map){
		return priceService.getSalesOrderList(map);	
	}
	
	/**
	 * 获取调度单已有车牌号
	 * @param 
	 * @return
	 */
	@RequestMapping("/getPlateNumberList.action")
	public @ResponseBody List<String> getPlateNumberList(@RequestParam Map<String, Object> map){
		return priceService.getPlateNumberList(map);	
	}

	/**
	 * 获取页面list信息
	 * @param 
	 * @return
	 */
	@RequestMapping("/getInfoList.action")
	public @ResponseBody DataTablesResponseInfo getInfoList(@RequestParam Map<String, Object> map){
		return priceService.getInfoList(map);	
	}
	
	/**
	 * 出库单作废或者删除
	 * @param 
	 * @return
	 * @throws IOException 
	 */
	@RequestMapping("/updateValidFlag.action")
	public @ResponseBody ResponseInfo updateValidFlag(HttpServletRequest request, @RequestParam Map<String, Object> map) throws IOException{
		return priceService.updateValidFlag(request, map);	
	}
	
	/**
	 * 根据出库单id获取信息
	 * @param 
	 * @return
	 */
	@RequestMapping("/getOutboundInfo.action")
	public @ResponseBody OutboundEntity getOutboundInfo(@RequestParam Map<String, Object> map){
		OutboundEntity outboundEntity = priceService.getOutboundInfo(map);
		
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
		return priceService.getOrderNumberList(map);
	}
	
	/**
	 * 物料名称和型号
	 * @param 
	 * @return
	 */
	@RequestMapping("/getMaterielinfoList.action")
	public @ResponseBody List<Map<String, Object>> getMaterielinfoList(@RequestParam Map<String, Object> map){
		return priceService.getMaterielinfoList(map);
	}
	
	/**
	 * 获取所有车辆号码
	 * @param 
	 * @return
	 */
	@RequestMapping("/getAllPlateNumbers.action")
	public @ResponseBody List<CarInfo> getAllPlateNumbers(@RequestParam Map<String, Object> map){
		return priceService.getAllPlateNumbers(map);
	}
	
	/**
	 * 采购合同
	 * @param 
	 * @return
	 */
	@RequestMapping("/getPurchasecontractList.action")
	public @ResponseBody List<Purchasecontract> getPurchasecontractList(@RequestParam Map<String, Object> map){
		return priceService.getPurchasecontractList(map);
	}
	
	/**
	 * 新增出库单
	 * @param 
	 * @return
	 */
	@RequestMapping("/addExportMeasure.action")
	public @ResponseBody ResponseInfo addExportMeasure(HttpServletRequest request, @RequestParam Map<String, Object> map) throws IOException{
		return priceService.addExportMeasure(request, map);
	}
	
	/**
	 * 更新出库单
	 * @param 
	 * @return
	 */
	@RequestMapping("/updateExportMeasure.action")
	public @ResponseBody ResponseInfo updateExportMeasure(HttpServletRequest request, @RequestParam Map<String, Object> map) throws IOException{
		return priceService.updateExportMeasure(request, map);
	}
	
	/**
	 * 获取无法选中的车辆信息
	 * @return
	 */
	@RequestMapping("/queryCarInUse.action")
	@ResponseBody
	public List<Map<String,Object>> queryCarInUse() throws IOException{
		return priceService.queryCarInUse();
	}
	
	/**
	 * 销售订单详细信息
	 * @return
	 */
	@RequestMapping("/getOrderDetail.action")
	@ResponseBody
	public List<Map<String,Object>> getOrderDetail(@RequestParam Map<String, Object> map) throws IOException{
		return priceService.getOrderDetail(map);
	}
	
	/**
	 * 空发出库单订单
	 * @return
	 */
	@RequestMapping("/getEmptyOutboundInfo.action")
	@ResponseBody
	public List<Map<String,Object>> getEmptyOutboundInfo(@RequestParam Map<String, Object> map) throws IOException{
		return priceService.getEmptyOutboundInfo(map);
	}
	
	/**
	 * 新增关联空发出库单订单
	 * @return
	 */
	@RequestMapping("/insertEmptyOutBound.action")
	@ResponseBody
	public ResponseInfo insertEmptyOutBound(HttpServletRequest request,@RequestParam Map<String, Object> map) throws IOException{
		return priceService.insertEmptyOutBound(request, map);
	}
	
	/**
	 * 修改关联空发出库单订单
	 * 先删除之前的再新增新的
	 * @return
	 */
	@RequestMapping("/updateEmptyOutBound.action")
	@ResponseBody
	public ResponseInfo updateEmptyOutBound(HttpServletRequest request,@RequestParam Map<String, Object> map) throws IOException{
		return priceService.updateEmptyOutBound(request, map);
	}
	
	/**
	 * 先删除出库单封签号
	 * @return
	 */
	@RequestMapping("/updateFacingSlipNum.action")
	@ResponseBody
	public int updateFacingSlipNum(HttpServletRequest request,@RequestParam Map<String, Object> map) throws IOException{
		return priceService.updateFacingSlipNum(request, map);
	}

	/**
	 * 修改价格的查询
	 * @return
	 */
	@RequestMapping("/getEmptyPriceInfo.action")
	@ResponseBody
	public List<Map<String,Object>> getEmptyPriceInfo(@RequestParam Map<String, Object> map) throws IOException{
		String str = map.get("id").toString();
		String[] list = str.split(",");
		map.put("id", list);
		return priceService.getEmptyPriceInfo(map);
	}
	/**
	 * 修改价格的提交
	 * @return
	 */
	@RequestMapping("/updateEmptyPriceInfo.action")
	@ResponseBody
	public ResponseInfo updateEmptyPriceInfo(HttpServletRequest request,@RequestParam Map<String, Object> map) throws IOException{
		return priceService.updateEmptyPriceInfo(request, map);
	}
	
	@RequestMapping("/getCustomerTrans.action")
	@ResponseBody
	public List<Map<String, Object>> getCustomerTrans(@RequestParam Map<String, Object> map) throws IOException{
		return priceService.getCustomerTrans(map);
	}
	
	//添加财务报价记录
	@RequestMapping("/addOfferrecord.action")
	@ResponseBody
	public ResponseInfo addOfferrecord(HttpServletRequest request,@RequestParam Map<String, Object> map) throws IOException{
		return priceService.addOfferrecord(request, map);
	}
	
	//查询所有的财务记录
	@RequestMapping("/getAllOfferrecord.action")
	public @ResponseBody DataTablesResponseInfo getAllOfferrecord(@RequestParam Map<String, Object> map){
		return priceService.getAllOfferrecord(map);	
	}
	
	@RequestMapping("/getOfferrecordTree.action")
	public @ResponseBody List<SalesOrderEntity> getOfferrecordTree(@RequestParam Map<String, Object> map){
		return priceService.getOfferrecordTree(map);	
	}
}
