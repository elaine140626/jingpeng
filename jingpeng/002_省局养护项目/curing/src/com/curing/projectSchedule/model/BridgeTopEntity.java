package com.curing.projectSchedule.model;

public class BridgeTopEntity {
	private String Id;
	// 项目id
	private String ProjectId;
	// 中心桩号
	private String CoreNumber;
	// 桥名
	private String BridgeName;
	// 上部板φj15.2钢绞线（Kg）
	private Double SteelStrand;
	// 上部板减震垫块（块）
	private Double CushionBlock;
	// 上部板钢筋（Kg）
	private Double UpRebar;
	// 上部板C30混凝土（m³）
	private Double UpC30Concrete;
	// 上部板C40混凝土（m³）
	private Double UpC40Concrete;
	// 桥面铺装、钝角加强及铰缝钢筋（Kg）
	private Double DeckRebar;
	// 桥面铺装、钝角加强及铰缝C40混凝土（m³）
	private Double DeckC40Concrete;
	// 桥面铺装、钝角加强及铰缝防水层（㎡）
	private Double DeckWaterproof;
	// 桥面连续钢筋（Kg）
	private Double DeckContinuityRebar;
	// 防撞墙/栏杆钢筋（Kg）
	private Double RailingRebar;
	// 防撞墙/栏杆C40混凝土（m³）
	private Double RailingC40Concrete;
	// 防撞墙/栏杆钢材（Kg）
	private Double RailingSteels;
	// 防撞墙/栏杆防腐彩色涂料（㎡）
	private Double RailingCoating;
	// 泄水管套
	private Double DrainPipeSleeve;
	// 备注
	private String Remarks;
	private int IsDel;
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
	public String getCoreNumber() {
		return CoreNumber;
	}
	public void setCoreNumber(String coreNumber) {
		CoreNumber = coreNumber;
	}
	public String getBridgeName() {
		return BridgeName;
	}
	public void setBridgeName(String bridgeName) {
		BridgeName = bridgeName;
	}
	public Double getSteelStrand() {
		return SteelStrand;
	}
	public void setSteelStrand(Double steelStrand) {
		SteelStrand = steelStrand;
	}
	public Double getCushionBlock() {
		return CushionBlock;
	}
	public void setCushionBlock(Double cushionBlock) {
		CushionBlock = cushionBlock;
	}
	public Double getUpRebar() {
		return UpRebar;
	}
	public void setUpRebar(Double upRebar) {
		UpRebar = upRebar;
	}
	public Double getUpC30Concrete() {
		return UpC30Concrete;
	}
	public void setUpC30Concrete(Double upC30Concrete) {
		UpC30Concrete = upC30Concrete;
	}
	public Double getUpC40Concrete() {
		return UpC40Concrete;
	}
	public void setUpC40Concrete(Double upC40Concrete) {
		UpC40Concrete = upC40Concrete;
	}
	public Double getDeckRebar() {
		return DeckRebar;
	}
	public void setDeckRebar(Double deckRebar) {
		DeckRebar = deckRebar;
	}
	public Double getDeckC40Concrete() {
		return DeckC40Concrete;
	}
	public void setDeckC40Concrete(Double deckC40Concrete) {
		DeckC40Concrete = deckC40Concrete;
	}
	public Double getDeckWaterproof() {
		return DeckWaterproof;
	}
	public void setDeckWaterproof(Double deckWaterproof) {
		DeckWaterproof = deckWaterproof;
	}
	public Double getDeckContinuityRebar() {
		return DeckContinuityRebar;
	}
	public void setDeckContinuityRebar(Double deckContinuityRebar) {
		DeckContinuityRebar = deckContinuityRebar;
	}
	public Double getRailingRebar() {
		return RailingRebar;
	}
	public void setRailingRebar(Double railingRebar) {
		RailingRebar = railingRebar;
	}
	public Double getRailingC40Concrete() {
		return RailingC40Concrete;
	}
	public void setRailingC40Concrete(Double railingC40Concrete) {
		RailingC40Concrete = railingC40Concrete;
	}
	public Double getRailingSteels() {
		return RailingSteels;
	}
	public void setRailingSteels(Double railingSteels) {
		RailingSteels = railingSteels;
	}
	public Double getRailingCoating() {
		return RailingCoating;
	}
	public void setRailingCoating(Double railingCoating) {
		RailingCoating = railingCoating;
	}
	public Double getDrainPipeSleeve() {
		return DrainPipeSleeve;
	}
	public void setDrainPipeSleeve(Double drainPipeSleeve) {
		DrainPipeSleeve = drainPipeSleeve;
	}
	public String getRemarks() {
		return Remarks;
	}
	public void setRemarks(String remarks) {
		Remarks = remarks;
	}
	public int getIsDel() {
		return IsDel;
	}
	public void setIsDel(int isDel) {
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
