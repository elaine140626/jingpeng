package com.jingpeng.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.jingpeng.model.Left;
import com.jingpeng.model.LqAxis;
import com.jingpeng.model.LqBar;
import com.jingpeng.model.SnBar;
import com.jingpeng.model.SnSwAxis;
import com.jingpeng.model.SwBar;
import com.jingpeng.model.Tab;
import com.kdt.base.exception.DataAccessException;
import com.kdt.base.support.mybatis.impl.KDDaoSupport;

@Repository
public class BigScreenDao extends KDDaoSupport{
	private final static String NAMESPACE = "bigScreen";
	
	public List<Left> getLeft(Map<String, Object> map)  throws DataAccessException {
		return this.select(NAMESPACE+".getLeft", map);
	}
	
	public List<Tab> getPie(Map<String, Object> map)  throws DataAccessException {
		return this.select(NAMESPACE+".getPie", map);
	}
	
	public List<LqBar> getLqBar(Map<String, Object> map)  throws DataAccessException {
		return this.select(NAMESPACE+".getLqBar", map);
	}
	
	public List<SnBar> getSnBar(Map<String, Object> map)  throws DataAccessException {
		return this.select(NAMESPACE+".getSnBar", map);
	}
	
	public List<SwBar> getSwBar(Map<String, Object> map)  throws DataAccessException {
		return this.select(NAMESPACE+".getSwBar", map);
	}
	
	public List<LqAxis> getLqAxis(Map<String, Object> map)  throws DataAccessException {
		return this.select(NAMESPACE+".getLqAxis", map);
	}
	
	public List<SnSwAxis> getSnSwAxis(Map<String, Object> map)  throws DataAccessException {
		return this.select(NAMESPACE+".getSnSwAxis", map);
	}
	
	public List<Tab> getTab(Map<String, Object> map)  throws DataAccessException {
		return this.select(NAMESPACE+".getTab", map);
	}
	
	public List<Map<String, Object>> getMaps(Map<String, Object> map)  throws DataAccessException {
		return this.select(NAMESPACE+".getMaps", map);
	}
}
