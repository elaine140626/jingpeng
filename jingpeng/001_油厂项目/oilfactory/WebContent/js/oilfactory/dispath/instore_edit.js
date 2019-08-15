var id = "";
//车牌号码
var plateNumberList;
//物料名称和型号
var materielinfoList;
// 采购合同
var purchasecontractList;
// 赋值和修改的标识
var flag;
// 不可选中的车牌
var carsInUseList;

//起运地信息
var startAddress;

$(function() {
	// 不可选中的车牌
	carsInUseList = queryCarInUse();
	// 所有车辆号码
	getAllPlateNumbers();
	// 采购合同
	getPurchasecontractList();
	// 物料名称型号
//	getMaterielinfoList();
	// 优先级
	$("#priority").html(getDataDictionary('yxj'));
	// 起运地信息
	getStartAddress();
	
	//称重类型
	cartWeighTypeList = getDataDictionary('weighingtype');
	$("#cartWeighType").append(cartWeighTypeList.substring(49, cartWeighTypeList.length));
	
	// 获取上页面传过来的值
	id = getUrlParam("id");
	flag = getUrlParam("flag");
	if(flag == 2){
		// 隐藏保存按钮
		$("#submitbutton").hide();
	}
	// 新增
	if (flag == null){
		$("#serialID").val(getContractInfoPrefix("RK"));
	}
	if(id != null && id != ""){
		// 修改
		if(flag == 1){
			$("#purchaseContractId").attr("readonly","readonly");
			$("#serialID").attr("readonly","readonly");
			$("#serialIDFlag").show();
		}
		
		// 画面数据初始化
		$.ajax({
			type: "post",
			url: "../../instore/getInstoreInfo.action",
			data:{"id":id},
			dataType: "json",
			async:false,
			success: function (data) {
				SetFromValues($("#submitForm"), data);
				
				// 采购合同
				$("#purchaseContractId option[value='"+data.purchaseContractId+"']").attr("selected", true);
			
				// 是否大车称重
				$("input[type=radio][value='"+data.cartWeighType +"']").attr("checked",'checked');
				if (flag != 1){
					$("#serialID").val(getContractInfoPrefix("RK"));
				}
				// 优先级
				$("#priority option[value='"+data.priority+"']").attr("selected", true);
				
				getMaterielinfoList();
				// 物料名称和型号
				$("#materielName option[value='"+data.materielName+"']").attr("selected", true);
				$("#materielModel option[value='"+data.materielModel+"']").attr("selected", true);
				
				// 起运地
				$("#startAddress option[value='"+data.startAddressId+"']").attr("selected", true);
				$("#deviation").val(data.supplierDeviation);
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
					$("#plateNumberList").empty();
					for(var i = 0; i < plateNumberList.length; i++){
						var carFlag = false;
						for (var j = 0 ; j < carsInUseList.length ; j++) {
							var a = carsInUseList[j].PlateNumber;
							var b = plateNumberList[i].plateNumber;
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

//物料名称和型号
function getMaterielinfoList(id){
	$.ajax({
		type: "post",
		url: "../../outbound/getMaterielinfoList.action",
		data:{
			"purchaseId":id
		},
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

// 保存按钮
function add(){
	// 获取画面数据
	var data = formToJson($("#submitForm"));
	
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
	
	// 数量
	if(data.amount == ""){
		swal({
 			title: "错误提示",
 			text: "数量必须填写!",
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

	// 优先级
	if(data.priority == ""){
		swal({
 			title: "错误提示",
 			text: "请选择优先级!",
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
		if(flag == 0){
			// 出库单编号
			data.serialID = getContractInfoPrefix("RK");
			updateContractInfoPrefix("RK");
			// 添加
			url = "../../instore/addImportMeasure.action";
		}else{
			// 修改
			url = "../../instore/updateImportMeasure.action";
		}
		
	}else{
		// 出库单编号
		data.serialID = getContractInfoPrefix("RK");
		updateContractInfoPrefix("RK");
		// 添加
		url = "../../instore/addImportMeasure.action";
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

//// 车牌号码更改
//function changePlateNumber(value){
//	flag = false;
//	// 判断当前选中车辆是否可选中
//	if(carsInUseList) {
//		for (var i = 0; i < carsInUseList.length; i++) {
//			if (carsInUseList[i].PlateNumber == value) {
////				$("#submitbutton").attr("disabled","disabled");
//				flag = true;
//				break;
//			}
//		}
//		// 若不可用则弹窗提示，清空车牌号码
//		if (flag) {
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
//			flag = false;
//		} else {
//			if (plateNumberList != null){
//				for(var i=0; i<plateNumberList.length; i++){
//					if(plateNumberList[i].plateNumber == value){
//						// 车主
//						$("#carOwner").val(plateNumberList[i].driver1);
//						// 车主电话
//						$("#carOwnerTelephone").val(plateNumberList[i].telephone1);
////						$("#submitbutton").removeAttr("disabled");
//						flag = false;
//						break;
//					}else{
//						flag = true;
//					}
//				}
//			}
//		}
//	} else {
//		if (plateNumberList != null){
//			for(var i=0; i<plateNumberList.length; i++){
//				if(plateNumberList[i].plateNumber == value){
//					// 车主
//					$("#carOwner").val(plateNumberList[i].driver1);
//					// 车主电话
//					$("#carOwnerTelephone").val(plateNumberList[i].telephone1);
//					flag = false;
//					break;
//				}else{
//					flag = true;
//				}
//			}
//		}
////		$("#submitbutton").removeAttr("disabled");
//	}
//	if (flag) {
//		swal({
// 			title: "错误提示",
// 			text: "车牌号码格式不正确！",
// 			type: "error",
// 			confirmButtonText: '确定',
// 			cancelButtonFont: '微软雅黑',
// 		},function(){
// 		// 清空处理
//			$("#plateNumber").val("");
// 		});
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

function mouseover(){
	var value = $("#purchaseContractId  option:selected").val();
	for(var i = 0; i < purchasecontractList.length; i++){
		if(purchasecontractList[i].id == value){
			SetFromValues($("#submitForm"), purchasecontractList[i]);
		}
	}
	//鼠标放在合同编号，显示飘窗
	$('.fuchuang2').show();
}

function mouseout(){
	//鼠标离开合同编号，飘窗消失
	$('.fuchuang2').hide();
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

// 根据合同获取物料信息
function changePurchaseContract(id) {
	getMaterielinfoList(id)
}

//查询所有起运地
function getStartAddress () {
	$.ajax({
		type : "POST",
		url : "../../Transports/getStartTransports.action",
		async:false,
		data : {},
		dataType : "json",
		success : function(data) {
			startAddress = data.data;
			$("#startAddress").empty();
			$("#startAddress").append("<option value=''>请选择</option>");
			for(var i=0; i<startAddress.length; i++){
				$("#startAddress").append("<option value='"+startAddress[i].id+"'>"+startAddress[i].startAddress+"</option>");
			}
		}
	});
}

//变更起源地
function changeStartAddress (value) {
	for(var i=0; i<startAddress.length; i++){
		if (startAddress[i].id == value) {
			$("#deviation").val(startAddress[i].deviation);
			break;
		}
	}
}