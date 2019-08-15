package com.curing.projectSchedule.model;

public class PassingThroughNumberEntity {
	private String Id;
	private String ProjectId; // 项目id
	private String CoreNumber; // 中心桩号
	private String BridgeName; // 孔数-孔径（孔-米）
	private String SupportingC25Concrete; // 位置
	private Double SupportingRebar; // L（m）
	private Double AliformC15Concrete; // 工程数量M15浆砌片石端墙基础（m³）
	private Double AliformM10Wall ; // 工程数量M15浆砌片石端墙墙身（m³）
	private Double AliformM10Basics; // 工程数量C25砼帽（m³）
	private Double AliformM10Veneer; // 工程数量C25硬化面（m³）
	private Double RubblePaving; // 工程数量涂料（㎡）
	private Double Rubble;// 工程数量C20砼管基（m³）
	private Double Gravel; // 工程数量管节数（个）
	private Double BridgeBrand; // 工程数量钢筋（Kg）
	private Double Mason; // 工程数量C30管节（m³）
	private Double Backfilling; // 工程数量中部砂砾垫层（m³）
	private Double Earthwork2; // 工程数量透水性材料（m³）
	private Double Earthwork; // 工程数量挖基土方（m³）
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
	public String getBridgeName() {
		return BridgeName;
	}
	public void setBridgeName(String bridgeName) {
		BridgeName = bridgeName;
	}
	public String getSupportingC25Concrete() {
		return SupportingC25Concrete;
	}
	public void setSupportingC25Concrete(String supportingC25Concrete) {
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
	public Double getEarthwork2() {
		return Earthwork2;
	}
	public void setEarthwork2(Double earthwork2) {
		Earthwork2 = earthwork2;
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
