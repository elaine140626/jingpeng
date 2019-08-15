package com.curing.projectSchedule.model;

public class MarkingLineNumberEntity {
	private String Id;
	private String ProjectId; // 项目id
	private String ProjectType; // 项目分类
	private String ProjectName; // 项目名称
	private String MarkingMaterial; // 标线材料
	private String MarkingMaterialName; // 标线材料
	private String MarkingType; // 标线类型
	private Double Spec; // 规格
	private String Unit; // 单位
	private Double Amount; // 数量
	private Double RealLength; // 实段长度
	private Double EmptyLength; // 虚段长度
	private Double SolidLineLength; // 实线长度
	private Double SolidLineArea; // 实线面积
	private String Remarks; // 备注
	private Integer IsDel;
	private String Creater;
	private String CreaterDate;
	private String Reviser;
	private String ReviserDate;
	private Double HotmeltmarkingSum; // 热熔标线
	private Double ErasemarkingSum; //擦除标线
	
	
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
	public String getProjectType() {
		return ProjectType;
	}
	public void setProjectType(String projectType) {
		ProjectType = projectType;
	}
	public String getProjectName() {
		return ProjectName;
	}
	public void setProjectName(String projectName) {
		ProjectName = projectName;
	}
	public String getMarkingMaterial() {
		return MarkingMaterial;
	}
	public void setMarkingMaterial(String markingMaterial) {
		MarkingMaterial = markingMaterial;
	}
	public String getMarkingMaterialName() {
		return MarkingMaterialName;
	}
	public void setMarkingMaterialName(String markingMaterialName) {
		MarkingMaterialName = markingMaterialName;
	}
	public String getMarkingType() {
		return MarkingType;
	}
	public void setMarkingType(String markingType) {
		MarkingType = markingType;
	}
	public Double getSpec() {
		return Spec;
	}
	public void setSpec(Double spec) {
		Spec = spec;
	}
	public String getUnit() {
		return Unit;
	}
	public void setUnit(String unit) {
		Unit = unit;
	}
	public Double getAmount() {
		return Amount;
	}
	public void setAmount(Double amount) {
		Amount = amount;
	}
	public Double getRealLength() {
		return RealLength;
	}
	public void setRealLength(Double realLength) {
		RealLength = realLength;
	}
	public Double getEmptyLength() {
		return EmptyLength;
	}
	public void setEmptyLength(Double emptyLength) {
		EmptyLength = emptyLength;
	}
	public Double getSolidLineLength() {
		return SolidLineLength;
	}
	public void setSolidLineLength(Double solidLineLength) {
		SolidLineLength = solidLineLength;
	}
	public Double getSolidLineArea() {
		return SolidLineArea;
	}
	public void setSolidLineArea(Double solidLineArea) {
		SolidLineArea = solidLineArea;
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
	public Double getHotmeltmarkingSum() {
		return HotmeltmarkingSum;
	}
	public void setHotmeltmarkingSum(Double hotmeltmarkingSum) {
		HotmeltmarkingSum = hotmeltmarkingSum;
	}
	public Double getErasemarkingSum() {
		return ErasemarkingSum;
	}
	public void setErasemarkingSum(Double erasemarkingSum) {
		ErasemarkingSum = erasemarkingSum;
	}
	
	

}
