var baseUrl = "";
$(function() {
	baseUrl = getContextPath();
	getOrg();
});

function getOrg() {
	$.ajax({
		type : "POST",
		url : baseUrl + "/Register/getOrg.html",
		data : {},
		dataType : "json",
		success : function(data) {
			var html = "";
			for(var i = 0; i < data.length; i++) {
				html += "<div class='danxuan'><input name='i_power_Org_Id' type='radio' value='"+data[i].i_id+"'>"
                        +"<label for='checkbox'>"+data[i].str_org_Name+"</label></div>";
			}
			$("#org").html(html);
		}
	});
}

function add() {
	var password = $("#str_password").val();
	if($("#str_user_Name").val() == "") {
		/*alert("必须输入用户名");*/
		swal("操作失败","必须输入用户名", "info");
		return
	} 
	if(password == "") {
		/*alert("必须输入密码");*/
		swal("操作失败","必须输入密码", "info");
		return
	} 
	
	if($("#str_name").val() == "") {
		/*alert("必须输入姓名");*/
		swal("操作失败","必须输入姓名", "info");
		return
	} 
	var passwordAgin = $("#passwordAgin").val();
	if(password != passwordAgin) {
		/*alert("两次输入的密码不一致");*/
		swal("操作失败","两次输入的密码不一致", "info");
		return;
	}
	var a = $("input[type='radio']:checked").val();
	if(typeof(a) == "undefined") {
		/*alert("必须选择权限");*/
		swal("操作失败","必须选择权限", "info");
		return;
	}
	var params = $("#tab").serialize();
/*	$.ajax({
		type : "POST",
		url : baseUrl + "/Register/addUser.html",
		data : params,
		dataType : "json",
		success : function(data) {
			if(data.code == "300") {
				alert(data.message);
				return;
			}
			alert(data.message);
			window.location.href=baseUrl+"/Login/login.html"
		}
	});
	*/
	

	$.ajax({
		type : "POST",
		url : baseUrl + "/Register/addUser.html",
		data : params,
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
					window.location.href=baseUrl+"/Login/login.html";
				});
				
			}else{
				swal("操作失败",data.message, "error");
			}
		}
	});
}