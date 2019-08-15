package com.oil.model.dispath;

/**
 * @author Administrator
 *
 */
public class InstroeEntity {
	private int id;                      //  id                                            
	private String serialID;             //  流水号                                           
	private int contractId;              //  销售合同id                                        
	private String client;               //  客户名称                                          
	private String customerAlias;        //  客户别称                                          
	private int productID;               //  物料id                                          
	private int materielNameId;          //  物料名称id                                        
	private int materielModelId;         //  规格型号id                                        
	private String materielName;         //  物料名称                                          
	private String materielModel;        //  规格型号                                          
	private String plateNumber;          //  车牌号码                                          
	private String deliveryMan;          //  送货人                                           
	private String deliveryManPhone;     //  送货电话                                          
	private String carOwner;             //  车主                                            
	private String carOwnerTelephone;    //  车主电话                                          
	private String remarks;              //  备注                                            
	private int purchaseContractId;      //  采购合同id 
	private int supplierId;              //  供应商id
	private Double amount;               //  数量   
	private Double suttle;               //  净重
	private Double tareWeight;               //  净重
	private Double grossWeight;               //  净重
	private int priority;                //  优先级 0正常/ 1 优先                                 
	private int enterTypeMark;           //  入库单类型标识 0：入库单 1：未入厂出库单对应的入库单 2:来料加工 3：退货      
	private int validFlag;               //  有效标识/0无效/1有效                                  
	private String CartWeighType;        //  是否大车称重                                        
	private String enterDate;            //  入厂时间                                          
	private String contractNumber;       //  销售合同编号                                        
	private String cancellationCause;       //  销售合同编号                                        
	private String billNumber;           //  采购合同编号 
	private String creater;              //  创建人
	private String reviser;              //  修改人
	private int outWarehouseId;          // 出库单id
	private int salesOrderId;            // 销售订单id
	private String orderNumber;          // 销售订单编号
	private String orderNumbers;
	private String prioritys;
	private double SupplierSuttle;		 // 实际净重
	private int StartAddressId;		 // 起运地Id
	private double SupplierDeviation;		 // 供应物料允许偏差
	private int orderDetailedId;		 // 供应物料允许偏差
	private String materielName2;		 // 兑换前物料名称
	private String materielModel2;		 // 兑换前规格型号
	private String isExchange;		 // 是否兑换
	private double proportion;		 // 兑换比例
	private Integer isExamine;
	private String startAddress;
	
	public int getSalesOrderId() {
		return salesOrderId;
	}
	public void setSalesOrderId(int salesOrderId) {
		this.salesOrderId = salesOrderId;
	}
	public String getOrderNumber() {
		return orderNumber;
	}
	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}
	public Double getSuttle() {
		return suttle;
	}
	public void setSuttle(Double suttle) {
		this.suttle = suttle;
	}
	public int getOutWarehouseId() {
		return outWarehouseId;
	}
	public void setOutWarehouseId(int outWarehouseId) {
		this.outWarehouseId = outWarehouseId;
	}
	public int getSupplierId() {
		return supplierId;
	}
	public void setSupplierId(int supplierId) {
		this.supplierId = supplierId;
	}
	public String getCreater() {
		return creater;
	}
	public void setCreater(String creater) {
		this.creater = creater;
	}
	public String getReviser() {
		return reviser;
	}
	public void setReviser(String reviser) {
		this.reviser = reviser;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getSerialID() {
		return serialID;
	}
	public void setSerialID(String serialID) {
		this.serialID = serialID;
	}
	public int getContractId() {
		return contractId;
	}
	public void setContractId(int contractId) {
		this.contractId = contractId;
	}
	public String getClient() {
		return client;
	}
	public void setClient(String client) {
		this.client = client;
	}
	public String getCustomerAlias() {
		return customerAlias;
	}
	public void setCustomerAlias(String customerAlias) {
		this.customerAlias = customerAlias;
	}
	public int getProductID() {
		return productID;
	}
	public void setProductID(int productID) {
		this.productID = productID;
	}
	public int getMaterielNameId() {
		return materielNameId;
	}
	public void setMaterielNameId(int materielNameId) {
		this.materielNameId = materielNameId;
	}
	public int getMaterielModelId() {
		return materielModelId;
	}
	public void setMaterielModelId(int materielModelId) {
		this.materielModelId = materielModelId;
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
	public String getPlateNumber() {
		return plateNumber;
	}
	public void setPlateNumber(String plateNumber) {
		this.plateNumber = plateNumber;
	}
	public String getDeliveryMan() {
		return deliveryMan;
	}
	public void setDeliveryMan(String deliveryMan) {
		this.deliveryMan = deliveryMan;
	}
	public String getDeliveryManPhone() {
		return deliveryManPhone;
	}
	public void setDeliveryManPhone(String deliveryManPhone) {
		this.deliveryManPhone = deliveryManPhone;
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
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public int getPurchaseContractId() {
		return purchaseContractId;
	}
	public void setPurchaseContractId(int purchaseContractId) {
		this.purchaseContractId = purchaseContractId;
	}
	public Double getAmount() {
		return amount;
	}
	public void setAmount(Double amount) {
		this.amount = amount;
	}
	public int getPriority() {
		return priority;
	}
	public void setPriority(int priority) {
		this.priority = priority;
	}
	public int getEnterTypeMark() {
		return enterTypeMark;
	}
	public void setEnterTypeMark(int enterTypeMark) {
		this.enterTypeMark = enterTypeMark;
	}
	public int getValidFlag() {
		return validFlag;
	}
	public void setValidFlag(int validFlag) {
		this.validFlag = validFlag;
	}
	
	public String getCartWeighType() {
		return CartWeighType;
	}
	public void setCartWeighType(String cartWeighType) {
		CartWeighType = cartWeighType;
	}
	public String getEnterDate() {
		return enterDate;
	}
	public void setEnterDate(String enterDate) {
		this.enterDate = enterDate;
	}
	public String getContractNumber() {
		return contractNumber;
	}
	public void setContractNumber(String contractNumber) {
		this.contractNumber = contractNumber;
	}
	public String getBillNumber() {
		return billNumber;
	}
	public void setBillNumber(String billNumber) {
		this.billNumber = billNumber;
	}
	public String getPrioritys() {
		return prioritys;
	}
	public void setPrioritys(String prioritys) {
		this.prioritys = prioritys;
	}
	public String getCancellationCause() {
		return cancellationCause;
	}
	public void setCancellationCause(String cancellationCause) {
		this.cancellationCause = cancellationCause;
	}
	public String getOrderNumbers() {
		return orderNumbers;
	}
	public void setOrderNumbers(String orderNumbers) {
		this.orderNumbers = orderNumbers;
	}
	public double getSupplierSuttle() {
		return SupplierSuttle;
	}
	public void setSupplierSuttle(double supplierSuttle) {
		SupplierSuttle = supplierSuttle;
	}
	public int getStartAddressId() {
		return StartAddressId;
	}
	public void setStartAddressId(int startAddressId) {
		StartAddressId = startAddressId;
	}
	public double getSupplierDeviation() {
		return SupplierDeviation;
	}
	public void setSupplierDeviation(double supplierDeviation) {
		SupplierDeviation = supplierDeviation;
	}
	public Double getTareWeight() {
		return tareWeight;
	}
	public void setTareWeight(Double tareWeight) {
		this.tareWeight = tareWeight;
	}
	public Double getGrossWeight() {
		return grossWeight;
	}
	public void setGrossWeight(Double grossWeight) {
		this.grossWeight = grossWeight;
	}
	public int getOrderDetailedId() {
		return orderDetailedId;
	}
	public void setOrderDetailedId(int orderDetailedId) {
		this.orderDetailedId = orderDetailedId;
	}
	public String getMaterielName2() {
		return materielName2;
	}
	public void setMaterielName2(String materielName2) {
		this.materielName2 = materielName2;
	}
	public String getMaterielModel2() {
		return materielModel2;
	}
	public void setMaterielModel2(String materielModel2) {
		this.materielModel2 = materielModel2;
	}
	public String getIsExchange() {
		return isExchange;
	}
	public void setIsExchange(String isExchange) {
		this.isExchange = isExchange;
	}
	public double getProportion() {
		return proportion;
	}
	public void setProportion(double proportion) {
		this.proportion = proportion;
	}
	public Integer getIsExamine() {
		return isExamine;
	}
	public void setIsExamine(Integer isExamine) {
		this.isExamine = isExamine;
	}
	public String getStartAddress() {
		return startAddress;
	}
	public void setStartAddress(String startAddress) {
		this.startAddress = startAddress;
	}
	
}
