var baseUrl = "";
$(function(){
	baseUrl = getContextPath();
	//查询所有
	getAllPrefix();
});

//操作数据
function add(){
	//主键
	var id = $("#zj").val();
	//销售合同编号前缀
	var contractPrefix = $("#hetong").val();
	//销售订单编号前缀
	var orderPrefix = $("#xiaoshou").val();
	//出库单编号前缀
	var exportMeasurePrefix = $("#chuku").val();
	//入库单编号前缀
	var storageMeasurePrefix = $("#ruku").val();
	var url;
	var param;
	if(id == null || id == ""){
		//添加
		 param = {
				"contractPrefix":contractPrefix,
				"orderPrefix":orderPrefix,
				"exportMeasurePrefix":exportMeasurePrefix,
				"storageMeasurePrefix":storageMeasurePrefix
		}
		url = baseUrl+"/prefix/addContractInfoPrefix.action";
	}else{
		//修改
		 param = {
				"id":id,
				"contractPrefix":contractPrefix,
				"orderPrefix":orderPrefix,
				"exportMeasurePrefix":exportMeasurePrefix,
				"storageMeasurePrefix":storageMeasurePrefix
		}
		url = baseUrl+"/prefix/updateSerialNumber.action";
	}
	$.ajax({
		type: "post",
		url: url,
		data:param,
		async:false,
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
			 			window.location.reload();
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

//查询所有
function getAllPrefix(){
	$.ajax({
		type: "post",
		url: baseUrl+'/prefix/getAllPrefix.action',
		async:false,
		dataType: "json",
		success: function (data) {
			//主键
			$("#zj").val(data.id);
			//销售合同编号前缀
			$("#hetong").val(data.contractPrefix);
			//销售订单编号前缀
			$("#xiaoshou").val(data.orderPrefix);
			//出库单编号前缀
			$("#chuku").val(data.exportMeasurePrefix);
			//入库单编号前缀
			$("#ruku").val(data.storageMeasurePrefix);
		}
	});
}