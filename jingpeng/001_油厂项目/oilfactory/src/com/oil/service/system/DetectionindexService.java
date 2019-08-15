package com.oil.service.system;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import com.oil.model.DataTablesResponseInfo;
import com.oil.model.ResponseInfo;
import com.oil.model.system.Detectionindex;

public interface DetectionindexService {

	DataTablesResponseInfo getDetectionindex(HashMap<String, Object> map);

	ResponseInfo addDetectionindex(Detectionindex detectionindex);

	ResponseInfo delDetectionindex(HttpServletRequest request, HashMap<String, Object> map);

	ResponseInfo updateDetectionindex(Detectionindex detectionindex);

	DataTablesResponseInfo findDetectionindexByName(String testingItems);

}
