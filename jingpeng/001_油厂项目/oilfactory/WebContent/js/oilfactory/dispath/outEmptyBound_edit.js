// 主键id
var id = '';
// 用户id
var userId = '';
//用户的权限
var rolecode;
//赋值和修改的标识
var flag;
// 所有车辆号码
var plateNumberList;
// 销售订单list
var orderNumberList;
$(function() {
	// 获取用户信息
	var userInfo = getUserInfo();
	userId = userInfo.username;
	
	// 获取上页面传过来的值
	id = getUrlParam("id");
	flag = getUrlParam("flag");
	rolecode = getUrlParam("rolecode");
	
	// 新增场合
	if (flag != 1){
		$("#serialId").val(getContractInfoPrefix("CK"));
	}
	
	// 所有车辆号码 
	getAllPlateNumbers();
	// 销售订单
	getOrderNumberList();
	
	//称重类型
	cartWeighTypeList = getDataDictionary('weighingtype');
	$("#cartWeighType").append(cartWeighTypeList);
	
	// 修改
	if(id != null && id != ''){
		$.ajax({
			type: "post",
			url: "../../ExportMeasure/getExportMeasureList.action",
			data:{"id":id},
			dataType: "json",
			async:false,
			success: function (data) {
				oldPlateNumber = data.data[0].plateNumber;
				// 销售订单明细
				changeOrderNumber(data.data[0].salesOrderId);
				// 出库单
				getOutboundList(data.data[0].transports);
				SetFromValues($("#submitForm"), data.data[0]);
			    // 空发关联的出库单
				$("#outboundList option[value='"+data.data[0].outWarehouseId+"']").attr("selected", true);
				// 用户的权限(查看)
				if(rolecode == 'false' && flag == 0){
					$("#submitbutton").hide();
					$("input").attr("readonly",true);
					$("select").attr("disabled",true);
				}else{
					// 复制
					if(flag == 0){
						$("#serialId").val(getContractInfoPrefix("CK"));
					}
					$("#submitbutton").show();
				}
			}
		});
	}
});

//form表单赋值方法
function SetFromValues(el, data)
{
    for (var p in data)
    {
        el.find(":input[name='" + p + "']").val(data[p]);
    } 
}

//销售订单编号
function getOrderNumberList(){
	$.ajax({
		type: "post",
		url: "../../ExportMeasure/getSalesOrderList.action",
		data:{
			type:"empty",
		},
		dataType: "json",
		async:false,
		success: function (data) {
			if(data != null){
				// 销售订单编号
				orderNumberList = data;
				if(orderNumberList != null && orderNumberList.length > 0){
					// 清空
					$("#salesOrderId").empty();
					$("#salesOrderId").append("<option value=''>请选择</option>");
					var orderNumber = '';
					for(var i = 0; i < orderNumberList.length; i++){
						if(orderNumber != orderNumberList[i].id){
							// 判断标识
							var pdFlag = false;
							for(var j = 0; j < orderNumberList.length; j++){
								if(orderNumberList[i].id == orderNumberList[j].id){
									// 存在关联出库单
									if(orderNumberList[j].exmOrderDetailedId == null){
										pdFlag = true;
										continue;
									}
								}
							}
							
							// 字体变红
							if(pdFlag){
								$("#salesOrderId").append("<option style='color:red;' value='"+orderNumberList[i].id+"'>"+orderNumberList[i].orderNumbers+"_"+orderNumberList[i].customerName+"</option>");
							}else{
								$("#salesOrderId").append("<option value='"+orderNumberList[i].id+"'>"+orderNumberList[i].orderNumbers+"_"+orderNumberList[i].customerName+"</option>");
							}
						}
						orderNumber = orderNumberList[i].id;
					}
				}
			}
		}
	});
}

//出库单号
function getOutboundList(value){
	$.ajax({
		type: "post",
		url: "../../ExportMeasure/getAllOutboundList.action",
		data:{"transports":value},
		dataType: "json",
		async:false,
		success: function (data) {
			console.log(data);
			if(data != null){
				// 车牌号码
				var outboundList = data;
				// 清空
				$("#outboundList").empty();
				$("#outboundList").append("<option value=''>请选择</option>");
				for(var i = 0; i < outboundList.length; i++){
					$("#outboundList").append("<option value='"+outboundList[i].Serial_ID+"'>"+outboundList[i].Serial_ID+" / "+outboundList[i].MaterielInfo+" / "+outboundList[i].Transports+" / "+outboundList[i].PlateNumber+"</option>");
				}
			}
		}
	});
}

// 所有车牌号码
function getAllPlateNumbers(){
	$.ajax({
		type: "post",
		url: "../../ExportMeasure/getAllPlateNumbers.action",
		data:{},
		dataType: "json",
		async:false,
		success: function (data) {
			if(data != null){
				if(data != null && data.length > 0){
					plateNumberList = data;
					// 清空
					$("#plateNumberList").empty();
					for(var i = 0; i < data.length; i++){
						$("#plateNumberList").append("<option value='"+data[i].plateNumber+"'>"+data[i].plateNumber+"</option>");
					}
				}
			}
		}
	});
}

// 车辆号码change事件
function changePlateNumber(value){
//	var result = false;
	if(plateNumberList != null){	
//		// 验证格式
//		if (value.length == 7){
//		  var express = /^[京津沪渝冀豫云辽黑湘皖鲁新苏浙赣鄂桂甘晋蒙陕吉闽贵粤青藏川宁琼使领A-Z]{1}[A-Z]{1}[A-Z0-9]{4}[A-Z0-9挂学警港澳]{1}$/;
//		  result = express.test(value);
//		}
		
//		if (result) {
			for(var i=0; i<plateNumberList.length; i++){
				if(plateNumberList[i].plateNumber == value){
					// 车主
					$("#deliveryMan").val(plateNumberList[i].driver1);
					// 车主电话
					$("#deliveryManPhone").val(plateNumberList[i].telephone1);
					// 荷载吨数
					$("#loadTon").val(plateNumberList[i].loadTon);
					break;
				}
			}
//		} else {
//			swal({
//	 			title: "错误提示",
//	 			text: "车牌号码格式不正确！",
//	 			type: "error",
//	 			confirmButtonText: '确定',
//	 			cancelButtonFont: '微软雅黑',
//	 		},function(){
//	 			// 清空处理
//				$("#plateNumber").val("");
//	 		});
//		}
	}
}

// 销售订单编号change事件
function changeOrderNumber(value){
	// 给其他表单项赋值
	if (value != null && value != "") {
		if(orderNumberList != null){
			$("#orderDetailedId").empty();
			$("#orderDetailedId").append("<option value=''>请选择</option>");
			for(var i=0; i<orderNumberList.length; i++){
				if(orderNumberList[i].id == value){
					// 是否存在关联出库单
					if(orderNumberList[i].exmOrderDetailedId == null){
						// 字体变红
						$("#orderDetailedId").append("<option style='color:red;' value='"+orderNumberList[i].orderDetailedId+"'>"
								+orderNumberList[i].orderDetailedNumber+" / "+orderNumberList[i].materielName
								+"-"+orderNumberList[i].materielModel+" / "+orderNumberList[i].transports+"</option>");
					}else{
						$("#orderDetailedId").append("<option value='"+orderNumberList[i].orderDetailedId+"'>"
								+orderNumberList[i].orderDetailedNumber+" / "+orderNumberList[i].materielName
								+"-"+orderNumberList[i].materielModel+" / "+orderNumberList[i].transports+"</option>");
					}
					
					// 客户编号
					$("#customerCode").val(orderNumberList[i].customerCode);
					// 客户名称
					$("#customerName").val(orderNumberList[i].customerName);
					// 客户别称
					$("#customerAlias").val(orderNumberList[i].customerAlias);
					// 送货地址
					$("#address").val(orderNumberList[i].address);
					// 收货人
					$("#consignee").val(orderNumberList[i].contacts);
					// 联系电话
					$("#consigneePhone").val(orderNumberList[i].telephone);
					// 销售公司名称
					$("#saleCompanyName").val(orderNumberList[i].saleCompanyName);
				}
			}
		}
	} else {
		$("#orderDetailedId").empty();
		// 客户编号
		$("#customerCode").val("");
		// 客户别称
		$("#customerAlias").val("");
		// 送货地址
		$("#address").val("");
		// 收货人
		$("#consignee").val("");
		// 联系电话
		$("#consigneePhone").val("");
		// 销售公司名称
		$("#saleCompanyName").val("");
	}
}

// 销售订单明细编号change事件
function changeDetailedNumber(value){
	// 给其他表单项赋值
	if (value != null && value != "") {
		if(orderNumberList != null){
			for(var i=0; i<orderNumberList.length; i++){
				if(orderNumberList[i].orderDetailedId == value){
					// 物料id
					$("#productId").val(orderNumberList[i].materielId);
					// 物料名称
					$("#materielName").val(orderNumberList[i].materielName);
					// 物料型号
					$("#materielModel").val(orderNumberList[i].materielModel);
					// 结算情况
					$("#transportBalancesName").val(orderNumberList[i].transportBalancesName);
					// 止运地
					$("#transports").val(orderNumberList[i].transports);
					// 运距
					$("#distance").val(orderNumberList[i].distance);
					// 磅单打印名称
					$("#lbsMaterialName").val(orderNumberList[i].listModel);
					// 销售数量
					$("#saleNumber").val(orderNumberList[i].saleNumber);
					// 销售备注
					$("#salRemarks").val(orderNumberList[i].salRemarks);
					// 出库单
					getOutboundList(orderNumberList[i].transports);
				}
			}
		}	
	} else {
		// 物料id
		$("#productId").val("");
		// 物料名称
		$("#materielName").val("");
		// 物料型号
		$("#materielModel").val("");
		// 结算情况
		$("#transportBalancesName").val("");
		// 止运地
		$("#transports").val("");
		// 运距
		$("#distance").val("");
		// 磅单打印名称
		$("#lbsMaterialName").val("");
		// 销售数量
		$("#saleNumber").val("");
		// 出库单
		$("#outboundList").empty();
	}
}

// 保存操作
function save(){
	// 获取画面数据
	var param = formToJson($("#submitForm"));
	// 销售订单编号
	if(param.salesOrderId == ""){
		swal({
 			title: "错误提示",
 			text: "销售订单编号选择!",
 			type: "error",
 			confirmButtonText: '确定',
 			cancelButtonFont: '微软雅黑',
 		});
		return;
	}

	// 销售合同明细编号id
	if(param.orderDetailedId == ""){
		swal({
			title: "错误提示",
			text: "销售合同明细编号必须选择!",
			type: "error",
			confirmButtonText: '确定',
			cancelButtonFont: '微软雅黑',
		});
		return;
	}
	
	// 车牌号码
	if(param.plateNumber == ""){
		swal({
			title: "错误提示",
			text: "车牌号码不能为空!",
			type: "error",
			confirmButtonText: '确定',
			cancelButtonFont: '微软雅黑',
		});
		return;
	}
	
	// 送货人
/*	if(param.deliveryMan == ""){
		swal({
			title: "错误提示",
			text: "送货人不能为空!",
			type: "error",
			confirmButtonText: '确定',
			cancelButtonFont: '微软雅黑',
		});
		return;
	}
	
	// 送货电话
	if(param.deliveryManPhone == ""){
		swal({
			title: "错误提示",
			text: "送货人不能为空!",
			type: "error",
			confirmButtonText: '确定',
			cancelButtonFont: '微软雅黑',
		});
		return;
	}*/
	
	// 车辆称重类型
	if(param.cartWeighType == ""){
		swal({
			title: "错误提示",
			text: "车辆称重类型必须选择!",
			type: "error",
			confirmButtonText: '确定',
			cancelButtonFont: '微软雅黑',
		});
		return;
	}
	
	// 毛重
	if(param.grossWeight == ""){
		swal({
			title: "错误提示",
			text: "毛重不能为空!",
			type: "error",
			confirmButtonText: '确定',
			cancelButtonFont: '微软雅黑',
		});
		return;
	}
	
	// 皮重
	if(param.tareWeight == ""){
		swal({
			title: "错误提示",
			text: "皮重不能为空!",
			type: "error",
			confirmButtonText: '确定',
			cancelButtonFont: '微软雅黑',
		});
		return;
	}
	
	// 收货人
	if(param.consignee == ""){
		swal({
			title: "错误提示",
			text: "收货人不能为空!",
			type: "error",
			confirmButtonText: '确定',
			cancelButtonFont: '微软雅黑',
		});
		return;
	}
	
	// 收货电话
	if(param.consigneePhone == ""){
		swal({
			title: "错误提示",
			text: "联系电话不能为空!",
			type: "error",
			confirmButtonText: '确定',
			cancelButtonFont: '微软雅黑',
		});
		return;
	}
	
	// 送货地址
	if(param.address == ""){
		swal({
			title: "错误提示",
			text: "送货地址不能为空!",
			type: "error",
			confirmButtonText: '确定',
			cancelButtonFont: '微软雅黑',
		});
		return;
	}
	
	// 出库单
	if(param.outboundList == ""){
		swal({
			title: "错误提示",
			text: "出库单必须选择!",
			type: "error",
			confirmButtonText: '确定',
			cancelButtonFont: '微软雅黑',
		});
		return;
	}
	
	// 出库单状态
	param.outType =3;
	// 创建人/修改人
	param.userId = userId;
	
	// 操作路径
	var url = "";
	if(id != null && id != ""){
		// 复制
		if(flag == 0){
			// 新增
			url = "../../ExportMeasure/addExportMeasure.action";
			// 出库单编号
			param.serialId = getContractInfoPrefix("CK");
		}
	}else{
		url = "../../ExportMeasure/addExportMeasure.action";
		// 出库单编号
		param.serialId = getContractInfoPrefix("CK");
	}
	$.ajax({
		type: "post",
		url: url,
		data:param,
		dataType: "json",
		success: function (data) {
			if(data != null){
				if(data.code == "success"){
					// 出库单/运输单编号自增
					updateContractInfoPrefix("CK");
					updateContractInfoPrefix("YS");
				}
				swal({
					title: "提示",
					text: data.message,
					type: data.code,
					confirmButtonText: '确定',
					cancelButtonFont: '微软雅黑',
				},function(){
					window.parent.$.fancybox.close();
				});
			}
		}
	});
	
}

//电话号码
function changeTelephone(value, flag){
	var regBox = {
	        regMobile : /^0?1[3|4|5|8][0-9]\d{8}$/,//手机
	        regTel : /^0[\d]{2,3}-[\d]{7,8}$/ // 固定电话
	    }
	if(value != null && value != ''){
		var mflag = regBox.regMobile.test(value);
	    var tflag = regBox.regTel.test(value);
	    if (!(mflag || tflag)) {
	    	if(flag == 1){
	    		swal({
		 			title: "错误提示",
		 			text: "收货人联系电话格式错误,请重新填写!",
		 			type: "error",
		 			confirmButtonText: '确定',
		 			cancelButtonFont: '微软雅黑',
		 		},function(){
		 			// 清空处理
					$("#consigneePhone").val("");
		 		});
	    	}else if(flag == 2){
	    		swal({
		 			title: "错误提示",
		 			text: "送货人联系电话格式错误,请重新填写!",
		 			type: "error",
		 			confirmButtonText: '确定',
		 			cancelButtonFont: '微软雅黑',
		 		},function(){
		 			// 清空处理
					$("#deliveryManPhone").val("");
		 		});
	    	}
	    }
	}else{
		swal({
 			title: "错误提示",
 			text: "联系电话必须填写!",
 			type: "error",
 			confirmButtonText: '确定',
 			cancelButtonFont: '微软雅黑',
 		});
	}   
}

//净重
function setSuttle() {
	// 当毛重和皮重都有值时。计算净重并赋值
	if ($("#grossWeight").val() != "" && $("#tareWeight").val() != "") {
		// 净重 = 毛重 - 皮重
		var suttle = $("#grossWeight").val()*1 - $("#tareWeight").val()*1;
		// 毛重需大于皮重
		if (suttle < 0) {
			swal({
	 			title: "错误提示",
	 			text: "毛重需大于皮重！",
	 			type: "error",
	 			confirmButtonText: '确定',
	 			cancelButtonFont: '微软雅黑',
	 		},function(){
	 		// 清空处理
				$("#grossWeight").val("");
				$("#tareWeight").val("");
	 		});
		} else {
			$("#suttle").val(suttle);
		}
	}
}
