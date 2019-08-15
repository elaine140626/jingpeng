package com.oil.dao.contract;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.oil.model.contract.ContractDetailedEntity;
import com.oil.model.contract.ContractIncomingDetailedEntity;
import com.oil.model.contract.ContractInfoEntity;
import com.oil.model.contract.ContractTreeEntity;
import com.oil.model.dispath.OutboundEntity;
import com.oil.model.export.ContractInfoExportEntity;
import com.oil.model.sales.SalesOrdersDetailedEntity;

public interface SalesContractManagementDao {
	// 客户名称
	List<Map<String,Object>> getCustomerName();
	
	// 销售员
	List<Map<String,Object>> getSalesName();
	
	// 物料信息取得
	List<Map<String,Object>> getMaterielInfo(Map<String,Object> map);
	
	// 数据字典取得
	List<Map<String,Object>> getDataDictionary(@Param("Type") String Type);
	
	// 合同目录tree
	List<ContractTreeEntity> getContractTree(Map<String, Object> map);
	
	// 合同管理表获取
	List<ContractInfoEntity> getContractInfo(Map<String,Object> map);
	
	// 合同明细表获取
	List<ContractDetailedEntity> getContractDetailed(Map<String,Object> map);
	
	// 合同来料加工明细
	List<ContractIncomingDetailedEntity> getContractIncomingDetailed(Map<String,Object> map);
	
	// 合同编号查重
	String checkContractNumber(@Param("ContractNumber") String ContractNumber,@Param("id") String id);
	
	// 合同信息表插入
	int insertContractInfo(ContractInfoEntity contractInfoEntity);
	
	// 取得刚插入合同信息表的id
	int getContractInfoId(@Param("Creater") String Creater);
	
	// 合同明细表插入
	int insertContractDetailed(ContractDetailedEntity contractDetailedEntity);
	
	// 合同表来料加工明细插入
	int insertContractIncomingDetailed(ContractIncomingDetailedEntity contractIncomingDetailedEntity);
	
	// 合同表更新
	int updateContractInfo(ContractInfoEntity contractInfoEntity);
	
	// 合同明细更新删除
	int deleteContractDetailed(@Param("Id") String Id);
	
	// 合同来料加工明细更新删除 
	int deleteContractIncomingDetailed(@Param("Id") String Id);
	
	// 合同作废
	int updateContractState(ContractInfoEntity contractInfoEntity);
	
	// 合同删除
	int updateIsDelContractInfo(@Param("Reviser") String Reviser,@Param("Id") String Id);
	
	// 合同明细删除(删除所有)
	int updateIsDelContractDetailedByContractId(@Param("Reviser") String Reviser,@Param("Id") String Id);
	
	// 合同表来料加工明细删除(删除所有)
	int updateIsDelContractIncomingDetailedByContractId(@Param("Reviser") String Reviser,@Param("Id") String Id);

	List<ContractInfoExportEntity> getContractInfoExportEntity(Map<String, Object> map);
	
	//查询合同下的销售订单信息(ygt)
	List<Map<String,Object>> getOrderAndDispatchInfo(Map<String,Object> map);

	//查询销售订单明细(ygt)
	List<SalesOrdersDetailedEntity> getOrdersDetailed(Map<String,Object> map);
	
	//获取调度出库单信息(ygt)
	List<OutboundEntity> getExportMeasureInfo(Map<String,Object> map);
	
	//添加上传文件
	int adduploadfile(Map<String,Object> map);
	
	//查询上传文件信息
	List<Map<String,Object>> getUploadfile(Map<String,Object> map);
	
	//删除上传文件信息
	int delUploadfile(Map<String,Object> map);
}
