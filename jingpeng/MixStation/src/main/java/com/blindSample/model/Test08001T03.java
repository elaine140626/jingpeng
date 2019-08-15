package com.blindSample.model;

//Test08001沥青三大指标(延度)子表03
public class Test08001T03 {
	
	private Long id;	//子表03ID
	private String serialnumber;	//主表流水号
	private String specimen_no;	//试件编号
	private Float temperature;	//试验温度
	private Float soaktime;	//保温时间
	private Float tensilespeed;	//拉伸速度
	private Float ductility1;	//延度1
	private Float ductility2;	//延度2
	private Float ductility3;	//延度3
	private String ductility;	//延度
	private String isductdisp;	//延度显示大于100
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
	public Float getSoaktime() {
		return soaktime;
	}
	public void setSoaktime(Float soaktime) {
		this.soaktime = soaktime;
	}
	public Float getTensilespeed() {
		return tensilespeed;
	}
	public void setTensilespeed(Float tensilespeed) {
		this.tensilespeed = tensilespeed;
	}
	public Float getDuctility1() {
		return ductility1;
	}
	public void setDuctility1(Float ductility1) {
		this.ductility1 = ductility1;
	}
	public Float getDuctility2() {
		return ductility2;
	}
	public void setDuctility2(Float ductility2) {
		this.ductility2 = ductility2;
	}
	public Float getDuctility3() {
		return ductility3;
	}
	public void setDuctility3(Float ductility3) {
		this.ductility3 = ductility3;
	}
	public String getDuctility() {
		return ductility;
	}
	public void setDuctility(String ductility) {
		this.ductility = ductility;
	}
	public String getIsductdisp() {
		return isductdisp;
	}
	public void setIsductdisp(String isductdisp) {
		this.isductdisp = isductdisp;
	}
	

}
