package com.oil.dao.system;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.oil.model.system.CachetPersonnel;

public interface CachetPersonnelDao {

	List<CachetPersonnel> getCachetPersonnel(HashMap<String, Object> map);

	int addCachetPersonnel(CachetPersonnel cachetPersonnel);

	int updateCachetPersonnel(CachetPersonnel cachetPersonnel);

	int delCachetPersonnel(HashMap<String, Object> map);

	List<CachetPersonnel> findCachetPersonnelByName(@Param("cachetName") String cachetName);

	int updateElectronicsName(HashMap<String, Object> map);

}
