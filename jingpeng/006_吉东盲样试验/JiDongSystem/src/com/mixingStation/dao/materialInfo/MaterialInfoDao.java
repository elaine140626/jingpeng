package com.mixingStation.dao.materialInfo;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.mixingStation.model.materialInfo.MaterialInfo;
import com.mixingStation.model.userInfo.OrganizationInfo;



@Repository
public interface MaterialInfoDao {
	//物料list
	List<MaterialInfo> getMaterialList(Map<String, Object> map);
		
	//  获取物料名称信息
	List<MaterialInfo> getMaterialNameList(Map<String, Object> map);
	
	// 获取物料型号信息
	List<MaterialInfo> getMaterialModelList(Map<String, Object> map);

	
	//修改物料信息
	int updateMaterialInfo(Map<String, Object> map);
	
	// 获取已存在的物料名称
	Map<String, Object> getMaterialName(Map<String, Object> map);

	//插入新的物料名称
	int insertMaterialName(Map<String, Object> map);
	
	// 获取已存在的物料型号
	Map<String, Object> getMaterialModel(Map<String, Object> map);
	
	//插入新的物料型号
	int insertMaterialModel(Map<String, Object> map);
	
	// 新增物料信息
	int insertMaterialInfo(Map<String, Object> map);

	// 删除物料信息
	int delMaterial(Map<String, Object> map);

	//获取已存在的计量单位
	List<MaterialInfo> getMeasureUnitList(Map<String, Object> map);

	//获取物料编码
	Map<String, Object> getMaterialCode(Map<String, Object> map);
	
	//获取当前用户是否含有该物料型号
	Map<String, Object> getIsOrgMaterial(Map<String, Object> map);

	List<Map<String, Object>> getMaterialModelByName(Map<String, Object> map);

	List<MaterialInfo> queryProductId(Map<String, Object> map);
		
		
}
