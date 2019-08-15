var id = "";
//销售订单编号
var orderNumberList;
//销售订单明细的获取
var orderDetailNumberList;
//物料名称和型号
var materielinfoList;
//出库单状态流水号
var outTypeNameList;
//复制和修改的标识
var flag;
//参数
var param;
//未称重流水号
var serialID;
//调拨标识
var outType;
$(function(){
	// 销售订单编号
	getOrderNumberList(param);
	//数据字典查询税率，运输结算情况，其他发货方式，类型
	getAllDatadictionaty();
	
	// 获取上页面传过来的值
	id = getUrlParam("id");
	flag = getUrlParam("flag");
	if(flag != 1){
		serialID = getContractInfoPrefix("WCZ");
		$("#serialID").val(serialID);
	}
	if(flag == 2){
		// 隐藏保存按钮
		$("#submitbutton").hide();
	}
	if(id != null && id != ""){
		// 修改
		if(flag == 1){
			$("#serialIDFlag").show();
		}
		// 画面数据初始化
		$.ajax({
			type: "post",
			url: "../../repertory/getOutboundInfo.action",
			data:{"id":id},
			dataType: "json",
			async:false,
			success: function (data) {
				SetFromValues($("#submitForm"), data);
				if(data.type == 1){
					//调拨
					outType = 1;
				}else if(data.type == 0){
					outType = 0;
				}
				//复制未称重编号变化
				if(flag == 0){
					var wcz = getContractInfoPrefix("WCZ");
					$("#serialID").val(wcz);
				}
				// 销售订单编号
				$("#orderNumber option[value='"+data.salesOrderId+"']").attr("selected", true);
				$("#orderNumber").attr("readonly","true");
			
				//销售订单明细
				getOrderDetailInfo(data.salesOrderId);
				$("#orderDetailedId option[value='"+data.orderDetailedNumber+"']").attr("selected", true);
				
				// 物料名称和型号
				getMaterielinfoList(data.materielId);
				$("#materielName option[value='"+data.materielName+"']").attr("selected", true);
				$("#materielModel option[value='"+data.materielModel+"']").attr("selected", true);
				
				//出库时间
				$("#ckTime").val(data.factoryTime);
				
				//是否随车带走
				if(data.isCarryOff==1){ 
					$("#isCarryOff2")[0].checked="checked";//否
					$('input[type=radio][name=isSelfLifting]').attr("disabled",false);
					$('#isShowTextkehu').show();
					$('#isShowValkehu').show();
					$("#isSelfLifting2")[0].checked="checked";//否
					// 车辆信息
					$('#customerCarName').attr("disabled",true);
					//$('#customerCarName').val("");
					$('#customerCarName').removeClass("inputEable");
					$('#customerCarName').addClass("inputDisable");
					$('#otherDelivery').attr("disabled",false);
					$('#otherDelivery').removeClass("inputDisable");
					$('#otherDelivery').addClass("inputEable");
					if(data.isSelfLifting == 1){
						$("#isSelfLifting2")[0].checked="checked";//否
						// 车辆信息
						$('#customerCarName').attr("disabled",true);
						$('#customerCarName').val("");
						$('#customerCarName').removeClass("inputEable");
						$('#customerCarName').addClass("inputDisable");
						$('#otherDelivery').attr("disabled",false);
						$('#otherDelivery').removeClass("inputDisable");
						$('#otherDelivery').addClass("inputEable");
					}else{
						$("#isSelfLifting1")[0].checked="checked";//是
						//是
						// 车辆信息
						$('#customerCarName').attr("disabled",false);
						$('#customerCarName').removeClass("inputDisable");
						$('#customerCarName').addClass("inputEable");
						$('#otherDelivery').attr("disabled",true);
						// 其他发货方式
						$("#otherDelivery option[value='-1']").attr("selected", true);
						$('#otherDelivery').attr("disabled",true);
						$('#otherDelivery').removeClass("inputEable");
						$('#otherDelivery').addClass("inputDisable");
						// 邮编号码和其他备注
						$('#postNumber').attr("readonly","readonly");
						$('#postNumber').val("")
						$('#postNumber').removeClass("inputEable");
						$('#postNumber').addClass("inputDisable")
						$('#otherRemarks').attr("readonly","readonly");
						$('#otherRemarks').val("")
						$('#otherRemarks').removeClass("inputEable");
						$('#otherRemarks').addClass("inputDisable");
					}
				}else{
					$("#isCarryOff1")[0].checked="checked";//是
					/*$('#isShowTextkehu').hide();
					$('#isShowValkehu').hide();*/
					$("#isSelfLifting2")[0].checked="checked";//否
					// 车辆信息
					$('#customerCarName').attr("disabled",false);
					$('#customerCarName').removeClass("inputDisable");
					$('#customerCarName').addClass("inputEable");
					$('#otherDelivery').attr("disabled",true);
					// 其他发货方式
					$("#otherDelivery option[value='-1']").attr("selected", true);
					$('#otherDelivery').attr("disabled",true);
					$('#otherDelivery').removeClass("inputEable");
					$('#otherDelivery').addClass("inputDisable");
					// 邮编号码和其他备注
					$('#postNumber').attr("readonly","readonly");
					$('#postNumber').val("")
					$('#postNumber').removeClass("inputEable");
					$('#postNumber').addClass("inputDisable")
					$('#otherRemarks').attr("readonly","readonly");
					$('#otherRemarks').val("")
					$('#otherRemarks').removeClass("inputEable");
					$('#otherRemarks').addClass("inputDisable");
				}
				
				//是否需要检测
				if(data.isTesting == 1){
					$("#isTesting2")[0].checked="checked";//否
				}else{
					$("#isTesting1")[0].checked="checked";//是
				}
				
				//车牌号码
				$("#plateNumber").val(data.carName);
				
				//客户名称
				$("#customername").val(data.customerCode);
				
				//出库单
				getAllOutbounds(data.transports);
				$("#outTypeName").val(data.dispatchOutWarehouseId);
				
				//其他发货方式
				$("#otherDelivery option[value='"+data.otherDelivery+"']").attr("selected",true);
				if(data.otherDelivery == '0') {
					$('#postNumber').attr("readonly","");
					$('#postNumber').removeClass("inputDisable");
					$('#postNumber').addClass("inputEable");
					$('#otherRemarks').attr("readonly","readonly");
					$('#otherRemarks').val("");
					$('#otherRemarks').removeClass("inputEable");
					$('#otherRemarks').addClass("inputDisable");
				}else if(data.otherDelivery == '1'){
					$('#postNumber').attr("readonly","readonly");
					$('#postNumber').val("");
					$('#postNumber').removeClass("inputEable");
					$('#postNumber').addClass("inputDisable");
					$('#otherRemarks').attr("readonly","");
					$('#otherRemarks').removeClass("inputDisable");
					$('#otherRemarks').addClass("inputEable");
				}else if(data.otherDelivery == '-1'){
					$('#postNumber').attr("readonly","readonly");
					$('#postNumber').val("");
					$('#postNumber').removeClass("inputEable");
					$('#postNumber').addClass("inputDisable")
					$('#otherRemarks').attr("readonly","readonly");
					$('#otherRemarks').val("");
					$('#otherRemarks').removeClass("inputEable");
					$('#otherRemarks').addClass("inputDisable")
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
function getOrderNumberList(param){
	$.ajax({
		type: "post",
		url: "../../repertory/getOutboundEntitys.action",
		data:param,
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
						if(orderNumber != orderNumberList[i].Id){
							$("#orderNumber").append("<option value='"+orderNumberList[i].Id+"'>"+orderNumberList[i].OrderNumber+"</option>");
						}
						orderNumber = orderNumberList[i].Id;
					}
				}
			}
		}
	});
}

//销售订单明细的获取
function getOrderDetailInfo(param){
	$.ajax({
		type: "post",
		url: "../../repertory/getDiaoBoNoWeighList.action",
		data:{"salesOrderId":param},
		dataType: "json",
		async:false,
		success: function (data) {
			if(data != null && data.length>0){
				for(var i=0;i<data.length;i++){
					if(flag == 1){
						if(outType == 1 && data[i].Type == 1){
							$.ajax({
								type: "post",
								url: "../../repertory/getOrderDetailInfo.action",
								data:{"salesOrderId":param},
								dataType: "json",
								async:false,
								success: function (data) {
									$("#orderDetailedId").empty();
									$("#orderDetailedId").append("<option value=''>请选择</option>");
									if (data && data.length > 0) {
										orderDetailNumberList = data;
										
										for(var i = 0; i < orderDetailNumberList.length; i++){
											if(orderDetailNumberList[i].Transports != null){
												$("#orderDetailedId").append("<option value='"+orderDetailNumberList[i].Id+"'>"+orderDetailNumberList[i].OrderDetailedNumber+"/"+orderDetailNumberList[i].MaterielInfo+"/"+orderDetailNumberList[i].Transports+"</option>");
											}else{
												$("#orderDetailedId").append("<option value='"+orderDetailNumberList[i].Id+"'>"+orderDetailNumberList[i].OrderDetailedNumber+"/"+orderDetailNumberList[i].MaterielInfo+"</option>");
											}
										}
									}
								}
							});
						}else if(data[i].Type == 1 && outType == 0){
							$.ajax({
								type: "post",
								url: "../../repertory/getOrderDetailInfo.action",
								data:{"salesOrderId":param,"id":data[i].OrderDetailedNumber},
								dataType: "json",
								async:false,
								success: function (data) {
									$("#orderDetailedId").empty();
									$("#orderDetailedId").append("<option value=''>请选择</option>");
									if (data && data.length > 0) {
										orderDetailNumberList = data;
									
										for(var i = 0; i < orderDetailNumberList.length; i++){
											if(orderDetailNumberList[i].Transports != null){
												$("#orderDetailedId").append("<option value='"+orderDetailNumberList[i].Id+"'>"+orderDetailNumberList[i].OrderDetailedNumber+"/"+orderDetailNumberList[i].MaterielInfo+"/"+orderDetailNumberList[i].Transports+"</option>");
											}else{
												$("#orderDetailedId").append("<option value='"+orderDetailNumberList[i].Id+"'>"+orderDetailNumberList[i].OrderDetailedNumber+"/"+orderDetailNumberList[i].MaterielInfo+"</option>");
											}
										}
									}
								}
							});
						}
					}else{
						$.ajax({
							type: "post",
							url: "../../repertory/getOrderDetailInfo.action",
							data:{"salesOrderId":param,"id":data[i].OrderDetailedNumber},
							dataType: "json",
							async:false,
							success: function (data) {
								$("#orderDetailedId").empty();
								$("#orderDetailedId").append("<option value=''>请选择</option>");
								if (data && data.length > 0) {
									orderDetailNumberList = data;
									
									for(var i = 0; i < orderDetailNumberList.length; i++){
										if(orderDetailNumberList[i].Transports != null){
											$("#orderDetailedId").append("<option value='"+orderDetailNumberList[i].Id+"'>"+orderDetailNumberList[i].OrderDetailedNumber+"/"+orderDetailNumberList[i].MaterielInfo+"/"+orderDetailNumberList[i].Transports+"</option>");
										}else{
											$("#orderDetailedId").append("<option value='"+orderDetailNumberList[i].Id+"'>"+orderDetailNumberList[i].OrderDetailedNumber+"/"+orderDetailNumberList[i].MaterielInfo+"</option>");
										}
									}
								}
							}
						});
					}
				}
			}else{
				$.ajax({
					type: "post",
					url: "../../repertory/getOrderDetailInfo.action",
					data:{"salesOrderId":param},
					dataType: "json",
					async:false,
					success: function (data) {
						$("#orderDetailedId").empty();
						$("#orderDetailedId").append("<option value=''>请选择</option>");
						if (data && data.length > 0) {
							orderDetailNumberList = data;
							for(var i = 0; i < orderDetailNumberList.length; i++){
								if(orderDetailNumberList[i].Transports != null){
									$("#orderDetailedId").append("<option value='"+orderDetailNumberList[i].Id+"'>"+orderDetailNumberList[i].OrderDetailedNumber+"/"+orderDetailNumberList[i].MaterielInfo+"/"+orderDetailNumberList[i].Transports+"</option>");
								}else{
									$("#orderDetailedId").append("<option value='"+orderDetailNumberList[i].Id+"'>"+orderDetailNumberList[i].OrderDetailedNumber+"/"+orderDetailNumberList[i].MaterielInfo+"</option>");
								}
							}
						}
					}
				});
			}
		}
	});
}

//所有的出库单流水号
//按止运地查询
function getAllOutbounds(param){
	$.ajax({
		type: "post",
		url: "../../repertory/getAllOutbounds.action",
		data:{Transports:param},
		dataType: "json",
		async:false,
		success: function (data) {
			if(data != null){
				if(data.length < 1){
					$("#outTypeName").empty();
					return;
				}
				// 出库单状态名称
				outTypeNameList = data;
				// 清空
				$("#outTypeName").empty();
				$("#outTypeName").append("<option value='0'>请选择</option>");
				if(outTypeNameList != null && outTypeNameList.length > 0){
					
					for(var i = 0; i < outTypeNameList.length; i++){
						if(param == outTypeNameList[i].Transports){
							$("#outTypeName").append("<option value='"+outTypeNameList[i].id+"'>"+outTypeNameList[i].serialId+" / "+outTypeNameList[i].MaterielInfo+" / "+outTypeNameList[i].Transports+" / "+outTypeNameList[i].plateNumber+"</option>");
						}
					}
				}
			}
		}
	});
}

//物料名称和型号
function getMaterielinfoList(param){
	$.ajax({
		type: "post",
		url: "../../repertory/getMaterielinfoList.action",
		data:{"id":param},
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
					var materielName = '';
					//$("#materielName").append("<option value=''>请选择</option>");
					//$("#materielModel").append("<option value=''>请选择</option>");
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

//送货人和送货电话
function getAllCarInfo(param){
	$.ajax({
		type: "post",
		url: "../../repertory/getAllCarInfo.action",
		data:{"plateNumber":param},
		dataType: "json",
		async:false,
		success: function(data){
			if(data != null){
				for(var i=0;i<data.length;i++){
					//送货人
					$("#deliverer").val(data[i].Driver1);
					//送货电话
					$("#deliveryPhone").val(data[i].Telephone1);
				}
			}else{
				//送货人
				$("#deliverer").val("");
				//送货电话
				$("#deliveryPhone").val("");
			}
		}
	});
}

//数据字典查询税率，运输结算情况，其他发货方式，类型
function getAllDatadictionaty(){
	$.ajax({
		type: "post",
		url: "../../repertory/getAllDatadictionaty.action",
		data:{},
		dataType: "json",
		async:false,
		success: function (data) {
			if(data != null){
				if(data != null && data.length > 0){
					// 清空
//					$("#taxRate").empty();
					$("#transportBalance").empty();
					$("#otherDelivery").empty();
//					$("#taxRate").append("<option value=''>0</option>");
					$("#transportBalance").append("<option value=''>请选择</option>");
					$("#otherDelivery").append("<option value='-1'>请选择</option>");
					for(var i = 0; i < data.length; i++){
						if(data[i].type == 'sl'){
//							$("#taxRate").append("<option value='"+data[i].code+"'>"+data[i].content+"</option>");
						}else if(data[i].type == 'ysjsqk'){
							$("#transportBalance").append("<option value='"+data[i].code+"'>"+data[i].content+"</option>");
						}else if(data[i].type == 'qtfhfs'){
							$("#otherDelivery").append("<option value='"+data[i].code+"'>"+data[i].content+"</option>");
						}
					}
				}
			}
		}
	});
}

//保存按钮
function add(){
	// 获取画面数据
	var data = formToJson($("#submitForm"));
	// 销售订单编号
	if(data.orderNumber == ""){
		swal({
 			title: "错误提示",
 			text: "销售订单编号选择!",
 			type: "error",
 			confirmButtonText: '确定',
 			cancelButtonFont: '微软雅黑',
 		});
		return;
	}
	
	//销售订单明细
	if(data.orderDetailedId == ""){
		swal({
 			title: "错误提示",
 			text: "销售订单明细编号选择!",
 			type: "error",
 			confirmButtonText: '确定',
 			cancelButtonFont: '微软雅黑',
 		});
		return;
	}
	
	//客户名称
	if(data.customername == ""){
		swal({
			title: "错误提示",
			text: "客户名称必须填写!",
			type: "error",
			confirmButtonText: '确定',
			cancelButtonFont: '微软雅黑',
		});
		return;
	}
	
	// 客户别称
	if(data.customerAlias == ""){
		swal({
 			title: "错误提示",
 			text: "客户别称必须填写!",
 			type: "error",
 			confirmButtonText: '确定',
 			cancelButtonFont: '微软雅黑',
 		});
		return;
	}
	
	// 车牌号码
	if(data.plateNumber == ""){
		swal({
 			title: "错误提示",
 			text: "未有出库车辆!",
 			type: "error",
 			confirmButtonText: '确定',
 			cancelButtonFont: '微软雅黑',
 		});
		return;
	}
	
	// 送货地址
	if(data.consigneeAddress == ""){
		swal({
 			title: "错误提示",
 			text: "收货地址必须填写!",
 			type: "error",
 			confirmButtonText: '确定',
 			cancelButtonFont: '微软雅黑',
 		});
		return;
	}
	
	// 送货人
/*	if(data.deliverer == ""){
		swal({
 			title: "错误提示",
 			text: "送货人必须填写!",
 			type: "error",
 			confirmButtonText: '确定',
 			cancelButtonFont: '微软雅黑',
 		});
		return;
	}
	
	// 送货人电话
	if(data.deliveryPhone == ""){
		swal({
 			title: "错误提示",
 			text: "送货人电话必须填写!",
 			type: "error",
 			confirmButtonText: '确定',
 			cancelButtonFont: '微软雅黑',
 		});
		return;
	}*/
	
	// 收货人
	if(data.consignee == ""){
		swal({
 			title: "错误提示",
 			text: "收货人必须填写!",
 			type: "error",
 			confirmButtonText: '确定',
 			cancelButtonFont: '微软雅黑',
 		});
		return;
	}
	
	//出库单
	if(data.dispatchOutWarehouseId == "" || data.dispatchOutWarehouseId == 0){
		swal({
 			title: "错误提示",
 			text: "必须选择出库单!",
 			type: "error",
 			confirmButtonText: '确定',
 			cancelButtonFont: '微软雅黑',
 		});
		return;
	}
	
	// 收货人电话
	if(data.consigneePhone == ""){
		swal({
 			title: "错误提示",
 			text: "收货人电话必须填写!!",
 			type: "error",
 			confirmButtonText: '确定',
 			cancelButtonFont: '微软雅黑',
 		});
		return;
	}
	
	//税率
	if(data.taxRate == ""){
		swal({
 			title: "错误提示",
 			text: "税率必须填写!!",
 			type: "error",
 			confirmButtonText: '确定',
 			cancelButtonFont: '微软雅黑',
 		});
		return;
	}
	
	//结算情况
	if($("#transportBalance").val() == ""){
		swal({
 			title: "错误提示",
 			text: "结算情况必须选择!!",
 			type: "error",
 			confirmButtonText: '确定',
 			cancelButtonFont: '微软雅黑',
 		});
		return;
	}else{
		data.transportBalance = $("#transportBalance").val();
	}
	
	//销售数量
	if(data.saleNumber == ""){
		swal({
 			title: "错误提示",
 			text: "销售数量填写!!",
 			type: "error",
 			confirmButtonText: '确定',
 			cancelButtonFont: '微软雅黑',
 		});
		return;
	}
	//销售金额
	if(data.saleMoney == ""){
		swal({
 			title: "错误提示",
 			text: "销售金额必须填写!!",
 			type: "error",
 			confirmButtonText: '确定',
 			cancelButtonFont: '微软雅黑',
 		});
		return;
	}
	//销售单价
	if(data.salePrice == ""){
		swal({
 			title: "错误提示",
 			text: "销售单价必须填写!!",
 			type: "error",
 			confirmButtonText: '确定',
 			cancelButtonFont: '微软雅黑',
 		});
		return;
	}
	
	// 物料id
	var materielName = $("#materielName").val();
	var materielModel = $("#materielModel").val();
	if(materielName != "" && materielModel != ""){
		if(materielinfoList != null){
			for(var i =0; i< materielinfoList.length; i++){
				// 物料名称id和规格型号id
				if(materielName == materielinfoList[i].materielName && materielModel==  materielinfoList[i].materielModel){
					data.materielInfoId = materielinfoList[i].id;
				}
			}
		}
	}else{
		swal({
 			title: "错误提示",
 			text: "物料名称/型号必须选择!",
 			type: "error",
 			confirmButtonText: '确定',
 			cancelButtonFont: '微软雅黑',
 		});
		return;
	}
	
	// 路径
	var url;
	if(id != null && id != ''){
		data.id = id;
		// 修改
		if(flag == 1){
			// 修改
			url = "../../repertory/updateNoWeighOutWarehouse.action";
		}else{
			// 未称重出库单编号
			data.serialID = getContractInfoPrefix("WCZ");
			updateContractInfoPrefix("WCZ");
			// 运输单编号
			data.YSserialID = getContractInfoPrefix("YS");
			
			//添加
			url = "../../repertory/addNoWeighOutWarehouse.action";
		}
		
	}else{
		// 未称重出库单编号
		data.serialID = getContractInfoPrefix("WCZ");
		updateContractInfoPrefix("WCZ");
		// 运输单编号
		data.YSserialID = getContractInfoPrefix("YS");
		// 添加
		url = "../../repertory/addNoWeighOutWarehouse.action";
	}
	
	$.ajax({
		type: "post",
		url: url,
		data:data,
		dataType: "json",
		success: function (data) {
			if(data != null){
				if(data.code == "success"){
					updateContractInfoPrefix("YS");
					swal({
			 			title: "提示",
			 			text: data.message,
			 			type: data.code,
			 			confirmButtonText: '确定',
			 			cancelButtonFont: '微软雅黑',
			 		},function(){
			 			window.parent.$.fancybox.close();
			 		});
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
	    	if(flag == 0){
	    		swal({
		 			title: "错误提示",
		 			text: "车主联系电话格式错误,请重新填写!",
		 			type: "error",
		 			confirmButtonText: '确定',
		 			cancelButtonFont: '微软雅黑',
		 		},function(){
		 		// 清空处理
					$("#carOwnerTelephone").val("");
		 		});
	    	}else if(flag == 1){
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

//销售订单编号的更改
function changeOrderNumber(value){
	if(value == ''){
		//清空操作
		$("#ckTime").val('');
		$("#orderDetailedId").empty();
		$("#dispatchOutWarehouseId").empty();
		$("#materielName").empty();
		$("#materielModel").empty();
		//结算情况
		$("#transportBalance option[value='']").attr("selected", true);
		//税率
//		$("#taxRate option[value='']").attr("selected", true);
		$("#taxRate").val('');
		//销售单价
		$("#salePrice").val('');
		//客户别称
		$("#customerAlias").val('');
		//客户名称
		$("#customername").val('');
		// 送货地址
		$("#consigneeAddress").val('');
		// 收货人
		$("#consignee").val('');
		// 联系电话
		$("#consigneePhone").val('');
		$("#outTypeName").empty();
		$("#saleMoney").val('');
		//送货人
		$("#deliverer").val("");
		//送货电话
		$("#deliveryPhone").val("");
	}
	if(orderNumberList != null){
		$("#outTypeName").empty();
		//送货人
		$("#deliverer").val("");
		//送货电话
		$("#deliveryPhone").val("");
		for(var i=0; i<orderNumberList.length; i++){
			if(orderNumberList[i].Id == value){
				//销售订单明细
				getOrderDetailInfo(orderNumberList[i].Id);
			}
		}
	}
}

//销售订单明细编号的更改
function changeDetailedNumber(value){
	// 结算情况
	if (orderDetailNumberList != null && orderDetailNumberList.length > 0){
		for (var i = 0 ; i < orderDetailNumberList.length ; i++) {
			if (orderDetailNumberList[i].Id == value) {
				getMaterielinfoList(orderDetailNumberList[i].MaterielId);
				//结算情况
				$("#transportBalance option[value='"+orderDetailNumberList[i].TransportBalance+"']").attr("selected", true);
				//税率
//				$("#taxRate option[value='"+orderDetailNumberList[i].TaxRate+"']").attr("selected", true);
				$("#taxRate").val(orderDetailNumberList[i].TaxRate);
				//销售单价
				$("#salePrice").val(orderDetailNumberList[i].SalePrice);
				//客户别称
				$("#customerAlias").val(orderDetailNumberList[i].CustomerAlias);
				//客户名称
				$("#customername").val(orderDetailNumberList[i].CustomerCode);
				// 送货地址
				$("#consigneeAddress").val(orderDetailNumberList[i].Address);
				// 收货人
				$("#consignee").val(orderDetailNumberList[i].Contacts);
				// 联系电话
				$("#consigneePhone").val(orderDetailNumberList[i].Telephone);
				//销售数量
				//$("#saleNumber").val(orderDetailNumberList[i].SaleNumber);
				//销售金额
				//$("#saleMoney").val(orderDetailNumberList[i].SaleMoney);
				getAllOutbounds(orderDetailNumberList[i].Transports);
			}
		}
	}
	if(value == ''){
		$("#ckTime").val('');
		$("#materielName").empty();
		$("#materielModel").empty();
		//结算情况
		$("#transportBalance option[value='']").attr("selected", true);
		//税率
//		$("#taxRate option[value='']").attr("selected", true);
		$("#taxRate").val('');
		//销售单价
		$("#salePrice").val('');
		//客户别称
		$("#customerAlias").val('');
		//客户名称
		$("#customername").val('');
		// 送货地址
		$("#consigneeAddress").val('');
		// 收货人
		$("#consignee").val('');
		// 联系电话
		$("#consigneePhone").val('');
		$("#outTypeName").empty();
		$("#saleMoney").val('');
		//送货人
		$("#deliverer").val("");
		//送货电话
		$("#deliveryPhone").val("");
	}
}

//物料名称更改方法
function changeMaterieName(value){
	if(materielinfoList != null){
		$("#materielModel").empty();
		for(var i=0; i<materielinfoList.length; i++){
			if(materielinfoList[i].materielName == value){
				$("#materielModel").append("<option value='"+materielinfoList[i].materielModel+"'>"+materielinfoList[i].materielModel+"</option>");
			}
		}
	}
}

//车牌号码更改
function changePlateNumber(value){
	if(plateNumberList != null){
		for(var i=0; i<plateNumberList.length; i++){
			param = {
					"plateNumber":value
			};
			if(plateNumberList[i].plateNumber == value){
				// 送货地址
				$("#consigneeAddress").val(plateNumberList[i].Address);
				// 收货人
				$("#consignee").val(plateNumberList[i].Contacts);
				// 联系电话
				$("#consigneePhone").val(plateNumberList[i].Telephone);
				// 车主
				$("#carOwner").val(plateNumberList[i].carOwner);
				// 车主电话
				$("#carOwnerTelephone").val(plateNumberList[i].carOwnerTelephone);
				//客户别称
				$("#customerAlias").val(plateNumberList[i].CustomerAlias);
				//送货人和送货电话
				getAllCarInfo(value);
				//销售订单编号
				getOrderNumberList(param)
				$("#orderNumber").val(plateNumberList[i].Id);
				//销售订单明细
				getOrderDetailInfo(plateNumberList[i].Id);
				//出库时间
				$("#ckTime").val(plateNumberList[i].factoryTime);
			}else if(value == ""){
				getAllPlateNumbers(null);
			}
		}
	}
	if(value.length > 10){
		swal({
			title: "错误提示",
			text: "车牌号码长度过长重新输入!",
			type: "error",
			confirmButtonText: '确定',
			cancelButtonFont: '微软雅黑',
		});
		$("#plateNumber").val("");
	}
}
//客户名称
function changeCusttomerName(value){
	if(value.length > 100){
		swal({
			title: "错误提示",
			text: "客户名称长度过长重新输入!",
			type: "error",
			confirmButtonText: '确定',
			cancelButtonFont: '微软雅黑',
		});
		$("#customername").val("");
	}
}
//车主
function changeCarOwner(value){
	if(value.length > 10){
		swal({
			title: "错误提示",
			text: "车主名称长度过长重新输入!",
			type: "error",
			confirmButtonText: '确定',
			cancelButtonFont: '微软雅黑',
		});
		$("#carOwner").val("");
	}
}
//邮编
function changePostNumber(value){
	if(value.length > 32){
		swal({
			title: "错误提示",
			text: "邮编长度过长重新输入!",
			type: "error",
			confirmButtonText: '确定',
			cancelButtonFont: '微软雅黑',
		});
		$("#postNumber").val("");
	}
}
//客户别称
function changeCustomerAlias(value){
	if(value.length > 50){
		swal({
			title: "错误提示",
			text: "客户别称长度过长重新输入!",
			type: "error",
			confirmButtonText: '确定',
			cancelButtonFont: '微软雅黑',
		});
		$("#customerAlias").val("");
	}
}
//收货人
function changeConsignee(value){
	if(value.length > 10){
		swal({
			title: "错误提示",
			text: "收货人名称长度过长重新输入!",
			type: "error",
			confirmButtonText: '确定',
			cancelButtonFont: '微软雅黑',
		});
		$("#consignee").val("");
	}
}
//收货地址
function changeAddress(value){
	if(value.length > 128){
		swal({
			title: "错误提示",
			text: "收货地址长度过长重新输入!",
			type: "error",
			confirmButtonText: '确定',
			cancelButtonFont: '微软雅黑',
		});
		$("#consigneeAddress").val("");
	}
}
//送货人
function changeDeliverer(value){
	if(value.length > 10){
		swal({
			title: "错误提示",
			text: "送货人名称长度过长重新输入!",
			type: "error",
			confirmButtonText: '确定',
			cancelButtonFont: '微软雅黑',
		});
		$("#deliverer").val("");
	}
}
//备注
function changeRemarks(value){
	if(value.length > 200){
		swal({
			title: "错误提示",
			text: "备注长度过长重新输入!",
			type: "error",
			confirmButtonText: '确定',
			cancelButtonFont: '微软雅黑',
		});
		$("#remarks").val("");
	}
}

//计算金额
function count(data){
	// 销售单价
	SalePrice = $("#salePrice").val();
	// 数量 
	SaleNumber = $("#saleNumber").val();
	// 税率
//	TaxRate = $('#taxRate option:selected').text();
	//TaxRate = $("#taxRate").val();
	// 销售金额
	//$("#saleMoney").val((SalePrice*(1+TaxRate/100)*SaleNumber).toFixed(2));
	$("#saleMoney").val((SalePrice*SaleNumber).toFixed(2));
}

//隐藏车辆信息
function changeIsCarryOff(flag){
	if (flag == '0') {
		// 是否客戶自提
		$('input[type=radio][name=isSelfLifting]').attr("disabled",true);
		// 车辆信息
		$('#customerCarName').attr("disabled",false);
		$('#customerCarName').removeClass("inputDisable");
		$('#customerCarName').addClass("inputEable");
		// 其他发货方式
		$("#otherDelivery option[value='-1']").attr("selected", true);
		$('#otherDelivery').attr("disabled",true);
		$('#otherDelivery').removeClass("inputEable");
		$('#otherDelivery').addClass("inputDisable");
		// 邮编号码和其他备注
		$('#postNumber').attr("readonly","readonly");
		$('#postNumber').val("");
		$('#postNumber').removeClass("inputEable");
		$('#postNumber').addClass("inputDisable")
		$('#otherRemarks').attr("readonly","readonly");
		$('#otherRemarks').val("");
		$('#otherRemarks').removeClass("inputEable");
		$('#otherRemarks').addClass("inputDisable");
		$("input[name='isCarryOff']:checked").val("0");
		/*$('#isShowTextkehu').hide();
		$('#isShowValkehu').hide();*/
	} else if (flag == '1') {//不随车带走
		// 是否客戶自提
		$('input[type=radio][name=isSelfLifting]').attr("disabled",false);
		$('#isShowTextkehu').show();
		$('#isShowValkehu').show();
		var val=$('input:radio[name="isSelfLifting"]:checked').val();
	      if(val==1){
	    	  $('#customerCarName').attr("disabled",true);
	  		$('#customerCarName').val("");
	  		$('#customerCarName').removeClass("inputEable");
	  		$('#customerCarName').addClass("inputDisable");
	  		$('#otherDelivery').attr("disabled",false);
	  		$('#otherDelivery').removeClass("inputDisable");
	  		$('#otherDelivery').addClass("inputEable");
	  		$("input[name='isSelfLifting']:checked").val("1");
	      }else{
	    		$('#customerCarName').attr("disabled",false);
	    		$('#customerCarName').removeClass("inputDisable");
	    		$('#customerCarName').addClass("inputEable");
	    		$('#otherDelivery').attr("disabled",true);
	    		// 其他发货方式
	    		$("#otherDelivery option[value='-1']").attr("selected", true);
	    		$('#otherDelivery').attr("disabled",true);
	    		$('#otherDelivery').removeClass("inputEable");
	    		$('#otherDelivery').addClass("inputDisable");
	    		// 邮编号码和其他备注
	    		$('#postNumber').attr("readonly","readonly");
	    		$('#postNumber').val("");
	    		$('#postNumber').removeClass("inputEable");
	    		$('#postNumber').addClass("inputDisable")
	    		$('#otherRemarks').attr("readonly","readonly");
	    		$('#otherRemarks').val("");
	    		$('#otherRemarks').removeClass("inputEable");
	    		$('#otherRemarks').addClass("inputDisable");
	    		$("input[name='isSelfLifting']:checked").val("0");
	      }
		// 车辆信息
		$("input[name='isCarryOff']:checked").val("1");
	}
}
//隐藏,显示客户自提信息
function changeIsSelfLifting(flag){
	if(flag == '0'){
		//是
		// 车辆信息
		$('#customerCarName').attr("disabled",false);
		$('#customerCarName').removeClass("inputDisable");
		$('#customerCarName').addClass("inputEable");
		$('#otherDelivery').attr("disabled",true);
		// 其他发货方式
		$("#otherDelivery option[value='-1']").attr("selected", true);
		$('#otherDelivery').attr("disabled",true);
		$('#otherDelivery').removeClass("inputEable");
		$('#otherDelivery').addClass("inputDisable");
		// 邮编号码和其他备注
		$('#postNumber').attr("readonly","readonly");
		$('#postNumber').val("");
		$('#postNumber').removeClass("inputEable");
		$('#postNumber').addClass("inputDisable")
		$('#otherRemarks').attr("readonly","readonly");
		$('#otherRemarks').val("");
		$('#otherRemarks').removeClass("inputEable");
		$('#otherRemarks').addClass("inputDisable");
		$("input[name='isSelfLifting']:checked").val("0");
	}else if(flag == '1'){
		// 车辆信息
		$('#customerCarName').attr("disabled",true);
		$('#customerCarName').val("");
		$('#customerCarName').removeClass("inputEable");
		$('#customerCarName').addClass("inputDisable");
		$('#otherDelivery').attr("disabled",false);
		$('#otherDelivery').removeClass("inputDisable");
		$('#otherDelivery').addClass("inputEable");
		$("input[name='isSelfLifting']:checked").val("1");
	}
}
//出库单事件
function changeOut(value){
	if(value == 0){
		$("#ckTime").val('');
		//送货人
		$("#deliverer").val("");
		//送货电话
		$("#deliveryPhone").val("");
	}
	if(outTypeNameList != null){
		for(var i=0; i<outTypeNameList.length; i++){
			if(outTypeNameList[i].id == value){
				$("#ckTime").val(outTypeNameList[i].factoryTime);
				//车牌号码
				$("#plateNumber").val(outTypeNameList[i].plateNumber);
				//送货人和送货电话
				getAllCarInfo(outTypeNameList[i].plateNumber);
			}
		}
	}
}