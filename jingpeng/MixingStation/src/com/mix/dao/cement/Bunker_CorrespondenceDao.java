package com.mix.dao.cement;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


import com.mix.model.Bunker_Correspondence;

public interface Bunker_CorrespondenceDao {
	public int addBunker_Correspondence(Bunker_Correspondence bunker_Correspondence);
	
	public List<Bunker_Correspondence> getBunker_Correspondences(HashMap<String, Object> map);
	
	public List<Bunker_Correspondence> getBunker_CorrespondenceByCode(Bunker_Correspondence bunker_Correspondence);
	
	public List<Bunker_Correspondence> getBunker_CorrespondencesByContonid(HashMap<String, Object> map);
	
	public List<Map<String,Object>> getBunkerCorrespondencesconstructionConsByPropID(HashMap<String, Object> map);
}
