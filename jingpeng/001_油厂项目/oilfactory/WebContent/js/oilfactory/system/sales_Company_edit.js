//销售公司id
var id = "";
var continuity ="";
$(function(){
	// 获取上页面传过来的值
	id = getUrlParam("id");
	//是否连续添加标识
	continuity = getUrlParam("continuity");
	if(continuity != null && continuity != ""){
		if(continuity == "true"){
			$("#continuityAdd").attr("checked", true);
		}
	}
	if(id != null && id != ""){
		$("#continuityTr").css('display','none');
		// 画面数据初始化
		$.ajax({
			type: "post",
			url: "../../salesCompany/getSalesCompanyInfo.action",
			data:{"id":id},
			dataType: "json",
			async:false,
			success: function (data) {
				SetFromValues($("#submitForm"), data);
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
//保存按钮
function addSalesCompany(){
	var continuityAdd = $("#continuityAdd").is(':checked')
	//获取页面的数据
	var data = formToJson($("#submitForm"));
	//销售公司编号
	if(data.companyNumber == ""){
		swal({
 			title: "错误提示",
 			text: "销售公司编号必须填写!",
 			type: "error",
 			confirmButtonText: '确定',
 			cancelButtonFont: '微软雅黑',
 		});
		return;
	}
	//销售公司名称
	if(data.companyName == ""){
		swal({
 			title: "错误提示",
 			text: "销售公司名称必须填写!",
 			type: "error",
 			confirmButtonText: '确定',
 			cancelButtonFont: '微软雅黑',
 		});
		return;
	}
	//销售公司地址
	if(data.companyAddress == ""){
		swal({
 			title: "错误提示",
 			text: "销售公司地址必须填写!",
 			type: "error",
 			confirmButtonText: '确定',
 			cancelButtonFont: '微软雅黑',
 		});
		return;
	}
	//联系人
	if(data.contacts == ""){
		swal({
 			title: "错误提示",
 			text: "销售公司联系人必须填写!",
 			type: "error",
 			confirmButtonText: '确定',
 			cancelButtonFont: '微软雅黑',
 		});
		return;
	}
	//电话
	if(data.telephone == ""){
		swal({
 			title: "错误提示",
 			text: "销售公司电话必须填写!",
 			type: "error",
 			confirmButtonText: '确定',
 			cancelButtonFont: '微软雅黑',
 		});
		return;
	}
	
	//路径
	var url;
	if(id != null && id != ''){
		data.id = id;
		//修改
		url = "../../salesCompany/updateSalesCompany.action";
	}else{
		//添加
		url = "../../salesCompany/addSalesCompany.action";
	}
	$.ajax({
		type: "post",
		url: url,
		data:data,
		dataType: "json",
		success: function (data) {
			if(data != null){
				if(data.code == "success"){
					if(url == "../../salesCompany/addSalesCompany.action"){
						if(continuityAdd){
							window.location.href = 'sales_Company_edit.html?continuity='+continuityAdd;
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
					}else{
						swal({
							title: "提示",
							text: data.message,
							type: data.code,
							confirmButtonText: '确定',
							cancelButtonFont: '微软雅黑',
						},function(){
							window.parent.$.fancybox.close();
						});
					}
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
//销售公司名称
function isJudgeCompanyName(){
	var companyName = $("#companyName").val();
	if(companyName.length>20){
		swal("操作失败", "销售公司名称长度过长", "info");
		$("#companyName").val("");
		return;
	}
}
//地址
function isJudgeCompanyAddress(){
	var companyAddress = $("#companyAddress").val();
	if(companyAddress.length>30){
		swal("操作失败", "地址长度过长", "info");
		$("#companyAddress").val("");
		return;
	}
}
//备注
function isJudgeRemarks(){
	var remarks = $("#remarks").val();
	if(remarks.length>200){
		swal("操作失败", "备注内容过长", "info");
		$("#remarks").val("");
		return;
	}
}
//联系电话
function changeTelephone(value){
	if(value.length > 11){
		swal({
			title: "错误提示",
			text: "联系电话长度过长重新输入!",
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
		$("#telephone").val("");
	} 
}