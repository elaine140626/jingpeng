var baseUrl = "";
//试验室选中id
var testId = "";
//登录人
var operator = "";
//显示的标签
var title = "用户注册";
//权限标注
var root = false;
//姓名
var name = "";

// 修改用户id
var id = getUrlParam("i_id");
$(function(){
	
	baseUrl = getContextPath();
	$.ajax({
        type: "post",
        url: "../testUserInfo/getTestUserName.html",
        data:{},
        async:false,
        dataType: "json",
        success: function (data) {
        	
        	if(data.issystemuser=="" || data.issystemuser==null || data.issystemuser==0){
        		root = true;
        	}
        	operator = data.userName;
        	//获取当前登录人做显示
        	name = data.userName;
        	$(".userid").html(data.userName);
        	
        }
    });
	
	// 试验室名称
	var authority = document.getElementsByName("authority");
	var children = document.getElementsByName("children");
	for (var j = 0; j < authority.length; j++) {
		// 获取选中项
		if (authority[j].checked) {
			testIds+= authority[j].value + ',';
		}
	}
	for (var j = 0; j < children.length; j++) {
		// 获取选中项
		if (children[j].checked) {
			testIds+= children[j].value + ','
		}
	}	
	
	if(name=="" || name==null){
		$("#rootUser").attr("style","display:none;");
	}
	//转码解析跳页参数
	if(id != null && id != ""){
		$('#userName').attr("disabled",true); 
		title = "用户修改";		
		$.ajax({
			type : "POST",
			url : baseUrl + "/testUserInfo/getTestNameUpdate.html",
			data : {id:id},
			dataType : "json",
			success : function(data) {
				//赋值操作
				var dt = data.data[0];
				$("#i_id").val(dt.list[0].id);
				$("#userName").val(dt.list[0].userCode);
				$("#password").val(dt.list[0].password);
				$("#repassword").val(dt.list[0].password);
				$("#name").val(dt.list[0].name);
				if(dt.list[0].IsCollector==true){ 
					$("#r1")[0].checked="checked";
				}else{
					$("#r2")[0].checked="checked";
				}
				
				if(dt.list[0].IsSamping==true){ 
					$("#s1")[0].checked="checked";
				}else{
					$("#s2")[0].checked="checked";
				}
				
				if(dt.list[0].RoleType==1){ 
					$("#sfsy").show();
					$("#sfcy").hide();
					$("#q1")[0].checked="checked";
				}else{
					$("#sfsy").hide();
					$("#sfcy").show();
					$("#q2")[0].checked="checked";
				}
				
				// tree選中
				var ck = $("input[type='checkbox']");
				for (var i = 0; i < ck.length; i++) {
					for (var j = 0; j < dt.list2.length; j++) {	
						if(ck[i].value==dt.list2[j].TestRoomName){
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
		var id = $("input[name='radio']:checked").val();
		if(id==1){
			$("#sfsy").show();
			$("#sfcy").hide();
		}else{
			$("#sfcy").show();
			$("#sfsy").hide();
		}
});

//试验室选中事件
d.checkNode = function (id,i_parent_Id,flag,obj,flag1) {
}


//添加修改方法
function add() {
	var testIds = "";
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
			testIds+= children[j].id.replace("children_","") + ','
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
	
	if($("input[name='radio']:checked").val()==1){
		if($("input[name='gender']:checked").val()==1){
			if($("input[type='checkbox']:checked").length>1){
					swal("操作失败","收样只能选择一个试验室！", "info");
					return;
			}
		}
	}	
	//判断 有id就是修改 没id就是增加
	var url = "../testUserInfo/addTestRoomName.html";
	if($("#i_id").val()!="" && $("#i_id").val()!=null){
		url= "../testUserInfo/updateTestUser.html";
	}

	$.ajax({
		type : "POST",
		url : url,
		data :{
			"userName":$("#userName").val(),
			"password":$("#password").val(),
			"name":$("#name").val(),
			"testId":testIds,
			"id":$("#i_id").val(),
			"operator":operator,
			"isCollector":$("input[name='gender']:checked").val(),
			"roleType":$("input[name='radio']:checked").val(),
			"isSamping":$("input[name='cy']:checked").val(),
		},
		dataType : "json",
		success : function(data) {
			if(data.code == "success") {
				if(data.message=="修改成功"){
					swal({
						title: data.message,
						type: "info",
						showCancelButton: false,
						confirmButtonColor: '#AEDEF4',
						confirmButtonText: '确定',
						cancelButtonFont: '微软雅黑',
						
					},
					function(){
						window.location.href = baseUrl+"/testUserInfo/login.html";
					});
				}else{
					swal({
						title: data.message,
						type: "info",
						showCancelButton: false,
						confirmButtonColor: '#AEDEF4',
						confirmButtonText: '确定',
						cancelButtonFont: '微软雅黑',
					},
					function(){
						window.location.href = baseUrl+"/testUserInfo/login.html"
					});
					
				}
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
