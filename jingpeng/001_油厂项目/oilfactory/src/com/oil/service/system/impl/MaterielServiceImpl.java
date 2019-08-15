package com.oil.service.system.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oil.dao.system.MaterielDao;
import com.oil.model.system.MaterielInfo;
import com.oil.model.system.WareHouseInfo;
import com.oil.service.system.MaterielService;
@Service
public class MaterielServiceImpl implements MaterielService{
	@Autowired
	private MaterielDao materielDao;
	
	// 物料页查询所有
	@Override
	public List<MaterielInfo> getMateriel(Map<String, Object> map) {
		return materielDao.getMateriel(map);
	}
	
	// 删除物料明细判断是否被合同占用
	public List<Map<String, Object>> getAllContractdetailed(Map<String, Object> map) {
		return materielDao.getAllContractdetailed(map);
	}
	
	//删除物料信息
	@Override
	public int delMaterielById(int id) {
		return materielDao.delMaterielById(id);
	}
	
	//添加物料信息
	@Override 	
	public int addMateriel(MaterielInfo Materiel) {
		return materielDao.addMateriel(Materiel);
	}
	
	//修改物料信息
	@Override
	public int updateMateriel(MaterielInfo Materiel) {
		return materielDao.updateMateriel(Materiel);
	}
	
	// 根据物料编号,物料名称,物料型号去重
	@Override
	public List<Map<String, Object>> getAllMaterielNumber(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return materielDao.getAllMaterielNumber(map);
	}

	// 获取仓库信息
	@Override
	public List<WareHouseInfo> getWareHouseInfo(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return materielDao.getWarehouseInfo(map);
	}
}
