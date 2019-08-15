package com.jingpeng.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.jingpeng.model.Cement_ProductionDetailed;
import com.kdt.base.exception.DataAccessException;
import com.kdt.base.support.mybatis.impl.KDDaoSupport;

@Repository
public class Cement_ProductionDetailedDao extends KDDaoSupport{

	private final static String  NAMESPACE ="cement_ProductionDetailed";
	
	public List<Cement_ProductionDetailed> getCement_ProductionDetaileds (Cement_ProductionDetailed cement_ProductionDetailed) throws DataAccessException{
		
		return select(NAMESPACE+".getCement_ProductionDetaileds", cement_ProductionDetailed);
	}
}
