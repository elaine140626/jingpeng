package com.oil.dao.carjournal;

import java.util.List;
import java.util.Map;

import com.oil.model.Carjournal;

public interface CarjournalDao {

	int addCarjournal(Carjournal carjournal);
	
	List<Map<String,Object>> getExportmeasureFactoryTime(Map<String,Object> map);
	
	int addExportmeasureFactoryTime(Map<String,Object> map);

}
