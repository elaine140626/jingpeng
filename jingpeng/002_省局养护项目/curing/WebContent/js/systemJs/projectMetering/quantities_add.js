// 当前登录用户id
var userId = ''; 
// 主键id
var id = '';
// 项目id
var ProjectId = '';
$(function(){
	// 获取当前登录用户信息
	userInfo = getUserInfo();
	if(userInfo != ""){
		userId = userInfo.id;
	}
	id = getUrlParam("id");
	ProjectId = getUrlParam("ProjectId");
	// 修改的场合
	if (id != null && id != ""){
		// 获取合同信息
		$.ajax({
			type : "post",
			url : "../..//.action",
			data : {"id" : id},
			dataType : "json",
			success : function(data) {
				if(data != null && data.length > 0){
					SetFromValues($("#form1"), data[0]);
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
		url = '../..//.action';
		params.projectId = ProjectId;
		params.creater=userId;
	} else {
		// 修改
		url = '../..//.action';
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