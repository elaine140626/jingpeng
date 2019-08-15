// 用户信息
var userInfo = '';
// 用户id
var userId = '';
// 用户的权限
var rolecode = '';
var param = {};
$(function(){ 
	// 获取当前登录用户信息
	userInfo = getUserInfo();
	if(userInfo != ""){	
		userId = userInfo.id;
		rolecode = userInfo.rolecode;	
		var flag = true;
		if(rolecode.indexOf(",0,") != -1){
			// 管理员(操作)
			flag = false;
		}
		
		if(rolecode.indexOf(",7,") != -1){
			// 总经理(操作)
			roleFlag = true;
			$("#addExchangeBtn").show();
		}
			
		// 非管理员只能查看自己的信息
		if(flag){
			param.id = userId;
		}
	}

	getUserInfoList(param);
 });

// 获取用户的信息
function getUserInfoList(param){
	$("#list").DataTable({
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
			type : "post",
			url : "../../login/getUserInfoList.action",
			dataSrc : "data",
			data : function(d) {
				return param;
			}
		},
        "deferRender": true,
        "columns": [ 
    	{
            "data": "id"
        },
        {
            "data": "username"
        }, {
            "data": "rolecode",
            render:function(data,type,row){
            	var roleName = "";
            	var rolecodeList = data.split(',');
            	for (var i = 0; i < rolecodeList.length; i++) {
            		var value = '';
            		if(rolecodeList[i] != ''){
            			value = rolecodeList[i];
            			if(value == 0){
                    		value = "管理员";
                    	}else if(value == 1){
                    		value = "销售员";
                    	}else if(value == 2){
                    		value = "销售总监";
                    	}else if(value == 3){
                    		value = "调度员";
                    	}else if(value == 4){
                    		value = "质检员";
                    	}else if(value == 5){
                    		value = "质检主任";
                    	}else if(value == 6){
                    		value = "财务";
                    	}else if(value == 7){
                    		value = "总经理";
                    	}else if(value == 8){
                    		value = "库管";
                    	}else if(value == 9){
                    		value = "生产部长";
                    	}else if(value == 10){
                    		value = "生产副部长";
                    	}else if(value == 11){
                    		value = "生产班长";
                    	}else if(value == 12){
                    		value = "检斤员";
                    	}else if(value == 13){
                    		value = "调度管理员";
                    	}
                    	
                    	roleName += value + ",";
                    	data = roleName.substring(0,roleName.length - 1);
            		}
				}
                return data;
            }
        }, {
            "data": "name"
        }, {
            "data": "age"
        }, {
            "data": "sex",
            render: function(data, type, row) {
				if(data == '1'){
					data = "男";
				}else{
					data = "女";
				}
				return data;
            }
        }, {
            "data": "birthday"
        }, {
            "data": "telephone"
        }, {
            "data": "othernumbers"
        }, {
            "data": "address"
        }, {
            "data": "id",
            render: function(data, type, row) {
				var html = "<input type='button' style='outline:none;border:none;background:none;margin-right:5px;color:green' value='修改' onclick='updateUser(\""+ row.id +"\")'>  ";
				html += "  <input type='button'  style='outline:none;border:none;background:none;margin-left:5px;color:red'value='删除' onclick='deleteUser(\""+ row.id +"\")'>";
				return html;
            }
        }],
        "order": [[ 1, 'asc' ]],
        "fnRowCallback" : function(nRow, aData, iDisplayIndex){
            $("td:first", nRow).html(iDisplayIndex +1);//设置序号位于第一列，并顺次加一
           return nRow;
        },
        "columnDefs": []
    });
	$('.dataTables_paginate').parent('div').addClass('col-sm-12');
}

// 修改用户信息
function updateUser(id){
	// 注册页面
	window.location.href = 'userInfo.html?id='+id;
}

//删除用户信息
function deleteUser(id){
	if(userId == id){
		swal({
			title: "错误提示",
			text: "不可删除当前用户!",
			type: "error",
			confirmButtonText: '确定',
			cancelButtonFont: '微软雅黑',
		});
	}else{
		$.ajax({
			type: "post",
			url: "../../login/deleteUserInfo.action",
			data:{"id":id},
			dataType: "json",
			success: function (data) {
				if(data.code == "success"){
					//获取列表 刷新
					var table = $('#list').dataTable();
					table.fnDestroy();
					getUserInfoList(param);
				}else{
					swal({
						title: "错误提示",
						text: data.message,
						type: data.code,
						confirmButtonText: '确定',
						cancelButtonFont: '微软雅黑',
					});
				}
			}
		});
	}
}