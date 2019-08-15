package com.oil.service.transportation;

import java.text.ParseException;
import java.util.Map;

import com.oil.model.DataTablesResponseInfo;
import com.oil.model.ResponseInfo;
import com.oil.model.transportation.TranSportList;

public interface TranSportListStoragemeasureService {

	ResponseInfo addTranSportList(Map<String, Object> map);
	
	DataTablesResponseInfo getExOrStPlateNumber(Map<String, Object> map);
	
	DataTablesResponseInfo getSaleType();
	
	DataTablesResponseInfo getMaterielinfo();
	
	DataTablesResponseInfo getFleetInfo();
	
	DataTablesResponseInfo getTranSportList(Map<String,Object> map) throws ParseException;
	
	ResponseInfo updateTranSportList(Map<String,Object> map);
	
	ResponseInfo delTranSportList(Map<String, Object> map);
	
	DataTablesResponseInfo getIsExAndSt(Map<String,Object> map);
	
	DataTablesResponseInfo getPlate();
	
	DataTablesResponseInfo getTransportsById(Map<String,Object> map);
	
	ResponseInfo updateNoWeighOut(Map<String,Object> map) throws Exception ;
	
	ResponseInfo adduploadfile(Map<String,Object> map);
	
	DataTablesResponseInfo getUploadfile(Map<String,Object> map);
	
	ResponseInfo delUploadfile(Map<String,Object> map);

	ResponseInfo updateExportmeasureIsTransport(Map<String,Object> map);
	
	ResponseInfo updateStoragemeasureIsTransport(Map<String,Object> map);
	
	ResponseInfo isHaveUpload(Map<String,Object> map);
	
	DataTablesResponseInfo getEXById(Map<String,Object> map);
}
