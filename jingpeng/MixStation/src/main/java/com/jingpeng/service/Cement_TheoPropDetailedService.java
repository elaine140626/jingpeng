package com.jingpeng.service;

import java.util.List;

import com.jingpeng.model.Cement_TheoPropDetailed;
import com.jingpeng.model.Cement_TheoryProportion;
import com.kdt.base.exception.BusinessException;

public interface Cement_TheoPropDetailedService {
	
	public List<Cement_TheoPropDetailed> getCement_TheoPropDetailed(Cement_TheoPropDetailed cement_TheoPropDetailed) throws BusinessException;
	
	public List<Cement_TheoPropDetailed> getCement_TheoPropDetailedByTheoProp_ID(Cement_TheoryProportion cement_TheoryProportion) throws BusinessException;

	public int updateCement_TheoPropDetailed(Cement_TheoPropDetailed cement_TheoPropDetailed)throws BusinessException;
	
	public void deletCement_TheoPropDetailed(Cement_TheoPropDetailed cement_TheoPropDetailed)throws BusinessException;

}
