package com.oil.service.system.impl;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oil.dao.system.CachetPersonnelDao;
import com.oil.model.DataTablesResponseInfo;
import com.oil.model.ResponseInfo;
import com.oil.model.Userinfo;
import com.oil.model.system.CachetPersonnel;
import com.oil.service.system.CachetPersonnelService;

@Service
public class CachetPersonnelServiceImpl implements CachetPersonnelService{

	@Autowired
	CachetPersonnelDao cachetPersonnelDao;
	
	@Override
	public DataTablesResponseInfo getCachetPersonnel(HashMap<String, Object> map) {
		DataTablesResponseInfo info = new DataTablesResponseInfo();
		info.setData(cachetPersonnelDao.getCachetPersonnel(map));
		return info;
	}

	@Override
	public ResponseInfo addCachetPersonnel(CachetPersonnel cachetPersonnel) {
		ResponseInfo info = new ResponseInfo();
		int result;
		cachetPersonnel.setIsDel(0);
		result = cachetPersonnelDao.addCachetPersonnel(cachetPersonnel);
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
	public ResponseInfo updateCachetPersonnel(CachetPersonnel cachetPersonnel) {
		ResponseInfo info = new ResponseInfo();
		int result = cachetPersonnelDao.updateCachetPersonnel(cachetPersonnel);
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
	public ResponseInfo delCachetPersonnel(HttpServletRequest request, HashMap<String, Object> map) {
		ResponseInfo info = new ResponseInfo();
		Userinfo userInfo = (Userinfo) request.getSession().getAttribute("user");
		if (userInfo != null) {
			map.put("userId", userInfo.getId());
		}
		int result = cachetPersonnelDao.delCachetPersonnel(map);
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
	public DataTablesResponseInfo findCachetPersonnelByName(String cachetName) {
		DataTablesResponseInfo info = new DataTablesResponseInfo();
		info.setData(cachetPersonnelDao.findCachetPersonnelByName(cachetName));
		return info;
	}

	@Override
	public int updateElectronicsName(HttpServletRequest request, HashMap<String, Object> map) {
		Userinfo userInfo = (Userinfo) request.getSession().getAttribute("user");
		if (userInfo != null) {
			map.put("userId", userInfo.getId());
		}
		return cachetPersonnelDao.updateElectronicsName(map);
	}

}
