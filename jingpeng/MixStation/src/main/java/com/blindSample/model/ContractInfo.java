package com.blindSample.model;

//合同信息表
public class ContractInfo {
	
	private Long id;	//自增长ID
	private String uniqueidentifier;	//试验室唯一标识
	private String unitcode;	//单位编号
	private String contractcode;	//合同编号
	private String contractdate;	//合同日期
	private String engineeringname;	//工程名称
	private String address;	//地址
	private String linkman;	//联系人
	private String phonenumber;	//电话
	private String remarks;	//备注
	private String constructionunit;	//施工单位
	private String superviseunit;	//监理单位
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
	public String getUniqueidentifier() {
		return uniqueidentifier;
	}
	public void setUniqueidentifier(String uniqueidentifier) {
		this.uniqueidentifier = uniqueidentifier;
	}
	public String getUnitcode() {
		return unitcode;
	}
	public void setUnitcode(String unitcode) {
		this.unitcode = unitcode;
	}
	public String getContractcode() {
		return contractcode;
	}
	public void setContractcode(String contractcode) {
		this.contractcode = contractcode;
	}
	public String getContractdate() {
		return contractdate;
	}
	public void setContractdate(String contractdate) {
		this.contractdate = contractdate;
	}
	public String getEngineeringname() {
		return engineeringname;
	}
	public void setEngineeringname(String engineeringname) {
		this.engineeringname = engineeringname;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getLinkman() {
		return linkman;
	}
	public void setLinkman(String linkman) {
		this.linkman = linkman;
	}
	public String getPhonenumber() {
		return phonenumber;
	}
	public void setPhonenumber(String phonenumber) {
		this.phonenumber = phonenumber;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public String getConstructionunit() {
		return constructionunit;
	}
	public void setConstructionunit(String constructionunit) {
		this.constructionunit = constructionunit;
	}
	public String getSuperviseunit() {
		return superviseunit;
	}
	public void setSuperviseunit(String superviseunit) {
		this.superviseunit = superviseunit;
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
