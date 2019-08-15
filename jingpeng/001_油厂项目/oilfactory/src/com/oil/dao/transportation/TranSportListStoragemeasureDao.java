package com.oil.dao.transportation;

import java.util.List;
import java.util.Map;

import com.oil.model.Datadictionary;
import com.oil.model.dispath.OutboundEntity;
import com.oil.model.system.FleetInfo;
import com.oil.model.system.MaterielInfo;
import com.oil.model.transportation.TranSportList;

public interface TranSportListStoragemeasureDao {

	int addTranSportList(Map<String, Object> map);
	
	List<Map<String,Object>> getExOrStPlateNumber(Map<String,Object> map);
	
	List<Datadictionary> getSaleType();
	
	List<MaterielInfo> getMaterielinfo();
	
	List<FleetInfo> getFleetinfo();
		
	List<Map<String,Object>> getTranSportList(Map<String,Object> map);
	
	int updateTranSportList(Map<String, Object> map);
	
	int delTranSportList(Map<String, Object> map);
	
	Map<String,Object> getIsExAndSt(Map<String,Object> map);
	
	List<Map<String,Object>> getPlate();
	
	List<Map<String,Object>> getTransportsById(Map<String,Object> map);
	
	int updateNoWeighOut(Map<String,Object> map);
	
	int adduploadfile(Map<String,Object> map);
	
	List<Map<String,Object>> getUploadfile(Map<String,Object> map);
	
	int delUploadfile(Map<String,Object> map);
	
	int updateExportmeasureIsTransport(Map<String,Object> map);
	
	int updateStoragemeasureIsTransport(Map<String,Object> map);
	
	List<Map<String,Object>> getEXById(Map<String,Object> map);
	
	List<Map<String,Object>> exIsHaveNoweighoutwarehouse(Map<String,Object> map);
}
