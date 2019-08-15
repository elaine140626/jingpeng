package com.testRoom.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.testRoom.model.ShiYan01DetailEntity;
import com.testRoom.model.ShiYan01Entity;
import com.testRoom.model.ShiYan02DetailEntity;
import com.testRoom.model.ShiYan02Entity;
import com.testRoom.model.ShiYan03DetailEntity;
import com.testRoom.model.ShiYan03Entity;
import com.testRoom.model.ShiYan04DetailEntity;
import com.testRoom.model.ShiYan04Entity;
import com.testRoom.model.ShiYan0501DetailEntity;
import com.testRoom.model.ShiYan0502DetailEntity;
import com.testRoom.model.ShiYan05Entity;
import com.testRoom.model.ShiYan06DetailEntity;
import com.testRoom.model.ShiYan06Entity;
import com.testRoom.model.ShiYan07DetailEntity;
import com.testRoom.model.ShiYan07Entity;
import com.testRoom.model.ShiYan08DetailEntity;
import com.testRoom.model.ShiYan08Entity;

@Repository
public interface ShiYan01Dao{	
	/**
	 * 试验01
	 * 
	**/
	public List<ShiYan01Entity> getShiYan01 (Map<String, Object> map);
	
	/**
	 * 试验01明细
	 * 
	**/
	public List<ShiYan01DetailEntity> getShiYanDetail01 (Map<String, Object> map);
	
	/**
	 * 试验02
	 * 
	**/
	public List<ShiYan02Entity> getShiYan02 (Map<String, Object> map);
	
	/**
	 * 试验02明细
	 * 
	**/
	public List<ShiYan02DetailEntity> getShiYanDetail02 (Map<String, Object> map);
	
	/**
	 * 试验03
	 * 
	**/
	public List<ShiYan03Entity> getShiYan03 (Map<String, Object> map);
	
	/**
	 * 试验03明细
	 * 
	**/
	public List<ShiYan03DetailEntity> getShiYanDetail03 (Map<String, Object> map);
	
	/**
	 * 试验04
	 * 
	**/
	public List<ShiYan04Entity> getShiYan04 (Map<String, Object> map);
	
	/**
	 * 试验04明细
	 * 
	**/
	public List<ShiYan04DetailEntity> getShiYanDetail04 (Map<String, Object> map);
	
	/**
	 * 试验05
	 * 
	**/
	public List<ShiYan05Entity> getShiYan05 (Map<String, Object> map);
	
	/**
	 * 试验0501明细
	 * 
	**/
	public List<ShiYan0501DetailEntity> getShiYanDetail0501 (Map<String, Object> map);
	
	/**
	 * 试验0502明细
	 * 
	**/
	public List<ShiYan0502DetailEntity> getShiYanDetail0502 (Map<String, Object> map);
	
	/**
	 * 试验06
	 * 
	**/
	public List<ShiYan06Entity> getShiYan06 (Map<String, Object> map);
	
	/**
	 * 试验06明细
	 * 
	**/
	public List<ShiYan06DetailEntity> getShiYanDetail06 (Map<String, Object> map);
	
	/**
	 * 试验07
	 * 
	**/
	public List<ShiYan07Entity> getShiYan07 (Map<String, Object> map);
	
	/**
	 * 试验07明细
	 * 
	**/
	public List<ShiYan07DetailEntity> getShiYanDetail07 (Map<String, Object> map);
	
	/**
	 * 试验08
	 * 
	**/
	public List<ShiYan08Entity> getShiYan08 (Map<String, Object> map);
	
	/**
	 * 试验08明细
	 * 
	**/
	public List<ShiYan08DetailEntity> getShiYanDetail08 (Map<String, Object> map);
	
}
