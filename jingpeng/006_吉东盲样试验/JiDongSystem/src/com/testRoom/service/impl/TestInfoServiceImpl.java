package com.testRoom.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.testRoom.dao.TestInfoDao;
import com.testRoom.service.TestInfoService;


@Service
@Transactional
public class TestInfoServiceImpl implements TestInfoService{
	@Autowired
	private TestInfoDao testInfoDao;
	public List<Map<String, Object>> getTestRoomName(Map<String, Object> map){
		return testInfoDao.getTestRoom(map);
	}
	
	public List<Map<String, Object>> getIndexSummary(Map<String, Object> map){
		return testInfoDao.getIndexSummary(map);
	}
	
	public List<Map<String, Object>> getTestSummary(Map<String, Object> map){
		return testInfoDao.getTestSummary(map);
	}
	
	public List<Map<String, Object>> getTestSummaryDetailed(Map<String, Object> map){
		return testInfoDao.getTestSummaryDetailed(map);
	}
	
	public List<Map<String, Object>> getTestSummaryDetailedNumber(Map<String, Object> map){
		return testInfoDao.getTestSummaryDetailedNumber(map);
	}
	public List<Map<String, Object>> getChart(Map<String, Object> map){
		return testInfoDao.getChart(map);
	}

	@Override
	public List<Map<String, Object>> getConcreteChartData(Map<String, Object> params) {
		return testInfoDao.getConcreteChartData(params);
	}

	@Override
	public List<Map<String, Object>> getMixtureChartData(Map<String, Object> params) {
		return testInfoDao.getMixtureChartData(params);
	}

	@Override
	public List<Map<String, Object>> getConcreteStrengthChartData(Map<String, Object> params) {
		List<Map<String,Object>> concreteStrengthChartList = testInfoDao.getConcreteStrengthChartData(params);
		List<Map<String,Object>> resultList = new ArrayList<Map<String,Object>>();
		Map<String, Object> map = new HashMap<String, Object>();
		// 占设计强度总和
		double sum = 0;
		double avg = 0;
		for (int i = 85; i <= 150; i += 5) {
			for (int j = 0; j < concreteStrengthChartList.size(); j++) {
				Double propDesignStrength = Double.valueOf(concreteStrengthChartList.get(j).get("propDesignStrength").toString());
				if (i == 85 && propDesignStrength <= i) {
					map.put("" + i, (map.get(i + "") == null ? 0 : Integer.valueOf(map.get(i + "").toString())) + 1);
				} else if (propDesignStrength > (i - 5) && propDesignStrength <= i) {
					map.put("" + i, (map.get(i + "") == null ? 0 : Integer.valueOf(map.get(i + "").toString())) + 1);
				}
				sum += propDesignStrength;
			}
			if (i == 85) {
				// 平均值
				avg = concreteStrengthChartList.size() == 0 ? 0 : sum / concreteStrengthChartList.size();
				map.put("avg", avg);
				double standard = 0;
				for (int j = 0; j < concreteStrengthChartList.size(); j++) {
					Double propDesignStrength = Double.valueOf(concreteStrengthChartList.get(j).get("propDesignStrength").toString());
					// 标准差
					standard += Math.sqrt((propDesignStrength - avg) * (propDesignStrength - avg));
				}
				map.put("standard", concreteStrengthChartList.size() == 0 ? 0 : (standard / (concreteStrengthChartList.size() - 1)));
			}
				
		}
		resultList.add(map);
		return resultList;
	}

	@Override
	public List<Map<String, Object>> queryAsphaltTypeCombobox(HashMap<String, Object> map) {
		return testInfoDao.queryAsphaltTypeCombobox(map);
	}

	@Override
	public List<Map<String, Object>> queryAsphaltGradeCombobox(HashMap<String, Object> map) {
		return testInfoDao.queryAsphaltGradeCombobox(map);
	}
	
	@Override
	public List<Map<String, Object>> queryMixtureTypeCombobox(HashMap<String, Object> map) {
		return testInfoDao.queryMixtureTypeCombobox(map);
	}

	@Override
	public List<Map<String, Object>> queryGradationTypeCombobox(HashMap<String, Object> map) {
		return testInfoDao.queryGradationTypeCombobox(map);
	}

	@Override
	public List<Map<String, Object>> queryAllChart(HashMap<String, Object> map) {
		return testInfoDao.queryAllChart(map);
	}

	@Override
	public Integer updateScreenDisplay(String[] chartArr, String displayType) {
		// 更改选择的展示规格
		Map<String, Object> map = new HashMap<String, Object>();
		String name = "";
		if ("4".equals(displayType)) {
			name = "2x2";
		} else if ("6".equals(displayType)) {
			name = "3x2";
		}
		map.put("code", "display_type");
		map.put("value", displayType);
		map.put("name", name);
		Integer result = testInfoDao.updateScreenDisplay(map);
		
		// 将数据库中所有选中图表记录状态变为N
		testInfoDao.deleteScreenDisplay();
		
		// 将选中的图表状态改为Y
		for (int i = 0; i < chartArr.length; i++) {
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("code", "chart_type");
			params.put("value", "Y");
			params.put("name", chartArr[i]);
			testInfoDao.updateScreenDisplay(params);
		}
		
		return result;
	}
}
