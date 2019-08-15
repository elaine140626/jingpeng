package com.mixingStation.dao.asphalt;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.mixingStation.model.asphalt.AsphaltGrading;

@Repository
public interface GradationInfoDao {

	int insertGradationInfo(AsphaltGrading newAsphaltGrading);

	List<AsphaltGrading> queryList(Map<String, Object> params);

	List<Map<String, Object>> getColumnModel(Map<String, Object> params);

	AsphaltGrading queryDataById(Map<String, Object> params);

	int updateGradationInfo(AsphaltGrading asphaltGrading);

	int deleteGradationInfo(AsphaltGrading asphaltGrading);

}
