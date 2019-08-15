package com.mixingStation.service.cement;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.mixingStation.model.cement.Bunker_Correspondence;


public interface Bunker_CorrespondenceService {
	
	List<Bunker_Correspondence>	getBunker_Correspondences(HashMap<String, Object> map);
	
//	List<Bunker_Correspondence>	getBunker_CorrespondenceByCode(Bunker_Correspondence bunker_Correspondence);

	int addBunker_Correspondence(Map<String, Object> params);

//	List<Bunker_Correspondence>	getBunker_CorrespondencesByContonid(HashMap<String, Object> map);
//	
//    List<Map<String,Object>> getBunkerCorrespondencesconstructionConsByPropID(HashMap<String, Object> map)  ;
}
