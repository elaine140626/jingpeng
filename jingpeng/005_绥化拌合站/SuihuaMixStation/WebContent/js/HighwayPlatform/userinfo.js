
// 用户的id
var userId = "";
var id = '';
// 当前登录用户的角色
var role = 0;
$(function() {
	$.ajax({
        type: "post",
        url: "../../UserInfo/getPtUserInfo.action",
        data:{},
        async:false,
        dataType: "json",
        success: function (data) {
        	//获取当前登录人做显示
        	$(".userid").html(data.name);
        	userId = data.id;
        	id = data.id;
        	role = data.issystemuser;
        }
    });

	// 获取平台用户信息
	getList();

});

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

// 用户的list
function getList() {
	
	// 管理员角色
	if(role == 1){
		userId = '';
	}
	
	$.ajax({
		type : "POST",
		url :"../../UserInfo/getPtUserList.action",
		data : {"id":userId},
		dataType : "json",
		success : function(data) {
			var list = data.data;
			console.log(list);
			html = "";
			for(var i=0; i<list.length; i++){
				// 试验室名称
	    		var testName = "|";
	    		if(list[i].userTestDetailed != null && list[i].userTestDetailed.length > 0){
	    			for(var j = 0; j < list[i].userTestDetailed.length; j++) {
		    			testName +=list[i].userTestDetailed[j].TestRoomName+"|";
		    	}
	    		}
		    	
		    	// 拌合站名称
		    	var orgName = "|";
		    	if(list[i].userOrgDetailed != null && list[i].userOrgDetailed.length > 0){
		    		for(var j = 0;  j < list[i].userOrgDetailed.length; j++) {
		    			orgName +=list[i].userOrgDetailed[j].Org_Name+"|";
		    	    }
		    	}
		    	
		    	//预警等级
		    	var warningLevel = "";
		    	if(list[i].userInfo.warningLevel == "0"){
		    		warningLevel = "初级预警";
		    	}else if(list[i].userInfo.warningLevel == "1"){
		    		warningLevel = "中级预警";
		    	}else if(list[i].userInfo.warningLevel == "2"){
		    		warningLevel = "高级预警";
		    	}
		    	
	    		html += '<tr>'
					+'<td>'+(i+1)+'</td>'
	                +'<td>'+list[i].userInfo.userCode+'</td>'
	                +'<td>'+list[i].userInfo.name+'</td>'
	                +'<td>'+warningLevel+'</td>'
	                +'<td>'+orgName+'</td>'
	                +"<td><span><a href='javascript:void(0)' onclick='update(\""+list[i].userInfo.id+"\")'>修改</a><a href='javascript:void(0)' onclick='del(\""+list[i].userInfo.id+"\")'>删除</a></span></td>"
	                +'</tr>';   		
			}			
	    	$("#list").html(html);
		}
	});
}

// 更新用户
function update(i_id) {
	window.location.href = "../../page/centerLogin/user.html?i_id="+i_id;
}

// 删除用户
function del(i_id) {
	if(id == i_id){
		swal("操作失败","不能删除当前登录的账号！", "info");
		return;
	}else{
		swal({
			title: "确定操作吗？",
			type: "warning",
			showCancelButton: true,
			confirmButtonColor: '#AEDEF4',
			confirmButtonText: '确定',
			cancelButtonText: '取消',
			cancelButtonFont: '微软雅黑',	
		},
		function(){
			$.ajax({
				type : "POST",
				url : "../../UserInfo/deleteUserInfo.action",
				data : {"id":i_id},
				async:false,
				dataType : "json",
				success : function(data) {
					if(data.code=="success"){
						swal("操作成功",data.message, "info");
						// 刷新当前页面
						window.location.href = "../../page/centerLogin/userinfo.html";
					}else{
						swal("操作失败","操作失败！", "error");
						return;
					}
					
				}
			});	
		});
	}
}

function outLogin() {
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
}