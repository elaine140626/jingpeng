package com.testRoom.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.testRoom.dao.TestReceviedDao;
import com.testRoom.model.SampleIntelligenceEntity;
import com.testRoom.model.TestCollectionEntity;
import com.testRoom.model.TestInfo;
import com.testRoom.model.TestPageInfos;
import com.testRoom.model.TestRoomInfo;
import com.testRoom.service.TestReceviedService;

@Service
@Transactional
public class TestReceviedServiceImpl implements TestReceviedService {

	@Autowired
	private TestReceviedDao testReceviedDao;

	/*
	 * 查询当前用户权限的实验室
	 */
	@Override
	public List<TestRoomInfo> getTestRoomList(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return testReceviedDao.getTestRoomList(map);
	}

	// 根据试验室名称获取相应的测试员
	public List<TestInfo> getTestOperatorList(Map<String, Object> map) {
		return testReceviedDao.getTestOperatorList(map);
	};

	// 获取试验名称
	public List<TestPageInfos> getTestNameList(Map<String, Object> map) {
		return testReceviedDao.getTestNameList(map);
	};

	// 获取试验收样明细数据list
	public List<TestCollectionEntity> getTestInfoList(Map<String, Object> map) {
		return testReceviedDao.getTestInfoList(map);
	};

	// 根据二维码判断试验是否已经收样
	public int getExistFlag(Map<String, Object> map) {
		return testReceviedDao.getExistFlag(map).get(0);
	};

	// 根据二维码获取相应收样信息
	public List<SampleIntelligenceEntity> getSampleIntelligence(Map<String, Object> map) {
		return testReceviedDao.getSampleIntelligence(map);
	}

	// 扫码后保存信息
	public int addTestInfo(Map<String, Object> map) {
		// 获取画面显示的试验信息
		List<SampleIntelligenceEntity> list = testReceviedDao.getSampleIntelligence(map);
		// 检索条件设置
		Map<String, Object> param = new HashMap<String, Object>();
		String uniqueIdentifiers = map.get("uniqueIdentifier").toString();
		if(("").equals(uniqueIdentifiers)) {
			param.put("uniqueIdentifier", uniqueIdentifiers);
		}else {
			String[] uniqueIdentifierList = uniqueIdentifiers.split(",");
			param.put("uniqueIdentifier", uniqueIdentifierList);
		}
		
		// 获取试验室前缀
		String prefix = testReceviedDao.getTestRoomList(param).get(0).getPrefix();
		int result = 0;
		if (list != null && list.size() > 0) {
			// 试验室前缀
			map.put("prefix", prefix);
			// 试验流水号
			map.put("serialnumber", list.get(0).getTesttable());
			// 试验名称id
			map.put("testnameid", list.get(0).getTestNameId());
			// 样品编号/名称
			map.put("samplecode", list.get(0).getSampleName());
			// 试验日期
			map.put("testdate", list.get(0).getMolddate());
			// 试验流程
			map.put("testrules", list.get(0).getTestrules());
			// 试验个数
			map.put("samplecount", list.get(0).getSampleCount());
			// 试验状态
			map.put("teststate", "1");
			// 采集方式
			map.put("collectionstate", list.get(0).getIsautotest());
			// 是否盲样试验
			map.put("istestblind", true);

			// 水泥胶砂强度
			if (("Test04006T0").equals(list.get(0).getTesttable())) {
				result = this.addTest04006T0(list.get(0), map);
			} else if (("Test0500101T0").equals(list.get(0).getTesttable())) {
				// 水泥混凝土抗压强度（立方体）
				result = this.addTest0500101T0(list.get(0), map);
			} else if (("Test0500102T0").equals(list.get(0).getTesttable())) {
				// 砂浆抗压强度试验
				result = this.addTest0500102T0(list.get(0), map);
			} else if (("Test07003T0").equals(list.get(0).getTesttable())) {
				// 无机结合料稳定材料无侧限抗压强度试验
				result = this.addTest07003Test0(list.get(0), map);
			} else if (("Test08001T0").equals(list.get(0).getTesttable())) {
				// 沥青三大指标试验
				result = this.addTest08001T0(list.get(0), map);
			} else if (("Test0900101T0").equals(list.get(0).getTesttable())) {
				// 沥青混合料马歇尔稳定度试验
				result = this.addTest0900101T0(list.get(0), map);
			} else if (("Test1000101T0").equals(list.get(0).getTesttable())) {
				// 钢筋抗拉强度、屈服强度、伸长率、冷弯试验信息
				result = this.addTest1000101T0(list.get(0), map);
			} else if (("Test1000201T0").equals(list.get(0).getTesttable())) {
				// 钢筋接头抗拉强度、冷弯试验
				result = this.addTest1000201T0(list.get(0), map);
			} else if (("Test0200101T0").equals(list.get(0).getTesttable())) {
				// 粗集料筛分试验
				result = this.addTest0200101T0(list.get(0), map);
			} else if (("Test0200102T0").equals(list.get(0).getTesttable())) {
				// 细集料筛分试验
				result = this.addTest0200102T0(list.get(0), map);
			} else if (("Test02015T0").equals(list.get(0).getTesttable())) {
				// 粗集料含泥量试验
				result = this.addTest02015T0(list.get(0), map);
			} else if (("Test02006T0").equals(list.get(0).getTesttable())) {
				// 细集料含泥量试验
				result = this.addTest02006T0(list.get(0), map);
			} else if (("Test02002T0").equals(list.get(0).getTesttable())) {
				// 粗集料针、片状颗粒含量试验
				result = this.addTest02002T0(list.get(0), map);
			} else if (("Test0200301T0").equals(list.get(0).getTesttable())) {
				// 粗集料压碎值试验
				result = this.addTest0200301T0(list.get(0), map);
			} else if (("Test04003T0").equals(list.get(0).getTesttable())) {
				// 水泥凝结时间
				result = this.addTest04003T0(list.get(0), map);
			} else if (("Test0201T0").equals(list.get(0).getTesttable())) {
				// 粗集料试验
				result = this.addTest0201T0(list.get(0), map);
			} else if (("Test0202T0").equals(list.get(0).getTesttable())) {
				// 细集料试验
				result = this.addTest0202T0(list.get(0), map);
			}
		}

		// 子表插入成功
		if (result > 0) {
			result = testReceviedDao.addTestInfo(map);
		}
		return result;
	};

	// 添加水泥胶砂强度信息
	public int addTest04006T0(SampleIntelligenceEntity sampleIntelligenceEntity, Map<String, Object> map) {
		// 水泥品种
		map.put("cementtype", sampleIntelligenceEntity.getCement_type());
		// 水泥强度等级
		map.put("cementstrengthgrade", sampleIntelligenceEntity.getCement_strength_grade());
		// 龄期
		map.put("age", sampleIntelligenceEntity.getAge());
		return testReceviedDao.addTest04006T0(map);
	}

	// 添加水泥混凝土抗压强度（立方体）信息
	public int addTest0500101T0(SampleIntelligenceEntity sampleIntelligenceEntity, Map<String, Object> map) {
		// 设计强度
		map.put("designstrength", sampleIntelligenceEntity.getDesignstrength());
		// 试件尺寸(mm)
		map.put("samplesize", sampleIntelligenceEntity.getSamplesize());
		return testReceviedDao.addTest0500101T0(map);
	}

	// 添加砂浆抗压强度试验信息
	public int addTest0500102T0(SampleIntelligenceEntity sampleIntelligenceEntity, Map<String, Object> map) {
		// 设计强度
		map.put("designstrength", sampleIntelligenceEntity.getDesignstrength());
		// 试件数量
		map.put("sampleamount", sampleIntelligenceEntity.getSampleAmount());
		return testReceviedDao.addTest0500102T0(map);
	}

	// 添加无机结合料稳定材料无侧限抗压强度试验信息
	public int addTest07003Test0(SampleIntelligenceEntity sampleIntelligenceEntity, Map<String, Object> map) {
		// 时间类型
		map.put("spectype", sampleIntelligenceEntity.getSpectype());
		// 设计强度
		map.put("designstrth", sampleIntelligenceEntity.getDesignstrength());
		return testReceviedDao.addTest07003Test0(map);
	}

	// 添加沥青三大指标试验信息
	public int addTest08001T0(SampleIntelligenceEntity sampleIntelligenceEntity, Map<String, Object> map) {
		// 沥青种类
		map.put("asphalttype", sampleIntelligenceEntity.getAsphalttype());
		return testReceviedDao.addTest08001T0(map);
	}

	// 添加沥青混合料马歇尔稳定度试验信息
	public int addTest0900101T0(SampleIntelligenceEntity sampleIntelligenceEntity, Map<String, Object> map) {
		// 混合料种类
		map.put("mixturetype", sampleIntelligenceEntity.getMixturetype());
		// 级配类型
		map.put("gradationtype", sampleIntelligenceEntity.getGradationtype());
		return testReceviedDao.addTest0900101T0(map);
	}

	// 添加钢筋抗拉强度、屈服强度、伸长率、冷弯试验信息
	public int addTest1000101T0(SampleIntelligenceEntity sampleIntelligenceEntity, Map<String, Object> map) {
		// 强度等级或牌号
		map.put("strengthgrade", sampleIntelligenceEntity.getStrength_grade());
		// 直径
		map.put("diameter", sampleIntelligenceEntity.getDiameter());
		return testReceviedDao.addTest1000101T0(map);
	}

	// 添加钢筋接头抗拉强度、冷弯试验
	public int addTest1000201T0(SampleIntelligenceEntity sampleIntelligenceEntity, Map<String, Object> map) {
		// 强度等级或牌号
		map.put("strengthgrade", sampleIntelligenceEntity.getStrength_grade());
		// 直径
		map.put("diameter", sampleIntelligenceEntity.getDiameter());
		return testReceviedDao.addTest1000201T0(map);
	}

	// 粗集料筛分试验
	public int addTest0200101T0(SampleIntelligenceEntity sampleIntelligenceEntity, Map<String, Object> map) {
		map.put("aggregate_type", sampleIntelligenceEntity.getAggregate_type());
		map.put("aggregate_specification", sampleIntelligenceEntity.getAggregate_specification());
		return testReceviedDao.addTest0200101T0(map);
	};

	// 细集料筛分试验
	public int addTest0200102T0(SampleIntelligenceEntity sampleIntelligenceEntity, Map<String, Object> map) {
		map.put("aggregate_type", sampleIntelligenceEntity.getAggregate_type());
		map.put("aggregate_specification", sampleIntelligenceEntity.getAggregate_specification());
		return testReceviedDao.addTest0200102T0(map);
	};

	// 粗集料含泥量试验
	public int addTest02015T0(SampleIntelligenceEntity sampleIntelligenceEntity, Map<String, Object> map) {
		map.put("aggregate_type", sampleIntelligenceEntity.getAggregate_type());
		map.put("aggregate_specification", sampleIntelligenceEntity.getAggregate_specification());
		return testReceviedDao.addTest02015T0(map);
	};

	// 细集料含泥量试验
	public int addTest02006T0(SampleIntelligenceEntity sampleIntelligenceEntity, Map<String, Object> map) {
		map.put("aggregate_type", sampleIntelligenceEntity.getAggregate_type());
		map.put("aggregate_specification", sampleIntelligenceEntity.getAggregate_specification());
		return testReceviedDao.addTest02006T0(map);
	};

	// 粗集料针、片状颗粒含量试验
	public int addTest02002T0(SampleIntelligenceEntity sampleIntelligenceEntity, Map<String, Object> map) {
		map.put("aggregate_type", sampleIntelligenceEntity.getAggregate_type());
		map.put("aggregate_specification", sampleIntelligenceEntity.getAggregate_specification());
		map.put("test_method", sampleIntelligenceEntity.getTest_method());
		return testReceviedDao.addTest02002T0(map);
	};

	// 粗集料压碎值试验
	public int addTest0200301T0(SampleIntelligenceEntity sampleIntelligenceEntity, Map<String, Object> map) {
		map.put("aggregate_type", sampleIntelligenceEntity.getAggregate_type());
		map.put("aggregate_specification", sampleIntelligenceEntity.getAggregate_specification());
		return testReceviedDao.addTest0200301T0(map);
	};

	// 水泥凝结时间
	public int addTest04003T0(SampleIntelligenceEntity sampleIntelligenceEntity, Map<String, Object> map) {
		map.put("cementtype", sampleIntelligenceEntity.getCement_type());
		map.put("cementstrengthgrade", sampleIntelligenceEntity.getCement_strength_grade());
		return testReceviedDao.addTest04003T0(map);
	};

	// 粗集料试验
	public int addTest0201T0(SampleIntelligenceEntity sampleIntelligenceEntity, Map<String, Object> map) {
		map.put("aggregate_type", sampleIntelligenceEntity.getAggregate_type());
		map.put("aggregate_specification", sampleIntelligenceEntity.getAggregate_specification());
		return testReceviedDao.addTest0201T0(map);
	};

	// 细集料试验
	public int addTest0202T0(SampleIntelligenceEntity sampleIntelligenceEntity, Map<String, Object> map) {
		map.put("aggregate_type", sampleIntelligenceEntity.getAggregate_type());
		map.put("aggregate_specification", sampleIntelligenceEntity.getAggregate_specification());
		return testReceviedDao.addTest0202T0(map);
	};
}
