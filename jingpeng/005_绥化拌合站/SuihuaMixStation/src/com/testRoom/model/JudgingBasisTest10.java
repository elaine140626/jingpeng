package com.testRoom.model;

//判定依据Test10钢筋
public class JudgingBasisTest10 {

	private Long id;	//自增长ID
	private boolean isvaliddata;	//数据是否有效
	private String strengthgrade;	//强度等级或牌号
	private Float diameter1 = 0F;	//直径1
	private Float diameter2 = 0F;	//直径2
	private boolean ist1000201;	//是否焊接试验
	private Double yieldstrth;	//屈服强度(MPa)
	private Double tensilestrength;	//抗拉强度(MPa)
	private String operator;	//创建人
	private String createdate;	//创建日期
	private String modifier;	//修改人
	private String modifydate;	//修改日期
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public boolean isIsvaliddata() {
		return isvaliddata;
	}
	public void setIsvaliddata(boolean isvaliddata) {
		this.isvaliddata = isvaliddata;
	}
	public String getStrengthgrade() {
		return strengthgrade;
	}
	public void setStrengthgrade(String strengthgrade) {
		this.strengthgrade = strengthgrade;
	}
	public Float getDiameter1() {
		return diameter1;
	}
	public void setDiameter1(Float diameter1) {
		this.diameter1 = diameter1;
	}
	public Float getDiameter2() {
		return diameter2;
	}
	public void setDiameter2(Float diameter2) {
		this.diameter2 = diameter2;
	}
	public boolean isIst1000201() {
		return ist1000201;
	}
	public void setIst1000201(boolean ist1000201) {
		this.ist1000201 = ist1000201;
	}
	public Double getYieldstrth() {
		return yieldstrth;
	}
	public void setYieldstrth(Double yieldstrth) {
		this.yieldstrth = yieldstrth;
	}
	public Double getTensilestrength() {
		return tensilestrength;
	}
	public void setTensilestrength(Double tensilestrength) {
		this.tensilestrength = tensilestrength;
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
	
	
	
}
