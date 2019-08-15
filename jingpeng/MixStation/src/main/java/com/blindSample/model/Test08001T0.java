package com.blindSample.model;

//Test08001沥青三大指标
public class Test08001T0 {

	private Long id;	//自增长ID
	private String serialnumber;	//流水号
	private String asphalttype;	//沥青种类
	private String asphaltgrade;	//沥青标号/等级
	private String producedate;	//出厂日期
	private Float coefficient;	//针入度相关系数
	private Double pi;	//针入度指数PI
	private Double t800;	//当量软化点T800(℃)
	private Double t12;	//当量脆点T1.2(℃)
	private Double t;	//塑性温度范围△T(℃)
	private String operator;	//创建人
	private String createdate;	//创建日期
	private String modifier;	//修改人
	private String modifydate;	//修改日期
	private Boolean isValidData;	//有效标识
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
	public String getAsphalttype() {
		return asphalttype;
	}
	public void setAsphalttype(String asphalttype) {
		this.asphalttype = asphalttype;
	}
	public String getAsphaltgrade() {
		return asphaltgrade;
	}
	public void setAsphaltgrade(String asphaltgrade) {
		this.asphaltgrade = asphaltgrade;
	}
	public String getProducedate() {
		return producedate;
	}
	public void setProducedate(String producedate) {
		this.producedate = producedate;
	}
	public Float getCoefficient() {
		return coefficient;
	}
	public void setCoefficient(Float coefficient) {
		this.coefficient = coefficient;
	}
	public Double getPi() {
		return pi;
	}
	public void setPi(Double pi) {
		this.pi = pi;
	}
	public Double getT800() {
		return t800;
	}
	public void setT800(Double t800) {
		this.t800 = t800;
	}
	public Double getT12() {
		return t12;
	}
	public void setT12(Double t12) {
		this.t12 = t12;
	}
	public Double getT() {
		return t;
	}
	public void setT(Double t) {
		this.t = t;
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
	public Boolean getIsValidData() {
		return isValidData;
	}
	public void setIsValidData(Boolean isValidData) {
		this.isValidData = isValidData;
	}
	
}
