package com.mix.service.asphalt;

import java.util.List;
import java.util.Map;

import com.mix.model.Asph_TargetProportion;
import com.mix.model.Asphalt_GradDetailed;
import com.mix.model.Asphalt_Grading;
import com.mix.model.Asphalt_GradingModel;

public interface Asphalt_GradingService {
	
	public List<Asphalt_GradingModel> getAsphalt_Gradings(Map<String, Object> map) ;
	
	public List<Asphalt_GradingModel> getAsphalt_GradingById(Asphalt_GradingModel asphalt_GradingModel) ;

	public List<Asphalt_GradingModel> getAsphalt_GradDetailedById(Asphalt_GradingModel asphalt_GradingModel) ;

	public int addAsphalt_Grad(Asphalt_Grading asphalt_Grading);

	public List<Asphalt_Grading> getAsphalt_Gradingcode (Asphalt_Grading asphalt_Grading);
	
	public int updateAsphalt_GradDetailed(Asphalt_Grading asphalt_Grading);

	public int deletAsphalt_Grading(Asphalt_Grading asphalt_Grading);

	public List<Asphalt_Grading> select_Asph_TargetPropDetailed(Asphalt_Grading asphalt_Grading) ;

}
