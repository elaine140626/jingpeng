// 当前登录用户id
var userId = ''; 
// 主键id
var id = '';
// 项目id
var ProjectId = '';
// 查看/编辑标识
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
			url : "../../OverhaulPavementNumber/getOverhaulPavementNumberById.action",
			data : {"id" : id},
			dataType : "json",
			success : function(data) {
				if(data != null && data.length > 0){
					SetFromValues($("#form1"), data[0]);
					ProjectId = data[0].projectId;
				}
				
				if(flag == 0){
					// 查看
					$('input[type="text"]').attr('disabled','disabled');
					// 隐藏保存按钮
					$("#saveButton").hide();
				}else if(flag == 1 || flag == 2){
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
	var url = '';
	if (id == null){
		// 新增
		url = '../../OverhaulPavementNumber/insertOverhaulPavementNumber.action';
		params.projectId = ProjectId;
		params.creater=userId;
	} else {
		if (flag == 1){
			// 修改
			url = '../../OverhaulPavementNumber/updateOverhaulPavementNumber.action';
			params.id = id;
			params.reviser=userId;
		} else {
			// 复制新增
			url = '../../OverhaulPavementNumber/insertOverhaulPavementNumber.action';
			params.projectId = ProjectId;
			params.creater=userId;
		}
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