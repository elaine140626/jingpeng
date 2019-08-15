package com.mix.service.asphalt.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mix.dao.asphalt.Asphalt_GradingDao;
import com.mix.model.Asph_TargetProportion;
import com.mix.model.Asphalt_GradDetailed;
import com.mix.model.Asphalt_Grading;
import com.mix.model.Asphalt_GradingModel;
import com.mix.service.asphalt.Asphalt_GradingService;

@Service
@Transactional
public class Asphalt_GradingServiceImpl implements Asphalt_GradingService {
	
	@Autowired
	Asphalt_GradingDao asphalt_GraingdDao;

	public List<Asphalt_GradingModel> getAsphalt_Gradings(Map<String, Object> map) {
			return asphalt_GraingdDao.getasphalt_Gradings(map);
	}
	
	public List<Asphalt_GradingModel> getAsphalt_GradingById(Asphalt_GradingModel asphalt_GradingModel) {
			return asphalt_GraingdDao.getAsphalt_GradingById(asphalt_GradingModel);
	}
	
	public List<Asphalt_GradingModel> getAsphalt_GradDetailedById(Asphalt_GradingModel asphalt_GradingModel) {
			return asphalt_GraingdDao.getAsphalt_GradDetailedById(asphalt_GradingModel);
	}
	
	public List<Asphalt_Grading> getAsphalt_Gradingcode(Asphalt_Grading asphalt_Grading)  {
			return asphalt_GraingdDao.getAsphalt_Gradingcode(asphalt_Grading);
	}

	public int addAsphalt_Grad(Asphalt_Grading asphalt_Grading)  {
		
		int backi_grad_Id;
			List<Asphalt_GradDetailed> list = asphalt_Grading.getList();
			asphalt_GraingdDao.addAsphalt_Grading(asphalt_Grading);
			List<Asphalt_Grading>  grad = asphalt_GraingdDao.getAsphalt_Gradingcode(asphalt_Grading);
			if(list != null) {
				for(int i = 0; i < list.size(); i++) {
					list.get(i).setI_grad_Id(grad.get(0).getI_id());
				}
				asphalt_GraingdDao.addAsphalt_GradDetailed(list);
			}
		return 0;
	}

	public int updateAsphalt_GradDetailed(Asphalt_Grading asphalt_Grading){
			asphalt_GraingdDao.updateAsphalt_GradDetailed(asphalt_Grading);
			asphalt_GraingdDao.deletAsphalt_GradDetailed(asphalt_Grading);
			List<Asphalt_GradDetailed> list = asphalt_Grading.getList();
			if(list != null) {
				for(int i = 0; i < list.size(); i++) {
					list.get(i).setI_grad_Id(asphalt_Grading.getI_id());
				}
				asphalt_GraingdDao.addAsphalt_GradDetailed(list);
			}
		return 0;
	}

	public int deletAsphalt_Grading(Asphalt_Grading asphalt_Grading)  {
			asphalt_GraingdDao.deletAsphalt_Grading(asphalt_Grading);
			asphalt_GraingdDao.deletAsphalt_GradDetailed(asphalt_Grading);
		return 0;
	}
	
	public List<Asphalt_Grading> select_Asph_TargetPropDetailed(Asphalt_Grading asphalt_Grading)  {
			return  asphalt_GraingdDao.select_Asph_TargetPropDetailed(asphalt_Grading);
	}
}
