package com.mixingStation.service.asphalt;

import java.util.List;
import java.util.Map;

import com.MixStation.model.DataTablesResponseInfo;
import com.mixingStation.model.asphalt.AsphaltGrading;
import com.mixingStation.model.userInfo.UserInfo;

public interface GradationInfoService {
	
	/**
	 * @since 新建级配信息
	 * @param params
	 * @param loginUser 
	 * @return
	 */
	int insertGradationInfo(Map<String, Object> params, UserInfo loginUser);

	/**
	 * @since 查询级配信息列表
	 * @param params
	 * @param loginUser 
	 * @return
	 */
	DataTablesResponseInfo queryList(Map<String, Object> params, UserInfo loginUser);

	/**
	 * @since 加载该条级配数据的筛孔模板列表的列
	 * @param params
	 * @param loginUser 
	 * @return
	 */
	List<Map<String, Object>> getColumnModel(Map<String, Object> params, UserInfo loginUser);
	
	/**
	 * @since 查询级配信息
	 * @param request
	 * @param params
	 * @return
	 */
	AsphaltGrading queryDataById(Map<String, Object> params);

	/**
	 * @since 修改级配信息
	 * @param request
	 * @param params
	 * @return
	 */
	int updateGradationInfo(Map<String, Object> params, UserInfo loginUser);

	/**
	 * @since 删除级配信息
	 * @param request
	 * @param params
	 * @return
	 */
	int deleteGradationInfo(Map<String, Object> params, UserInfo loginUser);

}
