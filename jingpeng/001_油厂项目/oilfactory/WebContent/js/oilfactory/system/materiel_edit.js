// 物料id
var id = "";
// 连续添加标识
var continuity = '';
// 物料名称
var materielName = '';
// 物料型号
var materielModel = '';
// 物料编号
var materielNumber = '';
// 仓库
var warehouseId = '';
// 用户信息
var userInfo = '';
var userId = "";

$(function() {
	//关闭按钮
	$("#cancelbutton").click(function() {
		window.parent.$.fancybox.close();
	});
	//保存按钮
	$("#submitbutton").click(function() {
		addMateriel();
	});
	
	// 获取用户信息
	userInfo = getUserInfo();
	if(userInfo != ''){
		userId = userInfo.username;
	}
	
	// 获取物料类别
	var materielType = getDataDictionary("1");
	$("#materielType").html(materielType);
	
	// 仓库信息
	$.ajax({
		type: "post",
		url: '../../Materiel/getWarehouseinfo.action',
		async:false,
		dataType: "json",
		success: function (data) {
			$("#warehouseId").empty();
			$("#warehouseId").append("<option value=''>请选择</option>");
			$.each(data, function (i, v) {
				var html = "<option value="+ v.id +">"+ v.warehouseName +"</option>";
				$("#warehouseId").append(html);
			})
		}
	});
	
	// 获取上页面传过来的值
	id = getUrlParam("id");
	continuity = getUrlParam("continuity");
	if(continuity != null && continuity != ""){
		if(continuity == "true"){
			$("#continuityAdd").attr("checked", true);
		}
	}
	
	// 修改界面赋值
	if(id != null && id != ""){
		$("#continuityTr").css('display','none');
		$.ajax({
			type: "post",
			url: '../../Materiel/getMateriel.action',
			async:false,
			data : {"id" : id},
			dataType: "json",
			success: function (data) {
				if(data != null){
					SetFromValues($("#submitForm"), data.data[0]);
					//物料编号
					materielNumber = data.data[0].materielNumber;
					//物料名称
					materielName = data.data[0].materielName;
					//物料型号
					materielModel = data.data[0].materielModel;
					//物料仓库
					warehouseId = data.data[0].warehouseId;
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

// 保存
function addMateriel() {
	//获取页面的数据
	var param = formToJson($("#submitForm"));
	//是否连续添加
	var continuityAdd = $("#continuityAdd").is(':checked');
	
	if(param.materielType == ''){
		swal("操作失败", "请选择物料级别", "info");
		return
	}
	if(	param.warehouseId == ''){
		swal("操作失败", "请选择默认仓库", "info");
		return
	}else{
		// 仓库验证(油罐不能被多次使用)
		if(checkWarehouse(param.warehouseId)){
			swal({
				title: "错误提示",
				text: "仓库(油罐)已经被使用!",
				type: "error",
				confirmButtonText: '确定',
				cancelButtonFont: '微软雅黑',
			});
			return;
		}
	}
	
	if (param.materielNumber == "") {
		swal("操作失败", "必须输入物料编号", "info");
		return
	}
	if (param.materielName == "") {
		swal("操作失败", "必须输入物料名称", "info");
		return
	}
	if (param.materielModel == ""){
		swal("操作失败", "必须输入规格型号", "info");
		return
	}
	if (param.unit == "") {
		swal("操作失败", "必须输入单位", "info");
		return
	}
	if (param.primeNumber == "") {
		swal("操作失败", "必须输入年期初数量", "info");
		return
	}
	if (param.taxMoney == "") {
		swal("操作失败", "必须输入年期初含税金额", "info");
		return
	}
	if (param.noTaxMoney == "") {
		swal("操作失败", "必须输入年期初不含税金额", "info");
		return
	}
	
	// 修改
	if(id != null && id != ""){
		param.id = id;
		// 更新人
		param.reviser = userId;
		$.ajax({
			type : "POST",
			url : "../../Materiel/updateMateriel.action",
			data : param,
			dataType : "json",
			success : function(data) {
				if (data.code == "success") {
					swal({
						title : data.message,
						text : "",
						type : "success",
						confirmButtonText : '确定',
						cancelButtonFont : '微软雅黑',
					}, function() {
						window.parent.$.fancybox.close();
						window.location.reload();
					});
				} else {
					swal("操作失败", data.message, "error");
				}
			}
		});
	}else{
		// 新增
		// 创建人
		param.creater = userId;
		$.ajax({
			type : "POST",
			url : "../../Materiel/addMateriel.action",
			data : param,
			dataType : "json",
			success : function(data) {
				if (data.code == "success") {
					if(continuityAdd){
						window.location.href = 'materiels_information_edit.html?continuity='+continuityAdd;
					}else{
					swal({
						title : data.message,
						text : "",
						type : "success",
						confirmButtonText : '确定',
						cancelButtonFont : '微软雅黑',
					}, function() {
						window.parent.$.fancybox.close();
						window.location.reload();
					});
					}
				} else {
					swal("操作失败", data.message, "error");
				}
			}
		});
	}
}

//物料编号
function changeMaterielNumber(value){
	if(value != ''){
		if(materielNumber != value){
			$.ajax({
				type: "post",
				async:false,
				url: '../../Materiel/getAllMaterielNumber.action',
				dataType: "json",
				data:{"materielNumber":value},
				success: function (data) {
					if(data.length>0){
						swal({
							title: "错误提示",
							text: "物料编号重复重新输入!",
							type: "error",
							confirmButtonText: '确定',
							cancelButtonFont: '微软雅黑',
						});
						$("#materielNumber").val("");
					}
				}
			});
		}
	}else{
		swal("操作失败", "必须输入物料编号", "info");
		return;
	}
	
}

// 仓库
function checkWarehouse(value){
	var flag = true;
	if(value != ''){
		if(warehouseId != value){
			$.ajax({
				type: "post",
				async:false,
				url: '../../Materiel/getWarehouseinfo.action',
				dataType: "json",
				data:{"id":value},
				success: function (data) {
					if(data != null && data.length > 0){
						flag = true;
					}else{
						flag = false;
					}
				}
			});
		}else{
			flag = false;
		}
	}
	
	return flag;
}

// 物料名称,物料型号验证
function changeMateriel(){
	var materielNameValue = $("#materielName").val();
	var materielModelValue = $("#materielModel").val();	
	var params = {
			"materielName" : materielNameValue,
			"materielModel" : materielModelValue
	};
	
	if(materielNameValue != "" && materielModelValue != ""){
		if(id != ''){
			// 修改的情况下
			if(materielName != materielNameValue || materielModel != materielModelValue ){
				$.ajax({
					type: "post",
					async:false,
					url: '../../Materiel/getAllMaterielNumber.action',
					dataType: "json",
					data:params,
					success: function (data) {
						if(data.length > 0){
							swal({
								title: "错误提示",
								text: "相同物料名称和物料型号已经存在!",
								type: "error",
								confirmButtonText: '确定',
								cancelButtonFont: '微软雅黑',
							});
							$("#materielName").val('');
							$("#materielModel").val('');	
						}
					}
				});
			}
		}else{
			$.ajax({
				type: "post",
				async:false,
				url: '../../Materiel/getAllMaterielNumber.action',
				dataType: "json",
				data:params,
				success: function (data) {
					if(data.length > 0){
						swal({
							title: "错误提示",
							text: "相同物料名称和物料型号已经存在!",
							type: "error",
							confirmButtonText: '确定',
							cancelButtonFont: '微软雅黑',
						});
					}
				}
			});
		}
	}
	
}


