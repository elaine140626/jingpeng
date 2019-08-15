package com.jingpeng.service;

import java.util.List;
import java.util.Map;

import com.jingpeng.model.Left;
import com.jingpeng.model.LqAxis;
import com.jingpeng.model.LqBar;
import com.jingpeng.model.SnBar;
import com.jingpeng.model.LqBar;
import com.jingpeng.model.SnSwAxis;
import com.jingpeng.model.SwBar;
import com.jingpeng.model.Tab;
import com.kdt.base.exception.BusinessException;

public interface BigScreenService {

	List<Left> getLeft(Map<String, Object> params) throws BusinessException;

	List<Tab> getTab(Map<String, Object> params) throws BusinessException;

	List<Tab> getPie(Map<String, Object> params) throws BusinessException;

	List<LqBar> getLqBar(Map<String, Object> params) throws BusinessException;

	List<SnBar> getSnBar(Map<String, Object> params) throws BusinessException;
	
	List<SwBar> getSwBar(Map<String, Object> params) throws BusinessException;

	List<LqAxis> getLqAxis(Map<String, Object> params) throws BusinessException;
	
	List<SnSwAxis> getSnSwAxis(Map<String, Object> params) throws BusinessException;

	List<Map<String, Object>> getMaps(Map<String, Object> params) throws BusinessException;

}
