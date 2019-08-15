package com.curing.common.model;

public class SubheadNumberMoneyEntity {
	private String Id;
	private String ProjectId; // 项目id
	private String SubheadNumber; // 第100章-第700章子目号
	private String SubheadName; // 第100章-第700章子目名称
	private Double ContractAmount; // 合同金额
	private Double Total; // 总量
	private Double UnitPrice; // 单价
	private Double TotalPrice; // 总价
	private String Remarks; // 备注
	private Integer IsDel;
	private String Creater;
	private String CreaterDate;
	private String Reviser;
	private String ReviserDate;

	
	

	public String getId() {
		return Id;
	}

	public void setId(String id) {
		Id = id;
	}

	public String getProjectId() {
		return ProjectId;
	}

	public void setProjectId(String projectId) {
		ProjectId = projectId;
	}

	public String getSubheadNumber() {
		return SubheadNumber;
	}

	public void setSubheadNumber(String subheadNumber) {
		SubheadNumber = subheadNumber;
	}

	public String getSubheadName() {
		return SubheadName;
	}

	public void setSubheadName(String subheadName) {
		SubheadName = subheadName;
	}

	public Double getContractAmount() {
		return ContractAmount;
	}

	public void setContractAmount(Double contractAmount) {
		ContractAmount = contractAmount;
	}

	public Double getTotal() {
		return Total;
	}

	public void setTotal(Double total) {
		Total = total;
	}

	public Double getUnitPrice() {
		return UnitPrice;
	}

	public void setUnitPrice(Double unitPrice) {
		UnitPrice = unitPrice;
	}

	public Double getTotalPrice() {
		return TotalPrice;
	}

	public void setTotalPrice(Double totalPrice) {
		TotalPrice = totalPrice;
	}

	public String getRemarks() {
		return Remarks;
	}

	public void setRemarks(String remarks) {
		Remarks = remarks;
	}

	public Integer getIsDel() {
		return IsDel;
	}

	public void setIsDel(Integer isDel) {
		IsDel = isDel;
	}

	public String getCreater() {
		return Creater;
	}

	public void setCreater(String creater) {
		Creater = creater;
	}

	public String getCreaterDate() {
		return CreaterDate;
	}

	public void setCreaterDate(String createrDate) {
		CreaterDate = createrDate;
	}

	public String getReviser() {
		return Reviser;
	}

	public void setReviser(String reviser) {
		Reviser = reviser;
	}

	public String getReviserDate() {
		return ReviserDate;
	}

	public void setReviserDate(String reviserDate) {
		ReviserDate = reviserDate;
	}

}
