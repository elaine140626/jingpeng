package com.mixingStation.model.cement;


/**
 * 水泥料仓对应关系
 * 
 * @author Administrator
 *
 */
public class Bunker_Correspondence{
	private int id;//
	private int orgId;// 组织机构ID
	private int equId;// 拌和机Id
	private int consPropId;// 施工配比ID
	private String bunkerCode;//料仓对应关系编号
	private int mateIdCement1;// 水泥仓1
	private int mateIdCement2;// 水泥仓2
	private int mateIdCement3;// 水泥仓3
	private int mateIdCement4;// 水泥仓4
	private int mateIdAggregate1;// 骨料仓1
	private int mateIdAggregate2;// 骨料仓2
	private int mateIdAggregate3;// 骨料仓3
	private int mateIdAggregate4;// 骨料仓4
	private int mateIdAggregate5;// 骨料仓5
	private int mateIdAggregate6;// 骨料仓6
	private int mateIdWater;// 水仓
	private int mateIdAdmixture1;// 外掺剂1仓
	private int mateIdAdmixture2;// 外掺剂2仓
	private String effectiveTime;// 生效时间
	private String remarks;// 备注
	private String operator;// 创建人
	private String createDate;// 创建日期
	private String modifier;// 修改人
	private String modifyDate;// 修改日期
	private String materialName;//产品名称
	private String materialMold;//物料类别
	private int validFlag;// 有效标识
	private int upload;// 上传标识
	private String Material;//产品名称-型号
	private String propCode;//施工配合比编号
	private String equipmentName;//拌和设备名称
	private String operate;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getOrgId() {
		return orgId;
	}
	public void setOrgId(int orgId) {
		this.orgId = orgId;
	}
	public int getEquId() {
		return equId;
	}
	public void setEquId(int equId) {
		this.equId = equId;
	}
	public int getConsPropId() {
		return consPropId;
	}
	public void setConsPropId(int consPropId) {
		this.consPropId = consPropId;
	}
	public String getBunkerCode() {
		return bunkerCode;
	}
	public void setBunkerCode(String bunkerCode) {
		this.bunkerCode = bunkerCode;
	}
	public int getMateIdCement1() {
		return mateIdCement1;
	}
	public void setMateIdCement1(int mateIdCement1) {
		this.mateIdCement1 = mateIdCement1;
	}
	public int getMateIdCement2() {
		return mateIdCement2;
	}
	public void setMateIdCement2(int mateIdCement2) {
		this.mateIdCement2 = mateIdCement2;
	}
	public int getMateIdCement3() {
		return mateIdCement3;
	}
	public void setMateIdCement3(int mateIdCement3) {
		this.mateIdCement3 = mateIdCement3;
	}
	public int getMateIdCement4() {
		return mateIdCement4;
	}
	public void setMateIdCement4(int mateIdCement4) {
		this.mateIdCement4 = mateIdCement4;
	}
	public int getMateIdAggregate1() {
		return mateIdAggregate1;
	}
	public void setMateIdAggregate1(int mateIdAggregate1) {
		this.mateIdAggregate1 = mateIdAggregate1;
	}
	public int getMateIdAggregate2() {
		return mateIdAggregate2;
	}
	public void setMateIdAggregate2(int mateIdAggregate2) {
		this.mateIdAggregate2 = mateIdAggregate2;
	}
	public int getMateIdAggregate3() {
		return mateIdAggregate3;
	}
	public void setMateIdAggregate3(int mateIdAggregate3) {
		this.mateIdAggregate3 = mateIdAggregate3;
	}
	public int getMateIdAggregate4() {
		return mateIdAggregate4;
	}
	public void setMateIdAggregate4(int mateIdAggregate4) {
		this.mateIdAggregate4 = mateIdAggregate4;
	}
	public int getMateIdAggregate5() {
		return mateIdAggregate5;
	}
	public void setMateIdAggregate5(int mateIdAggregate5) {
		this.mateIdAggregate5 = mateIdAggregate5;
	}
	public int getMateIdAggregate6() {
		return mateIdAggregate6;
	}
	public void setMateIdAggregate6(int mateIdAggregate6) {
		this.mateIdAggregate6 = mateIdAggregate6;
	}
	public int getMateIdWater() {
		return mateIdWater;
	}
	public void setMateIdWater(int mateIdWater) {
		this.mateIdWater = mateIdWater;
	}
	public int getMateIdAdmixture1() {
		return mateIdAdmixture1;
	}
	public void setMateIdAdmixture1(int mateIdAdmixture1) {
		this.mateIdAdmixture1 = mateIdAdmixture1;
	}
	public int getMateIdAdmixture2() {
		return mateIdAdmixture2;
	}
	public void setMateIdAdmixture2(int mateIdAdmixture2) {
		this.mateIdAdmixture2 = mateIdAdmixture2;
	}
	public String getEffectiveTime() {
		return effectiveTime;
	}
	public void setEffectiveTime(String effectiveTime) {
		this.effectiveTime = effectiveTime;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
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
	public String getMaterialName() {
		return materialName;
	}
	public void setMaterialName(String materialName) {
		this.materialName = materialName;
	}
	public String getMaterialMold() {
		return materialMold;
	}
	public void setMaterialMold(String materialMold) {
		this.materialMold = materialMold;
	}
	public int getValidFlag() {
		return validFlag;
	}
	public void setValidFlag(int validFlag) {
		this.validFlag = validFlag;
	}
	public int getUpload() {
		return upload;
	}
	public void setUpload(int upload) {
		this.upload = upload;
	}
	public String getMaterial() {
		return Material;
	}
	public void setMaterial(String material) {
		Material = material;
	}
	public String getPropCode() {
		return propCode;
	}
	public void setPropCode(String propCode) {
		this.propCode = propCode;
	}
	public String getEquipmentName() {
		return equipmentName;
	}
	public void setEquipmentName(String equipmentName) {
		this.equipmentName = equipmentName;
	}
	public String getOperate() {
		return operate;
	}
	public void setOperate(String operate) {
		this.operate = operate;
	}
	
}
