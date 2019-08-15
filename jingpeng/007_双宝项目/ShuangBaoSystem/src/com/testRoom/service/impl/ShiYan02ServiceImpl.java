package com.testRoom.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.testRoom.dao.ShiYan02Dao;
import com.testRoom.model.JudgingBasisTest02;
import com.testRoom.model.ShiYan21DetailEntity;
import com.testRoom.model.ShiYan21Entity;
import com.testRoom.model.ShiYan22DetailEntity;
import com.testRoom.model.ShiYan2301DetailEntity;
import com.testRoom.model.ShiYan2302DetailEntity;
import com.testRoom.model.ShiYan24DetailEntity;
import com.testRoom.model.ShiYan28DetailEntity;
import com.testRoom.service.ShiYan02Service;

@Service
@Transactional
public class ShiYan02ServiceImpl implements ShiYan02Service {

	@Autowired
	ShiYan02Dao shiYan02Dao;

	/**
	 * 试验21 粗集料筛分试验
	 * 
	 **/
	public List<ShiYan21Entity> getShiYan21(Map<String, Object> map) {
		return shiYan02Dao.getShiYan21(map);
	}

	/**
	 * 试验21明细 粗集料筛分试验
	 * 
	 **/
	public List<ShiYan21DetailEntity> getShiYanDetail21(Map<String, Object> map) {
		return shiYan02Dao.getShiYanDetail21(map);
	}

	/**
	 * 试验25 细集料筛分试验
	 * 
	 **/
	public List<ShiYan21Entity> getShiYan25(Map<String, Object> map) {
		return shiYan02Dao.getShiYan25(map);
	}

	/**
	 * 试验25明细 细集料筛分试验
	 * 
	 **/
	public List<ShiYan21DetailEntity> getShiYanDetail25(Map<String, Object> map) {
		return shiYan02Dao.getShiYanDetail25(map);
	}

	/**
	 * 试验22 粗集料含泥量试验
	 * 
	 **/
	public List<ShiYan21Entity> getShiYan22(Map<String, Object> map) {
		return shiYan02Dao.getShiYan22(map);
	}

	/**
	 * 试验22明细 粗集料含泥量试验
	 * 
	 **/
	public List<ShiYan22DetailEntity> getShiYanDetail22(Map<String, Object> map) {
		return shiYan02Dao.getShiYanDetail22(map);
	}

	/**
	 * 试验26 细集料含泥量试验
	 * 
	 **/
	public List<ShiYan21Entity> getShiYan26(Map<String, Object> map) {
		return shiYan02Dao.getShiYan26(map);
	}

	/**
	 * 试验26明细 细集料含泥量试验
	 * 
	 **/
	public List<ShiYan22DetailEntity> getShiYanDetail26(Map<String, Object> map) {
		return shiYan02Dao.getShiYanDetail26(map);
	}

	/**
	 * 试验23 粗集料针、片状颗粒含量试验
	 * 
	 **/
	public List<ShiYan21Entity> getShiYan23(Map<String, Object> map) {
		return shiYan02Dao.getShiYan23(map);
	}

	/**
	 * 试验23明细 粗集料针、片状颗粒含量试验(规准仪法)
	 * 
	 **/
	public List<ShiYan2302DetailEntity> getShiYanDetail2302(Map<String, Object> map) {
		return shiYan02Dao.getShiYanDetail2302(map);
	}

	/**
	 * 试验23明细 粗集料针、片状颗粒含量试验(游标卡尺法)
	 * 
	 **/
	public List<ShiYan2301DetailEntity> getShiYanDetail2301(Map<String, Object> map) {
		return shiYan02Dao.getShiYanDetail2301(map);
	}
	
	/**
	 * 试验24  粗集料压碎值试验
	 * 
	 **/
	public List<ShiYan21Entity> getShiYan24(Map<String, Object> map) {
		return shiYan02Dao.getShiYan24(map);
	}

	/**
	 * 试验24明细  粗集料压碎值试验
	 * 
	 **/
	public List<ShiYan24DetailEntity> getShiYanDetail24(Map<String, Object> map) {
		return shiYan02Dao.getShiYanDetail24(map);
	}

	/**
	 * 试验27 水泥凝结时间
	 * 
	 **/
	public List<ShiYan21Entity> getShiYan27(Map<String, Object> map) {
		return shiYan02Dao.getShiYan27(map);
	}
	
	/**
	 * 试验28   粗集料试验
	 * 
	 **/
	public List<ShiYan21Entity> getShiYan28(Map<String, Object> map) {
		return shiYan02Dao.getShiYan28(map);
	}

	/**
	 * 试验28明细   粗集料试验
	 * 
	 **/
	public List<ShiYan28DetailEntity> getShiYanDetail28(Map<String, Object> map) {
		return shiYan02Dao.getShiYanDetail28(map);
	}
	
	/**
	 * 试验29   细集料试验
	 * 
	 **/
	public List<ShiYan21Entity> getShiYan29(Map<String, Object> map) {
		return shiYan02Dao.getShiYan29(map);
	}

	/**
	 * 试验29明细   细集料试验
	 * 
	 **/
	public List<ShiYan28DetailEntity> getShiYanDetail29(Map<String, Object> map) {
		return shiYan02Dao.getShiYanDetail29(map);
	}
	
	/**
	 * 判定依据Test02集料取得  试验项目
	**/
	public List<JudgingBasisTest02> getQualification (Map<String, Object> map){
		return shiYan02Dao.getQualification(map);
	}
	
	/**
	 * 判定依据Test02集料取得  技术指标
	**/
	public List<Map<String,Object>> getPosition (Map<String, Object> map){
		return shiYan02Dao.getPosition(map);
	}
	// *************************试验21 粗集料筛分试验 保存***********************//
	/**
	 * 试验21 粗集料筛分试验 保存
	 **/
	public int saveShiYan21(ShiYan21Entity shiYan21Entity, String SerialNumber,
			List<ShiYan21DetailEntity> shiYan21DetailList) {
		int result = 0;
		// TestInfo更新
		result = result + shiYan02Dao.updateTestInfo(shiYan21Entity);
		// t0表更新
		result = result + shiYan02Dao.updateTest0200101T0(shiYan21Entity);
		// t1表删除
		result = result + shiYan02Dao.deleteTest0200101T01(SerialNumber);
		// t1表插入
		if (shiYan21DetailList!=null && shiYan21DetailList.size()>0) {
			for (int i = 0; i < shiYan21DetailList.size(); i++) {
				result = result + shiYan02Dao.insertTest0200101T01(shiYan21DetailList.get(i));
			}
		}
		return result;
	}	
	// *************************试验25 细集料筛分试验 保存***********************//
	/**
	 * 试验25 细集料筛分试验 保存
	 **/
	public int saveShiYan25(ShiYan21Entity shiYan21Entity, String SerialNumber,
			List<ShiYan21DetailEntity> shiYan21DetailList) {
		int result = 0;
		// TestInfo更新
		result = result + shiYan02Dao.updateTestInfo(shiYan21Entity);
		// t0表更新
		result = result + shiYan02Dao.updateTest0200102T0(shiYan21Entity);
		// t1表删除
		result = result + shiYan02Dao.deleteTest0200102T01(SerialNumber);
		// t1表插入
		if (shiYan21DetailList!=null && shiYan21DetailList.size()>0) {
			for (int i = 0; i < shiYan21DetailList.size(); i++) {
				result = result + shiYan02Dao.insertTest0200102T01(shiYan21DetailList.get(i));
			}
		}				
		return result;
	}
	// *************************试验22 粗集料含泥量试验 保存***********************//
	/**
	 * 试验22 粗集料含泥量试验 保存
	 **/
	public int saveShiYan22(ShiYan21Entity shiYan21Entity, String SerialNumber,
			ShiYan22DetailEntity shiYan22DetailEntity) {
		int result = 0;
		// TestInfo更新
		result = result + shiYan02Dao.updateTestInfo(shiYan21Entity);
		// t0表更新
		result = result + shiYan02Dao.updateTest02015T0(shiYan21Entity);
		// t1表删除
		result = result + shiYan02Dao.deleteTest02015T01(SerialNumber);
		// t1表插入
		result = result + shiYan02Dao.insertTest02015T01(shiYan22DetailEntity);
		return result;
	}

	// *************************试验26 细集料含泥量试验 保存***********************//
	/**
	 * 试验26 细集料含泥量试验 保存
	 **/
	public int saveShiYan26(ShiYan21Entity shiYan21Entity, String SerialNumber,
			ShiYan22DetailEntity shiYan22DetailEntity) {
		int result = 0;
		// TestInfo更新
		result = result + shiYan02Dao.updateTestInfo(shiYan21Entity);
		// t0表更新
		result = result + shiYan02Dao.updateTest02006T0(shiYan21Entity);
		// t1表删除
		result = result + shiYan02Dao.deleteTest02006T01(SerialNumber);
		// t1表插入
		result = result + shiYan02Dao.insertTest02006T01(shiYan22DetailEntity);
		return result;
	}

	// *************************试验23 粗集料针、片状颗粒含量试验 保存***********************//
	/**
	 * 试验23 粗集料针、片状颗粒含量试验 保存
	 **/
	public int saveShiYan23(ShiYan21Entity shiYan21Entity, String SerialNumber,
			List<ShiYan2302DetailEntity> shiYan2302DetailList, List<ShiYan2301DetailEntity> shiYan2301DetailList) {
		int result = 0;
		// TestInfo更新
		result = result + shiYan02Dao.updateTestInfo(shiYan21Entity);
		// t0表更新
		result = result + shiYan02Dao.updateTest02002T0(shiYan21Entity);
		// 规准仪法
		if ("a".equals(shiYan21Entity.getTest_Method())) {
			// t1表删除
			result = result + shiYan02Dao.deleteTest02002T02(SerialNumber);
			// t1表插入
			if (shiYan2302DetailList!=null && shiYan2302DetailList.size()>0) {
				for (int i = 0; i < shiYan2302DetailList.size(); i++) {
					result = result + shiYan02Dao.insertTest02002T02(shiYan2302DetailList.get(i));
				}
			}		
		} else {
			// 游标卡尺法
			// t1表删除
			result = result + shiYan02Dao.deleteTest02002T01(SerialNumber);
			// t1表插入
			if (shiYan2301DetailList!=null && shiYan2301DetailList.size()>0) {
				for (int i = 0; i < shiYan2301DetailList.size(); i++) {
					result = result + shiYan02Dao.insertTest02002T01(shiYan2301DetailList.get(i));
				}
			}			
		}
		return result;
	}
	
	// *************************试验24 粗集料压碎值试验 保存***********************//
	/**
	 * 试验24 粗集料压碎值试验 保存
	 **/
	public int saveShiYan24(ShiYan21Entity shiYan21Entity, String SerialNumber,
			ShiYan24DetailEntity shiYan24DetailEntity) {
		int result = 0;
		// TestInfo更新
		result = result + shiYan02Dao.updateTestInfo(shiYan21Entity);
		// t0表更新
		result = result + shiYan02Dao.updateTest0200301T0(shiYan21Entity);
		// t1表删除
		result = result + shiYan02Dao.deleteTest0200301T01(SerialNumber);
		// t1表插入
		result = result + shiYan02Dao.insertTest0200301T01(shiYan24DetailEntity);
		return result;
	}

	// *************************试验27 水泥凝结时间 保存***********************//
	/**
	 * 试验27 水泥凝结时间 保存
	 **/
	public int saveShiYan27(ShiYan21Entity shiYan21Entity) {
		int result = 0;
		// TestInfo更新
		result = result + shiYan02Dao.updateTestInfo(shiYan21Entity);
		// t0表更新
		result = result + shiYan02Dao.updateTest04003T0(shiYan21Entity);
		return result;
	}
	
	//**************************试验28 粗集料试验****************************************//
	/**
	 * 试验28 粗集料试验 保存
	 **/
	public int saveShiYan28(ShiYan21Entity shiYan21Entity, String SerialNumber,
			List<ShiYan28DetailEntity> shiYan28DetailList) {
		int result = 0;
		// TestInfo更新
		result = result + shiYan02Dao.updateTestInfo(shiYan21Entity);
		// t1表删除
		result = result + shiYan02Dao.deleteTest0201T01(SerialNumber);
		// t1表插入
		if (shiYan28DetailList!=null && shiYan28DetailList.size()>0) {
			for (int i = 0; i < shiYan28DetailList.size(); i++) {
				result = result + shiYan02Dao.insertTest0201T01(shiYan28DetailList.get(i));
			}
		}	
		return result;
	}

	//**************************试验29 细集料试验****************************************//
	/**
	 * 试验29 细集料试验 保存
	 **/
	public int saveShiYan29(ShiYan21Entity shiYan21Entity, String SerialNumber,
			List<ShiYan28DetailEntity> shiYan28DetailList) {
		int result = 0;
		// TestInfo更新
		result = result + shiYan02Dao.updateTestInfo(shiYan21Entity);
		// t1表删除
		result = result + shiYan02Dao.deleteTest0202T01(SerialNumber);
		// t1表插入
		if (shiYan28DetailList!=null && shiYan28DetailList.size()>0) {
			for (int i = 0; i < shiYan28DetailList.size(); i++) {
				result = result + shiYan02Dao.insertTest0202T01(shiYan28DetailList.get(i));
			}
		}	
		return result;
	}
}
