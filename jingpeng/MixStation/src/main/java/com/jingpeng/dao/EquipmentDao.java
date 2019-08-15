package com.jingpeng.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.jingpeng.model.Equipment_Info;
import com.kdt.base.exception.DataAccessException;
import com.kdt.base.support.mybatis.impl.KDDaoSupport;

@Repository
public class EquipmentDao extends KDDaoSupport{

private final static String NAMESPACE = "equipment_info";
	
	
	public List<Equipment_Info> getEquipments (Equipment_Info equipment_info) throws DataAccessException {
	
		return select(NAMESPACE+".gets", equipment_info);
	}
	
	public List<Equipment_Info> getEquipmentById (Equipment_Info equipment_info) throws DataAccessException {
		return  select(NAMESPACE+".getById", equipment_info);
	}
	
	public List<Equipment_Info>  getEquipmentbyNo(Equipment_Info equipment_info) throws DataAccessException {
		
		return select(NAMESPACE+".getequipmentbyNo", equipment_info);
	}
	public List<Equipment_Info>  getEquipmentbyCode(Equipment_Info equipment_info) throws DataAccessException {
		
		return select(NAMESPACE+".getequipmentbyCode", equipment_info);
	}
	
	public int addEquipmentInfo(Equipment_Info equipment_Info) throws DataAccessException {
		
		return insert(NAMESPACE+".add", equipment_Info);
	}
	
	public int updateEquipmentInfo(Equipment_Info equipment_Info) throws DataAccessException {
		return update(NAMESPACE+".update", equipment_Info);
	}
	
	public int deletEquipmentInfo(Equipment_Info equipment_Info) throws DataAccessException {
		
		return update(NAMESPACE+".del", equipment_Info);
	}
}
