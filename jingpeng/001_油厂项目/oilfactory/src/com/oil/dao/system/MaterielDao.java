package com.oil.dao.system;

import java.util.List;
import java.util.Map;

import com.oil.model.system.MaterielInfo;
import com.oil.model.system.WareHouseInfo;

public interface MaterielDao {
	//物料页查询所有
	List<MaterielInfo> getMateriel(Map<String, Object> map);
	//删除校验查询（合同明细）
	List<Map<String,Object>> getAllContractdetailed(Map<String,Object> map);
	//删除物料信息
	int delMaterielById(int id);
	//添加物料信息
	int addMateriel(MaterielInfo Materiel);	
	//修改物料信息
	int updateMateriel(MaterielInfo Materiel);
	//查询仓库名称
	List<WareHouseInfo> getWarehouseInfo(Map<String, Object> map);
	//根据物料编号,物料名称,物料型号去重
	List<Map<String,Object>> getAllMaterielNumber(Map<String,Object> map);	
}
