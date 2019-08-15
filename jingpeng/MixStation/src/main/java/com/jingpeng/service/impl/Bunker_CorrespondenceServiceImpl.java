package com.jingpeng.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jsoup.select.Evaluator.IsEmpty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jingpeng.dao.Bunker_CorrespondenceDao;
import com.jingpeng.model.Bunker_Correspondence;
import com.jingpeng.service.Bunker_CorrespondenceService;
import com.jingpeng.service.impl.Bunker_CorrespondenceServiceImpl;
import com.kdt.base.exception.BusinessException;
import com.kdt.base.exception.DataAccessException;

@Service
@Transactional
public class Bunker_CorrespondenceServiceImpl implements Bunker_CorrespondenceService {

	@Autowired
	Bunker_CorrespondenceDao bunker_CorrespondenceDao;

	@Transactional
	public int addBunker_Correspondence(Bunker_Correspondence bunker_Correspondence) throws BusinessException {
		try {
			return bunker_CorrespondenceDao.addBunker_Correspondence(bunker_Correspondence);
		} catch (DataAccessException e) {
			throw new BusinessException(e);
		}
	}

	public List<Bunker_Correspondence> getBunker_Correspondences(HashMap<String, Object> map) throws BusinessException {
		try {
			return bunker_CorrespondenceDao.getBunker_Correspondences(map);
		} catch (DataAccessException e) {
			throw new BusinessException(e);
		}
	}

	public List<Bunker_Correspondence> getBunker_CorrespondenceByCode(Bunker_Correspondence bunker_Correspondence)
			throws BusinessException {
		try {
			return bunker_CorrespondenceDao.getBunker_CorrespondenceByCode(bunker_Correspondence);
		} catch (DataAccessException e) {
			throw new BusinessException(e);
		}
	}

	public List<Bunker_Correspondence> getBunker_CorrespondencesByContonid(HashMap<String, Object> map)
			throws BusinessException {
		try {
			return bunker_CorrespondenceDao.getBunker_CorrespondencesByContonid(map);
		} catch (DataAccessException e) {
			throw new BusinessException(e);
		}
	}

	/*
	 * 施工配比信息 删除条件
	 * tongn
	 * 2018.6.28
	 */
	public List<Map<String, Object>> getBunkerCorrespondencesconstructionConsByPropID(HashMap<String, Object> map)
			throws BusinessException {
		try {
			return bunker_CorrespondenceDao.getBunkerCorrespondencesconstructionConsByPropID(map);
		} catch (DataAccessException e) {
			throw new BusinessException(e);
		}
	}

}
