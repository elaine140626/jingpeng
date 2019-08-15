package com.curing.projectSchedule.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.curing.projectSchedule.dao.SubheadNumberMoneyDao;
import com.curing.projectSchedule.model.SubheadNumberMoneyEntity;
import com.curing.projectSchedule.service.SubheadNumberMoneyService;

@Service
@Transactional
public class SubheadNumberMoneyServiceImpl implements SubheadNumberMoneyService {
	
	@Autowired
	private SubheadNumberMoneyDao subheadNumberMoneyDao;

	@Override
	public List<SubheadNumberMoneyEntity> getSubheadNumberMoney(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return subheadNumberMoneyDao.getSubheadNumberMoney(map);
	}

	@Override
	public int insertSubheadNumberMoney(SubheadNumberMoneyEntity subheadNumberMoneyEntity) {
		// TODO Auto-generated method stub
		return subheadNumberMoneyDao.insertSubheadNumberMoney(subheadNumberMoneyEntity);
	}

	@Override
	public int updateSubheadNumberMoney(SubheadNumberMoneyEntity subheadNumberMoneyEntity) {
		// TODO Auto-generated method stub
		return subheadNumberMoneyDao.updateSubheadNumberMoney(subheadNumberMoneyEntity);
	}

	@Override
	public int deleteSubheadNumberMoney(SubheadNumberMoneyEntity subheadNumberMoneyEntity) {
		// TODO Auto-generated method stub
		return subheadNumberMoneyDao.deleteSubheadNumberMoney(subheadNumberMoneyEntity);
	}

	@Override
	public List<Map<String, Object>> getSevenDailyById(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return subheadNumberMoneyDao.getSevenDailyById(map);
	}

}
