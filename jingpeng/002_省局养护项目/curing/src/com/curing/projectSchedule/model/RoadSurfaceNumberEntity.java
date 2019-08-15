package com.curing.projectSchedule.model;

public class RoadSurfaceNumberEntity {
	private String Id;
	private String ProjectId; // 项目id
	private String PileNumber; // 起讫桩号
	private String BuildNature; // 建设性质
	private Double BuildLength; // 建筑长度（m）
	private Double Width; // 宽度（m）
	private Double FineGrain; // 4cm改性细粒式沥青砼（AC-13）面积（m2）
	private Double MediumGrain; // 6cm中粒式沥青砼（AC-20)面积（m2）
	private Double CoarseGrain; // 10cm粗粒式沥青砼（AC-25)面积（m2）
	private Double StickyLayer; // 粘层油面积（m2）
	private Double PermeableLayer; // 透层油面积（m2）
	private Double Seal; // 封层油面积（m2）
	private Double GravelUpper; // 20cm水泥稳定砂砾上基层面积（m2）
	private Double GravelLower; // 20cm水泥稳定砂砾下基层面积（m2）
	private Double GravelCushion; // 20cm天然砂砾垫层面积（m2）
	private Double ReinforceGravelUpper; // 补强层20cm水泥稳定砂砾上基层面积（m2）
	private Double ReinforceGravelLower; // 补强层20cm水泥稳定砂砾下基层面积（m2）
	private Double Permeable; // 60cm透水性材料换填面积（m2）
	private Double RoadMilling; // 路面铣刨h平均=4cm面积（m2）
	private Double UsedMilling; // 旧路铣刨（1cm）面积（m2）
	private Double Shoulder; // 培路肩面积（m2）
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

	public String getBuildNature() {
		return BuildNature;
	}

	public void setBuildNature(String buildNature) {
		BuildNature = buildNature;
	}

	public Double getBuildLength() {
		return BuildLength;
	}

	public void setBuildLength(Double buildLength) {
		BuildLength = buildLength;
	}

	public Double getWidth() {
		return Width;
	}

	public void setWidth(Double width) {
		Width = width;
	}

	public Double getFineGrain() {
		return FineGrain;
	}

	public void setFineGrain(Double fineGrain) {
		FineGrain = fineGrain;
	}

	public Double getMediumGrain() {
		return MediumGrain;
	}

	public void setMediumGrain(Double mediumGrain) {
		MediumGrain = mediumGrain;
	}

	public Double getCoarseGrain() {
		return CoarseGrain;
	}

	public void setCoarseGrain(Double coarseGrain) {
		CoarseGrain = coarseGrain;
	}

	public Double getStickyLayer() {
		return StickyLayer;
	}

	public void setStickyLayer(Double stickyLayer) {
		StickyLayer = stickyLayer;
	}

	public Double getPermeableLayer() {
		return PermeableLayer;
	}

	public void setPermeableLayer(Double permeableLayer) {
		PermeableLayer = permeableLayer;
	}

	public Double getSeal() {
		return Seal;
	}

	public void setSeal(Double seal) {
		Seal = seal;
	}

	public Double getGravelUpper() {
		return GravelUpper;
	}

	public void setGravelUpper(Double gravelUpper) {
		GravelUpper = gravelUpper;
	}

	public Double getGravelLower() {
		return GravelLower;
	}

	public void setGravelLower(Double gravelLower) {
		GravelLower = gravelLower;
	}

	public Double getGravelCushion() {
		return GravelCushion;
	}

	public void setGravelCushion(Double gravelCushion) {
		GravelCushion = gravelCushion;
	}

	public Double getReinforceGravelUpper() {
		return ReinforceGravelUpper;
	}

	public void setReinforceGravelUpper(Double reinforceGravelUpper) {
		ReinforceGravelUpper = reinforceGravelUpper;
	}

	public Double getReinforceGravelLower() {
		return ReinforceGravelLower;
	}

	public void setReinforceGravelLower(Double reinforceGravelLower) {
		ReinforceGravelLower = reinforceGravelLower;
	}

	public Double getPermeable() {
		return Permeable;
	}

	public void setPermeable(Double permeable) {
		Permeable = permeable;
	}

	public Double getRoadMilling() {
		return RoadMilling;
	}

	public void setRoadMilling(Double roadMilling) {
		RoadMilling = roadMilling;
	}

	public Double getUsedMilling() {
		return UsedMilling;
	}

	public void setUsedMilling(Double usedMilling) {
		UsedMilling = usedMilling;
	}

	public Double getShoulder() {
		return Shoulder;
	}

	public void setShoulder(Double shoulder) {
		Shoulder = shoulder;
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
