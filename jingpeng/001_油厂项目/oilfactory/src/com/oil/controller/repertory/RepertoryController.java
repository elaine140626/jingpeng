package com.oil.controller.repertory;

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
import com.oil.model.Datadictionaty;
import com.oil.model.ResponseInfo;
import com.oil.model.dispath.MaterielinfoEntity;
import com.oil.model.dispath.OrderNumberEntity;
import com.oil.model.dispath.SalesOrderEntity;
import com.oil.model.repertory.NoWeighOutWarehouse;
import com.oil.service.repertory.RepertoryService;

@Controller
@RequestMapping("/repertory")
public class RepertoryController {
	
	@Autowired
	private RepertoryService repertoryService;
	
	/**
	 * 根据用户获取对应的销售订单
	 * @param 关键字查询
	 * @return
	 */
	@RequestMapping("/getSalesOrderList.action")
	public @ResponseBody List<SalesOrderEntity> getSalesOrderList(@RequestParam Map<String, Object> map){
		return repertoryService.getSalesOrderList(map);	
	}
	
	/**
	 * 获取页面list信息
	 * @param 
	 * @return
	 */
	@RequestMapping("/getInfoList.action")
	public @ResponseBody DataTablesResponseInfo getInfoList(@RequestParam Map<String, Object> map){
		return repertoryService.getInfoList(map);	
	}
	
	/**
	 * 获取页面出库单list信息
	 * @param 
	 * @return
	 */
	@RequestMapping("/getOutBoundInfoList.action")
	public @ResponseBody DataTablesResponseInfo getOutBoundInfoList(@RequestParam Map<String, Object> map){
		return repertoryService.getOutBoundInfoList(map);	
	}
	
	/**
	 * 获取页面出库单list信息
	 * @param 
	 * @return
	 */
	@RequestMapping("/getUnBoundInfoList.action")
	public @ResponseBody DataTablesResponseInfo getUnBoundInfoList(@RequestParam Map<String, Object> map){
		return repertoryService.getUnBoundInfoList(map);	
	}
	
	/**
	 * 获取页面入库单list信息
	 * @param 
	 * @return
	 */
	@RequestMapping("/getInstoreInfoList.action")
	public @ResponseBody DataTablesResponseInfo getInstoreInfoList(@RequestParam Map<String, Object> map){
		return repertoryService.getInstoreInfoList(map);	
	}
	
	/**
	 * 获取销售订单编号
	 * @param 
	 * @return
	 */
	@RequestMapping("/getOrderNumberList.action")
	public @ResponseBody List<OrderNumberEntity> getOrderNumberList(@RequestParam Map<String, Object> map){
		return repertoryService.getOrderNumberList(map);
	}
	
	/**
	 * 物料名称和型号
	 * @param 
	 * @return
	 */
	@RequestMapping("/getMaterielinfoList.action")
	public @ResponseBody List<MaterielinfoEntity> getMaterielinfoList(@RequestParam Map<String, Object> map){
		List<MaterielinfoEntity> materielinfoList = repertoryService.getMaterielinfoList(map);
		System.out.println("++++++"+materielinfoList);
		return materielinfoList;
		
	}
	
	/**
	 * 获取所有车辆号码
	 * @param 
	 * @return
	 */
	@RequestMapping("/getAllPlateNumbers.action")
	public @ResponseBody List<Map<String,Object>> getAllPlateNumbers(@RequestParam Map<String, Object> map){
		return repertoryService.getAllPlateNumbers(map);
	}
	
	/**
	 * 获取所有客户名称
	 * @param 
	 * @return
	 */
	@RequestMapping("/getAllCustomerName.action")
	public @ResponseBody List<Map<String,Object>> getAllCustomerName(@RequestParam Map<String, Object> map){
		return repertoryService.getAllCustomerName(map);
	}
	
	/**
	 * 获取所有出库单
	 */
	@RequestMapping("/getAllOutbounds.action")
	public @ResponseBody List<Map<String,Object>> getAllOutbounds(@RequestParam Map<String, Object> map){
		return repertoryService.getAllOutbounds(map);
	}
	
	/**
	 * 数据字典查询
	 */
	@RequestMapping("/getAllDatadictionaty.action")
	public @ResponseBody List<Datadictionaty> getAllDatadictionaty(){
		List<Datadictionaty> allDatadictionaty = repertoryService.getAllDatadictionaty();
		System.out.println(allDatadictionaty);
		return allDatadictionaty;
	}
	
	/**
	 * 新增未称重出库单
	 * @param 
	 * @return
	 */
	@RequestMapping("/addNoWeighOutWarehouse.action")
	public @ResponseBody ResponseInfo addNoWeighOutWarehouse(HttpServletRequest request, @RequestParam Map<String, Object> map) throws IOException{
		return repertoryService.addNoWeighOutWarehouse(request, map);
	}
	
	/**
	 * 根据未称重出库单id获取信息
	 * @param 
	 * @return
	 */
	@RequestMapping("/getOutboundInfo.action")
	public @ResponseBody NoWeighOutWarehouse getOutboundInfo(@RequestParam Map<String, Object> map){
		NoWeighOutWarehouse noWeighOutWarehouse = repertoryService.getOutboundInfo(map);
		
		if(noWeighOutWarehouse == null) {
			noWeighOutWarehouse = new NoWeighOutWarehouse();
		}
		return noWeighOutWarehouse;
	}
	
	/**
	 * 更新未称重出库单
	 * @param 
	 * @return
	 */
	@RequestMapping("/updateNoWeighOutWarehouse.action")
	public @ResponseBody ResponseInfo updateNoWeighOutWarehouse(HttpServletRequest request, @RequestParam Map<String, Object> map) throws IOException{
		return repertoryService.updateNoWeighOutWarehouse(request, map);
	}
	
	/**
	 * 未称重出库单删除
	 * @param 
	 * @return
	 * @throws IOException 
	 */
	@RequestMapping("/updateValidFlag.action")
	public @ResponseBody ResponseInfo updateValidFlag(HttpServletRequest request, @RequestParam Map<String, Object> map) throws IOException{
		return repertoryService.updateValidFlag(request, map);	
	}
	
	/**
	 * 联动查询
	 * @param 
	 * @return
	 */
	@RequestMapping("/getOutboundEntitys.action")
	public @ResponseBody List<Map<String, Object>> getOutboundEntitys(@RequestParam Map<String, Object> map){
		List<Map<String, Object>> outboundEntitys = repertoryService.getOutboundEntitys(map);
		return outboundEntitys;
	}
	
	/**
	 * 查询所选销售订单明细
	 * @param 
	 * @return
	 */
	@RequestMapping("/getOrderDetailInfo.action")
	@ResponseBody
	public List<Map<String, Object>> getOrderDetailInfo(@RequestParam Map<String, Object> map) throws IOException{
		return repertoryService.getOrderDetailInfo(map);
	}
	
	/**
	 * 查询车辆信息
	 * @param 
	 * @return
	 */
	@RequestMapping("/getAllCarInfo.action")
	@ResponseBody
	public List<Map<String, Object>> getAllCarInfo(@RequestParam Map<String, Object> map) throws IOException{
		return repertoryService.getAllCarInfo(map);
	}
	
	/**
	 * 查询调拨未称重信息
	 * @param 
	 * @return
	 */
	@RequestMapping("/getDiaoBoNoWeighList.action")
	@ResponseBody
	public List<Map<String, Object>> getDiaoBoNoWeighList(@RequestParam Map<String, Object> map) throws IOException{
		return repertoryService.getDiaoBoNoWeighList(map);
	}
	
	/**
	 * 查询可选的车辆信息
	 * @param 
	 * @return
	 */
	@RequestMapping("/getCarNameList.action")
	@ResponseBody
	public List<Map<String, Object>> getCarNameList(@RequestParam Map<String, Object> map){
		return repertoryService.getCarNameList(map);
	}
	
	//查询未称重中的物料名称
	@RequestMapping("/getMaterialNameSearcList.action")
	@ResponseBody
	public List<Map<String, Object>> getMaterialNameSearcList(@RequestParam Map<String, Object> map){
		return repertoryService.getMaterialNameSearcList(map);
	}

}
