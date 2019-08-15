package com.blindSample.model;

//样品信息表
public class SampleBasicInfo {

	private Long id;	//自增长ID
	private String uniqueidentifier;	//试验室唯一标识
	private String samplecode;	//样品编号
	private String entrustcode;	//委托编号
	private String sampletypecode;	//样品试验类别编码
	private String samplename;	//样品名称
	private Float quantity;	//代表数量
	private String quantityunit;	//代表数量单位
	private String sampledescribe;	//样品描述
	private String samplesite;	//样品产地
	private String sender;	//送样人
	private String senderphone;	//送样人电话
	private String senddate;	//送样日期
	private String receiver;	//收样人
	private String receiverphone;	//收样人电话
	private String receivedate;	//收样日期
	private String remarks;	//备注
	private String operator;	//创建人
	private String createdate;	//创建日期
	private String modifier;	//修改人
	private String modifydate;	//修改日期
	private Integer upload;	//上传标识
	private boolean isvaliddata;	//有效标识
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
	public String getSamplecode() {
		return samplecode;
	}
	public void setSamplecode(String samplecode) {
		this.samplecode = samplecode;
	}
	public String getEntrustcode() {
		return entrustcode;
	}
	public void setEntrustcode(String entrustcode) {
		this.entrustcode = entrustcode;
	}
	public String getSampletypecode() {
		return sampletypecode;
	}
	public void setSampletypecode(String sampletypecode) {
		this.sampletypecode = sampletypecode;
	}
	public String getSamplename() {
		return samplename;
	}
	public void setSamplename(String samplename) {
		this.samplename = samplename;
	}
	public Float getQuantity() {
		return quantity;
	}
	public void setQuantity(Float quantity) {
		this.quantity = quantity;
	}
	public String getQuantityunit() {
		return quantityunit;
	}
	public void setQuantityunit(String quantityunit) {
		this.quantityunit = quantityunit;
	}
	public String getSampledescribe() {
		return sampledescribe;
	}
	public void setSampledescribe(String sampledescribe) {
		this.sampledescribe = sampledescribe;
	}
	public String getSamplesite() {
		return samplesite;
	}
	public void setSamplesite(String samplesite) {
		this.samplesite = samplesite;
	}
	public String getSender() {
		return sender;
	}
	public void setSender(String sender) {
		this.sender = sender;
	}
	public String getSenderphone() {
		return senderphone;
	}
	public void setSenderphone(String senderphone) {
		this.senderphone = senderphone;
	}
	public String getSenddate() {
		return senddate;
	}
	public void setSenddate(String senddate) {
		this.senddate = senddate;
	}
	public String getReceiver() {
		return receiver;
	}
	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}
	public String getReceiverphone() {
		return receiverphone;
	}
	public void setReceiverphone(String receiverphone) {
		this.receiverphone = receiverphone;
	}
	public String getReceivedate() {
		return receivedate;
	}
	public void setReceivedate(String receivedate) {
		this.receivedate = receivedate;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
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
	public Integer getUpload() {
		return upload;
	}
	public void setUpload(Integer upload) {
		this.upload = upload;
	}
	public boolean isIsvaliddata() {
		return isvaliddata;
	}
	public void setIsvaliddata(boolean isvaliddata) {
		this.isvaliddata = isvaliddata;
	}
	
	
}
