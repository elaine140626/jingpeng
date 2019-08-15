package com.curing.projectSchedule.model;

public class PassingCulvertNumberEntity {
	private String Id;
	private String ProjectId; // 项目id
	private String PileNumber; // 中心桩号
	private String HoleCount; // 孔数-孔径（孔-米）
	private String Position; // 位置
	private Double L; // L（m）
	private Double CrossingRebar; // 工程数量过路板钢筋（Kg）
	private Double CrossingConcrete; // 工程数量过路板混凝土C30（m3）
	private Double Coating; // 工程数量涂料（㎡）
	private Double SpreadRebar; // 工程数量涵面铺装钢筋（Kg）
	private Double SpreadConcrete; // 工程数量涵面铺装混凝土C30（m³）
	private Double ConcreteCap; // 工程数量C20混凝土台帽（m³）
	private Double RubbleBasics; // 工程数量M15浆砌片石涵台身及基础（㎡）
	private Double ThickFelt; // 工程数量涵身顶面1cm厚油毛毡（㎡）
	private Double PermeableGranules; // 工程数量透水性砾料（m³）
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

	public String getPileNumber() {
		return PileNumber;
	}

	public void setPileNumber(String pileNumber) {
		PileNumber = pileNumber;
	}

	public String getHoleCount() {
		return HoleCount;
	}

	public void setHoleCount(String holeCount) {
		HoleCount = holeCount;
	}

	public String getPosition() {
		return Position;
	}

	public void setPosition(String position) {
		Position = position;
	}

	public Double getL() {
		return L;
	}

	public void setL(Double l) {
		L = l;
	}

	public Double getCrossingRebar() {
		return CrossingRebar;
	}

	public void setCrossingRebar(Double crossingRebar) {
		CrossingRebar = crossingRebar;
	}

	public Double getCrossingConcrete() {
		return CrossingConcrete;
	}

	public void setCrossingConcrete(Double crossingConcrete) {
		CrossingConcrete = crossingConcrete;
	}

	public Double getCoating() {
		return Coating;
	}

	public void setCoating(Double coating) {
		Coating = coating;
	}

	public Double getSpreadRebar() {
		return SpreadRebar;
	}

	public void setSpreadRebar(Double spreadRebar) {
		SpreadRebar = spreadRebar;
	}

	public Double getSpreadConcrete() {
		return SpreadConcrete;
	}

	public void setSpreadConcrete(Double spreadConcrete) {
		SpreadConcrete = spreadConcrete;
	}

	public Double getConcreteCap() {
		return ConcreteCap;
	}

	public void setConcreteCap(Double concreteCap) {
		ConcreteCap = concreteCap;
	}

	public Double getRubbleBasics() {
		return RubbleBasics;
	}

	public void setRubbleBasics(Double rubbleBasics) {
		RubbleBasics = rubbleBasics;
	}

	public Double getThickFelt() {
		return ThickFelt;
	}

	public void setThickFelt(Double thickFelt) {
		ThickFelt = thickFelt;
	}

	public Double getPermeableGranules() {
		return PermeableGranules;
	}

	public void setPermeableGranules(Double permeableGranules) {
		PermeableGranules = permeableGranules;
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
