package com.curing.projectSchedule.model;

public class SideDitchNumberEntity {
	private String Id;
	private String ProjectId; // 项目id
	private String PileNumber; // 起讫桩号
	private String Position; // 位置
	private String Form; // 形式
	private Double Length; // 长度（m）
	private Double Foundation; // 挖基土方（m³）
	private Double Mortar; // 10号砂浆抹面（㎡）
	private Double SandCushion; // 10cm砂垫层（m³）
	private Double Rubble; // 7.5号浆砌片石（m³）
	private Double C20Concrete; // C20混凝土（m³）
	private Double CoverPlate; // 盖板（块）
	private Double EightRebar; // 8mm钢筋（Kg）
	private Double TwelveRebar; // 12mm钢筋（Kg）
	private Double C25Concrete; // C25混凝土（m³）
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

	public String getPosition() {
		return Position;
	}

	public void setPosition(String position) {
		Position = position;
	}

	public String getForm() {
		return Form;
	}

	public void setForm(String form) {
		Form = form;
	}

	public Double getLength() {
		return Length;
	}

	public void setLength(Double length) {
		Length = length;
	}

	public Double getFoundation() {
		return Foundation;
	}

	public void setFoundation(Double foundation) {
		Foundation = foundation;
	}

	public Double getMortar() {
		return Mortar;
	}

	public void setMortar(Double mortar) {
		Mortar = mortar;
	}

	public Double getSandCushion() {
		return SandCushion;
	}

	public void setSandCushion(Double sandCushion) {
		SandCushion = sandCushion;
	}

	public Double getRubble() {
		return Rubble;
	}

	public void setRubble(Double rubble) {
		Rubble = rubble;
	}

	public Double getC20Concrete() {
		return C20Concrete;
	}

	public void setC20Concrete(Double c20Concrete) {
		C20Concrete = c20Concrete;
	}

	public Double getCoverPlate() {
		return CoverPlate;
	}

	public void setCoverPlate(Double coverPlate) {
		CoverPlate = coverPlate;
	}

	public Double getEightRebar() {
		return EightRebar;
	}

	public void setEightRebar(Double eightRebar) {
		EightRebar = eightRebar;
	}

	public Double getTwelveRebar() {
		return TwelveRebar;
	}

	public void setTwelveRebar(Double twelveRebar) {
		TwelveRebar = twelveRebar;
	}

	public Double getC25Concrete() {
		return C25Concrete;
	}

	public void setC25Concrete(Double c25Concrete) {
		C25Concrete = c25Concrete;
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
