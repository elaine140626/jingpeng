package com.mixingStation.dao.cement;

import java.util.HashMap;
import java.util.List;

import com.mixingStation.model.cement.Bunker_Correspondence;



public interface Bunker_CorrespondenceDao {
	
	List<Bunker_Correspondence> getBunker_Correspondences(HashMap<String, Object> map);
	
	 int addBunker_Correspondence(Bunker_Correspondence bunker_Correspondence);
	
//	 List<Bunker_Correspondence> getBunker_CorrespondenceByCode(Bunker_Correspondence bunker_Correspondence);
//	
//	 List<Bunker_Correspondence> getBunker_CorrespondencesByContonid(HashMap<String, Object> map);
//	
//	 List<Map<String,Object>> getBunkerCorrespondencesconstructionConsByPropID(HashMap<String, Object> map);
}
