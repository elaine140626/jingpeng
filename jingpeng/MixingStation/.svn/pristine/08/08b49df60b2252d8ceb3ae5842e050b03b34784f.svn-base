package com.mix.service.asphalt.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mix.dao.asphalt.AsphProportionDao;
import com.mix.model.Asph_TargetProportion;
import com.mix.model.Asphalt_Prod_Proportion;
import com.mix.service.asphalt.AsphProportionService;


@Service
@Transactional
public class AsphProportionServiceImpl implements AsphProportionService{

	
	@Autowired
	AsphProportionDao  asphProportionDao;
	
	public List<Asph_TargetProportion> getAsph_TargetProportion(Asph_TargetProportion asph_TargetProportion)  {
			return asphProportionDao.getAsph_TargetProportion(asph_TargetProportion);
	}

	public List<Asphalt_Prod_Proportion> getAsphalt_Prod_Proportion(Map<String, Object> map) {
			return asphProportionDao.getAsphalt_Prod_Proportion(map);
	}

	public int addAsphalt_Prod_Proportion(Asphalt_Prod_Proportion asphalt_Prod_Proportion)  {
			return 	 asphProportionDao.addAsphalt_Prod_Proportion(asphalt_Prod_Proportion);
	}
	
	public int updateAsphalt_Prod_Proportion(Asphalt_Prod_Proportion asphalt_Prod_Proportion)  {
			return asphProportionDao.updateAsphalt_Prod_Proportion(asphalt_Prod_Proportion);
	}
	
	public void deletAsphalt_Prod_Proportion(Asphalt_Prod_Proportion asphalt_Prod_Proportion) {
			asphProportionDao.deletAsphalt_Prod_Proportion(asphalt_Prod_Proportion);
	}

	public List<Asph_TargetProportion> getAsph_TargetProportionCode(Asph_TargetProportion asph_TargetProportion)  {
			return asphProportionDao.getAsph_TargetProportionCode(asph_TargetProportion);
	}

	public int addAsph_TargetProportion(Asph_TargetProportion asph_TargetProportion)  {
			return asphProportionDao.addAsph_TargetProportion(asph_TargetProportion);
	}

	public List<Asphalt_Prod_Proportion> getAsphalt_Prod_ProportionCode(Asphalt_Prod_Proportion asphalt_Prod_Proportion){
			return asphProportionDao.getAsphalt_Prod_ProportionCode(asphalt_Prod_Proportion);
	}

	public List<Asphalt_Prod_Proportion> getAsphalt_Prod_ProportionById(Map<String, Object> map){
			return asphProportionDao.getAsphalt_Prod_ProportionById(map);
	}

	public List<Map> getProduction_Plan(Asphalt_Prod_Proportion asphalt_Prod_Proportion)  {
			return asphProportionDao.getProduction_Plan(asphalt_Prod_Proportion);
	}
	public List<Asphalt_Prod_Proportion> getAsphalt_Prod_ProportionByGradId(Map<String, Object> map) {
			return asphProportionDao.getAsphalt_Prod_ProportionByGradId(map);
	}

	public List<Asph_TargetProportion> select_Asph_TargetPropDetailed(Asphalt_Prod_Proportion asphalt_Prod_Proportion)  {
			return asphProportionDao.select_Asph_TargetPropDetailed(asphalt_Prod_Proportion);
	}

	
}
