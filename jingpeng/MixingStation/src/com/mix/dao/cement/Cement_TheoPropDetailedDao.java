package com.mix.dao.cement;

import java.util.List;

import com.mix.model.Cement_TheoPropDetailed;
import com.mix.model.Cement_TheoryProportion;

public interface Cement_TheoPropDetailedDao {

	//水泥配比明细
	public List<Cement_TheoPropDetailed> getCement_TheoPropDetailedByTheoProp_ID(Cement_TheoryProportion cement_TheoryProportion);
	
	public List<Cement_TheoPropDetailed> getCement_TheoPropDetailed(Cement_TheoPropDetailed cement_TheoPropDetailed);
	
	public int addCement_TheoPropDetailed(List<Cement_TheoPropDetailed> list);
	
	public int updateCement_TheoPropDetailed(Cement_TheoPropDetailed cement_TheoPropDetailed);
	
	public void  deletCement_TheoPropDetailed(Cement_TheoPropDetailed cement_TheoPropDetailed);
}
