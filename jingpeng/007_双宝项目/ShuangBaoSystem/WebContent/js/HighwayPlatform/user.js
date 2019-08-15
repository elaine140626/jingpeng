// 试验室选中id
var testId = "";
// 登录人
var operator = "";
//显示的标签
var title = "用户注册";
// 用户权限判定(1:管理员   0:普通用户)
var userRoot = 0;
// 用户名
var userCode = "";
// 修改用户id
var id = getUrlParam("i_id");
var flag = getUrlParam("flag");
// 当前用户id
var userId = "";

$(function(){
	if(flag != 0){
		// 获取session中的用户信息
		$.ajax({
	        type: "post",
	        url: "../../UserInfo/getPtUserInfo.action",
	        data:{},
	        async:false,
	        dataType: "json",
	        success: function (data) {
	            userRoot = data.issystemuser;
	        	userCode = data.userCode;
	        	userId = data.id;
	        	$(".userid").html(data.name);
	        	
	        }
	    });	
	}
	
	// 显示用户信息(从修改页跳转)
	if(id != null && id != ""){
		$('#userName').attr("disabled",true); 
		title = "用户修改";		
		$.ajax({
			type : "POST",
			url : "../../UserInfo/getPtUserList.action",
			data : {id:id},
			dataType : "json",
			success : function(data) {
				//赋值操作
				var user = data.data[0];
				$("#i_id").val(user.userInfo.id);
				$("#userName").val(user.userInfo.userCode);
				$("#password").val(user.userInfo.passWord);
				$("#repassword").val(user.userInfo.passWord);
				$("#name").val(user.userInfo.name);
				// tree選中
				var ck = $("input[type='checkbox']");
				for (var i = 0; i < ck.length; i++) {
					// 试验室权限
					for (var j = 0; j < user.userTestDetailed.length; j++) {	
						if(ck[i].value== user.userTestDetailed[j].TestRoomName){
							ck[i].checked="checked";
						}
					}
					
					// 拌合站权限
					for (var j = 0; j < user.userOrgDetailed.length; j++) {	
						if(ck[i].value== user.userOrgDetailed[j].Org_Name){
							ck[i].checked="checked";
						}
					}
				}
			}
		});
	}
	$("#title")[0].innerHTML=title;
		
});

//单选按钮的事件
$(".radioItem").change(
		function() {
		var id = $("input[name='checkbox']:checked").val();
		if(id==0){
			$("#sfsy").show();
			$("#sfcy").hide();
		}else{
			$("#sfcy").show();
			$("#sfsy").hide();
		}
});

/*//试验室选中事件
d.checkNode = function (id,i_parent_Id,flag,obj,flag1) {
}*/

//添加修改方法
function add() {
	var testIds = "";
	var bhzIds = "";
	var roleType = "";
	// 试验室名称
	var authority = document.getElementsByName("authority");
	var children = document.getElementsByName("children");
	for (var j = 0; j < authority.length; j++) {
		// 获取选中项
		if (authority[j].checked) {
			testIds+= authority[j].id.replace("authority_","") + ',';
		}
	}
	for (var j = 0; j < children.length; j++) {
		// 获取选中项
		if (children[j].checked) {
			testIds+= children[j].id.replace("children_","") + ',';
		}
	}	
	
	// 拌合站
	var authority1 = document.getElementsByName("authority1");
	for (var j = 0; j < authority1.length; j++) {
		// 获取选中项
		if (authority1[j].checked) {
			bhzIds += authority1[j].id.replace("authority1_","") + ',';
		}	
	}
	if($("#userName").val() == "") {
		swal("操作失败","请输入用户名！", "info");
		return
	} 
	var password = $("#password").val();
	if(password == "") {
		swal("操作失败","请输入密码！", "info");
		return
	} 
	if(password.length<6){
		swal("操作失败","密码长度不能小于6位！", "info");
		return
	}
	if($("#name").val() == "") {
		swal("操作失败","请输入姓名！", "info");
		return
	} 
	var passwordAgin = $("#repassword").val();
	if(password != passwordAgin) {
		swal("操作失败","两次输入的密码不一致！", "info");
		return;
	}
	var a = $("input[type='checkbox']:checked").val();
	if(typeof(a) == "undefined") {
		swal("操作失败","请选择权限！", "info");
		return;
	}
	
	if($("input[name='checkbox']:checked").val() == 0){
		if($("input[name='gender']:checked").val() == 1){
			if(testIds.substr(0,testIds.length - 1).split(',').length > 1){
					swal("操作失败","收样只能选择一个试验室！", "info");
					return;
			}
		}
	}	
	
	//判断 有id就是修改 没id就是增加
	var url = "../../UserInfo/insertUserInfo.action";
	if(id !=null && id != ""){
		url= "../../UserInfo/updateUserInfo.action";
	}
		
	$.ajax({
		type : "POST",
		url : url,
		data :{
			"userCode":$("#userName").val(),
			"passWord":$("#password").val(),
			"name":$("#name").val(),
			"testId":testIds,// 试验室权限信息
			"bhzId":bhzIds.substr(0,bhzIds.length - 1), // 拌合站权限信息
			"id":$("#i_id").val(),
			"operator":operator,
			"isCollector":$("input[name='gender']:checked").val(),
			"roleType":$("input[name='checkbox']:checked").val(),
			"isSamping":$("input[name='cy']:checked").val(),
		},
		dataType : "json",
		success : function(data) {
			if(data.code == "success") {
				swal({
					title: data.message,
					type: "success",
					showCancelButton: false,
					confirmButtonColor: '#AEDEF4',
					confirmButtonText: '确定',
					cancelButtonFont: '微软雅黑',
					
				},
				function(){
					if(userRoot == 1 && userId != id){
						window.location.href = "../../page/centerLogin/userinfo.html";
					}else{
						window.location.href = "../../page/centerLogin/centerLogin.html";
					}
					
				});
			}else{
				swal("操作失败",data.message, "info");
				return;
			}
		}
	});
}



//获取前页面参数
function getUrlParam(name) {
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)"); //构造一个含有目标参数的正则表达式对象
    var r = window.location.search.substr(1).match(reg); //匹配目标参数
    if(r != null){
        return decodeURIComponent(r[2]);
//这里为什么是从第三个字符解析呢？不知道这样理解对不对，因为路径后面的参数形式为参数名=参数值，而第一个字符为参数名，第二个为=，第三个就为参数值了。。。因为下面调用的时候得出的就是参数值
    }
    return null;//返回参数值
}

function outLogin() {
	if(userId != null && userId != ""){
		$.ajax({
			type : "POST",
			url : "../../UserInfo/delUserSession.action",
			data : {},
			dataType : "json",
			success : function(data) {
				if (data.code == 'error') {
					// 失败信息
					swal("操作失败", data.message, data.code);
				} else {
					// 首页
					$(location).attr('href', '../../page/centerLogin/centerLogin.html');
				}
			}
		});
	}else{
		// 首页
		$(location).attr('href', '../../page/centerLogin/centerLogin.html');
	}
}