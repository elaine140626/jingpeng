//出库单id
var id = "";
//用户信息
var userId = "";
//出库单状态
var outType = "";
//是否关联其它公司
var isRelation = "";
//调拨的数据条数
var dataLength;

$(function(){
	//获取传到当前页面的出库单id
	id = getUrlParam("id");
	//获取传到当前页面的用户信息
	userId = getUrlParam("userId");
	//获取传到当前页面的出库单状态
	outType = getUrlParam("outType");
	//获取传到当前页面的是否关联其它公司
	isRelation = getUrlParam("isRelation");
	//调拨的数据条数
	$.ajax({
		type : "post",
		url : "../../WeighingQueryOut/getNoweighEntityPrintInfo.action",
		data : {"id" : id,"outType" : outType},
		dataType : "json",
		async:false, 
		success : function(data) {
			dataLength = data.length;
			}
		});
	
	//正常出库单
	if(outType == "0"){
		$("#iframe").show();
		$("#iframe2").hide();
		$("#iframe3").hide();
		$("#iframe4").hide();
	}

	//正常出库单和关联其它公司
	if(outType == "0" && isRelation == 0){
		$("#iframe").show();
		$("#iframe2").show();
		$("#iframe3").hide();
		$("#iframe4").hide();
	}
	
	//整车调拨
	if(outType == "1" && dataLength == 1){
		$("#iframe").show();
		$("#iframe2").hide();
		$("#iframe3").show();
		$("#iframe4").hide();
	}
	
	//半车调拨
	if(outType == "1" && dataLength == 2){
		$("#iframe").show();
		$("#iframe2").hide();
		$("#iframe3").show();
		$("#iframe4").show();
	}
	
	//调拨和关联其它公司
	if(outType == "1" && isRelation == 0){
		$("#iframe").show();
		$("#iframe2").show();
		$("#iframe3").show();
		$("#iframe4").show();
	}
});