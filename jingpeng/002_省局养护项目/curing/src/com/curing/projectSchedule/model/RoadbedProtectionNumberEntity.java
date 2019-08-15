package com.curing.projectSchedule.model;

public class RoadbedProtectionNumberEntity {
	private String Id;
	private String ProjectId; // 项目id
	private String StartStopNumber; // 起止桩号
	private String Position; // 位置
	private String BuildNature; // 建设性质
	private String BuildNatureName; // 建设性质名
	private Double WallLength; // 墙长（m）
	private Double RaiseHeight; // 抬高高度（m）
	private Double AverageHigh; // 平均高（H）
	private Double StoneWallBody; // 片石墙身（m3）
	private Double StoneBasics; // 片石基础（m3）
	private Double Plastering; // 抹面（m3）
	private Double GravelCushion; // 砂砾垫层（m3）
	private Double OpenExcavation; // 开挖方（m3）
	private Double Length; // 长度（m）
	private Double BlockStone; // 块石（m3）
	private Double EightRebar; // 8mm钢筋（Kg）
	private Double Wire; // 铁丝（m)
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
	public String getStartStopNumber() {
		return StartStopNumber;
	}
	public void setStartStopNumber(String startStopNumber) {
		StartStopNumber = startStopNumber;
	}
	public String getPosition() {
		return Position;
	}
	public void setPosition(String position) {
		Position = position;
	}
	public String getBuildNature() {
		return BuildNature;
	}
	public void setBuildNature(String buildNature) {
		BuildNature = buildNature;
	}
	public Double getWallLength() {
		return WallLength;
	}
	public void setWallLength(Double wallLength) {
		WallLength = wallLength;
	}
	public Double getRaiseHeight() {
		return RaiseHeight;
	}
	public void setRaiseHeight(Double raiseHeight) {
		RaiseHeight = raiseHeight;
	}
	public Double getAverageHigh() {
		return AverageHigh;
	}
	public void setAverageHigh(Double averageHigh) {
		AverageHigh = averageHigh;
	}
	public Double getStoneWallBody() {
		return StoneWallBody;
	}
	public void setStoneWallBody(Double stoneWallBody) {
		StoneWallBody = stoneWallBody;
	}
	public Double getStoneBasics() {
		return StoneBasics;
	}
	public void setStoneBasics(Double stoneBasics) {
		StoneBasics = stoneBasics;
	}
	public Double getPlastering() {
		return Plastering;
	}
	public void setPlastering(Double plastering) {
		Plastering = plastering;
	}
	public Double getGravelCushion() {
		return GravelCushion;
	}
	public void setGravelCushion(Double gravelCushion) {
		GravelCushion = gravelCushion;
	}
	public Double getOpenExcavation() {
		return OpenExcavation;
	}
	public void setOpenExcavation(Double openExcavation) {
		OpenExcavation = openExcavation;
	}
	public Double getLength() {
		return Length;
	}
	public void setLength(Double length) {
		Length = length;
	}
	public Double getBlockStone() {
		return BlockStone;
	}
	public void setBlockStone(Double blockStone) {
		BlockStone = blockStone;
	}
	public Double getEightRebar() {
		return EightRebar;
	}
	public void setEightRebar(Double eightRebar) {
		EightRebar = eightRebar;
	}
	public Double getWire() {
		return Wire;
	}
	public void setWire(Double wire) {
		Wire = wire;
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
	public String getBuildNatureName() {
		return BuildNatureName;
	}
	public void setBuildNatureName(String buildNatureName) {
		BuildNatureName = buildNatureName;
	}
}
