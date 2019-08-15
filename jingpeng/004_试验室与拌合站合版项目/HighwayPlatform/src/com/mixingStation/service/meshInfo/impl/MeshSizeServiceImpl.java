package com.mixingStation.service.meshInfo.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mixingStation.dao.meshInfo.MeshSizeDao;
import com.mixingStation.model.meshInfo.MeshSizeDataAnalysis;
import com.mixingStation.model.meshInfo.MeshSizeInfo;
import com.mixingStation.service.meshInfo.MeshSizeService;

@Service
@Transactional
public class MeshSizeServiceImpl implements MeshSizeService {

	@Autowired
	private MeshSizeDao meshSizeDao;
	
	@Override
	public List<MeshSizeInfo> queryMeshSizeList(MeshSizeInfo meshSize) {
		return meshSizeDao.queryMeshSizeList(meshSize);
	}

	@Override
	public int insertMeshSizeList(MeshSizeInfo meshSize) throws Exception {
		return meshSizeDao.insertMeshSizeList(meshSize);
	}

	@Override
	public int updateMeshSizeList(MeshSizeInfo meshSize) throws Exception {
		return meshSizeDao.updateMeshSizeList(meshSize);
	}

	@Override
	public int deleteMeshSizeList(MeshSizeInfo meshSize) throws Exception {
		return meshSizeDao.deleteMeshSizeList(meshSize);
	}

	@Override
	public List<Map<String, Object>> queryMeshSizeDataList(MeshSizeDataAnalysis meshSizeDataAnalysis) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		// 新建时，只返回第一列，key为“9999”，value为对应的仓号，后续如有加仓需求，则在对应位置添加仓
		if(meshSizeDataAnalysis.getAnalysisId() == 0) {
			for (int i = 0; i < 11; i++) {
				switch (i) {
				case 0:
					Map<String, Object> map1 = new HashMap<String, Object>();
					map1.put("9999", "1#仓");
					list.add(map1);
					break;
				case 1:
					Map<String, Object> map2 = new HashMap<String, Object>();
					map2.put("9999", "2#仓");
					list.add(map2);
					break;
				case 2:
					Map<String, Object> map3 = new HashMap<String, Object>();
					map3.put("9999", "3#仓");
					list.add(map3);
					break;
				case 3:
					Map<String, Object> map4 = new HashMap<String, Object>();
					map4.put("9999", "4#仓");
					list.add(map4);
					break;
				case 4:
					Map<String, Object> map5 = new HashMap<String, Object>();
					map5.put("9999", "5#仓");
					list.add(map5);
					break;
				case 5:
					Map<String, Object> map6 = new HashMap<String, Object>();
					map6.put("9999", "6#仓");
					list.add(map6);
					break;
				case 6:
					Map<String, Object> map7 = new HashMap<String, Object>();
					map7.put("9999", "冷粉仓1");
					list.add(map7);
					break;
				case 7:
					Map<String, Object> map8 = new HashMap<String, Object>();
					map8.put("9999", "冷粉仓2");
					list.add(map8);
					break;
				case 8:
					Map<String, Object> map9 = new HashMap<String, Object>();
					map9.put("9999", "热粉仓");
					list.add(map9);
					break;
				case 9:
					Map<String, Object> map10 = new HashMap<String, Object>();
					map10.put("9999", "上限");
					list.add(map10);
					break;
				case 10:
					Map<String, Object> map11 = new HashMap<String, Object>();
					map11.put("9999", "下限");
					list.add(map11);
					break;
				default:
					break;
				}
			}
		} else {
			// 修改时，要返回第一列以及该条级配信息的筛孔模板
			List<MeshSizeDataAnalysis> meshSizeDataAnalysisList = meshSizeDao.queryMeshSizeDataList(meshSizeDataAnalysis.getAnalysisId());
			for (int i = 0; i < 11; i++) {
				switch (i) {
				case 0:
					Map<String, Object> map1 = new HashMap<String, Object>();
					map1.put("9999", "1#仓");
					for (int j = 0; j < meshSizeDataAnalysisList.size(); j++) {
						map1.put(meshSizeDataAnalysisList.get(j).getMeshId() + "", meshSizeDataAnalysisList.get(j).getWare1());
					}
					list.add(map1);
					break;
				case 1:
					Map<String, Object> map2 = new HashMap<String, Object>();
					map2.put("9999", "2#仓");
					for (int j = 0; j < meshSizeDataAnalysisList.size(); j++) {
						map2.put(meshSizeDataAnalysisList.get(j).getMeshId() + "", meshSizeDataAnalysisList.get(j).getWare2());
					}
					list.add(map2);
					break;
				case 2:
					Map<String, Object> map3 = new HashMap<String, Object>();
					map3.put("9999", "3#仓");
					for (int j = 0; j < meshSizeDataAnalysisList.size(); j++) {
						map3.put(meshSizeDataAnalysisList.get(j).getMeshId() + "", meshSizeDataAnalysisList.get(j).getWare3());
					}
					list.add(map3);
					break;
				case 3:
					Map<String, Object> map4 = new HashMap<String, Object>();
					map4.put("9999", "4#仓");
					for (int j = 0; j < meshSizeDataAnalysisList.size(); j++) {
						map4.put(meshSizeDataAnalysisList.get(j).getMeshId() + "", meshSizeDataAnalysisList.get(j).getWare4());
					}
					list.add(map4);
					break;
				case 4:
					Map<String, Object> map5 = new HashMap<String, Object>();
					map5.put("9999", "5#仓");
					for (int j = 0; j < meshSizeDataAnalysisList.size(); j++) {
						map5.put(meshSizeDataAnalysisList.get(j).getMeshId() + "", meshSizeDataAnalysisList.get(j).getWare5());
					}
					list.add(map5);
					break;
				case 5:
					Map<String, Object> map6 = new HashMap<String, Object>();
					map6.put("9999", "6#仓");
					for (int j = 0; j < meshSizeDataAnalysisList.size(); j++) {
						map6.put(meshSizeDataAnalysisList.get(j).getMeshId() + "", meshSizeDataAnalysisList.get(j).getWare6());
					}
					list.add(map6);
					break;
				case 6:
					Map<String, Object> map7 = new HashMap<String, Object>();
					map7.put("9999", "冷粉仓1");
					for (int j = 0; j < meshSizeDataAnalysisList.size(); j++) {
						map7.put(meshSizeDataAnalysisList.get(j).getMeshId() + "", meshSizeDataAnalysisList.get(j).getColdWare1());
					}
					list.add(map7);
					break;
				case 7:
					Map<String, Object> map8 = new HashMap<String, Object>();
					map8.put("9999", "冷粉仓2");
					for (int j = 0; j < meshSizeDataAnalysisList.size(); j++) {
						map8.put(meshSizeDataAnalysisList.get(j).getMeshId() + "", meshSizeDataAnalysisList.get(j).getColdWare2());
					}
					list.add(map8);
					break;
				case 8:
					Map<String, Object> map9 = new HashMap<String, Object>();
					map9.put("9999", "热粉仓");
					for (int j = 0; j < meshSizeDataAnalysisList.size(); j++) {
						map9.put(meshSizeDataAnalysisList.get(j).getMeshId() + "", meshSizeDataAnalysisList.get(j).getHotWare());
					}
					list.add(map9);
					break;
				case 9:
					Map<String, Object> map10 = new HashMap<String, Object>();
					map10.put("9999", "上限");
					for (int j = 0; j < meshSizeDataAnalysisList.size(); j++) {
						map10.put(meshSizeDataAnalysisList.get(j).getMeshId() + "", meshSizeDataAnalysisList.get(j).getUpperLimit());
					}
					list.add(map10);
					break;
				case 10:
					Map<String, Object> map11 = new HashMap<String, Object>();
					map11.put("9999", "下限");
					for (int j = 0; j < meshSizeDataAnalysisList.size(); j++) {
						map11.put(meshSizeDataAnalysisList.get(j).getMeshId() + "", meshSizeDataAnalysisList.get(j).getLowerLimit());
					}
					list.add(map11);
					break;
				default:
					break;
				}
			}
		}
		return list;
	}

}
