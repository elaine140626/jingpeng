var baseUrl = "";
var thisIds = "";
$(function() {
	baseUrl = getContextPath();
	$.ajax({
        type: "post",
        url: "../testUserInfo/getTestUserName.html",
        data:{},
        async:false,
        dataType: "json",
        success: function (data) {
        	//获取当前登录人做显示
        	$(".userid").html(data.userName);
        	thisIds = data.id;
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

function getList() {
	$.ajax({
		type : "POST",
		url : baseUrl + "/testUserInfo/getUserInformation.html",
		data : {},
		dataType : "json",
		success : function(data) {
			var list = data.data;
			isDelete = list;
	    	html = "";
	    	for (var j = 0; j < list.length; j++) {
	    		var testName = "|";
		    	for(var i = 0; i < list[j].list2.length; i++) {
		    		testName +=list[j].list2[i].TestRoomName+"|"
		    	}
	    		html += '<tr>'
    				+'<td>'+(j+1)+'</td>'
	                +'<td>'+list[j].list1.userCode+'</td>'
	                +'<td>'+list[j].list1.name+'</td>'
	                +'<td>'+testName+'</td>'
	                +'<td><span>'+list[j].list1.operation+'</span></td>'
	           +'</tr>'
			}
	    	$("#list").html(html);
		}
	});
}

function update(i_id) {
	window.location.href = baseUrl+"/testUserInfo/user.html?i_id="+i_id;
}

function del(i_id) {
	if(thisIds==i_id){
		swal("操作失败","不能删除当前登录的账号！", "info");
		return;
	}
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
			url : baseUrl + "/testUserInfo/testUserdel.html",
			data : {"id":i_id},
			async:false,
			dataType : "json",
			success : function(data) {
				if(data.code=="success"){
					location.reload();
				}else{
					swal("操作失败",data.message, "info");
					return;
				}
			}
		});
	
	});
}