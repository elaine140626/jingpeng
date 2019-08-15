package com.oil.dao.system;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.oil.model.system.Detectionindex;

public interface DetectionindexDao {

	List<Detectionindex> getDetectionindex(HashMap<String, Object> map);

	int addDetectionindex(Detectionindex detectionindex);

	int updateDetectionindex(Detectionindex detectionindex);

	int delDetectionindex(HashMap<String, Object> map);

	List<Detectionindex> findDetectionindexByName(@Param("testingItems") String testingItems);

}
