package com.curing.projectSchedule.model;

public class BridgeRetainingWallNumberSum {
	private Double WallLengthSum; // 挡墙长
	private Double StoneBasicsSum; // M10片石基础（m³）
	private Double StoneWallBodySum; // M10片石墙身（m³）
	private Double BlockStoneSum; // M10浆砌块石镶面（m³）
	private Double ExcavationSum; // 挖方（m³）
	private Double BackfillGravelSum; // 回填透水性砾料（m³）

	public Double getWallLengthSum() {
		return WallLengthSum;
	}

	public void setWallLengthSum(Double wallLengthSum) {
		WallLengthSum = wallLengthSum;
	}

	public Double getStoneBasicsSum() {
		return StoneBasicsSum;
	}

	public void setStoneBasicsSum(Double stoneBasicsSum) {
		StoneBasicsSum = stoneBasicsSum;
	}

	public Double getStoneWallBodySum() {
		return StoneWallBodySum;
	}

	public void setStoneWallBodySum(Double stoneWallBodySum) {
		StoneWallBodySum = stoneWallBodySum;
	}

	public Double getBlockStoneSum() {
		return BlockStoneSum;
	}

	public void setBlockStoneSum(Double blockStoneSum) {
		BlockStoneSum = blockStoneSum;
	}

	public Double getExcavationSum() {
		return ExcavationSum;
	}

	public void setExcavationSum(Double excavationSum) {
		ExcavationSum = excavationSum;
	}

	public Double getBackfillGravelSum() {
		return BackfillGravelSum;
	}

	public void setBackfillGravelSum(Double backfillGravelSum) {
		BackfillGravelSum = backfillGravelSum;
	}

}
