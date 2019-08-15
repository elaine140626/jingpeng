package com.curing.projectSchedule.model;

public class BridgeRetainingWallNumberEntity {
	private String Id;
	private String ProjectId; // 项目id
	private String PileNumber; // 桩号
	private String BridgeName; // 桥梁名称
	private Double WallLength; // 挡墙长
	private Double StoneBasics; // M10片石基础（m³）
	private Double StoneWallBody; // M10片石墙身（m³）
	private Double BlockStone; // M10浆砌块石镶面（m³）
	private Double Excavation; // 挖方（m³）
	private Double BackfillGravel; // 回填透水性砾料（m³）
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

	public String getBridgeName() {
		return BridgeName;
	}

	public void setBridgeName(String bridgeName) {
		BridgeName = bridgeName;
	}

	public Double getWallLength() {
		return WallLength;
	}

	public void setWallLength(Double wallLength) {
		WallLength = wallLength;
	}

	public Double getStoneBasics() {
		return StoneBasics;
	}

	public void setStoneBasics(Double stoneBasics) {
		StoneBasics = stoneBasics;
	}

	public Double getStoneWallBody() {
		return StoneWallBody;
	}

	public void setStoneWallBody(Double stoneWallBody) {
		StoneWallBody = stoneWallBody;
	}

	public Double getBlockStone() {
		return BlockStone;
	}

	public void setBlockStone(Double blockStone) {
		BlockStone = blockStone;
	}

	public Double getExcavation() {
		return Excavation;
	}

	public void setExcavation(Double excavation) {
		Excavation = excavation;
	}

	public Double getBackfillGravel() {
		return BackfillGravel;
	}

	public void setBackfillGravel(Double backfillGravel) {
		BackfillGravel = backfillGravel;
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
