// 供料单位数据
var dataList = "";
//用户的id
var userId = "";
//用户信息
var userInfo = "";
//出库流水号
var weighingCount = "";
$(function(){
	//用户信息
	userId = getUrlParam("userId");
	if(userId != null && userId != ""){
		userInfo = getUserInfo(userId);
	}
	//传所需的前缀英文简写
	$.ajax({
		type : "POST",
		url : "../../WeighingQuery/getWeighingInfoCount.action",
		async:false,
		data : {"type":"CK"},
		dataType : "json",
		success : function(data) {
			weighingCount = data.weighingCount;
		}
	});
	//下拉列表信息
	getListInfo();
	//获取收料单位信息(ygt)
	getOutReceiveUnitInfo();
});
//下拉列表查询
function getListInfo(){
	$.ajax({
		type : "POST",
		url : "../../WeighingQuery/getOutFeedCompanyInfo.action",
		async:false,
		data : {},
		dataType : "json",
		success : function(data) {
			if(data != null && data.length > 0){
				dataList = data;
				//供料单位下拉
				$("#feedCompanyId").empty();
				$("#feedCompanyId").append("<option value=''>-- 请选择 --</option>");
				// 供料单位名称
				var FeedCompanyName = "";
				for (var i = 0; i < data.length; i++) {
					if(FeedCompanyName != data[i].feedCompanyName){
						$("#feedCompanyId").append("<option value='"+data[i].id+"'>"+data[i].feedCompanyName+"</option>");
					}
					FeedCompanyName = data[i].feedCompanyName;
				}
				//物料名称
				$("#materielName").empty();
				$("#materielName").append("<option value=''>-- 请选择 --</option>");
				//物料型号
				$("#materielModel").empty();
				$("#materielModel").append("<option value=''>-- 请选择 --</option>");
			}
		}
	});
}

// 供料单位change事件
function changeFeedCompany(value){
		var index=document.getElementById("feedCompanyId").selectedIndex;
		var startAddress = document.getElementById("feedCompanyId").options[index].text;
		$("#startAddress").val(startAddress);
	if(dataList != null && dataList.length > 0){
		// 材料名称
		var materielName = "";
		//物料名称
		$("#materielName").empty();
		$("#materielName").append("<option value=''>-- 请选择 --</option>");
		//物料型号
		$("#materielModel").empty();
		$("#materielModel").append("<option value=''>-- 请选择 --</option>");
		for (var i = 0; i < dataList.length; i++) {
			if(value == dataList[i].id){
				if(materielName != dataList[i].materielName){
					$("#materielName").append("<option value='"+dataList[i].materielName+"'>"+dataList[i].materielName+"</option>");
				}
				materielName = dataList[i].materielName;
			}
			
		}
	}
}

//材料名称change事件
function changeMaterielName(value){
	if(dataList != null && dataList.length > 0){
		// 材料名称
		var materielModel = "";
		//物料型号
		$("#materielModel").empty();
		$("#materielModel").append("<option value=''>-- 请选择 --</option>");
		for (var i = 0; i < dataList.length; i++) {
			if(value == dataList[i].materielName && $("#feedCompanyId").val() == dataList[i].id){
				if(materielModel != dataList[i].materielModel){
					$("#materielModel").append("<option value='"+dataList[i].materielNameId+"'>"+dataList[i].materielModel+"</option>");
				}
				materielModel = dataList[i].materielModel;
			}
		}
	}
}

//获取收料单位信息(ygt)
function getOutReceiveUnitInfo(){
	$.ajax({
		type : "POST",
		url : "../../WeighingQuery/getOutReceiveUnitInfo.action",
		async:false,
		data : {"purpose" : 1},
		dataType : "json",
		success : function(data) {
			if(data != null && data.length > 0){
				//收料单位下拉
				$("#receiveUnitId").empty();
				$("#receiveUnitId").append("<option value=''>-- 请选择 --</option>");
				// 收料单位名称
				for (var i = 0; i < data.length; i++) {
						$("#receiveUnitId").append("<option value='"+data[i].id+"'>"+data[i].receiveUnitName+"</option>");
				}
			}
		}
	});
}

//计算皮重
function getNetWeight(){
	//毛重
	var grossWeight = $("#grossWeight").val();
	//皮重
	var tareWeight = $("#tareWeight").val();
	//扣除量或扣除率（数据字典）0 扣除量t 1扣除率%
	var deduction2 = "";//扣除率
	if($("#deduction").val() == 1){
		//扣除率   净重=毛重-（毛重*扣损率）-皮重
		deduction2 = $("#numericalValue").val();
		$("#netWeight").val(((grossWeight-tareWeight)*(1-deduction2/100)).toFixed(3));
	}
}

//保存数据
function save(){
	var params = formToJson($("#form1"));
	
	//车牌号码
	if(params.plateNumber == ""){
		layer.alert("车牌号码不能为空!",{
			icon: 2,
			title: "提示"
		});
		return;
	}
	//供料单位
	if(params.feedCompanyId == ""){
		layer.alert("供料单位不能为空!",{
			icon: 2,
			title: "提示"
		});
		return;
	}
	//材料名称
	if(params.materielName == ""){
		layer.alert("材料名称不能为空!",{
			icon: 2,
			title: "提示"
		});
		return;
	}
	//规格型号
	if(params.materielModel == ""){
		layer.alert("规格型号不能为空!",{
			icon: 2,
			title: "提示"
		});
		return;
	}
	//毛重（t）
	if(params.grossWeight == ""){
		layer.alert("毛重（t）不能为空!",{
			icon: 2,
			title: "提示"
		});
		return;
	}
	//收料单位
	if(params.receiveUnitId == ""){
		layer.alert("收料单位不能为空!",{
			icon: 2,
			title: "提示"
		});
		return;
	}
	//扣除量（数值）
	if(params.numericalValue == ""){
		layer.alert("扣除量不能为空!",{
			icon: 2,
			title: "提示"
		});
		return;
	}
	//司机名称
	if(params.driverName == ""){
		layer.alert("司机名称不能为空!",{
			icon: 2,
			title: "提示"
		});
		return;
	}
	//皮重（t）
	if(params.tareWeight == ""){
		layer.alert("皮重（t）不能为空!",{
			icon: 2,
			title: "提示"
		});
		return;
	}
	//净重（t）
	if(params.netWeight == ""){
		layer.alert("净重（t）不能为空!",{
			icon: 2,
			title: "提示"
		});
		return;
	}
	//创建时间
	if(params.createrDate == ""){
		layer.alert("创建时间不能为空!",{
			icon: 2,
			title: "提示"
		});
		return;
	}
	
	//流水号
	params.serialId=weighingCount;
	//创建者
	params.creater=userInfo.name;
	// 保存操作
	$.ajax({
		type: 'POST',
		url: '../../WeighingQuery/addExportMeasureOut.action',
		data:JSON.stringify(params),
		dataType: 'json',
		contentType: 'application/json',
		success: function(data){
			if (data.code == 'success'){
				// 操作成功
				layer.alert(data.message, {
					icon: 1,
					title: "提示"
				},function(){
					location.reload();
				});
			} else {
				// 操作失败
				layer.alert(data.message, {
					icon: 2,
					title: "提示"
				});
			}			
		}
	});	
}

//车牌号码更改
function changePlateNumber(value){
	if (value.length == 7){
	  var express = /^[京津沪渝冀豫云辽黑湘皖鲁新苏浙赣鄂桂甘晋蒙陕吉闽贵粤青藏川宁琼使领A-Z]{1}[A-Z]{1}[A-Z0-9]{4}[A-Z0-9挂学警港澳]{1}$/;
	  if(!express.test(value)){
		  layer.alert("车牌号码格式不正确!",{
			  icon: 2,
			  title: "提示"
		  });
		  $("#plateNumber").val("");
		  return;
	  }
	}else{
		 layer.alert("车牌号码格式不正确!",{
			  icon: 2,
			  title: "提示"
		  });
		  $("#plateNumber").val("");
		  return;
	}
}