package com.oil.service.testreports.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.oil.dao.testreports.TestreportDao;
import com.oil.model.Userinfo;
import com.oil.model.testreports.Exportmeasure;
import com.oil.model.testreports.Storagemeasure;
import com.oil.model.testreports.Testreport;
import com.oil.model.testreports.Testreportsaledetailed;
import com.oil.service.testreports.TestreportService;

@Service
@Transactional
public class TestreportServiceImpl implements TestreportService{

	@Autowired
	private TestreportDao testreportDao;
	
	//检测员用户名称
	public List<Map<String, Object>> getTestreportName() {
		return testreportDao.getTestreportName();
	}

	//检测员信息
	public List<Map<String, Object>> getTestreportNameInfo(String telephones) {
		return testreportDao.getTestreportNameInfo(telephones);
	}

	// 添加检测报告单(出库单)
	public int addTestreportNameInfo(Testreport testreport,
			List<Testreportsaledetailed> testreportsaledetailedList) {
		int res = 0;
		int id = 0;
		//出库单检测报告单明细插入或修改,0是新增否则就是修改
		if(testreport.getId() == null) {
			res += testreportDao.insertTestreport(testreport);
			id = testreportDao.getTestreportId(testreport);
			testreport.setId(id);
		}else {
			res += testreportDao.updateTestreport(testreport);
		}
		
		// 检测报告单销售订单明细表插入或修改,0是新增否则就是修改
		if (testreportsaledetailedList != null && testreportsaledetailedList.size()>0) {
			testreportsaledetailedList.get(0).setTestReportId(testreport.getId());
			res += testreportDao.deleteTestreportByTestReportId(testreportsaledetailedList.get(0));
			for (int i = 0; i < testreportsaledetailedList.size(); i++) {
				testreportsaledetailedList.get(i).setTestReportId(testreport.getId());
					res += testreportDao.insertTestreportsaledetailed(testreportsaledetailedList.get(i));
			}
		}
		return res;
	}

	// 添加检测报告单(入库单)
	public int addTestreportNameInfos(Storagemeasure storagemeasure, Testreport testreport,
			List<Testreportsaledetailed> testreportsaledetailedList) {
		int res = 0;
		int id = 0;
		//出库单检测报告单明细插入或修改,0是新增否则就是修改
		if(testreport.getId() == null) {
			res += testreportDao.insertTestreport(testreport);
			id = testreportDao.getTestreportId(testreport);
			testreport.setId(id);
		}
		// 检测报告单销售订单明细表插入或修改,0是新增否则就是修改
		if (testreportsaledetailedList != null && testreportsaledetailedList.size()>0) {
			for (int i = 0; i < testreportsaledetailedList.size(); i++) {
				testreportsaledetailedList.get(i).setTestReportId(testreport.getId());
				if (testreportsaledetailedList.get(i).getId() == 0) {
					res += testreportDao.insertTestreportsaledetailed(testreportsaledetailedList.get(i));
				}else {
					res += testreportDao.updateTestreportsaledetailed(testreportsaledetailedList.get(i));
				}
			}
		}
		//查询检测报告单销售订单明细表所有关联数据只要有一项不合格,入库单检测报告单关联数据就代表不合格
		List<Testreportsaledetailed> dbTtestreportsaledetailedList = testreportDao.getTestreportsaledetailedList(id);
		if (dbTtestreportsaledetailedList != null && dbTtestreportsaledetailedList.size()>0) {
			for (int i = 0; i < dbTtestreportsaledetailedList.size(); i++) {
				if("1".equals(dbTtestreportsaledetailedList.get(i).getResultDetermine())) {
					testreport.setIsQualified(1);
					break;
				}else {
					testreport.setIsQualified(0);
				}
			}
		}
		//修改入库单检测报告单关联数据
		res += testreportDao.updateTestreport(testreport);
		storagemeasure.setTestReportId(testreport.getId());
		//修改入库单关联数据
		res += testreportDao.updateStoragemeasure(storagemeasure);
		return res;
	}
	
	// 查询出库单表数据
	public List<Exportmeasure> getExportmeasure(HashMap<String, Object> map) {
		return testreportDao.getExportmeasure(map);
	}

	// 查询入库单表数据
	public List<Storagemeasure> getStoragemeasure(HashMap<String, Object> map) {
		return testreportDao.getStoragemeasure(map);
	}

	// 用户表检测员获取
	public List<Userinfo> getUserinfo(HashMap<String, Object> map) {
		return testreportDao.getUserinfo(map);
	}

	// 出库单检测报告单明细(检测指标明细)
	public List<Testreportsaledetailed> getTestreportsaledetailed(HashMap<String, Object> map) {
		return testreportDao.getTestreportsaledetailed(map);
	}
	
	// 出库单检测报告单明细
	public List<Testreport> getTestreport(HashMap<String, Object> map) {
		return testreportDao.getTestreport(map);
	}

	// 删除报告单明细数据
	public int deleteTestreportsaledetailed(Map<String, Object> param) {
		Testreportsaledetailed testreportsaledetailed = new Testreportsaledetailed();
		testreportsaledetailed.setId(Integer.parseInt(param.get("testreportsaledetailedId").toString()));
		return testreportDao.deleteTestreportsaledetailed(testreportsaledetailed);
	}

	// 获取空白检测报告
	public List<Testreport> getTestreportList(Map<String, Object> map) {
		return testreportDao.getTestreportList(map);
	}

	// 赋值的报表list
	public List<Testreport> getTestreports() {
		return testreportDao.getTestreports();
	}
}
