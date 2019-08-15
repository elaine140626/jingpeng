package com.mixingStation.service.asphalt.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.MixStation.model.DataTablesResponseInfo;
import com.alibaba.fastjson.JSONArray;
import com.mixingStation.dao.asphalt.GradationInfoDao;
import com.mixingStation.dao.meshInfo.MeshSizeDao;
import com.mixingStation.model.asphalt.AsphaltGrading;
import com.mixingStation.model.meshInfo.MeshSizeDataAnalysis;
import com.mixingStation.model.userInfo.UserInfo;
import com.mixingStation.service.asphalt.GradationInfoService;

import net.sf.json.JSONObject;


@Service
@Transactional
public class GradationInfoServiceImpl implements GradationInfoService {

	@Autowired
	private GradationInfoDao gradationInfoDao;
	@Autowired
	private MeshSizeDao meshSizeDao;
	
	@Override
	public int insertGradationInfo(Map<String, Object> params, UserInfo loginUser) {
		// 级配信息
		JSONObject jsonObject=JSONObject.fromObject(params.get("asphaltGrading"));
		AsphaltGrading asphaltGrading=(AsphaltGrading)JSONObject.toBean(jsonObject, AsphaltGrading.class);
		// 创建人
		asphaltGrading.setOperator(loginUser.getName());
		// 添加一条级配数据
		int result2 = gradationInfoDao.insertGradationInfo(asphaltGrading);	
		if (result2 < 1) {
			return 0;
		}
		
		// +++++++++++++++++++++++++++++++++++筛孔模板数据开始+++++++++++++++++++++++++++++++++++
		int result = this.insertMeshSizeDataAnalysis(loginUser, params, asphaltGrading);
		if (result == 0) {
			return 0;
		}
		// +++++++++++++++++++++++++++++++++++筛孔模板数据结束+++++++++++++++++++++++++++++++++++
		
		return 1;
	}

	/**
	 * @since 添加筛孔模板数据
	 * @param loginUser
	 * @param params
	 * @param asphaltGrading
	 * @return
	 */
	private int insertMeshSizeDataAnalysis(UserInfo loginUser, Map<String, Object> params, AsphaltGrading asphaltGrading) {
		// 获取筛孔列表选择和填写的数据集合，将json转换为list集合
		List<Map<String,Object>> list = (List<Map<String,Object>>)JSONArray.parse(params.get("tableArray").toString());
		// 获取选中的筛孔个数，因为在表格中筛孔不固定，但仓是固定的，所以需要进行类似行转列操作，将每一个筛孔对应的那一列的数据，变成一条数据存在数据库中
		// 因为map没有直接获取大小的方法，所以将map转换为list，故可得出选中的筛孔个数
		List<Integer> meshList = new ArrayList<Integer>();
		Map<String, Object> meshMap = list.get(0);
		for (Object value : meshMap.values()) { 
			meshList.add(Integer.valueOf(value.toString()));
		}
		// 因为仓是固定的，所以没真正的进行行转列操作，而是用switch判断是第几行的数据，来找到对应的仓进行赋值，若仓位后续再有增多或减少的需求，可以直接修改switch中的判断条件
		// 第一层循环是按照选择的筛孔来循环，第二层循环是按照仓位循环，目的是获取到该筛孔对应在每个仓中的值，将列表中的一列数据，变换为数据库中的一条数据
		for (int i = 0; i < meshList.size(); i++) {
			MeshSizeDataAnalysis meshSizeDataAnalysis = new MeshSizeDataAnalysis();
			// 组织机构id
			meshSizeDataAnalysis.setOrgId(loginUser.getPowerOrgID());
			// 级配id
			meshSizeDataAnalysis.setAnalysisId(asphaltGrading.getId());
			// 创建人
			meshSizeDataAnalysis.setOperator(loginUser.getName());
			// 创建时间
			meshSizeDataAnalysis.setCreateDate(new Date());
			for (int j = 1; j < list.size(); j++) {
				String result = list.get(j).get("mesh" + (i + 1)).toString();
				// 筛孔id
				meshSizeDataAnalysis.setMeshId(Integer.valueOf(list.get(0).get("mesh" + (i + 1)).toString()));
				switch (j) {
				case 1:
					// 1#仓
					meshSizeDataAnalysis.setWare1(result.equals("-") ? null : Double.valueOf(result));
					break;
				case 2:
					// 2#仓
					meshSizeDataAnalysis.setWare2(result.equals("-") ? null : Double.valueOf(result));
					break;
				case 3:
					// 3#仓
					meshSizeDataAnalysis.setWare3(result.equals("-") ? null : Double.valueOf(result));
					break;
				case 4:
					// 4#仓
					meshSizeDataAnalysis.setWare4(result.equals("-") ? null : Double.valueOf(result));
					break;
				case 5:
					// 5#仓
					meshSizeDataAnalysis.setWare5(result.equals("-") ? null : Double.valueOf(result));
					break;
				case 6:
					// 6#仓
					meshSizeDataAnalysis.setWare6(result.equals("-") ? null : Double.valueOf(result));
					break;
				case 7:
					// 冷粉仓
					meshSizeDataAnalysis.setColdWare1(result.equals("-") ? null : Double.valueOf(result));
					break;
				case 8:
					// 冷粉仓2
					meshSizeDataAnalysis.setColdWare2(result.equals("-") ? null : Double.valueOf(result));
					break;
				case 9:
					// 热粉仓
					meshSizeDataAnalysis.setHotWare(result.equals("-") ? null : Double.valueOf(result));
					break;
				case 10:
					// 上限
					meshSizeDataAnalysis.setUpperLimit(result.equals("-") ? null : Double.valueOf(result));
					break;
				case 11:
					// 下限
					meshSizeDataAnalysis.setLowerLimit(result.equals("-") ? null : Double.valueOf(result));
					break;
				default:
					break;
				}
			}
			// 添加一条数据
			int result = meshSizeDao.insertMeshSizeDataAnalysis(meshSizeDataAnalysis);
			if (result < 1) {
				return 0;
			}
		}
		return 1;
	}

	@Override
	public DataTablesResponseInfo queryList(Map<String, Object> params, UserInfo loginUser) {
		DataTablesResponseInfo info = new DataTablesResponseInfo();
		info.setData( gradationInfoDao.queryList(params));
		return info;
	}

	@Override
	public List<Map<String, Object>> getColumnModel(Map<String, Object> params, UserInfo loginUser) {
		params.put("orgId", loginUser.getPowerOrgID());
		return gradationInfoDao.getColumnModel(params);
	}

	@Override
	public AsphaltGrading queryDataById(Map<String, Object> params) {
		return gradationInfoDao.queryDataById(params);
	}

	@Override
	public int updateGradationInfo(Map<String, Object> params, UserInfo loginUser) {
		// 级配信息
		JSONObject jsonObject=JSONObject.fromObject(params.get("asphaltGrading"));
		AsphaltGrading asphaltGrading=(AsphaltGrading)JSONObject.toBean(jsonObject, AsphaltGrading.class);
		// 修改人
		asphaltGrading.setModifier(loginUser.getName());
		// 修改一条级配数据
		int result = gradationInfoDao.updateGradationInfo(asphaltGrading);
		if (result == 0) {
			return 0;
		}
		// 先删除筛孔模板数据
		meshSizeDao.deleteGradationInfo(asphaltGrading);
		// 重新添加筛孔模板数据
		int result2 = this.insertMeshSizeDataAnalysis(loginUser, params, asphaltGrading);
		if (result2 == 0) {
			return 0;
		}
		return 1;
	}

	@Override
	public int deleteGradationInfo(Map<String, Object> params, UserInfo loginUser) {
		AsphaltGrading asphaltGrading = new AsphaltGrading();
		// 级配id
		asphaltGrading.setId(Integer.valueOf(params.get("id").toString()));
		// 修改人
		asphaltGrading.setModifier(loginUser.getName());
		// 修改一条级配数据
		int result = gradationInfoDao.deleteGradationInfo(asphaltGrading);
		return result;
	}

}
