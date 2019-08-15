package com.MixStation.model;
/**
 * 
 * @Title AsphaltGradDetailedEntity(级配筛孔通过率)
 * @author ygt
 * @date 2019年7月17日
 */
public class AsphaltGradDetailedEntity {
	
	private int  id;             // 自增长id
	private int  gradId;         // 级配主表id
	private Double ware1;        // 1#仓
	private Double ware2;        // 2#仓
	private Double ware3;        // 3#仓
	private Double ware4;        // 4#仓
	private Double ware5;        // 5#仓
	private Double ware6;        // 6#仓
	private Double coldPowder1;  // 冷粉1 
	private Double coldPowder2;  // 冷粉2
	private Double hotPowder;    // 热粉
	private Double upperLimit;   // 级配上限值
	private Double lowerLimit;   // 级配下限值
	private int validFlag;       // 是否有效：1有效，0无效
	private int meshId;          // 筛孔id
	private String operator;     // 创建者
	private String createDate;   // 创建时间
	private String modifier;     // 修改人
	private String modifyDate;   // 修改时间
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getGradId() {
		return gradId;
	}
	public void setGradId(int gradId) {
		this.gradId = gradId;
	}
	public Double getWare1() {
		return ware1;
	}
	public void setWare1(Double ware1) {
		this.ware1 = ware1;
	}
	public Double getWare2() {
		return ware2;
	}
	public void setWare2(Double ware2) {
		this.ware2 = ware2;
	}
	public Double getWare3() {
		return ware3;
	}
	public void setWare3(Double ware3) {
		this.ware3 = ware3;
	}
	public Double getWare4() {
		return ware4;
	}
	public void setWare4(Double ware4) {
		this.ware4 = ware4;
	}
	public Double getWare5() {
		return ware5;
	}
	public void setWare5(Double ware5) {
		this.ware5 = ware5;
	}
	public Double getWare6() {
		return ware6;
	}
	public void setWare6(Double ware6) {
		this.ware6 = ware6;
	}
	public Double getColdPowder1() {
		return coldPowder1;
	}
	public void setColdPowder1(Double coldPowder1) {
		this.coldPowder1 = coldPowder1;
	}
	public Double getColdPowder2() {
		return coldPowder2;
	}
	public void setColdPowder2(Double coldPowder2) {
		this.coldPowder2 = coldPowder2;
	}
	public Double getHotPowder() {
		return hotPowder;
	}
	public void setHotPowder(Double hotPowder) {
		this.hotPowder = hotPowder;
	}
	public Double getUpperLimit() {
		return upperLimit;
	}
	public void setUpperLimit(Double upperLimit) {
		this.upperLimit = upperLimit;
	}
	public Double getLowerLimit() {
		return lowerLimit;
	}
	public void setLowerLimit(Double lowerLimit) {
		this.lowerLimit = lowerLimit;
	}
	public int getValidFlag() {
		return validFlag;
	}
	public void setValidFlag(int validFlag) {
		this.validFlag = validFlag;
	}
	public int getMeshId() {
		return meshId;
	}
	public void setMeshId(int meshId) {
		this.meshId = meshId;
	}
	public String getOperator() {
		return operator;
	}
	public void setOperator(String operator) {
		this.operator = operator;
	}
	public String getCreateDate() {
		return createDate;
	}
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
	public String getModifier() {
		return modifier;
	}
	public void setModifier(String modifier) {
		this.modifier = modifier;
	}
	public String getModifyDate() {
		return modifyDate;
	}
	public void setModifyDate(String modifyDate) {
		this.modifyDate = modifyDate;
	}
	
}
