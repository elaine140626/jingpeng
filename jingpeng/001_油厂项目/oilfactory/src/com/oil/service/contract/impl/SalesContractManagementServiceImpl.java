package com.oil.service.contract.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.oil.dao.contract.SalesContractManagementDao;
import com.oil.dao.dispath.OutboundDao;
import com.oil.model.DataTablesResponseInfo;
import com.oil.model.ResponseInfo;
import com.oil.model.contract.ContractDetailedEntity;
import com.oil.model.contract.ContractIncomingDetailedEntity;
import com.oil.model.contract.ContractInfoEntity;
import com.oil.model.contract.ContractTreeEntity;
import com.oil.model.dispath.OutboundEntity;
import com.oil.model.export.ContractInfoExportEntity;
import com.oil.model.sales.SalesOrdersDetailedEntity;
import com.oil.service.contract.SalesContractManagementService;

@Service
@Transactional
public class SalesContractManagementServiceImpl implements SalesContractManagementService{
		
	@Autowired
	private SalesContractManagementDao salesContractManagementDao;
	
	@Autowired
	OutboundDao outboundDao;
	
	// 客户名称
	public List<Map<String,Object>> getCustomerName(){
		return salesContractManagementDao.getCustomerName();
	}
	
	// 销售员
	public List<Map<String,Object>> getSalesName(){
		return salesContractManagementDao.getSalesName();
	}
	
	// 物料信息取得
	public List<Map<String,Object>> getMaterielInfo(Map<String,Object> map){
		return salesContractManagementDao.getMaterielInfo(map);
	}
	
	// 数据字典取得
	public List<Map<String,Object>> getDataDictionary(String Type){
		return salesContractManagementDao.getDataDictionary(Type);
	}
		
	// 合同目录tree
	public List<ContractTreeEntity> getContractTree(Map<String, Object> map){
		return salesContractManagementDao.getContractTree(map);
	}

	// 合同管理表获取
	public List<ContractInfoEntity> getContractInfo(Map<String,Object> map){
		return salesContractManagementDao.getContractInfo(map);
	}
	
	// 合同明细表获取
	public List<ContractDetailedEntity> getContractDetailed(Map<String,Object> map){
		return salesContractManagementDao.getContractDetailed(map);
	}
	
	// 合同来料加工明细
	public List<ContractIncomingDetailedEntity> getContractIncomingDetailed(Map<String,Object> map){
		return salesContractManagementDao.getContractIncomingDetailed(map);
	}
	
	// 合同编号查重
	public String checkContractNumber(String ContractNumber,String id) {
		return salesContractManagementDao.checkContractNumber(ContractNumber,id);
	};
	
	// 新增合同
	public int addContractInfo(ContractInfoEntity contractInfoEntity,List<ContractDetailedEntity> ContractDetailedList
			,List<ContractIncomingDetailedEntity> ContractIncomingDetailedList) {
		int res = 0;
		// 合同信息表插入
		res += salesContractManagementDao.insertContractInfo(contractInfoEntity);
		// 取得刚插入合同信息表的id
		int id = salesContractManagementDao.getContractInfoId(contractInfoEntity.getCreater());
		// 合同明细表插入
		if (ContractDetailedList != null && ContractDetailedList.size()>0) {
			for (int i = 0; i < ContractDetailedList.size(); i++) {
				ContractDetailedList.get(i).setContractId(id);
				res += salesContractManagementDao.insertContractDetailed(ContractDetailedList.get(i));
			}
		}
		// 来料加工的情况下
		if (0 == contractInfoEntity.getIsIncoming()) {
			// 合同表来料加工明细插入
			if(ContractIncomingDetailedList != null && ContractIncomingDetailedList.size()>0) {
				for (int i = 0; i < ContractIncomingDetailedList.size(); i++) {
					ContractIncomingDetailedList.get(i).setContractId(id);
					res += salesContractManagementDao.insertContractIncomingDetailed(ContractIncomingDetailedList.get(i));
				}
			}
		}	
		return res;	
	}
	
	// 修改合同
	public int updateContractInfo(ContractInfoEntity contractInfoEntity,List<ContractDetailedEntity> ContractDetailedList
			,List<ContractIncomingDetailedEntity> ContractIncomingDetailedList) {
		int res = 0;
		// 合同表更新
		if(Integer.parseInt(contractInfoEntity.getContractState()) != 3) {
			contractInfoEntity.setCancellationCause("");
		}
		res += salesContractManagementDao.updateContractInfo(contractInfoEntity);		
		// 合同明细删除
		res += salesContractManagementDao.deleteContractDetailed(Integer.toString(contractInfoEntity.getId()));
		// 合同表来料加工明细删除
		res += salesContractManagementDao.deleteContractIncomingDetailed(Integer.toString(contractInfoEntity.getId()));
		// 合同明细表插入
		if (ContractDetailedList != null && ContractDetailedList.size()>0) {
			for (int i = 0; i < ContractDetailedList.size(); i++) {
				ContractDetailedList.get(i).setContractId(contractInfoEntity.getId());
				res += salesContractManagementDao.insertContractDetailed(ContractDetailedList.get(i));
			}
		}
		// 来料加工的情况下
		if (0 == contractInfoEntity.getIsIncoming()) {
			// 合同表来料加工明细插入
			if(ContractIncomingDetailedList != null && ContractIncomingDetailedList.size()>0) {
				for (int i = 0; i < ContractIncomingDetailedList.size(); i++) {
					ContractIncomingDetailedList.get(i).setContractId(contractInfoEntity.getId());
					res += salesContractManagementDao.insertContractIncomingDetailed(ContractIncomingDetailedList.get(i));
				}
			}
		}			
		return res;	
	}
	
	// 合同作废
	public int updateContractState(ContractInfoEntity contractInfoEntity) {
		return salesContractManagementDao.updateContractState(contractInfoEntity);
	}
	
	// 合同删除
	public int deleteContractInfo(String Reviser,String Id) {
		int res = 0;
		// 合同删除
		res += salesContractManagementDao.updateIsDelContractInfo(Reviser,Id);
		// 合同明细删除
		res += salesContractManagementDao.updateIsDelContractDetailedByContractId(Reviser,Id);
		// 合同表来料加工明细删除
		res += salesContractManagementDao.updateIsDelContractIncomingDetailedByContractId(Reviser,Id);
		return res;
	}

	// 合同导出
	public List<ContractInfoExportEntity> getContractInfoExportEntity(HashMap<String, Object> map) {
		return salesContractManagementDao.getContractInfoExportEntity(map);
	}

	//查询合同下的销售订单信息(ygt)
	public List<Map<String, Object>> getOrderAndDispatchInfo(Map<String,Object> map) {
		return salesContractManagementDao.getOrderAndDispatchInfo(map);
	}

	//查询销售订单明细(ygt)
	public List<SalesOrdersDetailedEntity> getOrdersDetailed(Map<String, Object> map) {
		return salesContractManagementDao.getOrdersDetailed(map);
	}

	//获取调度出库单信息(ygt)
	public DataTablesResponseInfo getExportMeasureInfo(Map<String, Object> map) {
		DataTablesResponseInfo info = new DataTablesResponseInfo();
		List<OutboundEntity> dataList = salesContractManagementDao.getExportMeasureInfo(map);
		// 查询关联的空发出库单
		for (int i = 0; i < dataList.size(); i++) {
			List<Map<String, Object>> list = outboundDao.queryEmptyOutBoundById(dataList.get(i).getSerialId());
			if (list != null && list.size() > 0) {
				StringBuilder emptyIds = new StringBuilder();
				for (int j = 0; j < list.size(); j++) {
					emptyIds.append(list.get(j).get("EmptyCarId")).append(",");
				}
				dataList.get(i).setIsEmpty("Y");
				dataList.get(i).setEmptyList(emptyIds.toString().substring(0, emptyIds.length()-1));
			} else {
				dataList.get(i).setIsEmpty("N");
			}			
		}
		if (dataList == null || dataList.size() < 0) {
			dataList = new ArrayList<OutboundEntity>();
		}
		info.setData(dataList);
		return info;
	}

	//添加上传文件
	public ResponseInfo adduploadfile(Map<String, Object> map) {
		ResponseInfo info = new ResponseInfo();
		int result = salesContractManagementDao.adduploadfile(map);
			if(result > 0) {
				info.setMessage("操作成功");
				info.setCode("success");
			}else {
				info.setMessage("操作失败");
				info.setCode("error");
			}
		return info;
	}

	//查询文件上传信息
	public DataTablesResponseInfo getUploadfile(Map<String, Object> map, HttpServletRequest request) {
		DataTablesResponseInfo info = new DataTablesResponseInfo();
		List<Map<String,Object>> fileList = salesContractManagementDao.getUploadfile(map);
		List<Map<String,Object>> list_remove = new ArrayList<Map<String,Object>>();
		for (int i = 0; i < fileList.size(); i++) {
			String path = request.getSession().getServletContext().getRealPath("/upload")+"/"+fileList.get(i).get("fileName");
			File file=new File(path);
			 if (!file .exists()  && !file .isDirectory()) {      
			     //不存在
				 list_remove.add(fileList.get(i)); //把要移除的统一放在一个集合	
			 }
		}
		if(list_remove.size()>0) {
			fileList.removeAll(list_remove);
			for (int i = 0; i < list_remove.size(); i++) {
				this.delUploadfile(list_remove.get(i),request);
			}
		}
		info.setData(fileList);
		return info;
	}

	//删除上传文件信息
	public ResponseInfo delUploadfile(Map<String, Object> map, HttpServletRequest request) {
		ResponseInfo info = new ResponseInfo();
		int result = salesContractManagementDao.delUploadfile(map);
		if(result > 0) {
			if(isHaveUpload(map).getCode().equals("success")) {
				String path = request.getSession().getServletContext().getRealPath("/upload")+"/"+map.get("fileName");  
				FileUtils.deleteQuietly(new File(path));
			}
			info.setMessage("操作成功");
			info.setCode("success");
		} else {
			info.setMessage("操作失败");
			info.setCode("error");
		}
		return info;
	}

	//判断是否存在该文件
	public ResponseInfo isHaveUpload(Map<String, Object> map) {
		ResponseInfo info = new ResponseInfo();
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		String path = request.getSession().getServletContext().getRealPath("/upload")+"/"+map.get("fileName");
			File file=new File(path);
			 if (!file .exists()  && !file .isDirectory()) { 			 
			     //不存在
				 info.setMessage("操作失败,文件已被删除");
				 info.setCode("error");
			 } else {				 
		 		 info.setMessage("操作成功");
				 info.setCode("success");
			 }
			 
		return info;
	}
	
}
