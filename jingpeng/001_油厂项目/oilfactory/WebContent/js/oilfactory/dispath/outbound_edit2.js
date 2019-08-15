var id = "";
//销售订单编号
var orderNumberList;
//调拨订单编号
var diaoOrderNumberList;
//销售订单详情编号
var orderDetailNumberList;
//物料名称和型号
var materielinfoList;
//车牌号码
var plateNumberList;
//赋值和修改的标识
var flag;

var flags;
//不可选中的车牌
var carsInUseList;
//销售订单信息
var orderData;
//物料id
var materielId;
//销售订单明细id
var orderDetailId;
//调拨收货人信息
var diaoConsignee;
//净重
var suttle;
// 用户的权限
var rolecode;
$(function() {
	// 结算方式
	$("#transportBalance").html(getDataDictionary('ysjsqk'));
//	$("#diaoTransportBalance").html(getDataDictionary('ysjsqk'));
	var a = getDataDictionary('ysjsqk').substring(0, 82);
	var b = getDataDictionary('ysjsqk').substring(113, getDataDictionary('ysjsqk').length);
	$("#diaoTransportBalance").html(a + b);
//	console.log(getDataDictionary('ysjsqk'));
	// 隐藏调拨相关表单项
	$(".diaoboList").hide();
	// 绑定单选框事件
	$(':radio[name="isDiaobo"]').click(function(e){
	    if (e.target.value == 1) {
	    	$(".diaoboList").hide();
	    } else {
	    	$(".diaoboList").show();
	    }
	});
	// 不可选中的车牌
	carsInUseList = queryCarInUse();
	// 销售订单编号
	getOrderNumberList();
	// 物料名称型号
//	getMaterielinfoList();
	// 所有车辆号码
	getAllPlateNumbers();
	
	//称重类型
	cartWeighTypeList = getDataDictionary('weighingtype');
	$("#cartWeighType").append(cartWeighTypeList.substring(49, cartWeighTypeList.length));

	// 获取上页面传过来的值
	id = getUrlParam("id");
	flag = getUrlParam("flag");
	rolecode = getUrlParam("rolecode");
	// 新增
	if (flag != 1){
		$("#serialId").val(getContractInfoPrefix("CK"));
	}
	if(id != null && id != ""){
		
		// 修改
//		if(flag == 1){
//			$("#serialIDFlag").show();
//		}
		
		// 画面数据初始化
		$.ajax({
			type: "post",
			url: "../../outbound/getOutboundInfo.action",
			data:{"id":id},
			dataType: "json",
			async:false,
			success: function (data) {
				getMaterielinfoList(data.salesOrderId);
				changeOrderNumber(data.salesOrderId);
				changeDetailedNumber(data.orderDetailedId);
//				// 是否调拨
//				if (data.outType == 1) { // 调拨
//					changeDiaoOrderNumber(data.diaoOrderNumber);
//					$("input[name=isDiaobo]:eq(0)").attr("checked",'checked');
//					$(".diaoboList").show();
//					$("#allotClient").val(data.diaoBoClient);
//					$("#allotCustomerAlias").val(data.diaoBoCustomerAlias);
//					$("#diaoTransportBalance option[value='"+data.transportBalance+"']").attr("selected", true);
//					$("#diaoOrderNumber option[value='"+data.diaoOrderNumber+"']").attr("selected", true);
//					$("#diaoOrderDetailedId option[value='"+data.diaoOrderDetailedNumber+"']").attr("selected", true);
//					$("#diaoRemarks").val(data.diaoBoRemarks);
//				} else {
//					$("input[name=isDiaobo]:eq(1)").attr("checked",'checked');
//				}
				
				$("#serialId").val(data.serialId);
				SetFromValues($("#submitForm"), data);
				$("#client").val(data.customerCode);
				if (flag != 1){
					$("#serialId").val(getContractInfoPrefix("CK"));
				}
				// 净重
				if (flag != 1){
					suttle = data.suttle;
				}
				// 封签号
				if(data.facingSlipNum){
					var fileName = data.facingSlipNum;
					$("#fileName").val(fileName);
					$("#facingSlipNum1").show();
					$("#facingSlipNum2").show();
					$("#file").html(fileName.substring(0,fileName.indexOf('_')) + fileName.substring(fileName.indexOf('.'), fileName.length));
					$("#files").val(fileName);
				} else {
					$("#facingSlipNum1").hide();
					$("#facingSlipNum2").hide();
				}
				
				// 备注
				$("#remark").val(data.remarks);
				
				// 销售订单编号
				$("#orderNumber option[value='"+data.salesOrderId+"']").attr("selected", true);
				$("#orderNumber").attr("readonly","true");
				$("#orderDetailedId option[value='"+data.orderDetailedId+"']").attr("selected", true);
			
				// 物料名称和型号
				$("#materielName option[value='"+data.materielName+"']").attr("selected", true);
				$("#materielModel option[value='"+data.materielModel+"']").attr("selected", true);
				
				// 是否大车称重
				$("#cartWeighType option[value='"+data.cartWeighType +"']").attr("selected", true);
				
				// 用户的权限
				if(rolecode == 'false'){
					$("#submitbutton").hide();
					$("input").attr("readonly",true);
					$("select").attr("disabled",true);
					$("textarea").attr("disabled",true);
				}else{
					$("#submitbutton").show();
				}
			}
		});
		
		
	}
});

// form表单赋值方法
function SetFromValues(el, data)
{
    for (var p in data)
    {
        el.find(":input[name='" + p + "']").val(data[p]);
    } 
}

// 销售订单编号
function getOrderNumberList(){
	$.ajax({
		type: "post",
		url: "../../outbound/getOrderNumberList.action",
		data:{
			flag:"true",
			query: "Y"
		},
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
//					$("#diaoOrderNumber").empty();
//					$("#diaoOrderNumber").append("<option value=''>请选择</option>");
					var orderNumber = '';
					for(var i = 0; i < orderNumberList.length; i++){
						if(orderNumber != orderNumberList[i].id){
							$("#orderNumber").append("<option value='"+orderNumberList[i].id+"'>"+orderNumberList[i].orderNumbers+"</option>");
						}
						orderNumber = orderNumberList[i].id;
//						$("#diaoOrderNumber").append("<option value='"+orderNumberList[i].id+"'>"+orderNumberList[i].orderNumbers+"</option>");
					}
				}
			}
		}
	});
}

//物料名称和型号
function getMaterielinfoList(salesOrderId){
	$.ajax({
		type: "post",
		url: "../../outbound/getMaterielinfoList.action",
		data:{"salesOrderId":salesOrderId},
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
//					$("#materielName").append("<option value=''>请选择</option>");
//					$("#materielModel").append("<option value=''>请选择</option>");
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
//	changeMaterieModelName();
}

//调拨物料名称和型号
function getDiaoMaterielinfoList(salesOrderId){
	$.ajax({
		type: "post",
		url: "../../outbound/getMaterielinfoList.action",
		data:{"salesOrderId":salesOrderId},
		dataType: "json",
		async:false,
		success: function (data) {
			if(data != null){
				// 物料名称和型号
				materielinfoList = data;
				if(materielinfoList != null && materielinfoList.length > 0){
					// 清空
					$("#diaoMaterielName").empty();
					$("#diaoMaterielModel").empty();
					var materielName = 0;
					$("#diaoMaterielName").append("<option value=''>请选择</option>");
					$("#diaoMaterielModel").append("<option value=''>请选择</option>");
					for(var i = 0; i < materielinfoList.length; i++){
						if(materielinfoList[i].materielName != materielName){
							$("#diaoMaterielName").append("<option value='"+materielinfoList[i].materielName+"'>"+materielinfoList[i].materielName+"</option>");
						}
						
						materielName = materielinfoList[i].materielName;
						$("#diaoMaterielModel").append("<option value='"+materielinfoList[i].materielModel+"'>"+materielinfoList[i].materielModel+"</option>");
					}
				}
			}
		}
	});
}

// 所有车牌号码
function getAllPlateNumbers(){
	$.ajax({
		type: "post",
		url: "../../outbound/getAllPlateNumbers.action",
		data:{},
		dataType: "json",
		async:false,
		success: function (data) {
			if(data != null){
				// 车牌号码
				plateNumberList = data;
				if(plateNumberList != null && plateNumberList.length > 0){
					// 清空
					$("#diaoPlateNumberList").empty();
					$("#plateNumberList").empty();
					for(var i = 0; i < plateNumberList.length; i++){
						var carFlag = false;
						for (var j = 0 ; j < carsInUseList.length ; j++) {
							if (carsInUseList[j].PlateNumber == plateNumberList[i].plateNumber) {
								carFlag = true;
								break;
							}
						}
//						if (!carFlag) {
							$("#diaoPlateNumberList").append("<option value='"+plateNumberList[i].plateNumber+"'>"+plateNumberList[i].plateNumber+"</option>");
							$("#plateNumberList").append("<option value='"+plateNumberList[i].plateNumber+"'>"+plateNumberList[i].plateNumber+"</option>");
//						}
					}
				}
			}
		}
	});
}

// 保存按钮
function add(){
	// 获取画面数据
	var data = formToJson($("#submitForm"));
  data.transportBalance = $("#transportBalance").val();
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

	// 销售合同明细编号id
	if(data.orderDetailedId == ""){
		swal({
			title: "错误提示",
			text: "销售合同明细编号必须选择!",
			type: "error",
			confirmButtonText: '确定',
			cancelButtonFont: '微软雅黑',
		});
		return;
	}
	
	// 车牌号不能重复
//	for (var i = 0 ; i < carsInUseList.length ; i++) {
//		if(data.plateNumber == carsInUseList[i].PlateNumber && flag == null){
//			swal({
//				title: "错误提示",
//				text: "当前车牌号不可用，请重新选择!",
//				type: "error",
//				confirmButtonText: '确定',
//				cancelButtonFont: '微软雅黑',
//			},function(){
//		 		// 清空处理
//				$("#plateNumber").val("");
//	 		});
//			return;
//		}
//	}
	
	// 销售公司名称
	if(data.saleCompanyName == ""){
		swal({
 			title: "错误提示",
 			text: "销售公司名称必须填写!",
 			type: "error",
 			confirmButtonText: '确定',
 			cancelButtonFont: '微软雅黑',
 		});
		return;
	}
	
	// 客户名称
	if(data.client == ""){
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
 			text: "车牌号码必须填写!",
 			type: "error",
 			confirmButtonText: '确定',
 			cancelButtonFont: '微软雅黑',
 		});
		return;
	}
	
	// 车主
//	if(data.carOwner == ""){
//		swal({
// 			title: "错误提示",
// 			text: "车主必须填写!",
// 			type: "error",
// 			confirmButtonText: '确定',
// 			cancelButtonFont: '微软雅黑',
// 		});
//		return;
//	}
	
	
	// 车主电话
//	if(data.carOwnerTelephone == ""){
//		swal({
// 			title: "错误提示",
// 			text: "车主电话必须填写!",
// 			type: "error",
// 			confirmButtonText: '确定',
// 			cancelButtonFont: '微软雅黑',
// 		});
//		return;
//	}
	
	// 送货地址
	if(data.address == ""){
		swal({
 			title: "错误提示",
 			text: "送货地址必须填写!",
 			type: "error",
 			confirmButtonText: '确定',
 			cancelButtonFont: '微软雅黑',
 		});
		return;
	}
	
//	// 送货人
//	if(data.deliveryMan == ""){
//		swal({
// 			title: "错误提示",
// 			text: "送货人必须填写!",
// 			type: "error",
// 			confirmButtonText: '确定',
// 			cancelButtonFont: '微软雅黑',
// 		});
//		return;
//	}
//	
//	// 送货人电话
//	if(data.deliveryManPhone == ""){
//		swal({
// 			title: "错误提示",
// 			text: "送货人电话必须填写!",
// 			type: "error",
// 			confirmButtonText: '确定',
// 			cancelButtonFont: '微软雅黑',
// 		});
//		return;
//	}
	
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
	
	// 磅单打印物料名称
	if(data.lbsMaterialName == ""){
		swal({
 			title: "错误提示",
 			text: "磅单打印物料名称必须填写!!",
 			type: "error",
 			confirmButtonText: '确定',
 			cancelButtonFont: '微软雅黑',
 		});
		return;
	}
	
	// 磅单输出名称
//	if(data.lbsOutputName == ""){
//		swal({
// 			title: "错误提示",
// 			text: "磅单输出名称必须填写!!",
// 			type: "error",
// 			confirmButtonText: '确定',
// 			cancelButtonFont: '微软雅黑',
// 		});
//		return;
//	}
	
												/*调拨相关代码++++++++开始*/
	var isDiaobo = $(':radio[name="isDiaobo"]:checked').val();
	if (isDiaobo == 0) {
		// 调拨销售订单
		if(orderData == null){
			swal({
				title: "错误提示",
				text: "调拨销售订单不能为空!",
				type: "error",
				confirmButtonText: '确定',
				cancelButtonFont: '微软雅黑',
			});
			return;
		}
		
		// 调拨销售订单明细
		if(data.diaoOrderDetailedId == ""){
			swal({
				title: "错误提示",
				text: "调拨销售订单明细不能为空!",
				type: "error",
				confirmButtonText: '确定',
				cancelButtonFont: '微软雅黑',
			});
			return;
		}
		
		// 调拨地址
		if(data.diaoAddress.trim() == ""){
			swal({
				title: "错误提示",
				text: "调拨收货地址不能为空!",
				type: "error",
				confirmButtonText: '确定',
				cancelButtonFont: '微软雅黑',
			});
			return;
		}

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
		
		// 调拨重量
//		var allotWeight = data.allotWeight == "" ? 0 : parseInt(data.allotWeight);
//		// 出库重量
//		var suttle = data.suttle == "" ? 0 : parseInt(data.suttle);
//		// 出库重量必须大于等于调拨重量
//		if (allotWeight > suttle) {
//			swal({
//				title: "错误提示",
//				text: "出库重量必须大于等于调拨重量！",
//				type: "error",
//				confirmButtonText: '确定',
//				cancelButtonFont: '微软雅黑',
//			});
//			return;
//		}
		// 收货人
		if(data.diaoConsignee == ""){
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
		if(data.diaoConsigneePhone == ""){
			swal({
				title: "错误提示",
				text: "收货电话不能为空!",
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
		
		// 物料id
//	data.materielInfoId = info.productID;	
		// 出库单id
//	data.id = id;
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
		// 送货地址
//		data.address = orderData.address;
		// 未称重出库单编号
		data.wczSerialID = getContractInfoPrefix("WCZ");
		updateContractInfoPrefix("WCZ");
//		data.wczSerialID = getContractInfoPrefix("CK");
	}
											/*调拨相关代码++++++++结束*/
	// 物料id
	var materielName = $("#materielName").val();
	data.consigneeAddress = $("#transports").val();
	var materielModel = $("#materielModel").val();
	if(materielName != "" && materielModel != ""){
		if(materielinfoList != null){
			for(var i =0; i< materielinfoList.length; i++){
				// 物料名称id和规格型号id
				if(materielName ==materielinfoList[i].materielName && materielModel==  materielinfoList[i].materielModel){
					data.productID = materielinfoList[i].id;
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
	
	// 净重
	data.suttle = suttle == null ? "" : suttle;
	if (data.suttle) {
		// 调拨重量不能超过净重
		if(data.suttle < data.allotWeight){
			swal({
				title: "错误提示",
				text: "调拨重量不能超过"+data.suttle+"千克!",
				type: "error",
				confirmButtonText: '确定',
				cancelButtonFont: '微软雅黑',
			});
			return;
		}
	}
	
	data.remarks = $("#remark").val();
	// 路径
	var url;
	
	if(id != null && id != ''){
		data.id = id;
		// 修改
		if(flag == 1){
			// 运输单编号
			data.YSserialID = getContractInfoPrefix("YS");
			// 修改
			url = "../../outbound/updateExportMeasure.action";
		}else{
			// 整车调拨
			if(flag == 2){
				data.flag = 2
			}else{
				data.flag = 0
			}
			
			// 出库单编号
			data.serialID = getContractInfoPrefix("CK");
			updateContractInfoPrefix("CK");
			// 添加
			url = "../../outbound/addExportMeasure.action";
		}
		
	}else{
		data.flag = 0
		// 出库单编号
		data.serialID = getContractInfoPrefix("CK");
		updateContractInfoPrefix("CK");
		// 运输单编号
		data.YSserialID = getContractInfoPrefix("YS");
		// 添加
		url = "../../outbound/addExportMeasure.action";
	}
	
	// 当已经上传过封签号时，再次上传。先删除历史文件，再执行上传操作，否做直接进行操作
	if ($("#files").val() != null && $("#files").val().indexOf('.') != -1 && $("#myBlogImage").val() != "") {
		layer.confirm("该操作会覆盖历史文件！" , {
			btn: ['确定','取消'] //按钮
		}, function(){
			$.ajax({
				url : "../../file/fileDelete.action",
				async : false,
				dataType:'json',
				data : {
					"filename" : $("#files").val()
				},
				type : "post",
				success : function(_info) {
					layer.msg('覆盖成功');
					$.ajaxFileUpload({  
						url: '../../file/fileUpload.action',  
						secureuri:false,                           //是否启用安全提交,默认为false   
						fileElementId:'myBlogImage',               //文件选择框的id属性  
						dataType:'json',                           //服务器返回的格式,选择json或者xml貌似有问题
						success:function(fileData){
							if (fileData.fileName != null) {
								// 文件名
								$("#fileName").val(fileData.fileName);
								data.facingSlipNum = fileData.fileName;
								
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
							} else {
								swal({
									title: "错误提示",
									text: "文件上传失败!",
									type: "error",
									confirmButtonText: '确定',
									cancelButtonFont: '微软雅黑',
								});
								return;
							}
						}
					});
				}
			});
		}, function(){
			// 点击取消时触发，清空选择的文件
			var file = document.getElementById('myBlogImage');
			file.value = ''; //虽然file的value不能设为有字符的值，但是可以设置为空值
			//或者
			file.outerHTML = file.outerHTML;
		});
	} else {
		// 判断是否上传了封签号。若没上传则执行普通保存，否则先上传再保存
		if ($("#myBlogImage")[0].files.length > 0 && $("#myBlogImage")[0].files[0].name != "") {
			// 文件上传
			$.ajaxFileUpload({  
				url: '../../file/fileUpload.action',  
				secureuri:false,                           //是否启用安全提交,默认为false   
				fileElementId:'myBlogImage',               //文件选择框的id属性  
				dataType:'json',                           //服务器返回的格式,选择json或者xml貌似有问题
				success:function(fileData){
					if (fileData.fileName != null) {
						// 文件名
						$("#fileName").val(fileData.fileName);
						data.facingSlipNum = fileData.fileName;
						
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
					} else {
						swal({
							title: "错误提示",
							text: "文件上传失败!",
							type: "error",
							confirmButtonText: '确定',
							cancelButtonFont: '微软雅黑',
						});
						return;
					}
				}
			});
		} else {
			data.facingSlipNum = $("#files").val();
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
					$("#deliveryManPhone").val("");
		 		});
	    	}else if(flag == 3){
	    		swal({
		 			title: "错误提示",
		 			text: "调拨收货电话格式错误,请重新填写!",
		 			type: "error",
		 			confirmButtonText: '确定',
		 			cancelButtonFont: '微软雅黑',
		 		},function(){
		 		// 清空处理
					$("#diaoConsigneePhone").val("");
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

// 物料名称更改方法
function changeMaterieName(value){
	if(materielinfoList != null){
		$("#materielModel").empty();
		for(var i=0; i<materielinfoList.length; i++){
			if(materielinfoList[i].materielName== value){
				$("#materielModel").append("<option value='"+materielinfoList[i].materielModel+"'>"+materielinfoList[i].materielModel+"</option>");
//				changeMaterieModelName(materielinfoList[i].materielModelId);
			}
		}
	}

}

// 调拨物料名称更改方法
function changeDiaoMaterieName(value){
	if(materielinfoList != null){
		$("#diaoMaterielModel").empty();
		for(var i=0; i<materielinfoList.length; i++){
			if(materielinfoList[i].materielName == value){
				$("#diaoMaterielModel").append("<option value='"+materielinfoList[i].materielModel+"'>"+materielinfoList[i].materielModel+"</option>");
			}
		}
	}
	
}

// 销售订单编号的更改
function changeOrderNumber(value){
	// 给订单明细赋值
	$.ajax({
		type: "post",
		async:false,
		url: "../../outbound/getOrderDetailInfo.action",
		data:{
			"id":value
		},
		dataType: "json",
		success: function (data) {
			if (data && data.length > 0) {
				orderDetailNumberList = data;
				$("#orderDetailedId").empty();
				$("#orderDetailedId").append("<option value=''>请选择</option>");
				for(var i = 0; i < data.length; i++){
					$("#orderDetailedId").append("<option value='"+data[i].Id+"'>"+data[i].OrderDetailedNumber+" / "+data[i].MaterielInfo+" / "+data[i].Transports+"</option>");
				}
			}
		}
	});
	// 给其他表单项赋值
	if (value == "") {
		changeDetailedNumber("");
		$("#orderDetailedId option[value='']").attr("selected", true);
		$("#materielName").empty();
		$("#materielModel").empty();
		$("#orderDetailedId").empty();
		// 销售公司名称
		$("#saleCompanyName").val("");
		// 客户名称
		$("#client").val("");
		// 客户别称
		$("#customerAlias").val("");
		// 送货地址
		$("#address").val("");
		// 收货人
		$("#consignee").val("");
		// 联系电话
		$("#consigneePhone").val("");
		// 合同编号
		$("#contractNumber").val("");
	} else {
		if(orderNumberList != null){
			for(var i=0; i<orderNumberList.length; i++){
				if(orderNumberList[i].id == value){
					// 销售公司名称
					$("#saleCompanyName").val(orderNumberList[i].salesCompanyName);
					// 客户名称
					$("#client").val(orderNumberList[i].customerCode);
					// 客户别称
					$("#customerAlias").val(orderNumberList[i].customer);
					// 送货地址
					$("#address").val(orderNumberList[i].address);
					// 收货人
					$("#consignee").val(orderNumberList[i].contacts);
					// 联系电话
					$("#consigneePhone").val(orderNumberList[i].telephone);
					// 合同编号
					$("#contractNumber").val(orderNumberList[i].contractNumber);
					break;
				}
			}
		}
	}
}

// 销售订单明细编号的更改
function changeDetailedNumber(value){
	orderDetailId = value;
	// 清空数据
	$("input[name=isDiaobo]:eq(1)").attr("checked",'checked');
	$(".diaoboList").hide();
	$("#diaoOrderDetailedId").empty();
	$("#diaoContractNumber").val("");
	$("#diaoClient").val("");
	$("#diaoCustomerAlias").val("");
	$("#diaoConsignee").val("");
	$("#diaoConsigneeList").val("");
	$("#diaoConsigneePhone").val("");
	$("#diaoConsigneePhoneList").html("");
	$("#diaoAddress").val("");
	$("#allotWeight").val("");
	$("#diaoRemarks").val("");
	$("#diaoTransportBalance option[value='']").attr("selected", true);
	$("#transportBalance option[value='']").attr("selected", true);
	getMaterielinfoList(value);
	if (value == "") {
		$("#materielName").empty();
		$("#materielModel").empty();
		$("#distance").val("");
		$("#transports").val("");
		$("#lbsMaterialName").val("");
		$("#transportBalance option[value='']").attr("selected", true);
		$("#diaoOrderNumber").empty();
		$("#diaoOrderDetailedId").empty();
	}
	
	
	
	// 结算情况
	if (orderDetailNumberList != null && orderDetailNumberList.length > 0){
		for (var i = 0 ; i < orderDetailNumberList.length ; i++) {
			if (orderDetailNumberList[i].Id == value) {
				$("#transportBalance option[value='"+orderDetailNumberList[i].TransportBalance+"']").attr("selected", true);
				$("#distance").val(orderDetailNumberList[i].Distance);
				$("#transports").val(orderDetailNumberList[i].Transports);
				$("#lbsMaterialName").val(orderDetailNumberList[i].ListModel);
				// 客户自提不能调拨
				if (orderDetailNumberList[i].TransportBalance == 1) {
					var input = $("#diaobo").find("input:radio");
						input.attr("disabled","disabled");
				} else {
					var input = $("#diaobo").find("input:radio");
					input.removeAttr("disabled");
					// 绑定单选框事件
					$(':radio[name="isDiaobo"]').click(function(e){
					    if (e.target.value == 1) {
					    	$(".diaoboList").hide();
					    } else {
					    	$(".diaoboList").show();
					    }
					});
				}
			}
		}
	}
	
	$.ajax({
		type: "post",
		url: "../../outbound/getDiaoMaterielId.action",
		data: {
			orderId: value
		},
		async: false,
		dataType: "json",
		success: function(data) {
			if (data != null && data.length > 0) {
				materielId = data[0].MaterielId
				getDiaoOrderNumber(data[0].MaterielId, orderDetailId);
			}
		}
	});
}

// 获取调拨销售订单编号
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
				$("#diaoOrderNumber").empty();
				$("#diaoOrderNumber").append("<option value=''>请选择</option>");
				for(var i = 0; i < data.length; i++){
					$("#diaoOrderNumber").append("<option value='"+data[i].Id+"'>"+data[i].OrderNumber+"</option>");
				}
			}
		}
	});
}

// 修改调拨销售订单编号
function changeDiaoOrderNumber (value) {
	$("#diaoOrderDetailedId").empty();
	$("#diaoOrderDetailedId").append("<option value=''>请选择</option>");
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
					$("#diaoOrderDetailedId").append("<option value='"+data[i].Id+"'>"+data[i].OrderDetailedNumber+"         "+data[i].MaterielInfo+"         "+data[i].Transports+"</option>");
				}
			}
		}
	});
	if(diaoOrderNumberList != null){
		for(var i=0; i<diaoOrderNumberList.length; i++){
			if(diaoOrderNumberList[i].Id == value){
				orderData = diaoOrderNumberList[i];
				// 客户名称
				$("#diaoClient").val(diaoOrderNumberList[i].customerName);
				// 客户别称
				$("#diaoCustomerAlias").val(diaoOrderNumberList[i].customer);
				// 合同编号
				$("#diaoContractNumber").val(diaoOrderNumberList[i].contractNumber);
				break;
			}
		}
	}
}

// 获取调拨销售订单明细编号
function changeDiaoOrderDetail (value) {
	// 调拨收货地址
	$("#diaoAddress").val("");
	$("#diaoConsignee").val("");
	$("#diaoConsigneePhone").val("");
	if(orderDetailNumberList != null){
		for(var i=0; i<orderDetailNumberList.length; i++){
			if(orderDetailNumberList[i].Id == value){
				// 调拨收货地址
				$("#diaoAddress").val(orderDetailNumberList[i].Transports);
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
							$("#diaoConsignee").val(data[0].Consignee);
							$("#diaoConsigneePhone").val(data[0].ConsigneePhone);
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
			$("#diaoConsigneePhone").val("");
			// 如果收货人为空时。初始化收货人电话的datalist
			if ($("#diaoConsignee").val() == "") {
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
			$("#diaoConsignee").val("");
			// 校验格式
			changeTelephone(value,3);
			for (var i = 0 ; i < diaoConsignee.length ; i++) {
				if (diaoConsignee[i].ConsigneePhone == value) {
					$("#diaoConsignee").val(diaoConsignee[i].Consignee);
					break;
				}
			}
		}
	}
}

//车牌号码更改
function changePlateNumber(value){
	var result = false;
	if(plateNumberList != null){
		if (value.length == 7){
		  var express = /^[京津沪渝冀豫云辽黑湘皖鲁新苏浙赣鄂桂甘晋蒙陕吉闽贵粤青藏川宁琼使领A-Z]{1}[A-Z]{1}[A-Z0-9]{4}[A-Z0-9挂学警港澳]{1}$/;
		  result = express.test(value);
		}
		if (result) {
			for(var i=0; i<plateNumberList.length; i++){
				if(plateNumberList[i].plateNumber == value){
					// 车主
					$("#deliveryMan").val(plateNumberList[i].driver1);
					// 车主电话
					$("#deliveryManPhone").val(plateNumberList[i].telephone1);
					break;
				}
			}
		} else {
			swal({
	 			title: "错误提示",
	 			text: "车牌号码格式不正确！",
	 			type: "error",
	 			confirmButtonText: '确定',
	 			cancelButtonFont: '微软雅黑',
	 		},function(){
	 		// 清空处理
				$("#plateNumber").val("");
	 		});
		}
	}
}

// 调拨车牌号码更改
function changeDiaoPlateNumber(value){
	var result = false;
	if(plateNumberList != null){
		if (value.length == 7){
			var express = /^[京津沪渝冀豫云辽黑湘皖鲁新苏浙赣鄂桂甘晋蒙陕吉闽贵粤青藏川宁琼使领A-Z]{1}[A-Z]{1}[A-Z0-9]{4}[A-Z0-9挂学警港澳]{1}$/;
			result = express.test(value);
		}
		if (result) {
			for(var i=0; i<plateNumberList.length; i++){
				if(plateNumberList[i].plateNumber == value){
					// 车主
					$("#diaoCarOwner").val(plateNumberList[i].driver1);
					// 车主电话
					$("#diaoCarOwnerTelephone").val(plateNumberList[i].telephone1);
					break;
				}
			}
		} else {
			swal({
				title: "错误提示",
				text: "车牌号码格式不正确！",
				type: "error",
				confirmButtonText: '确定',
				cancelButtonFont: '微软雅黑',
			},function(){
				// 清空处理
				$("#diaoPlateNumber").val("");
			});
		}
	}
}


/*// 销售订单编号飘窗出现
function mouseover(){
	var value = $("#orderNumber option:selected").val();
	if(value != ''){
		for(var i = 0; i < orderNumberList.length; i++){
			if(orderNumberList[i].id == value){
				SetFromValues($("#submitForm"), orderNumberList[i]);
			}
		}
		//鼠标放在合同编号，显示飘窗
		$('#fuchaung1').show();
	}
}

//销售订单编号飘窗消失
function mouseout(){
	$('#fuchaung1').hide();
}

// 物料信息飘窗出现
function mouseoverMateriel(){
	var orderNumber = $("#orderNumber option:selected").val();
	var materielId;
	var materielName = $("#materielName").val();
	var materielModel = $("#materielModel").val();
	if(materielName != "" && materielModel != ""){
		if(materielinfoList != null){
			for(var i =0; i< materielinfoList.length; i++){
				// 物料名称id和规格型号id
				if(materielName ==materielinfoList[i].materielName && materielModel==  materielinfoList[i].materielModel){
					materielId = materielinfoList[i].id;
				}
			}
		}
	}
	if(materielId && materielId != ''){
		$.ajax({
			type : "POST",
			url : "../../outbound/getOrderDetail.action",
			async:false,
			data : {
				"orderNumber": orderNumber,
				"materielId" : materielId	
			},
			dataType : "json",
			success : function(data) {
				if (data && data.length != 0) {
					SetFromValues($("#submitForm"), data[0]);
				} else {
					$("#UnitPrice").val("");
					$("#ListModel").val("");
					$("#transportBalances").val("");
					$("#ExpectedDeliveryDate").val("");
					$("#SaleNumber").val("");
					$("#SalePrice").val("");
					$("#TaxRate").val("");
					$("#SaleMoney").val("");
				}
				// 显示飘窗
				$('#fuchaung2').show();
			}
		})
	}
}

// 物料信息飘窗消失
function mouseoutMateriel(){
	$('#fuchaung2').hide();
}
*/

//获取无法选中的车辆信息
function queryCarInUse(){
	var carInUseList;
	$.ajax({
		type : "POST",
		url : "../../instore/queryCarInUse.action",
		async:false,
		data : {},
		dataType : "json",
		success : function(data) {
			carInUseList = data;
		}
	});
	return carInUseList;	
}

// 删除封签号并更新数据库
function deleteFiles(){
	$.ajax({ 
		type : "POST",
	    url: '../../outbound/updateFacingSlipNum.action',  
	    async:false,
		data : {"id": id},
		dataType : "json",                         //服务器返回的格式,选择json或者xml貌似有问题
	    success:function(fileData){
	    	deleteFile();
	   }
	});
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

//磅单打印名称
function changeMaterieModelName(){
	var materielName = $('#materielName option:selected').text();
	var materielModel = $('#materielModel option:selected').text();
	$("#lbsMaterialName").val(materielName + '-' + materielModel);
}