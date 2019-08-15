package com.blindSample.model;

//Test04006水泥胶砂强度试验主表
public class Test04006T0 {

	private Long id; //自增长ID
	private String serialnumber; //流水号
	private String cementtype;	//水泥品种
	private String cementstrengthgrade;	//水泥强度等级
	private String productiondate;	//出厂日期
	private String molddate;	//成型日期
	private String operator;	//创建人
	private String createdate;	//创建日期
	private String modifier;	//修改人
	private String modifydate;	//修改日期
	private Boolean isValidData; //有效标识
	
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
	public String getProductiondate() {
		return productiondate;
	}
	public void setProductiondate(String productiondate) {
		this.productiondate = productiondate;
	}
	public String getMolddate() {
		return molddate;
	}
	public void setMolddate(String molddate) {
		this.molddate = molddate;
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
