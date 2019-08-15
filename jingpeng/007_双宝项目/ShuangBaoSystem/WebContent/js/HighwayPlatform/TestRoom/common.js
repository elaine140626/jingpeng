//session超时
$(function(){
	$.ajaxSetup({ 
	    statusCode: {
	        401: function() { 
	            window.location.href="../../page/centerLogin/centerLogin.html"
	        }, 
	        403: function() { 
	        	window.location.href="../../page/centerLogin/centerLogin.html"
	        }
	    }
	})
});

// 获取试验室的权限树形
function getTestRooms(){
	var testRooms = '';
	$.ajax({
		type : "POST",
		url : "../../TestRecevied/getTestRoomList.action",
		async : false,
		dataType : "json",
		success : function(data) {
			testRooms = data;
		}
	});
	return testRooms;
}


//获取项目名称
function getProjectName() {
	$.ajax({
		type: "get",
		url: "../../js/set.json",
		async: true,
		dataType: 'json',
		success: function(data) {
			$(".topleft").html(data.projectName);
			$("head title").html(data.projectName);
		}
	});
}

// 获取当前用户的信息
function getUserInfo(){
	var userInfo = '';
	$.ajax({
        type: "post",
        url: "../../UserInfo/getPtUserInfo.action",
        data:{},
        async:false,
        dataType: "json",
        success: function (data) {
        	userInfo = data;
        	//获取当前登录人做显示
        	$(".userid").html(data.name);
        }
    });
	return userInfo;
}


// 获取试验名称
function getTestNameList(){
	// 获取试验名称
	$.ajax({
		type : "POST",
		url : "../../TestRecevied/getTestNameList.action",
		dataType : "json",
		success : function(data) {
			var html = "<option value='' selected='selected'>请选择</option>";
			for (var i = 0; i < data.length; i++) {
				html += "<option value='" + data[i].id + "'>"
						+ data[i].testname + "</option>"
			}
			
			// 清空操作
			$("#testPageInfosList").empty();
			$("#testPageInfosList").html(html);
		}
	});
}

//截取url字符串
function URL_Request(strName) {
	var strHref = document.location.toString();
	var intPos = strHref.indexOf("?");
	var strRight = strHref.substr(intPos + 1); //==========获取到右边的参数部分
	var arrTmp = strRight.split("&"); //=============以&分割成数组

	for(var i = 0; i < arrTmp.length; i++) //===========循环数组
	{
		var dIntPos = arrTmp[i].indexOf("=");
		var paraName = arrTmp[i].substr(0, dIntPos);
		var paraData = arrTmp[i].substr(dIntPos + 1);
		if(paraName.toUpperCase() == strName.toUpperCase()) {
			return paraData;
		}
	}
	return "";
}
