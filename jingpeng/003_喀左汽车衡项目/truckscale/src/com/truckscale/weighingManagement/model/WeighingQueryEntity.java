package com.truckscale.weighingManagement.model;

public class WeighingQueryEntity {
	private int Id;	
	private int CarId;//车牌号码
	private String CarNumber;//车辆编码
	private String PlateNumber;//车辆牌号
	private String TransportCompanyName;//运输单位
	private int FeedCompanyId;//供料单位id
	private String FeedCompanyName;//供料单位名称
	private int ReceiveUnitId;//收料单位id	
	private String ReceiveUnitName;//收料单位名称
	private int MaterielNameId;//材料名称id			
	private int MaterielModelId;//规格型号id			
	private String Deduction;//扣除量或扣除率（数据字典）
	private double NumericalValue;//数值
	private String DriverName;//司机姓名
	private String CarOwnerName;//车主名字
	private double GrossWeight;//毛重（t）			
	private int GrossWeightCount;//毛重称重次数
	private double TareWeight;//皮重（t）
	private int TareWeightCount;//皮重称重次数
	private double NetWeight;//净重（t）			
	private String EndAddress;//止运地	
	private String StartAddress;//起运地
	private double Temperature;//温度（℃）			
	private String Route;//路线，桩号
	private String RatioOfOil;//油石比
	private int OutOrEnter;//出/入库 0:出 1:入			
	private int IsFile;//是否归档 0:是 1:否	
	private int IsKeepVehicle;//是否保存车辆信息 0:是 1:否
	private int MeasurementMode;//计量方式 0:自动 1:手动
	private String Remarks;//备注
	private int IsDel;//删除标记	
	private String SerialId;//流水号
	private String Creater;//创建人
	private String CreaterDate;//创建日期			
	private String Reviser;//修改人
	private String ReviserDate;//修改日期	
	private String MaterielName;//材料名称		
	private String MaterielModel;//规格型号
	
	public String getPlateNumber() {
		return PlateNumber;
	}
	public void setPlateNumber(String plateNumber) {
		PlateNumber = plateNumber;
	}
	public int getId() {
		return Id;
	}
	public void setId(int id) {
		Id = id;
	}
	public int getCarId() {
		return CarId;
	}
	public void setCarId(int carId) {
		CarId = carId;
	}
	public String getCarNumber() {
		return CarNumber;
	}
	public void setCarNumber(String carNumber) {
		CarNumber = carNumber;
	}
	public String getTransportCompanyName() {
		return TransportCompanyName;
	}
	public void setTransportCompanyName(String transportCompanyName) {
		TransportCompanyName = transportCompanyName;
	}
	public int getFeedCompanyId() {
		return FeedCompanyId;
	}
	public void setFeedCompanyId(int feedCompanyId) {
		FeedCompanyId = feedCompanyId;
	}
	public String getFeedCompanyName() {
		return FeedCompanyName;
	}
	public void setFeedCompanyName(String feedCompanyName) {
		FeedCompanyName = feedCompanyName;
	}
	public int getReceiveUnitId() {
		return ReceiveUnitId;
	}
	public void setReceiveUnitId(int receiveUnitId) {
		ReceiveUnitId = receiveUnitId;
	}
	public String getReceiveUnitName() {
		return ReceiveUnitName;
	}
	public void setReceiveUnitName(String receiveUnitName) {
		ReceiveUnitName = receiveUnitName;
	}
	public int getMaterielNameId() {
		return MaterielNameId;
	}
	public void setMaterielNameId(int materielNameId) {
		MaterielNameId = materielNameId;
	}
	public int getMaterielModelId() {
		return MaterielModelId;
	}
	public void setMaterielModelId(int materielModelId) {
		MaterielModelId = materielModelId;
	}
	public String getDeduction() {
		return Deduction;
	}
	public void setDeduction(String deduction) {
		Deduction = deduction;
	}
	public double getNumericalValue() {
		return NumericalValue;
	}
	public void setNumericalValue(double numericalValue) {
		NumericalValue = numericalValue;
	}
	public String getDriverName() {
		return DriverName;
	}
	public void setDriverName(String driverName) {
		DriverName = driverName;
	}
	public String getCarOwnerName() {
		return CarOwnerName;
	}
	public void setCarOwnerName(String carOwnerName) {
		CarOwnerName = carOwnerName;
	}
	public double getGrossWeight() {
		return GrossWeight;
	}
	public void setGrossWeight(double grossWeight) {
		GrossWeight = grossWeight;
	}
	public int getGrossWeightCount() {
		return GrossWeightCount;
	}
	public void setGrossWeightCount(int grossWeightCount) {
		GrossWeightCount = grossWeightCount;
	}
	public double getTareWeight() {
		return TareWeight;
	}
	public void setTareWeight(double tareWeight) {
		TareWeight = tareWeight;
	}
	public int getTareWeightCount() {
		return TareWeightCount;
	}
	public void setTareWeightCount(int tareWeightCount) {
		TareWeightCount = tareWeightCount;
	}
	public double getNetWeight() {
		return NetWeight;
	}
	public void setNetWeight(double netWeight) {
		NetWeight = netWeight;
	}
	public String getEndAddress() {
		return EndAddress;
	}
	public void setEndAddress(String endAddress) {
		EndAddress = endAddress;
	}
	public String getStartAddress() {
		return StartAddress;
	}
	public void setStartAddress(String startAddress) {
		StartAddress = startAddress;
	}
	public double getTemperature() {
		return Temperature;
	}
	public void setTemperature(double temperature) {
		Temperature = temperature;
	}
	public String getRoute() {
		return Route;
	}
	public void setRoute(String route) {
		Route = route;
	}
	public String getRatioOfOil() {
		return RatioOfOil;
	}
	public void setRatioOfOil(String ratioOfOil) {
		RatioOfOil = ratioOfOil;
	}
	public int getOutOrEnter() {
		return OutOrEnter;
	}
	public void setOutOrEnter(int outOrEnter) {
		OutOrEnter = outOrEnter;
	}
	public int getIsFile() {
		return IsFile;
	}
	public void setIsFile(int isFile) {
		IsFile = isFile;
	}
	public int getIsKeepVehicle() {
		return IsKeepVehicle;
	}
	public void setIsKeepVehicle(int isKeepVehicle) {
		IsKeepVehicle = isKeepVehicle;
	}
	public int getMeasurementMode() {
		return MeasurementMode;
	}
	public void setMeasurementMode(int measurementMode) {
		MeasurementMode = measurementMode;
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
	public String getSerialId() {
		return SerialId;
	}
	public void setSerialId(String serialId) {
		SerialId = serialId;
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
	public String getMaterielName() {
		return MaterielName;
	}
	public void setMaterielName(String materielName) {
		MaterielName = materielName;
	}
	public String getMaterielModel() {
		return MaterielModel;
	}
	public void setMaterielModel(String materielModel) {
		MaterielModel = materielModel;
	}
	
}
