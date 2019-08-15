package com.oil.model.dispath;

public class SalesOrderListEntity {
	private Integer id; // 销售订单id
	private String orderNumbers; // 销售订单编号
	private String customerCode; // 客户编号
	private String customerName; // 客户名称
	private String customerAlias; // 客户别称
	private String contacts; // 收货人
	private String telephone; // 联系电话
	private String address; // 送货地址
	private Double saleNumber; // 销售数量
	private String saleCompanyName; // 销售公司名称
	private Integer orderDetailedId; // 销售订单明细id
	private String orderDetailedNumber; // 销售订单明细编号
	private Integer materielId; // 物料id
	private String materielName; // 物料名称
	private String materielModel; // 物料型号
	private String unit; // 单位
	private Integer exchangeMaterielId; // 物料id(兑换后)
	private String exchangeMaterielName; // 物料名称(兑换后)
	private String exchangeMaterielModel; // 物料型号(兑换后)
	private String exchangeUnit; // 单位
	private Double proportion; // 兑换比例
	private String listModel; // 磅单打印物料名称
	private String transports; // 止运地
	private Double distance; // 运距（千米）

	public String getSalRemarks() {
		return salRemarks;
	}

	public void setSalRemarks(String salRemarks) {
		this.salRemarks = salRemarks;
	}

	private String transportBalances; // 结算情况
	private String transportBalancesName; // 结算情况
	private Integer exmOrderDetailedId; // 关联出库单的销售订单明细id
	private Integer planId; // 计划调度id
	private String salRemarks; // 销售备注

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getOrderNumbers() {
		return orderNumbers;
	}

	public void setOrderNumbers(String orderNumbers) {
		this.orderNumbers = orderNumbers;
	}

	public String getCustomerCode() {
		return customerCode;
	}

	public void setCustomerCode(String customerCode) {
		this.customerCode = customerCode;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getCustomerAlias() {
		return customerAlias;
	}

	public void setCustomerAlias(String customerAlias) {
		this.customerAlias = customerAlias;
	}

	public String getContacts() {
		return contacts;
	}

	public void setContacts(String contacts) {
		this.contacts = contacts;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Double getSaleNumber() {
		return saleNumber;
	}

	public void setSaleNumber(Double saleNumber) {
		this.saleNumber = saleNumber;
	}

	public String getSaleCompanyName() {
		return saleCompanyName;
	}

	public void setSaleCompanyName(String saleCompanyName) {
		this.saleCompanyName = saleCompanyName;
	}

	public Integer getOrderDetailedId() {
		return orderDetailedId;
	}

	public void setOrderDetailedId(Integer orderDetailedId) {
		this.orderDetailedId = orderDetailedId;
	}

	public String getOrderDetailedNumber() {
		return orderDetailedNumber;
	}

	public void setOrderDetailedNumber(String orderDetailedNumber) {
		this.orderDetailedNumber = orderDetailedNumber;
	}

	public Integer getMaterielId() {
		return materielId;
	}

	public void setMaterielId(Integer materielId) {
		this.materielId = materielId;
	}

	public String getMaterielName() {
		return materielName;
	}

	public void setMaterielName(String materielName) {
		this.materielName = materielName;
	}

	public String getMaterielModel() {
		return materielModel;
	}

	public void setMaterielModel(String materielModel) {
		this.materielModel = materielModel;
	}

	public Integer getExchangeMaterielId() {
		return exchangeMaterielId;
	}

	public void setExchangeMaterielId(Integer exchangeMaterielId) {
		this.exchangeMaterielId = exchangeMaterielId;
	}

	public String getExchangeMaterielName() {
		return exchangeMaterielName;
	}

	public void setExchangeMaterielName(String exchangeMaterielName) {
		this.exchangeMaterielName = exchangeMaterielName;
	}

	public String getExchangeMaterielModel() {
		return exchangeMaterielModel;
	}

	public void setExchangeMaterielModel(String exchangeMaterielModel) {
		this.exchangeMaterielModel = exchangeMaterielModel;
	}

	public Double getProportion() {
		return proportion;
	}

	public void setProportion(Double proportion) {
		this.proportion = proportion;
	}

	public String getListModel() {
		return listModel;
	}

	public void setListModel(String listModel) {
		this.listModel = listModel;
	}

	public String getTransports() {
		return transports;
	}

	public void setTransports(String transports) {
		this.transports = transports;
	}

	public Double getDistance() {
		return distance;
	}

	public void setDistance(Double distance) {
		this.distance = distance;
	}

	public String getTransportBalances() {
		return transportBalances;
	}

	public void setTransportBalances(String transportBalances) {
		this.transportBalances = transportBalances;
	}

	public String getTransportBalancesName() {
		return transportBalancesName;
	}

	public void setTransportBalancesName(String transportBalancesName) {
		this.transportBalancesName = transportBalancesName;
	}

	public Integer getExmOrderDetailedId() {
		return exmOrderDetailedId;
	}

	public void setExmOrderDetailedId(Integer exmOrderDetailedId) {
		this.exmOrderDetailedId = exmOrderDetailedId;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public String getExchangeUnit() {
		return exchangeUnit;
	}

	public void setExchangeUnit(String exchangeUnit) {
		this.exchangeUnit = exchangeUnit;
	}

	public Integer getPlanId() {
		return planId;
	}

	public void setPlanId(Integer planId) {
		this.planId = planId;
	}

}
