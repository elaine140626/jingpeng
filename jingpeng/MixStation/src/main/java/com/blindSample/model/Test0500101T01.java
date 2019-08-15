package com.blindSample.model;

//Test0500101水泥混凝土抗压强度（立方体）子表01
public class Test0500101T01 {

	private Long id;	//子表01ID
	private String serialnumber;	//主表流水号
	private Integer testgroup;	//试件组号
	private String location;	//具体工程部位
	private String molddate;	//成型日期
	private String testdate;	//试验日期
	private Integer age;	//龄期(天)
	private Float slump;	//坍落度
	private Float ultimateload1;	//极限荷载(kN)1
	private Double comprstrength1;	//抗压强度1
	private Float ultimateload2;	//极限荷载(kN)2
	private Double comprstrength2;	//抗压强度2
	private Float ultimateload3;	//极限荷载(kN)3
	private Double comprstrength3;	//抗压强度3
	private Double compressivestrength;	//抗压强度测定值
	private String mensuratenature;	//测定值性质	
	private Double prop_designstrength;	//占设计强度(%)
	private String specifiedvalue;	//规定值
	private String result;	//结果
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
	public Integer getTestgroup() {
		return testgroup;
	}
	public void setTestgroup(Integer testgroup) {
		this.testgroup = testgroup;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getMolddate() {
		return molddate;
	}
	public void setMolddate(String molddate) {
		this.molddate = molddate;
	}
	public String getTestdate() {
		return testdate;
	}
	public void setTestdate(String testdate) {
		this.testdate = testdate;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	public Float getSlump() {
		return slump;
	}
	public void setSlump(Float slump) {
		this.slump = slump;
	}
	public Float getUltimateload1() {
		return ultimateload1;
	}
	public void setUltimateload1(Float ultimateload1) {
		this.ultimateload1 = ultimateload1;
	}
	public Double getComprstrength1() {
		return comprstrength1;
	}
	public void setComprstrength1(Double comprstrength1) {
		this.comprstrength1 = comprstrength1;
	}
	public Float getUltimateload2() {
		return ultimateload2;
	}
	public void setUltimateload2(Float ultimateload2) {
		this.ultimateload2 = ultimateload2;
	}
	public Double getComprstrength2() {
		return comprstrength2;
	}
	public void setComprstrength2(Double comprstrength2) {
		this.comprstrength2 = comprstrength2;
	}
	public Float getUltimateload3() {
		return ultimateload3;
	}
	public void setUltimateload3(Float ultimateload3) {
		this.ultimateload3 = ultimateload3;
	}
	public Double getComprstrength3() {
		return comprstrength3;
	}
	public void setComprstrength3(Double comprstrength3) {
		this.comprstrength3 = comprstrength3;
	}
	public Double getCompressivestrength() {
		return compressivestrength;
	}
	public void setCompressivestrength(Double compressivestrength) {
		this.compressivestrength = compressivestrength;
	}
	public String getMensuratenature() {
		return mensuratenature;
	}
	public void setMensuratenature(String mensuratenature) {
		this.mensuratenature = mensuratenature;
	}
	public Double getProp_designstrength() {
		return prop_designstrength;
	}
	public void setProp_designstrength(Double prop_designstrength) {
		this.prop_designstrength = prop_designstrength;
	}
	public String getSpecifiedvalue() {
		return specifiedvalue;
	}
	public void setSpecifiedvalue(String specifiedvalue) {
		this.specifiedvalue = specifiedvalue;
	}
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	
	
}
