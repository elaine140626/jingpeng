package com.curing.projectSchedule.model;

public class DeckTreatmentNumberEntity {
	private String Id;
	private String ProjectId; // 项目id
	private String BridgeName; // 桥梁名称
	private String BridgeNumber; // 桥梁桩号
	private Double BridgeLength; // 桥梁长度（米）
	private Double BridgeWidth; // 桥面净宽（米）
	private Double FineThickness; // 温拌细粒厚度（厘米）
	private Double FineArea; // 温拌细粒面积（千平方米）
	private Double NeutralThickness; // 温拌中粒厚度（厘米）
	private Double NeutralArea; // 温拌中粒面积（千平方米）
	private Double RubberAsphaltGravel; // 橡胶沥青碎石封层（千平方米）
	private Double DeckThickness; // 铣刨桥面厚度（厘米）
	private Double DeckArea; // 铣刨桥面面积（千平方米）
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
	public String getBridgeName() {
		return BridgeName;
	}
	public void setBridgeName(String bridgeName) {
		BridgeName = bridgeName;
	}
	public String getBridgeNumber() {
		return BridgeNumber;
	}
	public void setBridgeNumber(String bridgeNumber) {
		BridgeNumber = bridgeNumber;
	}
	public Double getBridgeLength() {
		return BridgeLength;
	}
	public void setBridgeLength(Double bridgeLength) {
		BridgeLength = bridgeLength;
	}
	public Double getBridgeWidth() {
		return BridgeWidth;
	}
	public void setBridgeWidth(Double bridgeWidth) {
		BridgeWidth = bridgeWidth;
	}
	public Double getFineThickness() {
		return FineThickness;
	}
	public void setFineThickness(Double fineThickness) {
		FineThickness = fineThickness;
	}
	public Double getFineArea() {
		return FineArea;
	}
	public void setFineArea(Double fineArea) {
		FineArea = fineArea;
	}
	public Double getNeutralThickness() {
		return NeutralThickness;
	}
	public void setNeutralThickness(Double neutralThickness) {
		NeutralThickness = neutralThickness;
	}
	public Double getNeutralArea() {
		return NeutralArea;
	}
	public void setNeutralArea(Double neutralArea) {
		NeutralArea = neutralArea;
	}
	public Double getRubberAsphaltGravel() {
		return RubberAsphaltGravel;
	}
	public void setRubberAsphaltGravel(Double rubberAsphaltGravel) {
		RubberAsphaltGravel = rubberAsphaltGravel;
	}
	public Double getDeckThickness() {
		return DeckThickness;
	}
	public void setDeckThickness(Double deckThickness) {
		DeckThickness = deckThickness;
	}
	public Double getDeckArea() {
		return DeckArea;
	}
	public void setDeckArea(Double deckArea) {
		DeckArea = deckArea;
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
