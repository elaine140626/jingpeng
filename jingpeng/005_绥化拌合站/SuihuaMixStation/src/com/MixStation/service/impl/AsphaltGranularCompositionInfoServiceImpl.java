package com.MixStation.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.MixStation.dao.AsphaltGranularCompositionInfoDao;
import com.MixStation.model.ApshaltMixProportionEntity;
import com.MixStation.model.AsphaltGradDetailedEntity;
import com.MixStation.model.AsphaltGradingInfoEntity;
import com.MixStation.model.DataTablesResponseInfo;
import com.MixStation.model.MeshSizeInfoEntity;
import com.MixStation.service.AsphaltGranularCompositionInfoService;
@Service
public class AsphaltGranularCompositionInfoServiceImpl implements AsphaltGranularCompositionInfoService {

	@Autowired
	private AsphaltGranularCompositionInfoDao asphaltGranularCompositionInfoDao;

	@Override
	public List<ApshaltMixProportionEntity> getApshaltMixProportion(Map<String, Object> map) {
		return asphaltGranularCompositionInfoDao.getApshaltMixProportion(map);
	}

	@Override
	public List<MeshSizeInfoEntity> getAllMeshSizeInfo(Map<String, Object> map) {
		return asphaltGranularCompositionInfoDao.getAllMeshSizeInfo(map);
	}

	@Override
	public DataTablesResponseInfo getAllAsphaltGradingInfo(Map<String, Object> map) {
		DataTablesResponseInfo dri = new DataTablesResponseInfo();
		List<AsphaltGradingInfoEntity> list = asphaltGranularCompositionInfoDao.getAllAsphaltGradingInfo(map);
		dri.setData(list);
		return dri;
	}

	@Override
	public int addAsphaltGradingInfo(AsphaltGradingInfoEntity param1,List<AsphaltGradDetailedEntity> param2) {
		int result = 0;
		//添加级配
		result += asphaltGranularCompositionInfoDao.addAsphaltGradingInfo(param1);
		//获取刚添加级配的id
		int id = asphaltGranularCompositionInfoDao.getAsphaltGradingInfoId(param1.getOperator());
		//增加筛孔通过率
		if(param2 != null && param2.size() > 0) {
			for (int i = 0; i < param2.size(); i++) {
				//设值级配id
				param2.get(i).setGradId(id);
				result += asphaltGranularCompositionInfoDao.insertAsphaltGradDetailed(param2.get(i));
			}
		}
		return result;
	}

	@Override
	public List<AsphaltGradDetailedEntity> getAsphaltGradDetailedByGradId(Map<String, Object> map) {
		return asphaltGranularCompositionInfoDao.getAsphaltGradDetailedByGradId(map);
	}

	@Override
	public List<AsphaltGradingInfoEntity> getAsphaltGradingInfoById(Map<String, Object> map) {
		return asphaltGranularCompositionInfoDao.getAsphaltGradingInfoById(map);
	}

	@Override
	public int updateAsphaltGradingInfo(AsphaltGradingInfoEntity param1, List<AsphaltGradDetailedEntity> param2) {
		int result = 0;
		//修改级配信息
		result += asphaltGranularCompositionInfoDao.upateAsphaltGradingInfo(param1);
		//删除筛孔通过率
		result += asphaltGranularCompositionInfoDao.deleteAsphaltGradDetailed(Integer.toString(param1.getId()));
		//添加筛孔通过率
		if(param2 != null && param2.size() > 0) {
			for (int i = 0; i < param2.size(); i++) {
				//设值级配id
				param2.get(i).setGradId(param1.getId());
				result += asphaltGranularCompositionInfoDao.insertAsphaltGradDetailed(param2.get(i));
			}
		}
		return result;
	}

	@Override
	public int deleteAsphaltGradingInfo(Map<String, Object> map) {
		int result = 0;
		//删除级配
		result += asphaltGranularCompositionInfoDao.deleteAsphaltGradDetailedInfo(map);
		//删除筛孔通过率
		result += asphaltGranularCompositionInfoDao.deleteAsphaltGradingInfo(map);
		return result;
	}

	@Override
	public int enableAsphaltGradingInfo(Map<String, Object> map) {
		int result = 0;
		//启动一个级配
		result += asphaltGranularCompositionInfoDao.enableAsphaltGradingInfo(map);
		//同一配比下的其他级配不启动
		result += asphaltGranularCompositionInfoDao.updateOtherEnable(map);
		return result;
	}

	@Override
	public String selectGradeCode(String gradeCode, String id, String orgId) {
		return asphaltGranularCompositionInfoDao.selectGradeCode(gradeCode, id, orgId);
	}

	@Override
	public List<ApshaltMixProportionEntity> getApshaltMixProportionUsed(Map<String, Object> map) {
		return asphaltGranularCompositionInfoDao.getApshaltMixProportionUsed(map);
	}
}
