package com.testRoom.model;

//判定依据Test04006水泥胶砂强度
public class JudgingBasis04006 {
	private Long id;  //自增长ID
	private String cementtype;  //水泥品种
	private String cementstrengthgrade;  //水泥强度等级
	private Double rupturestrength3;  //抗折强度(Mpa)1
	private Double rupturestrength28;   //抗折强度(Mpa)1 
	private Double comptrength28;  //抗压强度(Mpa)2
	private Double comptrength3;  //抗压强度(Mpa)2
	private String operator;	//创建人
	private String createdate;	//创建日期
	private String modifier;	//修改人
	private String modifydate;	//修改日期
	private boolean isvaliddata;	//数据是否有效
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getCementtype() {
		return cementtype;
	}
	public void setCementtype(String cementtype) {
		this.cementtype = cementtype;
	}
	public String getCementstrengthgrade() {
		return cementstrengthgrade;
	}
	public void setCementstrengthgrade(String cementstrengthgrade) {
		this.cementstrengthgrade = cementstrengthgrade;
	}
	public Double getRupturestrength3() {
		return rupturestrength3;
	}
	public void setRupturestrength3(Double rupturestrength3) {
		this.rupturestrength3 = rupturestrength3;
	}
	public Double getRupturestrength28() {
		return rupturestrength28;
	}
	public void setRupturestrength28(Double rupturestrength28) {
		this.rupturestrength28 = rupturestrength28;
	}
	public Double getComptrength28() {
		return comptrength28;
	}
	public void setComptrength28(Double comptrength28) {
		this.comptrength28 = comptrength28;
	}
	public Double getComptrength3() {
		return comptrength3;
	}
	public void setComptrength3(Double comptrength3) {
		this.comptrength3 = comptrength3;
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
