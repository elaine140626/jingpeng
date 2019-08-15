package com.blindSample.model;

//Test1000201钢筋接头抗拉强度、冷弯试验子表01
public class Test1000201T01 {

	private Long id;	//子表01ID
	private String serialnumber;	//流水号
	private Integer testgroupno;	//试验组号
	private Integer testserial;	//试验序号
	private String strengthgrade;	//强度等级或牌号
	private Float diameter;	//直径
	private String weldtype;	//焊接类型
	private Float weldlength;	//焊接长度
	private double area;	//母材截面积
	private Float maxload;	//最大力荷载(kN)
	private double tensilestrength;	//抗拉强度(MPa)
	private Float maxelongation;	//最大力总伸长率(%)
	private String fracturetype;	//断口形式
	private Float distance;	//断口距焊缝/连接器距离
	private Float benddiameter;	//弯心直径
	private Float bendangle;	//弯曲角度
	private String result;	//结果
	private String qualitycomment;	//质量评述
	private Float permanentdeformation;	//单向拉伸残余变形(mm)单值
	private double avgpermdefo;	//单向拉伸残余变形(mm)平均值
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
	public Integer getTestgroupno() {
		return testgroupno;
	}
	public void setTestgroupno(Integer testgroupno) {
		this.testgroupno = testgroupno;
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
	public String getWeldtype() {
		return weldtype;
	}
	public void setWeldtype(String weldtype) {
		this.weldtype = weldtype;
	}
	public Float getWeldlength() {
		return weldlength;
	}
	public void setWeldlength(Float weldlength) {
		this.weldlength = weldlength;
	}
	public double getArea() {
		return area;
	}
	public void setArea(double area) {
		this.area = area;
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
	public Float getMaxelongation() {
		return maxelongation;
	}
	public void setMaxelongation(Float maxelongation) {
		this.maxelongation = maxelongation;
	}
	public String getFracturetype() {
		return fracturetype;
	}
	public void setFracturetype(String fracturetype) {
		this.fracturetype = fracturetype;
	}
	public Float getDistance() {
		return distance;
	}
	public void setDistance(Float distance) {
		this.distance = distance;
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
	public String getQualitycomment() {
		return qualitycomment;
	}
	public void setQualitycomment(String qualitycomment) {
		this.qualitycomment = qualitycomment;
	}
	public Float getPermanentdeformation() {
		return permanentdeformation;
	}
	public void setPermanentdeformation(Float permanentdeformation) {
		this.permanentdeformation = permanentdeformation;
	}
	public double getAvgpermdefo() {
		return avgpermdefo;
	}
	public void setAvgpermdefo(double avgpermdefo) {
		this.avgpermdefo = avgpermdefo;
	}
	
	
}
