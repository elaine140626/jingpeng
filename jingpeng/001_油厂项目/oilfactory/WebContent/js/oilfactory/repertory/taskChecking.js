// 用户
var userInfo = "";

// 权限判断
var rolecode = "";

$(document).ready(function(){
	
	// 获取用户信息
	userInfo = getUserInfo();
	if(userInfo != ""){
		// 用户权限
		var list = userInfo.rolecode;
		if(list.indexOf(",3,") != -1 || list.indexOf(",13,") != -1){
			// 调度 && 调度管理员(查看)
			rolecode = false;
		}
		if(list.indexOf(",9,") != -1 || list.indexOf(",10,") != -1 || list.indexOf(",11,") != -1 || list.indexOf(",12,") != -1){
			// 生产部长    生产副部长   生产班长   检斤员(查看)
			rolecode = false;
		}
		
		if(list.indexOf(",8,") != -1){
			// 库管(操作)
			rolecode = true;
		}
		
		if(list.indexOf(",0,") != -1){
			// 管理员(操作)
			rolecode = true;
		}
		if(list.indexOf(",7,") != -1){
			// 总经理(操作)
			rolecode = true;
		}
	}
	
	// 画面数据list初始化
	getList();
})

function getList(){
	//获取列表 刷新
	var table = $('#list').dataTable();
	table.fnDestroy();
	
	// 检索参数
	var param = formToJson($("#submitForm"));
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
			url : "../../taskChecking/getProductionPlanList.action",
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
            "data": "id",
            render: function(data, type, row) {
            	var html = '';
            	// 权限显示
            	if(rolecode){
            		if(row.isCheck == 0){
                		html +="<a class='fb' onclick=update("+row.id+",0)><img src=\"../../img/common/cha.png\" width=\"20\" height=\"20\" alt=\"查看\" title=\"查看\"></a>";
    				}else{
    					html +="<a class='fb' onclick=update("+row.id+",1)><img src=\"../../img/common/tijiao.png\" width=\"20\" height=\"20\" alt=\"核对\" title=\"核对\"></a>";
    				}
            	}else{
            		html +="<a class='fb' onclick=update("+row.id+",0)><img src=\"../../img/common/cha.png\" width=\"20\" height=\"20\" alt=\"查看\" title=\"查看\"></a>";
            	}
            	
	  		    return html;
            }
        }, {
        	 "data": "planNumber"
        }, {
            "data": "materielName"
        },{
        	"data": "materielModel"
        },{
            "data": "batch"
        },{
            "data": "finishTime"
        }, {
            "data": "planWeight"
        },
        {
        	"data":"unit"
        }, {
            "data": "isCheck",
            render: function(data, type, row) {
				if(data == 0){
					data = "是";
				}else{
					data = "否";
				}
				return data;
            }
        },
        {
            "data": "checkPerson"
        }, {
            "data": "checkDate"
        }],
        "order": [[ 1, 'asc' ]],
        "fnRowCallback" : function(nRow, aData, iDisplayIndex){
        	var html = '';
       		html += iDisplayIndex +1;        	
            $("td:first", nRow).html(html);//设置序号位于第一列，并顺次加一   
        	 // 调整行变色
            if(aData.isCheck == 1 && aData.adjustNumber > 0){                		
            	//设置满足条件行的背景颜色                		
            	$(nRow).css("background", "#ffcc99");                		
            	//设置满足条件行的字体颜色                		
            	$(nRow).css("color", "white !important");                	
            }else if(aData.isCheck == 0){
            	//设置满足条件行的背景颜色                		
            	$(nRow).css("background", "#999999");                		
            	//设置满足条件行的字体颜色                		
            	$(nRow).css("color", "white !important"); 
            }
            return nRow;
        }
    });
	$('.dataTables_paginate').parent('div').addClass('col-sm-12');
}

// 查询方法
function search(){
	getList();
}

// 修改
function update(id, flag){
	$.fancybox({
		'href': 'taskChecking_edit.html?id='+id+"&flag="+flag,
		'width': 800,
		'height': 425,
		'type': 'iframe',
		'hideOnOverlayClick': false,
		'showCloseButton': false,
		'onClosed': function() {
			window.location.href = 'taskChecking.html';
		}
	});
}

// 导出
function exportInfo(){
	//var param = formToJson($("#form1"));
	$.ajaxFileUpload({
		url : '../../outbound/export.action',
		//data:param,
		secureuri : false,
		fileElementId : 'file',
		success : function(res, status) {
		}
	});
}