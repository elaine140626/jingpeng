//路径
var baseUrl = "";
//供应商id
var id = "";
//是否连续添加
var continuity;


$(function() {
	//获取路径
	baseUrl = getContextPath();
	//解析所有参数
	var param = parseUrl();
	if(param != null && param!= ""){
		if(param.continuity != null && param.continuity != ""){
			continuity = param['continuity']
			if(continuity == "true"){
				//选中连续添加
				$("#continuityAdd").attr("checked", true);
			}
		}
		//修改的时候连续添加按钮隐藏
		if(param.id != null && param.id != ""){
			id = param['id'];
			$("#continuityTr").css('display','none');
			//通过id查询供应商信息
			findProviderByid(id);
		}
	} 
});

//解析所有参数
function parseUrl() {
	var url = location.href;
	var i = url.indexOf('?');
	if (i == -1)
		return;
	var querystr = url.substr(i + 1);
	var arr1 = querystr.split('&');
	var arr2 = new Object();
	for (i in arr1) {
		var ta = arr1[i].split('=');
		arr2[ta[0]] = ta[1];
	}
	return arr2;
}

//添加供应商信息
function addProvider() {
	//是否连续添加
	var continuityAdd = $("#continuityAdd").is(':checked')
	//供应商编号
	var SupplierNumber = $("#SupplierNumber").val();
	//供应商名称
	var SupplierName = $("#SupplierName").val();
	//地址
	var Address = $("#Address").val();
	//联系人
	var Contacts = $("#Contacts").val();
	//联系电话
	var Telephone = $("#Telephone").val();
	//其他联系方式
	var OtherNumbers = $("#OtherNumbers").val();
	//备注
	var Remarks = $("#Remarks").val();
	//年期初我方欠款(元)
	var ArrearsMoney = $("#ArrearsMoney").val();
	
	if (SupplierNumber == "") {
		swal("操作失败", "必须输入供应商编号", "info");
		return
	}
	if (SupplierName == "") {
		swal("操作失败", "必须输入供应商名称", "info");
		return
	}
	if (Address == ""){
		swal("操作失败", "必须输入地址", "info");
		return
	}
	if (Contacts == "") {
		swal("操作失败", "必须输入联系人", "info");
		return
	}
	if (Telephone == "") {
		swal("操作失败", "必须输入联系电话", "info");
		return
	}
	if (OtherNumbers == "") {
		swal("操作失败", "必须输入其它联系方式", "info");
		return
	}
	if (ArrearsMoney == "") {
		swal("操作失败", "必须输入年期初我方欠款", "info");
		return
	}
	var params = {
		"SupplierNumber" : SupplierNumber,
		"SupplierName" : SupplierName,
		"Address":Address,
		"Contacts" : Contacts,
		"Telephone" : Telephone,
		"OtherNumbers" : OtherNumbers,
		"Remarks" : Remarks,
		"ArrearsMoney" : ArrearsMoney
	};
	if(id != null && id != ""){
		params.id = id;
		$.ajax({
			type : "POST",
			url : baseUrl + "/Provider/updateProvider.action",
			data : params,
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
	}
	else{
		$.ajax({
			type : "POST",
			url : baseUrl + "/Provider/addProvider.action",
			data : params,
			dataType : "json",
			success : function(data) {
				if (data.code == "success") {
					if(continuityAdd){
						window.location.href = 'provider_edit.html?continuity='+continuityAdd;
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
function findProviderByid(id) {
	$.ajax({
		type : "POST",
		url : baseUrl + "/Provider/findProviderById.action",
		data : {"id" : id},
		async : false,
		dataType : "json",
		success : function(data) {
			//供应商编号
			$("#SupplierNumber").val(data.supplierNumber);
			//供应商名称
			$("#SupplierName").val(data.supplierName);
			//供应商地址
			$("#Address").val(data.address);
			//联系人
			$("#Contacts").val(data.contacts);
			//联系电话
			$("#Telephone").val(data.telephone);
			//其他联系方式
			$("#OtherNumbers").val(data.otherNumbers);
			//备注
			$("#Remarks").val(data.remarks);
			//年期初我方欠款(元)
			$("#ArrearsMoney").val(data.arrearsMoney);
		}
	});
}
//供应商编号
function changeSupplierNumber(value){
	if(value.length > 32){
		swal({
			title: "错误提示",
			text: "长度过长重新输入!",
			type: "error",
			confirmButtonText: '确定',
			cancelButtonFont: '微软雅黑',
		});
		$("#SupplierNumber").val("");
	}
}
//供应商名称
function changeSupplierName(value){
	if(value.length > 15){
		swal({
			title: "错误提示",
			text: "长度过长重新输入!",
			type: "error",
			confirmButtonText: '确定',
			cancelButtonFont: '微软雅黑',
		});
		$("#SupplierName").val("");
	}
	
}
//地址
function changeAddress(value){
	if(value.length > 15){
		swal({
			title: "错误提示",
			text: "长度过长重新输入!",
			type: "error",
			confirmButtonText: '确定',
			cancelButtonFont: '微软雅黑',
		});
		$("#Address").val("");
	}
}
//联系人
function changeContacts(value){
	if(value.length > 15){
		swal({
			title: "错误提示",
			text: "长度过长重新输入!",
			type: "error",
			confirmButtonText: '确定',
			cancelButtonFont: '微软雅黑',
		});
		$("#Contacts").val("");
	}
}
//联系电话
function changeTelephone(value){
	if(value.length > 11){
		swal({
			title: "错误提示",
			text: "长度过长重新输入!",
			type: "error",
			confirmButtonText: '确定',
			cancelButtonFont: '微软雅黑',
		});
		$("#Telephone").val("");
	}
	if(!(/^1[34578]\d{9}$/.test(value))){ 
		swal({
			title: "错误提示",
			text: "请输入有效11位手机号码!",
			type: "error",
			confirmButtonText: '确定',
			cancelButtonFont: '微软雅黑',
		});
		$("#Telephone").val("");
	} 
}
//其它联系方式
function changeOtherNumbers(value){
	if(value.length > 15){
		swal({
			title: "错误提示",
			text: "长度过长重新输入!",
			type: "error",
			confirmButtonText: '确定',
			cancelButtonFont: '微软雅黑',
		});
		$("#OtherNumbers").val("");
	} 
	
}
//年期初我方欠款
function changeArrearsMoney(value){
	if(value.length > 8){
		swal({
			title: "错误提示",
			text: "长度过长重新输入!",
			type: "error",
			confirmButtonText: '确定',
			cancelButtonFont: '微软雅黑',
		});
		$("#ArrearsMoney").val("");
	}
}
//备注
function changeRemarks(value){
	if(value.length > 200){
		swal({
			title: "错误提示",
			text: "长度过长重新输入!",
			type: "error",
			confirmButtonText: '确定',
			cancelButtonFont: '微软雅黑',
		});
		$("#Remarks").val("");
	}
}

 