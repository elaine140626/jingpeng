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
//兑换前检测报告信息
var testreportListList;
//兑换后检测报告信息
var testreportListListLater;

$(function(){
	//获取传到当前页面的出库单id
	id = getUrlParam("id");
	//获取传到当前页面的出库单状态
	outType = getUrlParam("outType");
	//获取传到当前页面的是否关联其它公司
	isRelation = getUrlParam("isRelation");
	if(id != null && id != ""){
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
		
		//兑换前检测报告信息
		$.ajax({
			type : "post",
			url : "../../WeighingQueryOut/getTestreportInfoById.action",
			data : {"id" : id,"exchange" : 0},
			dataType : "json",
			async:false, 
			success : function(data) {
				testreportListList = data.testreportListList;
				}
			});
		
		//兑换后检测报告信息
		$.ajax({
			type : "post",
			url : "../../WeighingQueryOut/getTestreportInfoById.action",
			data : {"id" : id,"exchange" : 1},
			dataType : "json",
			async:false, 
			success : function(data) {
				testreportListListLater = data.testreportListList;
				}
			});
		
		//兑换后检测报告信息
		if(testreportListListLater.length != null && testreportListListLater.length > 0){
			$("#iframe6").show();
		}else{
			$("#iframe6").hide();
		}
		
		//兑换前检测报告信息
		if(testreportListList.length != null && testreportListList.length > 0){
			$("#iframe7").show();
		}else{
			$("#iframe7").hide();
		}
		
		if(outType == "0" || outType == "1" || outType == "3" || outType == "4"){
	
			//正常出库单
			if(outType == "0"){
				$("#iframe").show();
				$("#iframe2").hide();
				$("#iframe3").hide();
				$("#iframe4").hide();
				$("#iframe5").hide();
			}

			//正常出库单和关联其它公司
			if(outType == "0" && isRelation == 0){
				$("#iframe").show();
				$("#iframe2").show();
				$("#iframe3").hide();
				$("#iframe4").hide();
				$("#iframe5").hide();
			}
			
			//整车调拨
			if(outType == "1" && dataLength == 1){
				$("#iframe").show();
				$("#iframe2").hide();
				$("#iframe3").show();
				$("#iframe4").hide();
				$("#iframe5").hide();
			}
			
			//半车调拨
			if(outType == "1" && dataLength == 2){
				$("#iframe").show();
				$("#iframe2").hide();
				$("#iframe3").show();
				$("#iframe4").show();
				$("#iframe5").hide();
			}
			
			//整车调拨和关联其它公司
			if(outType == "1" && isRelation == 0 && dataLength == 1){
				$("#iframe").show();
				$("#iframe2").show();
				$("#iframe3").show();
				$("#iframe4").hide();
				$("#iframe5").hide();
			}

			//半车调拨和关联其它公司
			if(outType == "1" && isRelation == 0 && dataLength == 2){
				$("#iframe").show();
				$("#iframe2").show();
				$("#iframe3").show();
				$("#iframe4").show();
				$("#iframe5").hide();
			}
			
			//兑换出库单
			if(outType == "4"){
				$("#iframe").show();
				$("#iframe2").hide();
				$("#iframe3").hide();
				$("#iframe4").hide();
				$("#iframe5").show();
			}
			
			//兑换出库单和关联公司
			if(outType == "4" && isRelation == 0){
				$("#iframe").show();
				$("#iframe2").show();
				$("#iframe3").hide();
				$("#iframe4").hide();
				$("#iframe5").show();
			}
			
			//空发
			if(outType == "3"){
				$("#iframe").show();
				$("#iframe2").hide();
				$("#iframe3").hide();
				$("#iframe4").hide();
				$("#iframe5").hide();
			}
		}else{
			$("#iframe").hide();
			$("#iframe2").hide();
			$("#iframe3").hide();
			$("#iframe4").hide();
			$("#iframe5").hide();
		}
	}else{
		$("#iframe").hide();
		$("#iframe2").hide();
		$("#iframe3").hide();
		$("#iframe4").hide();
		$("#iframe5").hide();
		$("#iframe6").hide();
		$("#iframe7").hide();
	}
});

//关闭按钮
function closes(){
	//关闭当前页面，刷新父页面的父页面
	window.parent.location.reload();
}

//打印按钮
function print(){
	//正常出库单
	if(outType == "0" && isRelation != 0){
		document.getElementById('iframe').contentWindow.print();
		document.getElementById('iframeCopy').contentWindow.print();
	}

	//正常出库单和关联其它公司
	if(outType == "0" && isRelation == 0){
		document.getElementById('iframe2').contentWindow.print();
		document.getElementById('iframeCopy2').contentWindow.print();
	}
	
	//整车调拨
	if(outType == "1" && dataLength == 1 && isRelation != 0){
		document.getElementById('iframe').contentWindow.print();
		document.getElementById('iframeCopy').contentWindow.print();
		document.getElementById('iframe3').contentWindow.print();
		document.getElementById('iframeCopy3').contentWindow.print();
	}
	
	//半车调拨
	if(outType == "1" && dataLength == 2 && isRelation != 0){
		document.getElementById('iframe').contentWindow.print();
		document.getElementById('iframeCopy').contentWindow.print();
		document.getElementById('iframe3').contentWindow.print();
		document.getElementById('iframeCopy3').contentWindow.print();
		document.getElementById('iframe4').contentWindow.print();
		document.getElementById('iframeCopy4').contentWindow.print();
	}
	
	//整车调拨和关联其它公司
	if(outType == "1" && isRelation == 0 && dataLength == 1){
		document.getElementById('iframe2').contentWindow.print();
		document.getElementById('iframeCopy2').contentWindow.print();
		document.getElementById('iframe3').contentWindow.print();
		document.getElementById('iframeCopy3').contentWindow.print();
	}

	//半车调拨和关联其它公司
	if(outType == "1" && isRelation == 0 && dataLength == 2){
		document.getElementById('iframe2').contentWindow.print();
		document.getElementById('iframeCopy2').contentWindow.print();
		document.getElementById('iframe3').contentWindow.print();
		document.getElementById('iframeCopy3').contentWindow.print();
		document.getElementById('iframe4').contentWindow.print();
		document.getElementById('iframeCopy4').contentWindow.print();
	}
	
	//兑换出库单
	if(outType == "4" && isRelation != 0){
		//兑换后
		document.getElementById('iframe').contentWindow.print();
		document.getElementById('iframeCopy').contentWindow.print();
		//兑换前
		document.getElementById('iframe5').contentWindow.print();
		document.getElementById('iframeCopy5').contentWindow.print();
	}
	
	//兑换出库单和关联公司
	if(outType == "4" && isRelation == 0){
		//兑换后
		document.getElementById('iframe2').contentWindow.print();
		document.getElementById('iframeCopy2').contentWindow.print();
		//兑换前
		document.getElementById('iframe5').contentWindow.print();
		document.getElementById('iframeCopy5').contentWindow.print();
	}
	
	//空发
	if(outType == "3"){
		document.getElementById('iframe').contentWindow.print();
		document.getElementById('iframeCopy').contentWindow.print();
	}
	
	//兑换后检测报告信息
	if(testreportListListLater.length != null && testreportListListLater.length > 0){
		document.getElementById('iframe6').contentWindow.print();
	}
	
	//兑换前检测报告信息
	if(testreportListList.length != null && testreportListList.length > 0){
		document.getElementById('iframe7').contentWindow.print();
	}
}