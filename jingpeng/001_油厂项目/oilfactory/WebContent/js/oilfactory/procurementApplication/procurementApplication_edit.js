var id = "";
//物料信息
var materielinfoList;
//复制和修改的标识
var flag;
//采购申请单流水号
var serialID;
var param;
$(function(){
	//数据字典查询紧急状态，采购级别
	getAllDatadictionaty();
	//物料型号
	getMaterielinfoList();
	// 获取上页面传过来的值
	id = getUrlParam("id");
	flag = getUrlParam("flag");
	if(flag != 1){
		serialID = getContractInfoPrefix("CGSQ");
		$("#serialID").val(serialID);
	}
	if(id != null && id != ""){
		// 画面数据初始化
		$.ajax({
			type: "post",
			url: "../../purchaseRequisition/getInfoListById.action",
			data:{"id":id},
			dataType: "json",
			async:false,
			success: function (data) {
				SetFromValues($("#submitForm"), data);
				//复制未称重编号变化
				if(flag == 0){
					var CGSQ = getContractInfoPrefix("CGSQ");
					$("#serialID").val(CGSQ);
				}
				// 物料名称
				$("#goodsName option[value='"+data.materielNameId+"']").attr("selected", true);
				//物料型号
				$.ajax({
					type: "post",
					url: "../../purchaseRequisition/getMaterielModel.action",
					data:{"materielNameId":data.materielName},
					dataType: "json",
					async:false,
					success: function (data1) {
						if(data1 != null){
							for(var i=0;i<data1.length;i++){
								$("#model").append("<option value='"+data1[i].materielModel+"'>"+data1[i].materielModel+"</option>");
							}
							$("#model option[value='"+data.materielModel+"']").attr("selected", true);
						}
					}
				});
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
//数据字典查询税率，运输结算情况，其他发货方式，类型
function getAllDatadictionaty(){
	$.ajax({
		type: "post",
		url: "../../purchaseRequisition/getAllDatadictionaty.action",
		data:{},
		dataType: "json",
		async:false,
		success: function (data) {
			if(data != null){
				if(data != null && data.length > 0){
					// 清空
					$("#isUrgentState").empty();
					$("#purchaseLevel").empty();
					$("#isUrgentState").append("<option value=''>请选择</option>");
					$("#purchaseLevel").append("<option value='-1'>请选择</option>");
					for(var i = 0; i < data.length; i++){
						if(data[i].type == 'jjzt'){
							$("#isUrgentState").append("<option value='"+data[i].code+"'>"+data[i].content+"</option>");
						}else if(data[i].type == 'cgjb'){
							$("#purchaseLevel").append("<option value='"+data[i].code+"'>"+data[i].content+"</option>");
						}
					}
				}
			}
		}
	});
}
//物料名称
function getMaterielinfoList(){
	$.ajax({
		type: "post",
		url: "../../purchaseRequisition/getMaterielinfoList.action",
		data:{},
		dataType: "json",
		async:false,
		success: function (data) {
			if(data != null){
				// 物料信息
				materielinfoList = data;
				if(materielinfoList != null && materielinfoList.length > 0){
					// 清空
					$("#goodsName").empty();
					var materielNameId = 0;
					$("#goodsName").append("<option value=''>请选择</option>");
					for(var i = 0; i < materielinfoList.length; i++){
						if(materielinfoList[i].materielNameId != materielNameId){
							$("#goodsName").append("<option value='"+materielinfoList[i].materielNameId+"'>"+materielinfoList[i].materielName+"</option>");
						}
					}
				}
			}
		}
	});
}
//计算金额
function count(data){
	// 单价1
	univalent1 = $("#univalent1").val();
	// 数量 
	applyNumber = $("#applyNumber").val();
	// 金额1
	$("#money1").val((univalent1*applyNumber).toFixed(2));
	// 单价2
	univalent2 = $("#univalent2").val();
	// 金额2
	$("#money2").val((univalent2*applyNumber).toFixed(2));
	// 单价3
	univalent3 = $("#univalent3").val();
	// 金额3
	$("#money3").val((univalent3*applyNumber).toFixed(2));
}
//保存按钮
function add(){
	// 获取画面数据
	var data = formToJson($("#submitForm"));
	// 物料名称和型号
	var materielName = $("#goodsName").val();
	var materielModel = $("#model").val();
	if(materielName != "" && materielModel != ""){
		$.ajax({
			type: "post",
			url: "../../purchaseRequisition/getMaterielModel.action",
			data:{"materielModelId":materielModel},
			dataType: "json",
			async:false,
			success: function (data1) {
				$("#goodsName").val(null);
				if(data1 != null){
					for(var i=0;i<data1.length;i++){
						data.goodsName = data1[0].id;
					}
				}
			}
		});
		/*if(materielinfoList != null){
			for(var i =0; i< materielinfoList.length; i++){
					data.goodsName = materielinfoList[i].id;
			}
		}*/
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
	//申请数量
	if(data.applyNumber == ""){
		swal({
 			title: "错误提示",
 			text: "申请数量不能为空!",
 			type: "error",
 			confirmButtonText: '确定',
 			cancelButtonFont: '微软雅黑',
 		});
		return;
	}
	//紧急状态
	if(data.isUrgentState == ""){
		swal({
 			title: "错误提示",
 			text: "紧急状态必须选择!",
 			type: "error",
 			confirmButtonText: '确定',
 			cancelButtonFont: '微软雅黑',
 		});
		return;
	}
	//预计到位时间
	if(data.estimatedTime == ""){
		swal({
 			title: "错误提示",
 			text: "预计到位时间不能为空!",
 			type: "error",
 			confirmButtonText: '确定',
 			cancelButtonFont: '微软雅黑',
 		});
		return;
	}
	//物料采购级别
	if(data.purchaseLevel == ""){
		swal({
 			title: "错误提示",
 			text: "物料采购级别必须选择!",
 			type: "error",
 			confirmButtonText: '确定',
 			cancelButtonFont: '微软雅黑',
 		});
		return;
	}
	//申请部门
	if(data.applyDepartment == ""){
		swal({
 			title: "错误提示",
 			text: "申请部门不能为空!",
 			type: "error",
 			confirmButtonText: '确定',
 			cancelButtonFont: '微软雅黑',
 		});
		return;
	}
	//申请人
	if(data.applicant == ""){
		swal({
 			title: "错误提示",
 			text: "申请人不能为空!",
 			type: "error",
 			confirmButtonText: '确定',
 			cancelButtonFont: '微软雅黑',
 		});
		return;
	}
	//录入采购人
	if(data.entryClerkPurchaser == ""){
		swal({
 			title: "错误提示",
 			text: "录入采购人不能为空!",
 			type: "error",
 			confirmButtonText: '确定',
 			cancelButtonFont: '微软雅黑',
 		});
		return;
	}
	//申请日期
	if(data.applyDate == ""){
		swal({
 			title: "错误提示",
 			text: "申请日期不能为空!",
 			type: "error",
 			confirmButtonText: '确定',
 			cancelButtonFont: '微软雅黑',
 		});
		return;
	}
	//录入申请人
	if(data.entryClerkApplicant == ""){
		swal({
 			title: "错误提示",
 			text: "录入申请人不能为空!",
 			type: "error",
 			confirmButtonText: '确定',
 			cancelButtonFont: '微软雅黑',
 		});
		return;
	}
	//录入时间
	if(data.entryClerkDate == ""){
		swal({
 			title: "错误提示",
 			text: "录入时间不能为空!",
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
			url = "../../purchaseRequisition/updatePurchaseRequisition.action";
		}else{
			// 未称重出库单编号
			data.serialID = getContractInfoPrefix("CGSQ");
			updateContractInfoPrefix("CGSQ");
			
			//添加
			url = "../../purchaseRequisition/addPurchaseRequisition.action";
		}
		
	}else{
		// 未称重出库单编号
		data.serialID = getContractInfoPrefix("CGSQ");
		updateContractInfoPrefix("CGSQ");
		// 添加
		url = "../../purchaseRequisition/addPurchaseRequisition.action";
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
//物料名称更改方法
function changeMaterieName(param){
	if(materielinfoList != null){
		$("#model").empty();
		for(var i=0; i<materielinfoList.length; i++){
			if(materielinfoList[i].materielNameId == param){
				$.ajax({
					type: "post",
					url: "../../purchaseRequisition/getMaterielModel.action",
					data:{"materielNameId":param},
					dataType: "json",
					success: function (data) {
						if(data != null){
							for(var i=0;i<data.length;i++){
								$("#model").append("<option value='"+data[i].materielModel+"'>"+data[i].materielModel+"</option>");
							}
						}
					}
				});
			}
		}
	}
}