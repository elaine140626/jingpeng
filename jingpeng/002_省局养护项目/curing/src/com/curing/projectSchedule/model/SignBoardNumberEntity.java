package com.curing.projectSchedule.model;

public class SignBoardNumberEntity {
	private String Id;
	private String ProjectId; // 项目id
	private String NameSpecifications; // 材料名称及规格
	private String Unit; // 单位
	private Double SingleHangAmount; // 单悬标志（块）数量
	private Double SingleHangWeight; // 单悬标志（块）重量（Kg）
	private Double SingleColumnAmount; // 单柱标志（块）数量
	private Double SingleColumnWeight; // 单柱标志（块）重量（Kg）
	private Double DoubleColumnAmount; // 双柱标志（块）数量
	private Double DoubleColumnWeight; // 双柱标志（块）重量（Kg）
	private Double AttachmentAmount; // 附着标志（块）数量
	private Double AttachmentWeight; // 附着标志（块）重量
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

	public String getNameSpecifications() {
		return NameSpecifications;
	}

	public void setNameSpecifications(String nameSpecifications) {
		NameSpecifications = nameSpecifications;
	}

	public String getUnit() {
		return Unit;
	}

	public void setUnit(String unit) {
		Unit = unit;
	}

	public Double getSingleHangAmount() {
		return SingleHangAmount;
	}

	public void setSingleHangAmount(Double singleHangAmount) {
		SingleHangAmount = singleHangAmount;
	}

	public Double getSingleHangWeight() {
		return SingleHangWeight;
	}

	public void setSingleHangWeight(Double singleHangWeight) {
		SingleHangWeight = singleHangWeight;
	}

	public Double getSingleColumnAmount() {
		return SingleColumnAmount;
	}

	public void setSingleColumnAmount(Double singleColumnAmount) {
		SingleColumnAmount = singleColumnAmount;
	}

	public Double getSingleColumnWeight() {
		return SingleColumnWeight;
	}

	public void setSingleColumnWeight(Double singleColumnWeight) {
		SingleColumnWeight = singleColumnWeight;
	}

	public Double getDoubleColumnAmount() {
		return DoubleColumnAmount;
	}

	public void setDoubleColumnAmount(Double doubleColumnAmount) {
		DoubleColumnAmount = doubleColumnAmount;
	}

	public Double getDoubleColumnWeight() {
		return DoubleColumnWeight;
	}

	public void setDoubleColumnWeight(Double doubleColumnWeight) {
		DoubleColumnWeight = doubleColumnWeight;
	}

	public Double getAttachmentAmount() {
		return AttachmentAmount;
	}

	public void setAttachmentAmount(Double attachmentAmount) {
		AttachmentAmount = attachmentAmount;
	}

	public Double getAttachmentWeight() {
		return AttachmentWeight;
	}

	public void setAttachmentWeight(Double attachmentWeight) {
		AttachmentWeight = attachmentWeight;
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
