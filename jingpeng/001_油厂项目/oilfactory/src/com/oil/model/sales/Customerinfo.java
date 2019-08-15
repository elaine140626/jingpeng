package com.oil.model.sales;

/**
 * 客户信息表
 */
public class Customerinfo {
	private int uuid;
    private int id;
    
    /**
     * 序号
     */
    private int rownum;
    /**
     * 客户编号
     */
    private String customerCode;
	/**
     * 客户名称
     */
    private String customername;
    /**
     * 所属省
     */
    private String province;
    /**
     * 所属市
     */
    private String city;
    /**
     * 意向客户级别（数据字典）
     */
    private String intentionallevel;
    /**
     * 采购级别（数据字典）
     */
    private String procurementlevel;
    /**
     * 诚信级别（数据字典）
     */
    private String creditlevel;
    /**
     * 地址
     */
    private String address;
    /**
     * 联系人
     */
    private String contacts;
    /**
     * 联系电话
     */
    private String telephone;
    /**
     * 其他联系方式
     */
    private String othernumbers;
    /**
     * 备注
     */
    private String remarks;
    /**
     * 是否来料加工客户
     */
    private int isincoming;
    /**
     * 是否也是供应商
     */
    private int issupplier;
    /**
     * 期初欠款金额
     */
    private double arrearsmoney;
    /**
     * 期初欠款数量
     */
    private double arrearsamount;
    /**
     * 创建人
     */
    private String creater;
    /**
     * 创建日期
     */
    private String createrdate;
    /**
     * 修改人
     */
    private String reviser;
    /**
     * 修改日期
     */
    private String reviserdate;

	public int getUuid() {
		return uuid;
	}
	public void setUuid(int uuid) {
		this.uuid = uuid;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getRownum() {
		return rownum;
	}
	public void setRownum(int rownum) {
		this.rownum = rownum;
	}
	public String getCustomerCode() {
		return customerCode;
	}
	public void setCustomerCode(String customerCode) {
		this.customerCode = customerCode;
	}
	public String getCustomername() {
		return customername;
	}
	public void setCustomername(String customername) {
		this.customername = customername;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getIntentionallevel() {
		return intentionallevel;
	}
	public void setIntentionallevel(String intentionallevel) {
		this.intentionallevel = intentionallevel;
	}
	public String getProcurementlevel() {
		return procurementlevel;
	}
	public void setProcurementlevel(String procurementlevel) {
		this.procurementlevel = procurementlevel;
	}
	public String getCreditlevel() {
		return creditlevel;
	}
	public void setCreditlevel(String creditlevel) {
		this.creditlevel = creditlevel;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
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
	public String getOthernumbers() {
		return othernumbers;
	}
	public void setOthernumbers(String othernumbers) {
		this.othernumbers = othernumbers;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public int getIsincoming() {
		return isincoming;
	}
	public void setIsincoming(int isincoming) {
		this.isincoming = isincoming;
	}
	public int getIssupplier() {
		return issupplier;
	}
	public void setIssupplier(int issupplier) {
		this.issupplier = issupplier;
	}
	public double getArrearsmoney() {
		return arrearsmoney;
	}
	public void setArrearsmoney(double arrearsmoney) {
		this.arrearsmoney = arrearsmoney;
	}
	public double getArrearsamount() {
		return arrearsamount;
	}
	public void setArrearsamount(double arrearsamount) {
		this.arrearsamount = arrearsamount;
	}
	public String getCreater() {
		return creater;
	}
	public void setCreater(String creater) {
		this.creater = creater;
	}
	public String getCreaterdate() {
		return createrdate;
	}
	public void setCreaterdate(String createrdate) {
		this.createrdate = createrdate;
	}
	public String getReviser() {
		return reviser;
	}
	public void setReviser(String reviser) {
		this.reviser = reviser;
	}
	public String getReviserdate() {
		return reviserdate;
	}
	public void setReviserdate(String reviserdate) {
		this.reviserdate = reviserdate;
	}
}