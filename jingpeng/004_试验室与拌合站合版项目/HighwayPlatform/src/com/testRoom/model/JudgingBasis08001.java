package com.testRoom.model;

//判定依据Test08001沥青三大指标
public class JudgingBasis08001 {

	private Long id;	//自增长ID
	private String asphalttype;	//沥青种类
	private Float penetupperlimit = 0F;	//针入度上限
	private Float penetlowerlimit = 0F;	//针入度下限
	private Float softenpoint = 0F;	//软化点
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
	public String getAsphalttype() {
		return asphalttype;
	}
	public void setAsphalttype(String asphalttype) {
		this.asphalttype = asphalttype;
	}
	public Float getPenetupperlimit() {
		return penetupperlimit;
	}
	public void setPenetupperlimit(Float penetupperlimit) {
		this.penetupperlimit = penetupperlimit;
	}
	public Float getPenetlowerlimit() {
		return penetlowerlimit;
	}
	public void setPenetlowerlimit(Float penetlowerlimit) {
		this.penetlowerlimit = penetlowerlimit;
	}
	public Float getSoftenpoint() {
		return softenpoint;
	}
	public void setSoftenpoint(Float softenpoint) {
		this.softenpoint = softenpoint;
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
