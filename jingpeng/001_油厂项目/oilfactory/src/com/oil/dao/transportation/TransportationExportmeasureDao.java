package com.oil.dao.transportation;

import java.util.List;
import java.util.Map;

import com.oil.model.Datadictionary;

public interface TransportationExportmeasureDao {

	List<Map<String,Object>> getExportMeasurePlate();

	List<Map<String, Object>> getExportmeasureByPlateNumber(Map<String, Object> map);
	
	List<Map<String,Object>> exIsHaveNoweighoutwarehouse(Map<String,Object> map);
	
	List<Datadictionary> getSaleType();
	
	List<Map<String,Object>> getCustomertransportsById(Map<String,Object> map);
	
	int addTranSportList(Map<String, Object> map);
}
