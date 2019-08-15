// 当前用户信息
var userInfo = "";
// 用户权限
var roleCode = "";
$(function(){
	// 获取当前登录用户信息
	userInfo = getUserInfo();
	if(userInfo != ""){
		userId = userInfo.id;
		roleCode = userInfo.roleCode;
	}
	
	// 用户的权限
	// datatable数据初始化
	var param = {};
	if(roleCode != ""){
		if(roleCode != '0'){
			param.id = userId;
		}
	}
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
            url: "../../Login/getUserInfoList.action",
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
		  		    html += "<a onclick='updateUserInfo(\""+row.id+"\")'style='text-decoration:none; font-size:14px;' href='javascript:;' title='编辑'>&nbsp;编辑&nbsp;</a>";
		  		    html += "<a onclick='delUserInfo(\""+row.id+"\")' style='text-decoration:none; font-size:14px;' href='javascript:;' title='删除'>&nbsp;删除</a>";
		  		    return html;
	            }
        	},
        	{"data": "userName"},
        	{"data": "roleCode"},
        	{"data": "name"},     	
        	{"data": "age"},
        	{"data": "sex"},
        	{"data": "birthday"},
        	{"data": "address"},
        	{"data": "telephone"},
        	{"data": "otherNumbers"}
        	],
        "createdRow": function( row, data, dataIndex ) {
        	// console.log(data);
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
//	$.fn.dataTable.ext.errMode = 'throw';//当出现问题时不弹出
//	$('.table1_info').parent('div').addClass('col-sm-12');
}

// 修改用户信息
function updateUserInfo(id){
	location.href = "userInfo.html?id="+id;
}

//删除用户信息
function delUserInfo(id){
	if(userId != id){
		layer.confirm('确认要删除吗？', 
			function(index) {
				var params = {"id":id,"reviser":userId};	
				$.ajax({
					type: 'POST',
					url: '../../Login/deleteUserInfo.action',
					data: JSON.stringify(params),
					dataType: 'json',
					contentType: 'application/json',
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
	}else{
		layer.alert('不能删除当前登录用户！', {
			icon : 2,
			title : "提示"
		});
	}
}

//注册
function register(){
	location.href = "userInfo.html";
}