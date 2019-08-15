package com.jingpeng.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jingpeng.dao.BlindSampleInfoDao;
import com.jingpeng.dao.OrganizationInfoDao;
import com.jingpeng.dao.UserInfoDao;
import com.jingpeng.model.BlindSampDetailed;
import com.jingpeng.model.BlindSampleInfo;
import com.jingpeng.model.QrCodeInfo;
import com.jingpeng.model.UserInfo;
import com.jingpeng.service.BlindSampleInfoService;
import com.jingpeng.service.OrganizationInfoService;
import com.jingpeng.service.UserInfoService;
import com.kdt.base.exception.BusinessException;
import com.kdt.base.exception.DataAccessException;

@Service
@Transactional
public class BlindSampleInfoServiceImpl implements BlindSampleInfoService{
	
	@Autowired
	private BlindSampleInfoDao blindSampleInfoDao;

	
    /*
     * (non-Javadoc)
     * @see com.jingpeng.service.BlindSampleInfoService#addBlindSampleInfo(com.jingpeng.model.BlindSampleInfo)
                * 添加盲样信息
     * tongn
     * 2018.7.16
     */
	public void addBlindSampleInfo(BlindSampleInfo blindSampleInfo) throws BusinessException {
		
		try {
				
				 blindSampleInfoDao.addBlindSampleInfo(blindSampleInfo);
										
			} catch (DataAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				throw new BusinessException(e);
			}
		
	}
		public List<BlindSampleInfo> getBlindSampleInfo(Map<String, Object> param) throws BusinessException {
		
		try {
			return blindSampleInfoDao.getBlindSampleInfo(param);
		} catch (DataAccessException e) {
			e.printStackTrace();
			throw new BusinessException(e);
		}
	}
	 /* @see com.jingpeng.service.BlindSampleInfoService#addBlindSampleInfo(com.jingpeng.model.BlindSampleInfo)
     * 查询盲样信息
     * lj
     * 2018.7.19
     */
	public List<Map<String, Object>> getBlindSampleInfo1(Map<String, Object> map) throws BusinessException {
		
		try {
			return blindSampleInfoDao.getBlindSampleInfo1(map);
		} catch (DataAccessException e) {
			e.printStackTrace();
			throw new BusinessException(e);
		}
	}
	 
	public int deleteBlindSampDetailed(Map<String, Object> param) throws BusinessException {
		
		try {
			
			 return blindSampleInfoDao.deleteBlindSampDetailed(param);
									
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new BusinessException(e);
		}
		
	}

	public int addBlindSampDetailed(List<BlindSampDetailed> blindSampDetailedList) throws BusinessException {
		
		try {
			
			return  blindSampleInfoDao.addBlindSampDetailed(blindSampDetailedList);
									
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new BusinessException(e);
		}
		
	}

	public int updateblindSampleInfo(Map<String, Object> param) throws BusinessException {
		
		try {
			
			return blindSampleInfoDao.updateblindSampleInfo(param);
									
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new BusinessException(e);
		}
		
		
	}

	public List<BlindSampDetailed> blindSampDetailed(Map<String, Object> param) throws BusinessException {
	try {
			
			return blindSampleInfoDao.blindSampDetailed(param);
									
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new BusinessException(e);
		}
	}

	/*
	 * 获取验证码信息
	 * 2018.7.17
	 * tongn
	 */
	public List<QrCodeInfo> getQrCodeInfo() throws BusinessException{
		
      try {
			
			return blindSampleInfoDao.getQrCodeInfo();
									
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new BusinessException(e);
		}
	}



	/*
	 * 获取验证码信息
	 * 2018.7.17
	 * tongn
	 */
	public List<Map<String, Object>> getypCode(Map<String, Object> param) throws BusinessException{
		
      try {
			
			return blindSampleInfoDao.getypCode(param);
									
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new BusinessException(e);
		}
	}
	
	public List<Map<String, Object>> getConstructionUnit(Map<String, Object> map) throws BusinessException {
		try {
			
			return blindSampleInfoDao.getConstructionUnit(map);
									
		} catch (DataAccessException e) {
			e.printStackTrace();
			throw new BusinessException(e);
		}
	}
	public List<Map<String, Object>> getEngineeringName(Map<String, Object> map) throws BusinessException {
		try {
			
			return blindSampleInfoDao.getEngineeringName(map);
									
		} catch (DataAccessException e) {
			e.printStackTrace();
			throw new BusinessException(e);
		}
}
}
