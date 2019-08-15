package com.mixingStation.dao.cement;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.mixingStation.model.cement.CementProductionFigures;
import com.mixingStation.model.userInfo.OrganizationInfo;



@Repository
public interface CementProductionFiguresDao {
	//list
	List<CementProductionFigures> getCementProductionStatisticsList(Map<String, Object> map);
}
