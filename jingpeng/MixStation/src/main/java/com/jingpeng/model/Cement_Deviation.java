package com.jingpeng.model;

import com.kdt.base.support.model.ModelSupport;

/**
 * 水泥偏差设定
 * 
 * @author Administrator
 *
 */
public class Cement_Deviation extends ModelSupport {
	private static final long serialVersionUID = -4155768403626890651L;
	private int id;
	private int org_Id;// 组织机构ID
	private double aggregate_Deviation;// 骨料仓偏差
	private double cement_Deviation;// 水泥仓偏差
	private double water_Deviation;// 水仓偏差
	private double admixture_Deviation;// 外掺剂仓偏差
	private double mixing_Time;// 拌合时间
	private String effective_Time;// 生效时间
	private String operator;// 创建人
	private String create_Date;// 创建日期
	private int valid_Flag;// 有效标识
	private int upload;// 上传标识

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getOrg_Id() {
		return org_Id;
	}

	public void setOrg_Id(int org_Id) {
		this.org_Id = org_Id;
	}

	public double getAggregate_Deviation() {
		return aggregate_Deviation;
	}

	public void setAggregate_Deviation(double aggregate_Deviation) {
		this.aggregate_Deviation = aggregate_Deviation;
	}

	public double getCement_Deviation() {
		return cement_Deviation;
	}

	public void setCement_Deviation(double cement_Deviation) {
		this.cement_Deviation = cement_Deviation;
	}

	public double getWater_Deviation() {
		return water_Deviation;
	}

	public void setWater_Deviation(double water_Deviation) {
		this.water_Deviation = water_Deviation;
	}

	public double getAdmixture_Deviation() {
		return admixture_Deviation;
	}

	public void setAdmixture_Deviation(double admixture_Deviation) {
		this.admixture_Deviation = admixture_Deviation;
	}

	public double getMixing_Time() {
		return mixing_Time;
	}

	public void setMixing_Time(double mixing_Time) {
		this.mixing_Time = mixing_Time;
	}

	public String getEffective_Time() {
		return effective_Time;
	}

	public void setEffective_Time(String effective_Time) {
		this.effective_Time = effective_Time;
	}

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	public String getCreate_Date() {
		return create_Date;
	}

	public void setCreate_Date(String create_Date) {
		this.create_Date = create_Date;
	}

	public int getValid_Flag() {
		return valid_Flag;
	}

	public void setValid_Flag(int valid_Flag) {
		this.valid_Flag = valid_Flag;
	}

	public int getUpload() {
		return upload;
	}

	public void setUpload(int upload) {
		this.upload = upload;
	}

}
