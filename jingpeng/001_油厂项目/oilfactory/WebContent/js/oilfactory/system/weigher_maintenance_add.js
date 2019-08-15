//路径
var baseUrl = "";
//检斤员id
var id = "";
$(function() {
	baseUrl = getContextPath();
	id = getUrlParam("id");
	// 获取当前登录用户信息
	userInfo = getUserInfo();
	if(userInfo != ""){	
		creater = userInfo.name;
	}
	if(id !=null && id != ""){
		getWeigherMaintenance(id);
	}
});
//修改检斤员
function updateWeigherMaintenance(){
	var anotherName = $("#anotherName").val();
	options = {
			"anotherName" : anotherName,
			"id" : id,
	};
	 $.ajax({
         type: "post",
         url: baseUrl+'/WeigherMaintenance/updateWeigherMaintenance.action',
         async:false,
         data:JSON.stringify(options),
         dataType: "json",
         contentType : 'application/json;charset=UTF-8',
         success: function (data) {
        	 if(data.code == "200"){
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
        	 }else{
        		 swal("操作失败", data.message, "error");
        	 }
         }
    });
}
//通过id查询检斤员
function getWeigherMaintenance(id){
	$.ajax({
        type: "post",
        url: baseUrl+'/WeigherMaintenance/getWeigherMaintenance.action',
        async:false,
        data:{"id":id},
        dataType: "json",
        success: function (data) {
        	var data = data.data[0];
        	$("#name").val(data.name);
        	$("#anotherName").val(data.anotherName);
        }
   });
}

