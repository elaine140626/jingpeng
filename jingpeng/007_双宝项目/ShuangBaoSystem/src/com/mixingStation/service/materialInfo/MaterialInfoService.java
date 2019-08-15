package com.mixingStation.service.materialInfo;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.mixingStation.model.DataTablesResponseInfo;
import com.mixingStation.model.materialInfo.MaterialInfo;



public interface MaterialInfoService {
	// 获取物料名称信息
		List<MaterialInfo> getMaterialNameList(Map<String, Object> map);

		// 获取物料型号信息
		List<MaterialInfo> getMaterialModelList(Map<String, Object> map);

		// 获取原材料list
		DataTablesResponseInfo getMaterialList(Map<String, Object> map);
		// 修改
		int updateMaterialInfo(HttpServletRequest request, Map<String, Object> map);
		
		// 新增
		int insertMaterialInfo(HttpServletRequest request,Map<String, Object> map);
	//	
	//	
//		int updateMaterialInfoById(HttpServletRequest request,Map<String, Object> map);
		//删除
		int delMaterial(HttpServletRequest request, Map<String, Object> map);

		// 获取物料单位
		List<MaterialInfo> getMeasureUnitList(Map<String, Object> map);

		List<Map<String, Object>> getMaterialModelByName(HttpServletRequest request, Map<String, Object> map);

		List<MaterialInfo> queryProductId(HttpServletRequest request, Map<String, Object> map);
}
