package com.curing.projectSchedule.model;

public class PlateCulvertNumberEntity {

	// id 
	private String Id;
	// 项目id
	private String ProjectId;
	// 桩号
	private String PileNumber;
	// 上部板钢筋（Kg）
	private Double TopRebar;
	// 上部板C30混凝土（m³）
	private Double TopC30Concrete;
	// 上部桥面铺装钢筋（Kg）
	private Double TopDeckRebar;
	// 上部桥面铺装C40混凝土（m³）
	private Double TopDeckC40Concrete;
	// 上部桥面铺装防水（㎡）
	private Double TopDeckWaterproof;
	// 上部护栏基座钢筋（Kg）
	private Double TopGuardrailRebar;
	// 上部护栏基座C40混凝土（m³）
	private Double TopGuardrailC40Concrete;
	// 上部支座锚栓钢筋（Kg）
	private Double TopBearingRebar;
	// 上部支座锚栓C40混凝土（m³）
	private Double TopBearingC40Concrete;
	// M15浆砌片石台身（m³）
	private Double M15Rubble;
	// 下部台身M10浆砌片石台身（m³）
	private Double BottomRubbleBody;
	// 下部台身M10浆砌块石镶面（m³）
	private Double BottomRubbleVeneer;
	// 下部台帽及挡块钢筋（Kg）
	private Double BottomCapRebar;
	// 下部台帽及挡块C30混凝土（m³）
	private Double BottomCapC30Concrete;
	// 下部台基础钢筋（Kg）
	private Double BottomPlatformRebar;
	// 下部台基础C20片石（m³）
	private Double BottomPlatformC20Stone;
	// 下部台基础C20混凝土（m³）
	private Double BottomPlatformC20Concrete;
	// 下部支撑梁钢筋（Kg）
	private Double BottomBraceRebar;
	// 下部支撑梁混凝土（m³）
	private Double BottomBraceConcrete;
	// 防护工程八字墙C15片石混凝土基础（m³）
	private Double GuardC15Concrete;
	// 防护工程八字墙M10浆砌片石墙身（m³）
	private Double GuardM10RubbleBody;
	// 防护工程八字墙M10浆砌块石镶面（m³）
	private Double GuardM10RubbleVeneer;
	// 防护工程铺砌M7.5片石铺砌（m³）
	private Double GuardPaving;
	// 防护工程隔水墙M7.5号浆砌片石（m³）
	private Double GuardRubble;
	// 防护工程砂砾
	private Double GuardGravel;
	// 后台填筑
	private Double Backfilling;
	// 圬工
	private Double Masonry;
	// 挖基土方
	private Double Earthwork;
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
	public Double getTopRebar() {
		return TopRebar;
	}
	public void setTopRebar(Double topRebar) {
		TopRebar = topRebar;
	}
	public Double getTopC30Concrete() {
		return TopC30Concrete;
	}
	public void setTopC30Concrete(Double topC30Concrete) {
		TopC30Concrete = topC30Concrete;
	}
	public Double getTopDeckRebar() {
		return TopDeckRebar;
	}
	public void setTopDeckRebar(Double topDeckRebar) {
		TopDeckRebar = topDeckRebar;
	}
	public Double getTopDeckC40Concrete() {
		return TopDeckC40Concrete;
	}
	public void setTopDeckC40Concrete(Double topDeckC40Concrete) {
		TopDeckC40Concrete = topDeckC40Concrete;
	}
	public Double getTopDeckWaterproof() {
		return TopDeckWaterproof;
	}
	public void setTopDeckWaterproof(Double topDeckWaterproof) {
		TopDeckWaterproof = topDeckWaterproof;
	}
	public Double getTopGuardrailRebar() {
		return TopGuardrailRebar;
	}
	public void setTopGuardrailRebar(Double topGuardrailRebar) {
		TopGuardrailRebar = topGuardrailRebar;
	}
	public Double getTopGuardrailC40Concrete() {
		return TopGuardrailC40Concrete;
	}
	public void setTopGuardrailC40Concrete(Double topGuardrailC40Concrete) {
		TopGuardrailC40Concrete = topGuardrailC40Concrete;
	}
	public Double getTopBearingRebar() {
		return TopBearingRebar;
	}
	public void setTopBearingRebar(Double topBearingRebar) {
		TopBearingRebar = topBearingRebar;
	}
	public Double getTopBearingC40Concrete() {
		return TopBearingC40Concrete;
	}
	public void setTopBearingC40Concrete(Double topBearingC40Concrete) {
		TopBearingC40Concrete = topBearingC40Concrete;
	}
	public Double getM15Rubble() {
		return M15Rubble;
	}
	public void setM15Rubble(Double m15Rubble) {
		M15Rubble = m15Rubble;
	}
	public Double getBottomRubbleBody() {
		return BottomRubbleBody;
	}
	public void setBottomRubbleBody(Double bottomRubbleBody) {
		BottomRubbleBody = bottomRubbleBody;
	}
	public Double getBottomRubbleVeneer() {
		return BottomRubbleVeneer;
	}
	public void setBottomRubbleVeneer(Double bottomRubbleVeneer) {
		BottomRubbleVeneer = bottomRubbleVeneer;
	}
	public Double getBottomCapRebar() {
		return BottomCapRebar;
	}
	public void setBottomCapRebar(Double bottomCapRebar) {
		BottomCapRebar = bottomCapRebar;
	}
	public Double getBottomCapC30Concrete() {
		return BottomCapC30Concrete;
	}
	public void setBottomCapC30Concrete(Double bottomCapC30Concrete) {
		BottomCapC30Concrete = bottomCapC30Concrete;
	}
	public Double getBottomPlatformRebar() {
		return BottomPlatformRebar;
	}
	public void setBottomPlatformRebar(Double bottomPlatformRebar) {
		BottomPlatformRebar = bottomPlatformRebar;
	}
	public Double getBottomPlatformC20Stone() {
		return BottomPlatformC20Stone;
	}
	public void setBottomPlatformC20Stone(Double bottomPlatformC20Stone) {
		BottomPlatformC20Stone = bottomPlatformC20Stone;
	}
	public Double getBottomPlatformC20Concrete() {
		return BottomPlatformC20Concrete;
	}
	public void setBottomPlatformC20Concrete(Double bottomPlatformC20Concrete) {
		BottomPlatformC20Concrete = bottomPlatformC20Concrete;
	}
	public Double getBottomBraceRebar() {
		return BottomBraceRebar;
	}
	public void setBottomBraceRebar(Double bottomBraceRebar) {
		BottomBraceRebar = bottomBraceRebar;
	}
	public Double getBottomBraceConcrete() {
		return BottomBraceConcrete;
	}
	public void setBottomBraceConcrete(Double bottomBraceConcrete) {
		BottomBraceConcrete = bottomBraceConcrete;
	}
	public Double getGuardC15Concrete() {
		return GuardC15Concrete;
	}
	public void setGuardC15Concrete(Double guardC15Concrete) {
		GuardC15Concrete = guardC15Concrete;
	}
	public Double getGuardM10RubbleBody() {
		return GuardM10RubbleBody;
	}
	public void setGuardM10RubbleBody(Double guardM10RubbleBody) {
		GuardM10RubbleBody = guardM10RubbleBody;
	}
	public Double getGuardM10RubbleVeneer() {
		return GuardM10RubbleVeneer;
	}
	public void setGuardM10RubbleVeneer(Double guardM10RubbleVeneer) {
		GuardM10RubbleVeneer = guardM10RubbleVeneer;
	}
	public Double getGuardPaving() {
		return GuardPaving;
	}
	public void setGuardPaving(Double guardPaving) {
		GuardPaving = guardPaving;
	}
	public Double getGuardRubble() {
		return GuardRubble;
	}
	public void setGuardRubble(Double guardRubble) {
		GuardRubble = guardRubble;
	}
	public Double getGuardGravel() {
		return GuardGravel;
	}
	public void setGuardGravel(Double guardGravel) {
		GuardGravel = guardGravel;
	}
	public Double getBackfilling() {
		return Backfilling;
	}
	public void setBackfilling(Double backfilling) {
		Backfilling = backfilling;
	}
	public Double getMasonry() {
		return Masonry;
	}
	public void setMasonry(Double masonry) {
		Masonry = masonry;
	}
	public Double getEarthwork() {
		return Earthwork;
	}
	public void setEarthwork(Double earthwork) {
		Earthwork = earthwork;
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
