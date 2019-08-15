package com.curing.projectSchedule.model;

public class OverhaulPavementNumberEntity {
	private String Id;
	private String ProjectId; // 项目id
	private String PileNumber; // 起讫桩号
	private Double PavingLength; // 铺筑长度（米）
	private Double NewWidth; // 新建路面平均宽度（米）
	private String NewType; // 新建路面结构类型
	private Double NewThickness; // 新建路面平均厚度（厘米）
	private Double ReinforceWidth; // 补强路面平均宽度（米）
	private String ReinforceType; // 补强路面结构类型
	private Double ReinforceThickness; // 补强路面平均厚度（厘米）
	private Double ProjectNewRoad; // 工程数量新建路面（千平方米）
	private Double ProjectReinforceRoad; // 工程数量补强路面（千平方米）
	private Double ProjectStickyOil; // 工程数量粘层油（千平方米）
	private Double ProjectSlurrySeal; // 工程数量稀浆封层（千平方米）
	private Double ProjectRubber; // 工程数量橡胶沥青碎石封层（千平方米）
	private Double ProjectSurfaceCourse; // 工程数量铣刨旧路面层均厚7cm（千平方米）
	private Double ProjectPavementBase; // 工程数量铣刨旧路面基层（千平方米）
	private Double ProjectShoulderThickness; // 工程数量培路肩厚（厘米）
	private Double ProjectShoulderArea; // 工程数量培路肩面积（千平方米）
	private Double BridgeLength; // 桥长（米）
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

	public Double getPavingLength() {
		return PavingLength;
	}

	public void setPavingLength(Double pavingLength) {
		PavingLength = pavingLength;
	}

	public Double getNewWidth() {
		return NewWidth;
	}

	public void setNewWidth(Double newWidth) {
		NewWidth = newWidth;
	}

	public String getNewType() {
		return NewType;
	}

	public void setNewType(String newType) {
		NewType = newType;
	}

	public Double getNewThickness() {
		return NewThickness;
	}

	public void setNewThickness(Double newThickness) {
		NewThickness = newThickness;
	}

	public Double getReinforceWidth() {
		return ReinforceWidth;
	}

	public void setReinforceWidth(Double reinforceWidth) {
		ReinforceWidth = reinforceWidth;
	}

	public String getReinforceType() {
		return ReinforceType;
	}

	public void setReinforceType(String reinforceType) {
		ReinforceType = reinforceType;
	}

	public Double getReinforceThickness() {
		return ReinforceThickness;
	}

	public void setReinforceThickness(Double reinforceThickness) {
		ReinforceThickness = reinforceThickness;
	}

	public Double getProjectNewRoad() {
		return ProjectNewRoad;
	}

	public void setProjectNewRoad(Double projectNewRoad) {
		ProjectNewRoad = projectNewRoad;
	}

	public Double getProjectReinforceRoad() {
		return ProjectReinforceRoad;
	}

	public void setProjectReinforceRoad(Double projectReinforceRoad) {
		ProjectReinforceRoad = projectReinforceRoad;
	}

	public Double getProjectStickyOil() {
		return ProjectStickyOil;
	}

	public void setProjectStickyOil(Double projectStickyOil) {
		ProjectStickyOil = projectStickyOil;
	}

	public Double getProjectSlurrySeal() {
		return ProjectSlurrySeal;
	}

	public void setProjectSlurrySeal(Double projectSlurrySeal) {
		ProjectSlurrySeal = projectSlurrySeal;
	}

	public Double getProjectRubber() {
		return ProjectRubber;
	}

	public void setProjectRubber(Double projectRubber) {
		ProjectRubber = projectRubber;
	}

	public Double getProjectSurfaceCourse() {
		return ProjectSurfaceCourse;
	}

	public void setProjectSurfaceCourse(Double projectSurfaceCourse) {
		ProjectSurfaceCourse = projectSurfaceCourse;
	}

	public Double getProjectPavementBase() {
		return ProjectPavementBase;
	}

	public void setProjectPavementBase(Double projectPavementBase) {
		ProjectPavementBase = projectPavementBase;
	}

	public Double getProjectShoulderThickness() {
		return ProjectShoulderThickness;
	}

	public void setProjectShoulderThickness(Double projectShoulderThickness) {
		ProjectShoulderThickness = projectShoulderThickness;
	}

	public Double getProjectShoulderArea() {
		return ProjectShoulderArea;
	}

	public void setProjectShoulderArea(Double projectShoulderArea) {
		ProjectShoulderArea = projectShoulderArea;
	}

	public Double getBridgeLength() {
		return BridgeLength;
	}

	public void setBridgeLength(Double bridgeLength) {
		BridgeLength = bridgeLength;
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
