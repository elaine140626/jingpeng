package com.mix.service.asphalt.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mix.dao.asphalt.MaterialDao;
import com.mix.model.MaterModel_Info;
import com.mix.model.MaterName_Info;
import com.mix.model.Material_Info;
import com.mix.model.V_MaterialInfo;
import com.mix.service.asphalt.MaterialService;
@Service
@Transactional
public class MaterialServiceImpl implements MaterialService {
	@Autowired
	private MaterialDao materialDao;

	public List<V_MaterialInfo> getMaterialInfo(V_MaterialInfo v_MaterialInfo)  {
			return materialDao.getMaterialInfo(v_MaterialInfo);
	}

	public List<V_MaterialInfo> getMaterialbyNameOrModel(V_MaterialInfo v_MaterialInfo)  {
			return materialDao.getMaterialbyNameOrModel(v_MaterialInfo);
	}
	
	public List<MaterModel_Info> getLastMaterModelId()  {
			return materialDao.getLastMaterModelId();
	}

	public List<MaterName_Info> getLastMaterNameId()  {
			return materialDao.getLastMaterNameId();
	}
	
	public List<MaterName_Info> getMaterName(V_MaterialInfo v_MaterialInfo)  {
			return materialDao.getMaterName(v_MaterialInfo);
	}
	
	public List<MaterName_Info> getMaterModel(V_MaterialInfo v_MaterialInfo)  {
			return materialDao.getMaterName(v_MaterialInfo);
	}

	public int addMaterName(V_MaterialInfo v_MaterialInfo)  {
			return materialDao.addMaterName(v_MaterialInfo);
	}

	public int addMaterModel(V_MaterialInfo v_MaterialInfo)  {
			return materialDao.addMaterModel(v_MaterialInfo);
	}

	public int updateMaterial(V_MaterialInfo v_MaterialInfo)  {
			List<MaterName_Info> nameList = materialDao.getMaterName(v_MaterialInfo);
			if(nameList != null && nameList.size() > 0) {
				v_MaterialInfo.setI_mateName_Id(nameList.get(0).getI_id());
			} else {
				materialDao.addMaterName(v_MaterialInfo);
				List<MaterName_Info> name = materialDao.getMaterName(v_MaterialInfo);
				v_MaterialInfo.setI_mateName_Id(name.get(0).getI_id());
			}
			List<MaterName_Info> modelList = materialDao.getMaterModel(v_MaterialInfo);
			if(modelList != null && modelList.size() > 0) {
				v_MaterialInfo.setI_mateModel_Id(modelList.get(0).getI_id());
			} else {
				materialDao.addMaterModel(v_MaterialInfo);
				List<MaterName_Info> model = materialDao.getMaterModel(v_MaterialInfo);
				v_MaterialInfo.setI_mateModel_Id(model.get(0).getI_id());
			}
			materialDao.updateMaterial(v_MaterialInfo);
			return 0;
	}

	public int addMaterial(V_MaterialInfo v_MaterialInfo)  {
			return materialDao.addMaterial(v_MaterialInfo);
	}

	public int deletMaterial(V_MaterialInfo v_MaterialInfo)  {
			return materialDao.deletMaterial(v_MaterialInfo);
	}

	public List<V_MaterialInfo> getMaterialbyCode(V_MaterialInfo v_MaterialInfo)  {
			return materialDao.getMaterialbyCode(v_MaterialInfo);
	}

	public void addMater(V_MaterialInfo v_MaterialInfo)  {
			List<MaterName_Info> nameList = materialDao.getMaterName(v_MaterialInfo);
			if(nameList != null && nameList.size() > 0) {
				v_MaterialInfo.setI_mateName_Id(nameList.get(0).getI_id());
			} else {
				materialDao.addMaterName(v_MaterialInfo);
				List<MaterName_Info> name = materialDao.getMaterName(v_MaterialInfo);
				v_MaterialInfo.setI_mateName_Id(name.get(0).getI_id());
			}
			List<MaterName_Info> modelList = materialDao.getMaterModel(v_MaterialInfo);
			if(modelList != null && modelList.size() > 0) {
				v_MaterialInfo.setI_mateModel_Id(modelList.get(0).getI_id());
			} else {
				materialDao.addMaterModel(v_MaterialInfo);
			List<MaterName_Info> model = materialDao.getMaterModel(v_MaterialInfo);
				v_MaterialInfo.setI_mateModel_Id(model.get(0).getI_id());
			}
			materialDao.addMaterial(v_MaterialInfo);
	}

	public List<V_MaterialInfo> getMaterialById(V_MaterialInfo v_MaterialInfo)  {
			return materialDao.getMaterialById(v_MaterialInfo);
	}

	public List<Map<String, Object>> isInAsphTargetProportion(V_MaterialInfo v_MaterialInfo)  {
			return materialDao.isInAsphTargetProportion(v_MaterialInfo);
	}

	public List<Map<String, Object>> isInCementTheoryProportion(V_MaterialInfo v_MaterialInfo){
			return materialDao.isInCementTheoryProportion(v_MaterialInfo);
	}

	public List<Map<String, Object>> isInAsphTargetPropDetailed(V_MaterialInfo v_MaterialInfo){
			return materialDao.isInAsphTargetPropDetailed(v_MaterialInfo);
	}

	public List<Map<String, Object>> isInCementTheoPropDetailed(V_MaterialInfo v_MaterialInfo)  {
			return materialDao.isInCementTheoPropDetailed(v_MaterialInfo);
	}
	public List<Map<String, Object>> isMayMaterialDel(Map<String, Object> map)  {
			return materialDao.isMayMaterialDel(map);
	}
	public List<Map<String, Object>> iscementMaterialMayMaterialDel(Map<String, Object> map)  {
			return materialDao.iscementMaterialMayMaterialDel(map);
	}
	
}
