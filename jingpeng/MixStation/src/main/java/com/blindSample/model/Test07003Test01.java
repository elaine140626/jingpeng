package com.blindSample.model;

//Test07003无机结合无侧限抗压强度试验子表01
public class Test07003Test01 {

	private Long id;	//子表01ID
	private String serialnumber;	//主表流水号
	private Integer testserial;	//试验序号
	private Float mass1;	//养生前试件质量m1
	private Float mass2;	//浸水前试件质量m2
	private Float mass3;	//浸水后试件质量m3
	private Float mass4;	//养生期间质量损失m4(m1-m2)
	private Float mass5;	//吸水量m5(m3-m2)
	private Float height1;	//养生前试件高度h1
	private Float height2;	//浸水后试件高度h2
	private Float maxstress;	//试验的最大压力P
	private Double rc;	//无侧限抗压强度Rc
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
	public Float getMass1() {
		return mass1;
	}
	public void setMass1(Float mass1) {
		this.mass1 = mass1;
	}
	public Float getMass2() {
		return mass2;
	}
	public void setMass2(Float mass2) {
		this.mass2 = mass2;
	}
	public Float getMass3() {
		return mass3;
	}
	public void setMass3(Float mass3) {
		this.mass3 = mass3;
	}
	public Float getMass4() {
		return mass4;
	}
	public void setMass4(Float mass4) {
		this.mass4 = mass4;
	}
	public Float getMass5() {
		return mass5;
	}
	public void setMass5(Float mass5) {
		this.mass5 = mass5;
	}
	public Float getHeight1() {
		return height1;
	}
	public void setHeight1(Float height1) {
		this.height1 = height1;
	}
	public Float getHeight2() {
		return height2;
	}
	public void setHeight2(Float height2) {
		this.height2 = height2;
	}
	public Float getMaxstress() {
		return maxstress;
	}
	public void setMaxstress(Float maxstress) {
		this.maxstress = maxstress;
	}
	public Double getRc() {
		return rc;
	}
	public void setRc(Double rc) {
		this.rc = rc;
	}
	
}
