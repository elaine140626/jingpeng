package com.jingpeng.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jingpeng.dao.MaterialDao;
import com.jingpeng.model.MaterModel_Info;
import com.jingpeng.model.MaterName_Info;
import com.jingpeng.model.V_MaterialInfo;
import com.jingpeng.service.MaterialService;
import com.kdt.base.exception.BusinessException;
import com.kdt.base.exception.DataAccessException;

@Service
@Transactional
public class MaterialServiceImpl implements MaterialService {
	@Autowired
	private MaterialDao materialDao;

	public List<V_MaterialInfo> getMaterialInfo(V_MaterialInfo v_MaterialInfo) throws BusinessException {
		try {
			return materialDao.getMaterialInfo(v_MaterialInfo);
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			throw new BusinessException(e);
		}
	}

	public List<V_MaterialInfo> getMaterialbyNameOrModel(V_MaterialInfo v_MaterialInfo) throws BusinessException {
		try {
			return materialDao.getMaterialbyNameOrModel(v_MaterialInfo);
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			throw new BusinessException(e);
		}
	}
	
	public List<MaterModel_Info> getLastMaterModelId() throws BusinessException {
		try {
			return materialDao.getLastMaterModelId();
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			throw new BusinessException(e);
		}
	}

	public List<MaterName_Info> getLastMaterNameId() throws BusinessException {
		try {
			return materialDao.getLastMaterNameId();
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			throw new BusinessException(e);
		}
	}
	
	public List<MaterName_Info> getMaterName(V_MaterialInfo v_MaterialInfo) throws BusinessException {
		try {
			return materialDao.getMaterName(v_MaterialInfo);
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			throw new BusinessException(e);
		}
	}
	
	public List<MaterName_Info> getMaterModel(V_MaterialInfo v_MaterialInfo) throws BusinessException {
		try {
			return materialDao.getMaterName(v_MaterialInfo);
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			throw new BusinessException(e);
		}
	}

	public int addMaterName(V_MaterialInfo v_MaterialInfo) throws BusinessException {
		try {
			return materialDao.addMaterName(v_MaterialInfo);
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			throw new BusinessException(e);
		}
	}

	public int addMaterModel(V_MaterialInfo v_MaterialInfo) throws BusinessException {
		try {
			return materialDao.addMaterModel(v_MaterialInfo);
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			throw new BusinessException(e);
		}
	}

	public int updateMaterial(V_MaterialInfo v_MaterialInfo) throws BusinessException {
		try {
			List<MaterName_Info> nameList = materialDao.getMaterName(v_MaterialInfo);
			if(nameList != null && nameList.size() > 0) {
				v_MaterialInfo.setI_mateName_Id(nameList.get(0).getI_id());
			} else {
				int i_mateName_Id = materialDao.addMaterName(v_MaterialInfo);
				v_MaterialInfo.setI_mateName_Id(i_mateName_Id);
			}
			List<MaterName_Info> modelList = materialDao.getMaterModel(v_MaterialInfo);
			if(modelList != null && modelList.size() > 0) {
				v_MaterialInfo.setI_mateModel_Id(modelList.get(0).getI_id());
			} else {
				int i_mateModel_Id = materialDao.addMaterModel(v_MaterialInfo);
				v_MaterialInfo.setI_mateModel_Id(i_mateModel_Id);
			}
			materialDao.updateMaterial(v_MaterialInfo);
			return 0;
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			throw new BusinessException(e);
		}
	}

	public int addMaterial(V_MaterialInfo v_MaterialInfo) throws BusinessException {
		try {
			return materialDao.addMaterial(v_MaterialInfo);
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			throw new BusinessException(e);
		}
	}

	public int deletMaterial(V_MaterialInfo v_MaterialInfo) throws BusinessException {
		try {
			return materialDao.deletMaterial(v_MaterialInfo);
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			throw new BusinessException(e);
		}
	}

	public List<V_MaterialInfo> getMaterialbyCode(V_MaterialInfo v_MaterialInfo) throws BusinessException {
		try {
			return materialDao.getMaterialbyCode(v_MaterialInfo);
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			throw new BusinessException(e);
		}
	}

	public void addMater(V_MaterialInfo v_MaterialInfo) throws BusinessException {
		try {
			List<MaterName_Info> nameList = materialDao.getMaterName(v_MaterialInfo);
			if(nameList != null && nameList.size() > 0) {
				v_MaterialInfo.setI_mateName_Id(nameList.get(0).getI_id());
			} else {
				int i_mateName_Id = materialDao.addMaterName(v_MaterialInfo);
				v_MaterialInfo.setI_mateName_Id(i_mateName_Id);
			}
			List<MaterName_Info> modelList = materialDao.getMaterModel(v_MaterialInfo);
			if(modelList != null && modelList.size() > 0) {
				v_MaterialInfo.setI_mateModel_Id(modelList.get(0).getI_id());
			} else {
				int i_mateModel_Id = materialDao.addMaterModel(v_MaterialInfo);
				v_MaterialInfo.setI_mateModel_Id(i_mateModel_Id);
			}
			materialDao.addMaterial(v_MaterialInfo);
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			throw new BusinessException(e);
		}
	}

	public List<V_MaterialInfo> getMaterialById(V_MaterialInfo v_MaterialInfo) throws BusinessException {
		try {
			return materialDao.getMaterialById(v_MaterialInfo);
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			throw new BusinessException(e);
		}
	}

	public List<Map<String, Object>> isInAsphTargetProportion(V_MaterialInfo v_MaterialInfo) throws BusinessException {
		try {
			return materialDao.isInAsphTargetProportion(v_MaterialInfo);
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			throw new BusinessException(e);
		}
	}

	public List<Map<String, Object>> isInCementTheoryProportion(V_MaterialInfo v_MaterialInfo)
			throws BusinessException {
		try {
			return materialDao.isInCementTheoryProportion(v_MaterialInfo);
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			throw new BusinessException(e);
		}
	}

	public List<Map<String, Object>> isInAsphTargetPropDetailed(V_MaterialInfo v_MaterialInfo)
			throws BusinessException {
		try {
			return materialDao.isInAsphTargetPropDetailed(v_MaterialInfo);
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			throw new BusinessException(e);
		}
	}

	public List<Map<String, Object>> isInCementTheoPropDetailed(V_MaterialInfo v_MaterialInfo) throws BusinessException {
		try {
			return materialDao.isInCementTheoPropDetailed(v_MaterialInfo);
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			throw new BusinessException(e);
		}
	}

	/* 
	 * 沥青原材料可以删除条件
	 * tongn
	 * 2018.6.28
	 */
	public List<Map<String, Object>> isMayMaterialDel(Map<String, Object> map) throws BusinessException {
		try {
			return materialDao.isMayMaterialDel(map);
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			throw new BusinessException(e);
		}
	}
	
	/* 
	 * 水泥原材料可以删除条件
	 * tongn
	 * 2018.6.28
	 */
	public List<Map<String, Object>> iscementMaterialMayMaterialDel(Map<String, Object> map) throws BusinessException {
		try {
			return materialDao.iscementMaterialMayMaterialDel(map);
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			throw new BusinessException(e);
		}
	}
	
}
