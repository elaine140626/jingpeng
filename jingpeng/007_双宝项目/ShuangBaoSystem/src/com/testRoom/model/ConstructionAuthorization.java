package com.testRoom.model;

public class ConstructionAuthorization {
	private Integer id;	//自增长ID
	private String testroomname;	//试验室名称
	private String uniqueidentifier;	//试验室唯一标识
	private String mileage; 	//桩号
	private String span; 	//跨径
	private String bridgeName; 	//桥梁名称
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getTestroomname() {
		return testroomname;
	}
	public void setTestroomname(String testroomname) {
		this.testroomname = testroomname;
	}
	public String getUniqueidentifier() {
		return uniqueidentifier;
	}
	public void setUniqueidentifier(String uniqueidentifier) {
		this.uniqueidentifier = uniqueidentifier;
	}
	public String getMileage() {
		return mileage;
	}
	public void setMileage(String mileage) {
		this.mileage = mileage;
	}
	public String getSpan() {
		return span;
	}
	public void setSpan(String span) {
		this.span = span;
	}
	public String getBridgeName() {
		return bridgeName;
	}
	public void setBridgeName(String bridgeName) {
		this.bridgeName = bridgeName;
	}
	
}