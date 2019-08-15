package com.blindSample.model;

//试验室信息
public class TestRoomInfo {

	private Integer id;	//自增长ID
	private String testroomname;	//试验室名称
	private String uniqueidentifier;	//试验室唯一标识
	private Boolean isontrial;	//是否试用版
	private String ontrialdateend;	//试用结束日期
	private String longitude;	//经度
	private String latitude;	//纬度
	private String address;	//地址
	private String linkman;	//联系人
	private String phonenumber;	//电话
	private String prefix;	//试验室前缀
	private String operator;	//创建人
	private String createdate;	//创建日期
	private String modifier;	//修改人
	private String modifydate;	//修改日期
	private Integer ParentNode;
	
	public Integer getParentNode() {
		return ParentNode;
	}
	public void setParentNode(Integer parentNode) {
		ParentNode = parentNode;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getTestroomname() {
		return testroomname;
	}
	public void setTestroomname(String testroomname) {
		this.testroomname = testroomname;
	}
	public String getUniqueidentifier() {
		return uniqueidentifier;
	}
	public void setUniqueidentifier(String uniqueidentifier) {
		this.uniqueidentifier = uniqueidentifier;
	}
	public Boolean getIsontrial() {
		return isontrial;
	}
	public void setIsontrial(Boolean isontrial) {
		this.isontrial = isontrial;
	}
	public String getOntrialdateend() {
		return ontrialdateend;
	}
	public void setOntrialdateend(String ontrialdateend) {
		this.ontrialdateend = ontrialdateend;
	}
	public String getLongitude() {
		return longitude;
	}
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	public String getLatitude() {
		return latitude;
	}
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getLinkman() {
		return linkman;
	}
	public void setLinkman(String linkman) {
		this.linkman = linkman;
	}
	public String getPhonenumber() {
		return phonenumber;
	}
	public void setPhonenumber(String phonenumber) {
		this.phonenumber = phonenumber;
	}
	public String getPrefix() {
		return prefix;
	}
	public void setPrefix(String prefix) {
		this.prefix = prefix;
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
	
	
	
	
}