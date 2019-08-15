package com.jingpeng.model;

import com.kdt.base.support.model.ModelSupport;

public class Deviation_Asphalt extends ModelSupport{

	/**
	 * 沥青偏差设定表
	 * 
	 * 沥青偏差设定表：设定机构（例如拌合站）设定生产时允许偏差上下限范围
		使用范围： 生产采集数据与生产配比对比分析时判断各料仓数据是否超差时使用；
	 */
	private static final long serialVersionUID = -3360894575337723854L;

	private int i_id;//自增长ID
	
	private int i_org_Id;//组织机构ID
	
	private  double d_dev_Aggregate;//骨料仓偏差
	
	private  double d_dev_Powder;//粉料仓偏差
	
	private  double d_dev_Admixture;//外掺剂仓偏差
	
	private  double d_dev_Asphalt;//沥青仓偏差
	
	private String str_takeEffect_Time;//生效时间
	
	private String str_operator;//创建人
	
	private String str_create_Date;//创建日期
	
	private int i_valid_Flag;//有效标识
	
	private int i_upload;//上传标识
	
	private double d_mixTemperatureUp;//混合料温度上限
	
	private double d_mixTemperatureDown;//混合料温度下限

	public int getI_id() {
		return i_id;
	}

	public void setI_id(int i_id) {
		this.i_id = i_id;
	}

	public int getI_org_Id() {
		return i_org_Id;
	}

	public void setI_org_Id(int i_org_Id) {
		this.i_org_Id = i_org_Id;
	}

	public double getD_dev_Aggregate() {
		return d_dev_Aggregate;
	}

	public void setD_dev_Aggregate(double d_dev_Aggregate) {
		this.d_dev_Aggregate = d_dev_Aggregate;
	}

	public double getD_dev_Powder() {
		return d_dev_Powder;
	}

	public void setD_dev_Powder(double d_dev_Powder) {
		this.d_dev_Powder = d_dev_Powder;
	}

	public double getD_dev_Admixture() {
		return d_dev_Admixture;
	}

	public void setD_dev_Admixture(double d_dev_Admixture) {
		this.d_dev_Admixture = d_dev_Admixture;
	}

	public double getD_dev_Asphalt() {
		return d_dev_Asphalt;
	}

	public void setD_dev_Asphalt(double d_dev_Asphalt) {
		this.d_dev_Asphalt = d_dev_Asphalt;
	}

	public String getStr_takeEffect_Time() {
		return str_takeEffect_Time;
	}

	public void setStr_takeEffect_Time(String str_takeEffect_Time) {
		this.str_takeEffect_Time = str_takeEffect_Time;
	}

	public String getStr_operator() {
		return str_operator;
	}

	public void setStr_operator(String str_operator) {
		this.str_operator = str_operator;
	}

	public String getStr_create_Date() {
		return str_create_Date;
	}

	public void setStr_create_Date(String str_create_Date) {
		this.str_create_Date = str_create_Date;
	}

	public int getI_valid_Flag() {
		return i_valid_Flag;
	}

	public void setI_valid_Flag(int i_valid_Flag) {
		this.i_valid_Flag = i_valid_Flag;
	}

	public int getI_upload() {
		return i_upload;
	}

	public void setI_upload(int i_upload) {
		this.i_upload = i_upload;
	}

	public double getD_mixTemperatureUp() {
		return d_mixTemperatureUp;
	}

	public void setD_mixTemperatureUp(double d_mixTemperatureUp) {
		this.d_mixTemperatureUp = d_mixTemperatureUp;
	}

	public double getD_mixTemperatureDown() {
		return d_mixTemperatureDown;
	}

	public void setD_mixTemperatureDown(double d_mixTemperatureDown) {
		this.d_mixTemperatureDown = d_mixTemperatureDown;
	}
	
}
