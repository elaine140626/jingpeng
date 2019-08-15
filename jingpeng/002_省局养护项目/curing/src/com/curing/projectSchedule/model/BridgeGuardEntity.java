package com.curing.projectSchedule.model;

public class BridgeGuardEntity {

	// id
	private String Id;
	// 项目id
	private String ProjectId;
	// 中心桩号
	private String CoreNumber;
	// 桥名
	private String BridgeName;
	// 支撑梁C25混凝土（m³）
	private Double SupportingC25Concrete;
	// 支撑梁钢筋（Kg）
	private Double SupportingRebar;
	// 八字墙/一字墙C15片石混凝土基础（m³）
	private Double AliformC15Concrete;
	// 八字墙/一字墙M10片石墙身（m³）
	private Double AliformM10Wall;
	// 八字墙/一字墙M10片石基础（m³）
	private Double AliformM10Basics;
	// 八字墙/一字墙M10浆砌块石镶面（m³）
	private Double AliformM10Veneer;
	// 铺砌M7.5片石铺砌（m³）
	private Double RubblePaving;
	// 隔水墙M7.5号浆砌片石（m³）
	private Double Rubble;
	// 砂砾（m³）
	private Double Gravel;
	// 桥名碑（70×30×1）
	private Double BridgeBrand;
	// 拆除圬工（m³）
	private Double Mason;
	// 后台填筑（m³）
	private Double Backfilling;
	// 挖基土方（m³）
	private Double Earthwork;
	// 备注
	private String Remarks;
	private int IsDel;
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
	public String getBridgeName() {
		return BridgeName;
	}
	public void setBridgeName(String bridgeName) {
		BridgeName = bridgeName;
	}
	public Double getSupportingC25Concrete() {
		return SupportingC25Concrete;
	}
	public void setSupportingC25Concrete(Double supportingC25Concrete) {
		SupportingC25Concrete = supportingC25Concrete;
	}
	public Double getSupportingRebar() {
		return SupportingRebar;
	}
	public void setSupportingRebar(Double supportingRebar) {
		SupportingRebar = supportingRebar;
	}
	public Double getAliformC15Concrete() {
		return AliformC15Concrete;
	}
	public void setAliformC15Concrete(Double aliformC15Concrete) {
		AliformC15Concrete = aliformC15Concrete;
	}
	public Double getAliformM10Wall() {
		return AliformM10Wall;
	}
	public void setAliformM10Wall(Double aliformM10Wall) {
		AliformM10Wall = aliformM10Wall;
	}
	public Double getAliformM10Basics() {
		return AliformM10Basics;
	}
	public void setAliformM10Basics(Double aliformM10Basics) {
		AliformM10Basics = aliformM10Basics;
	}
	public Double getAliformM10Veneer() {
		return AliformM10Veneer;
	}
	public void setAliformM10Veneer(Double aliformM10Veneer) {
		AliformM10Veneer = aliformM10Veneer;
	}
	public Double getRubblePaving() {
		return RubblePaving;
	}
	public void setRubblePaving(Double rubblePaving) {
		RubblePaving = rubblePaving;
	}
	public Double getRubble() {
		return Rubble;
	}
	public void setRubble(Double rubble) {
		Rubble = rubble;
	}
	public Double getGravel() {
		return Gravel;
	}
	public void setGravel(Double gravel) {
		Gravel = gravel;
	}
	public Double getBridgeBrand() {
		return BridgeBrand;
	}
	public void setBridgeBrand(Double bridgeBrand) {
		BridgeBrand = bridgeBrand;
	}
	public Double getMason() {
		return Mason;
	}
	public void setMason(Double mason) {
		Mason = mason;
	}
	public Double getBackfilling() {
		return Backfilling;
	}
	public void setBackfilling(Double backfilling) {
		Backfilling = backfilling;
	}
	public Double getEarthwork() {
		return Earthwork;
	}
	public void setEarthwork(Double earthwork) {
		Earthwork = earthwork;
	}
	public String getRemarks() {
		return Remarks;
	}
	public void setRemarks(String remarks) {
		Remarks = remarks;
	}
	public int getIsDel() {
		return IsDel;
	}
	public void setIsDel(int isDel) {
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
