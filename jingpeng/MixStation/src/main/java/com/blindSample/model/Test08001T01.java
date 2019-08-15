package com.blindSample.model;

//Test08001沥青三大指标(针入度)子表01
public class Test08001T01 {

	private Long id;	//子表01ID
	private String serialnumber;	//主表流水号
	private String specimen_no;	//试件编号
	private Float temperature;	//试验温度
	private Float testload;	//试验荷载
	private Float testtime;	//试验时间
	private Float penetration1;	//针入度1
	private Float penetration2;	//针入度2
	private Float penetration3;	//针入度3
	private Double penetration;	//针入度
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
	public String getSpecimen_no() {
		return specimen_no;
	}
	public void setSpecimen_no(String specimen_no) {
		this.specimen_no = specimen_no;
	}
	public Float getTemperature() {
		return temperature;
	}
	public void setTemperature(Float temperature) {
		this.temperature = temperature;
	}
	public Float getTestload() {
		return testload;
	}
	public void setTestload(Float testload) {
		this.testload = testload;
	}
	public Float getTesttime() {
		return testtime;
	}
	public void setTesttime(Float testtime) {
		this.testtime = testtime;
	}
	public Float getPenetration1() {
		return penetration1;
	}
	public void setPenetration1(Float penetration1) {
		this.penetration1 = penetration1;
	}
	public Float getPenetration2() {
		return penetration2;
	}
	public void setPenetration2(Float penetration2) {
		this.penetration2 = penetration2;
	}
	public Float getPenetration3() {
		return penetration3;
	}
	public void setPenetration3(Float penetration3) {
		this.penetration3 = penetration3;
	}
	public Double getPenetration() {
		return penetration;
	}
	public void setPenetration(Double penetration) {
		this.penetration = penetration;
	}
	
	
}
