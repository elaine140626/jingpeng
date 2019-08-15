package com.testRoom.model;

//试验基本信息表
public class TestInfo {

	private Long id;	//自增长ID
	private String uniqueidentifier;	//试验室唯一标识
	private String serialnumber;	//试验流水号
	private Integer testnameid;	//试验名称ID
	private String testcode;	//试验编号
	private String reportcode;	//报告编号
	private Long sampleid;	//样品ID
	private Long orderid;	//委托Id
	private String ordercode;	//委托编号
	private String engineeringname;	//工程名称
	private String testdate;	//试验日期
	private String testrules;	//试验规程
	private String testcondition;	//试验条件
	private String testdate_statr;	//试验开始日期
	private String testdate_end;	//试验结束日期
	private String instrumenttation;	//主要仪器设备
	private String judgingbasis;	//判定依据
	private String technicaindicators;	//技术指标
	private String purpose;	//工程部位/用途
	private String sampleplace;	//取样地点
	private String testconclusion;	//试验结论
	private String remarks;	//备注
	private String teststate;	//试验状态
	private String collectionstate;	//采集方式
	private String testoperator;	//试验员
	private String review;	//复核人
	private String reviewcomment;	//复核意见
	private String reviewdate;	//复核时间
	private String approve;	//审批人
	private String approvecomment;	//审批意见
	private String approvedate;	//审批时间
	private String print;	//打印人
	private String printdate;	//打印时间
	private String printreturn;	//打印退回人
	private String printreturndate;	//打印退回时间
	private String preturncomment;	//打印意见
	private String recordprintdate;	//记录打印时间
	private String reportprintdate;	//报告打印时间
	private String delstate;	//删除标识
	private String delete;	//删除人
	private String delreason;	//删除原因
	private String deldate;	//删除时间
	private boolean istestfast;	//是否快速试验
	private boolean istestblind;	//是否盲样试验
	private boolean isoverproof;	//试验是否超差
	private String qrcode;	//二维码
	private boolean isqualifiedtest;	//试验是否合格
	private Integer samplecount;	//试件个数
	private String operator;	//创建人
	private String createdate;	//创建日期
	private String modifier;	//修改人
	private String modifydate;	//修改日期
	private boolean isvaliddata;	//数据是否有效\
	private boolean isQualifiedTest;	//实验是否合格
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getUniqueidentifier() {
		return uniqueidentifier;
	}
	public void setUniqueidentifier(String uniqueidentifier) {
		this.uniqueidentifier = uniqueidentifier;
	}
	public String getSerialnumber() {
		return serialnumber;
	}
	public void setSerialnumber(String serialnumber) {
		this.serialnumber = serialnumber;
	}
	public Integer getTestnameid() {
		return testnameid;
	}
	public void setTestnameid(Integer testnameid) {
		this.testnameid = testnameid;
	}
	public String getTestcode() {
		return testcode;
	}
	public void setTestcode(String testcode) {
		this.testcode = testcode;
	}
	public String getReportcode() {
		return reportcode;
	}
	public void setReportcode(String reportcode) {
		this.reportcode = reportcode;
	}
	public Long getSampleid() {
		return sampleid;
	}
	public void setSampleid(Long sampleid) {
		this.sampleid = sampleid;
	}
	public Long getOrderid() {
		return orderid;
	}
	public void setOrderid(Long orderid) {
		this.orderid = orderid;
	}
	public String getOrdercode() {
		return ordercode;
	}
	public void setOrdercode(String ordercode) {
		this.ordercode = ordercode;
	}
	public String getEngineeringname() {
		return engineeringname;
	}
	public void setEngineeringname(String engineeringname) {
		this.engineeringname = engineeringname;
	}
	public String getTestdate() {
		return testdate;
	}
	public void setTestdate(String testdate) {
		this.testdate = testdate;
	}
	public String getTestrules() {
		return testrules;
	}
	public void setTestrules(String testrules) {
		this.testrules = testrules;
	}
	public String getTestcondition() {
		return testcondition;
	}
	public void setTestcondition(String testcondition) {
		this.testcondition = testcondition;
	}
	public String getTestdate_statr() {
		return testdate_statr;
	}
	public void setTestdate_statr(String testdate_statr) {
		this.testdate_statr = testdate_statr;
	}
	public String getTestdate_end() {
		return testdate_end;
	}
	public void setTestdate_end(String testdate_end) {
		this.testdate_end = testdate_end;
	}
	public String getInstrumenttation() {
		return instrumenttation;
	}
	public void setInstrumenttation(String instrumenttation) {
		this.instrumenttation = instrumenttation;
	}
	public String getJudgingbasis() {
		return judgingbasis;
	}
	public void setJudgingbasis(String judgingbasis) {
		this.judgingbasis = judgingbasis;
	}
	public String getTechnicaindicators() {
		return technicaindicators;
	}
	public void setTechnicaindicators(String technicaindicators) {
		this.technicaindicators = technicaindicators;
	}
	public String getPurpose() {
		return purpose;
	}
	public void setPurpose(String purpose) {
		this.purpose = purpose;
	}
	public String getSampleplace() {
		return sampleplace;
	}
	public void setSampleplace(String sampleplace) {
		this.sampleplace = sampleplace;
	}
	public String getTestconclusion() {
		return testconclusion;
	}
	public void setTestconclusion(String testconclusion) {
		this.testconclusion = testconclusion;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public String getTeststate() {
		return teststate;
	}
	public void setTeststate(String teststate) {
		this.teststate = teststate;
	}
	public String getCollectionstate() {
		return collectionstate;
	}
	public void setCollectionstate(String collectionstate) {
		this.collectionstate = collectionstate;
	}
	public String getTestoperator() {
		return testoperator;
	}
	public void setTestoperator(String testoperator) {
		this.testoperator = testoperator;
	}
	public String getReview() {
		return review;
	}
	public void setReview(String review) {
		this.review = review;
	}
	public String getReviewcomment() {
		return reviewcomment;
	}
	public void setReviewcomment(String reviewcomment) {
		this.reviewcomment = reviewcomment;
	}
	public String getReviewdate() {
		return reviewdate;
	}
	public void setReviewdate(String reviewdate) {
		this.reviewdate = reviewdate;
	}
	public String getApprove() {
		return approve;
	}
	public void setApprove(String approve) {
		this.approve = approve;
	}
	public String getApprovecomment() {
		return approvecomment;
	}
	public void setApprovecomment(String approvecomment) {
		this.approvecomment = approvecomment;
	}
	public String getApprovedate() {
		return approvedate;
	}
	public void setApprovedate(String approvedate) {
		this.approvedate = approvedate;
	}
	public String getPrint() {
		return print;
	}
	public void setPrint(String print) {
		this.print = print;
	}
	public String getPrintdate() {
		return printdate;
	}
	public void setPrintdate(String printdate) {
		this.printdate = printdate;
	}
	public String getPrintreturn() {
		return printreturn;
	}
	public void setPrintreturn(String printreturn) {
		this.printreturn = printreturn;
	}
	public String getPrintreturndate() {
		return printreturndate;
	}
	public void setPrintreturndate(String printreturndate) {
		this.printreturndate = printreturndate;
	}
	public String getPreturncomment() {
		return preturncomment;
	}
	public void setPreturncomment(String preturncomment) {
		this.preturncomment = preturncomment;
	}
	public String getRecordprintdate() {
		return recordprintdate;
	}
	public void setRecordprintdate(String recordprintdate) {
		this.recordprintdate = recordprintdate;
	}
	public String getReportprintdate() {
		return reportprintdate;
	}
	public void setReportprintdate(String reportprintdate) {
		this.reportprintdate = reportprintdate;
	}
	public String getDelstate() {
		return delstate;
	}
	public void setDelstate(String delstate) {
		this.delstate = delstate;
	}
	public String getDelete() {
		return delete;
	}
	public void setDelete(String delete) {
		this.delete = delete;
	}
	public String getDelreason() {
		return delreason;
	}
	public void setDelreason(String delreason) {
		this.delreason = delreason;
	}
	public String getDeldate() {
		return deldate;
	}
	public void setDeldate(String deldate) {
		this.deldate = deldate;
	}
	public boolean isIstestfast() {
		return istestfast;
	}
	public void setIstestfast(boolean istestfast) {
		this.istestfast = istestfast;
	}
	public boolean isIstestblind() {
		return istestblind;
	}
	public void setIstestblind(boolean istestblind) {
		this.istestblind = istestblind;
	}
	public boolean isIsoverproof() {
		return isoverproof;
	}
	public void setIsoverproof(boolean isoverproof) {
		this.isoverproof = isoverproof;
	}
	public String getQrcode() {
		return qrcode;
	}
	public void setQrcode(String qrcode) {
		this.qrcode = qrcode;
	}
	public boolean isIsqualifiedtest() {
		return isqualifiedtest;
	}
	public void setIsqualifiedtest(boolean isqualifiedtest) {
		this.isqualifiedtest = isqualifiedtest;
	}
	public Integer getSamplecount() {
		return samplecount;
	}
	public void setSamplecount(Integer samplecount) {
		this.samplecount = samplecount;
	}
	public String getOperator() {
		return operator;
	}
	public void setOperator(String operator) {
		this.operator = operator;
	}
	public String getCreatedate() {
		return createdate;
	}
	public void setCreatedate(String createdate) {
		this.createdate = createdate;
	}
	public String getModifier() {
		return modifier;
	}
	public void setModifier(String modifier) {
		this.modifier = modifier;
	}
	public String getModifydate() {
		return modifydate;
	}
	public void setModifydate(String modifydate) {
		this.modifydate = modifydate;
	}
	public boolean isIsvaliddata() {
		return isvaliddata;
	}
	public void setIsvaliddata(boolean isvaliddata) {
		this.isvaliddata = isvaliddata;
	}
	public boolean isQualifiedTest() {
		return isQualifiedTest;
	}
	public void setQualifiedTest(boolean isQualifiedTest) {
		this.isQualifiedTest = isQualifiedTest;
	}
	
	
}
