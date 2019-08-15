package com.oil.dao.system;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.oil.model.system.CachetCompany;

public interface CachetCompanyDao {

	List<CachetCompany> getCachetCompany(HashMap<String, Object> map);

	int addCachetCompany(CachetCompany cachetCompany);

	int updateCachetCompany(CachetCompany cachetCompany);

	int delCachetCompany(HashMap<String, Object> map);

	List<CachetCompany> findCachetCompanyByName(@Param("testCompany") String testCompany);

	int updateElectronicsName(HashMap<String, Object> map);

}
