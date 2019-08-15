package com.mixingStation.model.asphalt;
/**
 * 
 * @Title EquipmentInfo(拌和设备表)
 * @author Administrator
 * @date 2019年2月12日
 */
public class EquipmentInfo {
	private int id;                 //自增长ID
	private int orgId;              //组织机构ID
	private String uploadModuleId;  //上传模块标识
	private String equipmentCode;   //拌和机标识
	private int equipmentType;      //拌和机类型  0 沥青拌和机,1水泥混凝土拌和机。2水泥稳拌和站
	private String equipmentNo;     //设备编号
	private String equipmentName;   //拌和机名称
	private String equipmentModel;  //拌和机型号
	private int equiState;          //设备状态 0 不在线,1在线但没有开启监控程序,2在线并开启监控程序,3开启监控程序下 突然计算机关闭或者拔出数据线
	private String stateTime;       //设备状态时间
	private String vendor;          //厂家
	private Double latitude;        //纬度
	private Double longitude;       //经度
	private String remarks;         //备注
	private String operator;        //创建人
	private String createDate;      //创建日期
	private String modifier;        //修改人
	private String modifyDate;      //修改日期
	private int validFlag;          //有效标识
	private int upload;             //上传标识
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
	public String getUploadModuleId() {
		return uploadModuleId;
	}
	public void setUploadModuleId(String uploadModuleId) {
		this.uploadModuleId = uploadModuleId;
	}
	public String getEquipmentCode() {
		return equipmentCode;
	}
	public void setEquipmentCode(String equipmentCode) {
		this.equipmentCode = equipmentCode;
	}
	public int getEquipmentType() {
		return equipmentType;
	}
	public void setEquipmentType(int equipmentType) {
		this.equipmentType = equipmentType;
	}
	public String getEquipmentNo() {
		return equipmentNo;
	}
	public void setEquipmentNo(String equipmentNo) {
		this.equipmentNo = equipmentNo;
	}
	public String getEquipmentName() {
		return equipmentName;
	}
	public void setEquipmentName(String equipmentName) {
		this.equipmentName = equipmentName;
	}
	public String getEquipmentModel() {
		return equipmentModel;
	}
	public void setEquipmentModel(String equipmentModel) {
		this.equipmentModel = equipmentModel;
	}
	public int getEquiState() {
		return equiState;
	}
	public void setEquiState(int equiState) {
		this.equiState = equiState;
	}
	public String getStateTime() {
		return stateTime;
	}
	public void setStateTime(String stateTime) {
		this.stateTime = stateTime;
	}
	public String getVendor() {
		return vendor;
	}
	public void setVendor(String vendor) {
		this.vendor = vendor;
	}
	public Double getLatitude() {
		return latitude;
	}
	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}
	public Double getLongitude() {
		return longitude;
	}
	public void setLongitude(Double longitude) {
		this.longitude = longitude;
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
	
}