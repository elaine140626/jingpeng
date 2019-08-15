package com.testRoom.model;

// 实际施工完成量表
public class ActualCompletionQuantity {
	private Integer Id;
    private String UniqueIdentifier;            // 试验室唯一标识
    private String TestRoomName;                // 试验室名称
    private Double Subgrade_Fill;               // 路基_填方实际完成量
    private Double Subgrade_Excavation;         // 路基_挖方实际完成量
    private Double Subgrade_Total;              // 路基_总量实际完成量
    private Double sumSubgrade_Total; 
    private Double Cushion_LeftAmplitude;       // 垫层_左幅实际完成量
    private Double sumCushion_LeftAmplitude; 
    private Double Cushion_RightAmplitude;      // 垫层_右幅实际完成量
    private Double sumCushion_RightAmplitude; 
    private Double Subbase_LeftAmplitude;       // 底基层_左幅实际完成量
    private Double sumSubbase_LeftAmplitude; 
    private Double Subbase_RightAmplitude;      // 底基层_右幅实际完成量
    private Double sumSubbase_RightAmplitude; 
    private Double Substrate_LeftAmplitude;     // 基层_左幅实际完成量
    private Double sumSubstrate_LeftAmplitude; 
    private Double Substrate_RightAmplitude;    // 基层_右幅实际完成量
    private Double sumSubstrate_RightAmplitude; 
    private Double LowerLayer_LeftAmplitude;    // 下面层_左幅实际完成量
    private Double sumLowerLayer_LeftAmplitude; 
    private Double LowerLayer_RightAmplitude;   // 下面层_右幅实际完成量
    private Double sumLowerLayer_RightAmplitude; 
    private Double Mesosphere_LeftAmplitude;    // 中面层_左幅实际完成量
    private Double sumMesosphere_LeftAmplitude; 
    private Double Mesosphere_RightAmplitude;   // 中面层_右幅实际完成量
    private Double sumMesosphere_RightAmplitude; 
    private Double UpperLayer_LeftAmplitude;    // 上面层_左幅实际完成量
    private Double sumUpperLayer_LeftAmplitude; 
    private Double UpperLayer_RightAmplitude;   // 上面层_右幅实际完成量
    private Double sumUpperLayer_RightAmplitude; 
    private String PicturePath;                 // 图片路径
    private Double Subgrade_Fill_1;                  // 路基_填方
	private String Subgrade_FillUnit;                // 路基_填方单位
	private Double Subgrade_Excavation_1;            // 路基_挖方
	private String Subgrade_ExcavationUnit;          // 路基_挖方单位
	private Double Subgrade_Total_1;                 // 路基_总量
	private String Subgrade_TotalUnit;               // 路基_总量单位
	private Double Cushion_LeftAmplitude_1;          // 垫层_左幅
	private String Cushion_LeftAmplitudeUnit;        // 垫层_左幅单位
	private Double Cushion_RightAmplitude_1;         // 垫层_右幅
	private String Cushion_RightAmplitudeUnit;       // 垫层_右幅单位
	private Double Subbase_LeftAmplitude_1;          // 底基层_左幅
	private String Subbase_LeftAmplitudeUnit;        // 底基层_左幅单位
	private Double Subbase_RightAmplitude_1;         // 底基层_右幅
	private String Subbase_RightAmplitudeUnit;       // 底基层_右幅单位
	private Double Substrate_LeftAmplitude_1;        // 基层_左幅
	private String Substrate_LeftAmplitudeUnit;      // 基层_左幅单位
	private Double Substrate_RightAmplitude_1;       // 基层_右幅
	private String Substrate_RightAmplitudeUnit;     // 基层_右幅单位
	private Double LowerLayer_LeftAmplitude_1;       // 下面层_左幅
	private String LowerLayer_LeftAmplitudeUnit;     // 下面层_左幅单位
	private Double LowerLayer_RightAmplitude_1;      // 下面层_右幅
	private String LowerLayer_RightAmplitudeUnit;    // 下面层_右幅单位
	private Double Mesosphere_LeftAmplitude_1;       // 中面层_左幅
	private String Mesosphere_LeftAmplitudeUnit;     // 中面层_左幅单位
	private Double Mesosphere_RightAmplitude_1;      // 中面层_右幅
	private String Mesosphere_RightAmplitudeUnit;    // 中面层_右幅单位
	private Double UpperLayer_LeftAmplitude_1;       // 上面层_左幅
	private String UpperLayer_LeftAmplitudeUnit;     // 上面层_左幅单位
	private Double UpperLayer_RightAmplitude_1;      // 上面层_右幅
	private String UpperLayer_RightAmplitudeUnit;    // 上面层_右幅单位
    private int Isdel;
    private String Creater;
    private String CreaterDate;
    private String Reviser;
    private String ReviserDate;
	public Integer getId() {
		return Id;
	}
	public void setId(Integer id) {
		Id = id;
	}
	public String getUniqueIdentifier() {
		return UniqueIdentifier;
	}
	public void setUniqueIdentifier(String uniqueIdentifier) {
		UniqueIdentifier = uniqueIdentifier;
	}
	public String getTestRoomName() {
		return TestRoomName;
	}
	public void setTestRoomName(String testRoomName) {
		TestRoomName = testRoomName;
	}
	public Double getSubgrade_Fill() {
		return Subgrade_Fill;
	}
	public void setSubgrade_Fill(Double subgrade_Fill) {
		Subgrade_Fill = subgrade_Fill;
	}
	public Double getSubgrade_Excavation() {
		return Subgrade_Excavation;
	}
	public void setSubgrade_Excavation(Double subgrade_Excavation) {
		Subgrade_Excavation = subgrade_Excavation;
	}
	public Double getSubgrade_Total() {
		return Subgrade_Total;
	}
	public void setSubgrade_Total(Double subgrade_Total) {
		Subgrade_Total = subgrade_Total;
	}
	public Double getCushion_LeftAmplitude() {
		return Cushion_LeftAmplitude;
	}
	public void setCushion_LeftAmplitude(Double cushion_LeftAmplitude) {
		Cushion_LeftAmplitude = cushion_LeftAmplitude;
	}
	public Double getCushion_RightAmplitude() {
		return Cushion_RightAmplitude;
	}
	public void setCushion_RightAmplitude(Double cushion_RightAmplitude) {
		Cushion_RightAmplitude = cushion_RightAmplitude;
	}
	public Double getSubbase_LeftAmplitude() {
		return Subbase_LeftAmplitude;
	}
	public void setSubbase_LeftAmplitude(Double subbase_LeftAmplitude) {
		Subbase_LeftAmplitude = subbase_LeftAmplitude;
	}
	public Double getSubbase_RightAmplitude() {
		return Subbase_RightAmplitude;
	}
	public void setSubbase_RightAmplitude(Double subbase_RightAmplitude) {
		Subbase_RightAmplitude = subbase_RightAmplitude;
	}
	public Double getSubstrate_LeftAmplitude() {
		return Substrate_LeftAmplitude;
	}
	public void setSubstrate_LeftAmplitude(Double substrate_LeftAmplitude) {
		Substrate_LeftAmplitude = substrate_LeftAmplitude;
	}
	public Double getSubstrate_RightAmplitude() {
		return Substrate_RightAmplitude;
	}
	public void setSubstrate_RightAmplitude(Double substrate_RightAmplitude) {
		Substrate_RightAmplitude = substrate_RightAmplitude;
	}
	public Double getLowerLayer_LeftAmplitude() {
		return LowerLayer_LeftAmplitude;
	}
	public void setLowerLayer_LeftAmplitude(Double lowerLayer_LeftAmplitude) {
		LowerLayer_LeftAmplitude = lowerLayer_LeftAmplitude;
	}
	public Double getLowerLayer_RightAmplitude() {
		return LowerLayer_RightAmplitude;
	}
	public void setLowerLayer_RightAmplitude(Double lowerLayer_RightAmplitude) {
		LowerLayer_RightAmplitude = lowerLayer_RightAmplitude;
	}
	public Double getMesosphere_LeftAmplitude() {
		return Mesosphere_LeftAmplitude;
	}
	public void setMesosphere_LeftAmplitude(Double mesosphere_LeftAmplitude) {
		Mesosphere_LeftAmplitude = mesosphere_LeftAmplitude;
	}
	public Double getMesosphere_RightAmplitude() {
		return Mesosphere_RightAmplitude;
	}
	public void setMesosphere_RightAmplitude(Double mesosphere_RightAmplitude) {
		Mesosphere_RightAmplitude = mesosphere_RightAmplitude;
	}
	public Double getUpperLayer_LeftAmplitude() {
		return UpperLayer_LeftAmplitude;
	}
	public void setUpperLayer_LeftAmplitude(Double upperLayer_LeftAmplitude) {
		UpperLayer_LeftAmplitude = upperLayer_LeftAmplitude;
	}
	public Double getUpperLayer_RightAmplitude() {
		return UpperLayer_RightAmplitude;
	}
	public void setUpperLayer_RightAmplitude(Double upperLayer_RightAmplitude) {
		UpperLayer_RightAmplitude = upperLayer_RightAmplitude;
	}
	public String getPicturePath() {
		return PicturePath;
	}
	public void setPicturePath(String picturePath) {
		PicturePath = picturePath;
	}
	public Double getSubgrade_Fill_1() {
		return Subgrade_Fill_1;
	}
	public void setSubgrade_Fill_1(Double subgrade_Fill_1) {
		Subgrade_Fill_1 = subgrade_Fill_1;
	}
	public String getSubgrade_FillUnit() {
		return Subgrade_FillUnit;
	}
	public void setSubgrade_FillUnit(String subgrade_FillUnit) {
		Subgrade_FillUnit = subgrade_FillUnit;
	}
	public Double getSubgrade_Excavation_1() {
		return Subgrade_Excavation_1;
	}
	public void setSubgrade_Excavation_1(Double subgrade_Excavation_1) {
		Subgrade_Excavation_1 = subgrade_Excavation_1;
	}
	public String getSubgrade_ExcavationUnit() {
		return Subgrade_ExcavationUnit;
	}
	public void setSubgrade_ExcavationUnit(String subgrade_ExcavationUnit) {
		Subgrade_ExcavationUnit = subgrade_ExcavationUnit;
	}
	public Double getSubgrade_Total_1() {
		return Subgrade_Total_1;
	}
	public void setSubgrade_Total_1(Double subgrade_Total_1) {
		Subgrade_Total_1 = subgrade_Total_1;
	}
	public String getSubgrade_TotalUnit() {
		return Subgrade_TotalUnit;
	}
	public void setSubgrade_TotalUnit(String subgrade_TotalUnit) {
		Subgrade_TotalUnit = subgrade_TotalUnit;
	}
	public Double getCushion_LeftAmplitude_1() {
		return Cushion_LeftAmplitude_1;
	}
	public void setCushion_LeftAmplitude_1(Double cushion_LeftAmplitude_1) {
		Cushion_LeftAmplitude_1 = cushion_LeftAmplitude_1;
	}
	public String getCushion_LeftAmplitudeUnit() {
		return Cushion_LeftAmplitudeUnit;
	}
	public void setCushion_LeftAmplitudeUnit(String cushion_LeftAmplitudeUnit) {
		Cushion_LeftAmplitudeUnit = cushion_LeftAmplitudeUnit;
	}
	public Double getCushion_RightAmplitude_1() {
		return Cushion_RightAmplitude_1;
	}
	public void setCushion_RightAmplitude_1(Double cushion_RightAmplitude_1) {
		Cushion_RightAmplitude_1 = cushion_RightAmplitude_1;
	}
	public String getCushion_RightAmplitudeUnit() {
		return Cushion_RightAmplitudeUnit;
	}
	public void setCushion_RightAmplitudeUnit(String cushion_RightAmplitudeUnit) {
		Cushion_RightAmplitudeUnit = cushion_RightAmplitudeUnit;
	}
	public Double getSubbase_LeftAmplitude_1() {
		return Subbase_LeftAmplitude_1;
	}
	public void setSubbase_LeftAmplitude_1(Double subbase_LeftAmplitude_1) {
		Subbase_LeftAmplitude_1 = subbase_LeftAmplitude_1;
	}
	public String getSubbase_LeftAmplitudeUnit() {
		return Subbase_LeftAmplitudeUnit;
	}
	public void setSubbase_LeftAmplitudeUnit(String subbase_LeftAmplitudeUnit) {
		Subbase_LeftAmplitudeUnit = subbase_LeftAmplitudeUnit;
	}
	public Double getSubbase_RightAmplitude_1() {
		return Subbase_RightAmplitude_1;
	}
	public void setSubbase_RightAmplitude_1(Double subbase_RightAmplitude_1) {
		Subbase_RightAmplitude_1 = subbase_RightAmplitude_1;
	}
	public String getSubbase_RightAmplitudeUnit() {
		return Subbase_RightAmplitudeUnit;
	}
	public void setSubbase_RightAmplitudeUnit(String subbase_RightAmplitudeUnit) {
		Subbase_RightAmplitudeUnit = subbase_RightAmplitudeUnit;
	}
	public Double getSubstrate_LeftAmplitude_1() {
		return Substrate_LeftAmplitude_1;
	}
	public void setSubstrate_LeftAmplitude_1(Double substrate_LeftAmplitude_1) {
		Substrate_LeftAmplitude_1 = substrate_LeftAmplitude_1;
	}
	public String getSubstrate_LeftAmplitudeUnit() {
		return Substrate_LeftAmplitudeUnit;
	}
	public void setSubstrate_LeftAmplitudeUnit(String substrate_LeftAmplitudeUnit) {
		Substrate_LeftAmplitudeUnit = substrate_LeftAmplitudeUnit;
	}
	public Double getSubstrate_RightAmplitude_1() {
		return Substrate_RightAmplitude_1;
	}
	public void setSubstrate_RightAmplitude_1(Double substrate_RightAmplitude_1) {
		Substrate_RightAmplitude_1 = substrate_RightAmplitude_1;
	}
	public String getSubstrate_RightAmplitudeUnit() {
		return Substrate_RightAmplitudeUnit;
	}
	public void setSubstrate_RightAmplitudeUnit(String substrate_RightAmplitudeUnit) {
		Substrate_RightAmplitudeUnit = substrate_RightAmplitudeUnit;
	}
	public Double getLowerLayer_LeftAmplitude_1() {
		return LowerLayer_LeftAmplitude_1;
	}
	public void setLowerLayer_LeftAmplitude_1(Double lowerLayer_LeftAmplitude_1) {
		LowerLayer_LeftAmplitude_1 = lowerLayer_LeftAmplitude_1;
	}
	public String getLowerLayer_LeftAmplitudeUnit() {
		return LowerLayer_LeftAmplitudeUnit;
	}
	public void setLowerLayer_LeftAmplitudeUnit(String lowerLayer_LeftAmplitudeUnit) {
		LowerLayer_LeftAmplitudeUnit = lowerLayer_LeftAmplitudeUnit;
	}
	public Double getLowerLayer_RightAmplitude_1() {
		return LowerLayer_RightAmplitude_1;
	}
	public void setLowerLayer_RightAmplitude_1(Double lowerLayer_RightAmplitude_1) {
		LowerLayer_RightAmplitude_1 = lowerLayer_RightAmplitude_1;
	}
	public String getLowerLayer_RightAmplitudeUnit() {
		return LowerLayer_RightAmplitudeUnit;
	}
	public void setLowerLayer_RightAmplitudeUnit(String lowerLayer_RightAmplitudeUnit) {
		LowerLayer_RightAmplitudeUnit = lowerLayer_RightAmplitudeUnit;
	}
	public Double getMesosphere_LeftAmplitude_1() {
		return Mesosphere_LeftAmplitude_1;
	}
	public void setMesosphere_LeftAmplitude_1(Double mesosphere_LeftAmplitude_1) {
		Mesosphere_LeftAmplitude_1 = mesosphere_LeftAmplitude_1;
	}
	public String getMesosphere_LeftAmplitudeUnit() {
		return Mesosphere_LeftAmplitudeUnit;
	}
	public void setMesosphere_LeftAmplitudeUnit(String mesosphere_LeftAmplitudeUnit) {
		Mesosphere_LeftAmplitudeUnit = mesosphere_LeftAmplitudeUnit;
	}
	public Double getMesosphere_RightAmplitude_1() {
		return Mesosphere_RightAmplitude_1;
	}
	public void setMesosphere_RightAmplitude_1(Double mesosphere_RightAmplitude_1) {
		Mesosphere_RightAmplitude_1 = mesosphere_RightAmplitude_1;
	}
	public String getMesosphere_RightAmplitudeUnit() {
		return Mesosphere_RightAmplitudeUnit;
	}
	public void setMesosphere_RightAmplitudeUnit(String mesosphere_RightAmplitudeUnit) {
		Mesosphere_RightAmplitudeUnit = mesosphere_RightAmplitudeUnit;
	}
	public Double getUpperLayer_LeftAmplitude_1() {
		return UpperLayer_LeftAmplitude_1;
	}
	public void setUpperLayer_LeftAmplitude_1(Double upperLayer_LeftAmplitude_1) {
		UpperLayer_LeftAmplitude_1 = upperLayer_LeftAmplitude_1;
	}
	public String getUpperLayer_LeftAmplitudeUnit() {
		return UpperLayer_LeftAmplitudeUnit;
	}
	public void setUpperLayer_LeftAmplitudeUnit(String upperLayer_LeftAmplitudeUnit) {
		UpperLayer_LeftAmplitudeUnit = upperLayer_LeftAmplitudeUnit;
	}
	public Double getUpperLayer_RightAmplitude_1() {
		return UpperLayer_RightAmplitude_1;
	}
	public void setUpperLayer_RightAmplitude_1(Double upperLayer_RightAmplitude_1) {
		UpperLayer_RightAmplitude_1 = upperLayer_RightAmplitude_1;
	}
	public String getUpperLayer_RightAmplitudeUnit() {
		return UpperLayer_RightAmplitudeUnit;
	}
	public void setUpperLayer_RightAmplitudeUnit(String upperLayer_RightAmplitudeUnit) {
		UpperLayer_RightAmplitudeUnit = upperLayer_RightAmplitudeUnit;
	}
	public int getIsdel() {
		return Isdel;
	}
	public void setIsdel(int isdel) {
		Isdel = isdel;
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
	public Double getSumSubgrade_Total() {
		return sumSubgrade_Total;
	}
	public void setSumSubgrade_Total(Double sumSubgrade_Total) {
		this.sumSubgrade_Total = sumSubgrade_Total;
	}
	public Double getSumCushion_LeftAmplitude() {
		return sumCushion_LeftAmplitude;
	}
	public void setSumCushion_LeftAmplitude(Double sumCushion_LeftAmplitude) {
		this.sumCushion_LeftAmplitude = sumCushion_LeftAmplitude;
	}
	public Double getSumCushion_RightAmplitude() {
		return sumCushion_RightAmplitude;
	}
	public void setSumCushion_RightAmplitude(Double sumCushion_RightAmplitude) {
		this.sumCushion_RightAmplitude = sumCushion_RightAmplitude;
	}
	public Double getSumSubbase_LeftAmplitude() {
		return sumSubbase_LeftAmplitude;
	}
	public void setSumSubbase_LeftAmplitude(Double sumSubbase_LeftAmplitude) {
		this.sumSubbase_LeftAmplitude = sumSubbase_LeftAmplitude;
	}
	public Double getSumSubbase_RightAmplitude() {
		return sumSubbase_RightAmplitude;
	}
	public void setSumSubbase_RightAmplitude(Double sumSubbase_RightAmplitude) {
		this.sumSubbase_RightAmplitude = sumSubbase_RightAmplitude;
	}
	public Double getSumSubstrate_LeftAmplitude() {
		return sumSubstrate_LeftAmplitude;
	}
	public void setSumSubstrate_LeftAmplitude(Double sumSubstrate_LeftAmplitude) {
		this.sumSubstrate_LeftAmplitude = sumSubstrate_LeftAmplitude;
	}
	public Double getSumSubstrate_RightAmplitude() {
		return sumSubstrate_RightAmplitude;
	}
	public void setSumSubstrate_RightAmplitude(Double sumSubstrate_RightAmplitude) {
		this.sumSubstrate_RightAmplitude = sumSubstrate_RightAmplitude;
	}
	public Double getSumLowerLayer_LeftAmplitude() {
		return sumLowerLayer_LeftAmplitude;
	}
	public void setSumLowerLayer_LeftAmplitude(Double sumLowerLayer_LeftAmplitude) {
		this.sumLowerLayer_LeftAmplitude = sumLowerLayer_LeftAmplitude;
	}
	public Double getSumLowerLayer_RightAmplitude() {
		return sumLowerLayer_RightAmplitude;
	}
	public void setSumLowerLayer_RightAmplitude(Double sumLowerLayer_RightAmplitude) {
		this.sumLowerLayer_RightAmplitude = sumLowerLayer_RightAmplitude;
	}
	public Double getSumMesosphere_LeftAmplitude() {
		return sumMesosphere_LeftAmplitude;
	}
	public void setSumMesosphere_LeftAmplitude(Double sumMesosphere_LeftAmplitude) {
		this.sumMesosphere_LeftAmplitude = sumMesosphere_LeftAmplitude;
	}
	public Double getSumMesosphere_RightAmplitude() {
		return sumMesosphere_RightAmplitude;
	}
	public void setSumMesosphere_RightAmplitude(Double sumMesosphere_RightAmplitude) {
		this.sumMesosphere_RightAmplitude = sumMesosphere_RightAmplitude;
	}
	public Double getSumUpperLayer_LeftAmplitude() {
		return sumUpperLayer_LeftAmplitude;
	}
	public void setSumUpperLayer_LeftAmplitude(Double sumUpperLayer_LeftAmplitude) {
		this.sumUpperLayer_LeftAmplitude = sumUpperLayer_LeftAmplitude;
	}
	public Double getSumUpperLayer_RightAmplitude() {
		return sumUpperLayer_RightAmplitude;
	}
	public void setSumUpperLayer_RightAmplitude(Double sumUpperLayer_RightAmplitude) {
		this.sumUpperLayer_RightAmplitude = sumUpperLayer_RightAmplitude;
	} 
}
