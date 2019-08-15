package com.mix.service.cement.impl;

import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mix.dao.cement.CementMaterialDao;
import com.mix.model.MaterName_Info;
import com.mix.model.V_MaterialInfo;
import com.mix.service.cement.CementMaterialService;
/**
 * 
 * @Title 水泥物料业务实现
 * @author ygt
 * @date 2018年9月30日
 */
@Service
@Transactional
public class CementMaterialServiceImpl implements CementMaterialService {

	@Autowired
	private CementMaterialDao cementMaterialDao;
	
	@Override
	public List<V_MaterialInfo> getMaterialInfo(V_MaterialInfo v_MaterialInfo) {
		return cementMaterialDao.getMaterialInfo(v_MaterialInfo);
	}

	@Override
	public List<V_MaterialInfo> getMaterialById(V_MaterialInfo v_MaterialInfo) {
		return cementMaterialDao.getMaterialById(v_MaterialInfo);
	}

	@Override
	public List<V_MaterialInfo> getMaterialbyNameOrModel(V_MaterialInfo v_MaterialInfo) {
		return cementMaterialDao.getMaterialbyNameOrModel(v_MaterialInfo);
	}

	@Override
	public int updateMaterial(V_MaterialInfo v_MaterialInfo) {
		List<MaterName_Info> nameList = cementMaterialDao.getMaterName(v_MaterialInfo);
		if(nameList != null && nameList.size() > 0) {
			v_MaterialInfo.setI_mateName_Id(nameList.get(0).getI_id());
		} else {
			int i_mateName_Id = cementMaterialDao.addMaterName(v_MaterialInfo);
			v_MaterialInfo.setI_mateName_Id(i_mateName_Id);
		}
		List<MaterName_Info> modelList = cementMaterialDao.getMaterModel(v_MaterialInfo);
		if(modelList != null && modelList.size() > 0) {
			v_MaterialInfo.setI_mateModel_Id(modelList.get(0).getI_id());
		} else {
			int i_mateModel_Id = cementMaterialDao.addMaterModel(v_MaterialInfo);
			v_MaterialInfo.setI_mateModel_Id(i_mateModel_Id);
		}
		cementMaterialDao.updateMaterial(v_MaterialInfo);
		return 0;
	}

	@Override
	public List<V_MaterialInfo> getMaterialbyCode(V_MaterialInfo v_MaterialInfo) {
		return cementMaterialDao.getMaterialbyCode(v_MaterialInfo);
	}

	@Override
	public List<Map<String, Object>> iscementMaterialMayMaterialDel1(Map<String, Object> map) {
		return cementMaterialDao.iscementMaterialMayMaterialDel1(map);
	}
	@Override
	public List<Map<String, Object>> iscementMaterialMayMaterialDel2(Map<String, Object> map) {
		return cementMaterialDao.iscementMaterialMayMaterialDel2(map);
	}
	@Override
	public int deletMaterial(V_MaterialInfo v_MaterialInfo) {
		return cementMaterialDao.deletMaterial(v_MaterialInfo);
	}

	@Override
	public void addMater(V_MaterialInfo v_MaterialInfo) {
		List<MaterName_Info> nameList = cementMaterialDao.getMaterName(v_MaterialInfo);
		if(nameList != null && nameList.size() > 0) {
			v_MaterialInfo.setI_mateName_Id(nameList.get(0).getI_id());
		} else {
			int i_mateName_Id = cementMaterialDao.addMaterName(v_MaterialInfo);
			v_MaterialInfo.setI_mateName_Id(i_mateName_Id);
		}
		List<MaterName_Info> modelList = cementMaterialDao.getMaterModel(v_MaterialInfo);
		if(modelList != null && modelList.size() > 0) {
			v_MaterialInfo.setI_mateModel_Id(modelList.get(0).getI_id());
		} else {
			int i_mateModel_Id = cementMaterialDao.addMaterModel(v_MaterialInfo);
			v_MaterialInfo.setI_mateModel_Id(i_mateModel_Id);
		}
		cementMaterialDao.addMaterial(v_MaterialInfo);
	}

}