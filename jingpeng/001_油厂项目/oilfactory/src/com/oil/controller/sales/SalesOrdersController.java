package com.oil.controller.sales;

import java.io.IOException;
import java.util.ArrayList;
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
import com.oil.model.contract.ContractTreeEntity;
import com.oil.model.sales.Customerinfo;
import com.oil.model.sales.SalesOrdersDetailedEntity;
import com.oil.model.sales.SalesOrdersInfoEntity;
import com.oil.service.sales.SalesOrdersService;
import com.oil.util.PropertyUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Controller
@RequestMapping("/salesOrders")
public class SalesOrdersController {
	@Autowired
	private SalesOrdersService salesOrdersService;
	
	// 客户信息获取
	@RequestMapping("/getCustomerInfo.action")
	@ResponseBody
	public Customerinfo getCustomerInfo(@RequestParam HashMap<String, Object> map){
		Customerinfo list = salesOrdersService.getCustomerInfo(map);
		return list;
	}
	
	// 销售订单表获取datatable
	@RequestMapping("/getSalesOrdersInfo.action")
	@ResponseBody
	public DataTablesResponseInfo getSalesOrdersInfo(HttpServletRequest request,@RequestParam HashMap<String, Object> map){
		DataTablesResponseInfo dInfo = new DataTablesResponseInfo();
		List<SalesOrdersInfoEntity> list = salesOrdersService.getSalesOrdersInfo(map);
		dInfo.setData(list);
		return dInfo;
	}
	
	// 销售订单单条获取
	@RequestMapping("/getSalesOrdersInfoById.action")
	@ResponseBody
	public Map<String,Object> getSalesOrdersInfoById(HttpServletRequest request,@RequestParam HashMap<String, Object> map){
		Map<String,Object> resultMap = new HashMap<String,Object>();
		// 销售订单取得
		List<SalesOrdersInfoEntity> SalesOrdersInfoList = salesOrdersService.getSalesOrdersInfo(map);
		// 销售订单明细查询
		List<SalesOrdersDetailedEntity> SalesOrdersDetailedList = salesOrdersService.getSalesOrdersDetailed(map);
		
		resultMap.put("SalesOrdersInfoList", SalesOrdersInfoList);
		resultMap.put("SalesOrdersDetailedList", SalesOrdersDetailedList);
		return resultMap;
	}
	
	// 销售订单信息新增
	@RequestMapping("/addSalesOrdersInfo.action")
	@ResponseBody
	public ResponseInfo addSalesOrdersInfo(@RequestParam Map<String, Object> param) throws IOException{
		ResponseInfo info = new ResponseInfo();
		// 0:新增  1:修改  2:复制
		String flag = param.get("flag").toString();
		 
		String count = "0";
		// 新增或复制的场合
		if ( "0".equals(flag) || "2".equals(flag) ) {		
			count = salesOrdersService.checkOrderNumber(param.get("OrderNumber").toString(),"");
		} else {
			// 修改的场合
			count = salesOrdersService.checkOrderNumber(param.get("OrderNumber").toString(),param.get("id").toString());
		}		
		
		if ("0".equals(count)) {
			// 合同信息表
			SalesOrdersInfoEntity salesOrdersInfoEntity = new SalesOrdersInfoEntity();
			// 修改的场合
			if ("1".equals(flag)) {
				salesOrdersInfoEntity.setId(Integer.parseInt(param.get("id").toString()));
			}
			salesOrdersInfoEntity.setOrderNumber(param.get("OrderNumber").toString());
			salesOrdersInfoEntity.setContractId(Integer.parseInt(param.get("ContractId").toString()));
			salesOrdersInfoEntity.setCustomerId(Integer.parseInt(param.get("CustomerId").toString()));
			salesOrdersInfoEntity.setCustomerAlias(param.get("CustomerAlias").toString());
			salesOrdersInfoEntity.setOrderState(param.get("OrderState").toString());
			salesOrdersInfoEntity.setAddress(param.get("Address").toString());
			salesOrdersInfoEntity.setContacts(param.get("Contacts").toString());
			salesOrdersInfoEntity.setTelephone(param.get("Telephone").toString());
			salesOrdersInfoEntity.setSalesCompanyId(Integer.parseInt(param.get("SalesCompanyId").toString()));
			salesOrdersInfoEntity.setUserInfoId(Integer.parseInt(param.get("UserInfoId").toString()));
			salesOrdersInfoEntity.setRemarks(param.get("remarks").toString());
			salesOrdersInfoEntity.setCreater(param.get("Creater").toString());

			// 合同明细表
			// 首先把字符串转成 JSONArray  对象
			JSONArray jsonArray=JSONArray.fromObject(param.get("SalesOrdersDetailedList"));
			List<SalesOrdersDetailedEntity> SalesOrdersDetailedList = new ArrayList<SalesOrdersDetailedEntity>();
			if(jsonArray.size()>0){
				for(int i=0;i<jsonArray.size();i++){
					SalesOrdersDetailedEntity salesOrdersDetailedEntity = new SalesOrdersDetailedEntity();
					// 遍历 jsonarray 数组，把每一个对象转成 json 对象
					JSONObject job = jsonArray.getJSONObject(i);					
					if(job.containsKey("orderDetailedNumber")) {
						salesOrdersDetailedEntity.setMaterielId(Integer.parseInt(job.get("MaterielModel").toString()));
						salesOrdersDetailedEntity.setOrderDetailedNumber(job.get("orderDetailedNumber").toString());
						if (job.get("MaterielModelDh") != null) {
							salesOrdersDetailedEntity.setExchangeMaterielId(Integer.parseInt(job.get("MaterielModelDh").toString()));
						}
						if(!"".equals(job.getString("AsphaltContent").toString())) {
							salesOrdersDetailedEntity.setAsphaltContent(Double.parseDouble(job.getString("AsphaltContent").toString()));
						}
						if(!"".equals(job.getString("AsphaltContentExchange").toString())) {
							salesOrdersDetailedEntity.setAsphaltContentExchange(Double.parseDouble(job.getString("AsphaltContentExchange").toString()));
						}
						salesOrdersDetailedEntity.setListModel(job.get("ListModel").toString());
						salesOrdersDetailedEntity.setUnitPrice(job.get("UnitPrice").toString());
						salesOrdersDetailedEntity.setExpectedDeliveryDate(job.get("ExpectedDeliveryDate").toString());
						salesOrdersDetailedEntity.setIsCancel(Integer.parseInt(job.get("IsCancel").toString()));
						salesOrdersDetailedEntity.setCancelPerson(job.get("CancelPerson").toString());
						salesOrdersDetailedEntity.setSaleNumber(Double.parseDouble(job.get("SaleNumber").toString()));
						salesOrdersDetailedEntity.setSalePrice(Double.parseDouble(job.get("SalePrice").toString()));
						salesOrdersDetailedEntity.setSaleMoney(Double.parseDouble(job.get("SaleMoney").toString()));
						salesOrdersDetailedEntity.setIsActualDelivery(Integer.parseInt(job.get("IsActualDelivery").toString()));
						salesOrdersDetailedEntity.setIsExchange(Integer.parseInt(job.get("IsExchange").toString()));
						if(job.get("IsExchange").toString().equals("0")) {
							salesOrdersDetailedEntity.setProportion(Double.parseDouble(job.get("Proportion").toString()));
							salesOrdersDetailedEntity.setExchangeWeight(Double.parseDouble(job.get("ExchangeWeight").toString()));
						}
						salesOrdersDetailedEntity.setTransportBalance(job.get("TransportBalance").toString());
						/*		if(job.get("TransportBalance").toString().equals("1")) {
					}else {*/
						//salesOrdersDetailedEntity.setStartAddress(job.get("StartAddress").toString());
						if(job.get("Transports") != null) {
							salesOrdersDetailedEntity.setTransports(job.get("Transports").toString());
						}
						salesOrdersDetailedEntity.setDistance(Double.parseDouble(job.get("Distance").toString()));
						if(!"".equals(job.getString("Mileage").toString())) {
							salesOrdersDetailedEntity.setMileage(Double.parseDouble(job.getString("Mileage").toString()));
						}
						//}
						salesOrdersDetailedEntity.setTaxRate(job.get("TaxRate").toString());
						salesOrdersDetailedEntity.setRemarks(job.get("Remarks").toString());								
						salesOrdersDetailedEntity.setCreater(param.get("Creater").toString());	
						SalesOrdersDetailedList.add(salesOrdersDetailedEntity);	
					}
				}
			}
			
			int res = 0;
			// 新增或赋值的场合
			if ("0".equals(flag) || "2".equals(flag) ) {
				res = salesOrdersService.addSalesOrdersInfo(salesOrdersInfoEntity, SalesOrdersDetailedList);
			} else {
				// 修改的场合
				res = salesOrdersService.updateSalesOrdersInfo(salesOrdersInfoEntity, SalesOrdersDetailedList);
			}
	
			if (res>0) {
				// 操作成功
				info.setCode("success");
				info.setMessage(PropertyUtil.getProperties("M0005"));
			} else {
				// 操作失败
				info.setCode("error");
				info.setMessage(PropertyUtil.getProperties("M0006"));
			}
		} else {
			// 合同编号重复
			info.setCode("error");
			info.setMessage("订单编号重复");
		}
		
		return info;
	}
	
	// 销售订单作废
	@RequestMapping("/updateOrderState.action")
	@ResponseBody
	public ResponseInfo updateOrderState(SalesOrdersInfoEntity salesOrdersInfoEntity) throws IOException{
		ResponseInfo info = new ResponseInfo();
		int res = salesOrdersService.updateOrderState(salesOrdersInfoEntity);
		if (res>0) {
			// 操作成功
			info.setCode("success");
			info.setMessage(PropertyUtil.getProperties("M0005"));
		} else {
			// 操作失败
			info.setCode("error");
			info.setMessage(PropertyUtil.getProperties("M0006"));
		}
		return info;
	}
	
	// 销售订单删除(删除所有)
	@RequestMapping("/deleteSalesOrdersInfo.action")
	@ResponseBody
	public ResponseInfo deleteSalesOrdersInfo(@RequestParam Map<String, Object> param) throws IOException{
		ResponseInfo info = new ResponseInfo();	
		String Id = param.get("id").toString();
		String Reviser = param.get("reviser").toString();
		int res = salesOrdersService.deleteSalesOrdersInfo(Reviser,Id);
		if (res>0) {
			// 操作成功
			info.setCode("success");
			info.setMessage(PropertyUtil.getProperties("M0005"));
		} else {
			// 操作失败
			info.setCode("error");
			info.setMessage(PropertyUtil.getProperties("M0006"));
		}
		return info;
	}
	// 销售订单目录tree
	@RequestMapping("/getContractTree.action")
	@ResponseBody
	public List<ContractTreeEntity> getContractTree(@RequestParam HashMap<String, Object> map){
		List<ContractTreeEntity> list = salesOrdersService.getContractTree(map);
		return list;
	}
	
	
	@RequestMapping("/getCustomertransportsById.action")
	@ResponseBody
	public DataTablesResponseInfo getCustomertransportsById(@RequestParam HashMap<String, Object> map){
		DataTablesResponseInfo info = salesOrdersService.getCustomertransportsById(map);
		return info;
	}
	
	@RequestMapping("/updateIsDelSalesOrdersDetailedByOrderDetailedNumber.action")
	@ResponseBody
	public ResponseInfo updateIsDelSalesOrdersDetailedByOrderDetailedNumber(@RequestParam HashMap<String, Object> map){
		ResponseInfo info = salesOrdersService.updateIsDelSalesOrdersDetailedByOrderDetailedNumber(map);
		return info;
	}
	
	// 导出
	@RequestMapping("/export.action")
	@ResponseBody
	public void export(HttpServletRequest request,HttpServletResponse response,@RequestParam HashMap<String, Object> map) throws IOException{
		String param = request.getParameter("param");
		Map<String,Object> paramMap= JSON.parseObject(param);
		salesOrdersService.export(request,response,paramMap);	
	}
}
