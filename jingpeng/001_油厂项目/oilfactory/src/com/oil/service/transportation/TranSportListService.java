package com.oil.service.transportation;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.oil.model.DataTablesResponseInfo;
import com.oil.model.ResponseInfo;
import com.oil.model.transportation.TranSportList;

public interface TranSportListService {

	DataTablesResponseInfo getTranSportList(Map<String,Object> map) throws ParseException;
	
	DataTablesResponseInfo getExOrStPlateNumber(Map<String, Object> map);
	
	ResponseInfo updateTranSportList(Map<String,Object> map, HttpServletRequest request);
	
	ResponseInfo delTranSportList(Map<String, Object> map);
	
	ResponseInfo adduploadfile(Map<String,Object> map);
	
	ResponseInfo delUploadfile(Map<String,Object> map, HttpServletRequest request);
	
	ResponseInfo isHaveUpload(Map<String,Object> map);
	
	int updateTranSportListApp(Map<String, Object> map);
	
	List<Map<String, Object>> getTranSportListApp(Map<String, Object> map);
	
	List<Map<String, Object>> getOutTranSportListApp(Map<String, Object> map);
	
	//ResponseInfo addTranSportList(Map<String, Object> map);
	
	//DataTablesResponseInfo getSaleType();
	
	//DataTablesResponseInfo getMaterielinfo();
	
	//DataTablesResponseInfo getIsExAndSt(Map<String,Object> map);
	
	//DataTablesResponseInfo getPlate();
	
	//DataTablesResponseInfo getTransportsById(Map<String,Object> map);
	
	//ResponseInfo updateNoWeighOut(Map<String,Object> map) throws Exception ;
	
	//DataTablesResponseInfo getUploadfile(Map<String,Object> map, HttpServletRequest request);
	
	//ResponseInfo updateExportmeasureIsTransport(Map<String,Object> map);
	
	//ResponseInfo updateStoragemeasureIsTransport(Map<String,Object> map);
	
	//DataTablesResponseInfo getEXById(Map<String,Object> map);
}
