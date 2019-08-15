package com.mixingStation.service.cement.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mixingStation.dao.cement.Bunker_CorrespondenceDao;
import com.mixingStation.model.asphalt.AsphaltGrading;
import com.mixingStation.model.cement.Bunker_Correspondence;
import com.mixingStation.service.cement.Bunker_CorrespondenceService;

import net.sf.json.JSONObject;

@Service
public class Bunker_CorrespondenceServiceImpl implements Bunker_CorrespondenceService {

	@Autowired
	private Bunker_CorrespondenceDao bunker_CorrespondenceDao;
	
	@Override
	public List<Bunker_Correspondence> getBunker_Correspondences(HashMap<String, Object> map) {
		return bunker_CorrespondenceDao.getBunker_Correspondences(map);
	}
	
	@Override
	public int addBunker_Correspondence(Map<String, Object> params) {
		JSONObject jsonObject=JSONObject.fromObject(params.get("bunker_Correspondence"));
		Bunker_Correspondence bunker_Correspondence=(Bunker_Correspondence)JSONObject.toBean(jsonObject, Bunker_Correspondence.class);
		return bunker_CorrespondenceDao.addBunker_Correspondence(bunker_Correspondence);
	}

//	@Override
//	public List<Bunker_Correspondence> getBunker_CorrespondenceByCode(Bunker_Correspondence bunker_Correspondence) {
//		return bunker_CorrespondenceDao.getBunker_CorrespondenceByCode(bunker_Correspondence);
//	}
//
//
//	@Override
//	public List<Bunker_Correspondence> getBunker_CorrespondencesByContonid(HashMap<String, Object> map) {
//		return bunker_CorrespondenceDao.getBunker_CorrespondencesByContonid(map);
//	}
//
//	@Override
//	public List<Map<String, Object>> getBunkerCorrespondencesconstructionConsByPropID(HashMap<String, Object> map) {
//		return bunker_CorrespondenceDao.getBunkerCorrespondencesconstructionConsByPropID(map);
//	}

}
