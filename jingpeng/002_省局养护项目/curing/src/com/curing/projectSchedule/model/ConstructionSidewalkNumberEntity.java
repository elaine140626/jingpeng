package com.curing.projectSchedule.model;

public class ConstructionSidewalkNumberEntity {
	private String Id;
	private String ProjectId; // 项目id
	private String PileNumber; // 起讫桩号
	private Double Length; // 长度
	private String BuildNature; // 建设性质
	private Double RoadWidth; // 路面宽度
	private Double OrdinaryConcrete4cm; // 4cm普通沥青混凝土（AC-13）
	private Double ModificationConcrete4cm; // 4cm改性沥青混凝土（AC-13)
	private Double ModificationConcrete5cm; // 5cm改性沥青混凝土（AC-16）
	private Double OrdinaryConcrete7cm; // 7cm普通沥青混凝土（AC-25）
	private Double GravelSeal; // 橡胶沥青碎石封层0.6cm
	private Double StickyLayer; // 粘油层
	private Double PermeableLayer; // 透油层
	private Double MillingThick; // 铣刨旧路厚4cm
	private Double DigOut; // 新建结构挖除旧路
	private Double GravelCushion; // 新建结构20cm天然砂砾垫层
	private Double GravelUpper; // 新建结构20cm水泥稳定砂砾上基层
	private Double GravelLower; // 新建结构20cm水泥稳定砂砾下基层
	private Double Shoulder; // 培路肩厚5cm
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

	public Double getLength() {
		return Length;
	}

	public void setLength(Double length) {
		Length = length;
	}

	public String getBuildNature() {
		return BuildNature;
	}

	public void setBuildNature(String buildNature) {
		BuildNature = buildNature;
	}

	public Double getRoadWidth() {
		return RoadWidth;
	}

	public void setRoadWidth(Double roadWidth) {
		RoadWidth = roadWidth;
	}

	public Double getOrdinaryConcrete4cm() {
		return OrdinaryConcrete4cm;
	}

	public void setOrdinaryConcrete4cm(Double ordinaryConcrete4cm) {
		OrdinaryConcrete4cm = ordinaryConcrete4cm;
	}

	public Double getModificationConcrete4cm() {
		return ModificationConcrete4cm;
	}

	public void setModificationConcrete4cm(Double modificationConcrete4cm) {
		ModificationConcrete4cm = modificationConcrete4cm;
	}

	public Double getModificationConcrete5cm() {
		return ModificationConcrete5cm;
	}

	public void setModificationConcrete5cm(Double modificationConcrete5cm) {
		ModificationConcrete5cm = modificationConcrete5cm;
	}

	public Double getOrdinaryConcrete7cm() {
		return OrdinaryConcrete7cm;
	}

	public void setOrdinaryConcrete7cm(Double ordinaryConcrete7cm) {
		OrdinaryConcrete7cm = ordinaryConcrete7cm;
	}

	public Double getGravelSeal() {
		return GravelSeal;
	}

	public void setGravelSeal(Double gravelSeal) {
		GravelSeal = gravelSeal;
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

	public Double getMillingThick() {
		return MillingThick;
	}

	public void setMillingThick(Double millingThick) {
		MillingThick = millingThick;
	}

	public Double getDigOut() {
		return DigOut;
	}

	public void setDigOut(Double digOut) {
		DigOut = digOut;
	}

	public Double getGravelCushion() {
		return GravelCushion;
	}

	public void setGravelCushion(Double gravelCushion) {
		GravelCushion = gravelCushion;
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
