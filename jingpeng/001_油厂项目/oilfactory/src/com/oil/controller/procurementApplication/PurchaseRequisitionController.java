package com.oil.controller.procurementApplication;

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
import com.oil.model.procurementApplication.PurchaseRequisition;
import com.oil.model.repertory.NoWeighOutWarehouse;
import com.oil.service.procurementApplication.PurchaseRequisitionService;

@Controller
@RequestMapping("/purchaseRequisition")
public class PurchaseRequisitionController {

	@Autowired
	private PurchaseRequisitionService purchaseRequisitionService;
	
	//获取tree信息
	@RequestMapping("/getTreeList.action")
	@ResponseBody
	public List<PurchaseRequisition> getTreeList(HttpServletRequest request,@RequestParam Map<String, Object> map) {
		return purchaseRequisitionService.getTreeList(map);
	}
	
	//获取页面的list信息
	@RequestMapping("/getInfoList.action")
	@ResponseBody
	public DataTablesResponseInfo getInfoList(HttpServletRequest request,@RequestParam Map<String, Object> map) {
		return purchaseRequisitionService.getInfoList(map);
	}
	
	//删除采购申请单
	@RequestMapping("/deletePurchaseRequisition.action")
	@ResponseBody
	public ResponseInfo deletePurchaseRequisition(HttpServletRequest request,@RequestParam Map<String, Object> map)throws IOException  {
		return purchaseRequisitionService.deletePurchaseRequisition(request, map);
	}
	
	//数据字典查询
	@RequestMapping("/getAllDatadictionaty.action")
	@ResponseBody
	public List<Datadictionaty> getAllDatadictionaty(){
		List<Datadictionaty> allDatadictionaty = purchaseRequisitionService.getAllDatadictionaty();
		return allDatadictionaty;
	}
	
	//物料名称
	@RequestMapping("/getMaterielinfoList.action")
	@ResponseBody
	public List<MaterielinfoEntity> getMaterielinfoList(@RequestParam Map<String, Object> map){
		List<MaterielinfoEntity> materielinfoList = purchaseRequisitionService.getMaterielinfoList(map);
		return materielinfoList;
	}
	
	//物料型号
	@RequestMapping("/getMaterielModel.action")
	@ResponseBody
	public List<MaterielinfoEntity> getMaterielModel(@RequestParam Map<String, Object> map){
		List<MaterielinfoEntity> materielinfoList = purchaseRequisitionService.getMaterielModel(map);
		return materielinfoList;
	}
	
	//新增采购申请单
	@RequestMapping("/addPurchaseRequisition.action")
	@ResponseBody
	public  ResponseInfo addPurchaseRequisition(HttpServletRequest request, @RequestParam Map<String, Object> map) throws IOException{
		return purchaseRequisitionService.addPurchaseRequisition(request, map);
	}
	
	//修改采购申请单
	@RequestMapping("/updatePurchaseRequisition.action")
	@ResponseBody
	public  ResponseInfo updatePurchaseRequisition(HttpServletRequest request, @RequestParam Map<String, Object> map) throws IOException{
		return purchaseRequisitionService.updatePurchaseRequisition(request, map);
	}
	
	//根据id查询采购申请单
	@RequestMapping("/getInfoListById.action")
	@ResponseBody
	public PurchaseRequisition getInfoListById(@RequestParam Map<String, Object> map){
		PurchaseRequisition infoListById = purchaseRequisitionService.getInfoListById(map);
		if(infoListById == null) {
			infoListById = new PurchaseRequisition();
		}
		return infoListById;
	}
	
	//查询采购和同明细
	@RequestMapping("/getAllPurchaseorderinfo.action")
	@ResponseBody
	public  List<Map<String, Object>> getAllPurchaseorderinfo(HttpServletRequest request, @RequestParam Map<String, Object> map) throws IOException{
		return purchaseRequisitionService.getAllPurchaseorderinfo(map);
	}
}
