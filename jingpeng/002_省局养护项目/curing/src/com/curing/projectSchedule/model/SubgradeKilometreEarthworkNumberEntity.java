package com.curing.projectSchedule.model;

public class SubgradeKilometreEarthworkNumberEntity {
	private String Id;
	private String ProjectId; // 项目id
	private String PileNumber; // 起讫桩号
	private Integer AllCount; // 总数量
	private Double ExcavationSoil; // 挖方土普通土（立方米）
	private Double ExcavationSoilOldRoad; // 挖方土旧路结构层（立方米）
	private Double ExcavationSoilReplacement; // 挖方土换填挖方（立方米）
	private Double ExcavationStoneBrick; // 挖方石砖砌圬工（立方米）
	private Double ExcavationStoneSoft; // 挖方石软岩（立方米）
	private Double ExcavationStoneFirm; // 挖方石坚岩（立方米）
	private Double FillAllCount; // 填方合计总数量（立方米）
	private Double FillTotal; // 填方合计土（立方米）
	private Double FillStone; // 填方合计石渣（立方米）
	private Double FillLocalSoil; // 填方本桩利用土（立方米）
	private Double FillLocalStone; // 填方本桩利用石（立方米）
	private Double FillPortraitSoil; // 填方纵向利用土（立方米）
	private Double FillPortraitStone; // 填方纵向利用石（立方米）
	private Double FillDebtorTotal; // 填方借方土（立方米）
	private Double FillDebtorStone; // 填方借方石渣（立方米）
	private Double AbandonSoil; // 弃方土（立方米）
	private Double AbandonOldRoad; // 弃方旧路结构层（立方米）
	private Double AbandonBrick; // 弃方砖砌圬工（立方米）
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

	public Integer getAllCount() {
		return AllCount;
	}

	public void setAllCount(Integer allCount) {
		AllCount = allCount;
	}

	public Double getExcavationSoil() {
		return ExcavationSoil;
	}

	public void setExcavationSoil(Double excavationSoil) {
		ExcavationSoil = excavationSoil;
	}

	public Double getExcavationSoilOldRoad() {
		return ExcavationSoilOldRoad;
	}

	public void setExcavationSoilOldRoad(Double excavationSoilOldRoad) {
		ExcavationSoilOldRoad = excavationSoilOldRoad;
	}

	public Double getExcavationSoilReplacement() {
		return ExcavationSoilReplacement;
	}

	public void setExcavationSoilReplacement(Double excavationSoilReplacement) {
		ExcavationSoilReplacement = excavationSoilReplacement;
	}

	public Double getExcavationStoneBrick() {
		return ExcavationStoneBrick;
	}

	public void setExcavationStoneBrick(Double excavationStoneBrick) {
		ExcavationStoneBrick = excavationStoneBrick;
	}

	public Double getExcavationStoneSoft() {
		return ExcavationStoneSoft;
	}

	public void setExcavationStoneSoft(Double excavationStoneSoft) {
		ExcavationStoneSoft = excavationStoneSoft;
	}

	public Double getExcavationStoneFirm() {
		return ExcavationStoneFirm;
	}

	public void setExcavationStoneFirm(Double excavationStoneFirm) {
		ExcavationStoneFirm = excavationStoneFirm;
	}

	public Double getFillAllCount() {
		return FillAllCount;
	}

	public void setFillAllCount(Double fillAllCount) {
		FillAllCount = fillAllCount;
	}

	public Double getFillTotal() {
		return FillTotal;
	}

	public void setFillTotal(Double fillTotal) {
		FillTotal = fillTotal;
	}

	public Double getFillStone() {
		return FillStone;
	}

	public void setFillStone(Double fillStone) {
		FillStone = fillStone;
	}

	public Double getFillLocalSoil() {
		return FillLocalSoil;
	}

	public void setFillLocalSoil(Double fillLocalSoil) {
		FillLocalSoil = fillLocalSoil;
	}

	public Double getFillLocalStone() {
		return FillLocalStone;
	}

	public void setFillLocalStone(Double fillLocalStone) {
		FillLocalStone = fillLocalStone;
	}

	public Double getFillPortraitSoil() {
		return FillPortraitSoil;
	}

	public void setFillPortraitSoil(Double fillPortraitSoil) {
		FillPortraitSoil = fillPortraitSoil;
	}

	public Double getFillPortraitStone() {
		return FillPortraitStone;
	}

	public void setFillPortraitStone(Double fillPortraitStone) {
		FillPortraitStone = fillPortraitStone;
	}

	public Double getFillDebtorTotal() {
		return FillDebtorTotal;
	}

	public void setFillDebtorTotal(Double fillDebtorTotal) {
		FillDebtorTotal = fillDebtorTotal;
	}

	public Double getFillDebtorStone() {
		return FillDebtorStone;
	}

	public void setFillDebtorStone(Double fillDebtorStone) {
		FillDebtorStone = fillDebtorStone;
	}

	public Double getAbandonSoil() {
		return AbandonSoil;
	}

	public void setAbandonSoil(Double abandonSoil) {
		AbandonSoil = abandonSoil;
	}

	public Double getAbandonOldRoad() {
		return AbandonOldRoad;
	}

	public void setAbandonOldRoad(Double abandonOldRoad) {
		AbandonOldRoad = abandonOldRoad;
	}

	public Double getAbandonBrick() {
		return AbandonBrick;
	}

	public void setAbandonBrick(Double abandonBrick) {
		AbandonBrick = abandonBrick;
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
