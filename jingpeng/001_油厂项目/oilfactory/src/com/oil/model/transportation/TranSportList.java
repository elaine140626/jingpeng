package com.oil.model.transportation;

public class TranSportList {

	private int id;
	private int dispatchOutWarehouseId; //出库单id
	private String transportParty; //运输方
	private String saleType; //结算类别（数据字典） 0:出厂运输 1:入厂运输 2:未进厂运输
	private String carOwner; //车主
	private String carOwnerTelephone; //联系电话
	private String billNumber; //票据编号
	private String company; //送达单位/采购单位
	private int materielId; //物料名称
	private int model; //物料型号
	private double weigh; //称重重量
	private double collectWeigh; //收货重量
	private double riseLoss; //涨亏吨
	private double kilometre; //公里数
	private double settlementKilometre; //结算公里数
	private String startAddress; //起运地
	private String endAddress; //止运地
	private String remarks; //备注
	private double freightPrice; //运费单价
	private double freightMoney; //运费金额
	private String plateNumber; //车牌号码
	private int escortDays; //押车天数
	private double escortMoney; //押车金额
	private int isDel; //删除标记
	private String creater; //创建人
	private String createrDate; //创建日期
	private String reviser; //修改人
	private String reviserDate; //修改日期
	private int rowCount;
	private String Driver1;
	private String Driver2;
	private String Telephone1;
	private String Telephone2;
	private String FactoryTime; ////出厂时间
	private String EnterDate;	//入厂时间
	private String goOutTime; //出入厂时间
	private String serialID;
	private String operate;//操作
	private String transportRoute;
	private String type;
	
	
	
	
	public String getEnterDate() {
		return EnterDate;
	}
	public void setEnterDate(String enterDate) {
		EnterDate = enterDate;
	}
	public String getGoOutTime() {
		return goOutTime;
	}
	public void setGoOutTime(String goOutTime) {
		this.goOutTime = goOutTime;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getTransportRoute() {
		return transportRoute;
	}
	public void setTransportRoute(String transportRoute) {
		this.transportRoute = transportRoute;
	}
	public String getSerialID() {
		return serialID;
	}
	public void setSerialID(String serialID) {
		this.serialID = serialID;
	}
	public String getOperate() {
		return operate;
	}
	public void setOperate(String operate) {
		this.operate = operate;
	}
	public String getDriver1() {
		return Driver1;
	}
	public void setDriver1(String driver1) {
		Driver1 = driver1;
	}
	public String getDriver2() {
		return Driver2;
	}
	public void setDriver2(String driver2) {
		Driver2 = driver2;
	}
	public String getTelephone1() {
		return Telephone1;
	}
	public void setTelephone1(String telephone1) {
		Telephone1 = telephone1;
	}
	public String getTelephone2() {
		return Telephone2;
	}
	public void setTelephone2(String telephone2) {
		Telephone2 = telephone2;
	}
	public String getFactoryTime() {
		return FactoryTime;
	}
	public void setFactoryTime(String factoryTime) {
		FactoryTime = factoryTime;
	}
	public int getRowCount() {
		return rowCount;
	}
	public void setRowCount(int rowCount) {
		this.rowCount = rowCount;
	}
	public double getSettlementKilometre() {
		return settlementKilometre;
	}
	public void setSettlementKilometre(double settlementKilometre) {
		this.settlementKilometre = settlementKilometre;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getDispatchOutWarehouseId() {
		return dispatchOutWarehouseId;
	}
	public void setDispatchOutWarehouseId(int dispatchOutWarehouseId) {
		this.dispatchOutWarehouseId = dispatchOutWarehouseId;
	}
	public String getTransportParty() {
		return transportParty;
	}
	public void setTransportParty(String transportParty) {
		this.transportParty = transportParty;
	}
	public String getSaleType() {
		return saleType;
	}
	public void setSaleType(String saleType) {
		this.saleType = saleType;
	}
	public String getCarOwner() {
		return carOwner;
	}
	public void setCarOwner(String carOwner) {
		this.carOwner = carOwner;
	}
	public String getCarOwnerTelephone() {
		return carOwnerTelephone;
	}
	public void setCarOwnerTelephone(String carOwnerTelephone) {
		this.carOwnerTelephone = carOwnerTelephone;
	}
	public String getBillNumber() {
		return billNumber;
	}
	public void setBillNumber(String billNumber) {
		this.billNumber = billNumber;
	}
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public int getMaterielId() {
		return materielId;
	}
	public void setMaterielId(int materielId) {
		this.materielId = materielId;
	}
	public int getModel() {
		return model;
	}
	public void setModel(int model) {
		this.model = model;
	}
	public double getWeigh() {
		return weigh;
	}
	public void setWeigh(double weigh) {
		this.weigh = weigh;
	}
	public double getCollectWeigh() {
		return collectWeigh;
	}
	public void setCollectWeigh(double collectWeigh) {
		this.collectWeigh = collectWeigh;
	}
	public double getRiseLoss() {
		return riseLoss;
	}
	public void setRiseLoss(double riseLoss) {
		this.riseLoss = riseLoss;
	}
	public double getKilometre() {
		return kilometre;
	}
	public void setKilometre(double kilometre) {
		this.kilometre = kilometre;
	}
	
	public String getStartAddress() {
		return startAddress;
	}
	public void setStartAddress(String startAddress) {
		this.startAddress = startAddress;
	}
	public String getEndAddress() {
		return endAddress;
	}
	public void setEndAddress(String endAddress) {
		this.endAddress = endAddress;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public double getFreightPrice() {
		return freightPrice;
	}
	public void setFreightPrice(double freightPrice) {
		this.freightPrice = freightPrice;
	}
	public double getFreightMoney() {
		return freightMoney;
	}
	public void setFreightMoney(double freightMoney) {
		this.freightMoney = freightMoney;
	}
	public String getPlateNumber() {
		return plateNumber;
	}
	public void setPlateNumber(String plateNumber) {
		this.plateNumber = plateNumber;
	}
	public int getEscortDays() {
		return escortDays;
	}
	public void setEscortDays(int escortDays) {
		this.escortDays = escortDays;
	}
	public double getEscortMoney() {
		return escortMoney;
	}
	public void setEscortMoney(double escortMoney) {
		this.escortMoney = escortMoney;
	}
	public int getIsDel() {
		return isDel;
	}
	public void setIsDel(int isDel) {
		this.isDel = isDel;
	}
	public String getCreater() {
		return creater;
	}
	public void setCreater(String creater) {
		this.creater = creater;
	}
	public String getCreaterDate() {
		return createrDate;
	}
	public void setCreaterDate(String createrDate) {
		this.createrDate = createrDate;
	}
	public String getReviser() {
		return reviser;
	}
	public void setReviser(String reviser) {
		this.reviser = reviser;
	}
	public String getReviserDate() {
		return reviserDate;
	}
	public void setReviserDate(String reviserDate) {
		this.reviserDate = reviserDate;
	}
	
	
	
}
