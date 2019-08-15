package com.curing.projectSchedule.model;

public class BridgeBottomEntity {

	// 主键id
	private String Id;
	// 项目id
	private String ProjectId;
	// 中心桩号
	private String CoreNumber;
	// 桥名
	private String BridgeName;
	// 支座、锚栓及垫石支座（块）
	private Double Bearing;
	// 支座、锚栓及垫石钢筋（Kg）
	private Double BearingRebar;
	// 支座、锚栓及垫石C40混凝土（m³）
	private Double BearingC40Concrete;
	// 台身C25混凝土（m³）
	private Double PlatformC25Concrete;
	// 台身钢筋（Kg）
	private Double PlatformRebar;
	// 台帽C30混凝土（m³）
	private Double CapC30Concrete;
	// 台帽钢筋（Kg）
	private Double CapRebar;
	// 挡块C30混凝土（m³）
	private Double BaffleC30Concrete;
	// 挡块钢筋（Kg）
	private Double BaffleRebar;
	// 台基础C25混凝土（m³）
	private Double BasicsC25Concrete;
	// 台基础C25混凝土（m³）
	private Double BasicsRebar;
	// 墩身C20混凝土（m³）
	private Double PierC20Concrete;
	// 墩身钢筋（Kg）
	private Double PierRebar;
	// 墩帽C30混凝土（m³）
	private Double PierCapC30Concrete;
	// 墩帽钢筋（Kg）
	private Double PierCapRebar;
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
	public Double getBearing() {
		return Bearing;
	}
	public void setBearing(Double bearing) {
		Bearing = bearing;
	}
	public Double getBearingRebar() {
		return BearingRebar;
	}
	public void setBearingRebar(Double bearingRebar) {
		BearingRebar = bearingRebar;
	}
	public Double getBearingC40Concrete() {
		return BearingC40Concrete;
	}
	public void setBearingC40Concrete(Double bearingC40Concrete) {
		BearingC40Concrete = bearingC40Concrete;
	}
	public Double getPlatformC25Concrete() {
		return PlatformC25Concrete;
	}
	public void setPlatformC25Concrete(Double platformC25Concrete) {
		PlatformC25Concrete = platformC25Concrete;
	}
	public Double getPlatformRebar() {
		return PlatformRebar;
	}
	public void setPlatformRebar(Double platformRebar) {
		PlatformRebar = platformRebar;
	}
	public Double getCapC30Concrete() {
		return CapC30Concrete;
	}
	public void setCapC30Concrete(Double capC30Concrete) {
		CapC30Concrete = capC30Concrete;
	}
	public Double getCapRebar() {
		return CapRebar;
	}
	public void setCapRebar(Double capRebar) {
		CapRebar = capRebar;
	}
	public Double getBaffleC30Concrete() {
		return BaffleC30Concrete;
	}
	public void setBaffleC30Concrete(Double baffleC30Concrete) {
		BaffleC30Concrete = baffleC30Concrete;
	}
	public Double getBaffleRebar() {
		return BaffleRebar;
	}
	public void setBaffleRebar(Double baffleRebar) {
		BaffleRebar = baffleRebar;
	}
	public Double getBasicsC25Concrete() {
		return BasicsC25Concrete;
	}
	public void setBasicsC25Concrete(Double basicsC25Concrete) {
		BasicsC25Concrete = basicsC25Concrete;
	}
	public Double getBasicsRebar() {
		return BasicsRebar;
	}
	public void setBasicsRebar(Double basicsRebar) {
		BasicsRebar = basicsRebar;
	}
	public Double getPierC20Concrete() {
		return PierC20Concrete;
	}
	public void setPierC20Concrete(Double pierC20Concrete) {
		PierC20Concrete = pierC20Concrete;
	}
	public Double getPierRebar() {
		return PierRebar;
	}
	public void setPierRebar(Double pierRebar) {
		PierRebar = pierRebar;
	}
	public Double getPierCapC30Concrete() {
		return PierCapC30Concrete;
	}
	public void setPierCapC30Concrete(Double pierCapC30Concrete) {
		PierCapC30Concrete = pierCapC30Concrete;
	}
	public Double getPierCapRebar() {
		return PierCapRebar;
	}
	public void setPierCapRebar(Double pierCapRebar) {
		PierCapRebar = pierCapRebar;
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
