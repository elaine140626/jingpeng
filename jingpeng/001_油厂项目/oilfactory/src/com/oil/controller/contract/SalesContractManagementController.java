package com.oil.controller.contract;

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

import com.oil.model.DataTablesResponseInfo;
import com.oil.model.ResponseInfo;
import com.oil.model.contract.ContractDetailedEntity;
import com.oil.model.contract.ContractIncomingDetailedEntity;
import com.oil.model.contract.ContractInfoEntity;
import com.oil.model.contract.ContractTreeEntity;
import com.oil.model.export.ContractInfoExportEntity;
import com.oil.model.sales.SalesOrdersDetailedEntity;
import com.oil.service.contract.SalesContractManagementService;
import com.oil.util.ExportUtil;
import com.oil.util.PropertyUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Controller
@RequestMapping("/salesContractManagement")
public class SalesContractManagementController {
	@Autowired
	private SalesContractManagementService salesContractManagementService;
	
	// 客户名称 
	@RequestMapping("/getCustomerName.action")
	@ResponseBody
	public List<Map<String,Object>> getCustomerName(){
		List<Map<String,Object>> list = salesContractManagementService.getCustomerName();
		return list;
	}
	
	// 销售员名称
	@RequestMapping("/getSalesName.action")
	@ResponseBody
	public List<Map<String,Object>> getSalesName(){
		List<Map<String,Object>> list = salesContractManagementService.getSalesName();
		return list;
	}
	
	// 物料信息取得
	@RequestMapping("/getMaterielInfo.action")
	@ResponseBody
	public List<Map<String,Object>> getMaterielInfo(HttpServletRequest request, @RequestParam Map<String,Object> map){
		List<Map<String,Object>> list = salesContractManagementService.getMaterielInfo(map);
		return list;
	}
	
	// 数据字典取得
	@RequestMapping("/getDataDictionary.action")
	@ResponseBody
	public List<Map<String,Object>> getDataDictionary(@RequestParam HashMap<String, Object> map){
		String Type = map.get("Type").toString();
		List<Map<String,Object>> list = salesContractManagementService.getDataDictionary(Type);
		return list;
	}
	
	// 合同目录tree
	@RequestMapping("/getContractTree.action")
	@ResponseBody
	public List<ContractTreeEntity> getContractTree(@RequestParam HashMap<String, Object> map){
		List<ContractTreeEntity> list = salesContractManagementService.getContractTree(map);
		return list;
	}
	
	// 合同管理表获取datatable
	@RequestMapping("/getContractInfo.action")
	@ResponseBody
	public DataTablesResponseInfo getContractInfo(HttpServletRequest request,@RequestParam HashMap<String, Object> map){
		DataTablesResponseInfo dInfo = new DataTablesResponseInfo();
		if (map.get("ContractState")!=null) {
			String ContractState = map.get("ContractState").toString();
			String[] param = {};
			if(ContractState != null && !("").equals(ContractState)) {
				param = ContractState.split(",");
				map.put("ContractState", param);
			}
		}
		List<ContractInfoEntity> list = salesContractManagementService.getContractInfo(map);
		dInfo.setData(list);
		return dInfo;
	}
	
	// 合同信息单条获取
	@RequestMapping("/getContractInfoById.action")
	@ResponseBody
	public Map<String,Object> getContractInfoById(HttpServletRequest request,@RequestParam HashMap<String, Object> map){
		Map<String,Object> resultMap = new HashMap<String,Object>();
		// 合同管理表获取
		List<ContractInfoEntity> ContractInfoList = salesContractManagementService.getContractInfo(map);
		// 合同明细表获取
		List<ContractDetailedEntity> ContractDetailedList = salesContractManagementService.getContractDetailed(map);
		// 合同来料加工明细
		List<ContractIncomingDetailedEntity> ContractIncomingDetailedList = salesContractManagementService.getContractIncomingDetailed(map);
		
		resultMap.put("ContractInfoList", ContractInfoList);
		resultMap.put("ContractDetailedList", ContractDetailedList);
		resultMap.put("ContractIncomingDetailedList", ContractIncomingDetailedList);
		return resultMap;
	}
	
	// 合同信息新增
	@RequestMapping("/addContractInfo.action")
	@ResponseBody
	public ResponseInfo addContractInfo(@RequestParam Map<String, Object> param) throws IOException{		
		ResponseInfo info = new ResponseInfo();
		// 0:新增  1:修改  2:复制
		String flag = param.get("flag").toString();	 
		String count = "0";
		// 新增或复制的场合
		if ( "0".equals(flag) || "2".equals(flag) ) {		
			count = salesContractManagementService.checkContractNumber(param.get("ContractNumber").toString(),"");
		} else {
			// 修改的场合
			count = salesContractManagementService.checkContractNumber(param.get("ContractNumber").toString(),param.get("id").toString());
		}		
		
		if ("0".equals(count)) {
			// 合同信息表
			ContractInfoEntity contractInfoEntity = new ContractInfoEntity();
			// 修改的场合
			if ("1".equals(flag)) {
				contractInfoEntity.setId(Integer.parseInt(param.get("id").toString()));
			}
			contractInfoEntity.setContractNumber(param.get("ContractNumber").toString());
			contractInfoEntity.setContractName(param.get("ContractName").toString());
			contractInfoEntity.setCustomerId(Integer.parseInt(param.get("CustomerId").toString()));
			contractInfoEntity.setSaleNumber(Double.parseDouble(param.get("SaleNumber").toString()));
			contractInfoEntity.setUserInfoId(Integer.parseInt(param.get("UserInfoId").toString()));
			contractInfoEntity.setContractState(param.get("ContractState").toString());
			contractInfoEntity.setSalesCompanyId(Integer.parseInt(param.get("SalesCompanyId").toString()));
			contractInfoEntity.setContractDate(param.get("ContractDate").toString());
			contractInfoEntity.setRemarks(param.get("remarks").toString());
			contractInfoEntity.setContractRoute(param.get("ContractRoute").toString());
			contractInfoEntity.setIsIncoming(Integer.parseInt(param.get("IsIncoming").toString()));
			contractInfoEntity.setIsApproval(Integer.parseInt(param.get("IsApproval").toString()));
			contractInfoEntity.setIsRelation(Integer.parseInt(param.get("IsRelation").toString()));
			contractInfoEntity.setRelationCompanyId(param.get("RelationCompanyId").toString() == "" ? 0 : Integer.parseInt(param.get("RelationCompanyId").toString()));
			contractInfoEntity.setIsModify(Integer.parseInt(param.get("IsModify").toString()));
			contractInfoEntity.setCreater(param.get("Creater").toString());

			// 合同明细表
			// 首先把字符串转成 JSONArray  对象
			JSONArray jsonArray=JSONArray.fromObject(param.get("ContractDetailedList"));
			List<ContractDetailedEntity> ContractDetailedList = new ArrayList<ContractDetailedEntity>();
			if(jsonArray.size()>0){
				for(int i=0;i<jsonArray.size();i++){
					ContractDetailedEntity contractDetailedEntity = new ContractDetailedEntity();
					// 遍历 jsonarray 数组，把每一个对象转成 json 对象
					JSONObject job = jsonArray.getJSONObject(i);
					int sum = 0;
					if (!"".equals(job.get("MaterielModel").toString())) {
						contractDetailedEntity.setMaterielId(Integer.parseInt(job.get("MaterielModel").toString()));
						sum++;
					}
					if (!"".equals(job.get("UnitPrice").toString())) {
						contractDetailedEntity.setUnitPrice(Double.parseDouble(job.get("UnitPrice").toString()));
						sum++;
					}
					contractDetailedEntity.setSaleNumber(Double.parseDouble(job.get("SaleNumber").toString()));
					if (!"".equals(job.get("TaxRate").toString())) {
						contractDetailedEntity.setTaxRate(job.get("TaxRate").toString());
						sum++;
					}
					if (!"".equals(job.get("Money").toString())) {
						contractDetailedEntity.setMoney(Double.parseDouble(job.get("Money").toString()));
						sum++;
					}
					if (!"".equals(job.get("IsModifyPrice").toString())) {
						contractDetailedEntity.setIsModifyPrice(Integer.parseInt(job.getString("IsModifyPrice").toString()));
						sum++;
					}
					if (!"".equals(job.get("Remarks").toString())) {
						contractDetailedEntity.setRemarks(job.get("Remarks").toString());
						sum++;
					}				
 					contractDetailedEntity.setCreater(param.get("Creater").toString());
 					if (sum!=0) {
 						ContractDetailedList.add(contractDetailedEntity);	
					}						
				}
			}
			
			// 合同表来料加工明细
			// 首先把字符串转成 JSONArray  对象
			JSONArray jsonArray2=JSONArray.fromObject(param.get("ContractIncomingDetailedList"));
			List<ContractIncomingDetailedEntity> ContractIncomingDetailedList = new ArrayList<ContractIncomingDetailedEntity>();
			if(jsonArray2.size()>0){
				for(int i=1;i<jsonArray2.size();i++){
					ContractIncomingDetailedEntity contractIncomingDetailedEntity = new ContractIncomingDetailedEntity();
					// 遍历 jsonarray 数组，把每一个对象转成 json 对象
					JSONObject job = jsonArray2.getJSONObject(i);
					int sum = 0;
					if (!"".equals(job.get("MaterielModel").toString())) {
						contractIncomingDetailedEntity.setMaterielId(Integer.parseInt(job.get("MaterielModel").toString()));
						sum++;
					}	
					if (!"".equals(job.get("SettlementMethod").toString())) {
						contractIncomingDetailedEntity.setSettlementMethod(job.get("SettlementMethod").toString());
						sum++;
					}	
					if (!"".equals(job.get("SettlementMethodRemarks").toString())) {
						contractIncomingDetailedEntity.setSettlementMethodRemarks(job.get("SettlementMethodRemarks").toString());
						sum++;
					}	
					if (!"".equals(job.get("CargoBalance").toString())) {
						contractIncomingDetailedEntity.setCargoBalance(job.get("CargoBalance").toString());
						sum++;
					}	
					if (!"".equals(job.get("CargoRemarks").toString())) {
						contractIncomingDetailedEntity.setCargoRemarks(job.get("CargoRemarks").toString());
						sum++;
					}
					if (!"".equals(job.get("TransportBalance").toString())) {
						contractIncomingDetailedEntity.setTransportBalance(job.get("TransportBalance").toString());
						sum++;
					}
					if (!"".equals(job.get("TransportRemarks").toString())) {
						contractIncomingDetailedEntity.setTransportRemarks(job.get("TransportRemarks").toString());
						sum++;
					}										
					contractIncomingDetailedEntity.setCreater(param.get("Creater").toString());					
					if (sum!=0) {
						ContractIncomingDetailedList.add(contractIncomingDetailedEntity);	
					}	
				}
			}
			
			int res = 0;
			// 新增或赋值的场合
			if ("0".equals(flag) || "2".equals(flag) ) {
				res = salesContractManagementService.addContractInfo(contractInfoEntity, ContractDetailedList, ContractIncomingDetailedList);
			} else {
				// 修改的场合
				res = salesContractManagementService.updateContractInfo(contractInfoEntity, ContractDetailedList, ContractIncomingDetailedList);
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
			info.setMessage("合同编号重复");
		}
		
		return info;
	}
	
	// 合同作废
	@RequestMapping("/updateContractState.action")
	@ResponseBody
	public ResponseInfo updateContractState(ContractInfoEntity contractInfoEntity) throws IOException{
		ResponseInfo info = new ResponseInfo();
		int res = salesContractManagementService.updateContractState(contractInfoEntity);
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
	
	// 合同删除(删除所有)
	@RequestMapping("/deleteContractInfo.action")
	@ResponseBody
	public ResponseInfo deleteContractInfo(@RequestParam Map<String, Object> param) throws IOException{
		ResponseInfo info = new ResponseInfo();	
		String Id = param.get("id").toString();
		String Reviser = param.get("reviser").toString();
		int res = salesContractManagementService.deleteContractInfo(Reviser,Id);
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
	
	// 导出
	@RequestMapping("/exportExcel.action")
	@ResponseBody
	public void export(HttpServletRequest request, HttpServletResponse response, @RequestParam String userInfoId, @RequestParam String contractState){
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("UserInfoId", userInfoId);
		if (!"".equals(contractState)) {
			map.put("ContractState", contractState.split(","));
		}
		List<ContractInfoExportEntity> list = salesContractManagementService.getContractInfoExportEntity(map);
		ExportUtil.exportExcel(request, response, "导出", "表头", "sheet页1", ContractInfoExportEntity.class, list);
	}
		
	//查询合同下的销售订单信息(ygt)
	@RequestMapping("/getOrderAndDispatchInfo.action")
	@ResponseBody
	public List<Map<String,Object>> getOrderAndDispatchInfo(@RequestParam Map<String,Object> map){
		return salesContractManagementService.getOrderAndDispatchInfo(map);
	}
	
	//查询销售订单明细(ygt)
	@RequestMapping("/getOrdersDetailed.action")
	@ResponseBody
	public List<SalesOrdersDetailedEntity> getOrdersDetailed(@RequestParam Map<String,Object> map){
		return salesContractManagementService.getOrdersDetailed(map);
	}
	
	//获取调度出库单信息(ygt)
	@RequestMapping("/getExportMeasureInfo.action")
	@ResponseBody
	public DataTablesResponseInfo getExportMeasureInfo(@RequestParam Map<String, Object> map){
		return salesContractManagementService.getExportMeasureInfo(map);	
	}
	
	//添加上传文件
	@RequestMapping("/adduploadfile.action")
	@ResponseBody
	public ResponseInfo adduploadfile(HttpServletRequest request, @RequestParam Map<String,Object> map)  {
		return salesContractManagementService.adduploadfile(map);
	}
	
	//查询上传文件
	@RequestMapping("/getUploadfile.action")
	@ResponseBody
	public DataTablesResponseInfo getUploadfile(HttpServletRequest request, @RequestParam Map<String,Object> map)  {
		return salesContractManagementService.getUploadfile(map,request);
	}
	
	//删除文件
	@RequestMapping("/delUploadfile.action")
	@ResponseBody
	public ResponseInfo delUploadfile(HttpServletRequest request, @RequestParam Map<String,Object> map)  {
		return salesContractManagementService.delUploadfile(map,request);
	}
	
	//是否存在该文件
	@RequestMapping("/isHaveUpload.action")
	@ResponseBody
	public ResponseInfo isHaveUpload(HttpServletRequest request, @RequestParam Map<String,Object> map)  {
		return salesContractManagementService.isHaveUpload(map);
	}
}
