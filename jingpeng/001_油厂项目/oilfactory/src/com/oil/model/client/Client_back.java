package com.oil.model.client;

public class Client_back {
	private	int	id;	 //id
	private	int	customerId ;//	客户id
	private String	visitDate ;//拜访日期
	private String visitForm ;//拜访形式（数据字典）
	private String visitContent ;//拜访内容
	private String	returnVisitDate ;//回访日期
	private String customerEvaluate ;//客户评价（数据字典）
	private String returnVisitContent ;//回访内容	
	private	int	userInfoId ;//销售员id
	private	int	isVisit ;//回访区分
	private	int 	isDel ;//删除标记
	private String	creater ;//创建人
	private String	createrDate ;//创建日期
	private String	reviser ;//修改人
	private String	reviserDate ;//修改日期
	private int serialnumber;//序号	
	private String remarks;//备注
	private String operate;//操作
	private String customerName; //客户名称
	private String customerCode; //客户编号
	private int cisDel; //客户名称
	private String userInfoName;//销售员名称
	
	
	public String getUserInfoName() {
		return userInfoName;
	}
	public void setUserInfoName(String userInfoName) {
		this.userInfoName = userInfoName;
	}
	public int getCisDel() {
		return cisDel;
	}
	public void setCisDel(int cisDel) {
		this.cisDel = cisDel;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getCustomerId() {
		return customerId;
	}
	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}
	public String getVisitDate() {
		return visitDate;
	}
	public void setVisitDate(String visitDate) {
		this.visitDate = visitDate;
	}
	public String getVisitForm() {
		return visitForm;
	}
	public void setVisitForm(String visitForm) {
		this.visitForm = visitForm;
	}
	public String getVisitContent() {
		return visitContent;
	}
	public void setVisitContent(String visitContent) {
		this.visitContent = visitContent;
	}
	public String getReturnVisitDate() {
		return returnVisitDate;
	}
	public void setReturnVisitDate(String returnVisitDate) {
		this.returnVisitDate = returnVisitDate;
	}
	public String getCustomerEvaluate() {
		return customerEvaluate;
	}
	public void setCustomerEvaluate(String customerEvaluate) {
		this.customerEvaluate = customerEvaluate;
	}
	public String getReturnVisitContent() {
		return returnVisitContent;
	}
	public void setReturnVisitContent(String returnVisitContent) {
		this.returnVisitContent = returnVisitContent;
	}
	public int getUserInfoId() {
		return userInfoId;
	}
	public void setUserInfoId(int userInfoId) {
		this.userInfoId = userInfoId;
	}
	public int getIsVisit() {
		return isVisit;
	}
	public void setIsVisit(int isVisit) {
		this.isVisit = isVisit;
	}
	public int getIsDel() {
		return isDel;
	}
	public void setIsDel(int isDel) {
		this.isDel = isDel;
	}
	public String getCreater() {
		return creater;
	}
	public void setCreater(String creater) {
		this.creater = creater;
	}
	public String getCreaterDate() {
		return createrDate;
	}
	public void setCreaterDate(String createrDate) {
		this.createrDate = createrDate;
	}
	public String getReviser() {
		return reviser;
	}
	public void setReviser(String reviser) {
		this.reviser = reviser;
	}
	public String getReviserDate() {
		return reviserDate;
	}
	public void setReviserDate(String reviserDate) {
		this.reviserDate = reviserDate;
	}
	public int getSerialnumber() {
		return serialnumber;
	}
	public void setSerialnumber(int serialnumber) {
		this.serialnumber = serialnumber;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public String getOperate() {
		return operate;
	}
	public void setOperate(String operate) {
		this.operate = operate;
	}
	public String getCustomerCode() {
		return customerCode;
	}
	public void setCustomerCode(String customerCode) {
		this.customerCode = customerCode;
	}
	
	
	
}