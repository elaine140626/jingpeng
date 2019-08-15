var id = "";
//销售订单编号
var orderNumberList;
//物料名称和型号
var materielinfoList;
//车牌号码
var plateNumberList;
// 采购合同
var purchasecontractList;
//赋值和修改的标识
var flag;

var flags;
//不可选中的车牌
var carsInUseList;
$(function() {
	// 结算方式
	$("#transportBalance").html(getDataDictionary('ysjsqk'));
	// 不可选中的车牌
	carsInUseList = queryCarInUse();
	// 销售订单编号
	getOrderNumberList();
	// 物料名称型号
//	getMaterielinfoList();
	// 所有车辆号码
	getAllPlateNumbers();
	// 采购合同
	getPurchasecontractList();
	
	// 获取上页面传过来的值
	id = getUrlParam("id");
	flag= getUrlParam("flag");
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
				changeOrderNumber(data.salesOrderId);
				getMaterielinfoList(data.salesOrderId);
				SetFromValues($("#submitForm"), data);
				// 销售订单编号
				$("#orderNumber option[value='"+data.salesOrderId+"']").attr("selected", true);
				$("#orderDetailedId option[value='"+data.orderDetailedId+"']").attr("selected", true);
				changeDetailedNumber(data.orderDetailedId);
				// 采购合同
				$("#purchaseContractId option[value='"+data.purchaseContractId+"']").attr("selected", true);
				if(flag == 1){
					$("#orderNumber").attr("disabled","true");
					$("#purchaseContractId").attr("disabled","true");
				}
				if (flag != 1){
					$("#serialId").val(getContractInfoPrefix("CK"));
				}
				// 物料名称和型号
				$("#materielName option[value='"+data.materielName+"']").attr("selected", true);
				$("#materielModel option[value='"+data.materielModel+"']").attr("selected", true);
				
				// 备注
				$("#remark").val(data.remarks);
				if (flag != 1){
					$("#suttle").val(data.suttle / 1000);
				}
				
				// 是否需要检测
				$("input[type=radio][value='"+data.isTesting +"']").attr("checked",'checked');
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
		data:{flag:"true"},
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
					for(var i = 0; i < orderNumberList.length; i++){
						$("#orderNumber").append("<option value='"+orderNumberList[i].id+"'>"+orderNumberList[i].orderNumbers+"</option>");
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
	changeMaterieModelName();
}

//所有车牌号码
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
							$("#plateNumberList").append("<option value='"+plateNumberList[i].plateNumber+"'>"+plateNumberList[i].plateNumber+"</option>");
//						}
					}
				}
			}
		}
	});
}

// 采购合同
function getPurchasecontractList(){
	$.ajax({
		type: "post",
		url: "../../outbound/getPurchasecontractList.action",
		data:{},
		dataType: "json",
		async:false,
		success: function (data) {
			if(data != null){
				// 采购合同
				purchasecontractList = data;
				if(purchasecontractList != null && purchasecontractList.length > 0){
					// 清空
					$("#purchaseContractId").empty();
					$("#purchaseContractId").append("<option value=''>请选择</option>");
					for(var i = 0; i < purchasecontractList.length; i++){
						$("#purchaseContractId").append("<option value='"+purchasecontractList[i].id+"'>"+purchasecontractList[i].billNumber+"</option>");
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
	// 销售订单编号
	if(data.orderNumber == "" && $("#orderNumber").val() == null && $("#orderNumber").val() == ""){
		swal({
 			title: "错误提示",
 			text: "销售订单编号必须选择!",
 			type: "error",
 			confirmButtonText: '确定',
 			cancelButtonFont: '微软雅黑',
 		});
		return;
	}
	// 修改时因为select标签设置disable属性后不能直接submit，所以手动赋值
	data.orderNumber = $("#orderNumber").val();
	
	// 采购合同
	if(data.purchaseContractId == ""){
		swal({
 			title: "错误提示",
 			text: "采购合同必须选择!",
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
//			});
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
	// 销售公司名称
	if(data.GrossWeight == ""){
		swal({
 			title: "错误提示",
 			text: "毛重不能为空!",
 			type: "error",
 			confirmButtonText: '确定',
 			cancelButtonFont: '微软雅黑',
 		});
		return;
	}else {
		data.GrossWeight *= 1000;
	}
	// 销售公司名称
	if(data.TareWeight == ""){
		swal({
 			title: "错误提示",
 			text: "皮重不能为空!",
 			type: "error",
 			confirmButtonText: '确定',
 			cancelButtonFont: '微软雅黑',
 		});
		return;
	}else {
		data.TareWeight *= 1000;
	}
	// 净重不能为空
	if(data.suttle == ""){
		swal({
			title: "错误提示",
			text: "净重不能为空!",
			type: "error",
			confirmButtonText: '确定',
			cancelButtonFont: '微软雅黑',
		});
		return;
	} else {
		data.suttle *= 1000;
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
	console.log($("#customerAlias").val());
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
	if(data.carOwner == ""){
		swal({
 			title: "错误提示",
 			text: "车主必须填写!",
 			type: "error",
 			confirmButtonText: '确定',
 			cancelButtonFont: '微软雅黑',
 		});
		return;
	}
	
	
	// 车主电话
	if(data.carOwnerTelephone == ""){
		swal({
 			title: "错误提示",
 			text: "车主电话必须填写!",
 			type: "error",
 			confirmButtonText: '确定',
 			cancelButtonFont: '微软雅黑',
 		});
		return;
	}
	
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
	
	// 送货人
	if(data.deliveryMan == ""){
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
	if(data.deliveryManPhone == ""){
		swal({
 			title: "错误提示",
 			text: "送货人电话必须填写!",
 			type: "error",
 			confirmButtonText: '确定',
 			cancelButtonFont: '微软雅黑',
 		});
		return;
	}
	
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
	if(data.lbsOutputName == ""){
		swal({
 			title: "错误提示",
 			text: "磅单输出名称必须填写!!",
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
	data.consigneeAddress = $("#transports").val();
	data.remarks = $("#remark").val();
	
	// 路径
	var url;
	
	if(id != null && id != ''){
		data.id = id;
		// 修改
		if(flag == 1){
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
			// 生成的来料加工流水号
			data.rkSerialID = getContractInfoPrefix("RK");
			updateContractInfoPrefix("RK");
			// 添加
			url = "../../outbound/addExportMeasure.action";
		}
	}else{
		data.flag = 0
		// 出库单编号
		data.serialID = getContractInfoPrefix("CK");
		updateContractInfoPrefix("CK");
		// 生成的来料加工流水号
		data.rkSerialID = getContractInfoPrefix("RK");
		updateContractInfoPrefix("RK");
		// 添加
		url = "../../outbound/addExportMeasure.action";
	}

	$.ajax({
		type: "post",
		url: url,
		data:data,
		dataType: "json",
		success: function (data) {
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

// 物料名称更改方法
function changeMaterieName(value){
	if(materielinfoList != null){
		$("#materielModel").empty();
		for(var i=0; i<materielinfoList.length; i++){
			if(materielinfoList[i].materielName == value){
				$("#materielModel").append("<option value='"+materielinfoList[i].materielModel+"'>"+materielinfoList[i].materielModel+"</option>");
				changeMaterieModelName(materielinfoList[i].materielModel);
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
					$("#orderDetailedId").append("<option value='"+data[i].Id+"'>"+data[i].OrderDetailedNumber+"         "+data[i].MaterielInfo+"         "+data[i].Transports+"</option>");
				}
			}
		}
	});
	if (value == "") {
		changeDetailedNumber("");
		$("#orderDetailedId").empty();
		$("#orderDetailedId option[value='']").attr("selected", true);
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
					$("#client").val(orderNumberList[i].customerName);
					// 客户别称
					$("#customerAlias").val(orderNumberList[i].customer);
					// 送货地址
					$("#address").val(orderNumberList[i].address);
					// 收货人
					$("#consignee").val(orderNumberList[i].contacts);
					// 联系电话
					$("#consigneePhone").val(orderNumberList[i].telephone);
					// 给运距，止运地赋值
//				$.ajax({
//					type: "post",
//					url: "../../outbound/getCustomerTrans.action",
//					data:{
//						"customerName":orderNumberList[i].customerName
//					},
//					dataType: "json",
//					success: function (data) {
//						if (data && data.length > 0) {
//							$("#distance").val(data[0].Distance);
//							$("#transports").val(data[0].Transports);
//						}
//					}
//				});
					break;
				}
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
				$("#transportBalance option[value='"+orderDetailNumberList[i].TransportBalance+"']").attr("selected", true);
				$("#distance").val(orderDetailNumberList[i].Distance);
				$("#transports").val(orderDetailNumberList[i].Transports);
			}
		}
	}
	getMaterielinfoList(value);
	if (value == "") {
		$("#materielName").empty();
		$("#materielModel").empty();
		$("#distance").val("");
		$("#transports").val("");
		$("#lbsMaterialName").val("");
		$("#transportBalance option[value='']").attr("selected", true);
	}
}

// 车牌号码更改
//function changePlateNumber(value){
//	flags = false;
//	// 判断当前选中车辆是否可选中
//	if(carsInUseList) {
//		for (var i = 0; i < carsInUseList.length; i++) {
//			if (carsInUseList[i].PlateNumber == value) {
//				flags = true;
//				break;
//			}
//		}
//		// 若不可用则弹窗提示，清空车牌号码
//		if (flags) {
//			swal({
//	 			title: "错误提示",
//	 			text: "该车辆当前不可用请重新选择！",
//	 			type: "error",
//	 			confirmButtonText: '确定',
//	 			cancelButtonFont: '微软雅黑',
//	 		},function(){
//	 		// 清空处理
//				$("#plateNumber").val("");
//	 		});
//		} else {
//			if(plateNumberList != null){
//				for(var i=0; i<plateNumberList.length; i++){
//					if(plateNumberList[i].plateNumber == value){
//						// 车主
//						$("#carOwner").val(plateNumberList[i].driver1);
//						// 车主电话
//						$("#carOwnerTelephone").val(plateNumberList[i].telephone1);
//						break;
//					}
//				}
//			}
//		}
//	} else {
//		if(plateNumberList != null){
//			for(var i=0; i<plateNumberList.length; i++){
//				if(plateNumberList[i].plateNumber == value){
//					// 车主
//					$("#carOwner").val(plateNumberList[i].driver1);
//					// 车主电话
//					$("#carOwnerTelephone").val(plateNumberList[i].telephone1);
//					break;
//				}
//			}
//		}
//	}
//}

//车牌号码更改
function changePlateNumber(value){
	if(plateNumberList != null){
		for(var i=0; i<plateNumberList.length; i++){
			if(plateNumberList[i].plateNumber == value){
				// 车主
				$("#deliveryMan").val(plateNumberList[i].driver1);
				// 车主电话
				$("#deliveryManPhone").val(plateNumberList[i].telephone1);
				break;
			}
		}
	}
}

function mouseover(flag){
	if(flag == 0){
		var value = $("#purchaseContractId  option:selected").val();
		if(value != ''){
			for(var i = 0; i < purchasecontractList.length; i++){
				if(purchasecontractList[i].id == value){
					SetFromValues($("#submitForm"), purchasecontractList[i]);
				}
			}
			//鼠标放在合同编号，显示飘窗
			$('.fuchuang2').show();
		}
		
	}else{
		var value = $("#orderNumber  option:selected").val();
		if(value != ''){
			for(var i = 0; i < orderNumberList.length; i++){
				if(orderNumberList[i].id == value){
					SetFromValues($("#submitForm"), orderNumberList[i]);
				}
			}
			//鼠标放在合同编号，显示飘窗
			$('#fuchaung').show();
		}
	}
	
}


//净重
function setSuttle() {
	// 当毛重和皮重都有值时。计算净重并赋值
	if ($("#GrossWeight").val() && $("#GrossWeight").val() != "" && $("#TareWeight").val() && $("#TareWeight").val() != "") {
		// 净重 = 毛重 - 皮重
		var suttle = $("#GrossWeight").val() - $("#TareWeight").val()
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
				$("#GrossWeight").val("");
				$("#TareWeight").val("");
	 		});
		} else {
			$("#suttle").val(suttle);
		}
	}
}

function mouseout(flag){
	if(flag == 0){
		//鼠标离开合同编号，飘窗消失
		$('.fuchuang2').hide();
	}else{
		$('#fuchaung').hide();
	}
}

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

//物料信息飘窗出现
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

//磅单打印名称
function changeMaterieModelName(){
	var materielName = $('#materielName option:selected').text();
	var materielModel = $('#materielModel option:selected').text();
	$("#lbsMaterialName").val(materielName + '-' + materielModel);
}