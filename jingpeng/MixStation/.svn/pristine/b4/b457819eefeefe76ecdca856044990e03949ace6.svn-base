package com.jingpeng.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jingpeng.dao.EquipmentDao;
import com.jingpeng.model.Equipment_Info;
import com.jingpeng.service.EquipmentService;
import com.kdt.base.exception.BusinessException;
import com.kdt.base.exception.DataAccessException;

@Service
public class EquipmentServiceImpl implements EquipmentService{
	
	@Autowired
	private EquipmentDao equipmentdao;

	public List<Equipment_Info> getEquipmentInfos(Equipment_Info equipment_Info) throws BusinessException {
		List<Equipment_Info> lists;
		try {
			lists = equipmentdao.getEquipments(equipment_Info);
			return lists;
		} catch (DataAccessException e) {
			 throw new BusinessException(e);
		}
		
	}
	
	public Equipment_Info getEquipmentInfoById(Equipment_Info equipment_Info) throws BusinessException {
	
		
		List<Equipment_Info> equipment_Infos;
		try {
			equipment_Infos = equipmentdao.getEquipmentById(equipment_Info);
		} catch (DataAccessException e) {
			 throw new BusinessException(e);
		}
		
		Equipment_Info Equipment_InfoPo = equipment_Infos.get(0);
		
		return Equipment_InfoPo;
	}

	@Transactional
	public int addEquipmentInfo(Equipment_Info equipment_Info) throws BusinessException {
		
		try {
		return	equipmentdao.addEquipmentInfo(equipment_Info);
		} catch (DataAccessException e) {
			 throw new BusinessException(e);
		}
	}

	@Transactional
	public int updateEquipmentInfo(Equipment_Info equipment_Info) throws BusinessException {

		try {
		return	equipmentdao.updateEquipmentInfo(equipment_Info);
		} catch (DataAccessException e) {
			 throw new BusinessException(e);
		}
		
	}

	@Transactional
	public int deletEquipmentInfo(Equipment_Info equipment_Info) throws BusinessException {
		
		try {
			return	equipmentdao.deletEquipmentInfo(equipment_Info);
		} catch (DataAccessException e) {
			 throw new BusinessException(e);
		}
	}


	public List<Equipment_Info> getEquipmentbyNo(Equipment_Info equipment_Info) throws BusinessException {
		try {
		return	equipmentdao.getEquipmentbyNo(equipment_Info);
		} catch (DataAccessException e) {
			throw new BusinessException(e);
		}
	}
	public List<Equipment_Info> getEquipmentbyCode(Equipment_Info equipment_Info) throws BusinessException {
		try {
		return	equipmentdao.getEquipmentbyCode(equipment_Info);
		} catch (DataAccessException e) {
			throw new BusinessException(e);
		}
	}
	
}
