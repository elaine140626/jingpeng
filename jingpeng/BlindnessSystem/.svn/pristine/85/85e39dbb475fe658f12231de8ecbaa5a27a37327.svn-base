package com.jingpeng.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.jingpeng.model.BlindSampDetailed;
import com.jingpeng.model.BlindSampleInfo;
import com.jingpeng.model.QrCodeInfo;
import com.jingpeng.model.UserInfo;
import com.kdt.base.exception.DataAccessException;
import com.kdt.base.support.mybatis.impl.KDDaoSupport;

/*
  * 用户信息
 * tongn
 * 2018.7.16
 */
@Repository
public class BlindSampleInfoDao extends KDDaoSupport{
	
	private final static String NAMESPACE = "blindSampleInfo";


	/*
	 * 添加盲样信息
	 * tongn
	 * 2018.7.16
	 */
	public void addBlindSampleInfo(BlindSampleInfo blindSampleInfo) throws DataAccessException{
		
		 this.insert(NAMESPACE+".addBlindSampleInfo", blindSampleInfo);
	}
	
	public List<BlindSampleInfo> getBlindSampleInfo(Map<String, Object> param) throws DataAccessException{
		
		return this.select(NAMESPACE+".getBlindSampleInfo",param);
	}
	/*
	 * 查询盲样信息
	 * lj
	 * 2018.7.19
	 */
	public List<Map<String, Object>> getBlindSampleInfo1(Map<String, Object> map) throws DataAccessException{
		
		return this.select(NAMESPACE+".getBlindSampleInfo1",map);
	}

	public int deleteBlindSampDetailed(Map<String, Object> param) throws DataAccessException{
		
		return this.update(NAMESPACE+".deleteBlindSampDetailed",param);
		
	}

	public int addBlindSampDetailed(List<BlindSampDetailed> blindSampDetailedList)throws DataAccessException{
		
		return this.insert(NAMESPACE+".addBlindSampDetailed",blindSampDetailedList);
	}

	public int updateblindSampleInfo(Map<String, Object> param) throws DataAccessException{
		
		return this.update(NAMESPACE+".updateblindSampleInfo",param);
	}

	public List<BlindSampDetailed> blindSampDetailed(Map<String, Object> param) throws DataAccessException{
		
		return this.select(NAMESPACE+".blindSampDetailed",param);
	}

	/*
	 * 获取二维码信息
	 * tongn
	 * 2018.7.17
	 */
	public List<QrCodeInfo> getQrCodeInfo() throws DataAccessException{
		
		return this.select(NAMESPACE+".getQrCodeInfo",null);
	}
	
	/*
	 * 样品编号查询信息
	 * tongn
	 * 2018.7.17
	 */
	public List<Map<String, Object>> getypCode(Map<String, Object> param) throws DataAccessException{
		
		return this.select(NAMESPACE+".getypCode",param);
	}
	
	public List<Map<String, Object>> getConstructionUnit(Map<String, Object> map) throws DataAccessException {
		// TODO Auto-generated method stub
		return this.select(NAMESPACE+".getConstructionUnit",map);
	}

	public List<Map<String, Object>> getEngineeringName(Map<String, Object> map) throws DataAccessException {
		return this.select(NAMESPACE+".getEngineeringName",map);
	}

}