package com.curing.projectSchedule.model;

public class CrossRoadbedNumberEntity {
	private String Id;
	private String ProjectId; // 项目id
	private String PileNumber; // 中心桩号及起讫桩号
	private String CrossRoadName; // 被交叉路名称
	private String AcrossForm; // 交叉形式
	private Double Corner; // 交角（°）
	private Double WidthLeft; // 被交叉道路宽度左
	private Double WidthRight; // 被交叉道路宽度右
	private Double CornerRadiusR1; // 转弯半径R1（m）
	private Double CornerRadiusR2; // 转弯半径R2（m）
	private Double RoadWidth; // 路面宽度
	private Double RebuildLength; // 改建长度
	private Double Concrete; // 工程数量4cm细粒式沥青混凝土
	private Double ModifiedAsphalt; // 工程数量4cm改性沥青砼
	private Double OrdinaryAsphalt; // 工程数量6cm普通沥青砼
	private Double StickyLayer; // 工程数量粘层油
	private Double PermeableLayer; // 工程数量透层油
	private Double Seal; // 工程数量封层油
	private Double CementGravel20cm; // 工程数量20cm水泥稳定砂砾
	private Double NaturalGravel15cm; // 工程数量15cm天然砂砾
	private Double NaturalGravel20cm; // 工程数量20cm天然砂砾
	private Double Excavation; // 工程数量挖方
	private Double Fill; // 工程数量填方
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

	public String getCrossRoadName() {
		return CrossRoadName;
	}

	public void setCrossRoadName(String crossRoadName) {
		CrossRoadName = crossRoadName;
	}

	public String getAcrossForm() {
		return AcrossForm;
	}

	public void setAcrossForm(String acrossForm) {
		AcrossForm = acrossForm;
	}

	public Double getCorner() {
		return Corner;
	}

	public void setCorner(Double corner) {
		Corner = corner;
	}

	public Double getWidthLeft() {
		return WidthLeft;
	}

	public void setWidthLeft(Double widthLeft) {
		WidthLeft = widthLeft;
	}

	public Double getWidthRight() {
		return WidthRight;
	}

	public void setWidthRight(Double widthRight) {
		WidthRight = widthRight;
	}

	public Double getCornerRadiusR1() {
		return CornerRadiusR1;
	}

	public void setCornerRadiusR1(Double cornerRadiusR1) {
		CornerRadiusR1 = cornerRadiusR1;
	}

	public Double getCornerRadiusR2() {
		return CornerRadiusR2;
	}

	public void setCornerRadiusR2(Double cornerRadiusR2) {
		CornerRadiusR2 = cornerRadiusR2;
	}

	public Double getRoadWidth() {
		return RoadWidth;
	}

	public void setRoadWidth(Double roadWidth) {
		RoadWidth = roadWidth;
	}

	public Double getRebuildLength() {
		return RebuildLength;
	}

	public void setRebuildLength(Double rebuildLength) {
		RebuildLength = rebuildLength;
	}

	public Double getConcrete() {
		return Concrete;
	}

	public void setConcrete(Double concrete) {
		Concrete = concrete;
	}

	public Double getModifiedAsphalt() {
		return ModifiedAsphalt;
	}

	public void setModifiedAsphalt(Double modifiedAsphalt) {
		ModifiedAsphalt = modifiedAsphalt;
	}

	public Double getOrdinaryAsphalt() {
		return OrdinaryAsphalt;
	}

	public void setOrdinaryAsphalt(Double ordinaryAsphalt) {
		OrdinaryAsphalt = ordinaryAsphalt;
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

	public Double getCementGravel20cm() {
		return CementGravel20cm;
	}

	public void setCementGravel20cm(Double cementGravel20cm) {
		CementGravel20cm = cementGravel20cm;
	}

	public Double getNaturalGravel15cm() {
		return NaturalGravel15cm;
	}

	public void setNaturalGravel15cm(Double naturalGravel15cm) {
		NaturalGravel15cm = naturalGravel15cm;
	}

	public Double getNaturalGravel20cm() {
		return NaturalGravel20cm;
	}

	public void setNaturalGravel20cm(Double naturalGravel20cm) {
		NaturalGravel20cm = naturalGravel20cm;
	}

	public Double getExcavation() {
		return Excavation;
	}

	public void setExcavation(Double excavation) {
		Excavation = excavation;
	}

	public Double getFill() {
		return Fill;
	}

	public void setFill(Double fill) {
		Fill = fill;
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
