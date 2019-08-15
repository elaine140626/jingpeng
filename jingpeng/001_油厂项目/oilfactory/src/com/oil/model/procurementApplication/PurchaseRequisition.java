package com.oil.model.procurementApplication;
/**
 * 
 * @Title 采购申请单
 * @author Administrator
 * @date 2018年12月7日
 */
public class PurchaseRequisition {
	private int id;                      
	private String serialID;             //采购申请单据编号（流水号）
	private String applyDate;            //申请日期
	private String applicant;            //申请人
	private String applyDepartment;      //申请部门
	private int goodsName;               //申请采购物资id
	private String model;                //规格型号
	private Double applyNumber;          //申请数量
	private String purpose;              //用途
	private int isUrgentState;           //紧急状态
	private String estimatedTime;        //预计到位时间
	private String applicantRemarks;     //申请人备注
	private String entryClerkApplicant;  //录入员（申请人）
	private String purchaseLevel;        //物料采购级别（数据字典）
	private String consumablesShow;      //耗品后显示
	private String inquirer;             //询价人
	private String inquirer1;            //询价方1
	private Double univalent1;           //单价1
	private Double money1;               //金额1
	private String otherInfo1;           //其他信息1
	private String assess1;              //评价1
	private String inquirer2;            //询价方2
	private Double univalent2;           //单价2
	private Double money2;               //金额2
	private String otherInfo2;           //其他信息2
	private String assess2;              //评价2
	private String inquirer3;            //询价方3
	private Double univalent3;           //单价3
	private Double money3;               //金额3
	private String otherInfo3;           //其他信息3
	private String assess3;              //评价3
	private String buyerRemarks;         //采购员备注
	private String entryClerkPurchaser;  //录入员（采购人）
	private String entryClerkDate;       //录入时间
	private int isDel;                   //删除标记
	private String creater;              //创建人
	private String createrDate;          //创建日期
	private String reviser;              //修改人
	private String reviserDate;          //修改日期
	private String materielName;         //物料名称
	private String materielModel;        //物料型号
	private int materielNameId;          //物料名称id
	private int materielModelId;         //物料型号id
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
	public String getApplyDate() {
		return applyDate;
	}
	public void setApplyDate(String applyDate) {
		this.applyDate = applyDate;
	}
	public String getApplicant() {
		return applicant;
	}
	public void setApplicant(String applicant) {
		this.applicant = applicant;
	}
	public String getApplyDepartment() {
		return applyDepartment;
	}
	public void setApplyDepartment(String applyDepartment) {
		this.applyDepartment = applyDepartment;
	}
	public int getGoodsName() {
		return goodsName;
	}
	public void setGoodsName(int goodsName) {
		this.goodsName = goodsName;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public Double getApplyNumber() {
		return applyNumber;
	}
	public void setApplyNumber(Double applyNumber) {
		this.applyNumber = applyNumber;
	}
	public String getPurpose() {
		return purpose;
	}
	public void setPurpose(String purpose) {
		this.purpose = purpose;
	}
	public int getIsUrgentState() {
		return isUrgentState;
	}
	public void setIsUrgentState(int isUrgentState) {
		this.isUrgentState = isUrgentState;
	}
	public String getEstimatedTime() {
		return estimatedTime;
	}
	public void setEstimatedTime(String estimatedTime) {
		this.estimatedTime = estimatedTime;
	}
	public String getApplicantRemarks() {
		return applicantRemarks;
	}
	public void setApplicantRemarks(String applicantRemarks) {
		this.applicantRemarks = applicantRemarks;
	}
	public String getEntryClerkApplicant() {
		return entryClerkApplicant;
	}
	public void setEntryClerkApplicant(String entryClerkApplicant) {
		this.entryClerkApplicant = entryClerkApplicant;
	}
	public String getPurchaseLevel() {
		return purchaseLevel;
	}
	public void setPurchaseLevel(String purchaseLevel) {
		this.purchaseLevel = purchaseLevel;
	}
	public String getConsumablesShow() {
		return consumablesShow;
	}
	public void setConsumablesShow(String consumablesShow) {
		this.consumablesShow = consumablesShow;
	}
	public String getInquirer() {
		return inquirer;
	}
	public void setInquirer(String inquirer) {
		this.inquirer = inquirer;
	}
	public String getInquirer1() {
		return inquirer1;
	}
	public void setInquirer1(String inquirer1) {
		this.inquirer1 = inquirer1;
	}
	public Double getUnivalent1() {
		return univalent1;
	}
	public void setUnivalent1(Double univalent1) {
		this.univalent1 = univalent1;
	}
	public Double getMoney1() {
		return money1;
	}
	public void setMoney1(Double money1) {
		this.money1 = money1;
	}
	public String getOtherInfo1() {
		return otherInfo1;
	}
	public void setOtherInfo1(String otherInfo1) {
		this.otherInfo1 = otherInfo1;
	}
	public String getAssess1() {
		return assess1;
	}
	public void setAssess1(String assess1) {
		this.assess1 = assess1;
	}
	public String getInquirer2() {
		return inquirer2;
	}
	public void setInquirer2(String inquirer2) {
		this.inquirer2 = inquirer2;
	}
	public Double getUnivalent2() {
		return univalent2;
	}
	public void setUnivalent2(Double univalent2) {
		this.univalent2 = univalent2;
	}
	public Double getMoney2() {
		return money2;
	}
	public void setMoney2(Double money2) {
		this.money2 = money2;
	}
	public String getOtherInfo2() {
		return otherInfo2;
	}
	public void setOtherInfo2(String otherInfo2) {
		this.otherInfo2 = otherInfo2;
	}
	public String getAssess2() {
		return assess2;
	}
	public void setAssess2(String assess2) {
		this.assess2 = assess2;
	}
	public String getInquirer3() {
		return inquirer3;
	}
	public void setInquirer3(String inquirer3) {
		this.inquirer3 = inquirer3;
	}
	public Double getUnivalent3() {
		return univalent3;
	}
	public void setUnivalent3(Double univalent3) {
		this.univalent3 = univalent3;
	}
	public Double getMoney3() {
		return money3;
	}
	public void setMoney3(Double money3) {
		this.money3 = money3;
	}
	public String getOtherInfo3() {
		return otherInfo3;
	}
	public void setOtherInfo3(String otherInfo3) {
		this.otherInfo3 = otherInfo3;
	}
	public String getAssess3() {
		return assess3;
	}
	public void setAssess3(String assess3) {
		this.assess3 = assess3;
	}
	public String getBuyerRemarks() {
		return buyerRemarks;
	}
	public void setBuyerRemarks(String buyerRemarks) {
		this.buyerRemarks = buyerRemarks;
	}
	public String getEntryClerkPurchaser() {
		return entryClerkPurchaser;
	}
	public void setEntryClerkPurchaser(String entryClerkPurchaser) {
		this.entryClerkPurchaser = entryClerkPurchaser;
	}
	public String getEntryClerkDate() {
		return entryClerkDate;
	}
	public void setEntryClerkDate(String entryClerkDate) {
		this.entryClerkDate = entryClerkDate;
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
}
