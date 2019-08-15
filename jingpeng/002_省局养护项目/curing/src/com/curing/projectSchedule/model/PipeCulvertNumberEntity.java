package com.curing.projectSchedule.model;

public class PipeCulvertNumberEntity {
	// id 
	private String Id;
	// 项目id
	private String ProjectId;
	// 桩号
	private String PileNumber;
	// M15浆砌块石端墙墙身（m³）
	private Double M15Body;
	// C20混凝土端墙基础（m³）
	private Double C25ConcreteWall;
	// C20混凝土管基（m³）
	private Double C25ConcreteTube;
	// C25混凝土墙帽（m³）
	private Double C25ConcreteCap;
	// 砂砾垫层（m³）
	private Double GravelCushion;
	// M7.5浆砌片石洞口铺砌（m³）
	private Double RubbleHole;
	// M7.5浆砌片石隔水墙（m³）
	private Double RubbleWater;
	// M10浆砌块石八字翼墙墙身（m³）
	private Double M10RubbleBody;
	// C15片石混凝土翼墙基础（m³）
	private Double C15StoneConcrete;
	// 管涵钢筋（Kg）
	private Double TubeRebar;
	// 管涵混凝土（m³）
	private Double TubeConcrete;
	// 回填砾料（m³）
	private Double BackfillPellets;
	// 基坑开挖（m³）
	private Double Excavation;
	// 备注
	private String Remarks;
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
	public Double getM15Body() {
		return M15Body;
	}
	public void setM15Body(Double m15Body) {
		M15Body = m15Body;
	}
	public Double getC25ConcreteWall() {
		return C25ConcreteWall;
	}
	public void setC25ConcreteWall(Double c25ConcreteWall) {
		C25ConcreteWall = c25ConcreteWall;
	}
	public Double getC25ConcreteTube() {
		return C25ConcreteTube;
	}
	public void setC25ConcreteTube(Double c25ConcreteTube) {
		C25ConcreteTube = c25ConcreteTube;
	}
	public Double getC25ConcreteCap() {
		return C25ConcreteCap;
	}
	public void setC25ConcreteCap(Double c25ConcreteCap) {
		C25ConcreteCap = c25ConcreteCap;
	}
	public Double getGravelCushion() {
		return GravelCushion;
	}
	public void setGravelCushion(Double gravelCushion) {
		GravelCushion = gravelCushion;
	}
	public Double getRubbleHole() {
		return RubbleHole;
	}
	public void setRubbleHole(Double rubbleHole) {
		RubbleHole = rubbleHole;
	}
	public Double getRubbleWater() {
		return RubbleWater;
	}
	public void setRubbleWater(Double rubbleWater) {
		RubbleWater = rubbleWater;
	}
	public Double getM10RubbleBody() {
		return M10RubbleBody;
	}
	public void setM10RubbleBody(Double m10RubbleBody) {
		M10RubbleBody = m10RubbleBody;
	}
	public Double getC15StoneConcrete() {
		return C15StoneConcrete;
	}
	public void setC15StoneConcrete(Double c15StoneConcrete) {
		C15StoneConcrete = c15StoneConcrete;
	}
	public Double getTubeRebar() {
		return TubeRebar;
	}
	public void setTubeRebar(Double tubeRebar) {
		TubeRebar = tubeRebar;
	}
	public Double getTubeConcrete() {
		return TubeConcrete;
	}
	public void setTubeConcrete(Double tubeConcrete) {
		TubeConcrete = tubeConcrete;
	}
	public Double getBackfillPellets() {
		return BackfillPellets;
	}
	public void setBackfillPellets(Double backfillPellets) {
		BackfillPellets = backfillPellets;
	}
	public Double getExcavation() {
		return Excavation;
	}
	public void setExcavation(Double excavation) {
		Excavation = excavation;
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
