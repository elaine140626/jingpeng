package com.curing.projectSchedule.model;

public class CrossingPillarEntity {
	private String Id;
	private String ProjectId; // 项目id
	private String CoreNumber; // 设置位置中心桩号
	private String AcrossForm; // 交叉形式
	private Double AcrossLeft; // 被交叉道路宽度左
	private Double AcrossRight; // 被交叉道路宽度右
	private Integer Amount; // 应设数量（个）
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
	public String getCoreNumber() {
		return CoreNumber;
	}
	public void setCoreNumber(String coreNumber) {
		CoreNumber = coreNumber;
	}
	public String getAcrossForm() {
		return AcrossForm;
	}
	public void setAcrossForm(String acrossForm) {
		AcrossForm = acrossForm;
	}
	public Double getAcrossLeft() {
		return AcrossLeft;
	}
	public void setAcrossLeft(Double acrossLeft) {
		AcrossLeft = acrossLeft;
	}
	public Double getAcrossRight() {
		return AcrossRight;
	}
	public void setAcrossRight(Double acrossRight) {
		AcrossRight = acrossRight;
	}
	public Integer getAmount() {
		return Amount;
	}
	public void setAmount(Integer amount) {
		Amount = amount;
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
