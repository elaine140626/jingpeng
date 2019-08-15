package com.oil.service.testreports;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.oil.model.Userinfo;
import com.oil.model.testreports.Exportmeasure;
import com.oil.model.testreports.Storagemeasure;
import com.oil.model.testreports.Testreport;
import com.oil.model.testreports.Testreportsaledetailed;

public interface TestreportService {
	
	// 检测员用户名称
	List<Map<String, Object>> getTestreportName();
	
	// 检测员信息
	List<Map<String, Object>> getTestreportNameInfo(String telephones);
	
	// 添加检测报告单(出库单)
	int addTestreportNameInfo(Testreport testreport,List<Testreportsaledetailed> testreportsaledetailedList);

	// 查询出库单表数据
	List<Exportmeasure> getExportmeasure(HashMap<String, Object> map);

	// 出库单检测报告单明细(检测指标明细)
	List<Testreportsaledetailed> getTestreportsaledetailed(HashMap<String, Object> map);

	// 用户表检测员获取
	List<Userinfo> getUserinfo(HashMap<String, Object> map);
	
	// 出库单检测报告单明细
	List<Testreport> getTestreport(HashMap<String, Object> map);

	// 查询入库单表数据
	List<Storagemeasure> getStoragemeasure(HashMap<String, Object> map);

	// 添加检测报告单(入库单)
	int addTestreportNameInfos(Storagemeasure storagemeasure, Testreport testreport, List<Testreportsaledetailed> testreportsaledetailedList);

	// 删除报告单明细数据
	int deleteTestreportsaledetailed(Map<String, Object> param);
	
	// 获取空白检测报告
	List<Testreport> getTestreportList(Map<String, Object> map);
	
	// 复制的报表list
	List<Testreport> getTestreports();

}
