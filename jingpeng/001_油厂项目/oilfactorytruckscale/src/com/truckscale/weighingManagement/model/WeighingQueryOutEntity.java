package com.truckscale.weighingManagement.model;

public class WeighingQueryOutEntity {
	
		private int Id;					//出库单Id		    
		private String Client;			//客户名称
		private String DispatchDate;	//调度时间
		private String PlateNumber;		//车牌号码
		private String MaterielName;	//产品名称
		private String MaterielModel;	//产品型号
		private String TareWeight;		//皮重
		private String TareMeasureTime;	//皮重称重时间
		private String GrossWeight;		//毛重
		private String GrossMeasureTime;//毛重称重时间
		private String Suttle;			//净重
		private String DeliveryMan;		//送货人
		private String DeliveryManPhone;//送货电话
		private int TransportBalance;	//是否客户自提
		private int OutType;			//出库状态
		private String CompanyName;		//关联公司名称
		private String Address;			//止运地
		private String FleetName;		//车队名称
		private String Driver1;			//司机名字
		private Double Temperature;		//温度
		private String FacingSlipNum;	//封签号1
		private String FacingSlipNum2;	//封签号2
		private String FacingSlipNum3;	//封签号3
		private String FacingSlipNum4;	//封签号4
		private String Reviser;			//修改人
		private String Remarks;		//备注
		private int IsRelation;     //是否关联其它公司 0是 1否
		private int IsExchange;     //是否兑换 0是 1否
		public int getId() {
			return Id;
		}
		public void setId(int id) {
			Id = id;
		}
		public String getClient() {
			return Client;
		}
		public void setClient(String client) {
			Client = client;
		}
		public String getDispatchDate() {
			return DispatchDate;
		}
		public void setDispatchDate(String dispatchDate) {
			DispatchDate = dispatchDate;
		}
		public String getPlateNumber() {
			return PlateNumber;
		}
		public void setPlateNumber(String plateNumber) {
			PlateNumber = plateNumber;
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
		public String getTareWeight() {
			return TareWeight;
		}
		public void setTareWeight(String tareWeight) {
			TareWeight = tareWeight;
		}
		public String getTareMeasureTime() {
			return TareMeasureTime;
		}
		public void setTareMeasureTime(String tareMeasureTime) {
			TareMeasureTime = tareMeasureTime;
		}
		public String getGrossWeight() {
			return GrossWeight;
		}
		public void setGrossWeight(String grossWeight) {
			GrossWeight = grossWeight;
		}
		public String getGrossMeasureTime() {
			return GrossMeasureTime;
		}
		public void setGrossMeasureTime(String grossMeasureTime) {
			GrossMeasureTime = grossMeasureTime;
		}
		public String getSuttle() {
			return Suttle;
		}
		public void setSuttle(String suttle) {
			Suttle = suttle;
		}
		public String getDeliveryMan() {
			return DeliveryMan;
		}
		public void setDeliveryMan(String deliveryMan) {
			DeliveryMan = deliveryMan;
		}
		public String getDeliveryManPhone() {
			return DeliveryManPhone;
		}
		public void setDeliveryManPhone(String deliveryManPhone) {
			DeliveryManPhone = deliveryManPhone;
		}
		public int getTransportBalance() {
			return TransportBalance;
		}
		public void setTransportBalance(int transportBalance) {
			TransportBalance = transportBalance;
		}
		public int getOutType() {
			return OutType;
		}
		public void setOutType(int outType) {
			OutType = outType;
		}
		public String getCompanyName() {
			return CompanyName;
		}
		public void setCompanyName(String companyName) {
			CompanyName = companyName;
		}
		public String getAddress() {
			return Address;
		}
		public void setAddress(String address) {
			Address = address;
		}
		public String getFleetName() {
			return FleetName;
		}
		public void setFleetName(String fleetName) {
			FleetName = fleetName;
		}
		public String getDriver1() {
			return Driver1;
		}
		public void setDriver1(String driver1) {
			Driver1 = driver1;
		}
		
		public Double getTemperature() {
			return Temperature;
		}
		public void setTemperature(Double temperature) {
			Temperature = temperature;
		}
		public String getFacingSlipNum() {
			return FacingSlipNum;
		}
		public void setFacingSlipNum(String facingSlipNum) {
			FacingSlipNum = facingSlipNum;
		}
		public String getFacingSlipNum2() {
			return FacingSlipNum2;
		}
		public void setFacingSlipNum2(String facingSlipNum2) {
			FacingSlipNum2 = facingSlipNum2;
		}
		public String getFacingSlipNum3() {
			return FacingSlipNum3;
		}
		public void setFacingSlipNum3(String facingSlipNum3) {
			FacingSlipNum3 = facingSlipNum3;
		}
		public String getFacingSlipNum4() {
			return FacingSlipNum4;
		}
		public void setFacingSlipNum4(String facingSlipNum4) {
			FacingSlipNum4 = facingSlipNum4;
		}
		public String getReviser() {
			return Reviser;
		}
		public void setReviser(String reviser) {
			Reviser = reviser;
		}
		public String getRemarks() {
			return Remarks;
		}
		public void setRemarks(String remarks) {
			Remarks = remarks;
		}
		public int getIsRelation() {
			return IsRelation;
		}
		public void setIsRelation(int isRelation) {
			IsRelation = isRelation;
		}
		public int getIsExchange() {
			return IsExchange;
		}
		public void setIsExchange(int isExchange) {
			IsExchange = isExchange;
		}
		
}
