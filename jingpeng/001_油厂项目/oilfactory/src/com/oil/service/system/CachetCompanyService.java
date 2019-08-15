package com.oil.service.system;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import com.oil.model.DataTablesResponseInfo;
import com.oil.model.ResponseInfo;
import com.oil.model.system.CachetCompany;

public interface CachetCompanyService {

	DataTablesResponseInfo getCachetCompany(HashMap<String, Object> map);

	ResponseInfo addCachetCompany(CachetCompany cachetPersonnel);

	ResponseInfo updateCachetCompany(CachetCompany cachetPersonnel);

	ResponseInfo delCachetCompany(HttpServletRequest request, HashMap<String, Object> map);

	DataTablesResponseInfo findCachetCompanyByName(String cachetName);

	int updateElectronicsName(HttpServletRequest request, HashMap<String, Object> map);

}
