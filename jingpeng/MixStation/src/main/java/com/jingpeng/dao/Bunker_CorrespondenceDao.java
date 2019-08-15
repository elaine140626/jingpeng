package com.jingpeng.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.jingpeng.model.Bunker_Correspondence;
import com.kdt.base.exception.DataAccessException;
import com.kdt.base.support.mybatis.impl.KDDaoSupport;

@Repository
public class Bunker_CorrespondenceDao extends KDDaoSupport {
	
	private final static  String NAMESPACE = "bunker";
	
	public List<Bunker_Correspondence> getBunker_Correspondences(HashMap<String, Object> map) throws DataAccessException {
		
		return this.select(NAMESPACE+".getBunker_Correspondences", map);
	}
	
	public List<Bunker_Correspondence> getBunker_CorrespondenceByCode(Bunker_Correspondence bunker_Correspondence) throws DataAccessException {
		
		return this.select(NAMESPACE+".getBunker_CorrespondenceByCode", bunker_Correspondence);
	}
	
	public int addBunker_Correspondence(Bunker_Correspondence bunker_Correspondence) throws DataAccessException {
		
		return this.insert(NAMESPACE+".addBunker_Correspondence", bunker_Correspondence);
	}
	
	
	public List<Bunker_Correspondence> getBunker_CorrespondencesByContonid(HashMap<String, Object> map) throws DataAccessException {
		
		return this.select(NAMESPACE+".getBunker_CorrespondencesByContonid", map);
	}
	
	/*
	 * 施工配比信息 删除条件
	 * tongn
	 * 2018.6.28
	 */
public List<Map<String,Object>> getBunkerCorrespondencesconstructionConsByPropID(HashMap<String, Object> map) throws DataAccessException {
		
		return this.select(NAMESPACE+".getBunkerCorrespondencesconstructionConsByPropID", map);
	}
}
