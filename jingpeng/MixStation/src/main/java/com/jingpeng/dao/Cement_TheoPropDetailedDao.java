package com.jingpeng.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.jingpeng.model.Cement_TheoPropDetailed;
import com.jingpeng.model.Cement_TheoryProportion;
import com.kdt.base.exception.DataAccessException;
import com.kdt.base.support.mybatis.impl.KDDaoSupport;

@Repository
public class Cement_TheoPropDetailedDao extends KDDaoSupport{

	private final static String NAMESPACE = "cementDetail";
	
	public List<Cement_TheoPropDetailed> getCement_TheoPropDetailed(Cement_TheoPropDetailed cement_TheoPropDetailed)throws DataAccessException {

		return select(NAMESPACE + ".getCement_TheoPropDetailed", cement_TheoPropDetailed);
	}
	
	public List<Cement_TheoPropDetailed> getCement_TheoPropDetailedByTheoProp_ID(Cement_TheoryProportion cement_TheoryProportion)throws DataAccessException {

		return select(NAMESPACE + ".getCement_TheoPropDetailedByTheoProp_ID", cement_TheoryProportion);
	}
	
	public int addCement_TheoPropDetailed(List<Cement_TheoPropDetailed> list)throws DataAccessException {

		return insert(NAMESPACE + ".addCement_TheoPropDetailed", list);
	}
	
	public int updateCement_TheoPropDetailed(Cement_TheoPropDetailed cement_TheoPropDetailed)throws DataAccessException {

		return update(NAMESPACE + ".updateCement_TheoPropDetailed", cement_TheoPropDetailed);
	}
	
	public void  deletCement_TheoPropDetailed(Cement_TheoPropDetailed cement_TheoPropDetailed)throws DataAccessException {

		 update(NAMESPACE + ".deletCement_TheoPropDetailed", cement_TheoPropDetailed);
	}
}
