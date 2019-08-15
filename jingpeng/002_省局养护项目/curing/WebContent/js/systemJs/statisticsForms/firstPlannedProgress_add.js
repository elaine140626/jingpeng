// 当前登录用户id
var userId = ''; 
// 市id
var cityId = '';
//批次
var planBatch = '';
//查看/修改标识
var flag = '';
$(function(){
	// 获取当前登录用户信息
	userInfo = getUserInfo();
	if(userInfo != ""){
		userId = userInfo.id;
	}
	cityId = getUrlParam("cityId");
	planBatch = getUrlParam("planBatch");
	flag = getUrlParam("flag");
	// 修改的场合
	if (cityId != null && cityId != ""){
		// 获取合同信息
		$.ajax({
			type : "post",
			url : "../../FirstPlannedProgress/getFirstPlannedProgressByCityId.action",
			data : {"CityId" : cityId,"PlanBatch":planBatch},
			dataType : "json",
			success : function(data) {
				if(data != null && data.length > 0){
					SetFromValues($("#form1"), data[0]);					
				}
				if(flag == 0){
					// 查看
					$('input[type="text"]').attr('disabled','disabled');
					$('select').attr('disabled','disabled');
					$('select').css('background-color','rgb(235, 235, 228)');
					// 隐藏保存按钮
					$("#saveButton").hide();
				}else if(flag == 1){
					// 修改
					$('input[type="text"]').removeAttr('readonly','readonly');
					$('select').removeAttr('disabled','disabled');
					$('select').css('background-color','#fff');
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
	var url = '../../FirstPlannedProgress/insertFirstPlannedProgress.action';
	params.cityId = cityId;
	params.planBatch = planBatch;// 计划批次
	params.creater=userId;
	params.reviser=userId;
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