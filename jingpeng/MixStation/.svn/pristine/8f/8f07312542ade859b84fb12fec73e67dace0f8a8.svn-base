package com.jingpeng.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.jingpeng.model.MaterModel_Info;
import com.jingpeng.model.MaterName_Info;
import com.jingpeng.model.V_MaterialInfo;
import com.kdt.base.exception.DataAccessException;
import com.kdt.base.support.mybatis.impl.KDDaoSupport;

@Repository
public class MaterialDao extends KDDaoSupport {
	private final static String NAMESPACE = "material";
	
	public List<V_MaterialInfo> getMaterialInfo(V_MaterialInfo v_MaterialInfo) throws DataAccessException {
		return this.select(NAMESPACE+".getMaterialInfo", v_MaterialInfo);
	}
	
	public List<V_MaterialInfo> getMaterialbyNameOrModel (V_MaterialInfo v_MaterialInfo) throws DataAccessException {
		return this.select(NAMESPACE+".getMaterialbyNameOrModel", v_MaterialInfo);
	}

	public List<MaterModel_Info> getLastMaterModelId() throws DataAccessException {
		return this.select(NAMESPACE+".getLastMaterModelId", "");
	}

	public List<MaterName_Info> getLastMaterNameId() throws DataAccessException {
		return this.select(NAMESPACE+".getLastMaterNameId", "");
	}
	
	public List<MaterName_Info> getMaterName(V_MaterialInfo v_MaterialInfo) throws DataAccessException {
		return this.select(NAMESPACE+".getMaterName", "v_MaterialInfo");
	}
	
	public List<MaterName_Info> getMaterModel(V_MaterialInfo v_MaterialInfo) throws DataAccessException {
		return this.select(NAMESPACE+".getMaterModel", "v_MaterialInfo");
	}

	public int addMaterName(V_MaterialInfo v_MaterialInfo) throws DataAccessException {
		this.insert(NAMESPACE+".addMaterName", v_MaterialInfo);
		return v_MaterialInfo.getI_mateName_Id();
	}

	public int addMaterModel(V_MaterialInfo v_MaterialInfo) throws DataAccessException {
		this.insert(NAMESPACE+".addMaterModel", v_MaterialInfo);
		return v_MaterialInfo.getI_mateModel_Id();
	}

	public int updateMaterial(V_MaterialInfo v_MaterialInfo) throws DataAccessException {
		return this.update(NAMESPACE+".updateMaterial", v_MaterialInfo);
	}

	public int addMaterial(V_MaterialInfo v_MaterialInfo) throws DataAccessException {
		return this.insert(NAMESPACE+".addMaterial", v_MaterialInfo);
	}

	public int deletMaterial(V_MaterialInfo v_MaterialInfo) throws DataAccessException {
		return this.update(NAMESPACE+".deletMaterial", v_MaterialInfo);
	}

	public List<V_MaterialInfo> getMaterialbyCode(V_MaterialInfo v_MaterialInfo) throws DataAccessException {
		return this.select(NAMESPACE+".getMaterialbyCode", v_MaterialInfo);
	}

	public List<V_MaterialInfo> getMaterialById(V_MaterialInfo v_MaterialInfo) throws DataAccessException {
		return this.select(NAMESPACE+".getMaterialById", v_MaterialInfo);
	}

	public List<Map<String, Object>> isInAsphTargetProportion(V_MaterialInfo v_MaterialInfo) throws DataAccessException {
		return this.select(NAMESPACE+".isInAsphTargetProportion", v_MaterialInfo);
	}

	public List<Map<String, Object>> isInCementTheoryProportion(V_MaterialInfo v_MaterialInfo) throws DataAccessException {
		return this.select(NAMESPACE+".isInCementTheoryProportion", v_MaterialInfo);
	}

	public List<Map<String, Object>> isInAsphTargetPropDetailed(V_MaterialInfo v_MaterialInfo) throws DataAccessException {
		return this.select(NAMESPACE+".isInAsphTargetPropDetailed", v_MaterialInfo);
	}

	public List<Map<String, Object>> isInCementTheoPropDetailed(V_MaterialInfo v_MaterialInfo) throws DataAccessException {
		return this.select(NAMESPACE+".isInCementTheoPropDetailed", v_MaterialInfo);
	}
	
	/* 
	 *  沥青原材料可以删除条件
	 * tongn
	 * 2018.6.28
	 */
	public List<Map<String, Object>> isMayMaterialDel(Map<String, Object> map) throws DataAccessException {
		return this.select(NAMESPACE+".isMayMaterialDel", map);
	}
	
	/* 
	 *  水泥原材料可以删除条件
	 * tongn
	 * 2018.6.28
	 */
	public List<Map<String, Object>> iscementMaterialMayMaterialDel(Map<String, Object> map) throws DataAccessException {
		return this.select(NAMESPACE+".iscementMaterialMayMaterialDel", map);
	}
	
}
