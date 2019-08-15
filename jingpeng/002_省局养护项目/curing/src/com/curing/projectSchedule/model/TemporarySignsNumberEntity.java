package com.curing.projectSchedule.model;

public class TemporarySignsNumberEntity {
	private String Id;
	private String ProjectId; // 项目id
	private String PileNumber; // 中心桩号
	private String Position; // 设置位置
	private String SignName; // 临时标志名称
	private String Size; // 板面尺寸（高度cm×长度cm）
	private Integer Amount; // 数量
	private String Remarks; // 备注
	private Integer IsDel;
	private String Creater;
	private String reaterDate;
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

	public String getPileNumber() {
		return PileNumber;
	}

	public void setPileNumber(String pileNumber) {
		PileNumber = pileNumber;
	}

	public String getPosition() {
		return Position;
	}

	public void setPosition(String position) {
		Position = position;
	}

	public String getSignName() {
		return SignName;
	}

	public void setSignName(String signName) {
		SignName = signName;
	}

	public String getSize() {
		return Size;
	}

	public void setSize(String size) {
		Size = size;
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

	public String getReaterDate() {
		return reaterDate;
	}

	public void setReaterDate(String reaterDate) {
		this.reaterDate = reaterDate;
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
