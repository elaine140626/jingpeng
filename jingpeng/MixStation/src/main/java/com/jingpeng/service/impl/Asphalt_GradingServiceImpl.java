package com.jingpeng.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jingpeng.dao.Asphalt_GradingDao;
import com.jingpeng.model.Asph_TargetProportion;
import com.jingpeng.model.Asphalt_GradDetailed;
import com.jingpeng.model.Asphalt_Grading;
import com.jingpeng.model.Asphalt_GradingModel;
import com.jingpeng.service.Asphalt_GradingService;
import com.jingpeng.service.impl.Asphalt_GradingServiceImpl;
import com.kdt.base.exception.BusinessException;
import com.kdt.base.exception.DataAccessException;

@Service
@Transactional
public class Asphalt_GradingServiceImpl implements Asphalt_GradingService {
	
	@Autowired
	Asphalt_GradingDao asphalt_GraingdDao;

	public List<Asphalt_GradingModel> getAsphalt_Gradings(Map<String, Object> map)throws BusinessException {
		
		try {
			return asphalt_GraingdDao.getasphalt_Gradings(map);
		} catch (DataAccessException e) {
			throw new BusinessException(e);
		}
	}
	
	public List<Asphalt_GradingModel> getAsphalt_GradingById(Asphalt_GradingModel asphalt_GradingModel)throws BusinessException {
		try {
			return asphalt_GraingdDao.getAsphalt_GradingById(asphalt_GradingModel);
		} catch (DataAccessException e) {
			throw new BusinessException(e);
		}
	}
	
	public List<Asphalt_GradingModel> getAsphalt_GradDetailedById(Asphalt_GradingModel asphalt_GradingModel) throws BusinessException{
		try {
			return asphalt_GraingdDao.getAsphalt_GradDetailedById(asphalt_GradingModel);
		} catch (DataAccessException e) {
			throw new BusinessException(e);
		}
	}
	
	public List<Asphalt_Grading> getAsphalt_Gradingcode(Asphalt_Grading asphalt_Grading) throws BusinessException {
		
		try {
			return asphalt_GraingdDao.getAsphalt_Gradingcode(asphalt_Grading);
		} catch (DataAccessException e) {
			throw new BusinessException(e);
		}
		
	}

	public int addAsphalt_Grad(Asphalt_Grading asphalt_Grading) throws BusinessException {
		
		int backi_grad_Id;
		try {
			List<Asphalt_GradDetailed> list = asphalt_Grading.getList();
			backi_grad_Id = asphalt_GraingdDao.addAsphalt_Grading(asphalt_Grading);
			if(list != null) {
				for(int i = 0; i < list.size(); i++) {
					list.get(i).setI_grad_Id(backi_grad_Id);
				}
				asphalt_GraingdDao.addAsphalt_GradDetailed(list);
			}
		}catch (DataAccessException e1) {
			throw new BusinessException(e1);
		}
		
	
		return 0;
	}

	public int updateAsphalt_GradDetailed(Asphalt_Grading asphalt_Grading)throws BusinessException{
		
		try {
			asphalt_GraingdDao.updateAsphalt_GradDetailed(asphalt_Grading);
			asphalt_GraingdDao.deletAsphalt_GradDetailed(asphalt_Grading);
			List<Asphalt_GradDetailed> list = asphalt_Grading.getList();
			if(list != null) {
				for(int i = 0; i < list.size(); i++) {
					list.get(i).setI_grad_Id(asphalt_Grading.getI_id());
				}
				asphalt_GraingdDao.addAsphalt_GradDetailed(list);
			}
		} catch (DataAccessException e) {
			throw new BusinessException(e);
		}
		return 0;
	}

	public int deletAsphalt_Grading(Asphalt_Grading asphalt_Grading) throws BusinessException {
		try {
			asphalt_GraingdDao.deletAsphalt_Grading(asphalt_Grading);
			asphalt_GraingdDao.deletAsphalt_GradDetailed(asphalt_Grading);
		} catch (DataAccessException e) {
			throw new BusinessException(e);
		}
		return 0;
	}
	
	public List<Asphalt_Grading> select_Asph_TargetPropDetailed(Asphalt_Grading asphalt_Grading) throws BusinessException {
		// TODO Auto-generated method stub
		try {
			return  asphalt_GraingdDao.select_Asph_TargetPropDetailed(asphalt_Grading);
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
