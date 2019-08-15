package com.blindSample.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.blindSample.dao.ShiYan01Dao;
import com.blindSample.model.ShiYan01DetailEntity;
import com.blindSample.model.ShiYan01Entity;
import com.blindSample.model.ShiYan02DetailEntity;
import com.blindSample.model.ShiYan02Entity;
import com.blindSample.model.ShiYan04DetailEntity;
import com.blindSample.model.ShiYan04Entity;
import com.blindSample.model.ShiYan0501DetailEntity;
import com.blindSample.model.ShiYan0502DetailEntity;
import com.blindSample.model.ShiYan05Entity;
import com.blindSample.model.ShiYan06DetailEntity;
import com.blindSample.model.ShiYan06Entity;
import com.blindSample.model.ShiYan07DetailEntity;
import com.blindSample.model.ShiYan07Entity;
import com.blindSample.model.ShiYan08DetailEntity;
import com.blindSample.model.ShiYan08Entity;
import com.blindSample.service.ShiYan01Service;

@Service
@Transactional
public class ShiYan01ServiceImpl implements ShiYan01Service {
	
	@Autowired
	ShiYan01Dao shiYan01Dao;

	/**
	 * 试验01
	 * 
	**/
	public List<ShiYan01Entity> getShiYan01(Map<String, Object> map){
		return shiYan01Dao.getShiYan01(map);
	}
	
	/**
	 * 试验01明细
	 * 
	**/
	public List<ShiYan01DetailEntity> getShiYanDetail01(Map<String, Object> map){
		return shiYan01Dao.getShiYanDetail01(map);
	}
	
	/**
	 * 试验02
	 * 
	**/
	public List<ShiYan02Entity> getShiYan02(Map<String, Object> map){
		return shiYan01Dao.getShiYan02(map);
	}
	
	/**
	 * 试验02明细
	 * 
	**/
	public List<ShiYan02DetailEntity> getShiYanDetail02(Map<String, Object> map){
		return shiYan01Dao.getShiYanDetail02(map);
	}
	
	/**
	 * 试验04
	 * 
	**/
	public List<ShiYan04Entity> getShiYan04(Map<String, Object> map){
		return shiYan01Dao.getShiYan04(map);
	}
	
	/**
	 * 试验04明细
	 * 
	**/
	public List<ShiYan04DetailEntity> getShiYanDetail04(Map<String, Object> map){
		return shiYan01Dao.getShiYanDetail04(map);
	}
	
	/**
	 * 试验05
	 * 
	**/
	public List<ShiYan05Entity> getShiYan05(Map<String, Object> map){
		return shiYan01Dao.getShiYan05(map);
	}
	
	/**
	 * 试验0501明细
	 * 
	**/
	public List<ShiYan0501DetailEntity> getShiYanDetail0501(Map<String, Object> map){
		return shiYan01Dao.getShiYanDetail0501(map);
	}
	
	/**
	 * 试验0502明细
	 * 
	**/
	public List<ShiYan0502DetailEntity> getShiYanDetail0502(Map<String, Object> map){
		return shiYan01Dao.getShiYanDetail0502(map);
	}
	
	/**
	 * 试验06
	 * 
	**/
	public List<ShiYan06Entity> getShiYan06(Map<String, Object> map){
		return shiYan01Dao.getShiYan06(map);
	}
	
	/**
	 * 试验06明细
	 * 
	**/
	public List<ShiYan06DetailEntity> getShiYanDetail06(Map<String, Object> map){
		return shiYan01Dao.getShiYanDetail06(map);
	}
	
	/**
	 * 试验07
	 * 
	**/
	public List<ShiYan07Entity> getShiYan07(Map<String, Object> map){
		return shiYan01Dao.getShiYan07(map);
	}
	
	/**
	 * 试验07明细
	 * 
	**/
	public List<ShiYan07DetailEntity> getShiYanDetail07(Map<String, Object> map){
		return shiYan01Dao.getShiYanDetail07(map);
	}
	
	/**
	 * 试验08
	 * 
	**/
	public List<ShiYan08Entity> getShiYan08(Map<String, Object> map){
		return shiYan01Dao.getShiYan08(map);
	}
	
	/**
	 * 试验08明细
	 * 
	**/
	public List<ShiYan08DetailEntity> getShiYanDetail08(Map<String, Object> map){
		return shiYan01Dao.getShiYanDetail08(map);
	}

}