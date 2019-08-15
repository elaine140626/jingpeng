package com.blindSample.service;


import java.util.List;
import java.util.Map;

import com.blindSample.model.JudgingBasisTest02;
import com.blindSample.model.ShiYan21DetailEntity;
import com.blindSample.model.ShiYan21Entity;
import com.blindSample.model.ShiYan22DetailEntity;
import com.blindSample.model.ShiYan2301DetailEntity;
import com.blindSample.model.ShiYan2302DetailEntity;
import com.blindSample.model.ShiYan24DetailEntity;
import com.blindSample.model.ShiYan28DetailEntity;

public interface ShiYan02Service {
	
	/**
	 * 试验21  粗集料筛分试验
	 * 
	**/
	public List<ShiYan21Entity> getShiYan21(Map<String, Object> map);
	
	/**
	 * 试验21明细  粗集料筛分试验
	 * 
	**/
	public List<ShiYan21DetailEntity> getShiYanDetail21(Map<String, Object> map);
	
	/**
	 * 试验25  细集料筛分试验
	 * 
	**/
	public List<ShiYan21Entity> getShiYan25(Map<String, Object> map);
	
	/**
	 * 试验25明细  细集料筛分试验
	 * 
	**/
	public List<ShiYan21DetailEntity> getShiYanDetail25(Map<String, Object> map);
	
	/**
	 * 试验22  粗集料含泥量试验
	 * 
	**/
	public List<ShiYan21Entity> getShiYan22(Map<String, Object> map);
	
	/**
	 * 试验22明细  粗集料含泥量试验
	 * 
	**/
	public List<ShiYan22DetailEntity> getShiYanDetail22(Map<String, Object> map);
	
	/**
	 * 试验26  细集料含泥量试验
	 * 
	**/
	public List<ShiYan21Entity> getShiYan26(Map<String, Object> map);
	
	/**
	 * 试验26明细  细集料含泥量试验
	 * 
	**/
	public List<ShiYan22DetailEntity> getShiYanDetail26(Map<String, Object> map);
	
	/**
	 * 试验23  粗集料针、片状颗粒含量试验
	 * 
	**/
	public List<ShiYan21Entity> getShiYan23(Map<String, Object> map);
	
	/**
	 * 试验23明细  粗集料针、片状颗粒含量试验(规准仪法)
	 * 
	**/
	public List<ShiYan2302DetailEntity> getShiYanDetail2302(Map<String, Object> map);
	
	/**
	 * 试验23明细  粗集料针、片状颗粒含量试验(游标卡尺法)
	 * 
	**/
	public List<ShiYan2301DetailEntity> getShiYanDetail2301(Map<String, Object> map);
	
	/**
	 * 试验24  粗集料压碎值试验
	 * 
	 **/
	public List<ShiYan21Entity> getShiYan24(Map<String, Object> map);

	/**
	 * 试验24明细  粗集料压碎值试验
	 * 
	 **/
	public List<ShiYan24DetailEntity> getShiYanDetail24(Map<String, Object> map);
	
	/**
	 * 试验27  水泥凝结时间 
	 * 
	**/
	public List<ShiYan21Entity> getShiYan27(Map<String, Object> map);
	
	/**
	 * 试验28   粗集料试验
	 * 
	 **/
	public List<ShiYan21Entity> getShiYan28(Map<String, Object> map);

	/**
	 * 试验28明细   粗集料试验
	 * 
	 **/
	public List<ShiYan28DetailEntity> getShiYanDetail28(Map<String, Object> map);
	
	/**
	 * 试验29   细集料试验
	 * 
	 **/
	public List<ShiYan21Entity> getShiYan29(Map<String, Object> map);

	/**
	 * 试验29明细   细集料试验
	 * 
	 **/
	public List<ShiYan28DetailEntity> getShiYanDetail29(Map<String, Object> map);
	
	/**
	 * 判定依据Test02集料取得  试验项目
	**/
	public List<JudgingBasisTest02> getQualification (Map<String, Object> map);
	
	/**
	 * 判定依据Test02集料取得  技术指标
	**/
	public List<Map<String,Object>> getPosition (Map<String, Object> map);
	
	/**
	 * 试验21 粗集料筛分试验 保存
	 **/
	public int saveShiYan21(ShiYan21Entity shiYan21Entity, String SerialNumber,
			List<ShiYan21DetailEntity> shiYan21DetailList);
	
	/**
	 * 试验25 细集料筛分试验 保存
	 **/
	public int saveShiYan25(ShiYan21Entity shiYan21Entity, String SerialNumber,
			List<ShiYan21DetailEntity> shiYan21DetailList);
	
	/**
	 * 试验22 粗集料含泥量试验 保存
	**/
	public int saveShiYan22(ShiYan21Entity shiYan21Entity,String SerialNumber,ShiYan22DetailEntity shiYan22DetailEntity);
	
	/**
	 * 试验26 细集料含泥量试验 保存
	**/
	public int saveShiYan26(ShiYan21Entity shiYan21Entity,String SerialNumber,ShiYan22DetailEntity shiYan22DetailEntity);
	
	/**
	 * 试验23 粗集料针、片状颗粒含量试验 保存
	 **/
	public int saveShiYan23(ShiYan21Entity shiYan21Entity, String SerialNumber,
			List<ShiYan2302DetailEntity> shiYan2302DetailList, List<ShiYan2301DetailEntity> shiYan2301DetailList);
	
	/**
	 * 试验24 粗集料压碎值试验 保存
	 **/
	public int saveShiYan24(ShiYan21Entity shiYan21Entity, String SerialNumber,
			ShiYan24DetailEntity shiYan24DetailEntity);
	
	/**
	 * 试验27 水泥凝结时间 保存
	**/
	public int saveShiYan27(ShiYan21Entity shiYan21Entity);
	
	/**
	 * 试验28 粗集料试验 保存
	 **/
	public int saveShiYan28(ShiYan21Entity shiYan21Entity, String SerialNumber,
			List<ShiYan28DetailEntity> shiYan28DetailList);
	
	/**
	 * 试验29 细集料试验 保存
	 **/
	public int saveShiYan29(ShiYan21Entity shiYan21Entity, String SerialNumber,
			List<ShiYan28DetailEntity> shiYan28DetailList);
}
