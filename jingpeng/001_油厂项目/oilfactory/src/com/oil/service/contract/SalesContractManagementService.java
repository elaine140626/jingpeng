package com.oil.service.contract;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.oil.model.DataTablesResponseInfo;
import com.oil.model.ResponseInfo;
import com.oil.model.contract.ContractDetailedEntity;
import com.oil.model.contract.ContractIncomingDetailedEntity;
import com.oil.model.contract.ContractInfoEntity;
import com.oil.model.contract.ContractTreeEntity;
import com.oil.model.export.ContractInfoExportEntity;
import com.oil.model.sales.SalesOrdersDetailedEntity;

public interface SalesContractManagementService {
	// 客户名称
	List<Map<String,Object>> getCustomerName();
	
	// 销售员
	List<Map<String,Object>> getSalesName();
	
	// 物料信息取得
	List<Map<String,Object>> getMaterielInfo(Map<String,Object> map);
	
	// 数据字典取得
	List<Map<String,Object>> getDataDictionary(String Type);
	
	// 合同目录tree
	List<ContractTreeEntity> getContractTree(Map<String, Object> map);
	
	// 合同管理表获取
	List<ContractInfoEntity> getContractInfo(Map<String,Object> map);
	
	// 合同明细表获取
	List<ContractDetailedEntity> getContractDetailed(Map<String,Object> map);
	
	// 合同来料加工明细
	List<ContractIncomingDetailedEntity> getContractIncomingDetailed(Map<String,Object> map);
	
	// 合同编号查重
	String checkContractNumber(String ContractNumber,String id);
	
	// 新增合同
	int addContractInfo(ContractInfoEntity contractInfoEntity,List<ContractDetailedEntity> ContractDetailedList
			,List<ContractIncomingDetailedEntity> ContractIncomingDetailedList);

	// 修改合同
	int updateContractInfo(ContractInfoEntity contractInfoEntity,List<ContractDetailedEntity> ContractDetailedList
			,List<ContractIncomingDetailedEntity> ContractIncomingDetailedList);
	
	// 合同作废
	int updateContractState(ContractInfoEntity contractInfoEntity);
	
	// 合同删除
	int deleteContractInfo(String Reviser,String Id);

	// 合同导出
	List<ContractInfoExportEntity> getContractInfoExportEntity(HashMap<String, Object> map);
	
	//查询合同下的销售订单信息(ygt)
	List<Map<String,Object>> getOrderAndDispatchInfo(Map<String,Object> map);
	
	//查询销售订单明细(ygt)
	List<SalesOrdersDetailedEntity> getOrdersDetailed(Map<String,Object> map);
	
	//获取调度出库单信息(ygt)
	DataTablesResponseInfo getExportMeasureInfo(Map<String,Object> map);
	
	//添加上传文件
	ResponseInfo adduploadfile(Map<String,Object> map);
	
	//查询文件上传信息
	DataTablesResponseInfo getUploadfile(Map<String,Object> map, HttpServletRequest request);

	//删除上传文件信息
	ResponseInfo delUploadfile(Map<String,Object> map, HttpServletRequest request);

	//判断是否存在该文件
	ResponseInfo isHaveUpload(Map<String,Object> map);
}
