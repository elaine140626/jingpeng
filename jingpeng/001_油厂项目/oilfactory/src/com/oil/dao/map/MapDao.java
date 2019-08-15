package com.oil.dao.map;

import java.util.List;
import java.util.Map;

import com.oil.model.map.MapCar;


public interface MapDao {

	List<MapCar> getCarTrajectoryXY(Map<String, Object> param);
/*	List<MapCar> getCarTrajectoryY(Map<String, Object> param);*/
	
	List<MapCar> getCarTrajectoryId(Map<String, Object> param);
	
	List<MapCar> getCarName();
	
	List<Map<String,Object>> getFleetCars(Map<String, Object> param);
	
	List<Map<String,Object>> getCustomertransports(Map<String, Object> param);
	
	int addCarsupervise(Map<String, Object> param);
	
	List<Map<String,Object>> getCarPlateNumber();
	
	int addPdacarinfo(Map<String, Object> param);
	
	int addPdaCarInfoHistory(Map<String, Object> param);
	
	List<Map<String,Object>> getPdaCarInfoHistory(Map<String, Object> param);
	
	int updatepdacarinfo(Map<String, Object> param);
	
	int updateCarinfoIsBinding(Map<String, Object> param);
	
	int delPdacarinfo(Map<String, Object> param);
	
	List<Map<String,Object>> getPdacarinfo(Map<String, Object> param);
	
	List<Map<String,Object>> GPSlogin(Map<String, Object> param);
	
	int delCarsupervise(Map<String, Object> param);
	
/*	int addMap(MapRoad mapRoad);
	
	List<MapRoad> getMapRoad(Map<String, Object> param);
	
	List<MapRoad> getMapRoadName();*/
}
