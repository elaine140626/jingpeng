package com.oil.controller.dispath;

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
import com.oil.model.dispath.ContractEntity;
import com.oil.model.dispath.InstroeEntity;
import com.oil.model.dispath.SalesContractEntity;
import com.oil.service.dispath.InstroeService;

@Controller
@RequestMapping("/instore")
public class InstroeController {

	@Autowired
	InstroeService instroeService;
	
	/**
	 * 根据用户获取对应的销售订单
	 * @param 关键字查询
	 * @return
	 */
	@RequestMapping("/getSalesContractList.action")
	public @ResponseBody List<SalesContractEntity> getSalesContractList(@RequestParam Map<String, Object> map){
		return instroeService.getSalesContractList(map);	
	}
	
	/**
	 * 获取调度单已有车牌号
	 * @param 
	 * @return
	 */
	@RequestMapping("/getPlateNumberList.action")
	public @ResponseBody List<String> getPlateNumberList(@RequestParam Map<String, Object> map){
		return instroeService.getPlateNumberList(map);	
	}
	
	/**
	 * 获取页面list信息
	 * @param 
	 * @return
	 */
	@RequestMapping("/getInfoList.action")
	public @ResponseBody DataTablesResponseInfo getInfoList(@RequestParam Map<String, Object> map){
		return instroeService.getInfoList(map);	
	}
	
	/**
	 * 根据入库单id获取信息
	 * @param 
	 * @return
	 */
	@RequestMapping("/getInstoreInfo.action")
	public @ResponseBody InstroeEntity getOutboundInfo(@RequestParam Map<String, Object> map){
		InstroeEntity instroeEntity = instroeService.getInstroeInfo(map);
		
		if(instroeEntity == null) {
			instroeEntity = new InstroeEntity();
		}
		return instroeEntity;
	}
	
	/**
	 * 入库单作废或者删除
	 * @param 
	 * @return
	 * @throws IOException 
	 */
	@RequestMapping("/updateValidFlag.action")
	public @ResponseBody ResponseInfo updateValidFlag(HttpServletRequest request, @RequestParam Map<String, Object> map) throws IOException{
		return instroeService.updateValidFlag(request, map);	
	}
	
	/**
	 * 新增入库单
	 * @param 
	 * @return
	 */
	@RequestMapping("/addImportMeasure.action")
	public @ResponseBody ResponseInfo addImportMeasure(HttpServletRequest request, @RequestParam Map<String, Object> map) throws IOException{
		return instroeService.addImportMeasure(request, map);
	}
	
	/**
	 * 更新入库单
	 * @param 
	 * @return
	 */
	@RequestMapping("/updateImportMeasure.action")
	public @ResponseBody ResponseInfo updateImportMeasure(HttpServletRequest request, @RequestParam Map<String, Object> map) throws IOException{
		return instroeService.updateImportMeasure(request, map);
	}
	
	/**
	 * 获取销售合同编号信息
	 * @param 
	 * @return
	 */
	@RequestMapping("/getContractList.action")
	public @ResponseBody List<ContractEntity> getContractList(@RequestParam Map<String, Object> map){
		return instroeService.getContractList(map);
	}
	
	/**
	 * 获取无法选中的车辆信息
	 * @return
	 */
	@RequestMapping("/queryCarInUse.action")
	@ResponseBody
	public List<Map<String,Object>> queryCarInUse() throws IOException{
		return instroeService.queryCarInUse();
	}
	
	/**
	 * 根据出库单流水号查询运输单流水号
	 * @return
	 */
	@RequestMapping("/queryBillNumber.action")
	@ResponseBody
	public List<Map<String,Object>> queryBillNumber(@RequestParam Map<String, Object> map) throws IOException{
		return instroeService.queryBillNumber(map);
	}
	
	/**
	 * 获取当前出库单相关重量
	 * @return
	 */
	@RequestMapping("/getOutBoundWeight.action")
	@ResponseBody
	public List<Map<String, Object>> getInBoundWeight(@RequestParam Map<String, Object> map) throws IOException{
		return instroeService.getInBoundWeight(map);
	}
	
	/**
	 * 修改当前出库单相关重量
	 * @return
	 */
	@RequestMapping("/updateOutBoundWeight.action")
	@ResponseBody
	public ResponseInfo updateInBoundWeight(@RequestParam Map<String, Object> map) throws IOException{
		return instroeService.updateInBoundWeight(map);
	}
}
