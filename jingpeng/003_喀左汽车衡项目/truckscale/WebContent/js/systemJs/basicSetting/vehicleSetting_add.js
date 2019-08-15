//获取的id
var id = "";
//用户的id
var userId = "";
//用户信息
var userInfo = "";
//车辆牌号
var plateNumberOnly = "";
//车辆自增编号
var carCount;

$(function(){
	//用户信息
	userId = getUrlParam("userId");
	if(userId != null && userId != ""){
		userInfo = getUserInfo(userId);
	}
	
	//运输单位
	getTransportCompanyList();
	
	//传所需的前缀英文简写
	$.ajax({
		type : "POST",
		url : "../../vehicleSetting/getCarCount.action",
		async:false,
		data : {"type":"GA"},
		dataType : "json",
		success : function(data) {
			carCount = data.carCount;
		}
	});
	
	//获取传值的id
	id = getUrlParam("id");
	
	if(id != null && id != ""){
		//获取车辆修改的信息
		$.ajax({
			type : "POST",
			url : "../../vehicleSetting/getAllCarInfo.action",
			data : {"id" : id},
			async:false,
			dataType : "json",
			success : function(data){
				//画面赋值
				if(data != null && data.data.length > 0){
					SetFromValues($("#form1"), data.data[0]);
					//车辆牌号
					plateNumberOnly = data.data[0].plateNumber;
				}
			}
		});
	}else{
		$("#carNumber").val(carCount);
	}
});


//查询运输单位
function getTransportCompanyList(){
	$.ajax({
		type : "POST",
		url : "../../vehicleSetting/getTransportCompanyList.action",
		data : {},
		dataType : "json",
		async:false,
		success : function(data){
			//清空操作
			$("#transportUnitId").empty();
			$("#transportUnitId").append("<option value=''>-- 请选择 --</option>");
			if(data != null && data.length > 0){
				for (var i = 0; i < data.length; i++) {
					$("#transportUnitId").append("<option value='"+data[i].id+"'>"+data[i].transportCompanyName+"</option>");
				}
			}
		}
	});
}

//保存按钮
function save(){
	var params = formToJson($("#form1"));
	//车辆编号
	if(params.carNumber == ""){
		layer.alert("车辆编号不能为空!",{
			icon: 2,
			title: "提示"
		});
		return;
	}
	//车辆牌号
	if(params.plateNumber == ""){
		layer.alert("车辆牌号不能为空!",{
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
	
	if(id == null){
		//添加车辆信息
		url = "../../vehicleSetting/addCarInfo.action";
		params.creater = userInfo.name;
	}else{
		//修改车辆信息
		url = "../../vehicleSetting/updateCarInfo.action"
		params.id = id;
		params.reviser=userInfo.name;
	}
	
	//保存操作
	$.ajax({
		type : "POST",
		url : url,
		data : JSON.stringify(params),
		dataType : "json",
		contentType : "application/json",
		success : function(data){
			if(data.code == 'success'){
				//操作成功
				layer.alert(data.message,{
					icon: 1,
					title: "提示"
				},function(){
					window.parent.location.reload();
				});
			}else{
				//操作失败
				layer.alert(data.message,{
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
	  }else{

		  //车辆牌号去重
		  if(id != null && id != ""){
				if(plateNumberOnly == value){
					return;
				}
			}
			$.ajax({
				type: 'POST',
				url: "../../vehicleSetting/getCarInfo.action",
				data:{"plateNumber" : value},
				dataType: 'json',
				success: function(data){
					if(data != null && data.length > 0){
						layer.alert("车辆牌号已经存在,请确认!", {
							icon: 2,
							title: "提示"
						});
						$("#plateNumber").val("");
						return;
					}
				}
			});
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

//运输单位的改变事件
function changeTransportUnit(value){
	if(value){
		$.ajax({
			type : "POST",
			url : "../../vehicleSetting/getTransportCompanyList.action",
			data : {"id" : value},
			dataType : "json",
			success : function(data){
				//车主姓名
				$("#carOwnerName").val(data[0].carOwnerName);
			}
		});
	}else{
		//车主姓名
		$("#carOwnerName").val("");
	}
}
