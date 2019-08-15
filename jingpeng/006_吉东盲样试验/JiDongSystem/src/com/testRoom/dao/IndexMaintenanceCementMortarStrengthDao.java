package com.testRoom.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.testRoom.model.JudgingBasis04006;
import com.testRoom.model.JudgingBasis08001;
import com.testRoom.model.JudgingBasis0900101;
import com.testRoom.model.JudgingBasisDeleteEntity;
import com.testRoom.model.JudgingBasisTest10;

@Repository
public interface IndexMaintenanceCementMortarStrengthDao{
	/**
	 * tab1 水泥品种列表获取
	 * 
	**/
	public List<Map<String, Object>> getCementTypeList (Map<String, Object> map);
	
	/**
	 * tab1 水泥胶砂强度 List 
	 * 
	**/
	public List<Map<String, Object>> getCementMortarStrength (Map<String, Object> map);
	
	/**
	 * tab2 沥青三大指标 List 
	 * 
	**/
	public List<Map<String, Object>> getAsphalt (Map<String, Object> map);
	
	/**
	 * tab3 马歇尔 List 
	 * 
	**/
	public List<Map<String, Object>> getMarshall (Map<String, Object> map);
	
	/**
	 * tab4 钢筋抗拉强度、屈服强度、伸长率、冷弯试验 List 
	 * 
	**/
	public List<Map<String, Object>> getRebar (Map<String, Object> map);
	
	/**
	 * tab5 钢筋接头抗拉强度、冷弯试验  List 
	 * 
	**/
	public List<Map<String, Object>> getRebarJoint (Map<String, Object> map);
	
	/**
	 * tab1 水泥胶砂强度 重复数据判断
	 * 
	**/
	public List<Map<String, Object>> getCementMortarStrengthCount (JudgingBasis04006 judgingBasis04006);
	
	/**
	 * tab1 水泥胶砂强度 插入数据
	 * 
	**/
	public int addCementMortarStrength (JudgingBasis04006 judgingBasis04006);
	
	/**
	 * tab2 沥青三大指标 重复数据判断
	 * 
	**/
	public List<Map<String, Object>> getAsphaltCount (JudgingBasis08001 judgingBasis08001);
	
	/**
	 * tab2 沥青三大指标 插入数据
	 * 
	**/
	public int addAsphalt (JudgingBasis08001 judgingBasis08001);
	
	/**
	 * tab3  马歇尔 重复数据判断
	 * 
	**/
	public List<Map<String, Object>> getMarshallCount (JudgingBasis0900101 judgingBasis0900101);
	
	/**
	 * tab3   马歇尔 插入数据
	 * 
	**/
	public int addMarshall (JudgingBasis0900101 judgingBasis0900101);
	
	/**
	 * tab4  钢筋抗拉强度、屈服强度、伸长率、冷弯试验  && tab5 钢筋接头抗拉强度、冷弯试验  重复数据判断
	 * 
	**/
	public List<Map<String, Object>> getRebarCount (JudgingBasisTest10 judgingBasisTest10);
	
	/**
	 * tab4  钢筋抗拉强度、屈服强度、伸长率、冷弯试验   && tab5 钢筋接头抗拉强度、冷弯试验  插入数据
	 * 
	**/
	public int addRebar (JudgingBasisTest10 judgingBasisTest10);
	
	/**
	 * tab1 水泥胶砂强度 删除数据
	 * 
	**/
	public int updateCementMortarStrength (JudgingBasisDeleteEntity judgingBasisDeleteEntity);
	
	/**
	 * tab2 沥青三大指标 删除数据
	 * 
	**/
	public int updateAsphalt (JudgingBasisDeleteEntity judgingBasisDeleteEntity);
	
	/**
	 * tab3 马歇尔 删除数据
	 * 
	**/
	public int updateMarshall (JudgingBasisDeleteEntity judgingBasisDeleteEntity);
	
	/**
	 * tab4 钢筋抗拉强度、屈服强度、伸长率、冷弯试验 删除数据 
	 * tab5 钢筋接头抗拉强度、冷弯试验 
	 * 
	**/
	public int updateRebar (JudgingBasisDeleteEntity judgingBasisDeleteEntity);
	
}
