package com.blindSample.model;

//Test0500102砂浆抗压强度
public class Test0500102T0 {

	private Long id;	//自增长ID
	private String serialnumber;	//流水号
	private String mortartype;	//砂浆种类
	private String designstrength;	//设计强度
	private String cementgrade;	//水泥品种及强度等级
	private String curingmethod;	//养护方式
	private String samplesize;	//试件尺寸(mm)
	private String mixtureratio;	//水泥：砂：水	
	private String admixture;	//外掺剂种类与用量
	private double read_flexuralstrength;	//直接读取抗折强度
	private Integer sampleamount;	//试件数量	
	private String operator;	//创建人
	private String createdate;	//创建日期
	private String modifier;	//修改人
	private String modifydate;	//修改日期
	private boolean isvaliddata;	//有效标识
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
	public String getMortartype() {
		return mortartype;
	}
	public void setMortartype(String mortartype) {
		this.mortartype = mortartype;
	}
	public String getDesignstrength() {
		return designstrength;
	}
	public void setDesignstrength(String designstrength) {
		this.designstrength = designstrength;
	}
	public String getCementgrade() {
		return cementgrade;
	}
	public void setCementgrade(String cementgrade) {
		this.cementgrade = cementgrade;
	}
	public String getCuringmethod() {
		return curingmethod;
	}
	public void setCuringmethod(String curingmethod) {
		this.curingmethod = curingmethod;
	}
	public String getSamplesize() {
		return samplesize;
	}
	public void setSamplesize(String samplesize) {
		this.samplesize = samplesize;
	}
	public String getMixtureratio() {
		return mixtureratio;
	}
	public void setMixtureratio(String mixtureratio) {
		this.mixtureratio = mixtureratio;
	}
	public String getAdmixture() {
		return admixture;
	}
	public void setAdmixture(String admixture) {
		this.admixture = admixture;
	}
	public double getRead_flexuralstrength() {
		return read_flexuralstrength;
	}
	public void setRead_flexuralstrength(double read_flexuralstrength) {
		this.read_flexuralstrength = read_flexuralstrength;
	}
	public Integer getSampleamount() {
		return sampleamount;
	}
	public void setSampleamount(Integer sampleamount) {
		this.sampleamount = sampleamount;
	}
	public String getOperator() {
		return operator;
	}
	public void setOperator(String operator) {
		this.operator = operator;
	}
	public String getCreatedate() {
		return createdate;
	}
	public void setCreatedate(String createdate) {
		this.createdate = createdate;
	}
	public String getModifier() {
		return modifier;
	}
	public void setModifier(String modifier) {
		this.modifier = modifier;
	}
	public String getModifydate() {
		return modifydate;
	}
	public void setModifydate(String modifydate) {
		this.modifydate = modifydate;
	}
	public boolean isIsvaliddata() {
		return isvaliddata;
	}
	public void setIsvaliddata(boolean isvaliddata) {
		this.isvaliddata = isvaliddata;
	}
	
	
}
