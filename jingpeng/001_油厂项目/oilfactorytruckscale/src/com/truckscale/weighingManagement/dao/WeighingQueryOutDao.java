package com.truckscale.weighingManagement.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.truckscale.weighingManagement.model.NoweighEntity;
import com.truckscale.weighingManagement.model.Testreport;
import com.truckscale.weighingManagement.model.Testreportsaledetailed;
import com.truckscale.weighingManagement.model.WeighingQueryOutEntity;
import com.truckscale.weighingManagement.model.WeighingQueryOutPrint;



@Repository
public interface WeighingQueryOutDao {
	
	//查询称重列表
	List<WeighingQueryOutEntity> getWeighingQueryOut(Map<String, Object> param);
	//查询称重列表select
	List<WeighingQueryOutEntity> getWeighingQueryOutSelect(Map<String, Object> param);
    // 删除称重信息
    int deleteWeighingQueryOut(Map<String, Object> param);
    // 编辑称重信息
    int updateWeighingQueryOut(WeighingQueryOutEntity param);
	//查询出库单的打印信息
	List<WeighingQueryOutPrint> getWeighingQueryOutPrintInfo(Map<String, Object> param);
	//调拨出库单打印信息
	List<NoweighEntity> getNoweighEntityPrintInfo(Map<String, Object> param);
	//检测报告单信息
    List<Testreport> getTestreportInfo(Map<String,Object> map);
    //检测报告单销售订单明细表
    List<Testreportsaledetailed> getTestreportsaledetailedInfo(HashMap<String, Object> map);
    //获取电子公章检测单位表
    List<Map<String,Object>> getCachetCompany(Map<String,Object> map);
    //获取电子公章人员表
    List<Map<String,Object>> getCachetPersonnel(Map<String,Object> map);
    
	List<WeighingQueryOutEntity> ClientList(Map<String, Object> map);
	List<WeighingQueryOutEntity> PlateNumberList(Map<String, Object> map);
	List<WeighingQueryOutEntity> CompanyNameList(Map<String, Object> map);
	List<WeighingQueryOutEntity> Driver1List(Map<String, Object> map);
	List<WeighingQueryOutEntity> FleetNameList(Map<String, Object> map);
	List<WeighingQueryOutEntity> MaterielNameList(Map<String, Object> map);
	List<WeighingQueryOutEntity> MaterielModelList(Map<String, Object> map);
}