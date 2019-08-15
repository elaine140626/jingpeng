package com.curing.projectSchedule.model;

public class CulvertMaintenanceNumberEntity {
	private String Id;
	private String ProjectId; // 项目id
	private String PileNumber; // 原构造物情况桩号
	private String Aperture; // 原构造物情况孔径
	private Double Angle; // 原构造物情况角度
	private String Type; // 原构造物情况类型
	private Double Silt; // 淤泥（m³）
	private Double Concrete; // C30混凝土（m³）
	private Integer BondedRebars; // 长35cm间距30cmφ12植筋（根）
	private Double Rubble; // M10浆砌片石（m³）
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
	public String getAperture() {
		return Aperture;
	}
	public void setAperture(String aperture) {
		Aperture = aperture;
	}
	public Double getAngle() {
		return Angle;
	}
	public void setAngle(Double angle) {
		Angle = angle;
	}
	public String getType() {
		return Type;
	}
	public void setType(String type) {
		Type = type;
	}
	public Double getSilt() {
		return Silt;
	}
	public void setSilt(Double silt) {
		Silt = silt;
	}
	public Double getConcrete() {
		return Concrete;
	}
	public void setConcrete(Double concrete) {
		Concrete = concrete;
	}
	public Integer getBondedRebars() {
		return BondedRebars;
	}
	public void setBondedRebars(Integer bondedRebars) {
		BondedRebars = bondedRebars;
	}
	public Double getRubble() {
		return Rubble;
	}
	public void setRubble(Double rubble) {
		Rubble = rubble;
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
