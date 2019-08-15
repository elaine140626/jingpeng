package com.mixingStation.service.materialInfo.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.Printer;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mchange.v1.lang.BooleanUtils;
import com.mixingStation.dao.materialInfo.MaterialInfoDao;
import com.mixingStation.model.DataTablesResponseInfo;
import com.mixingStation.model.materialInfo.MaterialInfo;
import com.mixingStation.model.userInfo.UserInfo;
import com.mixingStation.service.materialInfo.MaterialInfoService;

@Service
@Transactional
public class MaterialInfoServiceImpl implements MaterialInfoService {

	@Autowired
	private MaterialInfoDao materialInfoDao;
	
	@Override
	// 获取物料名称信息
	public List<MaterialInfo> getMaterialNameList(Map<String, Object> map) {
		return materialInfoDao.getMaterialNameList(map);
	}
	
	@Override
	// 获取物料名称信息
	public List<MaterialInfo> getMaterialModelList(Map<String, Object> map){
		return materialInfoDao.getMaterialModelList(map);	
	}
	
	// 获取物料单位信息
	@Override
	public List<MaterialInfo> getMeasureUnitList(Map<String, Object> map) {
		return materialInfoDao.getMeasureUnitList(map);	
	}

	@Override
	// 获取物料list
	public DataTablesResponseInfo getMaterialList(Map<String, Object> map) {
		DataTablesResponseInfo info = new DataTablesResponseInfo();
		List<MaterialInfo> materialList = materialInfoDao.getMaterialList(map);
		info.setData(materialList);
		return info;
	}
	// 修改
	@Override
	public int updateMaterialInfo(HttpServletRequest request, Map<String, Object> map) {
		UserInfo userInfo = (UserInfo) request.getSession().getAttribute("mixUser");
		if(userInfo != null) {
//			map.put("operator", userInfo.getName());
		}
//		Date now = new Date();
//		map.put("createDate", now);
		map.put("materialCode", map.get("materialCode").toString());
		if(map.get("density").toString().equals("")) {
			map.put("density",null);
		}
		if(map.get("measureUnit").toString().equals("")) {
			map.put("measureUnit",null);
		}
		//获取物料名称
		Map<String, Object> materialName = materialInfoDao.getMaterialName(map);
		if(materialName == null) {
			materialInfoDao.insertMaterialName(map);
		}
		else {
			map.put("mateNameId",materialName.get("Id"));
		}
		//获取物料型号
		Map<String, Object> materialModel = materialInfoDao.getMaterialModel(map);
		if(materialModel == null) {//数据库没有物料名称，新增新的并返回物料型号id
			materialInfoDao.insertMaterialModel(map);
			return materialInfoDao.updateMaterialInfo(map);
		} else {//数据库存在物料型号id
			int modelId = 0;
			map.put("mateModelId",Integer.parseInt(materialModel.get("Id").toString()));
			//获取当前id的物料信息
			List<MaterialInfo> materialMsgList = materialInfoDao.getMaterialList(map);
			if(materialMsgList != null && materialMsgList.size()>0) {
				//获取mateModelId；
				modelId = materialMsgList.get(0).getMateModelId();
			}
			if(modelId == Integer.parseInt(map.get("mateModelId").toString())) {
				return materialInfoDao.updateMaterialInfo(map);
			}else {
				//获取当前用户是否存在该物料名称
				Map<String, Object> isOrgMaterial = materialInfoDao.getIsOrgMaterial(map);
				if(isOrgMaterial != null) {
					return 3;//规格型号相同
				}else {
					//没有，可以更新数据
					return materialInfoDao.updateMaterialInfo(map);
				}
			}
		}	
	}

	@Override
	// 新增
	public int insertMaterialInfo(HttpServletRequest request,Map<String, Object> map) {
		UserInfo userInfo = (UserInfo) request.getSession().getAttribute("mixUser");
		if (userInfo != null) {
			map.put("operator", userInfo.getName());
		}
		
		//获取物料编码
		Map<String, Object> materialCode = materialInfoDao.getMaterialCode(map);
		//物料编码为空，可以新增
		if(materialCode == null) {
			Date now = new Date();
			map.put("createDate", now);
			if(map.get("density").toString().equals("")) {
				map.put("density",null);
			}
			if(map.get("measureUnit").toString().equals("")) {
				map.put("measureUnit",null);
			}
			//获取物料名称
			Map<String, Object> materialName = materialInfoDao.getMaterialName(map);
			if(materialName == null) {//数据库没有物料名称，新增新的并返回物料名称id
				materialInfoDao.insertMaterialName(map);
			}
			else {
				//数据库存在物料型号id
				map.put("mateNameId",materialName.get("Id"));
			}
			//获取物料型号
			Map<String, Object> materialModel = materialInfoDao.getMaterialModel(map);
			if(materialModel == null) {//数据库没有物料名称，新增新的并返回物料型号id
				materialInfoDao.insertMaterialModel(map);
				return materialInfoDao.insertMaterialInfo(map);
			}else {
				//数据库存在物料名称id
				map.put("mateModelId",materialModel.get("Id"));
				//获取当前用户是否存在该物料名称
				Map<String, Object> isOrgMaterial = materialInfoDao.getIsOrgMaterial(map);
				if(isOrgMaterial != null) {
					//物料型号相同
					return 3;//规格型号相同
				}else {
					//没有，插入数据
					return materialInfoDao.insertMaterialInfo(map);
					
				}
			}
		}else {//物料编码重复
			return 2;
		}
		
	}

	@Override
	//删除
	public int delMaterial(HttpServletRequest request, Map<String, Object> map) {
		UserInfo userInfo = (UserInfo) request.getSession().getAttribute("user");
		if(userInfo != null) {
//			map.put("operator", userInfo.getId());
		}
		
		return materialInfoDao.delMaterial(map);
	}

	@Override
	public List<Map<String, Object>> getMaterialModelByName(HttpServletRequest request, Map<String, Object> map) {
		return materialInfoDao.getMaterialModelByName(map);
	}

	@Override
	public List<MaterialInfo> queryProductId(HttpServletRequest request, Map<String, Object> map) {
		return materialInfoDao.queryProductId(map);
	}

	


}
