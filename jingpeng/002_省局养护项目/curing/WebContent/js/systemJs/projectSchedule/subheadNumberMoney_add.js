// 当前登录用户id
var userId = ''; 
// 主键id
var id = '';
// 项目id
var ProjectId = '';
// 子目编号初始值
var initialSubheadNumber = '';
//查看/编辑标识
var flag = '';
$(function(){
	// 获取当前登录用户信息
	userInfo = getUserInfo();
	if(userInfo != ""){
		userId = userInfo.id;
	}
	id = getUrlParam("id");
	ProjectId = getUrlParam("ProjectId");
	flag = getUrlParam("flag");
	// 修改的场合
	if (id != null && id != ""){
		// 获取合同信息
		$.ajax({
			type : "post",
			url : "../../SubheadNumberMoney/getSubheadNumberMoney.action",
			data : {"id" : id},
			dataType : "json",
			success : function(data) {
				if(data != null && data.data.length > 0){
					SetFromValues($("#form1"), data.data[0]);
					initialSubheadNumber =  data.data[0].subheadNumber
				}
				
				if(flag == 0){
					// 查看
					$('input[type="text"]').attr('disabled','disabled');
					// 隐藏保存按钮
					$("#saveButton").hide();
				}else if(flag == 1){
					// 修改
					$('input[type="text"]').removeAttr('readonly','readonly');
					// 显示保存按钮
					$("#saveButton").show();
				}
			}
		});
	}
})

// 保存
function save(){	
	var params = formToJson($("#form1"));	
	// 子目号不能为空
	if(params.subheadNumber == ''){
		layer.alert('子目编号不能为空!', {
			icon: 2,
			title: "提示"
		});
		return;
	}
	// 子目号名称不能为空
	if(params.subheadName == ''){
		layer.alert('子目名称不能为空!', {
			icon: 2,
			title: "提示"
		});
		return;
	}
	var url = '';
	if (id == null){
		// 新增
		url = '../../SubheadNumberMoney/insertSubheadNumberMoney.action';
		params.projectId = ProjectId;
		params.creater=userId;
	} else {
		// 修改
		url = '../../SubheadNumberMoney/updateSubheadNumberMoney.action';
		params.id = id;
		params.reviser=userId;
	}
	$.ajax({
		type: 'POST',
		url: url,
		data:JSON.stringify(params),
		dataType: 'json',
		contentType: 'application/json',
		success: function(data){
			if (data.code == 'success'){
				// 操作成功
				layer.alert(data.message, {
					icon: 1,
					title: "提示"
				},function(){
					window.parent.location.reload();
				});
			} else {
				// 操作失败
				layer.alert(data.message, {
					icon: 2,
					title: "提示"
				});
			}			
		}
	});		
}

// 子母号判断
function onchangeSubheadNumber(){
	
	var currentSubheadNumber = $("#subheadNumber").val();
	
	if(currentSubheadNumber != ''){
		if(currentSubheadNumber != initialSubheadNumber){
			isHave(currentSubheadNumber);
		}
	}else{
		layer.alert('子目编号不能为空!', {
			icon: 2,
			title: "提示"
		});
	}
	
}

function isHave(currentSubheadNumber){
	$.ajax({
		type : "post",
		url : "../../SubheadNumberMoney/getSubheadNumberMoney.action",
		data : {"currentSubheadNumber" : currentSubheadNumber, "treeIds":ProjectId, "levels":'3'},
		dataType : "json",
		success : function(data) {
			console.log(data);
			if(data.data.length > 0){
				$("#subheadNumber").val('')
				layer.alert('子目编号重复', {
					icon: 2,
					title: "提示"
				});
			}
		}
	});
}

// 子目号名称去重
function isHaveSubheadName(){
	var currentSubheadName = $("#subheadName").val();
	if(currentSubheadName != ""){
		$.ajax({
			type : "post",
			url : "../../SubheadNumberMoney/getSubheadNumberMoney.action",
			data : {"treeIds":ProjectId, "levels":'3',"currentSubheadName":currentSubheadName},
			dataType : "json",
			success : function(data) {
				
				if(data.data.length > 0){
					$("#subheadName").val('')
					layer.alert('子目名称重复', {
						icon: 2,
						title: "提示"
					});
				}
			}
		});
	}else{
		layer.alert('子目名称不能为空!', {
			icon: 2,
			title: "提示"
		});
	}
}

function onchangeTotalPrice(){
	var total = $("#total").val();
	var unitPrice = $("#unitPrice").val();
	if(total != '' && unitPrice != ''){
		$("#totalPrice").val((total*1) * (unitPrice*1));
	}
}