package com.oil.service.transportation;

import java.util.Map;

import com.oil.model.DataTablesResponseInfo;
import com.oil.model.ResponseInfo;

public interface TransportationExportmeasureService {

	DataTablesResponseInfo getExportMeasurePlate();
	
	DataTablesResponseInfo getExportmeasureByPlateNumber(Map<String, Object> map);
	
	DataTablesResponseInfo getSaleType();
	
	DataTablesResponseInfo getCustomertransportsById(Map<String, Object> map);
	
	ResponseInfo addTranSportList(Map<String, Object> map);
}
