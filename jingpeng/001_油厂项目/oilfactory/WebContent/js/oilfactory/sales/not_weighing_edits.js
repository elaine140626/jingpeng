// 获取出库单id
var id = '';
// 出库单信息
var info = "";

//销售订单编号
var orderNumberList;
$(function(){
	
	// 获取上页面传过来的值
	id = getUrlParam("id");
	outType = getUrlParam("outType");
	getOrderNumberList('qtfhfs');
	//税率取得
	TaxRateInfo = getDataDictionaryDefaultNums('sl');
	transportBalance = getDataDictionarys('ysjsqk');
	otherDelivery = getDataDictionarys('qtfhfs');
	// 获取出库单信息
	if(id != null && id != ""){
		// 画面数据初始化
		$.ajax({
			type: "post",
			url: "../../repertory/getInfoList.action",
			data:{"id":id},
			dataType: "json",
			async:false,
			success: function (data) {
				info = data.data[0];
				
				SetFromValues($("#submitForm"), info);
				if (info.isCarryOff == 0) {
					$("input[name=isCarryOff]:eq(0)").attr("checked",'checked'); 
				} else {
					$("input[name=isCarryOff]:eq(1)").attr("checked",'checked');
				}
				if (info.isSelfLifting == 0) {
					$("input[name=isSelfLifting]:eq(0)").attr("checked",'checked'); 
				} else {
					$("input[name=isSelfLifting]:eq(1)").attr("checked",'checked');
				}
				if (info.isTesting == 0) {
					$("input[name=isTesting]:eq(0)").attr("checked",'checked'); 
				} else {
					$("input[name=isTesting]:eq(1)").attr("checked",'checked');
				}
				getAllOutbounds(info.transports);
				getOrderDetailInfo(info.salesOrderId,info.orderDetailedNumber);
				for (var i = 0; i < TaxRateInfo.length; i++) {
					if(TaxRateInfo[i].Code == info.taxRate){
						$("#taxRate").val(TaxRateInfo[i].Content);
						break;
					}else{
						$("#taxRate").val("");
					}
				}
				for (var i = 0; i < transportBalance.length; i++) {
					if(transportBalance[i].Code == info.transportBalance){
						$("#transportBalance").val(transportBalance[i].Content);
						break;
					}else{
						$("#transportBalance").val("");
					}
				}
				for (var i = 0; i < otherDelivery.length; i++) {
					if(otherDelivery[i].Code == info.otherDelivery){
						$("#otherDelivery").val(otherDelivery[i].Content);
						break;
					}else{
						$("#otherDelivery").val("");
					}
				}
				var flag = 0;
			}
		});
	}
});



//销售订单明细的获取
function getOrderDetailInfo(param,orderDetailedNumber){
			$.ajax({
				type: "post",
				url: "../../repertory/getOrderDetailInfo.action",
				data:{"orderDetailedNumber":orderDetailedNumber},
				dataType: "json",
				async:false,
				success: function (data) {
					if (data && data.length > 0) {
						orderDetailNumberList = data;
						for(var i = 0; i < orderDetailNumberList.length; i++){
//							if(orderDetailNumberList[i].Id == orderDetailedNumber){
							var _html = "";
							var orderDetailedId = orderDetailNumberList[i].OrderDetailedNumber+"/"+orderDetailNumberList[i].MaterielInfo+"/"+orderDetailNumberList[i].Transports;
							if (orderDetailedId && orderDetailedId.length > 24) {
								_html = "<span title='"+orderDetailedId+"'>"+orderDetailedId.substring(0,24) + "..." +"</span>";
							}else{
								_html = "<span title='"+orderDetailedId+"'>"+orderDetailedId+"</span>";
							}
							$("#orderDetailedId").html(_html);	
//							}
						}
					}
				}
			});
}


function getAllOutbounds(param){
	$.ajax({
		type: "post",
		url: "../../repertory/getAllOutbounds.action",
		data:param,
		dataType: "json",
		async:false,
		success: function (data) {
			if(data != null){
				// 出库单状态名称
				outTypeNameList = data;
				if(outTypeNameList != null && outTypeNameList.length > 0){
					for(var i = 0; i < outTypeNameList.length; i++){ 
						if(param == outTypeNameList[i].Transports){
							var _html = "";
							var dispatchOutWarehouseId = outTypeNameList[i].serialId+" / "+outTypeNameList[i].MaterielInfo+" / "+outTypeNameList[i].Transports+" / "+outTypeNameList[i].plateNumber;
							if (dispatchOutWarehouseId && dispatchOutWarehouseId.length > 26) {
								_html = "<span title='"+dispatchOutWarehouseId+"'>"+dispatchOutWarehouseId.substring(0,26) + "..." +"</span>";
							}else{
								_html = "<span title='"+dispatchOutWarehouseId+"'>"+dispatchOutWarehouseId+"</span>";
							}
							$("#dispatchOutWarehouseId").html(_html);
						}
					}
				}
			}
		}
	});
}

//form表单赋值方法
function SetFromValues(el, data)
{
    for (var p in data)
    {
        el.find(":input[name='" + p + "']").val(data[p]);
    } 
    $("#productID").val(data["materielName"]+"-"+data["materielModel"]);
}


//销售合同编号
function getContractList(id){
	var Contract = '';
	$.ajax({
		type: "post",
		url: "../../instore/getContractList.action",
		data:{"id":id},
		dataType: "json",
		async:false,
		success: function (data) {
			Contract = data;
		}
	});
	return Contract;
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


