package com.blindSample.model;

//Test0900101沥青混合料马歇尔稳定度试验子表01
public class Test0900101T01 {

	private Long id;	//子表01ID
	private String serialnumber;	//主表流水号
	private Integer testserial;	//试验序号
	private String mineralname;	//矿料名称
	private Float mineralratio;	//矿料比例
	private double bvrelativedensity;	//矿料毛体积相对密度
	private double arelativedensity;	//矿料表观相对密度
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getSerialnumber() {
		return serialnumber;
	}
	public void setSerialnumber(String serialnumber) {
		this.serialnumber = serialnumber;
	}
	public Integer getTestserial() {
		return testserial;
	}
	public void setTestserial(Integer testserial) {
		this.testserial = testserial;
	}
	public String getMineralname() {
		return mineralname;
	}
	public void setMineralname(String mineralname) {
		this.mineralname = mineralname;
	}
	public Float getMineralratio() {
		return mineralratio;
	}
	public void setMineralratio(Float mineralratio) {
		this.mineralratio = mineralratio;
	}
	public double getBvrelativedensity() {
		return bvrelativedensity;
	}
	public void setBvrelativedensity(double bvrelativedensity) {
		this.bvrelativedensity = bvrelativedensity;
	}
	public double getArelativedensity() {
		return arelativedensity;
	}
	public void setArelativedensity(double arelativedensity) {
		this.arelativedensity = arelativedensity;
	}
	
	
}
