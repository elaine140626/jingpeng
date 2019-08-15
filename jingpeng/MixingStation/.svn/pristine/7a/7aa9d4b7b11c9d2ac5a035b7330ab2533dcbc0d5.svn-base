var baseUrl = "";
$(function() {
	baseUrl = getContextPath();
	getshowInfo();
});

function getshowInfo() {
	$.ajax({
		type : "POST",
		url : baseUrl + "/User/getshowInfo.action",
		data : {},
		dataType : "json",
		success : function(data) {
			var user = data.user;
			$("#str_user_Name").val(user.str_user_Name);
			$("#str_name").val(user.str_name);
			$("#i_id").val(user.i_id);
			var html = "";
			var orgs = data.orgs;
			for(var i = 0; i < orgs.length; i++) {
				/*if(user.i_power_Org_Id == orgs[i].i_id) {
					html += "<div class='danxuan'><input name='i_power_Org_Id' type='radio' value='"+orgs[i].i_id+"' checked='checked'>"
                         +"<label for='checkbox'>"+orgs[i].str_org_Name+"</label></div>";
				}*/
				html += "<div class='danxuan'><input name='i_power_Org_Id' type='radio' value='"+orgs[i].i_id+"'>"
                        +"<label for='checkbox'>"+orgs[i].str_org_Name+"</label></div>";
			}
			$("#org").html(html);
		}
	});
}

function add() {
	var password = $("#str_password").val();
	if($("#str_user_Name").val() == "") {
		alert("必须输入用户名");
		return
	} 
	if(password == "") {
		alert("必须输入密码");
		return
	} 
	
	if($("#str_name").val() == "") {
		alert("必须输入姓名");
		return
	} 
	var passwordAgin = $("#passwordAgin").val();
	if(password != passwordAgin) {
		alert("两次输入的密码不一致");
		return;
	}
	var a = $("input[type='radio']:checked").val();
	if(typeof(a) == "undefined") {
		alert("必须选择权限");
		return;
	}
	var params = $("#tab").serialize();
	$.ajax({
		type : "POST",
		url : baseUrl + "/User/updateUser.action",
		data : params,
		async:false,
		dataType : "json",
		success : function(data) {
			if(data.code=="success"){
				swal({
					title: data.message,
					text: "",
					type: "success",
					confirmButtonText: '确定',
					cancelButtonFont: '微软雅黑',
				},
				function(){
					window.location.href=baseUrl+"/Login/login.action";
				});
				
			}else{
				swal("操作失败",data.message, "error");
			}
		}
	});
}