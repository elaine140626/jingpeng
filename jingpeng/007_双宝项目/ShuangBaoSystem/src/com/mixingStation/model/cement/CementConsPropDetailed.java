package com.mixingStation.model.cement;
/**
 * 
 * @Title CementConsPropDetailed(水泥施工配比明细表)
 * @author Administrator
 * @date 2019年3月8日
 */
public class CementConsPropDetailed {
	private int id;                    //自增长ID
	private int consPropId;            //施工配比ID
	private int materialsId;           //材料Id
	private Double theoryWeight;       //理论配比量
	private Double constructionWeight; //施工配比量
	private Double moistureContent;    //含水率
	private String materialOrigin;     //材料产地
	private String manufacturer;       //生产厂家
	private int validFlag;             //有效标识
	private int upload;                //上传标识
	
	private String materialName;       //物料名称
	private String materialModel;      //物料型号
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getConsPropId() {
		return consPropId;
	}
	public void setConsPropId(int consPropId) {
		this.consPropId = consPropId;
	}
	public int getMaterialsId() {
		return materialsId;
	}
	public void setMaterialsId(int materialsId) {
		this.materialsId = materialsId;
	}
	public Double getTheoryWeight() {
		return theoryWeight;
	}
	public void setTheoryWeight(Double theoryWeight) {
		this.theoryWeight = theoryWeight;
	}
	public Double getConstructionWeight() {
		return constructionWeight;
	}
	public void setConstructionWeight(Double constructionWeight) {
		this.constructionWeight = constructionWeight;
	}
	public Double getMoistureContent() {
		return moistureContent;
	}
	public void setMoistureContent(Double moistureContent) {
		this.moistureContent = moistureContent;
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
