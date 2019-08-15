package com.jingpeng.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jingpeng.model.Bunker_Correspondence;
import com.jingpeng.service.Bunker_CorrespondenceService;
import com.kdt.base.exception.BusinessException;
import com.kdt.base.exception.DataAccessException;


public interface Bunker_CorrespondenceService {
	
	public List<Bunker_Correspondence>	getBunker_Correspondences(HashMap<String, Object> map)throws BusinessException;
	
	public List<Bunker_Correspondence>	getBunker_CorrespondenceByCode(Bunker_Correspondence bunker_Correspondence)throws BusinessException;

	public int addBunker_Correspondence(Bunker_Correspondence bunker_Correspondence)throws BusinessException;

	public List<Bunker_Correspondence>	getBunker_CorrespondencesByContonid(HashMap<String, Object> map)throws BusinessException;
	
	
	/*
	 * 施工配比信息 删除条件
	 * tongn
	 * 2018.6.28
	 */
    List<Map<String,Object>> getBunkerCorrespondencesconstructionConsByPropID(HashMap<String, Object> map) throws BusinessException ;



	
}
