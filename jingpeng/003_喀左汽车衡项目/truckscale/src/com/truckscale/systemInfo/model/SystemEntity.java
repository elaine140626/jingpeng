package com.truckscale.systemInfo.model;

public class SystemEntity {

	private int Id;
	private String TruckScaleType;		//汽车衡类型（数据字典）
	private String SerialPort;			//串口（数据字典）
	private String BaudRate;  			//波特率（数据字典）
	private String WeighingMethod; 		//称重方式（数据字典） 0:整车称重 1:分轴称重
	private String SaveSettings; 		//保存设置（数据字典） 0:仅保持 1:保存后自动打印（当称重数据完整）
	private String MaterialSelection;	//材料筛选（数据字典） 0:按供料单位筛选 1:不按供料单位筛选
	private int IsDel; 					//删除标记
	private String Creater; 			//创建人
	private String CreaterDate; 		//创建日期
	private String Reviser;				//修改人
	private String ReviserDate;			//修改日期
	public int getId() {
		return Id;
	}
	public void setId(int id) {
		Id = id;
	}
	public String getTruckScaleType() {
		return TruckScaleType;
	}
	public void setTruckScaleType(String truckScaleType) {
		TruckScaleType = truckScaleType;
	}
	public String getSerialPort() {
		return SerialPort;
	}
	public void setSerialPort(String serialPort) {
		SerialPort = serialPort;
	}
	public String getBaudRate() {
		return BaudRate;
	}
	public void setBaudRate(String baudRate) {
		BaudRate = baudRate;
	}
	public String getWeighingMethod() {
		return WeighingMethod;
	}
	public void setWeighingMethod(String weighingMethod) {
		WeighingMethod = weighingMethod;
	}
	public String getSaveSettings() {
		return SaveSettings;
	}
	public void setSaveSettings(String saveSettings) {
		SaveSettings = saveSettings;
	}
	public String getMaterialSelection() {
		return MaterialSelection;
	}
	public void setMaterialSelection(String materialSelection) {
		MaterialSelection = materialSelection;
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
