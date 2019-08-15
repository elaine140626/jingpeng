package com.oil.service.system.impl;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oil.dao.system.DetectionindexDao;
import com.oil.model.DataTablesResponseInfo;
import com.oil.model.ResponseInfo;
import com.oil.model.Userinfo;
import com.oil.model.system.Detectionindex;
import com.oil.service.system.DetectionindexService;
@Service
public class DetectionindexServiceImpl implements DetectionindexService {
	@Autowired
	private DetectionindexDao detectionindexDao;
	@Override
	public DataTablesResponseInfo getDetectionindex(HashMap<String, Object> map) {
		DataTablesResponseInfo info = new DataTablesResponseInfo();
		info.setData(detectionindexDao.getDetectionindex(map));
		return info;
	}
	@Override
	public ResponseInfo addDetectionindex(Detectionindex detectionindex) {
		ResponseInfo info = new ResponseInfo();
		int result;
		detectionindex.setIsDel(0);
		result = detectionindexDao.addDetectionindex(detectionindex);
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
	public ResponseInfo updateDetectionindex(Detectionindex detectionindex) {
		ResponseInfo info = new ResponseInfo();
		int result = detectionindexDao.updateDetectionindex(detectionindex);
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
	public ResponseInfo delDetectionindex(HttpServletRequest request,HashMap<String, Object> map) {
		ResponseInfo info = new ResponseInfo();
		Userinfo userInfo = (Userinfo) request.getSession().getAttribute("user");
		if (userInfo != null) {
			map.put("userId", userInfo.getId());
		}
		int result = detectionindexDao.delDetectionindex(map);
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
	public DataTablesResponseInfo findDetectionindexByName(String testingItems) {
		DataTablesResponseInfo info = new DataTablesResponseInfo();
		info.setData(detectionindexDao.findDetectionindexByName(testingItems));
		return info;
	}

}
