var baseUrl = getContextPath();
var userId = '';
var powerOrgID='';
$(function() {
	$.ajax({
		type: "post",
		url: baseUrl + "/login/getSessionUserInfo.action",
		data:{},
		dataType: "json",
		async:false,
		success: function (data) {
			userInfo = data.bean.userInfo;
			powerOrgID = userInfo.powerOrgID;
			/*if(userInfo.powerOrgID != 0){
				userId = userInfo.id;
			}else{
				userId = 0;
			}*/
			userId = userInfo.id;
			if(powerOrgID != 0){
				$(".rightcon_wz").hide();
			}
			$('.userid').html(userInfo.userName);
		}
	});
 getList();
});

function getList() {
	var table = $('#userlist').dataTable();
	table.fnDestroy();
	$('#userlist').DataTable(
			{
				"paging" : true,
				"lengthChange" : false,
				"pageLength" : 14,
				"searching" : false,
				"ordering" : false,
				"info" : true,
				"sInfo" : true,
				"autoWidth" : false,
				"iDisplayStart" : 0,
				scrollY : "100%",
				scrollX : true,
				scrollCollapse : true,
				"language" : {
					"lengthMenu" : "每页 _MENU_ 条记录",
					"zeroRecords" : "没有找到记录",
					"info" : "第 _PAGE_ 页,共 _PAGES_ 页 (显示_END_ 条，共 _TOTAL_ 项)",
					"infoEmpty" : "无记录",
					"sSearch" : "在结果中查找：",
					"infoFiltered" : "(从 _MAX_ 条记录过滤)",
					"oPaginate" : {
						"sFirst" : "第一页",
						"sPrevious" : "上一页",
						"sNext" : "下一页",
						"sLast" : "最后一页"
					},
				},
				"ajax" : {
			           type: "post",
			           url: baseUrl+ "/User/getUserinfo.action",
			           dataSrc: "data",
			           data: function (d) {
			        	   var param = {};
			        	   param.id = userId;
			        	   param.powerOrgID = powerOrgID;
			               return param;//自定义需要传递的参数。
			           }
			       },
				"deferRender" : true,
				"columns" : [
					{"data" : "serialNumber"},
					{"data" : "str_user_Name"},
					{"data" : "str_name"},
					{"data" : "org_Name"},
					{"data" : "userId",
				        render:function(data,type,row,meta) {
			  	          var html = '';
			  		  	  html += "<a href='javascript:void(0);' onclick='show(\""+row.userId+"\",\""+powerOrgID+"\");'title=\"修改\">修改</a>";
			  		  	  html += "<a href='javascript:void(0);' onclick='del(\""+row.userId+"\",\""+powerOrgID+"\");' title=\"删除\">删除</a>";
			  		  	 return html;
		               }
					}],
				"columnDefs" : [ {
					"targets" : [ 0 ],
					"visible" : true,
					"searchable" : false
				} ]
		});
}

function show(id,powerOrgID) {
	window.location.href = baseUrl+"/page/mixLogin/mixStatinoUpdate.html?id="+id+'&powerOrgID='+powerOrgID;
}

function del(id,powerOrgID) {
	if(id == userId){
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
					url : baseUrl + "/User/del.action",
					data : {"id":id},
					async:false,
					dataType : "json",
					success : function(data) {
						if(data.code=="success"){
							setTimeout(function(){swal({
								title: data.message,
								type: "success",
								cancelButtonText: '确定',
								cancelButtonFont: '微软雅黑',
							},
							function(){
								if(powerOrgID == 0){
									getList();
								}else{
									// 首页
									window.location.href= baseUrl + '/User/out.action';
								}
							}); },200);
						}else{
							setTimeout(function(){swal(data.message,"","error"); },200);
						}
						
				}
			});
		});
	}
}