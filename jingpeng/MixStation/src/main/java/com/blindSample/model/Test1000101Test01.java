package com.blindSample.model;

//Test1000101钢筋抗拉强度、屈服强度、伸长率、冷弯试验子表01
public class Test1000101Test01 {

	private Long id;	//子表01ID
	private String serialnumber;	//主表流水号
	private Integer testgroupid;	//试验组号
	private Integer testserial;	//试验序号
	private String strengthgrade;	//强度等级或牌号
	private Float diameter;	//直径
	private Float length;	//长度(mm)
	private Float mass;	//质量(g)
	private Float gauge;	//标距(mm)
	private double area;	//截面积
	private Float yieldload;	//屈服荷载(kN)
	private double yieldstrth;	//屈服强度(MPa)
	private Float maxload;	//最大力荷载(kN)
	private double tensilestrength;	//抗拉强度(MPa)
	private String fracturetype;	//断口形式
	private Float fracturegauge;	//断后标距(mm)
	private double elonrate;	//伸长率(%)
	private Float benddiameter;	//弯心直径
	private Float bendangle;	//弯曲角度	
	private String result;	//结果
	private Float bendradius;	//弯曲半径(mm)
	private Integer bendtimes;	//弯曲次数
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
	public Integer getTestgroupid() {
		return testgroupid;
	}
	public void setTestgroupid(Integer testgroupid) {
		this.testgroupid = testgroupid;
	}
	public Integer getTestserial() {
		return testserial;
	}
	public void setTestserial(Integer testserial) {
		this.testserial = testserial;
	}
	public String getStrengthgrade() {
		return strengthgrade;
	}
	public void setStrengthgrade(String strengthgrade) {
		this.strengthgrade = strengthgrade;
	}
	public Float getDiameter() {
		return diameter;
	}
	public void setDiameter(Float diameter) {
		this.diameter = diameter;
	}
	public Float getLength() {
		return length;
	}
	public void setLength(Float length) {
		this.length = length;
	}
	public Float getMass() {
		return mass;
	}
	public void setMass(Float mass) {
		this.mass = mass;
	}
	public Float getGauge() {
		return gauge;
	}
	public void setGauge(Float gauge) {
		this.gauge = gauge;
	}
	public double getArea() {
		return area;
	}
	public void setArea(double area) {
		this.area = area;
	}
	public Float getYieldload() {
		return yieldload;
	}
	public void setYieldload(Float yieldload) {
		this.yieldload = yieldload;
	}
	public double getYieldstrth() {
		return yieldstrth;
	}
	public void setYieldstrth(double yieldstrth) {
		this.yieldstrth = yieldstrth;
	}
	public Float getMaxload() {
		return maxload;
	}
	public void setMaxload(Float maxload) {
		this.maxload = maxload;
	}
	public double getTensilestrength() {
		return tensilestrength;
	}
	public void setTensilestrength(double tensilestrength) {
		this.tensilestrength = tensilestrength;
	}
	public String getFracturetype() {
		return fracturetype;
	}
	public void setFracturetype(String fracturetype) {
		this.fracturetype = fracturetype;
	}
	public Float getFracturegauge() {
		return fracturegauge;
	}
	public void setFracturegauge(Float fracturegauge) {
		this.fracturegauge = fracturegauge;
	}
	public double getElonrate() {
		return elonrate;
	}
	public void setElonrate(double elonrate) {
		this.elonrate = elonrate;
	}
	public Float getBenddiameter() {
		return benddiameter;
	}
	public void setBenddiameter(Float benddiameter) {
		this.benddiameter = benddiameter;
	}
	public Float getBendangle() {
		return bendangle;
	}
	public void setBendangle(Float bendangle) {
		this.bendangle = bendangle;
	}
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	public Float getBendradius() {
		return bendradius;
	}
	public void setBendradius(Float bendradius) {
		this.bendradius = bendradius;
	}
	public Integer getBendtimes() {
		return bendtimes;
	}
	public void setBendtimes(Integer bendtimes) {
		this.bendtimes = bendtimes;
	}
	
	
}
