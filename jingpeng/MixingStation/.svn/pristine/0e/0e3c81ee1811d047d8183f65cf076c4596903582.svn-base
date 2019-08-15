package com.mix.service.cement.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mix.dao.cement.Cement_TheoPropDetailedDao;
import com.mix.model.Cement_TheoPropDetailed;
import com.mix.model.Cement_TheoryProportion;
import com.mix.service.cement.Cement_TheoPropDetailedService;
@Service
@Transactional
public class Cement_TheoPropDetailedServiceImpl implements Cement_TheoPropDetailedService{

	@Autowired
	private Cement_TheoPropDetailedDao  cement_TheoPropDetailedDao;
	
	//水泥配比明细
	@Override
	public List<Cement_TheoPropDetailed> getCement_TheoPropDetailedByTheoProp_ID(
			Cement_TheoryProportion cement_TheoryProportion) {
		return cement_TheoPropDetailedDao.getCement_TheoPropDetailedByTheoProp_ID(cement_TheoryProportion);
	}

	@Override
	public List<Cement_TheoPropDetailed> getCement_TheoPropDetailed(Cement_TheoPropDetailed cement_TheoPropDetailed) {
		return cement_TheoPropDetailedDao.getCement_TheoPropDetailed(cement_TheoPropDetailed);
	}

	@Override
	public int updateCement_TheoPropDetailed(Cement_TheoPropDetailed cement_TheoPropDetailed) {
		return cement_TheoPropDetailedDao.updateCement_TheoPropDetailed(cement_TheoPropDetailed);
	}

	@Override
	public void deletCement_TheoPropDetailed(Cement_TheoPropDetailed cement_TheoPropDetailed) {
		cement_TheoPropDetailedDao.deletCement_TheoPropDetailed(cement_TheoPropDetailed);
	}

}
