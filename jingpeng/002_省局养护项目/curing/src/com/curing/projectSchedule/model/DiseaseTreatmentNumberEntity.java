package com.curing.projectSchedule.model;

public class DiseaseTreatmentNumberEntity {
	private String Id;
	private String ProjectId; // 项目id
	private String DiseaseName; // 病害名称
	private Double DiseaseAmount; // 病害工程数量（㎡）
	private Double Caulking; // 灌缝（m）
	private Double SBSConcrete; // 4cm温拌SBS改性沥青混凝土（AC-13)（㎡）
	private Double GravelUpper; // 20cm4.5%水泥稳定碎石上基层（㎡）
	private Double SevenConcrete; // 7cm沥青混凝土（㎡）
	private Double PermeableLayer; // 透层油（㎡）
	private Double StickyLayer; // 粘层油（㎡）
	private Double SingleAsphalt; // 单层沥青表面处治（㎡）
	private Double MillingFour; // 铣刨4cm面层（㎡）
	private Double MillingTwenty; // 铣刨20cm基层（㎡）
	private Double SprayAsphalt; // 喷洒沥青（㎡）
	private Double Gravel; // 碎石（㎡）
	private Double StoneChip; // 石屑（㎡）
	private Double DigOut; // 挖除旧路面（㎡）
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

	public String getDiseaseName() {
		return DiseaseName;
	}

	public void setDiseaseName(String diseaseName) {
		DiseaseName = diseaseName;
	}

	public Double getDiseaseAmount() {
		return DiseaseAmount;
	}

	public void setDiseaseAmount(Double diseaseAmount) {
		DiseaseAmount = diseaseAmount;
	}

	public Double getCaulking() {
		return Caulking;
	}

	public void setCaulking(Double caulking) {
		Caulking = caulking;
	}

	public Double getSBSConcrete() {
		return SBSConcrete;
	}

	public void setSBSConcrete(Double sBSConcrete) {
		SBSConcrete = sBSConcrete;
	}

	public Double getGravelUpper() {
		return GravelUpper;
	}

	public void setGravelUpper(Double gravelUpper) {
		GravelUpper = gravelUpper;
	}

	public Double getSevenConcrete() {
		return SevenConcrete;
	}

	public void setSevenConcrete(Double sevenConcrete) {
		SevenConcrete = sevenConcrete;
	}

	public Double getPermeableLayer() {
		return PermeableLayer;
	}

	public void setPermeableLayer(Double permeableLayer) {
		PermeableLayer = permeableLayer;
	}

	public Double getStickyLayer() {
		return StickyLayer;
	}

	public void setStickyLayer(Double stickyLayer) {
		StickyLayer = stickyLayer;
	}

	public Double getSingleAsphalt() {
		return SingleAsphalt;
	}

	public void setSingleAsphalt(Double singleAsphalt) {
		SingleAsphalt = singleAsphalt;
	}

	public Double getMillingFour() {
		return MillingFour;
	}

	public void setMillingFour(Double millingFour) {
		MillingFour = millingFour;
	}

	public Double getMillingTwenty() {
		return MillingTwenty;
	}

	public void setMillingTwenty(Double millingTwenty) {
		MillingTwenty = millingTwenty;
	}

	public Double getSprayAsphalt() {
		return SprayAsphalt;
	}

	public void setSprayAsphalt(Double sprayAsphalt) {
		SprayAsphalt = sprayAsphalt;
	}

	public Double getGravel() {
		return Gravel;
	}

	public void setGravel(Double gravel) {
		Gravel = gravel;
	}

	public Double getStoneChip() {
		return StoneChip;
	}

	public void setStoneChip(Double stoneChip) {
		StoneChip = stoneChip;
	}

	public Double getDigOut() {
		return DigOut;
	}

	public void setDigOut(Double digOut) {
		DigOut = digOut;
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
