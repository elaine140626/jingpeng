package com.jingpeng.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jingpeng.dao.BigScreenDao;
import com.jingpeng.model.Left;
import com.jingpeng.model.LqAxis;
import com.jingpeng.model.LqBar;
import com.jingpeng.model.SnBar;
import com.jingpeng.model.SnSwAxis;
import com.jingpeng.model.SwBar;
import com.jingpeng.model.Tab;
import com.jingpeng.service.BigScreenService;
import com.kdt.base.exception.BusinessException;
import com.kdt.base.exception.DataAccessException;

@Service
@Transactional
public class BigScreenServiceImpl implements BigScreenService{
	@Autowired
	private BigScreenDao bigScreenDao;

	public List<Left> getLeft(Map<String, Object> params) throws BusinessException {
		try {
			return bigScreenDao.getLeft(params);
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new BusinessException(e);
		}
	}

	public List<Tab> getTab(Map<String, Object> params) throws BusinessException {
		try {
			return bigScreenDao.getTab(params);
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new BusinessException(e);
		}
	}

	public List<Tab> getPie(Map<String, Object> params) throws BusinessException {
		try {
			return bigScreenDao.getPie(params);
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new BusinessException(e);
		}
	}

	public List<LqBar> getLqBar(Map<String, Object> params) throws BusinessException {
		try {
			return bigScreenDao.getLqBar(params);
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new BusinessException(e);
		}
	}

	public List<SnBar> getSnBar(Map<String, Object> params) throws BusinessException {
		try {
			return bigScreenDao.getSnBar(params);
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new BusinessException(e);
		}
	}

	public List<SwBar> getSwBar(Map<String, Object> params) throws BusinessException {
		try {
			return bigScreenDao.getSwBar(params);
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new BusinessException(e);
		}
	}

	public List<LqAxis> getLqAxis(Map<String, Object> params) throws BusinessException {
		try {
			return bigScreenDao.getLqAxis(params);
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new BusinessException(e);
		}
	}

	public List<SnSwAxis> getSnSwAxis(Map<String, Object> params) throws BusinessException {
		try {
			return bigScreenDao.getSnSwAxis(params);
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new BusinessException(e);
		}
	}

	public List<Map<String, Object>> getMaps(Map<String, Object> params) throws BusinessException {
		try {
			return bigScreenDao.getMaps(params);
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new BusinessException(e);
		}
	}
}
