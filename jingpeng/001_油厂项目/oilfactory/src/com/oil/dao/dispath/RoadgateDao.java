package com.oil.dao.dispath;

import java.util.List;

import com.oil.model.Roadgate;

public interface RoadgateDao {

	List<Roadgate> getRoadgateList(Roadgate roadgate);

	int addRoadgate(Roadgate roadgate);

	int deleteRoadgate(Roadgate roadgate);

	
}
