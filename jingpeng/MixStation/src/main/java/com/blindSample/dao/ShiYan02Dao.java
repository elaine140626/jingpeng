package com.blindSample.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.blindSample.model.JudgingBasisTest02;
import com.blindSample.model.ShiYan21DetailEntity;
import com.blindSample.model.ShiYan21Entity;
import com.blindSample.model.ShiYan22DetailEntity;
import com.blindSample.model.ShiYan2301DetailEntity;
import com.blindSample.model.ShiYan2302DetailEntity;
import com.blindSample.model.ShiYan24DetailEntity;
import com.blindSample.model.ShiYan28DetailEntity;

@Repository
public interface ShiYan02Dao{	
	/**
	 * 试验21  粗集料筛分试验
	**/
	public List<ShiYan21Entity> getShiYan21 (Map<String, Object> map);
	
	/**
	 * 试验21明细  粗集料筛分试验
	**/
	public List<ShiYan21DetailEntity> getShiYanDetail21 (Map<String, Object> map);
	
	/**
	 * 试验25  细集料筛分试验 
	**/
	public List<ShiYan21Entity> getShiYan25 (Map<String, Object> map);
	
	/**
	 * 试验25明细  细集料筛分试验
	**/
	public List<ShiYan21DetailEntity> getShiYanDetail25 (Map<String, Object> map);
	
	/**
	 * 试验22  粗集料含泥量试验
	**/
	public List<ShiYan21Entity> getShiYan22 (Map<String, Object> map);
	
	/**
	 * 试验22明细  粗集料含泥量试验
	**/
	public List<ShiYan22DetailEntity> getShiYanDetail22 (Map<String, Object> map);
	
	/**
	 * 试验26  细集料含泥量试验
	**/
	public List<ShiYan21Entity> getShiYan26 (Map<String, Object> map);
	
	/**
	 * 试验26明细  细集料含泥量试验
	**/
	public List<ShiYan22DetailEntity> getShiYanDetail26 (Map<String, Object> map);
	
	/**
	 * 试验23  粗集料针、片状颗粒含量试验 
	**/
	public List<ShiYan21Entity> getShiYan23 (Map<String, Object> map);
	
	/**
	 * 试验23明细  粗集料针、片状颗粒含量试验(规准仪法)
	**/
	public List<ShiYan2302DetailEntity> getShiYanDetail2302 (Map<String, Object> map);
	
	/**
	 * 试验23明细  粗集料针、片状颗粒含量试验(游标卡尺法)
	**/
	public List<ShiYan2301DetailEntity> getShiYanDetail2301 (Map<String, Object> map);
	
	/**
	 * 试验24  粗集料压碎值试验 
	**/
	public List<ShiYan21Entity> getShiYan24 (Map<String, Object> map);
	
	/**
	 * 试验24明细  粗集料压碎值试验
	**/
	public List<ShiYan24DetailEntity> getShiYanDetail24 (Map<String, Object> map);
	
	/**
	 * 试验27  水泥凝结时间 
	**/
	public List<ShiYan21Entity> getShiYan27 (Map<String, Object> map);
	
	/**
	 * 试验28  粗集料试验 
	**/
	public List<ShiYan21Entity> getShiYan28 (Map<String, Object> map);
	
	/**
	 * 试验28明细  粗集料试验
	**/
	public List<ShiYan28DetailEntity> getShiYanDetail28 (Map<String, Object> map);
	
	/**
	 * 试验29 细集料试验
	**/
	public List<ShiYan21Entity> getShiYan29 (Map<String, Object> map);
	
	/**
	 * 试验29明细  细集料试验
	**/
	public List<ShiYan28DetailEntity> getShiYanDetail29 (Map<String, Object> map);
	
	/**
	 * 判定依据Test02集料取得  试验项目
	**/
	public List<JudgingBasisTest02> getQualification (Map<String, Object> map);
	
	/**
	 * 判定依据Test02集料取得  技术指标
	**/
	public List<Map<String,Object>> getPosition (Map<String, Object> map);
	//**************************保存操作****************************************//
	/**
	 * 试验21~试验27 TestInfo更新 
	**/
	public int updateTestInfo(ShiYan21Entity shiYan21Entity);
	//**************************试验21 粗集料筛分试验****************************************//
	/**
	 * 试验21 粗集料筛分试验 t0表更新 
	**/
	public int updateTest0200101T0(ShiYan21Entity shiYan21Entity);
	
	/**
	 * 试验21 粗集料筛分试验 t1表删除 
	**/
	public int deleteTest0200101T01(String SerialNumber);
	
	/**
	 * 试验21 粗集料筛分试验 t1表插入
	**/
	public int insertTest0200101T01(ShiYan21DetailEntity shiYan21DetailEntity);
	//**************************试验25 细集料筛分试验****************************************//
	/**
	 * 试验25 细集料筛分试验 t0表更新 
	**/
	public int updateTest0200102T0(ShiYan21Entity shiYan21Entity);
	
	/**
	 * 试验25 细集料筛分试验 t1表删除 
	**/
	public int deleteTest0200102T01(String SerialNumber);
	
	/**
	 * 试验25 细集料筛分试验 t1表插入
	**/
	public int insertTest0200102T01(ShiYan21DetailEntity shiYan21DetailEntity);
	//**************************试验22 粗集料含泥量试验****************************************//
	/**
	 * 试验22 粗集料含泥量试验 t0表更新 
	**/
	public int updateTest02015T0(ShiYan21Entity shiYan21Entity);
	
	/**
	 * 试验22 粗集料含泥量试验 t1表删除 
	**/
	public int deleteTest02015T01(String SerialNumber);
	
	/**
	 * 试验22 粗集料含泥量试验 t1表插入
	**/
	public int insertTest02015T01(ShiYan22DetailEntity shiYan22DetailEntity);
	//**************************试验26 细集料含泥量试验****************************************//
	/**
	 * 试验26 细集料含泥量试验 t0表更新 
	**/
	public int updateTest02006T0(ShiYan21Entity shiYan21Entity);
	
	/**
	 * 试验26 细集料含泥量试验 t1表删除 
	**/
	public int deleteTest02006T01(String SerialNumber);
	
	/**
	 * 试验26 细集料含泥量试验 t1表插入
	**/
	public int insertTest02006T01(ShiYan22DetailEntity shiYan22DetailEntity);
	//**************************试验23 粗集料针、片状颗粒含量试验****************************************//
	/**
	 * 试验23 粗集料针、片状颗粒含量试验 t0表更新 
	**/
	public int updateTest02002T0(ShiYan21Entity shiYan21Entity);
	
	/**
	 * 试验23 粗集料针、片状颗粒含量试验(规准仪法) t1表删除 
	**/
	public int deleteTest02002T02(String SerialNumber);
	
	/**
	 * 试验23 粗集料针、片状颗粒含量试验(规准仪法) t1表插入
	**/
	public int insertTest02002T02(ShiYan2302DetailEntity shiYan2302DetailEntity);
	
	/**
	 * 试验23 粗集料针、片状颗粒含量试验(游标卡尺法) t1表删除 
	**/
	public int deleteTest02002T01(String SerialNumber);
	
	/**
	 * 试验23 粗集料针、片状颗粒含量试验(游标卡尺法) t1表插入
	**/
	public int insertTest02002T01(ShiYan2301DetailEntity shiYan2301DetailEntity);
	//**************************试验24 粗集料压碎值试验****************************************//
	/**
	 * 试验24 粗集料压碎值试验 t0表更新 
	**/
	public int updateTest0200301T0(ShiYan21Entity shiYan21Entity);
	
	/**
	 * 试验24 粗集料压碎值试验 t1表删除 
	**/
	public int deleteTest0200301T01(String SerialNumber);
	
	/**
	 * 试验24 粗集料压碎值试验 t1表插入
	**/
	public int insertTest0200301T01(ShiYan24DetailEntity shiYan24DetailEntity);
	//**************************试验27 水泥凝结时间****************************************//
	/**
	 * 试验27 水泥凝结时间 t0表更新 
	**/
	public int updateTest04003T0(ShiYan21Entity shiYan21Entity);	
	//**************************试验28 粗集料试验****************************************//
	/**
	 * 试验28 粗集料试验 t1表删除 
	**/
	public int deleteTest0201T01(String SerialNumber);
	
	/**
	 * 试验28 粗集料试验 t1表插入
	**/
	public int insertTest0201T01(ShiYan28DetailEntity shiYan28DetailEntity);	
	//**************************试验29 细集料试验****************************************//
	/**
	 * 试验29 细集料试验 t1表删除 
	**/
	public int deleteTest0202T01(String SerialNumber);
	
	/**
	 * 试验29 细集料试验 t1表插入
	**/
	public int insertTest0202T01(ShiYan28DetailEntity shiYan28DetailEntity);
}
