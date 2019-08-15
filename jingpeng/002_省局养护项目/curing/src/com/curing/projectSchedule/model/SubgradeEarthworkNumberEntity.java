package com.curing.projectSchedule.model;

public class SubgradeEarthworkNumberEntity {
	private String Id;
	private String ProjectId; // 项目id
	private String PileNumber; // 起讫桩号
	private Double EarthRock; // 横断面积挖土石（㎡）
	private Double OldRoad; // 横断面积挖旧路（㎡）
	private Double MountainFillStone; // 横断面积填山皮石（㎡）
	private Double PlantingSoil; // 横断面积填种植土（㎡）
	private Double Distance; // 距离（m）
	private Double ExcavationAllAmount; // 挖方分类及数量总数量（m³）
	private Double ExcavationAmount; // 挖方分类及数量Ⅰ（挖方）数量（m³）
	private Double ExcavationOldAmount; // 挖方分类及数量Ⅲ（挖旧路）数量（m³）
	private Double ExcavationRockAmount; // 挖方分类及数量Ⅴ（挖石方）数量（m³）
	private Double FillAllAmount; // 填方总数量（m³）
	private Double FillStoneAmount; // 填方填山皮石数量（m³）
	private Double FillSoilAmount; // 填方填种植土数量（m³）
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

	public Double getEarthRock() {
		return EarthRock;
	}

	public void setEarthRock(Double earthRock) {
		EarthRock = earthRock;
	}

	public Double getOldRoad() {
		return OldRoad;
	}

	public void setOldRoad(Double oldRoad) {
		OldRoad = oldRoad;
	}

	public Double getMountainFillStone() {
		return MountainFillStone;
	}

	public void setMountainFillStone(Double mountainFillStone) {
		MountainFillStone = mountainFillStone;
	}

	public Double getPlantingSoil() {
		return PlantingSoil;
	}

	public void setPlantingSoil(Double plantingSoil) {
		PlantingSoil = plantingSoil;
	}

	public Double getDistance() {
		return Distance;
	}

	public void setDistance(Double distance) {
		Distance = distance;
	}

	public Double getExcavationAllAmount() {
		return ExcavationAllAmount;
	}

	public void setExcavationAllAmount(Double excavationAllAmount) {
		ExcavationAllAmount = excavationAllAmount;
	}

	public Double getExcavationAmount() {
		return ExcavationAmount;
	}

	public void setExcavationAmount(Double excavationAmount) {
		ExcavationAmount = excavationAmount;
	}

	public Double getExcavationOldAmount() {
		return ExcavationOldAmount;
	}

	public void setExcavationOldAmount(Double excavationOldAmount) {
		ExcavationOldAmount = excavationOldAmount;
	}

	public Double getExcavationRockAmount() {
		return ExcavationRockAmount;
	}

	public void setExcavationRockAmount(Double excavationRockAmount) {
		ExcavationRockAmount = excavationRockAmount;
	}

	public Double getFillAllAmount() {
		return FillAllAmount;
	}

	public void setFillAllAmount(Double fillAllAmount) {
		FillAllAmount = fillAllAmount;
	}

	public Double getFillStoneAmount() {
		return FillStoneAmount;
	}

	public void setFillStoneAmount(Double fillStoneAmount) {
		FillStoneAmount = fillStoneAmount;
	}

	public Double getFillSoilAmount() {
		return FillSoilAmount;
	}

	public void setFillSoilAmount(Double fillSoilAmount) {
		FillSoilAmount = fillSoilAmount;
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
