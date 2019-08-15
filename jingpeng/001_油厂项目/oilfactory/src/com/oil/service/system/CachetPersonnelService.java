package com.oil.service.system;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import com.oil.model.DataTablesResponseInfo;
import com.oil.model.ResponseInfo;
import com.oil.model.system.CachetPersonnel;

public interface CachetPersonnelService {

	DataTablesResponseInfo getCachetPersonnel(HashMap<String, Object> map);

	ResponseInfo addCachetPersonnel(CachetPersonnel cachetPersonnel);

	ResponseInfo updateCachetPersonnel(CachetPersonnel cachetPersonnel);

	ResponseInfo delCachetPersonnel(HttpServletRequest request, HashMap<String, Object> map);

	DataTablesResponseInfo findCachetPersonnelByName(String cachetName);

	int updateElectronicsName(HttpServletRequest request, HashMap<String, Object> map);

}
