package com.jingpeng.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.jingpeng.model.Asph_TargetPropDetailed;
import com.jingpeng.model.Asph_TargetProportion;
import com.jingpeng.model.V_MaterialInfo;
import com.kdt.base.exception.DataAccessException;
import com.kdt.base.support.mybatis.impl.KDDaoSupport;

@Repository
public class Asph_TargetProDao extends KDDaoSupport{
    private final static String NAMESPACE = "asph_TargetPro";
	
    public List<Asph_TargetProportion> getV_Asph_TargetPro(Map<String, Object> map) throws DataAccessException {
    	return this.select(NAMESPACE+".getV_Asph_TargetPro", map);
    }

	public List<V_MaterialInfo> getRawMaterial(Map map) throws DataAccessException {
		return this.select(NAMESPACE+".getRawMaterial", map);
	}

	public List<V_MaterialInfo> getMaterialModelBymateNameid(V_MaterialInfo v_MaterialInfo) throws DataAccessException {
		return this.select(NAMESPACE+".getMaterialModelBymateNameid", v_MaterialInfo);
	}

	public List getProportionCode(Asph_TargetProportion asphTargetPro) throws DataAccessException {
		return this.select(NAMESPACE+".getProportionCode", asphTargetPro);
	}

	public int addAsphTargetProD(List<Asph_TargetPropDetailed> list) throws DataAccessException {
		return this.insert(NAMESPACE+".addAsphTargetProD", list);
	}

	public int addAsphTargetPro(Asph_TargetProportion asphTargetPro) throws DataAccessException {
		 this.insert(NAMESPACE+".addAsphTargetPro", asphTargetPro);
		 return asphTargetPro.getI_id();
	}

	public List<Map<String, Object>> getAsphTargetProD(Asph_TargetProportion asphTargetPro) throws DataAccessException {
		return this.select(NAMESPACE+".getAsphTargetProD", asphTargetPro);
	}

	public int updateAsphTargetPro(Asph_TargetProportion asphTargetPro) throws DataAccessException {
		return this.update(NAMESPACE+".updateAsphTargetPro", asphTargetPro);
	}

	public int delAsph_TargetPropDetailed(Asph_TargetProportion asphTargetPro) throws DataAccessException {
		return this.update(NAMESPACE+".delAsph_TargetPropDetailed", asphTargetPro);
	}

	public int delAsphTargetPro(Asph_TargetProportion asphTargetPro) throws DataAccessException {
		return this.update(NAMESPACE+".delAsphTargetPro", asphTargetPro);
	}

	public List<Asph_TargetProportion> getAsphTargetProById(Asph_TargetProportion asphTargetPro) throws DataAccessException {
		return this.select(NAMESPACE+".getAsphTargetProById", asphTargetPro);
	}
	
	public List<Asph_TargetProportion> select_Asph_TargetPropDetailed(Asph_TargetProportion asph_TargetPro) throws DataAccessException {
		return this.select(NAMESPACE+".select_Asph_TargetPropDetailed", asph_TargetPro);
	}
	
}
