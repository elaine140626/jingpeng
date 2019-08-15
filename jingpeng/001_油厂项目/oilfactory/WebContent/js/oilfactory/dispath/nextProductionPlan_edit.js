// 用户
var userInfo = "";
var id = "";
var flag = '';
// 销售订单
var salesOrdersList = '';
$(document).ready(function(){
	// 获取用户信息
	userInfo = getUserInfo();
	// 获取上页面传过来的值
	id = getUrlParam("id");
	flag = getUrlParam("flag");
	// 画面的数据select加载
	$.ajax({
		type: "post",
		url: "../../nextProductionPlan/getSalesList.action",
		data:{"id":id},
		dataType: "json",
		async:false,
		success: function (data) {
			// 订货单位名称
			salesOrdersList = data.salesOrdersList;
			var html = "<option selected='selected' value=''>请选择</option>";
			
			if(salesOrdersList != '' && salesOrdersList.length > 0){
				// 客户编号
				var customerCode = '';
				for(var i = 0; i < salesOrdersList.length; i++){
					if(customerCode != salesOrdersList[i].customerCode){
						var pdFlag = false;
						for(var j = 0; j < salesOrdersList.length; j++){
							// 判断是否存在关联计划单
							if(salesOrdersList[i].id == salesOrdersList[j].id && salesOrdersList[j].planId == null){
								pdFlag = true;
							}
						}
						
						// 字体变色
						if(pdFlag){
							html += "<option style='color:red;' value='"+ salesOrdersList[i].customerCode +"'>"+salesOrdersList[i].customerCode+"</option>";
						}else{
							html += "<option value='"+ salesOrdersList[i].customerCode +"'>"+salesOrdersList[i].customerCode+"</option>";
						}
//						html += "<option value='"+ salesOrdersList[i].customerCode +"'>"+salesOrdersList[i].customerCode+"</option>";
					}
					customerCode = salesOrdersList[i].customerCode;
				}
				$("#customerCode").append(html);
			}
			// 销售订单
			salesOrdersList = data.salesOrdersList;
			$("#salesOrderId").append("<option selected='selected' value=''>请选择</option>");
			// 销售订单明细
			$("#salesOrderDetailedId").append("<option selected='selected' value=''>请选择</option>");
		}
	});
	
	init();
})

// 画面数据初始化
function init(){
	// 画面数据初始化
	if(id != null && id != ''){
		$.ajax({
			type: "post",
			url: "../../nextProductionPlan/getProductionPlanList.action",
			data:{"id":id},
			dataType: "json",
			async:false,
			success: function (data) {
				SetFromValues($("#submitForm"), data.data[0]);
				$("#customerCode option[value='"+data.data[0].customerId+"']").attr("selected", true);
				changeCn();
				$("#salesOrderId option[value='"+data.data[0].salesOrderId+"']").attr("selected", true);
				changeSalesOrders();
				$("#salesOrderDetailedId option[value='"+data.data[0].salesOrderDetailedId+"']").attr("selected", true);
			}
		});
		
		// 查看
		if(flag == 1){
			// 画面不可编辑
			$("#customerCode").attr("disabled",true);
			$("#salesOrderId").attr("disabled",true);
			$("#salesOrderDetailedId").attr("disabled",true);
			$("#planNumber").attr("disabled",true);
			$("#planWeight").attr("disabled",true);
			$("#batch").attr("disabled",true);
			$("#finishTime").attr("disabled",true);
			$("#analystOrDirector").attr("disabled",true);
			$("#remarks").attr("disabled",true);
			$("#submitbutton").hide();
		}else if(flag == 0){
			// 修改
			$("#customerCode").attr("disabled",true);
			$("#salesOrderId").attr("disabled",true);
			$("#salesOrderDetailedId").attr("disabled",true);
			$("#planNumber").attr("disabled",true);
			document.getElementById('planNumber').style.background="#cccccc";
			$("#planWeight").attr("disabled",false);
			$("#batch").attr("disabled",false);
			$("#finishTime").attr("disabled",false);
			$("#analystOrDirector").attr("disabled",false);
			$("#remarks").attr("disabled",false);
			$("#submitbutton").show();
		}
	}else{
		$("#customerCode").attr("disabled",false);
		$("#salesOrderId").attr("disabled",false);
		$("#salesOrderDetailedId").attr("disabled",false);
		$("#planNumber").attr("disabled",false);
		$("#planWeight").attr("disabled",false);
		$("#batch").attr("disabled",false);
		$("#finishTime").attr("disabled",false);
		$("#analystOrDirector").attr("disabled",false);
		$("#remarks").attr("disabled",false);
		$("#submitbutton").show();
	}
}

// 订货单位名称
function changeCn(){
	var customerCode = $("#customerCode").val();
	for(var i = 0; i < salesOrdersList.length; i++){
		if(customerCode == salesOrdersList[i].customerCode){
			 $("#customerName").val(salesOrdersList[i].customerName);
			 continue;
		}
	}
	var html = "<option selected='selected' value=''>请选择</option>";
	if(salesOrdersList != "" && salesOrdersList.length > 0){
		$("#salesOrderId").html("");
		$("#salesOrderDetailedId").html("<option selected='selected' value=''>请选择</option>");
		var salesOrderId = '';
		for(var i = 0; i < salesOrdersList.length; i++){
			if(customerCode == salesOrdersList[i].customerCode){
				if(salesOrderId != salesOrdersList[i].id){
					var pdFlag = false;
					for(var j = 0; j < salesOrdersList.length; j++){
						// 判断是否存在关联计划单
						if(salesOrdersList[i].id == salesOrdersList[j].id && salesOrdersList[j].planId == null){
							pdFlag = true;
						}
					}
					
					// 字体变色
					if(pdFlag){
						html += "<option style='color:red;' value='"+ salesOrdersList[i].id +"'>"+salesOrdersList[i].orderNumbers+"</option>";
					}else{
						html += "<option value='"+ salesOrdersList[i].id +"'>"+salesOrdersList[i].orderNumbers+"</option>";
					}
					
				}
				salesOrderId = salesOrdersList[i].id;
			}
		}
		$("#salesOrderId").append(html);
	}
}

// 销售订单
function changeSalesOrders(){
	var salesOrderId = $("#salesOrderId").val();
	var html = "<option selected='selected' value=''>请选择</option>";
	if(salesOrdersList != "" && salesOrdersList.length > 0){
		$("#salesOrderDetailedId").html("");
		for(var i = 0; i < salesOrdersList.length; i++){
			if(salesOrderId == salesOrdersList[i].id){
				if(salesOrdersList[i].planId != null && salesOrdersList[i].orderDetailedId != null){
					html += "<option value='"+ salesOrdersList[i].orderDetailedId +"'>"+salesOrdersList[i].orderDetailedNumber+"</option>";
				}else if(salesOrdersList[i].orderDetailedId != null){
					html += "<option style='color:red;' value='"+ salesOrdersList[i].orderDetailedId +"'>"+salesOrdersList[i].orderDetailedNumber+"</option>";
				}
				
			}
		}
		$("#salesOrderDetailedId").append(html);
	}
}

// 判断销售订单明细是否已经存在生产计划
function IsSalesOrders(orderDetailedId){
	var flag = false;
	if(salesOrdersList != "" && salesOrdersList.length > 0){
		for(var i = 0; i < salesOrdersList.length; i++){
			if(orderDetailedId == salesOrdersList[i].orderDetailedId && salesOrdersList[i].planId != null){
				flag = true;
			}
		}
	}
	return flag;
}

// 销售订单明细
function changeSalesOrderDetailed(){	
	var salesOrderDetailedId = $("#salesOrderDetailedId").val();
	if(salesOrdersList != "" && salesOrdersList.length > 0){
		for(var i = 0; i < salesOrdersList.length; i++){
			if(salesOrderDetailedId == salesOrdersList[i].orderDetailedId){
				// 销售数量
				$("#saleNumber").val(salesOrdersList[i].saleNumber);
				if(salesOrdersList[i].materielId != null){
					// 产品名称
					$("#materielName").val(salesOrdersList[i].materielName);
					// 产品规格
					$("#materielModel").val(salesOrdersList[i].materielModel);
					// 单位
					$("#unit").val(salesOrdersList[i].unit);
				}
				
				if(salesOrdersList[i].exchangeMaterielId != null){
					// 产品名称
					$("#materielName").val(salesOrdersList[i].exchangeMaterielName);
					// 产品规格
					$("#materielModel").val(salesOrdersList[i].exchangeMaterielModel);
					// 单位
					$("#unit").val(salesOrdersList[i].exchangeUnit);
				}
				return;
			}
		}
	}
}

//form表单赋值方法
function SetFromValues(el, data)
{
    for (var p in data)
    {
        el.find(":input[name='" + p + "']").val(data[p]);
    } 
}

// 保存方法
function save(){
	// 获取画面数据
	var data = formToJson($("#submitForm"));
	var param = {};
	if(IsSalesOrders(data.salesOrderDetailedId)){
		swal({
			title: "错误提示",
			text: "该销售明细订单已经下发了计划单!",
			type: "error",
			confirmButtonText: '确定',
			cancelButtonFont: '微软雅黑',
		});
		return;
	}
	
	// 订货单位名称
	if(data.customerCode == ''){
		swal({
			title: "错误提示",
			text: "订货单位名称必须选择!",
			type: "error",
			confirmButtonText: '确定',
			cancelButtonFont: '微软雅黑',
		});
		return;
	}
	
	// 销售订单
	if(data.salesOrderId == ''){
		swal({
			title: "错误提示",
			text: "销售订单编号必须选择!",
			type: "error",
			confirmButtonText: '确定',
			cancelButtonFont: '微软雅黑',
		});
		return;
	}
	
	// 销售订单明细编号
	if(data.salesOrderDetailedId == ''){
		swal({
			title: "错误提示",
			text: "销售订单编号必须选择!",
			type: "error",
			confirmButtonText: '确定',
			cancelButtonFont: '微软雅黑',
		});
		return;
	}else{
		
		// 销售订单明细id
		param.salesOrderDetailedId = data.salesOrderDetailedId;
	}
	
	// 编号
	if(data.planNumber == ''){
		swal({
			title: "错误提示",
			text: "编号不能为空!",
			type: "error",
			confirmButtonText: '确定',
			cancelButtonFont: '微软雅黑',
		});
		return;
	}else{
		param.planNumber = data.planNumber;
	}
	
	// 计划生产量
	if(data.planWeight == ''){
		swal({
			title: "错误提示",
			text: "计划生产量不能为空!",
			type: "error",
			confirmButtonText: '确定',
			cancelButtonFont: '微软雅黑',
		});
		return;
	}else{
		param.planWeight = data.planWeight;
	}
		
	// 生产批次
	if(data.batch == ''){
		swal({
			title: "错误提示",
			text: "生产批次不能为空!",
			type: "error",
			confirmButtonText: '确定',
			cancelButtonFont: '微软雅黑',
		});
		return;
	}else{
		param.batch = data.batch;
	}
	
	// 计划完成时间
	if(data.finishTime == ''){
		swal({
			title: "错误提示",
			text: "计划完成时间不能为空!",
			type: "error",
			confirmButtonText: '确定',
			cancelButtonFont: '微软雅黑',
		});
		return;
	}else{
		param.finishTime = data.finishTime;
	}
	
	// 下发人员角色
	if(data.analystOrDirector == ''){
		swal({
			title: "错误提示",
			text: "下发人员角色必须选择!",
			type: "error",
			confirmButtonText: '确定',
			cancelButtonFont: '微软雅黑',
		});
		return;
	}else{
		param.analystOrDirector = data.analystOrDirector;
	}
	
	// 备注
	param.remarks = data.remarks;
	
	var url = '';
	if(id != null && id != ''){
		// 更新人
		param.reviser = userInfo.id;
		// id
		param.id= id;
		url = '../../nextProductionPlan/updateProductionPlan.action';
	}else{
		// 创建人
		param.creater = userInfo.id;
		url = '../../nextProductionPlan/insertProductionPlan.action';
	}

	$.ajax({
		type: "post",
		url: url,
		data: param,
		dataType: "json",
		success: function (data) {
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
	});
}

//编号判重
function changePlanNumber(value){
	$.ajax({
		type: "post",
		url: "../../nextProductionPlan/getPlanNumber.action",
		data: {"PlanNumber":value},
		dataType: "json",
		async:false,
		success: function (data) {
			if(data != null && data != 0){
				swal({
					title: "错误提示",
					text: "编号已经存在",
					type: "error",
					confirmButtonText: '确定',
					cancelButtonFont: '微软雅黑',
				});
				$("#planNumber").val("");
				return;
			}
		}
	});
}