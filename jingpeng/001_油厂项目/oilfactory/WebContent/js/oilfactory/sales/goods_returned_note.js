// 获取出库单id
var id = '';
//物料名称和型号
var materielinfoList;
//车牌号码
var plateNumberList;
// 净重
var suttle = 0;
// 出库单信息
var info = "";
// 出库单称重类型
var cartWeighType = "";
$(function(){
	// 获取所有车牌号码
	getAllPlateNumbers();
	// 物料型号
	getMaterielinfoList();
	
	getOrderNumberList();
	// 获取上页面传过来的值
	id = getUrlParam("id");
	
	// 获取出库单信息
	if(id != null && id != ""){
		// 画面数据初始化
		$.ajax({
			type: "post",
			url: "../../outlist/getSalesList.action",
			data:{"id":id},
			dataType: "json",
			async:false,
			success: function (data) {
				info = data.data[0];
				SetFromValues($("#submitForm"), info);
				$("#client").val(info.customerCode);
				// 物料名称和型号
				$("#materielName option[value='"+info.materielName+"']").attr("selected", true);
				$("#materielModel option[value='"+info.materielModel+"']").attr("selected", true);
				cartWeighType = info.cartWeighType;
				suttle = info.suttle;
				// 退货量
				$("#refundAmount").val((info.suttle - info.amount) < 0 ? 0 : (info.suttle - info.amount));
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
					$("#orderNumberList").empty();
					for(var i = 0; i < orderNumberList.length; i++){
						$("#orderNumberList").append("<option>"+orderNumberList[i].orderNumbers+"</option>");
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
					$("#plateNumberList").empty();
					for(var i = 0; i < plateNumberList.length; i++){
						$("#plateNumberList").append("<option value='"+plateNumberList[i].plateNumber+"'>"+plateNumberList[i].plateNumber+"</option>");
					}
				}
			}
		}
	});
}

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

//物料名称更改方法
function changeMaterieName(value){
	if(materielinfoList != null){
		$("#materielModel").empty();
		for(var i=0; i<materielinfoList.length; i++){
			if(materielinfoList[i].materielName== value){
				$("#materielModel").append("<option value='"+materielinfoList[i].materielModel+"'>"+materielinfoList[i].materielModel+"</option>");
			}
		}
	}

}

// 保存
function add(){
	// 获取画面数据
	var data = formToJson($("#submitForm"));
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
	
	// 退货重量
	if (data.refundAmount == "") {
		swal({
 			title: "错误提示",
 			text: "退货重量不能为空！",
 			type: "error",
 			confirmButtonText: '确定',
 			cancelButtonFont: '微软雅黑',
 		});
		return;
	}
	
	// 结算重量
	if (data.amount == "") {
		swal({
			title: "错误提示",
			text: "结算重量不能为空！",
			type: "error",
			confirmButtonText: '确定',
			cancelButtonFont: '微软雅黑',
		});
		return;
	}
	
	// 出库重量必须大于结算重量
	if ((data.suttle * 1) < (data.amount * 1)) {
		swal({
			title: "错误提示",
			text: "出库重量必须大于结算重量！",
			type: "error",
			confirmButtonText: '确定',
			cancelButtonFont: '微软雅黑',
		});
		return;
	}
	
	// 送货人
	if (data.deliveryMan == "") {
		swal({
			title: "错误提示",
			text: "送货人不能为空！",
			type: "error",
			confirmButtonText: '确定',
			cancelButtonFont: '微软雅黑',
		});
		return;
	}
	
	// 送货电话
	if (data.deliveryManPhone == "") {
		swal({
			title: "错误提示",
			text: "送货电话不能为空！",
			type: "error",
			confirmButtonText: '确定',
			cancelButtonFont: '微软雅黑',
		});
		return;
	}
	data.remarks = $("#remark").val();
	// 出库单id
	data.id = id;
	// 销售合同id
	data.contractId = info.contractId;
	// 大车称重
	data.isHeavyCar = info.isCartWeigh;
	data.cartWeighType = cartWeighType;

	data.orderDetailedId = info.orderDetailedId;
//	data.serialID = info.serialId;
	data.serialID = getContractInfoPrefix("RK");
	// 新增入库单/出库单"退货"
	$.ajax({
		type: "post",
		url: "../../outlist/refund.action",
		data:data,
		dataType: "json",
		async:false,
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
					updateContractInfoPrefix("RK");
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

// 结算数量和退货数量的计算
function changeAmount(flag){
	// 结算数量变更
	if(flag == 0){
		var amount = $("#amount").val();
		$("#refundAmount").val((suttle - amount) < 0 ? 0 : (suttle - amount));
	}else{
		// 退货数量变更
		var refundAmount = $("#refundAmount").val();
		$("#amount").val(suttle - refundAmount);
	}
}