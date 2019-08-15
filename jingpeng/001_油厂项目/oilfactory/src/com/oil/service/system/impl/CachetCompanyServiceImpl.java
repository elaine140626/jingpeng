package com.oil.service.system.impl;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oil.dao.system.CachetCompanyDao;
import com.oil.model.DataTablesResponseInfo;
import com.oil.model.ResponseInfo;
import com.oil.model.Userinfo;
import com.oil.model.system.CachetCompany;
import com.oil.service.system.CachetCompanyService;

@Service
public class CachetCompanyServiceImpl implements CachetCompanyService{

	@Autowired
	CachetCompanyDao cachetCompanyDao;
	
	@Override
	public DataTablesResponseInfo getCachetCompany(HashMap<String, Object> map) {
		DataTablesResponseInfo info = new DataTablesResponseInfo();
		info.setData(cachetCompanyDao.getCachetCompany(map));
		return info;
	}

	@Override
	public ResponseInfo addCachetCompany(CachetCompany cachetCompany) {
		ResponseInfo info = new ResponseInfo();
		int result;
		cachetCompany.setIsDel(0);
		result = cachetCompanyDao.addCachetCompany(cachetCompany);
		if(result>0) {
			info.setCode("200");
			info.setMessage("添加成功");
		}else {
			info.setCode("500");
			info.setMessage("添加失败");
		}
		return info;
	}

	@Override
	public ResponseInfo updateCachetCompany(CachetCompany cachetCompany) {
		ResponseInfo info = new ResponseInfo();
		int result = cachetCompanyDao.updateCachetCompany(cachetCompany);
		if(result>0) {
			info.setCode("200");
			info.setMessage("修改成功");
		}else {
			info.setCode("500");
			info.setMessage("修改失败");
		}
		return info;
	}

	@Override
	public ResponseInfo delCachetCompany(HttpServletRequest request, HashMap<String, Object> map) {
		ResponseInfo info = new ResponseInfo();
		Userinfo userInfo = (Userinfo) request.getSession().getAttribute("user");
		if (userInfo != null) {
			map.put("userId", userInfo.getId());
		}
		int result = cachetCompanyDao.delCachetCompany(map);
		if(result>0) {
			info.setCode("200");
			info.setMessage("删除成功");
		}else {
			info.setCode("500");
			info.setMessage("删除失败");
		}
		return info;
	}

	@Override
	public DataTablesResponseInfo findCachetCompanyByName(String testCompany) {
		DataTablesResponseInfo info = new DataTablesResponseInfo();
		info.setData(cachetCompanyDao.findCachetCompanyByName(testCompany));
		return info;
	}

	@Override
	public int updateElectronicsName(HttpServletRequest request, HashMap<String, Object> map) {
		Userinfo userInfo = (Userinfo) request.getSession().getAttribute("user");
		if (userInfo != null) {
			map.put("userId", userInfo.getId());
		}
		return cachetCompanyDao.updateElectronicsName(map);
	}

}
