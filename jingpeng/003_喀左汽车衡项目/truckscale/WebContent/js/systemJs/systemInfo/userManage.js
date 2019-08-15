// 当前用户信息
var userInfo = "";
var userList;
$(function(){
	// 获取当前登录用户信息
/*	userInfo = getUserInfo();
	if(userInfo != ""){
		userId = userInfo.id;
		roleCode = userInfo.roleCode;
	}*/
	
	// 用户的权限
	// datatable数据初始化
	
	$.ajax({
		type : 'POST',
		url : '../../UserManage/getUserInfoList.action',
		dataType : 'json',
		success : function(data) {
			userList = data.data
			var html = "<option value=''>查询全部</option>";
			for (var i = 0; i < userList.length; i++) {
				html += "<option value='" + userList[i].id + "'>"
				+ userList[i].name + "</option>"		
			}
			$("#nameList").empty();
			$("#nameList").append(html);
		}
	});
	var param = {};
	getUserInfoList(param);
})

// datatable数据初始化
function getUserInfoList(param){
	var table1 = $('#userInfoList').dataTable();
	table1.fnDestroy();
	$("#userInfoList").DataTable({
		 "paging": true,
         "lengthChange": false,
         "pageLength": 14,
         "searching": false,
         "ordering": false,
         "info": true,
         "sInfo": true,
         "autoWidth": false,
         "iDisplayStart" : 0,
         "language": {
             "lengthMenu": "每页 _MENU_ 条记录",
             "zeroRecords": "没有找到记录",
             "info": "第 _PAGE_ 页,共 _PAGES_ 页 (显示_END_ 条，共 _TOTAL_ 项)",
             "infoEmpty": "无记录",
             "sSearch": "在结果中查找：",
             "infoFiltered": "(从 _MAX_ 条记录过滤)",
             "oPaginate": {
                 "sFirst": "第一页",
                 "sPrevious": "上一页",
                 "sNext": "下一页",
                 "sLast": "最后一页"
             },
         },
        "ajax" : {
            type: "post",
            url: "../../UserManage/getUserInfoList.action",
            dataSrc: "data",
            data: function (d) {
                return param;//自定义需要传递的参数。
            }
        },
        "deferRender": true,
        "columns": [
        	{"data": "id"},
        	{
        		"data": "id",
        		render:function(data,type,row,meta) {
        			var html = '';
        			html += "<a onclick='updatePassWord("+row.id+")'style='text-decoration:none' href='javascript:;' title='重置密码'>重置</a>&nbsp;";
        			html += "<a onclick='updateUserInfo("+row.id+")'style='text-decoration:none' href='javascript:;' title='编辑'>编辑</a>&nbsp;";
        			html += "<a onclick='delUserInfo("+row.id+")' style='text-decoration:none' href='javascript:;' title='删除'>删除</a>";
        			return html;
        		}
        	},
        	{"data": "userName"},
        	{"data": "name"},     	
        	{"data": "sex",
        		render:function(data,type,row,meta) {
        			if(data == 0){
        				return '男';
        			}else{
        				return '女';
        			}
        		}},
        	{"data": "userPost"},
        	{"data": "telephone"},
        	{"data": "userState",
        		render:function(data,type,row,meta) {
        			if(data == 0){
        				return '启用';
        			}else{
        				return '未启用';
        			}
        		}},
        	{"data": "remarks"}
        	],
        "createdRow": function( row, data, dataIndex ) {
        	console.log(data);
        },
        "order": [],
        "fnRowCallback" : function(nRow, aData, iDisplayIndex){
        	var html = '';
        	html += iDisplayIndex +1;        	
            $("td:first", nRow).html(html);//设置序号位于第一列，并顺次加一      
            return nRow;
        },
        "columnDefs": []
    });
	$.fn.dataTable.ext.errMode = 'throw';//当出现问题时不弹出
	$('.table1_info').parent('div').addClass('col-sm-12');
}

function search(){
	var id = $("#nameList").val();
	var param = {"id":id};
	getUserInfoList(param);
}
// 修改用户信息
function updateUserInfo(id){
	location.href = "userManage_add.html?id="+id;
}
// 重置密码
function updatePassWord(id){
	layer.confirm('确认要重置密码吗？', 
			function(index) {
		var Reviser = 'admin';
				$.ajax({
					type: 'POST',
					url: '../../UserManage/updatePassWord.action',
					data: {"id":id,"Reviser":Reviser},
					dataType: 'json',
					success: function(data){
						if(data.code = "success"){
							layer.msg(data.message, {
								icon: 1,
								time: 1000
							},function(){
								location.reload();
							});
						}else{
							layer.msg(data.message, {
								icon: 2,
								time: 1000
							},function(){
								location.reload();
							});
						}
					}
				});
			});
}
//删除用户信息
function delUserInfo(id){
		layer.confirm('确认要删除吗？', 
			function(index) {
				$.ajax({
					type: 'POST',
					url: '../../UserManage/deleteUserInfo.action',
					data: {"id":id},
					dataType: 'json',
					success: function(data){
						if(data.code = "success"){
							layer.msg(data.message, {
								icon: 1,
								time: 1000
							},function(){
								location.reload();
							});
						}else{
							layer.msg(data.message, {
								icon: 2,
								time: 1000
							},function(){
								location.reload();
							});
						}
					}
				});
			});
}


// 新增用户
function add(){
	var index = layer.open({
		type: 2,
		title: '新增用户',
		content: 'userManage_add.html',
	});
	layer.full(index);
}