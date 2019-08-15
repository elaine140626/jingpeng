package com.mixingStation.model.cement;
/**
 * 
 * @Title CementTheoPropDetailed(水泥理论配比明细表)
 * @author Administrator
 * @date 2019年3月6日
 */
public class CementTheoPropDetailed {
	private int id;                //自增长ID
	private int theoPropId;        //理论配比ID
	private int materialsId;       //材料Id
	private Double weight;         //材料用量
	private String materialOrigin; //材料产地
	private String manufacturer;   //生产厂家
	private int validFlag;         //有效标识
	private int upload;            //上传标识
	
	private String materialName;   //原材料名称
	private String materialModel;  //原材料型号
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getTheoPropId() {
		return theoPropId;
	}
	public void setTheoPropId(int theoPropId) {
		this.theoPropId = theoPropId;
	}
	public int getMaterialsId() {
		return materialsId;
	}
	public void setMaterialsId(int materialsId) {
		this.materialsId = materialsId;
	}
	public Double getWeight() {
		return weight;
	}
	public void setWeight(Double weight) {
		this.weight = weight;
	}
	public String getMaterialOrigin() {
		return materialOrigin;
	}
	public void setMaterialOrigin(String materialOrigin) {
		this.materialOrigin = materialOrigin;
	}
	public String getManufacturer() {
		return manufacturer;
	}
	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
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
	public String getMaterialName() {
		return materialName;
	}
	public void setMaterialName(String materialName) {
		this.materialName = materialName;
	}
	public String getMaterialModel() {
		return materialModel;
	}
	public void setMaterialModel(String materialModel) {
		this.materialModel = materialModel;
	}
	
}
