// 获取出库单id
var id = '';
var flags;
//物料名称和型号
var materielinfoList;
//销售订单编号
var orderNumberList;
//销售订单详情编号
var orderDetailNumberList;

var diaoOrderNumberList;
// 净重
var suttle = 0;
// 出库单信息
var info = "";
// 销售订单信息
var orderData = '';

var materielId;

var orderDetailId;
$(function(){
	// 销售订单编号
	getOrderNumberList();
	// 物料型号
	getMaterielinfoList();
	// 其他发货方式
	$("#otherDelivery").html(getDataDictionary('qtfhfs'));
	// 结算方式
	$("#transportBalance").html(getDataDictionary('ysjsqk'));
	
	// 获取上页面传过来的值
	id = getUrlParam("id");
	flags = getUrlParam("flags");
	// 获取出库单信息
	if (flags == "1") {
		if(id != null && id != ""){
			// 画面数据初始化
			$.ajax({
				type: "post",
				url: "../../outlist/getExportList.action",
				data:{"id":id},
				dataType: "json",
				async:false,
				success: function (data) {
					info = data.data[0];
					if (info != null) {
						SetFromValues($("#submitForm"), info);
						$("#allotClient").val(null);
						$("#allotCustomerAlias").val(null);
						$("#consignee").val(null);
						$("#consigneePhone").val(null);
						$("#consigneeAddress").val(null);
						$("#carName").val(info.plateNumber);
						$("#customerName").val(info.customerCode);
//						$("#orderNumber").val(info.salesOrderId);
						$("#orderDetailedId").val(info.orderDetailedId);
						// 物料名称和型号
						$("#materielName option[value='"+info.materielName+"']").attr("selected", true);
						$("#materielModel option[value='"+info.materielModel+"']").attr("selected", true);
							
						suttle = info.suttle;
						$.ajax({
							type: "post",
							url: "../../outbound/getDiaoMaterielId.action",
							data: {
								orderId: info.orderDetailedId
							},
							async: false,
							dataType: "json",
							success: function(data) {
								if (data != null && data.length > 0) {
									if (data[0].IsExchange) {
										materielId = data[0].MaterielId;
										getDiaoOrderNumber(data[0].MaterielId, info.orderDetailedId);
									}else{
										materielId = data[0].ExchangeMaterielId;
										getDiaoOrderNumber(data[0].ExchangeMaterielId, info.orderDetailedId);
									}
								}
							}
						});
						orderDetailId = info.orderDetailedId;
						changeOrderNumber(info.salesOrderId);
					}
					// 调拨重量
//					$("#allotWeight").val(info.suttle - info.amount);

				
				}
			});
		}
	}
//	else if(flags == "2"){
//		if(id != null && id != ""){
//			// 画面数据初始化
//			$.ajax({
//				type: "post",
//				url: "../../outlist/getNoweighoutList.action",
//				data:{"id":id},
//				dataType: "json",
//				async:false,
//				success: function (data) {
//					info = data.data[0];
//					
//					SetFromValues($("#submitForm"), info);
//					// 物料名称和型号
//					if (info != null) {
//						$("#materielName option[value='"+info.materielNameId+"']").attr("selected", true);
//						$("#materielModel option[value='"+info.materielModelId+"']").attr("selected", true);
//						
//						suttle = info.suttle;
//					}
//					// 调拨重量
////					$("#allotWeight").val(info.suttle - info.amount);
//				}
//			});
//		}
//	}
	
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
		url: "../../outbound/getOrderNumberList.action",
		data:{},
		dataType: "json",
		async:false,
		success: function (data) {
			if(data != null){
				// 销售订单编号
				orderNumberList = data;
				if(orderNumberList != null && orderNumberList.length > 0){
					// 清空
					$("#orderNumber").empty();
					$("#orderNumber").append("<option value=''>请选择</option>");
					var orderNumber = '';
					for(var i = 0; i < orderNumberList.length; i++){
						if(orderNumber != orderNumberList[i].id){
//							$("#orderNumber").append("<option value='"+orderNumberList[i].id+"'>"+orderNumberList[i].orderNumbers+"</option>");
						}
						orderNumber = orderNumberList[i].id;
					}
				}
			}
		}
	});
}


//物料名称和型号
function getMaterielinfoList(){
	$.ajax({
		type: "post",
		url: "../../outbound/getMaterielinfoList.action",
		data:{},
		dataType: "json",
		async:false,
		success: function (data) {
			if(data != null){
				// 物料名称和型号
				materielinfoList = data;
				if(materielinfoList != null && materielinfoList.length > 0){
					// 清空
					$("#materielName").empty();
					$("#materielModel").empty();
					var materielName = 0;
					$("#materielName").append("<option value=''>请选择</option>");
					$("#materielModel").append("<option value=''>请选择</option>");
					for(var i = 0; i < materielinfoList.length; i++){
						if(materielinfoList[i].materielName != materielName){
							$("#materielName").append("<option value='"+materielinfoList[i].materielName+"'>"+materielinfoList[i].materielName+"</option>");
						}
						
						materielName = materielinfoList[i].materielName;
						$("#materielModel").append("<option value='"+materielinfoList[i].materielModel+"'>"+materielinfoList[i].materielModel+"</option>");
					}
				}
			}
		}
	});
}

// 保存
function add(){
	// 获取画面数据
	var data = formToJson($("#submitForm"));
	// 结算数量
//	if(data.amount.trim() == ""){
//		swal({
// 			title: "错误提示",
// 			text: "结算数量不能为空!",
// 			type: "error",
// 			confirmButtonText: '确定',
// 			cancelButtonFont: '微软雅黑',
// 		});
//		return;
//	}
	
	// 调拨数量
	if(data.allotWeight.trim() == ""){
		swal({
 			title: "错误提示",
 			text: "调拨数量不能为空!",
 			type: "error",
 			confirmButtonText: '确定',
 			cancelButtonFont: '微软雅黑',
 		});
		return;
	}	
	if (parseFloat(data.allotWeight) > parseFloat(data.suttle)){
		swal({
 			title: "错误提示",
 			text: "调拨数量不能大于出库重量!",
 			type: "error",
 			confirmButtonText: '确定',
 			cancelButtonFont: '微软雅黑',
 		});
		return;
	}
	
	// 调拨客户
	if(data.allotClient == ""){
		swal({
 			title: "错误提示",
 			text: "调拨客户名称不能为空!",
 			type: "error",
 			confirmButtonText: '确定',
 			cancelButtonFont: '微软雅黑',
 		});
		return;
	}
	
	// 调拨客户别称
	if(data.allotCustomerAlias == ""){
		swal({
 			title: "错误提示",
 			text: "调拨客户别称不能为空!",
 			type: "error",
 			confirmButtonText: '确定',
 			cancelButtonFont: '微软雅黑',
 		});
		return;
	}
	
	// 结算情况
	if(data.transportBalance == ''){
		swal({
 			title: "错误提示",
 			text: "结算方式必须选择!",
 			type: "error",
 			confirmButtonText: '确定',
 			cancelButtonFont: '微软雅黑',
 		});
		return;
	}
	data.transportBalance = $("#transportBalance").val();
	// 收货人
	if(data.consignee == ''){
		swal({
 			title: "错误提示",
 			text: "收货人不能为空!",
 			type: "error",
 			confirmButtonText: '确定',
 			cancelButtonFont: '微软雅黑',
 		});
		return;
	}
	// 收货联系方式
	if(data.consigneePhone == ''){
		swal({
 			title: "错误提示",
 			text: "收货联系方式不能为空!",
 			type: "error",
 			confirmButtonText: '确定',
 			cancelButtonFont: '微软雅黑',
 		});
		return;
	}
	// 收货地址
	if(data.consigneeAddress == ''){
		swal({
 			title: "错误提示",
 			text: "收货地址不能为空!",
 			type: "error",
 			confirmButtonText: '确定',
 			cancelButtonFont: '微软雅黑',
 		});
		return;
	}
	// 销售订单明细
	if(data.orderDetailedId == ''){
		swal({
 			title: "错误提示",
 			text: "销售订单明细不能为空!",
 			type: "error",
 			confirmButtonText: '确定',
 			cancelButtonFont: '微软雅黑',
 		});
		return;
	}
	
	// 物料id
	data.materielInfoId = info.productID;	
	// 出库单id
	data.id = id;
	// 销售合同id
	data.salesOrderId = orderData.id;
	// 销售金额
	data.saleMoney = orderData.saleMoney;
	// 销售单价
	data.salePrice = orderData.salePrice;
	// 销售数量
	data.saleNumber = orderData.saleNumber;
	// 税率
	data.taxRate = orderData.taxRate;
	/*// 是否检测
	data.isTesting = info.isTesting;*/
	// 送货地址
	data.address = orderData.address;
	data.plateNumber = data.carName;
	// 收货人
/*	data.consignee = info.consignee;
	// 收货人电话
	data.consigneePhone = info.consigneePhone;*/
	// 未称重出库单编号
//	data.serialID = info.serialId;
	data.serialID = getContractInfoPrefix("WCZ");
	data.YSserialID = getContractInfoPrefix("YS");
	
	// 兑换的场合 状态变为兑换时调拨
	if (info.outType == 4) {
		data.outType = 5
	} else {
		// 正常的场合 状态变为调拨
		data.outType = 1
	}
	// 半车调拨的场合 多增加一张未称重出库单
	if (parseFloat(data.allotWeight) < parseFloat(data.suttle)){
		updateContractInfoPrefix("WCZ");
		updateContractInfoPrefix("YS");
		// 流水号
		data.serialID1 = getContractInfoPrefix("WCZ");
		data.YSserialID1 = getContractInfoPrefix("YS");
		// 销售订单id
		data.salesOrderId1 = info.orderId;
		// 销售订单明细id
		data.orderDetailedId1 = info.orderDetailedId;
		// 调拨重量
		data.allotWeight1 = parseFloat(data.suttle) - parseFloat(data.allotWeight);
		// 销售数量
		data.saleNumber1 = info.saleNumber;
		// 销售单价
		data.salePrice1 = info.salePrice;
		// 销售金额
		data.saleMoney1 = info.saleMoney;
		// 客户名称
		data.allotClient1 =  info.customerCode;
		// 客户别称
		data.allotCustomerAlias1 = info.customerAlias;
		// 税率
		data.taxRate1 = info.taxRate;
		// 送货地址
		data.address1 = info.address;
		// 收货人
		data.consignee1 = info.consignee;
		// 收货地址
		data.consigneeAddress1 = info.consigneeAddress;
		// 收货电话
		data.consigneePhone1 = info.consigneePhone;
		// 运输结算情况
		data.transportBalance1 = info.transportBalances;
	}

	// 新增未称重出库单/出库单更新结算重量
	$.ajax({
		type: "post",
		url: "../../outlist/haltCar.action",
		data:data,
		dataType: "json",
		async:false,
		success: function (data) {
			console.log(data);
			if(data != null){
				if(data.code == "success"){
					swal({
			 			title: "提示",
			 			text: data.message,
			 			type: data.code,
			 			confirmButtonText: '确定',
			 			cancelButtonFont: '微软雅黑',
			 		},function(){
			 			window.parent.$.fancybox.close();
			 		});
					updateContractInfoPrefix("WCZ");
					updateContractInfoPrefix("YS");
				}else{
					swal({
			 			title: "错误提示",
			 			text: data.message,
			 			type: data.code,
			 			confirmButtonText: '确定',
			 			cancelButtonFont: '微软雅黑',
			 		});
				}
			}
		}
	});
}

// 结算数量和退货数量的计算
//function changeAmount(flag){
//	// 结算数量变更
//	if(flag == 0){
//		var amount = $("#amount").val();
//		if(amount == ''){
//			swal({
//	 			title: "错误提示",
//	 			text: "结算数量不能为空!",
//	 			type: "error",
//	 			confirmButtonText: '确定',
//	 			cancelButtonFont: '微软雅黑',
//	 		});
//			return;
//		}else if(amount > suttle){
//			swal({
//	 			title: "错误提示",
//	 			text: "结算数量大于出库重量!",
//	 			type: "error",
//	 			confirmButtonText: '确定',
//	 			cancelButtonFont: '微软雅黑',
//	 		});
//			return;
//		}
//		else{
//			$("#allotWeight").val(suttle - amount);
//		}
//	}
//}
//车牌号码更改
function changePlateNumber(value){
	if(plateNumberList != null){
		for(var i=0; i<plateNumberList.length; i++){
			if(plateNumberList[i].plateNumber == value){
				// 车主
				$("#carOwner").val(plateNumberList[i].driver1);
				// 车主电话
				$("#carOwnerTelephone").val(plateNumberList[i].telephone1);
				break;
			}
		}
	}
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
		 			text: "送货人联系电话格式错误,请重新填写!",
		 			type: "error",
		 			confirmButtonText: '确定',
		 			cancelButtonFont: '微软雅黑',
		 		},function(){
		 		// 清空处理
					$("#deliveryPhone").val("");
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

//只能入数字和小数点  
function keepNumOrDecimal(obj){ 
  obj.value = obj.value.replace(/[^\d.]/g,"");  //清除“数字”和“.”以外的字符  
	obj.value = obj.value.replace(/^\./g,"");   //必须保证第一个为数字而不是.
  obj.value = obj.value.replace(/\.{2,}/g,"."); //只保留第一个. 清除多余的  
  obj.value = obj.value.replace(".","$#$").replace(/\./g,"").replace("$#$","."); //保证.只出现一次，而不能出现两次以上 
  obj.value = obj.value.replace(/^(\-)*(\d+)\.(\d\d).*$/,'$1$2.$3');//只能输入两个小数 
  if(obj.value.indexOf(".")< 0 && obj.value !=""){//以上已经过滤，此处控制的是如果没有小数点，首位不能为类似于 01、02的金额 
      obj.value= parseFloat(obj.value); 
  } 
} 

//最后一位不是小数点
function checkPoint(obj){
	keepNumOrDecimal(obj);
	if (obj.value.charAt(obj.value.length - 1)=='.'){
		obj.value = obj.value.substring(0,obj.value.length - 1);
	}		
}


//获取调拨销售订单编号
function getDiaoOrderNumber (materielId, orderDetailId) {
	$.ajax({
		type: "post",
		url: "../../outbound/getDiaoOrderNumber.action",
		data: {
			materielId: materielId,
			orderDetailId: orderDetailId
		},
		async: false,
		dataType: "json",
		success: function(data) {
			if (data != null && data.length > 0) {
				diaoOrderNumberList = data;
				$("#orderNumber").empty();
				$("#orderNumber").append("<option value=''>请选择</option>");
				for(var i = 0; i < data.length; i++){
					$("#orderNumber").append("<option value='"+data[i].Id+"'>"+data[i].OrderNumber+"</option>");
				}
			}
		}
	});
}

//销售订单编号的更改
function changeOrderNumber(value){
//	debugger;
	// 给订单明细赋值
	$("#orderDetailedId").empty();
	$("#orderDetailedId").append("<option value=''>请选择</option>");
	$.ajax({
		type: "post",
		url: "../../outbound/getDiaoOrderDetail.action",
		data: {
			id: value,
			materielId: materielId,
			orderDetailId: orderDetailId
		},
		dataType: "json",
		async: false,
		success: function(data) {
			if (data != null && data.length > 0) {
				orderDetailNumberList = data;
				for(var i = 0; i < data.length; i++){
					if (data[i].isExchange) {
						$("#orderDetailedId").append("<option value='"+data[i].Id+"'>"+data[i].OrderDetailedNumber+"         "+data[i].MaterielInfo+"         "+data[i].Transports+"</option>");	
					}else{
						$("#orderDetailedId").append("<option value='"+data[i].Id+"'>"+data[i].OrderDetailedNumber+"         "+data[i].MaterielInfo+"         "+data[i].Transports+"</option>");
					}
					
				}
			}
		}
	});
	if(diaoOrderNumberList != null){
		for(var i=0; i<diaoOrderNumberList.length; i++){
			if(diaoOrderNumberList[i].Id == value){
				// 调拨客户名称
				$("#allotClient").val(diaoOrderNumberList[i].customerCode);
				// 调拨客户别称
				$("#allotCustomerAlias").val(diaoOrderNumberList[i].customer);
				// 送货地址
				$("#contractNumber").val(diaoOrderNumberList[i].contractNumber);
				break;
			}
		}
	}
	if(orderNumberList != null){
		for(var i=0; i<orderNumberList.length; i++){
			if(orderNumberList[i].id == value){
				orderData = orderNumberList[i];
				break;
			}
		}
	}
}

//获取调拨销售订单明细编号
function changeDetailedNumber (value) {
	if(orderDetailNumberList != null){
		for(var i=0; i<orderDetailNumberList.length; i++){
			if(orderDetailNumberList[i].Id == value){
				// 调拨收货地址
				$("#consigneeAddress").val(orderDetailNumberList[i].Transports);
				// 根据收货地址查询收货人和收货电话
				$.ajax({
					type: "post",
					url: "../../outbound/getConsigneeInfo.action",
					data: {
						consigneeAddress: orderDetailNumberList[i].Transports
					},
					dataType: "json",
					async: false,
					success: function(data) {
						if (data != null && data.length > 0) {
							diaoConsignee = data;
							for(var i = 0; i < data.length; i++){
								$("#diaoConsigneeList").append("<option value='"+data[i].Consignee+"'></option>");
								$("#diaoConsigneePhoneList").append("<option value='"+data[i].ConsigneePhone+"'></option>");
							}
							$("#consignee").val(data[0].Consignee);
							$("#consigneePhone").val(data[0].ConsigneePhone);
						}
					}
				});
				break;
			}
		}
	}
}

// 修改调拨收货人/调拨收货人电话
function diaoConsigneeChange (value, flag) {
	if (diaoConsignee != null) {
		if (flag == 1) {						// 修改收货人
			// 先清空收货人电话的datalist
			$("#diaoConsigneePhoneList").html("");
			$("#consigneePhone").val("");
			// 如果收货人为空时。初始化收货人电话的datalist
			if ($("#consignee").val() == "") {
				for (var i = 0 ; i < diaoConsignee.length ; i++) {
					$("#diaoConsigneePhoneList").append("<option value='"+diaoConsignee[i].ConsigneePhone+"'></option>");
				}
			}
			for (var i = 0 ; i < diaoConsignee.length ; i++) {
				if (diaoConsignee[i].Consignee == value) {
					$("#diaoConsigneePhoneList").append("<option value='"+diaoConsignee[i].ConsigneePhone+"'></option>");
				}
			}
		} else if (flag == 2) {					// 修改收货人电话
			$("#consignee").val("");
			// 校验格式
			changeTelephone(value,3);
			for (var i = 0 ; i < diaoConsignee.length ; i++) {
				if (diaoConsignee[i].ConsigneePhone == value) {
					$("#consignee").val(diaoConsignee[i].Consignee);
					break;
				}
			}
		}
	}
}
