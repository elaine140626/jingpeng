package com.oil.dao.testreports;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.oil.model.Userinfo;
import com.oil.model.testreports.Exportmeasure;
import com.oil.model.testreports.Storagemeasure;
import com.oil.model.testreports.Testreport;
import com.oil.model.testreports.Testreportsaledetailed;

public interface TestreportDao {
	// 检测员用户名称
	List<Map<String, Object>> getTestreportName();
	
	// 检测员信息(电话检索)
	List<Map<String, Object>> getTestreportNameInfo(@Param(value="telephones")String telephones);

	// 获取检测报告单的id
	Integer getTestreportId(Testreport testreport);

	// 添加检测报告单(出库单)
	int insertTestreport(Testreport testreport);
	
	// 添加检测报告单明细(出库单)
	int insertTestreportsaledetailed(Testreportsaledetailed testreportsaledetailed);

	// 获取检测报告单明细(出库单)
	List<Testreportsaledetailed> getTestreportsaledetailedList(int id);

	// 修改检测报告单(出库单)
	int updateTestreport(Testreport testreport);

	// 更新出库单的报告单号
	int updateExportmeasure(Exportmeasure exportmeasure);

	// 查询出库单表数据
	List<Exportmeasure> getExportmeasure(HashMap<String, Object> map);

	// 出库单检测报告单明细(检测指标明细)
	List<Testreportsaledetailed> getTestreportsaledetailed(HashMap<String, Object> map);
	
	// 用户表检测员获取
	List<Userinfo> getUserinfo(HashMap<String, Object> map);

	// 出库单检测报告单明细
	List<Testreport> getTestreport(HashMap<String, Object> map);

	// 更新检测报告单明细
	int updateTestreportsaledetailed(Testreportsaledetailed testreportsaledetailed);

	// 查询入库单表数据
	List<Storagemeasure> getStoragemeasure(HashMap<String, Object> map);

	// 更新入库单的报告单号
	int updateStoragemeasure(Storagemeasure storagemeasure);

	// 删除检测报告单明细数据
	int deleteTestreportsaledetailed(Testreportsaledetailed testreportsaledetailed);
	
	// 获取空白检测报告
	List<Testreport> getTestreportList(Map<String, Object> map);
	
	// 赋值的报表list
	List<Testreport> getTestreports();
	
	// 删除检测报告明细（彻底删除）
	int deleteTestreportByTestReportId(Testreportsaledetailed testreportsaledetailed);

}
